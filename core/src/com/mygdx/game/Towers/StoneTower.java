package com.mygdx.game.Towers;

import Miscellaneous.Resources;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.Enemy.Enemy;

import java.util.Vector;
import java.awt.geom.Point2D;

public class StoneTower extends Building {

    public StoneTower(float range, float shootingCooldown){
        this.range = range;
        this.shootingCooldown = shootingCooldown;
        this.skin = Resources.getInstance().tower_sprite;
    }
    @Override
    public void update_enemies(Vector<Enemy> enemies_in_range)
    {
        System.out.println("Enemy Update!: " + enemies_in_range.size());
    }

    public Enemy findClosestEnemy(Vector<Enemy> enemies_in_range) {
        float closestRange = Float.MAX_VALUE;
        float currentRange = 0.0F;
        Enemy closestEnemy = null;

        for(Enemy enemy : enemies_in_range){
            currentRange = (float) Point2D.distance(enemy.getX(), enemy.getY(), this.skin.getX(), this.skin.getY());
            if(currentRange<=closestRange){
                closestRange = currentRange;
                closestEnemy = enemy;
            }
        }
        return closestEnemy;
    }

    @Override
    public float getRange() {
        return this.range;
    }

    @Override
    public void setRange(float range) {
        this.range = range;
    }

    @Override
    public float getCooldown() {
        return this.shootingCooldown;
    }

    @Override
    public void setCooldown(float cooldown) {
        this.shootingCooldown = cooldown;
    }
}
