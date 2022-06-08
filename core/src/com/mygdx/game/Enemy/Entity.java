package com.mygdx.game.Enemy;


import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public abstract class Entity extends Sprite {

    protected Sprite skin;
    protected Vector2 position = new Vector2();
    public void setPositionEntity(Vector2 position){
        this.position = position;
    }
    @Override
    public void draw(Batch batch) {
        skin.setPosition(this.position.x, this.position.y);
        skin.draw(batch);
    }

    public void update() {}

    public Sprite getEntitySkin(){
        return this.skin;
    }
    public Vector2 getEntityPos(){
        return this.position;
    }
    public void setEntityPos(Vector2 position){
        this.position = position;
    }
}

