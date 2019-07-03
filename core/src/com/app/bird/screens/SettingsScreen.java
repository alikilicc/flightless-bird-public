package com.app.bird.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.app.bird.GameMain;
import com.app.bird.PlayServices;
import com.app.bird.actors.AboutFlightlessBirdActor;
import com.app.bird.actors.SettingsBird1Actor;
import com.app.bird.actors.SettingsBird2Actor;
import com.app.bird.actors.SettingsMusicActor;
import com.app.bird.actors.SettingsVoiceActor;
import com.app.bird.base.BaseScreen;
import com.app.bird.helpers.ScreenHelpers;
import com.app.bird.managers.AssetsManager;
import com.app.bird.managers.UserManager;
import com.app.bird.screens.freemode.FreeModeFinishScreen;
import com.app.bird.screens.freemode.FreeModeScreen;
import com.app.bird.screens.levelmode.LevelModeFinishScreen;
import com.app.bird.screens.levelmode.LevelModePlay;
import com.app.bird.utils.ActorClickListener;
import com.app.bird.utils.CustomListener;

import static com.app.bird.utils.Constants.SETTING_MUSIC_OFF_PATH;
import static com.app.bird.utils.Constants.SETTING_MUSIC_PATH;
import static com.app.bird.utils.Constants.SETTING_VOICE_OFF_PATH;
import static com.app.bird.utils.Constants.SETTING_VOICE_ON_PATH;

public class SettingsScreen extends BaseScreen {
    private GameMain gameMain;
    private SettingsMusicActor settingsMusicActor;
    private SettingsVoiceActor settingsVoiceActor;
    private SettingsBird1Actor settingsBird1Actor;
    private SettingsBird2Actor settingsBird2Actor;
    private AboutFlightlessBirdActor aboutFlightlessBirdActor;
    public PlayServices playServices;
    public PlayPauseListener playPauseListener;
    private Boolean isScreenShow = false;
    String TAG = "SettingsScreen";

    public SettingsScreen(GameMain gameMain) {
        this.gameMain = gameMain;
        initializeModeButtons();
        Gdx.input.setInputProcessor(stage);
        addActorsToStage();
        clickListeners();
    }

    private void clickListeners() {
        settingsVoiceActor.setClickListener(new ActorClickListener() {
            @Override
            public void clicked() {
                Gdx.app.log(TAG, "settings voice actor clicked");
                if (UserManager.getInstance().isTouchSoundActive()) {
                    UserManager.getInstance().setTouchSoundActive(false);
                    settingsVoiceActor.region = new TextureRegion(new Texture(SETTING_VOICE_OFF_PATH));
                } else {
                    settingsVoiceActor.region = new TextureRegion(new Texture(SETTING_VOICE_ON_PATH));
                    UserManager.getInstance().setTouchSoundActive(true);
                }
            }
        });

        settingsMusicActor.setClickListener(new ActorClickListener() {
            @Override
            public void clicked() {
                Gdx.app.log(TAG, "settings musisc actor clicked");
                UserManager.getInstance().setMusicActive(!UserManager.getInstance().isMusicActive());
                if (UserManager.getInstance().isMusicActive()) {
                    if (!AssetsManager.getInstance().getMusic().isPlaying()) {
                        AssetsManager.getInstance().getMusic().play();
                    }
                    settingsMusicActor.region = new TextureRegion(new Texture(SETTING_MUSIC_PATH));
                } else {
                    settingsMusicActor.region = new TextureRegion(new Texture(SETTING_MUSIC_OFF_PATH));
                    AssetsManager.getInstance().getMusic().stop();
                }
            }
        });
        settingsBird1Actor.addListener(new CustomListener(settingsBird1Actor) {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                UserManager.getInstance().setChoosenBird(1);
                settingsBird1Actor.setScale(1.5f);
                settingsBird2Actor.setScale(1.2f);
            }
        });
        settingsBird2Actor.addListener(new CustomListener(settingsBird2Actor) {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                UserManager.getInstance().setChoosenBird(2);
                settingsBird1Actor.setScale(1.2f);
                settingsBird2Actor.setScale(1.5f);
            }
        });
        aboutFlightlessBirdActor.addListener(new CustomListener(aboutFlightlessBirdActor) {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                gameMain.globalHelpers.startAboutFlightlessBirdActivity();
            }
        });
    }

    private void initializeModeButtons() {
        settingsBird2Actor = AssetsManager.getInstance().getSettingsBird2Actor();
        settingsBird1Actor = AssetsManager.getInstance().getSettingsBird1Actor();
        settingsMusicActor = AssetsManager.getInstance().getSettingsMusicActor();
        settingsVoiceActor = AssetsManager.getInstance().getSettingsVoiceActor();
        aboutFlightlessBirdActor = AssetsManager.getInstance().getAboutFlightlessBirdActor();
    }

    @Override
    protected void addActorsToStage() {


        stage.addActor(AssetsManager.getInstance().getSettingsBackgroundActor());
        stage.addActor(aboutFlightlessBirdActor);
        stage.addActor(settingsBird1Actor);
        stage.addActor(settingsBird2Actor);
        stage.addActor(settingsMusicActor);
        stage.addActor(settingsVoiceActor);
        settingsBird1Actor.setScale(1.2f);
        settingsBird2Actor.setScale(1.2f);
        if (UserManager.getInstance().getSelectedBird() == 1) {
            settingsBird1Actor.setScale(1.5f);
        } else if (UserManager.getInstance().getSelectedBird() == 2) {
            settingsBird2Actor.setScale(1.2f);
        }
        if (UserManager.getInstance().isMusicActive()) {
            settingsMusicActor.region = new TextureRegion(new Texture(SETTING_MUSIC_PATH));
        } else {
            settingsMusicActor.region = new TextureRegion(new Texture(SETTING_MUSIC_OFF_PATH));
        }

        if (UserManager.getInstance().isTouchSoundActive()) {
            settingsVoiceActor.region = new TextureRegion(new Texture(SETTING_VOICE_ON_PATH));
        } else {
            settingsVoiceActor.region = new TextureRegion(new Texture(SETTING_VOICE_OFF_PATH));
        }

    }

    @Override
    public void render(float delta) {
        isScreenShow = false;
        if (Gdx.input.isKeyPressed(Input.Keys.BACK)) {
            if (ScreenHelpers.getInstance().lastScreen == ScreenHelpers.Screens.LEVEL_MODE_PAUSE_SCREEN ||
                    ScreenHelpers.getInstance().lastScreen == ScreenHelpers.Screens.FREE_MODE_PAUSE_SCREEN) {
                if (playPauseListener != null) {
                    playPauseListener.onBackPressed();
                }
                return;
            }
            if (ScreenHelpers.getInstance().lastScreen == ScreenHelpers.Screens.FREE_MODE_PLAY_SCREEN) {
                gameMain.setScreen(new LevelModePlay(gameMain, 1));
                return;
            }
            if (ScreenHelpers.getInstance().lastScreen == ScreenHelpers.Screens.FREE_MODE_START) {
                gameMain.setScreen(new FreeModeScreen(gameMain));
                return;
            }
            if (ScreenHelpers.getInstance().lastScreen == ScreenHelpers.Screens.CHOOSE_LEVEL_SCREEN) {
                gameMain.setScreen(new ChooseLevel(gameMain));
                return;
            }
            if (ScreenHelpers.getInstance().lastScreen == ScreenHelpers.Screens.LEVEL_MODE_FINISH_SCREEN) {
                gameMain.setScreen(new LevelModeFinishScreen(gameMain));
                return;
            }
            if (ScreenHelpers.getInstance().lastScreen == ScreenHelpers.Screens.FREE_MODE_FINISH_SCREEN) {
                gameMain.setScreen(new FreeModeFinishScreen(gameMain));
                return;
            }
        }
        super.render(delta);
        stage.act(delta);
        isScreenShow = true;
        stage.draw();
    }

    @Override
    public void show() {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    public interface PlayPauseListener {
        void onBackPressed();
    }

    Boolean isShow() {
        return isScreenShow;
    }
}
