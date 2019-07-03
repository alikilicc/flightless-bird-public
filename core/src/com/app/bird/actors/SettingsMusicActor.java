package com.app.bird.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.app.bird.utils.ActorClickListener;
import com.app.bird.utils.CustomListener;

import static com.app.bird.utils.Constants.SETTING_MUSIC_PATH;

public class SettingsMusicActor extends Actor {
    public TextureRegion region;
    private ActorClickListener clickListener;

    public SettingsMusicActor() {
        region = new TextureRegion(new Texture(SETTING_MUSIC_PATH));
        setBounds(region.getRegionX(), region.getRegionY(),
                Gdx.graphics.getWidth() / 12, Gdx.graphics.getWidth() / 12);
        setX(15);
        setY(Gdx.graphics.getHeight() / 3 - getHeight() / 1.2f);
        addListener(new CustomListener(this) {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                Gdx.app.log("SettingsScreen", "settings music actor clicked on inside actor");
                if (clickListener != null) {
                    clickListener.clicked();
                }
            }
        });
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

    public ActorClickListener getClickListener() {
        return clickListener;
    }

    public void setClickListener(ActorClickListener clickListener) {
        this.clickListener = clickListener;
    }
}