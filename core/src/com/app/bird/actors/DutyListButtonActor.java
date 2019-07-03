package com.app.bird.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

import static com.app.bird.utils.Constants.DUTYLIST_PATH;

public class DutyListButtonActor extends Actor {
    TextureRegion region;

    public DutyListButtonActor() {
        region = new TextureRegion(new Texture(DUTYLIST_PATH));
        setBounds(region.getRegionX(), region.getRegionY(),
                Gdx.graphics.getWidth() / 6, Gdx.graphics.getHeight() / 5);
        setX(Gdx.graphics.getWidth() / 2 - getWidth() / 2);
        setY(Gdx.graphics.getHeight() / 2 - getHeight() / 2);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        Color color = getColor();
        batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);
        batch.draw(region, getX(), getY(), getOriginX(), getOriginY(),
                getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
    }

}
