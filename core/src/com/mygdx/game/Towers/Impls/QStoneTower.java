package com.mygdx.game.Towers.Impls;

import Miscellaneous.Resources;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.Entites.Projectiles.Arrow;
import com.mygdx.game.Entites.Enemy.Enemy;
import com.mygdx.game.Entites.Entity;
import com.mygdx.game.Towers.Building;

/**
 * QuickStoneTower, faster tower (extends Building). It has the medium cost, lowest range and cooldown
 */
public class QStoneTower extends Building {
    /**
     * Constructor for QStoneTower class
     * @param range turret's range
     * @param shootingCooldown turret's cooldown
     */
    public QStoneTower(float range, float shootingCooldown){
        this.cost = 120;
        this.range = range;
        this.shootingCooldown = shootingCooldown;
        this.skin = new Sprite(Resources.getInstance().tower_sprite);
        this.skin.setColor(0f,255.0f,0f,1f);
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
