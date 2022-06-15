package com.mygdx.game.Ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

/**
 * This class is template for creating UI elements
 */
public abstract class UiTemplate extends Actor {
    protected Stage stage;

    /**
     * Constructor for UI templates
     * @param stage Stage where we're going to build UI
     */
    public UiTemplate(Stage stage){
        this.stage=stage;
    }

    /**
     * Draws stage on screen after initializating object.
     */
    public void draw() {
        stage.setDebugAll(true);
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }
}