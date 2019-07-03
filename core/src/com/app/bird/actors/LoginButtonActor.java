package com.app.bird.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

import java.util.Locale;

import static com.app.bird.utils.Constants.LOGIN_BUTTON_EN;
import static com.app.bird.utils.Constants.LOGIN_BUTTON_TR;

public class LoginButtonActor extends Actor {
    TextureRegion region;

    public LoginButtonActor() {
        if (Locale.getDefault().getLanguage().contains("tr")) {
            region = new TextureRegion(new Texture(LOGIN_BUTTON_TR));
        } else {
            region = new TextureRegion(new Texture(LOGIN_BUTTON_EN));
        }
        setBounds(region.getRegionX(), region.getRegionY(),
                Gdx.graphics.getWidth() / 6, Gdx.graphics.getHeight() / 8);
        setX(Gdx.graphics.getWidth() / 2 - getWidth() / 2);
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
