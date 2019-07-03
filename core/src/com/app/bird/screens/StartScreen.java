package com.app.bird.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.app.bird.GameMain;
import com.app.bird.PlayServices;
import com.app.bird.actors.LoginButtonActor;
import com.app.bird.actors.PlayButtonActor;
import com.app.bird.actors.SettingsActor;
import com.app.bird.base.BaseScreen;
import com.app.bird.managers.AssetsManager;
import com.app.bird.utils.CustomListener;

public class StartScreen extends BaseScreen {

    GameMain gameMain;
    PlayButtonActor playButton;
    LoginButtonActor loginButton;
    private SettingsActor settingsActor;
    boolean isFlaying = false;
    PlayServices playServices;

    public StartScreen(GameMain gameMain, PlayServices playServices) {
        this.gameMain = gameMain;
        this.playServices = playServices;
        Gdx.input.setInputProcessor(stage);
        initializeButtons();
        addActorsToStage();
    }


    private void initializeButtons() {
        playButton = AssetsManager.getInstance().getPlayButtonActor();
        loginButton = AssetsManager.getInstance().getLoginButtonActor();
        settingsActor = AssetsManager.getInstance().getSettingsActor();
        playButton.addListener(new CustomListener(playButton) {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                if (!isFlaying) {
                    setScreenToChooseLevel();
                }
            }
        });
        settingsActor.addListener(new CustomListener(settingsActor) {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                if (gameMain.getScreen().getClass().getName().equalsIgnoreCase(StartScreen.class.getName())) {
                    gameMain.setScreen(new SettingsScreen(gameMain));
                }

            }
        });

        loginButton.addListener(new CustomListener(loginButton) {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                playServices.signIn();
            }
        });
    }

    @Override
    public void show() {

    }

    private void setScreenToChooseLevel() {
        isFlaying = true;
        gameMain.setScreen(new ChooseGameMode(gameMain));
    }

    @Override
    protected void addActorsToStage() {
        stage.addActor(AssetsManager.getInstance().getBackgroundActor());
        stage.addActor(playButton);
        if (playServices != null) {
            if (!playServices.isSignedIn()) {
                stage.addActor(loginButton);
            }
        }
    }

    @Override
    public void render(float delta) {
        stage.draw();
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


}
