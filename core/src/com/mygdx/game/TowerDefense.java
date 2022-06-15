package com.mygdx.game;

import Miscellaneous.BestScore;
import Miscellaneous.Resources;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Map.Map;


import static com.badlogic.gdx.math.MathUtils.floor;

/**
 * @author Maciej Złotorowicz, Adrian Rosół, Oskar Ożóg
 * This is the main class which combines all others so it can be run in programme
 */

public class TowerDefense extends ApplicationAdapter
{
	OrthographicCamera camera;
	Viewport gamePort;
	Viewport uiPort;
	SpriteBatch batch;
	Map map;
	Music mainTheme;
	BestScore bestScore;
	UITowerDef uiTowerDef;

	public static PlayerState playerState;

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
		mainTheme.setLooping(true);
		mainTheme.setVolume(0.075F);

		camera = new OrthographicCamera();
		map = new Map("map_layout.json");
		playerState = new PlayerState(map);

		batch = new SpriteBatch();

		gamePort = new ExtendViewport(32*32 , 32*16, camera);
		uiPort= new ExtendViewport(32*32 , 32*16);
		camera.setToOrtho(false, 32*32, 32*16);

		bestScore = new BestScore();
		bestScore.loadBest();

		uiTowerDef = new UITowerDef();

		uiTowerDef.create(uiPort, bestScore, playerState);

	}

	@Override
	public void resize(int width, int height)
	{
		gamePort.update(width, height);
		uiPort.update(width,height);
	}
	
	@Override
	public void render()
	{
		mainTheme.play();
		time += Gdx.graphics.getDeltaTime();
		ScreenUtils.clear(0.2f, 0.3f, 0.4f, 1);

		uiTowerDef.update();
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		handle_input();

		map.update();

		batch.begin();

		map.draw(batch);

		batch.end();

		uiTowerDef.render();

		if(playerState.isDead){
			Gdx.app.exit();
		}
	}


	void draw_ui(){

	}
	void handle_input()
	{
		if(Gdx.input.isTouched())
		{
			playerState.onPlaceBuilding(get_pointing_block());
		}
	}

	@Override
	public void dispose () {
		mainTheme.dispose();
		if(bestScore.getPlayerState()==null){
			bestScore.saveBest(playerState);
		}else {
			if (bestScore.getPlayerState().getEnemiesDefeated() < playerState.getEnemiesDefeated()) {
				bestScore.saveBest(playerState);
			}
		}
	}
}
