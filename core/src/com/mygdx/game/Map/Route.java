package com.mygdx.game.Map;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Enemy.Enemy;

import java.util.Vector;

public class Route
{
    static class VectorWithLenght extends Vector2
    {
        public static float total = 0.0f;

        public float distance;

        VectorWithLenght(float x, float y)
        {
            super(x, y);
            total += len();
            distance = total;
        }
    }
    public Vector<VectorWithLenght> route = new Vector<>();

    void add_point(float x, float y)
    {
        route.add(new VectorWithLenght(x, y));
    }

    void update_enemy(Enemy enemy)
    {
        float progress = enemy.progress;

        for(int i = 0; i < route.size(); i++)
        {
            if(progress < route.elementAt(i).distance)
            {
                var pFrom = route.elementAt(i-1);
                var pTo = route.elementAt(i);

                var f = (progress - pFrom.distance) / (pTo.distance - pFrom.distance);
                var x = pFrom.x + (pTo.x - pFrom.x) * f;
                var y = pFrom.y + (pTo.y - pFrom.y) * f;

                enemy.setPositionEnemy(new Vector2(x, y));

                break;
            }
        }
    }

    void update_enemies(Vector<Enemy> enemies)
    {
        for(var enemy : enemies)
        {
            update_enemy(enemy);

            enemy.progress += enemy.getSpeed();

        }
    }
}
