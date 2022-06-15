package com.mygdx.game.Map;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Towers.Building;

/**
 * <h1>Tile</h1>
 * Tile represents an tile on the map. You can place an <b>Building</b> on tile.
 * This class is used inside <b>Map</b> class.
 *
 * <h1>Example</h1>
 *
 * <pre>{@code
 * var tile = map.get_tile(0, 0); // Get tile from map
 *
 * var state = tile.place(building); // Try placing an building
 *
 * if(state == false)
 *     building.is_alive = false; // Destroy building if cannot place on thile
 *
 * }</pre>
 *
 * @see Building
 * @see Map
 */
public class Tile extends Sprite
{
    private final Vector2 position = new Vector2();
    private Building placed_building;
    private final boolean isPlacable;

    public final static float TILE_SIZE = 32.0f;


    public Vector2 getPosition() {
        return position;
    }
    public Vector2 getMiddlePosition() {
        return position.add(TILE_SIZE/2, TILE_SIZE/2);
    }

    /**
     * Try to place an building on this tile. Returns <b>false</b> if it is imposile to place building.
     * @param building_to_place - target instance of an building
     * @return <b>true</b> for succes
     */
    public boolean place(Building building_to_place)
    {
        if(placed_building != null)
            return false;
        if(building_to_place == null)
            return false;
        if(!isPlacable)
            return false;

        placed_building = building_to_place;

        placed_building.setEntityPos(this.position);
        placed_building.tile = this;
        return true;
    }

    /**
     * Returns building that is currently on this tile. Returns <b>null</b> is there is no buidling.
     * @return building on this tile
     */
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
