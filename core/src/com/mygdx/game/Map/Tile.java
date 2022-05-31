package com.mygdx.game.Map;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.Towers.Building;

public class Tile extends Sprite
{
    Building placed_building;

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
