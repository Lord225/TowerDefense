package com.mygdx.game.Enemy;

import Miscellaneous.Resources;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Map.Map;
import com.mygdx.game.Map.Tile;

public class Arrow extends Projectile
{
    public Arrow(Enemy target, Vector2 start, float speed, Map map) {
        super(target, start, speed, map);
        this.skin = Resources.getInstance().arrow_sprite;
    }

    public static Arrow emmit(Enemy target, Tile start, Map map)
    {
        var projectile =  new Arrow(target, start.getPosition(), 0.2f, map);

        map.enemies.add(projectile);

        return projectile;
    }

    @Override
    public void onProjectileHit(Enemy enemy) {
        System.out.println("Enemy hitted");
        enemy.updateHealth(10.0f);
    }
}
