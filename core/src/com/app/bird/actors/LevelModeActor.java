package com.app.bird.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

import java.util.Locale;

import static com.app.bird.utils.Constants.LEVEL_MODE_SELECT_EN_PATH;
import static com.app.bird.utils.Constants.LEVEL_MODE_SELECT_TR_PATH;

public class LevelModeActor extends Actor {
    TextureRegion region;

    public LevelModeActor(Texture texture) {
        if (Locale.getDefault().getLanguage().equals("tr")) {
            region = new TextureRegion(new Texture(LEVEL_MODE_SELECT_TR_PATH));
        } else {
            region = new TextureRegion(new Texture(LEVEL_MODE_SELECT_EN_PATH));

        }
        setBounds(region.getRegionX(), region.getRegionY(),
                Gdx.graphics.getWidth() / 5, Gdx.graphics.getWidth() / 5);
        setX(Gdx.graphics.getWidth() / 2f );
        setY(Gdx.graphics.getHeight() / 2f - getHeight() / 2);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        Color color = getColor();
        batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);
        batch.draw(region, getX(), getY(), getOriginX(), getOriginY(),
                getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
    }

}