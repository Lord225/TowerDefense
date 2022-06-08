package com.mygdx.game.Enemy;

import Miscellaneous.Resources;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Ghost extends Enemy
{
    public Ghost(float hp, float speed){
        this.health = hp;
        this.speed = speed;
        this.skin = Resources.getInstance().ghost_sprite;

    }
}
