package com.mygdx.game.Enemy;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public abstract class Enemy extends Sprite
{
    float health;
    float speed;
    Sprite skin;


    abstract void updateHealth(float damage);
    abstract void move();
    abstract float getHealth();
    abstract void setHealth(float newHp);
    abstract float getSpeed();
    abstract void setSpeed(int newVal);


}
