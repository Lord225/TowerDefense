package com.mygdx.game.Map;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.Enemy.Enemy;

import java.util.Vector;

public class Map extends Sprite
{
    Tile[][] tiles;
    Vector<Enemy> enemies = new Vector<>();

    Map()
    {
        this.tiles = new Tile[32][16];
    }

    void update()
    {

    }

    @Override
    public void draw(Batch batch) {
        super.draw(batch);


    }
}
