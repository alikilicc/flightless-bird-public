package com.app.bird.screens.levelmode;

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
import com.app.bird.actors.LevelsButtonActor;
import com.app.bird.actors.SettingsActor;
import com.app.bird.base.BaseScreen;
import com.app.bird.helpers.ScoreHelpers;
import com.app.bird.helpers.ScreenHelpers;
import com.app.bird.managers.AssetsManager;
import com.app.bird.model.ScoreItem;
import com.app.bird.screens.ChooseLevel;
import com.app.bird.screens.SettingsScreen;
import com.app.bird.utils.Constants;
import com.app.bird.utils.CustomListener;

public class LevelModeFinishScreen extends BaseScreen {

    GameMain gameMain;
    BackgroundActor arkaplanActor;
    LevelsButtonActor levelsButtonActor;
    private SettingsActor settingsActor;
    ScoreItem scoreItem;
    private BitmapFont scoreText;
    private DutyListActor dutyListButton;
    private ImageButton nextButon;
    private ImageButton repeatLevelButton;
    Table tableBottom = new Table();
    Table tableCenter = new Table();
    Table tableTop = new Table();

    /**
     * Handle the click - in real life, we'd go to the level
     */
    public ClickListener levelClickListener = new ClickListener() {
        @Override
        public void clicked(InputEvent event, float x, float y) {
            int level = scoreItem.getLevel();
            pause();
            switch (event.getListenerActor().getName()) {
                case Constants.NEXT_BUTTON:
                    gameMain.setScreen(new LevelModePlay(gameMain, level + 1));
                    break;
                case Constants.REPEAT_LEVEL:
                    gameMain.setScreen(new LevelModePlay(gameMain, level));

                    break;
                case Constants.LEVEL_MENU:
                    gameMain.setScreen(new ChooseLevel(gameMain));
                    break;
                case Constants.SETTING_WHEEL_PATH:
                    gameMain.setScreen(new SettingsScreen(gameMain));
                    break;
            }


        }
    };

    public LevelModeFinishScreen(GameMain gameMain) {
        this.gameMain = gameMain;
        this.scoreItem = ScoreHelpers.getInstance().currentLevelScores;
        arkaplanActor = AssetsManager.getInstance().getFinishBackground();
        arkaplanActor = AssetsManager.getInstance().getBackgroundActor();
        levelsButtonActor = AssetsManager.getInstance().getLevelsButtonActor();
        settingsActor = AssetsManager.getInstance().getSettingsActor();
        settingsActor.setPosition(20, 30);
        Gdx.input.setInputProcessor(stage);
        if (scoreItem == null) {
            ScoreItem tempScoreItem = new ScoreItem();
            tempScoreItem.setLevel(1);
            tempScoreItem.setScore(0);
            tempScoreItem.setSuccesfully(false);
            scoreItem = tempScoreItem;
        }
        if (!scoreItem.getSuccesfully()) {
            gameMain.globalHelpers.showInterstitialAd();
        }
        /**
         * Bu kısımda nextButton textture oluşturup ImageButton a atıyorum
         */
        //bu leveli geçtiyse background sarı eğer geçmediyse background kırmızı.
        try {
            if (scoreItem.getSuccesfully() || gameMain.globalHelpers.getScores().get(scoreItem.getLevel() - 1).getSuccesfully()) {
                // leveli geçtiyse başarılı yazısı white color
                nextButon = AssetsManager.getInstance().getNextButtonYellowImageButton();
                //bu leveli geçtiyse bir sonraki levele geçebilir ve geçmesi için click özelliği veriyorum.
                nextButon.addListener(levelClickListener);
                AssetsManager.getInstance().changeBackgroundActor();
                gameMain.playServices.achievementLevel(scoreItem.getScore());
            } else {
                nextButon = AssetsManager.getInstance().getNextButtonRedImageButton();
            }
        } catch (Exception e) {
            nextButon = AssetsManager.getInstance().getNextButtonRedImageButton();
        }

        dutyListButton = AssetsManager.getInstance().getDutyListActor();
        scoreText = AssetsManager.getInstance().getScoreText();

        // butonun boyutunu ayarlıyor
        nextButon.setSize(Gdx.graphics.getWidth() / 12, Gdx.graphics.getWidth() / 12);
        dutyListButton.setSize(Gdx.graphics.getWidth() / 12, Gdx.graphics.getWidth() / 12);
        // ekranda görülecek pozisyonunu ayarlıyor. Kanka burada slackten attığım ekran gibi ayarlayabilsek ya :D <3
        //    nextButon.setPosition((Gdx.graphics.getWidth() / 2) - 50, Gdx.graphics.getHeight() / 8);
        // butona isim veriyorum ki   nextButon.addListener(levelClickListener) da tıklanıldığında hangi butona tıklanıldığını handle edelim
        nextButon.setName(Constants.NEXT_BUTTON);


        repeatLevelButton = AssetsManager.getInstance().getRepeatLevelImageButton();
        // butonun boyutunu ayarlıyor
        repeatLevelButton.setSize(Gdx.graphics.getWidth() / 12, Gdx.graphics.getWidth() / 12);
        // ekranda görülecek pozisyonunu ayarlıyor. Kanka burada slackten attığım ekran gibi ayarlayabilsek ya :D <3
        //   repeatLevelButton.setPosition(Gdx.graphics.getWidth() / 5, Gdx.graphics.getHeight() / 8);
        // butona isim veriyorum ki   nextButon.addListener(levelClickListener) da tıklanıldığında hangi butona tıklanıldığını handle edelim
        repeatLevelButton.setName(Constants.REPEAT_LEVEL);
        repeatLevelButton.addListener(levelClickListener);

        ImageButton levelFinishFlagButton = AssetsManager.getInstance().getLevelFinishImageButton();

        tableTop.setWidth(Gdx.graphics.getWidth());
        tableTop.setPosition(0, Gdx.graphics.getHeight() - levelsButtonActor.getHeight() / 2);
        tableTop.add(levelsButtonActor).left().top().padTop(Gdx.graphics.getHeight() / 15);
        if (scoreItem.getSuccesfully()) {
            tableTop.add(AssetsManager.getInstance().getSuccessfullyLabel(scoreItem.getLevel())).expandX().center();
        } else if (gameMain.globalHelpers.getScores().get(scoreItem.getLevel() - 1).getSuccesfully()) {
            tableTop.add(AssetsManager.getInstance().getExistSuccessLevel(scoreItem.getLevel())).expandX().center();
        } else {
            tableTop.add(AssetsManager.getInstance().getUnSuccessfullyLabel(scoreItem.getLevel())).expandX().center();
        }
        tableTop.row();
        // tableTop.add(AssetsManager.getInstance().getScoreLabel(scoreItem.getScore())).expandX().colspan(2);
        tableTop.row();
        tableTop.row().height(Gdx.graphics.getHeight() / 17);
        tableCenter.setPosition(0, Gdx.graphics.getHeight() / 2);
        tableCenter.setWidth(Gdx.graphics.getWidth());
        tableCenter.add(levelFinishFlagButton).size(Gdx.graphics.getWidth() / 5, (float) Gdx.graphics.getWidth() / 3).center().expandX();
        tableBottom.setPosition(0, nextButon.getHeight() / 2);
        tableBottom.setWidth(Gdx.graphics.getWidth());
        tableBottom.add(repeatLevelButton).maxWidth(Gdx.graphics.getWidth() / 12).maxHeight((float) (Gdx.graphics.getWidth() / 12));
        tableBottom.add(dutyListButton).maxWidth(Gdx.graphics.getWidth() / 12).maxHeight((float) (Gdx.graphics.getWidth() / 12));
        tableBottom.add(nextButon).maxWidth(Gdx.graphics.getWidth() / 12).maxHeight((float) (Gdx.graphics.getWidth() / 12));
        tableBottom.padBottom(Gdx.graphics.getHeight() / 17);
        addActorsToStage();
        clickListeners();
    }

    private void clickListeners() {
        settingsActor.addListener(new CustomListener(settingsActor) {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);

                if (gameMain.getScreen().getClass().getName().equalsIgnoreCase(LevelModeFinishScreen.class.getName())) {
                    ScreenHelpers.getInstance().lastScreen = ScreenHelpers.Screens.LEVEL_MODE_FINISH_SCREEN;
                    gameMain.setScreen(new SettingsScreen(gameMain));
                }
            }
        });

        levelsButtonActor.addListener(new CustomListener(levelsButtonActor) {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                ScreenHelpers.getInstance().lastScreen = ScreenHelpers.Screens.LEVEL_MODE_FINISH_SCREEN;
                pause();
                gameMain.setScreen(new ChooseLevel(gameMain));
            }
        });

        dutyListButton.addListener(new CustomListener(dutyListButton) {
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
        stage.addActor(tableBottom);
        stage.addActor(tableCenter);
        stage.addActor(tableTop);
        stage.addActor(settingsActor);
    }

    @Override
    public void render(float delta) {
        if (Gdx.input.isKeyPressed(Input.Keys.BACK)) {
            dispose();
            gameMain.setScreen(new ChooseLevel(gameMain));
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
        tableBottom.remove();
        tableCenter.remove();
        tableTop.remove();

    }
}