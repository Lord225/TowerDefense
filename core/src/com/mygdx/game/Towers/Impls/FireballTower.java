package com.mygdx.game.Towers.Impls;

import Miscellaneous.Resources;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.Entites.Enemy.Enemy;
import com.mygdx.game.Entites.Entity;
import com.mygdx.game.Entites.Projectiles.FireBall;
import com.mygdx.game.Towers.Building;

import java.util.Random;
/**
 * FireballTower, slowest tower (extends Building). It has the highest cost, highest range and cooldown. It is able to hit multiple opponents
 */
public class FireballTower extends Building
{
    static Random rnd = new Random();
    float fireballrange = 1.0f;

    /**
     * Constructor for QStoneTower class
     * @param range turret's range
     * @param shootingCooldown turret's cooldown
     * @param fireballrange range of fireballs
     */
    public FireballTower(float range, float shootingCooldown, float fireballrange){
        this.cost = 300;
        this.setRange(range);
        this.shootingCooldown = shootingCooldown;
        this.skin = new Sprite(Resources.getInstance().tower_sprite);
        this.skin.setColor(255.0f,0f,0f,1f);
        this.fireballrange = fireballrange;
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
     * It is different from other towers, it randomly targets enemies that are in range for the fireball attack
     * @see Building
     * @param enemies_in_range array with enemies
     */
    @Override
    public void update_enemies(Entity[] enemies_in_range)
    {
        if(enemies_in_range.length == 0)
            return;

        if(currentCooldown >= shootingCooldown)
        {
            var target = enemies_in_range[rnd.nextInt(enemies_in_range.length)];

            if (target instanceof Enemy enemy) {
                FireBall.emmit(enemy, tile, fireballrange);
                currentCooldown = 0;
            }
        }
    }
}
