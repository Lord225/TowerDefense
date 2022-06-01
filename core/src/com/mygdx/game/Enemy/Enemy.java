package com.mygdx.game.Enemy;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public abstract class Enemy extends Sprite
{
    public Vector2 position = new Vector2();
    public float progress = 0.0f;

    public float health;
    public float speed;
    public Sprite skin;


    abstract public void updateHealth(float damage);
    abstract public void move();
    abstract public float getHealth();
    abstract public void setHealth(float newHp);
    abstract public float getSpeed();
    abstract public void setSpeed(int newVal);
    public void setPositionEnemy(Vector2 position){
        this.position = position;
    }

    @Override
    public void draw(Batch batch) {
        // set origin to mid
        // set postion
        // draw.

        skin.setPosition(this.position.x, this.position.y);
        skin.draw(batch);


    }
}
