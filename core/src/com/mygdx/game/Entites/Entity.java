package com.mygdx.game.Entites;


import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public abstract class Entity extends Sprite {

    protected Sprite skin;
    protected Vector2 position = new Vector2();
    protected float rotation = 0;

    public boolean is_alive = true;

    public Entity()
    {
        EntityMenager.registerEntity(this);
    }

    public void setPositionEntity(Vector2 position){
        this.position = position;
    }
    public void setRotationEntity(float rot){
        this.rotation = rot;
    }

    @Override
    public void draw(Batch batch) {
        skin.setPosition(this.position.x, this.position.y);
        skin.setRotation(rotation);
        skin.draw(batch);
    }

    public void update() {}

    public boolean isAlive() { return is_alive; }
    public Sprite getEntitySkin(){
        return this.skin;
    }
    public Vector2 getEntityPos(){
        return new Vector2(this.position);
    }
    public void setEntityPos(Vector2 position){
        this.position = position;
    }
}

