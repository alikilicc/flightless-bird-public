package com.app.bird;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.app.bird.actors.BackgroundActor;
import com.app.bird.actors.FontActor;
import com.app.bird.base.BaseScreen;
import com.app.bird.managers.AssetsManager;
import com.app.bird.screens.StartScreen;


public class SplashScreen extends BaseScreen {

    private final long time;
    private GameMain gameMain;
    private BackgroundActor backgroundActor;
    private FontActor fontActor;
    public PlayServices playServices;

    public SplashScreen(GameMain gameMain, PlayServices playServices) {
        this.gameMain = gameMain;
        this.playServices = playServices;
        time = System.currentTimeMillis();
        backgroundActor = AssetsManager.getInstance().getBackgroundActor();
        fontActor = new FontActor(
                "Flightless Bird\n",
                Color.BLACK,
                Gdx.graphics.getHeight() / 12);
        addActorsToStage();
    }


    @Override
    public void show() {
        gameMain.startMusic();


    }

    @Override
    protected void addActorsToStage() {
        stage.addActor(backgroundActor);
        stage.addActor(fontActor);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.getViewport().update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);
        stage.act(delta);
        if (System.currentTimeMillis() >= time + 5000) {
            dispose();
            gameMain.setScreen(new StartScreen(gameMain,playServices));
        }
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

    }

}
