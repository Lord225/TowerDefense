package com.mygdx.game.Towers;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.mygdx.game.Entites.Entity;
import com.mygdx.game.Map.Tile;
import java.awt.geom.Point2D;


/**
 * Abstract class <b>Building</b> which extends from <b>Entity</b> and is inherited by every tower related class
 */

public abstract class Building extends Entity
{

    public float cost;
    public float range;
    public float shootingCooldown;

    protected float currentCooldown;

    public Tile tile = null;

    /**
     * <h1>Abstract method update_enemies</h1>
     * It has different implementation for each turret, it is responsible for updating distance for each enemy in an array
     * @param enemies_in_range array with enemies
     */
    public abstract void update_enemies(Entity[] enemies_in_range);

    public float getRange() {
        return this.range;
    }

    public void setRange(float range) {
        this.range = range;
    }

    public float getCost() {
        return cost;
    }

    /**
     * <h1>draw method</h1>
     * Used for drawing skin of the turret
     * @param batch Object from libgdx handling skin
     */
    @Override
    public void draw(Batch batch) {
        skin.setPosition(position.x, position.y);
        skin.draw(batch);
    }

    /**
     * <h1>findClosestEnemy</h1>
     * Method used for finding closest enemy in an array (for turret). It calculates distance using Point2D class
     * @param enemies_in_range array of enemies
     * @return closestEnemy (entity object)
     */
    public Entity findClosestEnemy(Entity[] enemies_in_range) {
        float closestRange = Float.MAX_VALUE;
        float currentRange = 0.0F;
        Entity closestEnemy = null;

        for(Entity enemy : enemies_in_range){
            currentRange = (float) Point2D.distance(enemy.getX(), enemy.getY(), this.skin.getX(), this.skin.getY());
            if(currentRange<=closestRange){
                closestRange = currentRange;
                closestEnemy = enemy;
            }
        }
        return closestEnemy;
    }
}
