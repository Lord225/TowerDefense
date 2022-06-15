package com.mygdx.game.Towers;

import Miscellaneous.Resources;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.Entites.Projectiles.Arrow;
import com.mygdx.game.Entites.Enemy.Enemy;
import com.mygdx.game.Entites.Entity;

public class QStoneTower extends Building {
    public QStoneTower(float range, float shootingCooldown){
        this.cost = 120;
        this.range = range;
        this.shootingCooldown = shootingCooldown;
        this.skin = new Sprite(Resources.getInstance().tower_sprite);
        this.skin.setColor(0f,255.0f,0f,1f);
    }
    @Override
    public void update()
    {
        currentCooldown += Gdx.graphics.getDeltaTime();
    }

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
