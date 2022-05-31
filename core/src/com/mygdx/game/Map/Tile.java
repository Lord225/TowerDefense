package com.mygdx.game.Map;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Towers.Building;

public class Tile extends Sprite
{
    static float size = 32.0f;

    Vector2 position = new Vector2();
    Building placed_building;

    public Tile(Sprite texture, float posX, float posY)
    {
        position.set(posY*size, posX*size);
        this.set(texture);
    }

    @Override
    public void draw(Batch batch) {
        this.setPosition(position.x, position.y);
        super.draw(batch);

        if (placed_building != null)
        {
            placed_building.draw(batch);
        }
    }
}
