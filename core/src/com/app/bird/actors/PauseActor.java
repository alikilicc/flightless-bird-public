package com.app.bird.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

import static com.app.bird.utils.Constants.PAUSE_ICON;

public class PauseActor extends Actor {
    TextureRegion region;

    float positionX = 0, positionY = 0;

    public PauseActor() {
        region = new TextureRegion(new Texture(PAUSE_ICON));
        setBounds(region.getRegionX(), region.getRegionY(),
                Gdx.graphics.getWidth() / 10, Gdx.graphics.getWidth() / 10);
        setX(Gdx.graphics.getWidth() - getWidth());
        setY(Gdx.graphics.getHeight() - getHeight());
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        Color color = getColor();
        batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);
        batch.draw(region, getX(), getY(), getOriginX(), getOriginY(),
                getWidth(),getHeight(), getScaleX(), getScaleY(), getRotation());

    }
}
