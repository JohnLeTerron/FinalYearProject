package com.leterronapps.hyperfour.graphics;

import com.leterronapps.hyperfour.game.SceneObject;

import java.util.Vector;

/**
 * Created by williamlea on 17/04/15.
 */
public class HUD {

    private int width;
    private int height;

    private Vector<SceneObject> hudObjects;

    public HUD(int width, int height) {
        this.width = width;
        this.height = height;
        hudObjects = new Vector<>();
    }

    public void update(float deltaTime) {
        if(!hudObjects.isEmpty()) {
            for(SceneObject object : hudObjects) {
                object.update(deltaTime);
            }
        }
    }

    public void render(HFShader shader) {
        if(!hudObjects.isEmpty()) {
            for(SceneObject object : hudObjects) {
                object.render(shader);
            }
        }
    }

    public void addItem(SceneObject object) {
        hudObjects.add(object);
    }
}
