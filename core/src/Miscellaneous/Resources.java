package Miscellaneous;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import java.io.File;
/**
 * Singleton class which keeps references to objects responsible for skins/sound effects used in our game
 */

public class Resources
{
    public Sprite ghost_sprite = new Sprite(new Texture("textures/enemy/ghost.png"));
    public Sprite arrow_sprite = new Sprite(new Texture("textures/entity/arrow.png"));
    public Sprite tower_sprite = new Sprite(new Texture("textures/entity/tower.png"));
    public Sprite fireball_sprite = new Sprite(new Texture("textures/entity/fireball.png"));

    public Texture tower_texture = new Texture("textures/entity/tower.png");
    public TextureRegion myTextureRegion = new TextureRegion(tower_texture);
    public TextureRegionDrawable myTextureDrawable = new TextureRegionDrawable(myTextureRegion);

    public Texture towerQ_texture = new Texture("textures/entity/greentower.png");
    public TextureRegion myQTextureRegion = new TextureRegion(towerQ_texture);
    public TextureRegionDrawable myQTextureDrawable = new TextureRegionDrawable(myQTextureRegion);

    public Texture towerF_texture = new Texture("textures/entity/redtower.png");
    public TextureRegion myFTextureRegion = new TextureRegion(towerF_texture);
    public TextureRegionDrawable myFTextureDrawable = new TextureRegionDrawable(myFTextureRegion);

    public Music main_theme = Gdx.audio.newMusic(Gdx.files.internal("music/day.mp3"));
    public Sound shoot_arrow = Gdx.audio.newSound(Gdx.files.internal("sound_effects/shoot.mp3"));
    public Sound arrow_hit_sound= Gdx.audio.newSound(Gdx.files.internal("sound_effects/hitsound.mp3"));
    public Sound death_sound= Gdx.audio.newSound(Gdx.files.internal("sound_effects/deathsound.mp3"));
    public Sound castle_hit_sound= Gdx.audio.newSound(Gdx.files.internal("sound_effects/castle_hit.mp3"));
    public Sound fireball_sound= Gdx.audio.newSound(Gdx.files.internal("sound_effects/fireball.mp3"));

    public File bestScoreFile = new File("bestScore.dat");
    private static Resources _instance;

    public static Resources getInstance()
    {
        if(_instance == null)
            _instance = new Resources();
        return _instance;
    }
}
