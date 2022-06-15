package com.mygdx.game.Entites.Projectiles;

import Miscellaneous.Resources;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Entites.Enemy.Enemy;
import com.mygdx.game.Entites.Entity;

/**
 * <h1>Projectile</h1>
 * Class that represents all Projectiles on map. It lineary interpolates between position of starting tail to target Enemy.
 * It calls abstract function <b>onProjectileHit</b> when it reaches the end.
 *
 * <h1>Properties</h1>
 * <lu>
 * <li>{@code target} - target enemy</li>
 * <li>{@code start} - start tile</li>
 * <li>{@code speed} - speed in percents per frame</li>
 * </lu>
 *
 * <h1>Example usage</h1>
 *
 * <pre>{@code
 * class Arrow extends Projectile
 * {
 *     public Arrow(Enemy target, Vector2 start, float speed) {
 *         super(target, start, speed);
 *         this.skin = Resources.getInstance().arrow_sprite;
 *     }
 *     @Override
 *     public void onProjectileHit(Enemy enemy) {
 *         Resources.getInstance().arrow_hit_sound.play();
 *         enemy.updateHealth(10.0f);
 *     }
 * }
 * }</pre>
 *
 * @see com.mygdx.game.Entites.Projectiles.Projectile#onProjectileHit(Enemy)
 */
public abstract class Projectile extends Entity
{
    private final Enemy target;
    private final Vector2 start;
    private final float speed;

    private float progress;

    /**
     *
     * @param target - target enemy
     * @param start  - start tile
     * @param speed  - speed in percents per frame
     */
    public Projectile(Enemy target, Vector2 start, float speed)
    {
        System.out.println("Creating projectile");
        this.target = target;
        this.start = start;
        this.speed = speed;
        this.progress = 0;
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

    /**
     * This function is called before destroing this projectile. When Projectile hits target enemy.
     * this enemy is passed as argument.
     * @param enemy - Enemy that was hitted.
     */
    public abstract void onProjectileHit(Enemy enemy);
}
