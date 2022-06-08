package com.mygdx.game.Enemy;

import Miscellaneous.Resources;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;

public abstract class Enemy extends Entity
{
    float progress = 0.0f;

    float health;
    float speed;
    private float timeSeconds = 0f;
    private final float period = 0.5f;


    public void updateHealth(float damage){
        this.setHealth(getHealth()-damage);
        this.skin.setColor(255.0f,0f,0f,1f);
    }
    @Override
    public void update(){
        timeSeconds += Gdx.graphics.getDeltaTime();
        if(timeSeconds > period){
            timeSeconds-=period;
            this.skin.setColor(255.0f,255.0f,255.0f,1f);
        }
    }

    public float getHealth(){
        return this.health;
    }

    public void setHealth(float newHp) {
        this.health = newHp;
        if (this.health < 0)
            this.is_alive = false;
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
