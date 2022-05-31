package com.mygdx.game.Enemy;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public abstract class Enemy extends Sprite
{
    public Vector2 position = new Vector2();

    float health;
    float speed;
    Sprite skin;


    abstract void updateHealth(float damage);
    abstract void move();
    abstract float getHealth();
    abstract void setHealth(float newHp);
    abstract float getSpeed();
    abstract void setSpeed(int newVal);

    @Override
    public void draw(Batch batch) {
        // set origin to mid
        // set postion
        // draw.
        super.draw(batch);

    }
}
