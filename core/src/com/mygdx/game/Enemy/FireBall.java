package com.mygdx.game.Enemy;

import Miscellaneous.Resources;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Map.Tile;

public class FireBall extends Projectile
{
    float fireballrange = 50.0f;

    public FireBall(Enemy target, Vector2 start, float speed, float fireballrange) {
        super(target, start, speed);
        this.skin = Resources.getInstance().fireball_sprite;
    }

    public static FireBall emmit(Enemy target, Tile start, float fireballrange)
    {
        Resources.getInstance().fireball_sound.play();
        return new FireBall(target, start.getPosition(), 0.01f, fireballrange);
    }

    @Override
    public void onProjectileHit(Enemy enemy) {
        Resources.getInstance().arrow_hit_sound.play();
        var inRange=  EntityMenager
                .getDefaultMenager()
                .getRef()
                .stream()
                .filter(x -> x instanceof Enemy)
                .map(x -> (Enemy)x)
                .filter(x -> {
                    float dis2 = x.getEntityPos().sub(getEntityPos()).len();
                    System.out.println(dis2);
                    return dis2 < fireballrange;
                })
                .toList();

        for(var target : inRange)
        {
            target.updateHealth(20.0f);
        }
    }

}
