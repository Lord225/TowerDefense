package com.mygdx.game;

import Miscellaneous.BestScore;
import Miscellaneous.Resources;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Map.Map;
import com.mygdx.game.Towers.BuildingGenerator;
import com.mygdx.game.Ui.Button;
import com.mygdx.game.Ui.HealthBar;
import com.mygdx.game.Ui.Text;

import java.util.concurrent.TimeUnit;

/**
 * Class used for declaring our game's UI
 */
public class UITowerDef
{
    Stage stage;

    Button buttonStone;
    Button buttonQStone;
    Button buttonFireball;

    Text labelCostOfTower;

    Text labelScore;
    Text labelMoney;
    Text labelBestScore;

    PlayerState playerState;

    HealthBar healthBar;

    public void create(Viewport uiPort, BestScore bestScore, PlayerState playerState, Map map)
    {
        this.playerState = playerState;
        stage = new Stage(uiPort);

        healthBar=new HealthBar(100,20,stage,new Vector2(32*26,32*20));
        map.route.set_on_arrival_callback(enemy -> healthBar.setValue(healthBar.getValue()-0.1F));
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
                //System.out.println("Clicked " + event.toString());

                last = event.getType();
            }
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor)
            {
                //System.out.println("Enter " + event.toString());
                labelCostOfTower.setText("Koszt:80 Gold");
                labelCostOfTower.setPos(new Vector2(32*2,32*19));
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
                labelCostOfTower.setText("Koszt:180 Gold");
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

    public void update()
    {
        labelScore.setText(String.format("Aktualny wynik: %d", playerState.enemiesDefeated));
        labelMoney.setText(playerState.getGoldMessage());
    }

    public void render()
    {
        buttonStone.draw();

        buttonQStone.draw();

        buttonFireball.draw();

        healthBar.draw();

        labelMoney.draw();

        labelBestScore.draw();
    }
}
