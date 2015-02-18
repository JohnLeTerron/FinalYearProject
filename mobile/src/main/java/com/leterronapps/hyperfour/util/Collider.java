package com.leterronapps.hyperfour.util;

/**
 * Created by williamlea on 18/02/15.
 */
public abstract class Collider {

    public Vector3D position;
    public Vector3D rotation;
    public Vector3D scale;

    public Collider(Vector3D position, Vector3D rotation, Vector3D scale) {
        this.position = position;
        this.rotation = rotation;
        this.scale = scale;
    }
}
