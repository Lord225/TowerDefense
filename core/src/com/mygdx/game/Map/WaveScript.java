package com.mygdx.game.Map;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.Entites.Enemy.Ghost;

public class WaveScript
{
    public class Wave
    {
        public interface EnemyFactory
        {
            void make();
        }

        int size;
        EnemyFactory enemyFactory;
        float spawn_rate;

        private float wave_timer = 0;
        private int current_size = 0;

        public Wave(int size, EnemyFactory enemy, float spawn_rate)
        {
            this.size = size;
            this.enemyFactory = enemy;
            this.spawn_rate = spawn_rate;
        }

        void update()
        {
            wave_timer += Gdx.graphics.getDeltaTime();

            if(wave_timer > spawn_rate)
            {
                enemyFactory.make();
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
            new Wave(8, () -> new Ghost(20, 0.5f), 5.0f),
            new Wave(10, () -> new Ghost(50, 0.6f), 3.0f),
            new Wave(15, () -> new Ghost(100, 0.6f), 0.75f),
            new Wave(20, () -> new Ghost(100, 0.6f), 0.5f),
            new Wave(3, () -> new Ghost(500, 0.4f), 3.0f),
            new Wave(20, () -> new Ghost(100, 0.6f), 0.5f),
            new Wave(20, () -> new Ghost(100, 0.6f), 0.5f),
            new Wave(1, () -> new Ghost(800, 0.3f), 1.0f),
            new Wave(10, () -> new Ghost(100, 1.0f), 0.5f),
            new Wave(10, () -> new Ghost(100, 1.0f), 0.5f),
            new Wave(30, () -> new Ghost(80, 2.0f), 0.1f),
            new Wave(10, () -> new Ghost(1000, 0.5f), 0.15f),
            new Wave(5, () -> new Ghost(150, 1.0f), 0.3f),
            new Wave(5, () -> new Ghost(150, 1.0f), 0.3f),
    };


    void update()
    {
        if(current >= waves.length)
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
