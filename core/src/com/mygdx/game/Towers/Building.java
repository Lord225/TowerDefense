package com.mygdx.game.Towers;

import Miscellaneous.Resources;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Enemy.Enemy;
import com.mygdx.game.Enemy.Entity;

import java.awt.geom.Point2D;
import java.util.Vector;

public abstract class Building extends Entity
{
    float cost;
    //Sprite skin;
    float range;
    float shootingCooldown;
    //Vector2 position = new Vector2();
    ShapeRenderer shapeRenderer = new ShapeRenderer();
    Sprite skin = Resources.getInstance().tower_sprite;

    public abstract void update_enemies(Entity[] enemies_in_range); // przeciwnicy w zasiegu (sprawdzenie)
    public abstract float getRange(); // range wiezyczki
    public abstract void setRange(float range);
    public abstract float getCooldown();
    public abstract void setCooldown(float cooldown);



    @Override
    public void draw(Batch batch) {
        skin.setPosition(position.x, position.y);
        skin.draw(batch);
    }
    public Enemy findClosestEnemy(Vector<Enemy> enemies_in_range) {
        float closestRange = Float.MAX_VALUE;
        float currentRange = 0.0F;
        Enemy closestEnemy = null;

        for(Enemy enemy : enemies_in_range){
            currentRange = (float) Point2D.distance(enemy.getX(), enemy.getY(), this.skin.getX(), this.skin.getY());
            if(currentRange<=closestRange){
                closestRange = currentRange;
                closestEnemy = enemy;
            }
        }
        return closestEnemy;
    }
}
