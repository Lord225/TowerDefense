package com.mygdx.game.Ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

/**
 * Class for creating HealthBar
 */
public class HealthBar extends UiTemplate{
    ProgressBar healthBar;
    ProgressBar.ProgressBarStyle progressBarStyle;
    Vector2 position;
    int width;
    int height;

    /**
     * Constructor for healthbar
     * @param width Width of healthbar
     * @param height Height of healthbar
     * @param stage Stage where to draw healthbar
     * @param v Position of healthbar with x,y cord
     */
    public HealthBar(int width, int height, Stage stage, Vector2 v) {
        super(stage);
        position=v;
        this.width=width;
        this.height=height;

        Pixmap pixmapred = new Pixmap(this.width,this.height, Pixmap.Format.RGBA8888);
        pixmapred.setColor(Color.RED);
        pixmapred.fill();

        Pixmap pixmapgreen = new Pixmap(this.width,this.height, Pixmap.Format.RGBA8888);
        pixmapgreen.setColor(Color.GREEN);
        pixmapgreen.fill();

        Pixmap pixmapgreenBG = new Pixmap(0,this.height, Pixmap.Format.RGBA8888);
        pixmapgreen.setColor(Color.GREEN);
        pixmapgreen.fill();

        TextureRegionDrawable drawableRed = new TextureRegionDrawable(new TextureRegion(new Texture(pixmapred)));
        TextureRegionDrawable drawableGreen = new TextureRegionDrawable(new TextureRegion(new Texture(pixmapgreen)));
        TextureRegionDrawable drawableGreenBG = new TextureRegionDrawable(new TextureRegion(new Texture(pixmapgreenBG)));

        progressBarStyle=new ProgressBar.ProgressBarStyle();
        progressBarStyle.background=drawableRed;
        progressBarStyle.knob=drawableGreenBG;
        progressBarStyle.knobBefore=drawableGreen;

        healthBar=new ProgressBar(0.0F,1.0F,0.01F,false,progressBarStyle);
        healthBar.setPosition(position.x,position.y);
        healthBar.setBounds(position.x,position.y,this.width,this.height);
        healthBar.setValue(1F);

        this.stage.addActor(healthBar);

        pixmapgreen.dispose();
        pixmapred.dispose();
        pixmapgreenBG.dispose();
    }

    /**
     * Set value of health from 0-1
     * @param f Value of healthbar
     */
    public void setValue(Float f){
        healthBar.setValue(f);
    }

    /**
     *
     * @return Returns current value of healthbar
     */
    public float getValue(){
        return healthBar.getValue();
    }
}
