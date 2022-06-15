package com.mygdx.game.Entites;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

/**
 * <h1>Entity</h1>
 * Class responsible for all actors that are currently in game. This class is highly relateed to
 * <b>EntiryMenager</b> Instances of this class are automaticly registed in default <b>EntityMenager</b>
 * <p>
 * It menages rotation, postion and display of an actor.
 * <p>
 * You can send signal to kill an entiy by setting {@code is_alive} flag to false.
 * @see com.mygdx.game.Entites.EntityMenager
 */
public abstract class Entity extends Sprite {
    protected Sprite skin;
    protected Vector2 position = new Vector2();
    protected float rotation = 0;

    /**
     * <h1>Is Alive?</h1>
     * {@code Entity} will be hold in {@code EntityMenager}  as long as {@code is_alive} flag is set to <b>true</b>
     * Entity can kill itself by setting is_alive flag inside update method
     * <h1>Example</h1>
     * <pre>{@code
     * Entity entity = get_an_entity(); // create new entity
     *
     * entity.is_alive = false; // Kill immidetly
     * }</pre>
     */
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

    /**
     * Method called every frame for every entity in <b>EntiryMenager</b>
     */
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

