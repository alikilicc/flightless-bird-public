package com.app.bird.manager;

import android.util.Log;

import java.util.Random;

public class AppAdManager {
    private static AppAdManager ourInstance;
    String TAG = "AppAdManager";

    public static AppAdManager getInstance() {
        if (ourInstance == null) {
            ourInstance = new AppAdManager();
        }
        return ourInstance;
    }

    public AD_TYPE activeBannerAdType = null;
    public AD_TYPE activeInterstitialAdType = null;

    private AppAdManager() {
        defineAdSources();
    }

    private void defineAdSources() {
        int number = new Random().nextInt(2);
        if (number == 0) {
            activeBannerAdType = AD_TYPE.ADMOB;
            activeInterstitialAdType = AD_TYPE.STARTAPP;
        } else if (number == 1) {
            activeBannerAdType = AD_TYPE.STARTAPP;
            activeInterstitialAdType = AD_TYPE.ADMOB;
        }
        Log.d(TAG, "defineAdSources: number =" + number + activeBannerAdType.toString());
    }


    public enum AD_TYPE {
        ADMOB,
        STARTAPP
    }

}
