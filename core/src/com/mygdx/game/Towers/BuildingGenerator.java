package com.mygdx.game.Towers;

public class BuildingGenerator
{
    public static Building get_stone_tower()
    {
        return new StoneTower(100, 0.2f);
    }
}
