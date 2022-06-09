package com.mygdx.game.Towers;

public class BuildingGenerator
{
    public enum BuildingType
    {
        STONE_TOWER,
        QSTONE_TOWER,
        NONE,
    }

    public static Building get_stone_tower()
    {
        return new StoneTower(100, 0.2f);
    }
    public static Building get_Qstone_tower()
    {
        return new QStoneTower(80, 0.4f);
    }


    public static Building get_building(BuildingType type)
    {
        return switch (type)
        {
            case NONE -> null;
            case QSTONE_TOWER -> get_Qstone_tower();
            case STONE_TOWER -> get_stone_tower();
        };
    }
}
