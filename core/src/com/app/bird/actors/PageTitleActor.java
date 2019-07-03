package com.app.bird.actors;

import com.app.bird.utils.Constants;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class PageTitleActor extends Actor {
    FreeTypeFontGenerator generator;
    FreeTypeFontGenerator.FreeTypeFontParameter parameter;
    BitmapFont bitmapFont;
    private Color color;
    private String text = "";

    public PageTitleActor(String fontText, Color textColor, int textSize) {
        this.text = fontText;
        this.color = textColor;
        generator = new FreeTypeFontGenerator(Gdx.files.internal(Constants.FONT_TROIKA));
        parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = textSize;
        bitmapFont = generator.generateFont(parameter);
        bitmapFont.setColor(color);
        //setPosition(Gdx.graphics.getWidth() / 2 - getWidth(), Gdx.graphics.getHeight() - getHeight()/2);
    }

    @Override
    public void draw(Batch batch, float alpha) {
        bitmapFont.draw(batch, text, 20, Gdx.graphics.getHeight() - 35);
    }


    public void setAlpha(int a) {
        Color color = getColor();
        setColor(color.r, color.g, color.b, a);
    }

}