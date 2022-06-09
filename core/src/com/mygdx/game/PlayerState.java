package com.mygdx.game;

import Miscellaneous.Resources;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.Map.Map;
import com.mygdx.game.Towers.BuildingGenerator;

import static com.badlogic.gdx.math.MathUtils.floor;

public class PlayerState
{
    public int gold = 100;
    public Map map;
    public int enemiesDefeated=0;

    public BuildingGenerator.BuildingType buildingTypeInHand = BuildingGenerator.BuildingType.NONE;

    public PlayerState(Map map)
    {
        this.map = map;
    }
    //after click event
    public void setTower(BuildingGenerator.BuildingType type)
    {
        this.buildingTypeInHand = type;
    }


    public void onPlaceBuilding(Vector2 blockPosition)
    {
        if(blockPosition.x < 0 || blockPosition.y < 0)
            return;

        var tile = map.get_tile_by_cords((int)blockPosition.y, (int)blockPosition.x);

        var building = BuildingGenerator.get_building(this.buildingTypeInHand);

        if(building == null)
            return;
        if(this.gold-building.getCost()<0){
            building.is_alive=false;
            return;
        }
        if(tile.place(building)){
            this.gold -= building.getCost();
        }
        else
        {
            building.is_alive = false;
        }
    }
    //update golda
    public String getGoldMessage()
    {
        return String.format("Posiadasz %d gold'a", this.gold);
    }

    public int getGold() {
        return gold;
    }
    public void addGold(int g){
        this.gold+=g;
    }

    private static PlayerState _instance;

    public static PlayerState get_instance() {
        return _instance;
    }
}