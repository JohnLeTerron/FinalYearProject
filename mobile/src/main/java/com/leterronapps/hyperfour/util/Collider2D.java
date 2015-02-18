package com.leterronapps.hyperfour.util;

/**
 * Created by williamlea on 18/02/15.
 */
public abstract class Collider2D {

    public Vector2D position;
    public Vector2D rotation;
    public Vector2D scale;

    public Collider2D(Vector2D position, Vector2D rotation, Vector2D scale) {
        this.position = position;
        this.rotation = rotation;
        this.scale = scale;
    }
}
