package com.mygdx.game.Enemy;

import Miscellaneous.Resources;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Map.Map;

public abstract class Projectile extends Entity
{
    private final Enemy target;
    private final float speed;
    private final Map map;

    private float progress;

    public Projectile(Enemy target, Vector2 start, float speed, Map map)
    {
        System.out.println("Creating projectile");
        this.target = target;
        this.setPositionEntity(start);
        this.speed = speed;
        this.progress = 0;
        this.map = map;

        this.skin = Resources.getInstance().arrow_sprite;
    }

    @Override
    public void update()
    {
        var pFrom = this.target.getEntityPos();
        var pTo = this.getEntityPos();

        var x = pFrom.x + (pTo.x - pFrom.x) * progress;
        var y = pFrom.y + (pTo.y - pFrom.y) * progress;

        this.setPositionEntity(new Vector2(x, y));

        progress += speed;

        if(progress >= 1)
        {
            this.onProjectileHit(this.target);
            this.map.killEntity(this);
        }
    }

    public abstract void onProjectileHit(Enemy enemy);
}
