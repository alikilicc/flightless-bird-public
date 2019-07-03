package com.app.bird.manager;

import android.content.Context;

import com.app.bird.model.DatabaseObject;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;

public class FirebaseManager {

    public static FirebaseManager INSTANCE;
    private FirebaseAuth firebaseAuth = null;
    private FirebaseApp firebaseApp = null;
    private DatabaseObject database;

    public static FirebaseManager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new FirebaseManager();
        }

        return INSTANCE;
    }


    public void initializeFirebase(Context context) {
        Gson gson = new Gson();
        try {
            String json = inputStreamToString(context.getAssets().open("google-services.json"));
            database = gson.fromJson(json, DatabaseObject.class);
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setApiKey(database.getClient().get(0).getApiKey().toString())
                    .setApplicationId(database.getClient().get(0).getClientInfo().getMobilesdkAppId())
                    .setDatabaseUrl(database.getProjectInfo().getFirebaseUrl())//Firestore kullanılacaksa bu alan zorunludur.
                    //.setStorageBucket(database.getStorage_bucket().toString())// Storage kullanılacaksa bu alan zorunludur.
                    .setProjectId(database.getProjectInfo().getProjectId())
                    .build();
            FirebaseApp.initializeApp(context.getApplicationContext(), options, "FBirdFirebase");
            firebaseApp = FirebaseApp.getInstance("FBirdFirebase");
            firebaseAuth = FirebaseAuth.getInstance(firebaseApp);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public String inputStreamToString(InputStream inputStream) {
        try {
            byte[] bytes = new byte[inputStream.available()];
            inputStream.read(bytes, 0, bytes.length);
            String json = new String(bytes);
            return json;
        } catch (IOException e) {
            return null;
        }
    }

    public FirebaseAuth getFirebaseAuth() {
        return firebaseAuth;
    }

    public FirebaseApp getFirebaseApp() {
        return firebaseApp;
    }

    public DatabaseObject getDatabase() {
        return database;
    }
}
