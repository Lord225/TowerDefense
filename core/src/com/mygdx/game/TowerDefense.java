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

public class TowerDefense extends ApplicationAdapter
{
	OrthographicCamera camera;
	Rectangle test_rect;
	PolygonSpriteBatch batch;
	ShapeRenderer shapeRenderer;


	@Override
	public void create () {
		camera = new OrthographicCamera();

		test_rect =  new Rectangle().set(10, 10, 200, 200);

		batch = new PolygonSpriteBatch();

		shapeRenderer = new ShapeRenderer();

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

		shapeRenderer.setProjectionMatrix(camera.projection);
		shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
		shapeRenderer.setColor(Color.BLACK);
		shapeRenderer.rect(0, 0, 50, 50);
		shapeRenderer.end();
	}
	
	@Override
	public void dispose () {

	}
}
