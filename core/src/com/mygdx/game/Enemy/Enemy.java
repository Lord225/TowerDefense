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


    abstract public void updateHealth(float damage);
    abstract public float getHealth();
    abstract public void setHealth(float newHp);
    abstract public float getSpeed();
    abstract public void setSpeed(int newVal);

    public float getProgress() {
        return progress;
    }

    public void setProgress(float progress) {
        this.progress = progress;
    }
}
