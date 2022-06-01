package com.mygdx.game.Towers;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Enemy.Enemy;

import java.util.Vector;

public abstract class Building extends Sprite
{
    float cost;
    Sprite skin;
    float range;
    float shootingCooldown;
    Vector2 position = new Vector2();
    ShapeRenderer shapeRenderer = new ShapeRenderer();

    public abstract void update_enemies(Vector<Enemy> enemies_in_range); // przeciwnicy w zasiegu (sprawdzenie)
    public abstract float getRange(); // range wiezyczki
    public abstract void setRange(float range);
    public abstract float getCooldown();
    public abstract void setCooldown(float cooldown);


    public void draw(Batch batch,  Vector2 positionToPlace) {
        batch.end();//Trzeba wyłączyć by Shaperendered działał
        shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.RED);
        shapeRenderer.circle(positionToPlace.x,positionToPlace.y,100);
        shapeRenderer.end();
        batch.begin();//Włączamy spowrotem

        skin.setPosition(positionToPlace.x, positionToPlace.y);
        skin.draw(batch);
    }
}
