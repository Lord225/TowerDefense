package com.mygdx.game;

import Miscellaneous.BestScore;
import Miscellaneous.Resources;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
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
	SpriteBatch batch;
	Map map;
	Music mainTheme;
	Sound shootArrowS, arrowHitS, deathS ;
	Enemy ghost;
	BitmapFont font;
	Building stoneTower;
	BestScore bestScore;
	ObjectOutputStream save;
	ObjectInputStream readSave;

	float time = 0;

	UiTemplate button;
	UiTemplate label;

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

		label = new Text(new ExtendViewport(32*32,32*16),new Vector2(128,32*21),"Tekst");
		button = new Button(new ExtendViewport(32*32,32*16),
				Resources.getInstance().tower_texture,
				Resources.getInstance().myTextureRegion,
				Resources.getInstance().myTextureDrawable,
				new Vector2(64,32*21)
		);
		/* ZAPIS DO OBIEKTU
		try{
			save = new ObjectOutputStream(new FileOutputStream(Resources.getInstance().bestScoreFile));
			//tutaj dorobic stworzenie obiektu (stworzyc obiekt bestScore i dodac do konstruktora wymagane rzeczy)
			save.writeObject(bestScore);
		}catch(java.io.FileNotFoundException e){
			System.out.println("FileOutputStream error "+ e);
		}catch(java.io.IOException e) {
			System.out.println("ObjectOutputStream error" + e);
		}finally{
			try {
				if (save != null) save.close();
			}catch(java.io.IOException e){
			}
		}
		*/
		/* ODCZYT OBIEKTU
		try {
			readSave = new ObjectInputStream(new FileInputStream(Resources.getInstance().bestScoreFile));
			bestScore = (BestScore) readSave.readObject();
		}catch(java.io.FileNotFoundException e){
			System.out.println("FileNotFoundError"+ e);
		}catch(java.io.IOException e){
			System.out.println("ObjectInputStream error"+ e);
		}catch(java.lang.ClassNotFoundException e){
			System.out.println("ClassNotFoundException"+ e);
		}finally{
			try {
				if(readSave != null) readSave.close();
			}catch(java.io.IOException e){

			}
		}
		 */

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
		//System.out.println(button.addListener());
		label.draw();
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

	void draw_ui(){

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
		arrowHitS.dispose();
		deathS.dispose();
	}
}
