package com.mygdx.game.Towers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.Enemy.Enemy;

import java.util.Vector;

public abstract class Building extends Sprite
{
    Sprite skin;
    float range;
    float shootingCooldown;


    public abstract void update_enemies(Vector<Enemy> enemies_in_range); // przeciwnicy w zasiegu (sprawdzenie)
    public abstract float getRange(); // range wiezyczki
    public abstract void setRange(float range);
    public abstract float getCooldown();
    public abstract void setCooldown(float cooldown);

}
