package com.mygdx.game.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.mygdx.game.Enemy.Enemy;
import com.mygdx.game.Enemy.Ghost;

import java.util.Arrays;
import java.util.Vector;

public class Map extends Sprite
{
    Tile[][] tiles;
    public Route route;
    public Vector<Enemy> enemies = new Vector<>();

    public Map(String path)
    {
        this.tiles = new Tile[16][32];
        this.route = new Route();

        JsonReader json = new JsonReader();
        JsonValue base = json.parse(Gdx.files.internal(path));

        var sprites = Arrays.stream(base.get("textures").asStringArray())
                .map(texture_path -> new Sprite(new Texture(Gdx.files.internal(texture_path))))
                .toArray(Sprite[]::new);

        var map_layout = base.get("layout");
        var jsonroute = base.get("route");
        var isPlacable = base.get("placable").asBooleanArray();

        int i = 0;
        for(var strips : map_layout)
        {
            int j = 0;
            for(var tile_texture_index : strips.asIntArray())
            {
                this.tiles[i][j] = new Tile(sprites[tile_texture_index], i, j, isPlacable[tile_texture_index]);
                j += 1;
            }
            i += 1;
        }

        for(var route_point : jsonroute)
        {
            var x = route_point.asFloatArray()[0];
            var y = route_point.asFloatArray()[1];
            this.route.add_point(x, y);
        }
        this.route.set_on_arrival_callback(new Route.Callback() {

            @Override
            public void arrival_event(Enemy enemy) {
                System.out.println("Enemy hitted target");
            }
        });
    }


    public Tile get_tile_by_cords(int x, int y)
    {
        return tiles[x][y];
    }
    
    public void update()
    {
        route.update_enemies(enemies);
        update_turrents();
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

        // Draw enemies
        for(Enemy x: enemies){
            x.draw(batch);
        }
    }

    public void spawnEnemy()
    {
        Enemy enemy = new Ghost(100, 0.5f);

        enemies.add(enemy);
    }

    void update_turrents()
    {
        // Przejść przez wszstkie wiezyczki
        // Znaleść dla każdej wieżyczki enemiy w zasięgu
        // Wywołać dla każdej wiezyczki funkcje update_enemies (wcześniej)


    }

}
