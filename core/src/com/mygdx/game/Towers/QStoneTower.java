package com.mygdx.game.Towers;

import Miscellaneous.Resources;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.Enemy.Arrow;
import com.mygdx.game.Enemy.Enemy;
import com.mygdx.game.Enemy.Entity;
import com.mygdx.game.Map.Map;

import java.util.Vector;
import java.awt.geom.Point2D;

public class QStoneTower extends Building {
    public QStoneTower(float range, float shootingCooldown){
        this.range = range;
        this.shootingCooldown = shootingCooldown;
        this.skin = Resources.getInstance().tower_sprite;
        this.skin.setColor(0f,255.0f,0f,1f);
    }
    @Override
    public void update_enemies(Entity[] enemies_in_range)
    {
        if(currentCooldown >= shootingCooldown) {
            var target = this.findClosestEnemy(enemies_in_range);

            if (target instanceof Enemy enemy) {
                Arrow.emmit(enemy, tile);
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
