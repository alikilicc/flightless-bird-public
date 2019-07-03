package com.app.bird.managers;

import com.app.bird.model.ScoreItem;

import java.util.ArrayList;

public interface GlobalHelpers {

    public void setScore(ArrayList<ScoreItem> scores);

    public ArrayList<ScoreItem> getScores();

    public void createToast(String message);

    public void setScoreItem(ScoreItem scoreItem);

    public void startAboutFlightlessBirdActivity();

    public void showInterstitialAd();

    public void adViewVisibility(boolean isShow);


}
