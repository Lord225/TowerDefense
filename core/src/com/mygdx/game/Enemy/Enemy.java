package com.mygdx.game.Enemy;

public abstract class Enemy extends Entity
{
    float progress = 0.0f;

    float health;
    float speed;

    public void updateHealth(float damage){
        this.setHealth(getHealth()-damage);
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
