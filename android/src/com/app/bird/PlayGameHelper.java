package com.app.bird;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Gravity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.Player;
import com.google.android.gms.games.PlayersClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;


public class PlayGameHelper implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {

    private static PlayGameHelper INSTANCE = null;
    private Activity activity = null;

    public LoginProcessListener listener = null;

    public final static int CLIENT_NONE = 0x00;
    public final static int CLIENT_GAMES = 0x01;
    public final static int CLIENT_PLUS = 0x02;
    public final static int CLIENT_SNAPSHOT = 0x08;
    public final static int CLIENT_ALL = CLIENT_GAMES | CLIENT_PLUS;
    int mRequestedClients = CLIENT_NONE;
    GoogleApiClient mGoogleApiClient = null;
    GoogleApiClient.Builder mGoogleApiClientBuilder = null;
    Games.GamesOptions mGamesApiOptions = Games.GamesOptions.builder().build();

    public static PlayGameHelper getInstance(Activity activity) {
        if (INSTANCE == null) {
            INSTANCE = new PlayGameHelper(activity);
        }
        return INSTANCE;
    }

    private PlayGameHelper(Activity activity) {
        this.activity = activity;
        /*if (mGoogleApiClientBuilder == null) {
            createApiClientBuilder();
        }
        mGoogleApiClient = mGoogleApiClientBuilder.build();*/

    }


    boolean isSignedIn() {
        return GoogleSignIn.getLastSignedInAccount(activity) != null;
    }

    void signInSilently() {
        GoogleSignInClient signInClient = GoogleSignIn.getClient(activity,
                GoogleSignInOptions.DEFAULT_GAMES_SIGN_IN);
        signInClient.silentSignIn().addOnCompleteListener(activity,
                new OnCompleteListener<GoogleSignInAccount>() {
                    @Override
                    public void onComplete(@NonNull Task<GoogleSignInAccount> task) {
                        if (task.isSuccessful()) {
                            // The signed in account is stored in the task's result.
                            GoogleSignInAccount signedInAccount = task.getResult();
                            PlayersClient playersClient = Games.getPlayersClient(activity, signedInAccount);
                            playersClient.getCurrentPlayer().addOnSuccessListener(
                                    new OnSuccessListener<Player>() {
                                        @Override
                                        public void onSuccess(Player player) {
                                            if (listener != null) {
                                                listener.success(signedInAccount, player);
                                            }
                                        }
                                    }
                            );
                        } else {
                            // Player will need to sign-in explicitly using via UI
                            if (listener != null) {
                                listener.failure();
                            }
                        }
                    }
                });
    }

    void signOut() {
        GoogleSignInClient signInClient = GoogleSignIn.getClient(activity,
                GoogleSignInOptions.DEFAULT_GAMES_SIGN_IN);
        signInClient.signOut().addOnCompleteListener(activity,
                new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        // at this point, the user is signed out.
                        if (listener != null) {
                            listener.signOut();
                        }
                    }
                });
    }

    void showPopup() {
        Games.setGravityForPopups(mGoogleApiClient, Gravity.TOP);
    }

    public GoogleApiClient.Builder createApiClientBuilder() {
        GoogleApiClient.Builder builder = new GoogleApiClient.Builder(
                activity, this, this);

        if (0 != (mRequestedClients & CLIENT_GAMES)) {
            builder.addApi(Games.API, mGamesApiOptions);
            builder.addScope(Games.SCOPE_GAMES);
        }
        mGoogleApiClientBuilder = builder;
        return builder;
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    interface LoginProcessListener {
        void success(GoogleSignInAccount account, Player player);

        void failure();

        void alreadyLogined();

        void signOut();
    }

}
