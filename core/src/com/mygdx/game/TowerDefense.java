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
import com.badlogic.gdx.graphics.TextureData;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.*;
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

	public static PlayerState playerState;


	float time = 0;

	Stage stage;

	Button buttonStone;
	Button buttonQStone;
	Button buttonFireball;

	Text labelCostOfTower;

	Text labelScore;
	Text labelMoney;
	Text labelBestScore;

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
		//bestScore = new BestScore(map,10.0f(points),100(gold),"Player2");

		stage = new Stage(uiPort);

		labelMoney = new Text(new Vector2(32*20,32*20),playerState.getGoldMessage(), Color.WHITE,stage);

		buttonStone = new Button(
				Resources.getInstance().tower_texture,
				new TextureRegion(Resources.getInstance().myTextureRegion),
				new TextureRegionDrawable(Resources.getInstance().myTextureDrawable),
				new Vector2(32*2,32*20),
				stage
		);
		buttonQStone = new Button(
				Resources.getInstance().towerQ_texture,
				new TextureRegion(Resources.getInstance().myQTextureRegion),
				new TextureRegionDrawable(Resources.getInstance().myQTextureDrawable),
				new Vector2(32*4,32*20),
				stage
		);
		buttonFireball = new Button(
				Resources.getInstance().towerF_texture,
				new TextureRegion(Resources.getInstance().myFTextureRegion),
				new TextureRegionDrawable(Resources.getInstance().myFTextureDrawable),
				new Vector2(32*6,32*20),
				stage
		);

		labelScore = new Text(new Vector2(32*14,32*21),"Aktualny wynik: 0", Color.WHITE, stage);

		bestScore = new BestScore();
		bestScore.loadBest();

		if(bestScore.getPlayerState() == null){
			labelBestScore = new Text(new Vector2(32*14,32*20),"Najlepszy wynik: 0", Color.WHITE,stage);
		}else{
			labelBestScore = new Text(new Vector2(32*14,32*20),"Najlepszy wynik: " + bestScore.getPlayerState().getEnemiesDefeated(), Color.WHITE,stage);
		}

		labelCostOfTower = new Text(new Vector2(32*2,32*19),"NA", Color.WHITE,stage);
		labelCostOfTower.setVisibility(false);

		buttonStone.addListener(new ClickListener()
		{
			private InputEvent.Type last;

			@Override
			public void clicked(InputEvent event, float x, float y)
			{
				playerState.setTower(BuildingGenerator.BuildingType.STONE_TOWER);
				System.out.println("Clicked " + event.toString());

				last = event.getType();
			}
			@Override
			public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor)
			{
				System.out.println("Enter " + event.toString());
				labelCostOfTower.setText("Koszt:80 Gold");
				labelCostOfTower.setPos(new Vector2(32*2,32*19));
				labelCostOfTower.setVisibility(true);
				last = event.getType();
			}
			@Override
			public void exit(InputEvent event, float x, float y, int pointer, Actor toActor)
			{
				System.out.println("Exit " + event.getType().toString());
				labelCostOfTower.setVisibility(false);
				last = event.getType();
			}
		});

		buttonQStone.addListener(new ClickListener(){
			private InputEvent.Type last;

			@Override
			public void clicked(InputEvent event, float x, float y)
			{
				playerState.setTower(BuildingGenerator.BuildingType.QSTONE_TOWER);
				//System.out.println("Clicked " + event.toString());

				last = event.getType();
			}
			@Override
			public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor)
			{
				//System.out.println("Enter " + event.toString());
				labelCostOfTower.setText("Koszt:120 Gold");
				labelCostOfTower.setPos(new Vector2(32*4,32*19));
				labelCostOfTower.setVisibility(true);
				last = event.getType();
			}
			@Override
			public void exit(InputEvent event, float x, float y, int pointer, Actor toActor)
			{
				//System.out.println("Exit " + event.getType().toString());
				labelCostOfTower.setVisibility(false);
				last = event.getType();
			}
		});
		buttonFireball.addListener(new ClickListener(){
			private InputEvent.Type last;

			@Override
			public void clicked(InputEvent event, float x, float y)
			{
				playerState.setTower(BuildingGenerator.BuildingType.FIREBALL_TOWER);
				//System.out.println("Clicked " + event.toString());

				last = event.getType();
			}
			@Override
			public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor)
			{
				//System.out.println("Enter " + event.toString());
				labelCostOfTower.setText("Koszt:300 Gold");
				labelCostOfTower.setPos(new Vector2(32*6,32*19));
				labelCostOfTower.setVisibility(true);
				last = event.getType();
			}
			@Override
			public void exit(InputEvent event, float x, float y, int pointer, Actor toActor)
			{
				//System.out.println("Exit " + event.getType().toString());
				labelCostOfTower.setVisibility(false);
				last = event.getType();
			}
		});
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

		camera.update();
		batch.setProjectionMatrix(camera.combined);
		handle_input();

		map.update();

		batch.begin();

		map.draw(batch);

		batch.end();

		buttonStone.draw();

		buttonQStone.draw();

		buttonFireball.draw();


		labelMoney.setText(playerState.getGoldMessage());
		labelMoney.draw();

		labelScore.setText(String.format("Aktualny wynik: %d", playerState.enemiesDefeated));

		labelBestScore.draw();

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
