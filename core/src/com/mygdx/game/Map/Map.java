package com.mygdx.game.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.mygdx.game.Entites.Enemy.Enemy;
import com.mygdx.game.Entites.Entity;
import com.mygdx.game.Entites.EntityMenager;
import com.mygdx.game.Towers.Building;
import java.awt.geom.Point2D;
import java.util.Arrays;

/**
 * <h1>Map</h1>
 * Class that represents State of the game and its logic. Including:
 * <lu>
 *     <li><b>Tiles</b></li>
 *     <li><b>Enteties</b> - Including Placed buildings, Routes, Enemies, Projectiles</li>
 *     <li><b>GameScript</b></li>
 * </lu>
 * You can load an map preset from {@code json} file with this format
 * <pre>{@code
 * {
 *   "textures": [
 *      "path/to/textures.png"
 *   ],
 *   "placable":
 *   [
 *     false, // True for placable type, by index in textures
 *     true,  // False for non-placable type
 *   ],
 *   // Map layout as 2d array (32, 20) with indexed textures
 *   "layout": [
 *     [0,1,1,1,1,1,1,1,1,4,3,3,3,4,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1],
 *     [0,1,1,1,1,1,1,1,1,3,2,2,3,3,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1],
 *     [0,1,1,1,1,1,1,1,1,3,2,2,2,3,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1],
 *     [0,0,0,0,0,0,0,0,1,3,3,2,2,3,1,0,1,1,1,1,1,1,1,1,1,1,1,1,0,1,1,1],
 *     [1,1,1,1,1,1,1,0,1,3,2,2,2,3,1,0,1,1,1,1,1,1,1,1,1,1,1,1,0,1,1,1],
 *     [1,1,1,1,1,1,1,0,1,4,2,2,3,3,1,0,1,1,1,1,1,1,1,1,1,1,1,1,0,1,1,1],
 *     [1,1,1,1,1,1,1,0,1,3,3,3,3,1,1,0,1,1,1,0,0,0,0,0,1,1,1,1,0,1,1,1],
 *     [1,1,1,0,0,0,0,0,1,1,1,1,1,1,1,0,1,1,1,0,1,1,1,0,1,1,1,1,0,1,1,1],
 *     [1,1,1,0,1,1,1,1,1,1,1,1,1,1,1,0,1,1,1,0,1,1,1,0,1,1,1,1,0,1,1,1],
 *     [1,1,1,0,1,1,1,1,1,1,1,1,1,1,1,0,1,1,1,0,1,1,1,0,1,1,1,1,0,1,1,1],
 *     [1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,0,1,1,1,0,1,1,1,1,0,1,1,1],
 *     [1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,1,1,1,0,1,1,1,1,0,1,1,1],
 *     [1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,1,1,1,0,1,1,1,1,0,1,1,1],
 *     [1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,1,1,1,0,0,0,0,0,0,1,1,1],
 *     [1,1,1,1,1,1,1,1,1,1,1,1,1,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1],
 *     [1,1,1,1,1,1,1,1,1,1,1,1,1,7,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1]
 *   ],
 *   // Route (list of vertexes) for enemies.
 *   "route":[
 *     [0, 0],
 *     [0, 96],
 *     [224, 96],
 *     [224, 224],
 *     [96,224],
 *     [96,320],
 *     [480,320],
 *     [480,64],
 *     [896,64],
 *     [896,416],
 *     [736,416],
 *     [736,192],
 *     [608,192],
 *     [608,416],
 *     [416,416],
 *     [416,480]
 *   ]
 * }
 *
 * }</pre>
 */
public class Map extends Sprite
{
    Tile[][] tiles;
    public Route route;
    public EntityMenager menager;
    public WaveScript script;

    /**
     * Creates new map with given preset
     * @param path path to json file with map data
     */
    public Map(String path)
    {
        this.tiles = new Tile[16][32];
        this.route = new Route();
        this.menager = new EntityMenager();
        this.script = new WaveScript();

        menager.useAsDefault();

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
        this.route.set_on_arrival_callback(enemy -> System.out.println("Enemy hitted target"));
    }

    /**
     * @param x position on map
     * @param y position on map
     * @return Tile of index (x, y)
     */
    public Tile get_tile_by_cords(int x, int y)
    {
        return tiles[x][y];
    }
    
    public void update()
    {
        route.update_entities(menager.getRef());
        this.update_turrents();
        script.update();
        menager.update();
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

        menager.draw(batch);
    }

    public float distance(Entity enemy, Building currentTurret){
        float currentRange = 0.0F;
        currentRange = (float) Point2D.distance(enemy.getEntityPos().x,enemy.getEntityPos().y, currentTurret.getEntityPos().x, currentTurret.getEntityPos().y);
        return currentRange;
    }

    void update_turrents()
    {
        var enemies = Arrays.stream(menager.getOwned()).filter(x -> x instanceof Enemy).toList();

        for (int j = 0; j < 32; j++) {
            for (int i = 0; i < 16; i++)
            {
                var tile = tiles[i][j];
                if(tile.get_building()!=null){
                    Building currentTurret = tile.get_building();
                    var currentEnemies = enemies.stream().filter(enemy -> distance(enemy,currentTurret) < currentTurret.getRange()).toArray(Entity[]::new);
                    currentTurret.update_enemies(currentEnemies);
                }
            }
        }
    }

}
