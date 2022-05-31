package Miscellaneous;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Resources {
    public static Sprite ghost_texture= new Sprite(new Texture(Gdx.files.internal("textures/enemy/ghost.png")));
    public static Sprite arrow_texture= new Sprite(new Texture(Gdx.files.internal("textures/entity/arrow.png")));
    public static Sprite dirt_texture= new Sprite(new Texture(Gdx.files.internal("textures/tiles/dirt.png")));
    public static Sprite dirt_green_texture= new Sprite(new Texture(Gdx.files.internal("textures/tiles/dirt_green.png")));


}
