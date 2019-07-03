package com.app.bird.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

import static com.app.bird.utils.Constants.BIRDS_4_IMAGE_PATH;

public class SettingsBird2Actor extends Actor {
    TextureRegion region;

    public SettingsBird2Actor() {
        region = new TextureRegion(new Texture(BIRDS_4_IMAGE_PATH));
        setBounds(region.getRegionX(), region.getRegionY(),
                Gdx.graphics.getWidth() / 7, Gdx.graphics.getHeight() / 5);
        setX(Gdx.graphics.getWidth() / 2 + Gdx.graphics.getWidth() / 5);
        setY(Gdx.graphics.getHeight() / 2 - Gdx.graphics.getHeight() / 15);

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        Color color = getColor();
        batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);
        batch.draw(region, getX(), getY(), getOriginX(), getOriginY(),
                getWidth(),getHeight(), getScaleX(), getScaleY(), getRotation());

       /* elementPlay.draw(batch, play, wCenter - 450,
                hCenter + hCenter / 5,
                Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 4);*/
    }
}
