package com.mygdx.game.Towers;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.Enemy.Enemy;

import java.util.Vector;

public abstract class Building extends Sprite
{
    public abstract void update_enemies(Vector<Enemy> enemies_in_range);

    public abstract float get_range();
}
