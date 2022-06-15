package com.mygdx.game.Towers;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.mygdx.game.Entites.Entity;
import com.mygdx.game.Map.Tile;

import java.awt.geom.Point2D;

public abstract class Building extends Entity
{
    public float cost;
    public float range;
    public float shootingCooldown;

    protected float currentCooldown;

    public Tile tile = null;

    public abstract void update_enemies(Entity[] enemies_in_range);

    public float getRange() {
        return this.range;
    }// range wiezyczki
    public void setRange(float range) {
        this.range = range;
    }
    public float getCooldown() {
        return this.shootingCooldown;
    }
    public void setCooldown(float cooldown) {
        this.shootingCooldown = cooldown;
    }



    public float getCost() {
        return cost;
    }

    @Override
    public void draw(Batch batch) {
        skin.setPosition(position.x, position.y);
        skin.draw(batch);
    }

    public Entity findClosestEnemy(Entity[] enemies_in_range) {
        float closestRange = Float.MAX_VALUE;
        float currentRange = 0.0F;
        Entity closestEnemy = null;

        for(Entity enemy : enemies_in_range){
            currentRange = (float) Point2D.distance(enemy.getX(), enemy.getY(), this.skin.getX(), this.skin.getY());
            //System.out.println(this.skin.getX() + " " + this.skin.getY());
            if(currentRange<=closestRange){
                closestRange = currentRange;
                closestEnemy = enemy;
            }
        }
        return closestEnemy;
    }
}
