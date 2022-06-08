package com.mygdx.game.Ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.Viewport;

public class Text extends UiTemplate{
    Label label;
    Label.LabelStyle labelStyle;
    String text;
    Vector2 position;

    public Text(Viewport ui,Vector2 pos,String textExample) {
        super(ui);
        this.position=pos;
        this.text=textExample;
        labelStyle=new Label.LabelStyle();
        labelStyle.font=new BitmapFont();
        labelStyle.fontColor= Color.RED;

        label = new Label(text,labelStyle);
        label.setSize(200,50);
        label.setPosition(position.x,position.y);
        stage.addActor(label);
    }
}
