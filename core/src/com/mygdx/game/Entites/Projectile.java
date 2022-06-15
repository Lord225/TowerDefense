package com.mygdx.game.Entites;

import Miscellaneous.Resources;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Entites.Enemy.Enemy;

public abstract class Projectile extends Entity
{
    private final Enemy target;
    private final Vector2 start;
    private final float speed;

    private float progress;

    public Projectile(Enemy target, Vector2 start, float speed)
    {
        System.out.println("Creating projectile");
        this.target = target;
        this.start = start;
        this.speed = speed;
        this.progress = 0;

        this.skin = Resources.getInstance().arrow_sprite;
    }

    @Override
    public void draw(Batch batch)
    {
        var pFrom = new Vector2(this.position);
        var pTo = this.target.getEntityPos();

        var deg = pFrom.sub(pTo).angleDeg();

        this.setRotationEntity(deg+180-45);
        super.draw(batch);
    }

    @Override
    public void update()
    {
        var pFrom = this.start;
        var pTo = this.target.getEntityPos();

        var x = pFrom.x + (pTo.x - pFrom.x) * progress;
        var y = pFrom.y + (pTo.y - pFrom.y) * progress;

        this.setPositionEntity(new Vector2(x, y));

        progress += speed;

        if(progress >= 1)
        {
            this.onProjectileHit(this.target);
            this.is_alive = false;
        }
    }

    public abstract void onProjectileHit(Enemy enemy);
}
