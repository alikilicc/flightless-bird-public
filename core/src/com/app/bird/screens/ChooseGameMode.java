package com.app.bird.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.app.bird.GameMain;
import com.app.bird.PlayServices;
import com.app.bird.actors.FreeModeActor;
import com.app.bird.actors.LevelModeActor;
import com.app.bird.actors.SettingsActor;
import com.app.bird.base.BaseScreen;
import com.app.bird.helpers.ScreenHelpers;
import com.app.bird.managers.AssetsManager;
import com.app.bird.screens.freemode.FreeModeScreen;
import com.app.bird.utils.CustomListener;

public class ChooseGameMode extends BaseScreen {
    GameMain gameMain;
    private FreeModeActor freeMode;
    private LevelModeActor levelMode;
    private SettingsActor settingsActor;
    public PlayServices playServices;

    public ChooseGameMode(GameMain gameMain) {
        this.gameMain = gameMain;
        Gdx.input.setInputProcessor(stage);
        initializeModeButtons();
        clickListeners();
        settingsActor.setPosition(20, Gdx.graphics.getHeight() - settingsActor.getHeight());
        ScreenHelpers.getInstance().lastScreen = ScreenHelpers.Screens.CHOOSE_GAME_MODE;
    }

    private void clickListeners() {
        levelMode.addListener(new CustomListener(levelMode) {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                gameMain.setScreen(new ChooseLevel(gameMain));
            }
        });

        freeMode.addListener(new CustomListener(freeMode) {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                gameMain.setScreen(new FreeModeScreen(gameMain));
            }
        });

        settingsActor.addListener(new CustomListener(settingsActor) {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                if (gameMain.getScreen().getClass().getName().equalsIgnoreCase(ChooseGameMode.class.getName())) {
                    ScreenHelpers.getInstance().lastScreen = ScreenHelpers.Screens.LEVEL_MODE_FINISH_SCREEN;
                    gameMain.setScreen(new SettingsScreen(gameMain));
                }
            }
        });
    }

    private void initializeModeButtons() {
        freeMode = AssetsManager.getInstance().getFreeModeActor();
        levelMode = AssetsManager.getInstance().getLevelModeActor();
        settingsActor = AssetsManager.getInstance().getSettingsActor();
        /**
         * Actor ekleme sırası önemli -- ilk elenen en altta / son eklenen en üstte
         */
        addActorsToStage();
    }

    @Override
    public void show() {

    }

    @Override
    protected void addActorsToStage() {
        stage.addActor(AssetsManager.getInstance().getBackgroundActor());
        stage.addActor(freeMode);
        stage.addActor(levelMode);
        stage.addActor(settingsActor);
    }

    @Override
    public void render(float delta) {
        if (Gdx.input.isKeyPressed(Input.Keys.BACK)) {
            if (gameMain.getScreen() instanceof ChooseGameMode) {
                dispose();
                gameMain.setScreen(new StartScreen(gameMain, playServices));
                return;
            }
        }
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        // levelMode.setX((Gdx.graphics.getWidth() / 2) - levelMode.getWidth());
        // levelMode.setY((Gdx.graphics.getHeight() / 2) - levelMode.getHeight() / 2);
        // freeMode.setX((Gdx.graphics.getWidth() / 2) + freeMode.getWidth() / 2);
        // freeMode.setY((Gdx.graphics.getHeight() / 2) - freeMode.getHeight() / 2);
        stage.draw();

    }


    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
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

    @Override
    public void dispose() {
        levelMode.remove();
        freeMode.remove();
        stage.clear();

    }


}
