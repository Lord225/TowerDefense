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

public class Button extends UiTemplate{
    Texture myTexture;
    TextureRegion myTextureRegion;
    TextureRegionDrawable myTextureDrawable;
    ImageButton button;
    Vector2 position;

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
        /*
        button.addListener(new ClickListener()
        {
            @Override
            public void clicked(InputEvent event, float x, float y)
            {
                System.out.println("Wcisniete" + Gdx.input.getX() + " " + Gdx.input.getY());
            }
        });
         */
    }

    @Override
    public boolean addListener(EventListener listener) {
        return button.addListener(listener);
    }
}
