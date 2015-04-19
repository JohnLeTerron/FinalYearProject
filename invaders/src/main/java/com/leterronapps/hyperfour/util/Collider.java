package com.leterronapps.hyperfour.util;

/**
 * Created by williamlea on 18/02/15.
 */
public abstract class Collider {

    public Vector3D position;
    public Vector3D rotation;
    public Vector3D scale;

    private boolean active;

    public Collider(Vector3D position) {
        this.position = position;
        this.rotation = new Vector3D();
        this.scale = new Vector3D(1.0f, 1.0f, 1.0f);
        active = true;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isActive() {
        return active;
    }
}
