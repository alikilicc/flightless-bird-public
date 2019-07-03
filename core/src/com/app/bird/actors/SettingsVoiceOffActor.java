package com.app.bird.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

import static com.app.bird.utils.Constants.SETTING_VOICE_OFF_PATH;


public class SettingsVoiceOffActor extends Actor {
    TextureRegion region;

    public SettingsVoiceOffActor() {
        region = new TextureRegion(new Texture(SETTING_VOICE_OFF_PATH));
        setBounds(region.getRegionX(), region.getRegionY(),
                Gdx.graphics.getWidth() / 5, Gdx.graphics.getHeight() / 4);
        setX(15);
        setY(Gdx.graphics.getHeight() - getHeight()*5.5f);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        Color color = getColor();
        batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);
        batch.draw(region, getX(), getY(), getOriginX(), getOriginY(),
                getWidth(),getHeight(), getScaleX(), getScaleY(), getRotation());
    }
}
