package com.mygdx.game.Enemy;

import Miscellaneous.Resources;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Map.Map;
import com.mygdx.game.Map.Tile;

public class Arrow extends Projectile
{
    public Arrow(Enemy target, Vector2 start, float speed) {
        super(target, start, speed);
        this.skin = Resources.getInstance().arrow_sprite;
    }

    public static Arrow emmit(Enemy target, Tile start)
    {
        return new Arrow(target, start.getPosition(), 0.06f);
    }

    @Override
    public void onProjectileHit(Enemy enemy) {
        enemy.updateHealth(10.0f);
    }
}
