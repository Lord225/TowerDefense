package com.mygdx.game.Enemy;

import Miscellaneous.Resources;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Ghost extends Enemy
{
    public Ghost(float hp, float speed){
        this.health = hp;
        this.speed = speed;
        this.skin = Resources.getInstance().ghost_sprite;

    }
    @Override
    public void updateHealth(float damage){
        this.health -= getHealth();
    }
    @Override
    public void move(){}
    @Override
    public float getHealth(){
        return this.health;
    }
    @Override
    public void setHealth(float newHp){
        this.health = newHp;
    }
    @Override
    public float getSpeed(){
        return this.speed;
    }
    @Override
    public void setSpeed(int newVal){
        this.speed = this.speed - newVal;
    }
}
