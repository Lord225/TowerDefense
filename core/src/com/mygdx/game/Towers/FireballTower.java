package com.mygdx.game.Towers;

import Miscellaneous.Resources;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.Enemy.Arrow;
import com.mygdx.game.Enemy.Enemy;
import com.mygdx.game.Enemy.Entity;
import com.mygdx.game.Enemy.FireBall;

import java.util.Random;

public class FireballTower extends Building
{
    static Random rnd = new Random();
    float fireballrange = 1.0f;

    public FireballTower(float range, float shootingCooldown, float fireballrange){
        this.cost = 240;
        this.range = range;
        this.shootingCooldown = shootingCooldown;
        this.skin = new Sprite(Resources.getInstance().tower_sprite);
        this.skin.setColor(0f,255.0f,0f,1f);
        this.fireballrange = fireballrange;
    }
    @Override
    public void update()
    {
        currentCooldown += Gdx.graphics.getDeltaTime();
    }

}
