package com.mygdx.game.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.mygdx.game.Enemy.Enemy;

import java.util.Arrays;
import java.util.List;
import java.util.Vector;
import java.util.stream.Collectors;

public class Map extends Sprite
{
    Tile[][] tiles;
    public Route route;
    public Vector<Enemy> enemies = new Vector<>();

    public Map()
    {
        JsonReader json = new JsonReader();
        JsonValue base = json.parse(Gdx.files.internal("map_layout.json"));

        this.tiles = new Tile[16][32];

        var sprites = Arrays.stream(base.get("textures").asStringArray())
                .map(path -> new Sprite(new Texture(Gdx.files.internal(path))))
                .toArray(Sprite[]::new);

        var map_layout = base.get("layout");

        int i = 0;
        for(var strips : map_layout)
        {
            int j = 0;
            for(var tile_texture_index : strips.asIntArray())
            {
                this.tiles[i][j] = new Tile(sprites[tile_texture_index], i, j);
                j += 1;
            }
            i += 1;
        }
    }


    public Tile get_tile_by_cords(int x, int y)
    {
        return tiles[x][y];
    }

    public Tile get_tile_by_screen_space(int screen_x, int screen_y)
    {
        //TODO;
        return null;
    }

    public void update()
    {
        route.update_enemies();
    }

    @Override
    public void draw(Batch batch)
    {
        for (int j = 0; j < 32; j++) {
            for (int i = 0; i < 16; i++)
            {
                var tile = tiles[i][j];
                tile.draw(batch);
            }
        }
    }

    public void spawnEnemy(Batch batch){
        for(Enemy x: enemies){
            x.draw(batch);
        }
    }
}