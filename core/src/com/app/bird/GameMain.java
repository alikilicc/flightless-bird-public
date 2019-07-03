package com.app.bird;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.utils.Array;
import com.app.bird.managers.AssetsManager;
import com.app.bird.managers.GlobalHelpers;
import com.app.bird.managers.UserManager;
import com.app.bird.screens.SettingsScreen;
import com.app.bird.screens.freemode.FreeModeFinishScreen;
import com.app.bird.screens.freemode.FreeModePlay;
import com.app.bird.screens.levelmode.LevelModeFinishScreen;
import com.app.bird.screens.levelmode.LevelModePlay;
import com.app.bird.utils.transactionanim.ScreenTransition;
import com.app.bird.utils.transactionanim.SlidingTransition;
import com.app.bird.utils.transactionanim.TransitionListener;

import java.util.Locale;

import de.tomgrill.gdxdialogs.core.GDXDialogs;
import de.tomgrill.gdxdialogs.core.GDXDialogsSystem;
import de.tomgrill.gdxdialogs.core.dialogs.GDXButtonDialog;
import de.tomgrill.gdxdialogs.core.listener.ButtonClickListener;

public class GameMain extends Game implements ApplicationListener {
    public GlobalHelpers globalHelpers;
    public PlayServices playServices;
    private SpriteBatch batch;
    private Locale locale;
    private Array<TransitionListener> listeners;
    private FrameBuffer currentScreenFBO;
    private FrameBuffer nextScreenFBO;
    private Screen nextScreen;
    private float transitionDuration;
    private float currentTransitionTime;
    private boolean transitionRunning;
    private ScreenTransition screenTransition;
    GDXDialogs dialogs;
    public int gameCount = 0;

    public GameMain(GlobalHelpers globalHelpers, PlayServices playServices) {
        this.globalHelpers = globalHelpers;
        this.playServices = playServices;
        locale = Locale.getDefault();
        this.listeners = new Array<TransitionListener>();
        screenTransition = new SlidingTransition(SlidingTransition.Direction.LEFT, Interpolation.linear, true);//new SlicingTransition(SlicingTransition.Direction.DOWN, 8, Interpolation.bounce);
    }

    @Override
    public void create() {
        AssetsManager.getInstance().setupAssets();
        this.currentScreenFBO = new FrameBuffer(Pixmap.Format.RGBA8888, Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), false);
        this.nextScreenFBO = new FrameBuffer(Pixmap.Format.RGBA8888, Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), false);
        batch = new SpriteBatch();
    /*    transactions.add(new SlidingTransition(SlidingTransition.Direction.LEFT, Interpolation.linear, true));
        transactions.add(new SlidingTransition(SlidingTransition.Direction.UP, Interpolation.bounce, false));
        transactions.add(new SlicingTransition(SlicingTransition.Direction.UPDOWN, 128, Interpolation.pow4));
        transactions.add(new SlicingTransition(SlicingTransition.Direction.DOWN, 8, Interpolation.bounce));
        transactions.add(new RotatingTransition(Interpolation.pow2Out, 720, RotatingTransition.TransitionScaling.IN));
        transactions.add(new RotatingTransition(Interpolation.bounce, 360, RotatingTransition.TransitionScaling.IN));*/
        setScreen(new SplashScreen(this, playServices));
        dialogs = GDXDialogsSystem.install();
    }

    public void createDialogLoginForLeaderBoard() {
        GDXButtonDialog bDialog = dialogs.newDialog(GDXButtonDialog.class);
        //bDialog.setTitle("Buy a item");
        if (locale.getLanguage().contains("tr")) {
            bDialog.setMessage("\nLiderlik sıralamasını görmek istiyor musun ?\n");
            bDialog.addButton("Giriş Yap");
            bDialog.addButton("Kapat");
        } else {
            bDialog.setMessage("\nDo you wanna see leadership ranking ?\n");
            bDialog.addButton("Sign Up");
            bDialog.addButton("Close");
        }
        bDialog.setClickListener(new ButtonClickListener() {
            @Override
            public void click(int button) {
                // handle button click here
                if (button == 0) {
                    playServices.signIn();
                }
            }
        });
        bDialog.build().show();
    }

    public void createBestScore(int score) {
        GDXButtonDialog bDialog = dialogs.newDialog(GDXButtonDialog.class);
        //bDialog.setTitle("Buy a item");
        if (locale.getLanguage().contains("tr")) {
            bDialog.setMessage("\n\nYeni skorun : " + score + "\n");
            bDialog.setTitle("Yeni bir rekor daha !");
            bDialog.setCancelable(true);
            bDialog.addButton("Kapat");
        } else {
            bDialog.setMessage("\n\nNew score : " + score + "\n");
            bDialog.setTitle("New one more record !");
            bDialog.setCancelable(true);
            bDialog.addButton("Close");
        }
        bDialog.build().show();
    }

    @Override
    public void pause() {
        if (screen != null) screen.pause();
        if (nextScreen != null) nextScreen.pause();
    }

    @Override
    public void resume() {
        if (screen != null) screen.resume();
        if (nextScreen != null) nextScreen.resume();
    }

    @Override
    public void render() {
        super.render();

        float delta = Gdx.graphics.getDeltaTime();

        if (nextScreen == null) {
            // no other screen
            screen.render(delta);
        } else {
            if (transitionRunning && currentTransitionTime >= transitionDuration) {
                // is active and time limit reached
                this.screen.hide();
                this.screen = this.nextScreen;
                this.screen.resume();
                transitionRunning = false;
                this.nextScreen = null;
                notifyFinished();
                this.screen.render(delta);

            } else {
                // transition is active
                if (screenTransition != null) {
                    currentScreenFBO.begin();
                    this.screen.render(delta);
                    currentScreenFBO.end();

                    nextScreenFBO.begin();
                    this.nextScreen.render(delta);
                    nextScreenFBO.end();

                    float percent = currentTransitionTime / transitionDuration;

                    screenTransition.render(batch, currentScreenFBO.getColorBufferTexture(), nextScreenFBO.getColorBufferTexture(),
                            percent);
                    currentTransitionTime += delta;

                }

            }

        }
    }

    @Override
    public void dispose() {
        batch.dispose();
        if (screen != null) screen.hide();
        if (nextScreen != null) nextScreen.hide();

        this.currentScreenFBO.dispose();
        this.nextScreenFBO.dispose();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        if (screen != null) screen.resize(width, height);
        if (nextScreen != null) nextScreen.resize(width, height);

        this.currentScreenFBO.dispose();
        this.nextScreenFBO.dispose();

        this.currentScreenFBO = new FrameBuffer(Pixmap.Format.RGBA8888, width, height, false);
        this.nextScreenFBO = new FrameBuffer(Pixmap.Format.RGBA8888, width, height, false);
    }

    /**
     * Sets the {@link ScreenTransition} which is used. May be {@code null} to use instant switching.
     *
     * @param screenTransition may be {@code null}
     * @param duration         the transition duration in seconds
     * @return {@code true} if successful false if transition is running
     */
    public boolean setTransition(ScreenTransition screenTransition, float duration) {
        if (transitionRunning) return false;
        this.screenTransition = screenTransition;
        this.transitionDuration = 1f;
        return true;

    }

    /**
     * @return the currently active {@link Screen}.
     */
    @Override
    public Screen getScreen() {
        return screen;
    }

    /**
     * Sets the current screen. {@link Screen#hide()} is called on any old screen, and {@link Screen#show()} is called on the new
     * screen, if any.
     *
     * @param screen may be {@code null}
     */
    @Override
    public void setScreen(Screen screen) {
        screen.show();
        if (transitionRunning)
            Gdx.app.log(GameMain.class.getSimpleName(), "Changed Screen while transition in progress");
        if (this.screen == null) {
            this.screen = screen;
        } else {
            if (screenTransition == null) {
                this.screen.hide();
                this.screen = screen;
            } else {
                this.nextScreen = screen;
                this.screen.pause();
                this.nextScreen.pause();
                currentTransitionTime = 0;
                transitionRunning = true;
                notifyStarted();
            }

        }

        this.screen.resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
       /* if (screen instanceof LevelModeFinishScreen || screen instanceof FreeModeFinishScreen || screen instanceof ChooseLevel) {
            if (ScoreHelpers.getInstance().getCurrentFreeModeScores() != null && ScoreHelpers.getInstance().getCurrentFreeModeScores() != null) {
                if (!ScoreHelpers.getInstance().getCurrentLevelScores().getSuccesfully() || !ScoreHelpers.getInstance().getCurrentFreeModeScores().getSuccesfully()) {
                    globalHelpers.showInterstitialAd();
                }
            } else {
                globalHelpers.showInterstitialAd();
            }
        }*/
        globalHelpers.adViewVisibility(screen instanceof LevelModePlay || screen instanceof FreeModePlay || screen instanceof LevelModeFinishScreen || screen instanceof FreeModeFinishScreen || screen instanceof SettingsScreen);

    }

    /**
     * @return the next {@link Screen}.
     */
    public Screen getNextScreen() {
        return nextScreen;
    }

    /**
     * @param listener to get transition events
     */
    public void addTransitionListener(TransitionListener listener) {
        listeners.add(listener);
        return;
    }

    /**
     * @param listener to remove
     * @return {@code true} if successful
     */
    public boolean removeTransitionListener(TransitionListener listener) {
        return listeners.removeValue(listener, true);
    }

    /**
     * Clear listeners
     */
    public void clearTransitionListeners() {
        listeners.clear();
    }

    private void notifyFinished() {
        /*for (TransitionListener transitionListener : listeners) {
            transitionListener.onTransitionFinished();
        }*/
    }

    private void notifyStarted() {
       /* for (TransitionListener transitionListener : listeners) {
            transitionListener.onTransitionStart();
        }*/
    }

    public void startMusic() {
        if (!UserManager.getInstance().isMusicActive()) {
            return;
        }
        AssetsManager.getInstance().getMusic().setLooping(true);
        AssetsManager.getInstance().getMusic().setVolume(0.1f);
        ;
        AssetsManager.getInstance().getMusic().play();

    }

}
