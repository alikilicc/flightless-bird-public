package com.app.bird.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

import static com.app.bird.utils.Constants.BEST_SCORE_FREE_MODE;
import static com.app.bird.utils.Constants.CHOOSEN_BIRD;
import static com.app.bird.utils.Constants.DISPLAY_NAME;
import static com.app.bird.utils.Constants.IS_MUSIC_ACTIVE;
import static com.app.bird.utils.Constants.IS_TOUCH_SOUND_ACTIVE;

public class UserManager {
    private static UserManager USER_MANAGER = null;
    private static Preferences prefs;

    public static UserManager getInstance() {
        if (USER_MANAGER == null) {
            USER_MANAGER = new UserManager();
            prefs = Gdx.app.getPreferences("user_information");
        }
        return USER_MANAGER;
    }

    private void setDisplayName(String displayName) {
        prefs.putString(DISPLAY_NAME, displayName);
    }

    private String getDisplayName() {
        return prefs.getString(DISPLAY_NAME);
    }

    public boolean isMusicActive() {
        return prefs.getBoolean(IS_MUSIC_ACTIVE);
    }

    public void setMusicActive(boolean musicActive) {
        prefs.putBoolean(IS_MUSIC_ACTIVE, musicActive);
        prefs.flush();
    }

    public int getSelectedBird() {
        return prefs.getInteger(CHOOSEN_BIRD);
    }

    public void setChoosenBird(int choosenBirdPosition) {
        prefs.putInteger(CHOOSEN_BIRD, choosenBirdPosition);
        prefs.flush();
    }

    public boolean isTouchSoundActive() {
        return prefs.getBoolean(IS_TOUCH_SOUND_ACTIVE);
    }

    public void setTouchSoundActive(boolean touchSoundActive) {
        prefs.putBoolean(IS_TOUCH_SOUND_ACTIVE, touchSoundActive);
        prefs.flush();
    }

    public int getBestScore() {
        return prefs.getInteger(BEST_SCORE_FREE_MODE,0);
    }

    public void setBestScore(int score) {
        prefs.putInteger(BEST_SCORE_FREE_MODE, score);
        prefs.flush();
    }



}
