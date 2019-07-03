package com.app.bird.screens.levelmode;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.app.bird.GameMain;
import com.app.bird.LevelDimens;
import com.app.bird.actors.BackgroundActor;
import com.app.bird.actors.PauseActor;
import com.app.bird.actors.SettingsActor;
import com.app.bird.actors.SettingsMusicActor;
import com.app.bird.actors.SmallPlayActor;
import com.app.bird.base.BaseScreen;
import com.app.bird.helpers.ScoreHelpers;
import com.app.bird.helpers.ScreenHelpers;
import com.app.bird.managers.AssetsManager;
import com.app.bird.managers.UserManager;
import com.app.bird.model.LevelItem;
import com.app.bird.model.ScoreItem;
import com.app.bird.screens.ChooseLevel;
import com.app.bird.screens.SettingsScreen;
import com.app.bird.utils.Constants;
import com.app.bird.utils.CustomListener;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;

//TODO yapılacaklar


public class LevelModePlay extends BaseScreen {

    private enum OyunDurumu {baslangıc, bitis, devamediyor, durduruldu}


    private UserManager userManager;
    private SettingsActor settingsActor;
    private PauseActor pauseActor;
    private SmallPlayActor smallPlayActor;
    private SettingsMusicActor settingsMusicActor;
    private static final float Kus_Baslangıc_KonumX = Gdx.graphics.getWidth() / 13;
    private static final float Kus_Baslangıc_Konumy = Gdx.graphics.getHeight() / 2;
    private SpriteBatch batch; // görselleri çizmemizi sağlayan nesne
    private BackgroundActor backgroundActor; //görselleri tanımlama
    private Texture ari, ari2, ari3;
    private TextureRegion bitis;
    private TextureRegion baslangic;
    private Animation birdAnimation;
    private Animation bulletAnimation;
    private Animation birdAnimation2;
    private OrthographicCamera kamera;
    private Vector2 kusPozisyonu;
    private Vector2 kusYercekimi;
    private Vector2 yerCekimi;
    private int bitisSayaci = 0;
    private float d,b;
    private float screenWidth = Gdx.graphics.getWidth();
    private float screenHeight = Gdx.graphics.getHeight();
    private final float KUS_BASLANGIC_KONUM_X = screenWidth / 13;
    private final float KUS_BASLANGIC_KONUM_Y = screenHeight / 2;
    private float kusunHizi, ziplama, yercekimi, yaziX;
    private OyunDurumu oyunDurumu = OyunDurumu.baslangıc;
    private float bitisKonumX;
    private float kusGenislik = screenWidth / 14;
    private float kusYukseklik = screenHeight / 8;
    private float gecensure = 0.0f;
    private float uzaklık = 0, ariHizi = 4, hangisi;
    private int arisutunsayisi, ariXset = 0, ariXsetdeger = 0;
    private float[] ari1Xekseni;
    private float[] ari2Xekseni;
    private float[] ari3Xekseni;
    private float[] ari1Yekseni;
    private float[] ari2Yekseni;
    private float[] ari3Yekseni;
    private Circle[] ariDaire;
    private Circle[] ariDaire2;
    private Circle[] ariDaire3;
    private Circle kusDaire;
    private Rectangle bitisCizigisi;
    private Rectangle[] mermiRectangle1;
    private Rectangle[] mermiRectangle2;
    private Rectangle[] mermiRectangle3;
    private ShapeRenderer shapeRenderer;
    private Rectangle levelRectangle;
    private Random rasgele;
    private Float ekranYukseklik;
    private float ekranGenislik;
    private BitmapFont yazi, yaziOyunBitti;
    private GameMain gameMain;
    ScoreItem scoreItem = new ScoreItem();
    private int level;
    int enbuyuk;
    private long time, time2;
    private boolean sonekran;
    private SettingsScreen settingsScreen;
    private boolean drawSettingScreen = false , bittimi=false,akis=true;
    private Stage myStage;

    /**
     * level seçildiğinde bu constructor tetiklenicek
     */
    public LevelModePlay(GameMain gameMain, int level) {//oyun başladığında yapılacaklar
        this.gameMain = gameMain;
        this.level = level;
        myStage = new Stage(new ScreenViewport());
        scoreItem.setLevel(level);
        setRates();
        setGameConfiguration();
        setClickListeners();
        userManager = UserManager.getInstance();
        settingsActor.setPosition(20, 20);
    }

    private void setClickListeners() {
        pauseActor.addListener(new CustomListener(pauseActor) {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                if (oyunDurumu == OyunDurumu.durduruldu) {
                    oyunDurumu = OyunDurumu.devamediyor;
                } else {
                    oyunDurumu = OyunDurumu.durduruldu;
                }
            }
        });
        settingsActor.addListener(new CustomListener(settingsActor) {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                if (oyunDurumu == OyunDurumu.durduruldu) {
                    drawSettingScreen = true;
                    initSettingsScreen();
                }
            }
        });
        smallPlayActor.addListener(new CustomListener(smallPlayActor) {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                if (oyunDurumu == OyunDurumu.durduruldu) {
                    oyunDurumu = OyunDurumu.devamediyor;
                } else {
                    oyunDurumu = OyunDurumu.durduruldu;
                }
            }
        });
    }

    private void initSettingsScreen() {
        if (settingsScreen == null) {
            settingsScreen = new SettingsScreen(gameMain);
            settingsScreen.playPauseListener = new SettingsScreen.PlayPauseListener() {
                @Override
                public void onBackPressed() {
                    Gdx.input.setInputProcessor(myStage);
                    drawSettingScreen = false;
                    settingsScreen.dispose();
                    settingsScreen = null;
                    rebuildActors();
                }
            };
        }

    }

    private void rebuildActors() {
        settingsActor.setVisible(true);
        myStage.addActor(settingsActor);
        myStage.addActor(pauseActor);
        myStage.addActor(smallPlayActor);
    }

    private void setRates() {
        LevelItem levelItem = LevelDimens.getInstance().levelItems.get(level - 1);
        ariHizi = levelItem.getAriHizi();
        ziplama = levelItem.getZiplama();
        yercekimi = levelItem.getYercekimi();
        kusunHizi = levelItem.getKusunHizi();
        arisutunsayisi = levelItem.getArisutunsayisi();

    }

    private void setGameConfiguration() {
        kamera = new OrthographicCamera();
        kamera.setToOrtho(false, screenWidth, screenHeight);
        sonekran = true;
        kusPozisyonu = new Vector2();
        yerCekimi = new Vector2();
        kusYercekimi = new Vector2();
        batch = new SpriteBatch();
        ekranYukseklik = screenHeight;
        ekranGenislik = screenWidth;
        backgroundActor = AssetsManager.getInstance().getBackgroundActor();
        settingsActor = AssetsManager.getInstance().getSettingsActor();
        pauseActor = AssetsManager.getInstance().getPauseActor();
        smallPlayActor = AssetsManager.getInstance().getSmallPlayActor();
        settingsMusicActor = AssetsManager.getInstance().getSettingsMusicActor();
        bitis = AssetsManager.getInstance().getFinishFlag();
        baslangic = AssetsManager.getInstance().getFinishFlag();
        if (UserManager.getInstance().getSelectedBird() == 1)
            birdAnimation = AssetsManager.getInstance().getBirdAnimation();
        else if (UserManager.getInstance().getSelectedBird() == 2)
            birdAnimation = AssetsManager.getInstance().getBirdAnimation2();
        else birdAnimation = AssetsManager.getInstance().getBirdAnimation();
        bulletAnimation = AssetsManager.getInstance().getBulletAnimation();
        //birdAnimation2 = AssetsManager.getInstance().getBirdAnimation2();
        settingsActor.setVisible(false);
        smallPlayActor.setVisible(false);
        ari = new Texture(Constants.ENEMY_IMAGE_PATH);//arı şeçimi
        ari2 = new Texture(Constants.ENEMY_IMAGE_PATH);
        ari3 = new Texture(Constants.ENEMY_IMAGE_PATH);
        levelRectangle = new Rectangle(50,
                screenHeight - 150,
                screenWidth / 9, screenHeight / 10);

        Gdx.input.setInputProcessor(myStage);
        uzaklık = screenWidth / 2;
        shapeRenderer = new ShapeRenderer();
        kusDaire = new Circle();
        ariDaire = new Circle[arisutunsayisi];
        ariDaire2 = new Circle[arisutunsayisi];
        ariDaire3 = new Circle[arisutunsayisi];
        ari1Xekseni = new float[arisutunsayisi];
        ari2Xekseni = new float[arisutunsayisi];
        ari3Xekseni = new float[arisutunsayisi];
        ari1Yekseni = new float[arisutunsayisi];
        ari2Yekseni = new float[arisutunsayisi];
        ari3Yekseni = new float[arisutunsayisi];
        bitisCizigisi = new Rectangle();
        mermiRectangle1 = new Rectangle[arisutunsayisi];
        mermiRectangle2 = new Rectangle[arisutunsayisi];
        mermiRectangle3 = new Rectangle[arisutunsayisi];
        rasgele = new Random();
        yazi = AssetsManager.getInstance().getGameTextInPlayScreen();
        yaziOyunBitti = AssetsManager.getInstance().getGameFinishText();
        d =screenWidth / 5;
        b = screenWidth / 2.65f;

        resetle();

        for (int i = 0; i < arisutunsayisi; i++) {

            ari1Yekseni[i] = (((ekranYukseklik / 3) * 2) + screenHeight / 10) + rasgele.nextFloat() * ((ekranYukseklik - (screenHeight / 10)) - (((ekranYukseklik / 3) * 2) + screenHeight / 10));
            ari2Yekseni[i] = ((ekranYukseklik / 3) + screenHeight / 10) + rasgele.nextFloat() * ((((ekranYukseklik / 3) * 2) - (screenHeight / 10)) - ((ekranYukseklik / 3) + screenHeight / 10));
            ari3Yekseni[i] = screenHeight / 10 + rasgele.nextFloat() * (((ekranYukseklik / 3) - screenHeight / 10));
            ari1Xekseni[i] = (((ekranGenislik / 3) * 2) + rasgele.nextFloat() * ((ekranGenislik - ((ekranGenislik / 3) * 2)))) + (i * uzaklık + ekranGenislik);//arıların x ekseninin aralıklarını hesaplar
            ari2Xekseni[i] = ((ekranGenislik / 3) + rasgele.nextFloat() * (((ekranGenislik / 3) * 2) - (ekranGenislik / 3))) + (i * uzaklık + ekranGenislik);//arıların x ekseninin aralıklarını hesaplar
            ari3Xekseni[i] = ((0) + rasgele.nextFloat() * ((ekranGenislik / 3) - 0)) + (i * uzaklık + ekranGenislik);//arıların x ekseninin aralıklarını hesaplar
            mermiRectangle1[i] = new Rectangle();
            mermiRectangle2[i] = new Rectangle();
            mermiRectangle3[i] = new Rectangle();

        }
        bitisKonumX = Enbuyukbulma(ari1Xekseni[arisutunsayisi - 1], ari2Xekseni[arisutunsayisi - 1], ari3Xekseni[arisutunsayisi - 1]) + screenWidth / 17;
        addActorsToStage();
    }

    @Override
    public void show() {

    }


    @Override
    protected void addActorsToStage() {
        myStage.addActor(AssetsManager.getInstance().getBackgroundActor());
        myStage.addActor(settingsActor);
        myStage.addActor(pauseActor);
        myStage.addActor(smallPlayActor);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        // Oyun devam etiği sürede yaplacaklar sürekli tekrar tekrar çağrılır
        myStage.getViewport().update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);
        myStage.draw();
        myStage.act(delta);
        guncelle();
        cizdir();
        if (oyunDurumu == OyunDurumu.durduruldu && settingsScreen != null && drawSettingScreen) {
            ScreenHelpers.getInstance().lastScreen = ScreenHelpers.Screens.LEVEL_MODE_PAUSE_SCREEN;
            settingsScreen.render(delta);
        } else {
            drawSettingScreen = false;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.BACK)) {
            dispose();
            gameMain.setScreen(new ChooseLevel(gameMain));

            return;
        }
    }

    private void guncelle() {
        float deltatime = Gdx.graphics.getDeltaTime();
        gecensure += deltatime;
        if (oyunDurumu == OyunDurumu.baslangıc) {
            if (Gdx.input.justTouched()) oyunDurumu = OyunDurumu.devamediyor;
        }
        if (oyunDurumu == OyunDurumu.durduruldu) {
            settingsActor.setVisible(true);
            smallPlayActor.setVisible(true);
            pauseActor.setVisible(false);
            return;
        } else {
            pauseActor.setVisible(true);
            smallPlayActor.setVisible(false);
            settingsActor.setVisible(false);
        }
        if (oyunDurumu == OyunDurumu.devamediyor) {
            Gdx.app.log("Rates", "yer cekimi = " + yercekimi + " ziplama : " + ziplama + "  kusunHizi = " + kusunHizi + " ariHizi : " + ariHizi);

            yaziX = kamera.position.x + b;
            if (Gdx.input.justTouched()) {
                birdAnimation.setPlayMode(Animation.PlayMode.NORMAL);
                bulletAnimation.setPlayMode(Animation.PlayMode.NORMAL);
                time = System.currentTimeMillis();
                kusYercekimi.set(kusunHizi, ziplama);

                if (UserManager.getInstance().isTouchSoundActive())
                    AssetsManager.getInstance().getSound().play();

            }
            if (System.currentTimeMillis() <= time + 200) {
                birdAnimation.setPlayMode(Animation.PlayMode.LOOP_REVERSED);
                bulletAnimation.setPlayMode(Animation.PlayMode.LOOP_REVERSED);

            } else {
                birdAnimation.setPlayMode(Animation.PlayMode.NORMAL);
                bulletAnimation.setPlayMode(Animation.PlayMode.NORMAL);
            }

            if (kusPozisyonu.y < 0 || kusPozisyonu.y > screenHeight - kusYukseklik) {
                oyunDurumu = OyunDurumu.bitis; //kuş taban veya tavana değerse yanar
            }
            if (enbuyuk == 1) {
                if (kusPozisyonu.x > ari1Xekseni[arisutunsayisi - 1])
                    bittimi = true;
            } else if (enbuyuk == 2) {
                if (kusPozisyonu.x > ari2Xekseni[arisutunsayisi - 1])
                    bittimi = true;
            } else {
                if (kusPozisyonu.x > ari3Xekseni[arisutunsayisi - 1])
                    bittimi = true;
            }
            for (int i = 0; i < arisutunsayisi; i++) {
/*
                    if (ari1Xekseni[i] < kamera.position.x ) {

                        ari1Xekseni[i] = ari1Xekseni[i] + arisutunsayisi * uzaklık;//arılar sona ulaşınca 2 ekran geri kaydırır arıları
                        ari1Yekseni[i] = (rasgele.nextFloat() * ((ekranYukseklik / 3) - (screenHeight / 10)) + ((ekranYukseklik / 3) * 2));
                        ariXsetdeger++;
                    }
                    if (ari2Xekseni[i] < kamera.position.x ) {

                        ari2Xekseni[i] = ari2Xekseni[i] + arisutunsayisi * uzaklık;//arılar sona ulaşınca 2 ekran geri kaydırır arıları
                        ari2Yekseni[i] = (rasgele.nextFloat() * ((ekranYukseklik / 3) - screenHeight / 10) + ((screenHeight / 10) + (ekranYukseklik / 3)));
                        ariXsetdeger++;
                    }
                    if (ari3Xekseni[i] < kamera.position.x ) {

                        ari3Xekseni[i] = ari3Xekseni[i] + arisutunsayisi * uzaklık;//arılar sona ulaşınca 2 ekran geri kaydırır arıları
                        ari3Yekseni[i] = (rasgele.nextFloat() * ((ekranYukseklik / 3) - screenHeight / 10));
                        ariXsetdeger++;
                        bitisSayaci++;
                    }*/

                ari1Xekseni[i] = ari1Xekseni[i] - ariHizi;//arıların ileriye hareketi
                ari2Xekseni[i] = ari2Xekseni[i] - ariHizi;//arıların ileriye hareketi
                ari3Xekseni[i] = ari3Xekseni[i] - ariHizi;//arıların ileriye hareketi
                if (bitisKonumX > kamera.position.x + ekranGenislik / 2 - (ekranGenislik / 17)) {
                    bitisKonumX = bitisKonumX - ariHizi / arisutunsayisi;
                } else sonekran = false;


                //arılar için çember çizimi
                mermiRectangle1[i].set(ari1Xekseni[i] + screenHeight / 80, ari1Yekseni[i], screenWidth / 18, screenHeight / 22);
                mermiRectangle2[i].set(ari2Xekseni[i] + screenHeight / 80, ari2Yekseni[i] + screenHeight / 80, screenWidth / 18, screenHeight / 22);
                mermiRectangle3[i].set(ari3Xekseni[i] + screenHeight / 80, ari3Yekseni[i] + screenHeight / 80, screenWidth / 18, screenHeight / 22);

            }

            bitisCizigisi.set(bitisKonumX, 0, screenWidth / 17, screenHeight);
            if (!bittimi) {
                kusYercekimi.add(yerCekimi);
                kusPozisyonu.mulAdd(kusYercekimi, Gdx.graphics.getDeltaTime());
            } else kusPozisyonu.x += 5;

        }


        if (sonekran) {

            if (kusPozisyonu.x>screenWidth/2-(baslangic.getRegionWidth())){
                if(kamera.position.x<kusPozisyonu.x +screenWidth / 3 && akis) {
                    kamera.position.x += 5;
                    d = screenWidth / 3;
                    time2 = System.currentTimeMillis();
                }
                else{
                    akis=false;
                    kamera.position.x = kusPozisyonu.x +d;
                    time2 = System.currentTimeMillis();
                }

            }
            else kamera.position.x = kusPozisyonu.x +d;

        }
        if (!sonekran) {
            if (System.currentTimeMillis() <= time2 + 250) {
                settingsActor.setX(settingsActor.getX() - 4);
                pauseActor.setX(pauseActor.getX() - 4);
                smallPlayActor.setX(smallPlayActor.getX() - 4);
                b = b - 4;
            }
        }

        kusDaire.set(kusPozisyonu.x + screenWidth / 35 + screenHeight / 36,
                kusPozisyonu.y + screenHeight / 15,
                screenWidth / 30);//kuşun üzerinde daire çizim
        /*shapeRenderer.setProjectionMatrix(kamera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.BLACK);
        shapeRenderer.circle(kusDaire.x,kusDaire.y,kusDaire.radius);
        */
        for (int i = 0; i < arisutunsayisi; i++) {
            //shapeRenderer.rect(mermiRectangle1[i].x,mermiRectangle1[i].y+screenHeight/60,screenWidth / 18, screenHeight / 22);
            //shapeRenderer.rect(mermiRectangle2[i].x,mermiRectangle2[i].y+screenHeight/60,screenWidth / 18, screenHeight / 22);
            //shapeRenderer.rect(mermiRectangle3[i].x,mermiRectangle3[i].y+screenHeight/60,screenWidth / 18, screenHeight / 22);
            //Intersectormetodu çarpışma olduğunda ne olacağını belirliyoruz
            if (Intersector.overlaps(kusDaire, mermiRectangle1[i]) || Intersector.overlaps(kusDaire, mermiRectangle2[i]) ||
                    Intersector.overlaps(kusDaire, mermiRectangle3[i])) {
                oyunDurumu = OyunDurumu.bitis;
            }
        }
        if (Intersector.overlaps(kusDaire, bitisCizigisi)) {
            scoreItem.setSuccesfully(true);
            scoreItem.setScore(ariXsetdeger);
            scoreItem.setLevel(level);
            ArrayList<ScoreItem> scores = gameMain.globalHelpers.getScores();
            if (!scores.isEmpty()) {
                scores.set(level - 1, scoreItem);
            }
            gameMain.globalHelpers.setScore(scores);
            ScoreHelpers.getInstance().currentLevelScores = scoreItem;
            gameMain.setScreen(new LevelModeFinishScreen(gameMain));

        }
        //shapeRenderer.end();
    }

    public float Enbuyukbulma(float a, float b, float c) {

        if (a > b && a > c) {
            enbuyuk = 1;
            return ari1Xekseni[arisutunsayisi - 1];

        } else if (b > a && b > c) {
            enbuyuk = 2;
            return ari2Xekseni[arisutunsayisi - 1];
        } else {
            enbuyuk = 3;
            return ari3Xekseni[arisutunsayisi - 1];
        }
    }

    private void cizdir() {
        //arkaplanın kamerayı takibi
        kamera.update();
        batch.setProjectionMatrix(kamera.combined);
        batch.begin();
        if (oyunDurumu == OyunDurumu.devamediyor) {
            //if (bitisSayaci>5)
            batch.draw(bitis, bitisKonumX, 0, screenWidth / 17, screenHeight);
            if (Locale.getDefault().getLanguage().contains("tr")) {
                yazi.draw(batch, String.valueOf("Level: " + this.level), yaziX, backgroundActor.getHeight() / 12);//leveli yazdır
            } else {
                yazi.draw(batch, String.valueOf("Level: " + this.level), yaziX, backgroundActor.getHeight() / 12);//leveli yazdır
            }
        }
        batch.draw(baslangic, screenWidth/2-(baslangic.getRegionWidth()), 0, screenWidth / 17, screenHeight);
        for (int i = 0; i < arisutunsayisi; i++) {
            //arıların çizimi
            batch.draw((TextureRegion) bulletAnimation.getKeyFrame(gecensure), ari1Xekseni[i], ari1Yekseni[i], screenWidth / 10, screenHeight / 15);
            batch.draw((TextureRegion) bulletAnimation.getKeyFrame(gecensure), ari2Xekseni[i], ari2Yekseni[i], screenWidth / 10, screenHeight / 15);
            batch.draw((TextureRegion) bulletAnimation.getKeyFrame(gecensure), ari3Xekseni[i], ari3Yekseni[i], screenWidth / 10, screenHeight / 15);
        }


        //yazi.draw(batch, String.valueOf(skor), kamera.position.x + backgroundActor.getWidth() / 4.2f, backgroundActor.getHeight() / 12);//skoru yazdır
        batch.draw((TextureRegion) birdAnimation.getKeyFrame(gecensure), kusPozisyonu.x, kusPozisyonu.y, kusGenislik, kusYukseklik);

        if (oyunDurumu == OyunDurumu.bitis) {

            // scoreItem.setSuccesfully(false);
            scoreItem.setScore(ariXsetdeger);
            dispose();
            ScoreHelpers.getInstance().currentLevelScores = scoreItem;
            gameMain.setScreen(new LevelModeFinishScreen(gameMain));
            //gameMain.createDialogLoginForLeaderBoard();
            gameMain.gameCount++;
            if (gameMain.gameCount == 5 || gameMain.gameCount == 15 || gameMain.gameCount == 35 || gameMain.gameCount == 65) {
                if (!gameMain.playServices.isSignedIn()) {
                    gameMain.createDialogLoginForLeaderBoard();
                }
            }

        }
        batch.end();

    }

    private void resetle() {
        kusPozisyonu.set(KUS_BASLANGIC_KONUM_X, KUS_BASLANGIC_KONUM_Y);
        kamera.position.x = 400;
        yerCekimi.set(0, yercekimi);
        kusYercekimi.set(0, 0);
        time = 0;
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

