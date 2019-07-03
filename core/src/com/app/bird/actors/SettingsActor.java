package com.app.bird.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

import static com.app.bird.utils.Constants.SETTING_WHEEL_PATH;

public class SettingsActor extends Actor {
    TextureRegion region;

    float positionX = 0, positionY = 0;

    public SettingsActor() {
        region = new TextureRegion(new Texture(SETTING_WHEEL_PATH));
        setBounds(region.getRegionX(), region.getRegionY(),
                Gdx.graphics.getWidth() / 11, Gdx.graphics.getWidth() / 11);
        setX(20);
        setY(20);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        Color color = getColor();
        batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);
        batch.draw(region, getX(), getY(), getOriginX(), getOriginY(),
                getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());

    }
}
