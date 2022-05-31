package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.Map.Map;

public class TowerDefense extends ApplicationAdapter
{
	OrthographicCamera camera;
	SpriteBatch batch;
	Map map;

	@Override
	public void create () {
		camera = new OrthographicCamera();
		map = new Map();
		batch = new SpriteBatch();
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

		test_draw_enemies();

		batch.end();
	}

	void test_draw_towers()
	{

	}

	void test_draw_enemies()
	{

	}
	
	@Override
	public void dispose () {

	}
}
