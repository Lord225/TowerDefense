package Miscellaneous;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Resources {
    public static Sprite ghost_sprite= new Sprite(new Texture(Gdx.files.internal("textures/enemy/ghost.png")),32,32);
    public static Sprite arrow_sprite= new Sprite(new Texture(Gdx.files.internal("textures/entity/arrow.png")),32,32);
    public static Sprite dirt_sprite= new Sprite(new Texture(Gdx.files.internal("textures/tiles/dirt.png")),32,32);
    public static Sprite dirt_green_sprite= new Sprite(new Texture(Gdx.files.internal("textures/tiles/dirt_green.png")),32,32);
    public static Sprite tower_sprite = new Sprite(new Texture(Gdx.files.internal("textures/tiles/tower.png")),32,32);
}
