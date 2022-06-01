package com.mygdx.game.Map;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Enemy.Enemy;

import java.util.Vector;

public class Route
{
    public Vector<Vector2> route = new Vector<>();

    void spawn_enemy(Enemy enemy)
    {
        float progress =  enemy.progress;

        // Oblicz pozycje na podtawie tablicy route - czyli  0 - początek, 1 - koniec

        // Ustaw pozycje Enemy

        // x
        // |    o
        // |    |
        // --H--|
        // jeżeli progrss = 0, zwórci pozycje x (początek trasy)
        // jeżeli progrss = 1, zwróci pozycje o (koniec trasy)
        // jeżeli progress = 0.5 zwóci pozycje dokładnie na środku trasy (H)

        // [0,0] - [100, 0] - 100
        // [100, 0] - [100, 100] - 100
        // [100, 100] - [0, 0] - xxx
        //
        //

        for(int i=0;i<route.size();i++){

        }

        // enemy spawing
        /*
        Vector2 test1 = new Vector2();
        Vector2 test2 = new Vector2();
        Vector2 test3 = new Vector2();

        test1.x = 100;test1.y=100;
        test2.x = 0;test2.y=0;
        test3.x = 0;test3.y=100;

        enemy.setPositionEnemy(test1);
        */
    }

    void update_enemies()
    {
        // Update position of all enemies
        // after that update towers ec - find distance and call update_enemies
    }
}
