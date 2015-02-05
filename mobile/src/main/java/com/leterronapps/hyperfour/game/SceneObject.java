package com.leterronapps.hyperfour.game;

/**
 * Created by williamlea on 05/02/15.
 */
public abstract class SceneObject {

    public SceneObject() {

    }

    public abstract void update(float deltaTime);

    public abstract void render();
}
