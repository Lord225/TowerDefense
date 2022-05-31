package com.mygdx.game.Map;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.Towers.Building;

public class Tile extends Sprite
{
    Texture texture;
    Building placed_building;

    Tile(Texture texture)
    {
        this.texture = texture;
    }

    @Override
    public void draw(Batch batch) {
        super.draw(batch);

        // draw base texture (ground)

        if (placed_building != null)
        {
            placed_building.draw(batch);
        }
    }
}
