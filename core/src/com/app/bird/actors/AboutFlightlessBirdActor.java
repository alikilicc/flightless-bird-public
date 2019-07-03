package com.app.bird.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

import static com.app.bird.utils.Constants.INFO_ICON_PATH;

public class AboutFlightlessBirdActor extends Actor {
    TextureRegion region;

    public AboutFlightlessBirdActor() {
        region = new TextureRegion(new Texture(INFO_ICON_PATH));
        setBounds(region.getRegionX(), region.getRegionY(),
                Gdx.graphics.getWidth() / 12, Gdx.graphics.getWidth() / 12);
        setX(15);
        setY(Gdx.graphics.getHeight() - getHeight());
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        Color color = getColor();
        batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);
        batch.draw(region, getX(), getY(), getOriginX(), getOriginY(),
                getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());

    }

}
