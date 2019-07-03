package com.app.bird.screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.app.bird.GameMain;
import com.app.bird.LevelDimens;
import com.app.bird.actors.BackgroundActor;
import com.app.bird.actors.PageTitleActor;
import com.app.bird.actors.SettingsActor;
import com.app.bird.helpers.ScoreHelpers;
import com.app.bird.helpers.ScreenHelpers;
import com.app.bird.managers.AssetsManager;
import com.app.bird.model.ScoreItem;
import com.app.bird.screens.levelmode.LevelModePlay;
import com.app.bird.utils.CustomListener;
import com.app.bird.utils.PagedScrollPane;

import java.util.ArrayList;
import java.util.Locale;

public class ChooseLevel implements Screen {

    public ArrayList<ScoreItem> scores;
    GameMain gameMain;
    boolean selectedLevel = false;
    private SettingsActor settingsActor;
    /**
     * Handle the click - in real life, we'd go to the level
     */
    public ClickListener levelClickListener = new ClickListener() {
        @Override
        public void clicked(InputEvent event, float x, float y) {
            int level = Integer.parseInt(event.getListenerActor().getName());
            pause();
            int necessaryLevel = 1;
            for (int index = 0; index < scores.size(); index++) {
                if (index != 0) {
                    if (!scores.get(index).getSuccesfully()) {
                        necessaryLevel = index + 1;
                        break;
                    }
                }
            }
            if (!selectedLevel) {
                System.out.println("Click: " + event.getListenerActor().getName());
                if (level == 1) {
                    gameMain.setScreen(new LevelModePlay(gameMain, level));
                    selectedLevel = true;
                    return;
                } else {
                    boolean isLevelSuccessfull = scores.get(level - 2).getSuccesfully();
                    if (isLevelSuccessfull) {
                        gameMain.setScreen(new LevelModePlay(gameMain, level));
                        selectedLevel = true;
                    } else {
                        if (Locale.getDefault().getLanguage().contains("tr")) {
                            gameMain.globalHelpers.createToast("Önce " + (necessaryLevel) + ". leveli geçmelisin");
                        } else {
                            gameMain.globalHelpers.createToast("You must past level " + (AssetsManager.getInstance().numberToOrdinal(necessaryLevel) + ". first"));
                        }
                    }
                }
                if (scores.size() < 1 && level != 1) {
                    if (Locale.getDefault().getLanguage().contains("tr")) {
                        gameMain.globalHelpers.createToast("Önce " + necessaryLevel + ". geçmelisin");
                    } else {
                        gameMain.globalHelpers.createToast("You must past level " + (AssetsManager.getInstance().numberToOrdinal(necessaryLevel) + ". first"));
                    }
                }

            }
        }
    };
    BackgroundActor arkaplanActor;
    SpriteBatch batch;
    private Texture arkaplan;
    private Skin skinEmptyLevel;
    private Skin skinSuccessfullyLevel;
    private Stage stage;
    private Table container;
    private PageTitleActor titleActor;

    public ChooseLevel(GameMain gameMain) {
        this.gameMain = gameMain;
        gameMain.globalHelpers.showInterstitialAd();
        settingsActor = AssetsManager.getInstance().getSettingsActor();
        settingsActor.setPosition(20,20);
        LevelDimens.getInstance();
        scores = new ArrayList<ScoreItem>();
        scores = gameMain.globalHelpers.getScores();
        if (scores.isEmpty()) {
            for (int i = 1; i < 26; i++) {
                ScoreItem scoreItem = new ScoreItem();
                scoreItem.setLevel(i);
                scoreItem.setScore(0);
                scoreItem.setSuccesfully(false);
                scores.add(scoreItem);
            }
            gameMain.globalHelpers.setScore(scores);
        }
        if (ScoreHelpers.getInstance().currentLevelScores != null) {
            ScoreItem latestScoreItem = ScoreHelpers.getInstance().currentLevelScores;
            scores.set(latestScoreItem.getLevel() - 1, latestScoreItem);
        }
        selectedLevel = false;
        batch = new SpriteBatch();
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        skinEmptyLevel = AssetsManager.getInstance().getSkinEmptyLevel();
        skinSuccessfullyLevel = AssetsManager.getInstance().getSkinSuccessfullyLevel();
        arkaplanActor = AssetsManager.getInstance().getBackgroundActor();
        titleActor = AssetsManager.getInstance().getChooseLevelTitle();
        container = new Table();
        stage.addActor(arkaplanActor);
        stage.addActor(container);
        stage.addActor(titleActor);
        stage.addActor(settingsActor);
        container.setFillParent(true);
        PagedScrollPane scroll = new PagedScrollPane();
        scroll.setFlingTime(0.1f);
        scroll.setPageSpacing(25);
        int c = 1;
        for (int l = 0; l < 3; l++) {
            Table levels = new Table().pad(10);
            levels.defaults().pad(Gdx.graphics.getHeight() / 16, 10, Gdx.graphics.getHeight() / 16, 10);
            for (int y = 0; y < 2; y++) {
                levels.row();
                for (int x = 0; x < 4; x++) {
                    levels.add(getLevelButton(c++)).expand().fill().width(Gdx.graphics.getWidth() / 10).height(Gdx.graphics.getHeight() / 10);
                }
            }
            scroll.addPage(levels);
        }
        container.add(scroll).fill();
        settingsActor.setPosition(Gdx.graphics.getWidth() - settingsActor.getWidth(),Gdx.graphics.getHeight() - settingsActor.getHeight());
        clicklistener();


    }

    private void clicklistener() {
        settingsActor.addListener(new CustomListener(settingsActor) {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);

                if (gameMain.getScreen().getClass().getName().equalsIgnoreCase(ChooseLevel.class.getName())) {
                    ScreenHelpers.getInstance().lastScreen = ScreenHelpers.Screens.CHOOSE_LEVEL_SCREEN;
                    gameMain.setScreen(new SettingsScreen(gameMain));
                }

            }
        });
    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        if (Gdx.input.isKeyPressed(Input.Keys.BACK)) {
            dispose();
                gameMain.setScreen(new ChooseGameMode(gameMain));

            return;
        }
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height);

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
        container.remove();
        stage.clear();
        titleActor.remove();
    }

    public boolean needsGL20() {
        return false;
    }

    /**
     * Creates a button to represent the level
     *
     * @param level
     * @return The button to use for the level
     */
    public Button getLevelButton(int level) {
        Button button;

        if (level == 1) {
            button = new Button(skinEmptyLevel);
            ButtonStyle style = button.getStyle();
            style.up = style.down = null;

            // Stack the image and the label at the top of our button
            button.stack(AssetsManager.getInstance().getSuccessfullyLevelButtonImage(), AssetsManager.getInstance().getSuccessfullySkinLabelForButton(level)).expand().fill();

            // Randomize the number of stars earned for demonstration purposes
            int stars = MathUtils.random(-1, +3);
            button.setName(Integer.toString(level));
            button.addListener(levelClickListener);
            return button;
        } else {
            boolean isScoreSuccessfull = scores.get(level - 2).getSuccesfully();
            if (isScoreSuccessfull) {
                button = new Button(skinSuccessfullyLevel);
                ButtonStyle style = button.getStyle();
                style.up = style.down = null;


                // Stack the image and the label at the top of our button
                button.stack(new Image(skinSuccessfullyLevel.getDrawable("top")), AssetsManager.getInstance().getSuccessfullySkinLabelForButton(level)).expand().fill();

                // Randomize the number of stars earned for demonstration purposes
                int stars = MathUtils.random(-1, +3);


            } else {
                button = new Button(skinEmptyLevel);


                ButtonStyle style = button.getStyle();
                style.up = style.down = null;


                // Stack the image and the label at the top of our button
                button.stack(new Image(skinEmptyLevel.getDrawable("top")), AssetsManager.getInstance().getEmptySkinLabelForButton(level)).expand().fill();

            }
        }

        if (scores.size() < 1) {
            button = new Button(skinEmptyLevel);


            ButtonStyle style = button.getStyle();
            style.up = style.down = null;

            // Stack the image and the label at the top of our button
            button.stack(new Image(skinEmptyLevel.getDrawable("top")), AssetsManager.getInstance().getEmptySkinLabelForButton(level)).expand().fill();

            // Randomize the number of stars earned for demonstration purposes
            int stars = MathUtils.random(-1, +3);


            button.setName(Integer.toString(level));
            button.addListener(levelClickListener);
            return button;

        }


        button.setName(Integer.toString(level));
        button.addListener(levelClickListener);
        button.setHeight(Gdx.graphics.getWidth() / 15);
        button.setWidth(Gdx.graphics.getWidth() / 15);
        return button;
    }

}