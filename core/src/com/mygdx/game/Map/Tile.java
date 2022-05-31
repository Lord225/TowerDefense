package com.mygdx.game.Map;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.Towers.Building;

public class Tile extends Sprite
{
    static float size = 32.0f;

    Building placed_building;

    public Tile(Sprite texture, float posX, float posY)
    {
        System.out.println(posX);
        this.setPosition(posX*size, posY*size);
        this.set(texture);
        this.setOrigin(0,0);
    }

    @Override
    public void draw(Batch batch) {
        super.draw(batch);

        if (placed_building != null)
        {
            placed_building.draw(batch);
        }
    }
}
