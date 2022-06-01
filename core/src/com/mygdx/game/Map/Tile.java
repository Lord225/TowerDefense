package com.mygdx.game.Map;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Towers.Building;

public class Tile extends Sprite
{
    public final static float TILE_SIZE = 32.0f;
    public final static Vector2 OFFSET = new Vector2();

    private final Vector2 position = new Vector2();


    private Building placed_building;


    public Vector2 getPosition() {
        return position;
    }
    public Vector2 getMiddlePosition() {
        return position.add(TILE_SIZE/2, TILE_SIZE/2);
    }
    public boolean place(Building building_to_place)
    {
        if(placed_building != null)
            return false;

        placed_building = building_to_place;
        return true;
    }

    public Building get_building()
    {
        return placed_building;
    }


    public Tile(Sprite texture, float posX, float posY)
    {
        position.set(posY * TILE_SIZE, posX * TILE_SIZE);
        this.set(texture);
    }

    @Override
    public void draw(Batch batch) {
        this.setPosition(position.x + OFFSET.x, position.y + OFFSET.y);
        super.draw(batch);

        if (placed_building != null)
        {
            placed_building.draw(batch);
        }
    }
}
