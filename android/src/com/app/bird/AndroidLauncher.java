package com.app.bird;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.messaging.FirebaseMessaging;
import com.app.bird.database.SqliteManager;
import com.app.bird.manager.FirebaseManager;
import com.app.bird.manager.ReminderManager;
import com.app.bird.managers.GlobalHelpers;
import com.app.bird.model.ScoreItem;
import com.app.bird.screens.ChooseLevel;
import com.app.bird.screens.freemode.FreeModeFinishScreen;
import com.app.bird.screens.levelmode.LevelModeFinishScreen;
import com.app.bird.ui.AboutFlightlessBirdActivity;

import java.util.ArrayList;
import java.util.Locale;

import de.golfgl.gdxgamesvcs.GameServiceException;
import de.golfgl.gdxgamesvcs.GpgsClient;
import de.golfgl.gdxgamesvcs.IGameServiceListener;
import mk.gdx.firebase.GdxFIRApp;

import static com.app.bird.util.Constans.HIDE_ADS;
import static com.app.bird.util.Constans.IS_LOGINED;
import static com.app.bird.util.Constans.LAST_OPENED_APP;
import static com.app.bird.util.Constans.SHOW_ADS;

public class AndroidLauncher extends AndroidApplication implements GlobalHelpers, PlayServices {


    private String TAG = "AndroidLauncher";
    FirebaseAuth auth;
    private GpgsClient gpgsClient;
    private SharedPreferences sharedPref;
    private boolean activityIsRunning = false;
    private CustomAdView adView;
    private InterstitialAd mInterstitialAd;
    private AlertDialog alertDialog;
    private AlertDialog.Builder alertDialogBuilder;
    GameMain gameMain = null;
    int interstitialCounter = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeAdSource();
        init();
        FirebaseManager.getInstance().initializeFirebase(this);
        auth = FirebaseManager.getInstance().getFirebaseAuth();
        AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
        gameMain = new GameMain(this, this);
        View gameView = initializeForView(gameMain, config);
        RelativeLayout layout = new RelativeLayout(this);
        RelativeLayout.LayoutParams adParams =
                new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT);
        adParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        adParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        layout.addView(gameView);
        layout.addView(adView, adParams);
        adView.initializeAdView();
        adView.setVisibility(View.GONE);
        setContentView(layout);
        initGpgsClient();
        initLoginDialog();
        if (!sharedPref.getBoolean("isFirstOpen", false)) {
            ReminderManager.getInstance().initReminders(getApplicationContext());
        }
        if (Locale.getDefault().getLanguage().contains("tr")) {
            FirebaseMessaging.getInstance().subscribeToTopic("trUsers");
        } else {
            FirebaseMessaging.getInstance().subscribeToTopic("foreignUsers");
        }
        sharedPref.edit().putBoolean("isFirstOpen", true).apply();
        sharedPref.edit().putLong(LAST_OPENED_APP, System.currentTimeMillis()).apply();
        Log.d(TAG, "reachedlastlevel: "+ new SqliteManager(this).reachedLastLevel());
    }

    private void initLoginDialog() {
        alertDialogBuilder = new AlertDialog.Builder(AndroidLauncher.this);
        if (Locale.getDefault().getLanguage().equals("tr")) {
            alertDialog = alertDialogBuilder.setMessage("\n\nLiderlik sıralamasını görmek istiyor musun ?\n\n")
                    .setTitle("").setCancelable(true)
                    .setPositiveButton("Giriş yap", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            signIn();
                        }
                    }).setNegativeButton("Kapat", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    }).create();
        } else {
            alertDialog = alertDialogBuilder.setMessage("\n\nDo you wanna see leadership ranking ?\n\n")
                    .setTitle("").setCancelable(true)
                    .setPositiveButton("Sign Up", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            signIn();
                        }
                    }).setNegativeButton("Close", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    }).create();
        }
    }

    private void initializeAdSource() {
        MobileAds.initialize(this, getString(R.string.admob_id));
        //   StartAppAd.disableSplash();
        //   StartAppSDK.init(this, getString(R.string.start_app_id), false);
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getString(R.string.admob_interstitial));
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                checkIfLoadedAdThenShow();
            }
        });
    }

    private void checkIfLoadedAdThenShow() {
        if (mInterstitialAd.isLoaded()) {
            if (gameMain.getScreen() instanceof ChooseLevel || gameMain.getScreen() instanceof FreeModeFinishScreen || gameMain.getScreen() instanceof LevelModeFinishScreen) {
                mInterstitialAd.show();
            }
        }
    }


    protected Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SHOW_ADS: {
                    adView.setVisibility(View.VISIBLE);
                    break;
                }
                case HIDE_ADS: {
                    adView.setVisibility(View.GONE);
                    break;
                }
            }
        }
    };

    private void initGpgsClient() {
        gpgsClient = new GpgsClient().initialize(this, false);
        gpgsClient.setListener(new IGameServiceListener() {
            @Override
            public void gsOnSessionActive() {
                // Toast.makeText(AndroidLauncher.this, "Hoşgeldin", Toast.LENGTH_SHORT).show();
               /* try {
                    gpgsClient.showLeaderboards(getString(R.string.leaderboard_kahramanlar_siralamasi));
                } catch (GameServiceException e) {
                    e.printStackTrace();
                } */
                sharedPref.edit().putBoolean(IS_LOGINED, true).apply();
            }

            @Override
            public void gsOnSessionInactive() {
            }

            @Override
            public void gsShowErrorToUser(GsErrorType et, String msg, Throwable t) {
                if (Locale.getDefault().getLanguage().equals("tr")) {
                    Toast.makeText(AndroidLauncher.this, "Giriş yapılırken hata oluştu. Tekrar dene.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(AndroidLauncher.this, "Error while logging in. Try again.", Toast.LENGTH_SHORT).show();
                }
                sharedPref.edit().putBoolean(IS_LOGINED, false).apply();
            }
        });
        if (sharedPref.getBoolean(IS_LOGINED, false)) {
            gpgsClient.resumeSession();
        }
        GdxFIRApp.instance().configure();

    }

    private void init() {
        sharedPref = getApplicationContext().getSharedPreferences("info", Context.MODE_PRIVATE);
        adView = new CustomAdView(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        activityIsRunning = true;
        Log.d(TAG, "onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        activityIsRunning = false;
    }

    @Override
    public void setScore(ArrayList<ScoreItem> scores) {
        SqliteManager sqliteManager = new SqliteManager(getApplicationContext());
        sqliteManager.clearSavedChannels();
        sqliteManager.addItemToScores(scores);
    }

    @Override
    public ArrayList<ScoreItem> getScores() {
        SqliteManager sqliteManager = new SqliteManager(getApplicationContext());
        ArrayList<ScoreItem> items = sqliteManager.takeChannels();
        if (items.isEmpty()) {
            for (int i = 0; i < 25; i++) {
                items.add(new ScoreItem());
            }
        }
        return sqliteManager.takeChannels();
    }

    @Override
    public void createToast(String message) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public void setScoreItem(ScoreItem scoreItem) {
        SqliteManager sqliteManager = new SqliteManager(getApplicationContext());
        sqliteManager.updateScore(scoreItem);
    }

    @Override
    public void startAboutFlightlessBirdActivity() {
        if (activityIsRunning) {
            startActivity(new Intent(this, AboutFlightlessBirdActivity.class));
        }
    }

    @Override
    public void showInterstitialAd() {
        if (interstitialCounter == 4) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mInterstitialAd.loadAd(new AdRequest.Builder().build());
                    checkIfLoadedAdThenShow();
                }
            });
            interstitialCounter = 0;
        }
        interstitialCounter++;
      /*  if (AppAdManager.getInstance().activeInterstitialAdType == AppAdManager.AD_TYPE.ADMOB) {


        } else {
            StartAppAd.showAd(this);
        }*/
    }

    @Override
    public void adViewVisibility(boolean isShow) {
        if (adView != null) {
            handler.sendEmptyMessage(isShow ? HIDE_ADS : SHOW_ADS);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (gpgsClient.getPlayerDisplayName() != null) {
            // Toast.makeText(this, "Hoşgeldin " + gpgsClient.getPlayerDisplayName(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (gpgsClient != null) {
            gpgsClient.onGpgsActivityResult(requestCode, resultCode, data);
        }
    }

    private void createLoginDialog() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (!alertDialog.isShowing()) {
                    alertDialog.show();
                }
            }
        });
    }

    @Override
    public void signIn() {
        gpgsClient.logIn();
    }

    @Override
    public void signOut() {

    }

    @Override
    public void rateGame() {

    }

    @Override
    public void showScore() {
        try {
            gpgsClient.showLeaderboards(getString(R.string.leaderboard_kahramanlar_siralamasi));
        } catch (GameServiceException e) {
            createLoginDialog();
            e.printStackTrace();
        }
    }

    @Override
    public void showDutyList() {
        try {
            gpgsClient.showAchievements();
        } catch (GameServiceException e) {
            createLoginDialog();
            e.printStackTrace();
        }
    }

    @Override
    public void setScoreToLeaderBoard(int score) {
        gpgsClient.submitToLeaderboard(getString(R.string.leaderboard_kahramanlar_siralamasi), score
                , "leaderBoard");
    }

    @Override
    public void achievementLevel(int level) {
        switch (level) {
            case 5:
                gpgsClient.unlockAchievement(getString(R.string.achievement_you_have_passed_5th_level));
                break;
            case 7:
                gpgsClient.unlockAchievement(getString(R.string.achievement_you_have_passed_7th_level));
                break;
            case 10:
                gpgsClient.unlockAchievement(getString(R.string.achievement_you_have_passed_10th_level));
                break;
            case 12:
                gpgsClient.unlockAchievement(getString(R.string.achievement_you_have_passed_12th_level));
                break;
            case 15:
                gpgsClient.unlockAchievement(getString(R.string.achievement_you_have_passed_15th_level));
                break;
            case 17:
                gpgsClient.unlockAchievement(getString(R.string.achievement_you_have_passed_17th_level__incredible));
                break;
            case 20:
                gpgsClient.unlockAchievement(getString(R.string.achievement_you_have_passed_20th_level_what__you_are_legendary));
                break;
        }
    }

    @Override
    public boolean isSignedIn() {
        return gpgsClient.isSessionActive();
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }


}
