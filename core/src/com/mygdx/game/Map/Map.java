package com.mygdx.game.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.mygdx.game.Enemy.Enemy;
import com.mygdx.game.Enemy.Entity;
import com.mygdx.game.Enemy.EntityMenager;
import com.mygdx.game.Enemy.Ghost;
import com.mygdx.game.Towers.Building;

import java.awt.geom.Point2D;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Vector;
import java.util.stream.Collectors;

public class Map extends Sprite
{
    Tile[][] tiles;
    public Route route;
    public EntityMenager menager;
    public WaveScript script;

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
