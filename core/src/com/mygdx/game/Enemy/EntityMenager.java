package com.mygdx.game.Enemy;

import com.badlogic.gdx.graphics.g2d.Batch;

import java.io.Serializable;
import java.util.Vector;

public class EntityMenager implements Serializable
{
    public static boolean debug_mode = false;

    private static EntityMenager default_menager = null;

    public static void registerEntity(Entity entity)
    {
        default_menager.addEntity(entity);
    }

    public void addEntity(Entity entity)
    {
        if(entity != null && !this.entites.contains(entity)) {
            this.entites.add(entity);

            if(debug_mode)
                System.out.println("Adding entity" + entity.toString());
        }
    }

    private final Vector<Entity> entites = new Vector<>();

    public void useAsDefault()
    {
        EntityMenager.default_menager = this;
    }

    public void update()
    {
        for(var entity : entites)
        {
            entity.update();
        }

        entites.removeIf(x -> !x.isAlive());
    }

    public void draw(Batch batch)
    {
        for(var entity : entites)
        {
            entity.draw(batch);
        }
    }

    public Entity[] getOwned()
    {
        return entites.toArray(Entity[]::new);
    }

    public Vector<Entity> getRef()
    {
        return entites;
    }

}
