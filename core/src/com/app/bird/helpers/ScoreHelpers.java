package com.app.bird.helpers;

import com.app.bird.model.ScoreItem;

public class ScoreHelpers {
    private static ScoreHelpers ourInstance;

    public ScoreItem currentLevelScores = null;
    public ScoreItem currentFreeModeScores = null;

    public static ScoreHelpers getInstance() {
        if (ourInstance == null) {
            ourInstance = new ScoreHelpers();
        }
        return ourInstance;
    }

    private ScoreHelpers() {
    }

    public ScoreItem getCurrentLevelScores() {
        return currentLevelScores;
    }

    public void setCurrentLevelScores(ScoreItem currentLevelScores) {
        this.currentLevelScores = currentLevelScores;
    }

    public ScoreItem getCurrentFreeModeScores() {
        return currentFreeModeScores;
    }

    public void setCurrentFreeModeScores(ScoreItem currentFreeModeScores) {
        this.currentFreeModeScores = currentFreeModeScores;
    }
}

