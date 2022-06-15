package com.mygdx.game.Ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Class for creating Buttons elements in UI
 */
public class Button extends UiTemplate{
    Texture myTexture;
    TextureRegion myTextureRegion;
    TextureRegionDrawable myTextureDrawable;
    ImageButton button;
    Vector2 position;

    /**
     * Constructor for creating button with texture
     * Adding actor for stage
     * @param t Texture to cover button
     * @param tr TextureRegion to establish region of our button
     * @param trd TextureDrawableRegion Used for creating tints etc
     * @param pos Position of Button according to X and Y cordinates
     * @param stage Stage for creating actor with button in it
     */
    public Button(Texture t, TextureRegion tr, TextureRegionDrawable trd, Vector2 pos, Stage stage) {
        super(stage);
        this.myTexture = t;
        this.myTextureRegion = tr;
        this.myTextureDrawable = trd;
        this.position=pos;

        button = new ImageButton(myTextureDrawable); //Set the button up
        button.setPosition(position.x,position.y);
        Gdx.input.setInputProcessor(stage);
        stage.addActor(button);
    }

    @Override
    public boolean addListener(EventListener listener) {
        return button.addListener(listener);
    }
}