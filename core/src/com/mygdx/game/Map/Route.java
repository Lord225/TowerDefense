package com.mygdx.game.Map;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Enemy.Enemy;

import java.util.Vector;

public class Route
{
    public Vector<Vector2> route = new Vector<>();

    void spawn_enemy(Enemy enemy)
    {
        // enemy spawing
    }

    void update_enemies()
    {
        // Update position of all enemies
        // after that update towers ec - find distance and call update_enemies
    }
}
