package com.mygdx.game.Ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

/**
 * Class for creating Text elements in UI
 */
public class Text extends UiTemplate {
    Label label;
    Label.LabelStyle labelStyle;
    String text;
    Vector2 position;

    /**
     * Constructor for Text
     * Adding Actor to stage
     * @param pos Position of text
     * @param textExample String containing text for Label
     * @param color Color for font
     * @param stage Stage where to draw Label
     */
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
    /**
     * Set displayed text for label
     * @param text String containing text for Label
     */
    public void setText(String text){
        this.text=text;
        label.setText(this.text);
    }
    /**
     * Sets visbility for our Label
     * @param visibility true or false
     */
    public void setVisibility(boolean visibility){
        label.setVisible(visibility);
    }
    /**
     * Sets position for our Label
     * @param v Vector cointaining X and Y cordinates
     */
    public void setPos(Vector2 v){
        label.setPosition(v.x,v.y);
    }
}
