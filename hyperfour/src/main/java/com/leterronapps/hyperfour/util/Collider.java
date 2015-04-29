package com.leterronapps.hyperfour.util;

/**
 * The base class for SceneObject collision components.
 */
public abstract class Collider {

    /** The position of the collision component. */
    public Vector3D position;

    /** The rotation of the collision component. */
    public Vector3D rotation;

    /** The scale of the collision component. */
    public Vector3D scale;

    private boolean active;

    /**
     * Constructs a Collider with an initial position.
     * @param position The inital position of the collision component.
     */
    public Collider(Vector3D position) {
        this.position = position;
        this.rotation = new Vector3D();
        this.scale = new Vector3D(1.0f, 1.0f, 1.0f);
        active = true;
    }

    /**
     * Set the active status of the collider.
     * @param active The new active status.
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * Returns the active status of the Collider.
     * @return true if the Collider is active, else returns false.
     */
    public boolean isActive() {
        return active;
    }
}
