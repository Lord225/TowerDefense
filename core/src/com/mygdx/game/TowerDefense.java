package com.mygdx.game;

import Miscellaneous.BestScore;
import Miscellaneous.Resources;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Enemy.Enemy;
import com.mygdx.game.Enemy.Ghost;
import com.mygdx.game.Map.Map;
import com.mygdx.game.Towers.Building;
import com.mygdx.game.Towers.BuildingGenerator;
import com.mygdx.game.Towers.StoneTower;
import com.mygdx.game.Ui.Button;
import com.mygdx.game.Ui.Text;
import com.mygdx.game.Ui.Button;
import com.mygdx.game.Ui.Text;
import com.mygdx.game.Ui.UiTemplate;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import static com.badlogic.gdx.math.MathUtils.floor;

public class TowerDefense extends ApplicationAdapter
{
	OrthographicCamera camera;
	Viewport gamePort;
	Viewport uiPort;
	SpriteBatch batch;
	Map map;
	Music mainTheme;
	Sound shootArrowS, arrowHitS, deathS ;
	BestScore bestScore;

	public static PlayerState playerState;


	float time = 0;

	Button button;
	Text labelMoney;

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

		playerState=new PlayerState(map);

		gamePort = new ExtendViewport(32*32 , 32*16, camera);
		uiPort= new ExtendViewport(32*32 , 32*16);
		camera.setToOrtho(false, 32*32, 32*16);
		//bestScore = new BestScore(map,10.0f(points),100(gold),"Player2");

		labelMoney = new Text(uiPort,new Vector2(32*20,32*20),playerState.getGoldMessage(), Color.WHITE);
		button = new Button(uiPort,
				Resources.getInstance().tower_texture,
				Resources.getInstance().myTextureRegion,
				Resources.getInstance().myTextureDrawable,
				new Vector2(64,32*20)
		);

		bestScore = new BestScore();
		bestScore = bestScore.loadBest();
		if(bestScore == null){
			bestScore = new BestScore(map,playerState);
			bestScore.saveBest(bestScore);
		}


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
		time += Gdx.graphics.getDeltaTime();
		ScreenUtils.clear(0.2f, 0.3f, 0.4f, 1);

		camera.update();
		batch.setProjectionMatrix(camera.combined);
		handle_input();

		if(time > 3)
		{
			map.spawnEnemy();
			time = 0;
		}

		map.update();

		batch.begin();

		map.draw(batch);

		batch.end();

		button.draw();
		button.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y)
			{
				playerState.setTower(BuildingGenerator.BuildingType.STONE_TOWER);
				//System.out.println("Ustawiam buden na stonetower");
			}
		});
		labelMoney.setText(playerState.getGoldMessage());
		labelMoney.draw();
	}


	void draw_ui(){
		button.draw();
		labelMoney.draw();
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
		shootArrowS.dispose();
		arrowHitS.dispose();
		deathS.dispose();
		if(bestScore.getPlayerState().getEnemiesDefeated()<playerState.getEnemiesDefeated()){
			bestScore.setMap(map);
			bestScore.setPlayerState(playerState);
			bestScore.saveBest(bestScore);
		}
	}
}
