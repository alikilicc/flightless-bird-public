package com.app.bird.utils;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class CustomListener extends ClickListener {

    private final Actor yourActor;

    public CustomListener(Actor yourActor) {
        this.yourActor = yourActor;
    }

    @Override
    public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
    }
}