package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
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
	}


	@Override
	public void resize(int width, int height)
	{
		camera.setToOrtho(false,  width, height);
	}
	
	@Override
	public void render ()
	{
		ScreenUtils.clear(1, 0, 0, 1);


		camera.update();
		batch.setProjectionMatrix(camera.projection);

		batch.begin();

		map.draw(batch);

		batch.end();
	}
	
	@Override
	public void dispose () {

	}
}
