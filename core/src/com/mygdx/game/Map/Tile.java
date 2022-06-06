package com.mygdx.game.Map;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Towers.Building;

public class Tile extends Sprite
{
    public final static float TILE_SIZE = 32.0f;

    public final boolean isPlacable;
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
        if(building_to_place == null)
            return false;
        if(!isPlacable)
            return false;

        placed_building = building_to_place;

        placed_building.position.set(this.position);

        return true;
    }

    public Building get_building()
    {
        return placed_building;
    }


    public Tile(Sprite texture, float posX, float posY, boolean isPlacable)
    {
        position.set(posY * TILE_SIZE, posX * TILE_SIZE);
        this.isPlacable = isPlacable;
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
