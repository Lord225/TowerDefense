package com.mygdx.game;

import Miscellaneous.Resources;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.Enemy.Enemy;
import com.mygdx.game.Enemy.Ghost;
import com.mygdx.game.Map.Map;

import java.util.Vector;

public class TowerDefense extends ApplicationAdapter
{
	OrthographicCamera camera;
	SpriteBatch batch;
	Map map;
	Enemy ghost;


	@Override
	public void create () {
		camera = new OrthographicCamera();
		map = new Map();
		batch = new SpriteBatch();
		ghost = new Ghost(100,100);
	}


	@Override
	public void resize(int width, int height)
	{
		camera.setToOrtho(false,  width, height);
	}
	
	@Override
	public void render ()
	{
		ScreenUtils.clear(0.2f, 0.3f, 0.4f, 1);


		camera.update();
		batch.setProjectionMatrix(camera.projection);

		batch.begin();

		map.draw(batch);

		test_draw_towers();

		//test_draw_enemies(batch);

		batch.end();
	}

	void test_draw_towers()
	{

	}

	void test_draw_enemies(Batch batch)
	{
		//ghost.draw(batch);
	}
	
	@Override
	public void dispose () {

	}
}
