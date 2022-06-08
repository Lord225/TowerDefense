package Miscellaneous;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import java.io.File;

public class Resources
{
    public Sprite ghost_sprite = new Sprite(new Texture("textures/enemy/ghost.png"));
    public Sprite arrow_sprite = new Sprite(new Texture("textures/entity/arrow.png"));
    public Sprite tower_sprite = new Sprite(new Texture("textures/entity/tower.png"));
    public Music main_theme = Gdx.audio.newMusic(Gdx.files.internal("music/day.mp3"));
    public Sound shoot_arrow = Gdx.audio.newSound(Gdx.files.internal("sound_effects/shoot.mp3"));
    public Sound arrow_hit_sound= Gdx.audio.newSound(Gdx.files.internal("sound_effects/hitsound.mp3"));
    public Sound death_sound= Gdx.audio.newSound(Gdx.files.internal("sound_effects/deathsound.mp3"));
    public File bestScoreFile = new File("bestScore.dat");
    private static Resources _instance;

    public static Resources getInstance()
    {
        if(_instance == null)
            _instance = new Resources();
        return _instance;
    }
}
