package com.mygdx.game.Ui;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;


public abstract class UiTemplate extends Actor {
    protected Stage stage;
    protected Viewport uiPort;
    public UiTemplate(Viewport ui){
        this.uiPort=ui;
        stage=new Stage(this.uiPort);
    }

    public void draw() {
        stage.setDebugAll(true);
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }
}