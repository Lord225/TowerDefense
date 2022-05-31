package com.mygdx.game.Enemy;

import com.badlogic.gdx.graphics.g2d.Sprite;

public abstract class Enemy extends Sprite
{
    double health;
    double speed;

    abstract void updateHealth(double damage);
    abstract void move();
    abstract double getHealth();
    abstract void setHealth(double newHp);
    abstract double getSpeed();
    abstract void setSpeed(int newVal);





}
