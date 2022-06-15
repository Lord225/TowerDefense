package com.mygdx.game.Towers;

import Miscellaneous.Resources;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.Enemy.Arrow;
import com.mygdx.game.Enemy.Enemy;
import com.mygdx.game.Enemy.Entity;
import com.mygdx.game.Enemy.FireBall;

import java.util.Random;

public class FireballTower extends Building
{
    static Random rnd = new Random();
    float fireballrange = 1.0f;

    public FireballTower(float range, float shootingCooldown, float fireballrange){
        this.cost = 300;
        this.range = range;
        this.shootingCooldown = shootingCooldown;
        this.skin = new Sprite(Resources.getInstance().tower_sprite);
        this.skin.setColor(255.0f,0f,0f,1f);
        this.fireballrange = fireballrange;
    }

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

    @Override
    public void update()
    {
        currentCooldown += Gdx.graphics.getDeltaTime();
    }


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
