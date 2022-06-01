package Miscellaneous;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Resources
{
    public Sprite ghost_sprite = new Sprite(new Texture("textures/enemy/ghost.png"));
    public Sprite arrow_sprite = new Sprite(new Texture("textures/entity/arrow.png"));
    public Sprite tower_sprite = new Sprite(new Texture("textures/entity/tower.png"));


    private static Resources _instance;

    public static Resources getInstance()
    {
        if(_instance == null)
            _instance = new Resources();
        return _instance;
    }
}
