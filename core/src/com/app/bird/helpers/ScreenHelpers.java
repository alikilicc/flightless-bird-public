package com.app.bird.helpers;

public class ScreenHelpers {
    private static ScreenHelpers ourInstance;

    public static ScreenHelpers getInstance() {
        if (ourInstance == null) {
            ourInstance = new ScreenHelpers();
        }
        return ourInstance;
    }

    public Screens lastScreen = null;

    private ScreenHelpers() {

    }
   public enum Screens{
        FREE_MODE_START,
        FREE_MODE_PLAY_SCREEN,
        LEVEL_MODE_PLAY_SCREEN,
        FREE_MODE_PAUSE_SCREEN,
        LEVEL_MODE_PAUSE_SCREEN,
        CHOOSE_LEVEL_SCREEN,
        LEVEL_MODE_FINISH_SCREEN,
        FREE_MODE_FINISH_SCREEN,
       CHOOSE_GAME_MODE
    }


}
