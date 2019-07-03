package com.app.bird.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class BackgroundActor extends Actor {

    public TextureRegion region;

    public BackgroundActor(Texture texture) {
        region = new TextureRegion(texture);
        setBounds(region.getRegionX(), region.getRegionY(),
                Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        Color color = getColor();
        batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);
        batch.draw(region, getX(), getY(), getOriginX(), getOriginY(),
                Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), getScaleX(), getScaleY(), getRotation());

    }
}
