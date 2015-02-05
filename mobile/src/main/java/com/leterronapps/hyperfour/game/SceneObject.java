package com.leterronapps.hyperfour.game;

import com.leterronapps.hyperfour.graphics.Vertices;
import com.leterronapps.hyperfour.util.Vector3D;

/**
 * Created by williamlea on 05/02/15.
 */
public abstract class SceneObject {

    public Vector3D position;
    public Vector3D rotation;
    public Vector3D scale;

    protected Vertices vertices;

    public SceneObject() {
        position = new Vector3D();
        rotation = new Vector3D();
        scale = new Vector3D(1.0f, 1.0f, 1.0f);
    }

    public SceneObject(Vector3D position) {
        this.position = position;
        rotation = new Vector3D();
        scale = new Vector3D(1.0f, 1.0f, 1.0f);
    }

    public abstract void update(float deltaTime);

    public abstract void render();
}
