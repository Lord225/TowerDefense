package com.mygdx.game.Map;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.Enemy.Enemy;
import com.mygdx.game.Enemy.Ghost;
import com.mygdx.game.Map.Map;

public class WaveScript
{
    public class Wave
    {
        public interface EnemyFactory
        {
            void make();
        }

        int size;
        EnemyFactory enemy;
        float spawn_rate;

        private float wave_timer = 0;
        private int current_size = 0;

        public Wave(int size, EnemyFactory enemy, float spawn_rate)
        {
            this.size = size;
            this.enemy = enemy;
            this.spawn_rate = spawn_rate;
        }

        void update()
        {
            wave_timer += Gdx.graphics.getDeltaTime();

            if(wave_timer > spawn_rate)
            {
                enemy.make();
                current_size += 1;
                wave_timer = 0;
            }
        }

        boolean isDone()
        {
            return current_size == size;
        }
    }

    int current = 0;
    float wave_cooldown = 10.0f;
    float current_time = 0.0f;

    Wave[] waves = {
            new Wave(5, () -> new Ghost(100, 0.5f), 5.0f),
            new Wave(10, () -> new Ghost(100, 0.6f), 1.0f),
            new Wave(15, () -> new Ghost(100, 0.6f), 0.75f),
            new Wave(20, () -> new Ghost(100, 0.6f), 0.5f),
            new Wave(3, () -> new Ghost(500, 0.2f), 3.0f),
            new Wave(20, () -> new Ghost(100, 0.6f), 0.5f),
            new Wave(20, () -> new Ghost(100, 0.6f), 0.5f),
            new Wave(1, () -> new Ghost(800, 0.1f), 1.0f),
            new Wave(10, () -> new Ghost(100, 1.0f), 0.5f),
    };


    void update()
    {
        if(current > waves.length)
        {
            System.out.println("End of script");
            return;
        }

        if(waves[current].isDone())
        {
            current_time += Gdx.graphics.getDeltaTime();

            if(current_time > wave_cooldown)
            {
                current += 1;
                current_time = 0;
            }
            return;
        }

        waves[current].update();
    }
}