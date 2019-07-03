package com.app.bird.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class BirdActor extends Actor {
    TextureRegion region;

    public BirdActor(Texture texture) {
        region = new TextureRegion(texture);
        setBounds(region.getRegionX(), region.getRegionY(),
                Gdx.graphics.getWidth() / 6, Gdx.graphics.getHeight() / 5);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        Color color = getColor();
        batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);
        batch.draw(region, getX(), getY(), getOriginX(), getOriginY(),
                getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());

       /* elementPlay.draw(batch, play, wCenter - 450,
                hCenter + hCenter / 5,
                Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 4);*/

    }

}
