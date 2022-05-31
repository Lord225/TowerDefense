package com.mygdx.game.Enemy;

import com.badlogic.gdx.graphics.g2d.Batch;

public class Ghost extends Enemy
{
    public Ghost(double hp, double speed){
        this.health = hp;
        this.speed = speed;
    }
    @Override
    void updateHealth(double damage){
        this.health -= getHealth();
    }
    @Override
    void move(){}
    @Override
    double getHealth(){
        return this.health;
    }
    @Override
    void setHealth(double newHp){
        this.health = newHp;
    }
    @Override
    double getSpeed(){
        return this.speed;
    }
    @Override
    void setSpeed(int newVal){
        this.speed = this.speed - newVal;
    }
}
