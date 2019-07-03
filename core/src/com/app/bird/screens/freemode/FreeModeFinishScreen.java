package com.app.bird.screens.freemode;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.app.bird.GameMain;
import com.app.bird.actors.BackgroundActor;
import com.app.bird.actors.DutyListActor;
import com.app.bird.actors.RankingActor;
import com.app.bird.actors.SettingsActor;
import com.app.bird.base.BaseScreen;
import com.app.bird.helpers.ScoreHelpers;
import com.app.bird.helpers.ScreenHelpers;
import com.app.bird.managers.AssetsManager;
import com.app.bird.managers.UserManager;
import com.app.bird.model.ScoreItem;
import com.app.bird.screens.SettingsScreen;
import com.app.bird.utils.Constants;
import com.app.bird.utils.CustomListener;

public class FreeModeFinishScreen extends BaseScreen {

    private GameMain gameMain;
    private BackgroundActor arkaplanActor;
    private SettingsActor settingsActor;
    private DutyListActor dutyListActor;
    private RankingActor rankingActor;
    ScoreItem scoreItem;
    private ImageButton repeatLevelButton;
    private Table tableTop = new Table();
    private Table tableCenter = new Table();
    private Table tableBottomLeft = new Table();
    /**
     * Handle the click - in real life, we'd go to the level
     */
    private ClickListener levelClickListener = new ClickListener() {
        @Override
        public void clicked(InputEvent event, float x, float y) {
            int level = scoreItem.getLevel();
            pause();
            switch (event.getListenerActor().getName()) {
                case Constants.REPEAT_LEVEL:
                    gameMain.setScreen(new FreeModePlay(gameMain, 4));
                    break;
                case Constants.SETTING_WHEEL_PATH:
                    gameMain.setScreen(new SettingsScreen(gameMain));
                    break;
            }


        }
    };


    public FreeModeFinishScreen(GameMain gameMain) {
        this.gameMain = gameMain;
        this.scoreItem = ScoreHelpers.getInstance().currentFreeModeScores;
        gameMain.globalHelpers.showInterstitialAd();
        if (scoreItem.getScore() > UserManager.getInstance().getBestScore()) {
            gameMain.createBestScore(scoreItem.getScore());
            AssetsManager.getInstance().changeBackgroundActor();
            UserManager.getInstance().setBestScore(scoreItem.getScore());
        }
        arkaplanActor = AssetsManager.getInstance().getFinishBackground();
        arkaplanActor = AssetsManager.getInstance().getBackgroundActor();
        settingsActor = AssetsManager.getInstance().getSettingsActor();
        dutyListActor = AssetsManager.getInstance().getDutyListActor();
        dutyListActor.setX(Gdx.graphics.getWidth() / 10 * 2);
        dutyListActor.setY(Gdx.graphics.getHeight() / 2 - dutyListActor.getHeight() / 2);
        dutyListActor.setSize(Gdx.graphics.getWidth() / 7, Gdx.graphics.getWidth() / 7);
        rankingActor = AssetsManager.getInstance().getRankingActor();
        settingsActor.setPosition(20, Gdx.graphics.getHeight() - settingsActor.getHeight() - 20);
        repeatLevelButton = AssetsManager.getInstance().getRepeatLevelImageButton();
        BitmapFont scoreText = AssetsManager.getInstance().getScoreText();
        Gdx.input.setInputProcessor(stage);
        clickListeners();
        repeatLevelButton.setSize(Gdx.graphics.getWidth() / 10, (float) (Gdx.graphics.getWidth() / 10));
        repeatLevelButton.setName(Constants.REPEAT_LEVEL);
        repeatLevelButton.addListener(levelClickListener);
        repeatLevelButton.setPosition(Gdx.graphics.getWidth() / 2f - repeatLevelButton.getWidth() / 2, 50);
        ImageButton levelFinishFlagButton = AssetsManager.getInstance().getLevelFinishImageButton();
        tableTop.setPosition(0, Gdx.graphics.getHeight() - 20);
        tableTop.setWidth(Gdx.graphics.getWidth());
        tableTop.add(AssetsManager.getInstance().getScoreLabel(scoreItem.getScore())).padTop(35).expandX().center().colspan(2)
                .expandY().top();
        tableTop.row();
        tableCenter.setPosition(0, Gdx.graphics.getHeight() / 2f);
        tableCenter.setWidth(Gdx.graphics.getWidth());
        tableCenter.add(levelFinishFlagButton).size(Gdx.graphics.getWidth() / 7, (float) (Gdx.graphics.getWidth() / 7)).center().expandX();
        tableBottomLeft = new Table();
        tableBottomLeft.setPosition(0, Gdx.graphics.getHeight() / 15);
        tableBottomLeft.setWidth(Gdx.graphics.getWidth() / 3f);
        tableBottomLeft.add(AssetsManager.getInstance().getBestScoreLabel(UserManager.getInstance().getBestScore()));
        stage.addActor(arkaplanActor);
        stage.addActor(tableCenter);
        stage.addActor(tableTop);
        stage.addActor(repeatLevelButton);
        stage.addActor(settingsActor);
        stage.addActor(dutyListActor);
        stage.addActor(rankingActor);
        stage.addActor(tableBottomLeft);
        gameMain.playServices.setScoreToLeaderBoard(scoreItem.getScore());
    }

    private void clickListeners() {
        settingsActor.addListener(new CustomListener(settingsActor) {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                if (gameMain.getScreen().getClass().getName().equalsIgnoreCase(FreeModeFinishScreen.class.getName())) {
                    ScreenHelpers.getInstance().lastScreen = ScreenHelpers.Screens.FREE_MODE_FINISH_SCREEN;
                    gameMain.setScreen(new SettingsScreen(gameMain));
                }
            }
        });

        rankingActor.addListener(new CustomListener(rankingActor) {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                gameMain.playServices.showScore();
            }
        });

        dutyListActor.addListener(new CustomListener(dutyListActor) {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                gameMain.playServices.showDutyList();
            }
        });


    }


    @Override
    public void show() {

    }

    @Override
    protected void addActorsToStage() {
        stage.addActor(arkaplanActor);
        stage.addActor(tableCenter);
        stage.addActor(tableTop);
        stage.addActor(repeatLevelButton);
        stage.addActor(settingsActor);
        stage.addActor(dutyListActor);
        stage.addActor(rankingActor);
    }

    @Override
    public void render(float delta) {
        if (Gdx.input.isKeyPressed(Input.Keys.BACK)) {
            dispose();
            gameMain.setScreen(new FreeModeScreen(gameMain));
            return;
        }
        super.render(delta);
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
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

    @Override
    public void dispose() {
        super.dispose();
        arkaplanActor.remove();
        tableCenter.remove();
        tableTop.remove();
        repeatLevelButton.remove();
        dutyListActor.remove();
        rankingActor.remove();
    }
}