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
import com.mygdx.game.Towers.BuildingGenerator;
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
	Sound shootArrowS, arrowHitS, deathS ;
	Enemy ghost;
	BitmapFont font;
	Vector2 blockPosition;
	Building stoneTower;

	float time = 0;


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
		mainTheme = Resources.getInstance().main_theme;
		shootArrowS = Resources.getInstance().shoot_arrow;
		arrowHitS = Resources.getInstance().arrow_hit_sound;
		deathS = Resources.getInstance().death_sound;
		//mainTheme.setLooping(true);
		//mainTheme.play();
		camera = new OrthographicCamera();
		map = new Map("map_layout.json");
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
	public void render()
	{
		time += Gdx.graphics.getDeltaTime();

		ScreenUtils.clear(0.2f, 0.3f, 0.4f, 1);

		camera.update();
		batch.setProjectionMatrix(camera.combined);

		handle_input();

		System.out.println(time);
		if(time > 3)
		{
			map.spawnEnemy();
			time = 0;
		}

		map.update();

		batch.begin();

		map.draw(batch);

		batch.end();
	}

	void place_building()
	{
		var blockPosition = get_pointing_block();

		if(blockPosition.x < 0 || blockPosition.y < 0)
			return;

		var tile = map.get_tile_by_cords((int)blockPosition.y, (int)blockPosition.x);

		var building = BuildingGenerator.get_stone_tower();

		tile.place(building);
	}

	void handle_input()
	{
		if(Gdx.input.isTouched())
		{
			place_building();
		}
	}

	@Override
	public void dispose () {
		font.dispose();
		mainTheme.dispose();
		shootArrowS.dispose();
	}
}
