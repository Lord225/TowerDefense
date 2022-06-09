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

public class Text extends UiTemplate {
    Label label;
    Label.LabelStyle labelStyle;
    String text;
    Vector2 position;

    public Text(Viewport ui,Vector2 pos,String textExample,Color color) {
        super(ui);
        this.position=pos;
        this.text=textExample;
        labelStyle=new Label.LabelStyle();
        labelStyle.font=new BitmapFont();
        labelStyle.fontColor= color;
        label = new Label(this.text,labelStyle);
        stage.addActor(label);
    }
    public void setText(String text){
        this.text=text;
        label.setText(this.text);
        label.setPosition(position.x,position.y);
    }

    public String getText() {
        return text;
    }

    public Vector2 getPosition() {
        return position;
    }
}
