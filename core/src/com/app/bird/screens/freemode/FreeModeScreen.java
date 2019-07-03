package com.app.bird.screens.freemode;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.app.bird.GameMain;
import com.app.bird.actors.BackgroundActor;
import com.app.bird.actors.DutyListActor;
import com.app.bird.actors.PlayFreeActor;
import com.app.bird.actors.RankingActor;
import com.app.bird.actors.SettingsActor;
import com.app.bird.base.BaseScreen;
import com.app.bird.helpers.ScreenHelpers;
import com.app.bird.managers.AssetsManager;
import com.app.bird.screens.ChooseGameMode;
import com.app.bird.screens.SettingsScreen;
import com.app.bird.utils.CustomListener;

public class FreeModeScreen extends BaseScreen {
    private GameMain gameMain;
    private PlayFreeActor playFreeActor;
    private BackgroundActor backgroundActor;
    private RankingActor rankingActor;
    private SettingsActor settingsActor;
    private DutyListActor dutyListButtonActor;
    //public PlayServices playServices;
    // LevelModePlay play;

    public FreeModeScreen(GameMain gameMain) {
        this.gameMain = gameMain;
        initializeActor();
        Gdx.input.setInputProcessor(stage);
        addActorsToStage();
        clickListener();
        settingsActor.setPosition(20, Gdx.graphics.getHeight() - settingsActor.getHeight());
        playFreeActor.setVisible(true);
        playFreeActor.setScale(1.5f);
        dutyListButtonActor.setX(Gdx.graphics.getWidth() / 10 * 2);
        dutyListButtonActor.setY(Gdx.graphics.getHeight() / 2 - dutyListButtonActor.getHeight() / 2);
        dutyListButtonActor.setSize(Gdx.graphics.getWidth() / 7, Gdx.graphics.getWidth() / 7);

    }

    private void clickListener() {
        rankingActor.addListener(new CustomListener(rankingActor) {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                gameMain.playServices.showScore();
            }
        });
        dutyListButtonActor.addListener(new CustomListener(dutyListButtonActor) {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                gameMain.playServices.showDutyList();
            }
        });

        playFreeActor.addListener(new CustomListener(playFreeActor) {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                gameMain.setScreen(new FreeModePlay(gameMain, 8));
            }
        });

        settingsActor.addListener(new CustomListener(settingsActor) {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                if (gameMain.getScreen().getClass().getName().equalsIgnoreCase(FreeModeScreen.class.getName())) {
                    ScreenHelpers.getInstance().lastScreen = ScreenHelpers.Screens.FREE_MODE_START;
                    gameMain.setScreen(new SettingsScreen(gameMain));
                }
            }
        });

    }

    private void initializeActor() {
        playFreeActor = AssetsManager.getInstance().getPlayFreeActor();
        backgroundActor = AssetsManager.getInstance().getBackgroundActor();
        rankingActor = AssetsManager.getInstance().getRankingActor();
        settingsActor = AssetsManager.getInstance().getSettingsActor();
        dutyListButtonActor = AssetsManager.getInstance().getDutyListActor();
    }

    @Override
    protected void addActorsToStage() {
        stage.addActor(backgroundActor);
        stage.addActor(playFreeActor);
        stage.addActor(rankingActor);
        stage.addActor(settingsActor);
        stage.addActor(dutyListButtonActor);

    }

    @Override
    public void render(float delta) {
        if (Gdx.input.isKeyPressed(Input.Keys.BACK)) {
            if (ScreenHelpers.getInstance().lastScreen == ScreenHelpers.Screens.CHOOSE_GAME_MODE) {
                dispose();
                gameMain.setScreen(new ChooseGameMode(gameMain));
            }
            return;
        }
        super.render(delta);
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
        playFreeActor.remove();
        backgroundActor.remove();
        rankingActor.remove();
    }
}
