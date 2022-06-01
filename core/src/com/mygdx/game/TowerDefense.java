package com.mygdx.game;

import Miscellaneous.Resources;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.loaders.BitmapFontLoader;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Enemy.Enemy;
import com.mygdx.game.Enemy.Ghost;
import com.mygdx.game.Map.Map;
import com.mygdx.game.Towers.Building;
import com.mygdx.game.Towers.StoneTower;

import java.util.Vector;

import static com.badlogic.gdx.math.MathUtils.floor;

public class TowerDefense extends ApplicationAdapter
{
	OrthographicCamera camera;
	Viewport gamePort;
	SpriteBatch batch;
	Map map;
	Music mainTheme;
	Sound shootArrowS;
	Enemy ghost;
	BitmapFont font;
	Vector2 blockPosition;
	Building stoneTower;

	public Vector2 get_pointing_block(){
		Vector3 vMouse = new Vector3();
		float blokX;
		float blokY;
		vMouse.set(Gdx.input.getX(), Gdx.input.getY(),0);
		camera.unproject(vMouse);
		blokX=floor(vMouse.x/32);
		blokY=floor((vMouse.y/32));
		if(!(blokX>31||blokX<0) && !(blokY>15||blokY<0)){
			return new Vector2(blokX,blokY);
		}
		return new Vector2(-1,-1);
	}
	@Override
	public void create () {
		mainTheme = Gdx.audio.newMusic(Gdx.files.internal("music/day.mp3"));
		shootArrowS = Gdx.audio.newSound(Gdx.files.internal("sound_effects/shot.mp3"));
		//mainTheme.setLooping(true);
		//mainTheme.play();
		blockPosition=new Vector2();
		camera = new OrthographicCamera();
		map = new Map();
		batch = new SpriteBatch();
		ghost = new Ghost(100,100);
		stoneTower = new StoneTower(100,100);
		font = new BitmapFont();

		gamePort = new ExtendViewport(32*32 , 32*16, camera);
		camera.setToOrtho(false, 32*32, 32*16);
	}

	@Override
	public void resize(int width, int height)
	{
		gamePort.update(width, height);
	}
	
	@Override
	public void render ()
	{
		ScreenUtils.clear(0.2f, 0.3f, 0.4f, 1);


		camera.update();
		batch.setProjectionMatrix(camera.combined);

		batch.begin();

		map.draw(batch);

		//test_draw_enemies(batch);

		if(Gdx.input.isTouched()){
			blockPosition=get_pointing_block();
			font.draw(batch,blockPosition.toString(),200,200);
		}
		test_draw_towers(batch,blockPosition);
		batch.end();
	}

	void test_draw_towers(Batch batch,Vector2 placePosition)
	{
		if(placePosition.x!=-1 || placePosition.y!=-1)
			stoneTower.draw(batch,placePosition);
	}

	void test_draw_enemies(Batch batch)
	{
		//ghost.draw(batch);
	}
	
	@Override
	public void dispose () {
		font.dispose();
		mainTheme.dispose();
		shootArrowS.dispose();
	}
}
