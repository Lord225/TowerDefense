package com.mygdx.game.Enemy;

import Miscellaneous.Resources;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

public class Ghost extends Enemy
{
    public Ghost(float hp, float speed){
        this.health = hp;
        this.speed = speed;
        this.skin = Resources.ghost_sprite;

    }
    @Override
    void updateHealth(float damage){
        this.health -= getHealth();
    }
    @Override
    void move(){}
    @Override
    float getHealth(){
        return this.health;
    }
    @Override
    void setHealth(float newHp){
        this.health = newHp;
    }
    @Override
    float getSpeed(){
        return this.speed;
    }
    @Override
    void setSpeed(int newVal){
        this.speed = this.speed - newVal;
    }
}
