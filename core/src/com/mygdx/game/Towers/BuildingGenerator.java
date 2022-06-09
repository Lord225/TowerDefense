package com.mygdx.game.Towers;

public class BuildingGenerator
{
    public enum BuildingType
    {
        STONE_TOWER,
        NONE,
    }

    public static Building get_stone_tower()
    {
        return new StoneTower(100, 1f);
    }



    public static Building get_building(BuildingType type)
    {
        return switch (type)
        {
            case NONE -> null;
            case STONE_TOWER -> get_stone_tower();
        };
    }
}
