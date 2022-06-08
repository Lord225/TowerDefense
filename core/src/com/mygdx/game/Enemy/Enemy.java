package com.mygdx.game.Enemy;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public abstract class Enemy extends Entity
{
    //public Vector2 position = new Vector2();
    float progress = 0.0f;

    float health;
    float speed;
    //public Sprite skin;



    public void updateHealth(float damage){
        this.health -= getHealth();
    }

    public float getHealth(){
        return this.health;
    }

    public void setHealth(float newHp){
        this.health = newHp;
    }

    public float getSpeed(){
        return this.speed;
    }

    public void setSpeed(int newVal){
        this.speed = this.speed - newVal;
    }

    public float getProgress() {
        return progress;
    }

    public void setProgress(float progress) {
        this.progress = progress;
    }
}
