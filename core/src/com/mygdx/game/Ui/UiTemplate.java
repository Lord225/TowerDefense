package com.mygdx.game.Ui;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;


public abstract class UiTemplate extends Actor {
    protected Stage stage;

    public UiTemplate(Stage stage){
        this.stage=stage;
    }

    public void draw() {
        stage.setDebugAll(true);
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }
}