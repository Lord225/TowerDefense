package com.mygdx.game.Ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

public class Text extends UiTemplate {
    Label label;
    Label.LabelStyle labelStyle;
    String text;
    Vector2 position;

    public Text(Vector2 pos, String textExample, Color color, Stage stage) {
        super(stage);
        this.position=pos;
        this.text=textExample;
        labelStyle=new Label.LabelStyle();
        labelStyle.font=new BitmapFont();
        labelStyle.fontColor= color;
        label = new Label(this.text,labelStyle);
        label.setPosition(position.x,position.y);
        stage.addActor(label);

    }
    public void setText(String text){
        this.text=text;
        label.setText(this.text);
    }
    public void setVisibility(boolean visibility){
        label.setVisible(visibility);
    }
    public String getText() {
        return text;
    }

    public Vector2 getPosition() {
        return position;
    }
}
