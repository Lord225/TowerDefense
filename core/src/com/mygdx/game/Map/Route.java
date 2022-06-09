package com.mygdx.game.Map;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Enemy.Enemy;
import com.mygdx.game.Enemy.Entity;

import java.io.Serializable;
import java.util.Vector;

public class Route implements Serializable
{
    static class VectorWithLenght extends Vector2
    {
        public static float total = 0.0f;
        public static float last_x = 0;
        public static float last_y = 0;

        public float distance;

        VectorWithLenght(float x, float y)
        {
            super(x, y);
            total += Math.sqrt((x-last_x)*(x-last_x) + (y-last_y)*(y-last_y));
            distance = total;
            last_x = x;
            last_y = y;
        }
    }

    interface Callback
    {
        void arrival_event(Entity enemy);
    }

    public Vector<Callback> callbacks = new Vector<>();
    public Vector<VectorWithLenght> route = new Vector<>();

    public Route()
    {
        set_on_arrival_callback(enemy -> enemy.is_alive = false);
    }

    void add_point(float x, float y)
    {
        route.add(new VectorWithLenght(x, y));
    }

    public Vector2 get_end()
    {
        return route.lastElement();
    }

    public Vector2 get_start()
    {
        return route.firstElement();
    }

    public float get_route_lenght()
    {
        return route.lastElement().distance;
    }

    public void update_enemy(Enemy enemy)
    {
        float progress = enemy.getProgress();

        for(int i = 0; i < route.size(); i++)
        {
            if(progress < route.elementAt(i).distance)
            {
                var pFrom = route.elementAt(i-1);
                var pTo = route.elementAt(i);

                var f = (progress - pFrom.distance) / (pTo.distance - pFrom.distance);
                var x = pFrom.x + (pTo.x - pFrom.x) * f;
                var y = pFrom.y + (pTo.y - pFrom.y) * f;

                enemy.setPositionEntity(new Vector2(x, y));
                break;
            }
        }
    }

    public void update_entities(Vector<Entity> entities)
    {
        for(var entity : entities)
        {
            if(entity instanceof Enemy enemy) {
                update_enemy(enemy);
                enemy.setProgress(enemy.getProgress() + enemy.getSpeed());
            }
        }

        for(var entity : entities)
        {
            if(entity instanceof Enemy enemy) {
                if (enemy.getProgress() > this.get_route_lenght()) {
                    enemie_arrived(enemy);
                }
            }
        }
    }

    void enemie_arrived(Enemy enemy)
    {
        for(var callback : callbacks)
        {
            callback.arrival_event(enemy);
        }
    }

    public void set_on_arrival_callback(Callback callback)
    {
        callbacks.add(callback);
    }
}
