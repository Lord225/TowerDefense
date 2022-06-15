package com.mygdx.game.Towers;

import Miscellaneous.Resources;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.Entites.Projectiles.Arrow;
import com.mygdx.game.Entites.Enemy.Enemy;
import com.mygdx.game.Entites.Entity;

/**
 * StoneTower, the most basic tower (extends Building). It has lowest cost, medium range and cooldown
 */
public class StoneTower extends Building {

    /**
     * Constructor for StoneTower class
     * @param range turret's range
     * @param shootingCooldown turret's cooldown
     */
    public StoneTower(float range, float shootingCooldown){
        this.cost = 80;
        this.range = range;
        this.shootingCooldown = shootingCooldown;
        this.skin = new Sprite(Resources.getInstance().tower_sprite);
    }

    /**
     * @see Entity
     */
    @Override
    public void update()
    {
        currentCooldown += Gdx.graphics.getDeltaTime();
    }

    /**
     * @see Building
     * @param enemies_in_range array with enemies
     */
    @Override
    public void update_enemies(Entity[] enemies_in_range) {
        if(currentCooldown >= shootingCooldown) {
            var target = this.findClosestEnemy(enemies_in_range);

            if (target instanceof Enemy enemy) {
                Arrow.emmit(enemy, tile);
                currentCooldown = 0;
            }
        }
    }
}
