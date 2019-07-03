package com.app.bird.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.app.bird.actors.AboutFlightlessBirdActor;
import com.app.bird.actors.BackgroundActor;
import com.app.bird.actors.BackgroundFreePlayActor;
import com.app.bird.actors.BirdActor;
import com.app.bird.actors.DutyListActor;
import com.app.bird.actors.DutyListButtonActor;
import com.app.bird.actors.FreeModeActor;
import com.app.bird.actors.LevelModeActor;
import com.app.bird.actors.LevelsButtonActor;
import com.app.bird.actors.LoginButtonActor;
import com.app.bird.actors.PageTitleActor;
import com.app.bird.actors.PauseActor;
import com.app.bird.actors.PlayButtonActor;
import com.app.bird.actors.PlayFreeActor;
import com.app.bird.actors.RankingActor;
import com.app.bird.actors.SettingsActor;
import com.app.bird.actors.SettingsBackgroundActor;
import com.app.bird.actors.SettingsBird1Actor;
import com.app.bird.actors.SettingsBird2Actor;
import com.app.bird.actors.SettingsMusicActor;
import com.app.bird.actors.SettingsMusicOffActor;
import com.app.bird.actors.SettingsVoiceActor;
import com.app.bird.actors.SettingsVoiceOffActor;
import com.app.bird.actors.SmallPlayActor;
import com.app.bird.utils.Constants;

import java.util.Locale;
import java.util.Random;

import static com.app.bird.utils.Constants.BACKGROUND_IMAGE_PATH;
import static com.app.bird.utils.Constants.BIRDS_1_IMAGE_PATH;
import static com.app.bird.utils.Constants.BIRDS_2_IMAGE_PATH;
import static com.app.bird.utils.Constants.BIRDS_3_IMAGE_PATH;
import static com.app.bird.utils.Constants.BIRDS_4_IMAGE_PATH;
import static com.app.bird.utils.Constants.BIRD_1_IMAGE_PATH;
import static com.app.bird.utils.Constants.BIRD_2_IMAGE_PATH;
import static com.app.bird.utils.Constants.BIRD_3_IMAGE_PATH;
import static com.app.bird.utils.Constants.BIRD_4_IMAGE_PATH;
import static com.app.bird.utils.Constants.DUTYLIST_PATH;
import static com.app.bird.utils.Constants.IVY_IMAGE_PATH;
import static com.app.bird.utils.Constants.LEVELS_ICON;
import static com.app.bird.utils.Constants.LEVEL_FINISH_FLAG;
import static com.app.bird.utils.Constants.LEVEL_FINISH_SCREEN_FLAG;
import static com.app.bird.utils.Constants.NEXT_BUTTON_RED_BACKGROUND;
import static com.app.bird.utils.Constants.NEXT_BUTTON_YELLOW_BACKGROUND;
import static com.app.bird.utils.Constants.REPEAT_BUTTON_BACKGROUND;


public class AssetsManager {
    public static AssetsManager ASSETS_MANAGER = null;

    public static AssetsManager getInstance() {
        if (ASSETS_MANAGER == null) {
            ASSETS_MANAGER = new AssetsManager();
        }

        return ASSETS_MANAGER;
    }

    private Texture textureBackground;
    private Texture textureNextLevelButtonYellow;
    private Texture textureNextLevelButtonRed;
    private Texture textureDutyListButton;
    private Texture textureLevelSelectButton;
    private Texture textureRepeatLevelButton;
    private Texture textureLevelFinishFlag;
    private Texture textureLevelMode;
    private Texture textureFreeMode;
    private Texture textureBird1;
    private Texture textureBird2;
    private Texture textureBird3;
    private Texture textureBird4;
    private Texture textureBirds1;
    private Texture textureBirds2;
    private Texture textureBirds3;
    private Texture textureBirds4;
    private PlayFreeActor playFreeActor;
    private BackgroundFreePlayActor backgroundFreePlayActor;
    private RankingActor rankingActor;
    private AboutFlightlessBirdActor aboutFlightlessBirdActor;
    private SettingsActor settingsActor;
    private PauseActor pauseActor;
    private LevelsButtonActor levelsButtonActor;
    private SettingsBackgroundActor settingsBackgroundActor;
    private SmallPlayActor smallPlayActor;
    private SettingsVoiceOffActor settingsVoiceOffActor;
    private SettingsMusicOffActor settingsMusicOffActor;
    private SettingsBird2Actor settingsBird2Actor;
    private SettingsMusicActor settingsMusicActor;
    private SettingsBird1Actor settingsBird1Actor;
    private SettingsVoiceActor settingsVoiceActor;
    private DutyListButtonActor dutyListButtonActor;
    private BackgroundActor backgroundActor;
    private FreeModeActor freeModeActor;
    private LevelModeActor levelModeActor;
    private PlayButtonActor playButtonActor;
    private LoginButtonActor loginButtonActor;
    private BirdActor birdActor_1;
    private BirdActor birdActor_2;
    private BirdActor birdActor_3;
    private BirdActor birdActor_4;
    private BirdActor birdActors_1;
    private BirdActor birdActors_2;
    private BirdActor birdActors_3;
    private BirdActor birdActors_4;
    private Animation birdAnimation;
    private Animation bulletAnimation;
    private Animation birdAnimation2;
    private Texture textureIvy;
    private TextureRegion ivy;
    private Texture textureFinishFlag;
    private TextureRegion textureRegionFinishFlag;
    private TextureRegion textureRegionNextButtonYellow;
    private TextureRegion textureRegionNextButtonRed;
    private TextureRegion textureRegionDutyListButton;
    private TextureRegion textureRegionRepeatLevel;
    private TextureRegion textureRegionLevelFinishFlag;
    private TextureRegion textureRegionLevelSelectButton;
    private TextureRegionDrawable nextButtonRedDrawable;
    private TextureRegionDrawable dutyListButtonDrawable;
    private TextureRegionDrawable nextButtonYellowDrawable;
    private TextureRegionDrawable repeatLevelDrawable;
    private TextureRegionDrawable levelFinishDrawable;
    private TextureRegionDrawable levelSelectDrawable;
    private Button successfullyLevelButton;
    private Button emptyLevelButton;
    private ImageButton nextButtonYellowImageButton;
    private ImageButton nextButtonRedImageButton;
    private DutyListActor dutyListActor;
    private ImageButton repeatLevelImageButton;
    private ImageButton levelFinishImageButton;
    private ImageButton levelSelectImageButton;
    private Image emptyLevelButtonImage;
    private Image successfullyLevelButtonImage;
    private Texture textureFinishBackground;
    private BackgroundActor finishBackground;
    private BitmapFont bitmapFontWhite;
    private BitmapFont bitmapFontRed;
    private Label successfullyLabel;
    private Label unSuccessfullyLabel;
    private Label scoreLabel;
    private Label bestScoreLabel;
    private BitmapFont gameFinishText;
    private BitmapFont gameTextInPlayScreen;
    private BitmapFont scoreText;
    private Skin skinEmptyLevel;
    private Skin skinSuccessfullyLevel;
    private PageTitleActor chooseLevelTitle;
    FreeTypeFontGenerator generator;
    public Sound sound;
    public Music music;

    public void setupAssets() {
        generator = new FreeTypeFontGenerator(Gdx.files.internal(Constants.FONT_NAME));
        initializeLabels();
        initializeSkins();
        initializeTexture();
        initializeTextureRegions();
        initializeActors();
        initializeDrawables();
        initializeButtons();
        initializeAnimation();
        initializeButtons();
        initializeBitmapFonts();
        initializeButtonImages();
        initSounds();
    }

    public Sound getSound() {
        return sound;
    }

    private void initializeButtonImages() {
        emptyLevelButtonImage = new Image(AssetsManager.getInstance().getSkinEmptyLevel().getDrawable("top"));
        successfullyLevelButtonImage = new Image(AssetsManager.getInstance().getSkinSuccessfullyLevel().getDrawable("top"));
    }

    private void initializeSkins() {
        skinEmptyLevel = new Skin(Gdx.files.internal("data/uiskin.json"));
        skinEmptyLevel.add("top", skinEmptyLevel.newDrawable("default-round", getDefaultTextColor()), Drawable.class);
        skinEmptyLevel.add("star-filled", skinEmptyLevel.newDrawable("white", Color.GOLD), Drawable.class);
        skinEmptyLevel.add("star-unfilled", skinEmptyLevel.newDrawable("white", Color.SKY), Drawable.class);

        skinSuccessfullyLevel = new Skin(Gdx.files.internal("data/uiskin.json"));
        skinSuccessfullyLevel.add("top", skinSuccessfullyLevel.newDrawable("default-round", Color.RED), Drawable.class);
        skinSuccessfullyLevel.add("star-filled", skinSuccessfullyLevel.newDrawable("white", Color.YELLOW), Drawable.class);
        skinSuccessfullyLevel.add("star-unfilled", skinSuccessfullyLevel.newDrawable("white", Color.GRAY), Drawable.class);
    }

    public Music getMusic() {
        return music;
    }

    private void initSounds() {
        sound = Gdx.audio.newSound(Gdx.files.internal("data\\chirp.wav"));
        music = Gdx.audio.newMusic(Gdx.files.internal("data\\fonbg.mp3"));
    }//yok yine :D telefonun sesi açık mı evet buna ben bakayı mmı kalsın şimdi
    // birde başka bir classın propertylerine erişmek için getter kullan direk kullanabilirsinde getter-setter daha iyi yol

    private void initializeBitmapFonts() {
        gameFinishText = creataGameFinishBitmapFont();
        gameFinishText.setColor(getDefaultTextColor());
        gameTextInPlayScreen = createBitmapInPlayScreen();
        gameTextInPlayScreen.setColor(Color.WHITE);
        scoreText = createScoreBitmapFont();
        scoreText.setColor(getDefaultTextColor());
        String text = "";
        if (Locale.getDefault().getLanguage().contains("tr")) {
            text = "Level Seç";
        } else {
            text = "Choose Level";
        }
        chooseLevelTitle = new PageTitleActor(
                text,
                Color.RED,
                Gdx.graphics.getHeight() / 14);
    }


    private void initializeLabels() {
        successfullyLabel = createSuccessfullyLevelLabel("1");
        unSuccessfullyLabel = createUnsuccessfullyLevelLabel("1");
        scoreLabel = createScoreLabel(1);
        bestScoreLabel = createBestScoreLabel(1);
    }

    private void initializeButtons() {
        nextButtonRedImageButton = new ImageButton(nextButtonRedDrawable);
        nextButtonYellowImageButton = new ImageButton(nextButtonYellowDrawable);
        repeatLevelImageButton = new ImageButton(repeatLevelDrawable);
        levelFinishImageButton = new ImageButton(levelFinishDrawable);
        levelSelectImageButton = new ImageButton(levelSelectDrawable);
        emptyLevelButton = new Button(skinEmptyLevel);
        successfullyLevelButton = new Button(skinSuccessfullyLevel);
    }

    private void initializeDrawables() {
        levelFinishDrawable = new TextureRegionDrawable(textureRegionLevelFinishFlag);
        nextButtonRedDrawable = new TextureRegionDrawable(textureRegionNextButtonRed);
        dutyListButtonDrawable = new TextureRegionDrawable(textureRegionDutyListButton);
        nextButtonYellowDrawable = new TextureRegionDrawable(textureRegionNextButtonYellow);
        repeatLevelDrawable = new TextureRegionDrawable(textureRegionRepeatLevel);
        levelSelectDrawable = new TextureRegionDrawable(textureRegionLevelSelectButton);
    }

    private void initializeTextureRegions() {
        ivy = new TextureRegion(textureIvy);
        textureRegionFinishFlag = new TextureRegion(textureFinishFlag);
        textureRegionLevelFinishFlag = new TextureRegion(textureLevelFinishFlag);
        textureRegionRepeatLevel = new TextureRegion(textureRepeatLevelButton);
        textureRegionNextButtonRed = new TextureRegion(textureNextLevelButtonRed);
        textureRegionDutyListButton = new TextureRegion(textureDutyListButton);
        textureRegionNextButtonYellow = new TextureRegion(textureNextLevelButtonYellow);
        textureRegionLevelSelectButton = new TextureRegion(textureLevelSelectButton);
    }

    private void initializeAnimation() {

        birdAnimation = new Animation(0.15f,
                new TextureRegion(textureBird1),
                new TextureRegion(textureBird2),
                new TextureRegion(textureBird3),
                new TextureRegion(textureBird4));


        birdAnimation2 = new Animation(0.15f,
                new TextureRegion(textureBirds1),
                new TextureRegion(textureBirds2),
                new TextureRegion(textureBirds3),
                new TextureRegion(textureBirds4));

        bulletAnimation = new Animation(1f,
                new TextureRegion(new Texture("bullet_1.png")),
                new TextureRegion(new Texture("bullet_2.png")),
                new TextureRegion(new Texture("bullet_3.png")),
                new TextureRegion(new Texture("bullet_4.png")));

    }

    private void initializeActors() {
        backgroundActor = new BackgroundActor(textureBackground);
        backgroundFreePlayActor = new BackgroundFreePlayActor();
        settingsBackgroundActor = new SettingsBackgroundActor();
        playFreeActor = new PlayFreeActor();
        freeModeActor = new FreeModeActor(textureFreeMode);
        levelModeActor = new LevelModeActor(textureLevelMode);
        playButtonActor = new PlayButtonActor();
        loginButtonActor = new LoginButtonActor();
        birdActor_1 = new BirdActor(textureBird1);
        birdActor_2 = new BirdActor(textureBird2);
        birdActor_3 = new BirdActor(textureBird3);
        birdActor_4 = new BirdActor(textureBird4);
        birdActors_1 = new BirdActor(textureBirds1);
        birdActors_2 = new BirdActor(textureBirds2);
        birdActors_3 = new BirdActor(textureBirds3);
        birdActors_4 = new BirdActor(textureBirds4);
        finishBackground = new BackgroundActor(textureFinishBackground);
        settingsVoiceActor = new SettingsVoiceActor();
        settingsBird1Actor = new SettingsBird1Actor();
        settingsBird2Actor = new SettingsBird2Actor();
        settingsMusicActor = new SettingsMusicActor();
        dutyListButtonActor = new DutyListButtonActor();
        settingsMusicOffActor = new SettingsMusicOffActor();
        settingsVoiceOffActor = new SettingsVoiceOffActor();
        settingsActor = new SettingsActor();
        pauseActor = new PauseActor();
        levelsButtonActor = new LevelsButtonActor();
        smallPlayActor = new SmallPlayActor();
        rankingActor = new RankingActor();
        dutyListActor = new DutyListActor();
        aboutFlightlessBirdActor = new AboutFlightlessBirdActor();
    }

    private void initializeTexture() {
        textureBackground = new Texture("background_" + getRandomNumberForBackgroundImage() + ".png");
        textureFreeMode = new Texture(NEXT_BUTTON_YELLOW_BACKGROUND);
        textureLevelMode = new Texture(NEXT_BUTTON_YELLOW_BACKGROUND);
        textureIvy = new Texture(IVY_IMAGE_PATH);
        textureNextLevelButtonYellow = new Texture(NEXT_BUTTON_YELLOW_BACKGROUND);
        textureDutyListButton = new Texture(DUTYLIST_PATH);
        textureNextLevelButtonRed = new Texture(NEXT_BUTTON_RED_BACKGROUND);
        textureLevelSelectButton = new Texture(LEVELS_ICON);
        textureRepeatLevelButton = new Texture(REPEAT_BUTTON_BACKGROUND);
        textureLevelFinishFlag = new Texture(LEVEL_FINISH_SCREEN_FLAG);
        textureBird1 = new Texture(BIRD_1_IMAGE_PATH);
        textureBird2 = new Texture(BIRD_2_IMAGE_PATH);
        textureBird3 = new Texture(BIRD_3_IMAGE_PATH);
        textureBird4 = new Texture(BIRD_4_IMAGE_PATH);
        textureBirds1 = new Texture(BIRDS_1_IMAGE_PATH);
        textureBirds2 = new Texture(BIRDS_2_IMAGE_PATH);
        textureBirds3 = new Texture(BIRDS_3_IMAGE_PATH);
        textureBirds4 = new Texture(BIRDS_4_IMAGE_PATH);
        textureFinishFlag = new Texture(LEVEL_FINISH_FLAG);
        textureFinishBackground = new Texture(BACKGROUND_IMAGE_PATH);
    }

    private int getRandomNumberForBackgroundImage() {
        return new Random().nextInt(10) + 1;
    }

    private BitmapFont createBitmapWithFontWhite(int size) {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal(Constants.FONT_TROIKA));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = size;
        BitmapFont leveliGecemedinText = generator.generateFont(parameter);
        leveliGecemedinText.setColor(getDefaultTextColor());
        return leveliGecemedinText;
    }

    private BitmapFont createBitmapWithFontRed() {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal(Constants.FONT_TROIKA));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = Gdx.graphics.getHeight() / 14;
        BitmapFont leveliGecemedinText = generator.generateFont(parameter);
        leveliGecemedinText.setColor(Color.RED);
        return leveliGecemedinText;
    }

    private Label createUnsuccessfullyLevelLabel(String level) {
        Label.LabelStyle label1Style = new Label.LabelStyle();
        label1Style.font = createBitmapWithFontRed();
        label1Style.fontColor = Color.RED;
        String text;
        if (Locale.getDefault().getLanguage().contains("tr")) {
            text = "Leveli Geçemedin.";
        } else {
            text = "You haven't passed this level";
        }
        Label label = new Label(text, label1Style);
        label.setAlignment(Align.center);
        return label;
    }

    private BitmapFont creataGameFinishBitmapFont() {
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = Gdx.graphics.getHeight() / 14;
        return generator.generateFont(parameter);
    }

    private BitmapFont createChooseLevelTitleBitmapFont() {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal(Constants.FONT_NAME));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 150;
        return generator.generateFont(parameter);
    }

    private BitmapFont createScoreBitmapFont() {
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 50;
        return generator.generateFont(parameter);
    }

    private BitmapFont createBitmapInPlayScreen() {
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 50;
        return generator.generateFont(parameter);
    }


    private Label createScoreLabel(int score) {
        Label.LabelStyle label1Style = new Label.LabelStyle();
        label1Style.font = createBitmapWithFontWhite(Gdx.graphics.getHeight() / 15);
        label1Style.fontColor = Color.WHITE;
        String text;
        if (Locale.getDefault().getLanguage().contains("tr")) {
            text = "Skor: " + score;
        } else {
            text = "Score: " + score;
        }
        Label label = new Label(text, label1Style);
        label.setAlignment(Align.center);
        return label;
    }

    private Label createBestScoreLabel(int score) {
        Label.LabelStyle label1Style = new Label.LabelStyle();
        label1Style.font = createBitmapWithFontWhite(Gdx.graphics.getHeight() / 17);
        label1Style.fontColor = Color.WHITE;
        String text;
        if (Locale.getDefault().getLanguage().contains("tr")) {
            text = "Skor: " + score;
        } else {
            text = "Score: " + score;
        }
        Label label = new Label(text, label1Style);
        label.setAlignment(Align.center);
        return label;
    }

    public Label getCustomLabel(String text) {
        Label.LabelStyle label1Style = new Label.LabelStyle();
        label1Style.font = createBitmapWithFontWhite(Gdx.graphics.getHeight() / 17);
        label1Style.fontColor = getDefaultTextColor();
        Label label = new Label(text, label1Style);
        label.setAlignment(Align.center);
        return label;
    }

    private Label createSuccessfullyLevelLabel(String level) {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal(Constants.FONT_TROIKA));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = Gdx.graphics.getHeight() / 15;
        BitmapFont leveliGecemedinText = generator.generateFont(parameter);
        leveliGecemedinText.setColor(getDefaultTextColor());
        Label.LabelStyle label1Style = new Label.LabelStyle();
        label1Style.font = createBitmapWithFontWhite(Gdx.graphics.getHeight() / 15);
        label1Style.fontColor = getDefaultTextColor();
        String text;
        if (Locale.getDefault().getLanguage().contains("tr")) {
            text = level + ". Leveli Geçtin ! ";
        } else {
            text = "You have passed " + numberToOrdinal(Integer.parseInt(level)) + " level";
        }
        Label label = new Label(text, label1Style);
        label.setAlignment(Align.center);
        return label;
    }

    private Color getDefaultTextColor() {
        return new Color(133f / 255f, 123f / 255f, 107f / 255f, 255f / 255f);
    }

    public Label getEmptySkinLabelForButton(int level) {
        Label emptySkinLabelForButton = new Label(Integer.toString(level), skinEmptyLevel);
        emptySkinLabelForButton.setFontScale(2.0f);
        emptySkinLabelForButton.setAlignment(Align.center);
        return emptySkinLabelForButton;
    }

    public Label getSuccessfullySkinLabelForButton(int level) {
        Label succesfullySkinLabelForButton = new Label(Integer.toString(level), skinEmptyLevel);
        succesfullySkinLabelForButton.setFontScale(1.5f);
        succesfullySkinLabelForButton.setAlignment(Align.center);
        return succesfullySkinLabelForButton;
    }

    public BackgroundActor getBackgroundActor() {
        return new BackgroundActor(textureBackground);
    }

    public void changeBackgroundActor() {
        textureBackground = new Texture("background_" + getRandomNumberForBackgroundImage() + ".png");
        backgroundActor.region = new TextureRegion(textureBackground);
    }

    public FreeModeActor getFreeModeActor() {
        return freeModeActor;
    }

    public LevelModeActor getLevelModeActor() {
        return levelModeActor;
    }

    public PlayButtonActor getPlayButtonActor() {
        return playButtonActor;
    }

    public BirdActor getBirdActor_1() {
        return birdActor_1;
    }

    public BirdActor getBirdActor_2() {
        return birdActor_2;
    }

    public BirdActor getBirdActor_3() {
        return birdActor_3;
    }

    public BirdActor getBirdActor_4() {
        return birdActor_4;
    }

    public Texture getTextureNextLevelButtonYellow() {
        return textureNextLevelButtonYellow;
    }

    public BirdActor getBirdActors_1() {
        return birdActors_1;
    }

    public BirdActor getBirdActors_2() {
        return birdActors_2;
    }

    public BirdActor getBirdActors_3() {
        return birdActors_3;
    }

    public BirdActor getBirdActors_4() {
        return birdActors_4;
    }

    public Animation getBirdAnimation() {
        return birdAnimation;
    }

    public Animation getBirdAnimation2() {
        return birdAnimation2;
    }

    public TextureRegion getIvy() {
        return ivy;
    }

    public TextureRegion getFinishFlag() {
        return textureRegionFinishFlag;
    }

    public BackgroundActor getFinishBackground() {
        return finishBackground;
    }

    public Texture getTextureBackground() {
        return textureBackground;
    }

    public PlayFreeActor getPlayFreeActor() {
        if (playFreeActor.remove()) {
            playFreeActor = new PlayFreeActor();
        }
        return playFreeActor;
    }

    public BackgroundFreePlayActor getBackgroundFreePlayActor() {
        return backgroundFreePlayActor;
    }

    public ImageButton getNextButtonYellowImageButton() {
        return nextButtonYellowImageButton;
    }

    public ImageButton getNextButtonRedImageButton() {
        return nextButtonRedImageButton;
    }

    public DutyListActor getDutyListActor() {
        return dutyListActor;
    }

    public ImageButton getRepeatLevelImageButton() {
        return repeatLevelImageButton;
    }

    public ImageButton getLevelFinishImageButton() {
        return levelFinishImageButton;
    }

    public ImageButton getLevelSelectImageButton() {
        return levelSelectImageButton;
    }


    public Label getSuccessfullyLabel(int level) {
        String text;
        if (Locale.getDefault().getLanguage().contains("tr")) {
            text = level + ". Leveli Geçtin ! ";
        } else {
            text = "You have passed " + numberToOrdinal(level) + " level";
        }
        successfullyLabel.setText(text);
        return successfullyLabel;
    }

    public Label getExistSuccessLevel(int level) {
        String text;
        if (Locale.getDefault().getLanguage().contains("tr")) {
            text = level + ". Leveli Zaten Geçmistin !";
        } else {
            text = "You have already passed " + numberToOrdinal(level) + " level";
        }
        //successfullyLabel.setFontScale(0.2f);
        successfullyLabel.setColor(Color.RED);
        successfullyLabel.setText(text);
        return successfullyLabel;
    }

    public Label getUnSuccessfullyLabel(int level) {
        String text;
        if (Locale.getDefault().getLanguage().contains("tr")) {
            text = "Leveli Geçemedin.";
        } else {
            text = "You haven't passed this level";
          //  unSuccessfullyLabel.setFontScale(0.2f);
        }

        unSuccessfullyLabel.setText(text);
        return unSuccessfullyLabel;
    }

    public Label getScoreLabel(int score) {
        String text;
        if (Locale.getDefault().getLanguage().contains("tr")) {
            text = "Skorun: " + score;
        } else {
            text = "Your Score: " + score;
        }
        scoreLabel.setText(text);
        return scoreLabel;
    }

    public Label getBestScoreLabel(int score) {
        String text;
        if (Locale.getDefault().getLanguage().contains("tr")) {
            text = "En iyi skorun: " + score;
        } else {
            text = "Your best score: " + score;
        }

        bestScoreLabel.setText(text);
        return bestScoreLabel;
    }

    public BitmapFont getGameFinishText() {
        return gameFinishText;
    }

    public BitmapFont getGameTextInPlayScreen() {
        return gameTextInPlayScreen;
    }

    public BitmapFont getScoreText() {
        return scoreText;
    }

    public RankingActor getRankingActor() {
        return rankingActor;
    }

    public SettingsBird2Actor getSettingsBird2Actor() {
        return settingsBird2Actor;
    }

    public SettingsMusicActor getSettingsMusicActor() {
        return settingsMusicActor;
    }

    public SettingsBird1Actor getSettingsBird1Actor() {
        return settingsBird1Actor;
    }

    public SettingsVoiceActor getSettingsVoiceActor() {
        return settingsVoiceActor;
    }

    public SettingsVoiceOffActor getSettingsVoiceOffActor() {
        return settingsVoiceOffActor;
    }

    public SettingsMusicOffActor getSettingsMusicOffActor() {
        return settingsMusicOffActor;
    }

    public SettingsActor getSettingsActor() {
        settingsActor.setVisible(true);
        return settingsActor;
    }

    public Button getSuccessfullyLevelButton() {
        return successfullyLevelButton;
    }

    public Button getEmptyLevelButton() {
        return emptyLevelButton;
    }

    public Skin getSkinEmptyLevel() {
        return skinEmptyLevel;
    }

    public Skin getSkinSuccessfullyLevel() {
        return skinSuccessfullyLevel;
    }

    public Image getEmptyLevelButtonImage() {
        return emptyLevelButtonImage;
    }

    public Image getSuccessfullyLevelButtonImage() {
        return successfullyLevelButtonImage;
    }

    public PageTitleActor getChooseLevelTitle() {
        return chooseLevelTitle;
    }

    public LoginButtonActor getLoginButtonActor() {
        return loginButtonActor;
    }

    public PauseActor getPauseActor() {
        return pauseActor;
    }

    public SmallPlayActor getSmallPlayActor() {
        return smallPlayActor;
    }

    public LevelsButtonActor getLevelsButtonActor() {
        return levelsButtonActor;
    }

    public DutyListButtonActor getDutyListButtonActor() {
        return dutyListButtonActor;
    }

    public AboutFlightlessBirdActor getAboutFlightlessBirdActor() {
        return aboutFlightlessBirdActor;
    }

    public SettingsBackgroundActor getSettingsBackgroundActor() {
        return settingsBackgroundActor;
    }

    private com.badlogic.gdx.graphics.Color convertArgbToLibGdxColor(int argbColor) {
        com.badlogic.gdx.graphics.Color color = new com.badlogic.gdx.graphics.Color();
        com.badlogic.gdx.graphics.Color.argb8888ToColor(color, argbColor);
        return color;
    }

    public Animation getBulletAnimation() {
        return bulletAnimation;
    }

    public static String numberToOrdinal(int n) {

        if (n == 0) {
            return String.valueOf(n);
        }
        int j = n % 10,
                k = n % 100;


        if (j == 1 && k != 11) {
            return n + "st";
        }
        if (j == 2 && k != 12) {
            return n + "nd";
        }
        if (j == 3 && k != 13) {
            return n + "rd";
        }
        return n + "th";
    }

}