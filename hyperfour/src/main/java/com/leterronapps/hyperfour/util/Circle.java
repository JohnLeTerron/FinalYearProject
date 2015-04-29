package com.leterronapps.hyperfour.util;

/**
 * A bounding circle collision component for SceneObjects.
 * This class can also be used for spherical collision.
 */
public class Circle extends Collider {

    private float radius;

    /**
     * Constructs a Circle collider with an initial position and radius.
     * @param position The initial position of the Circle collider
     * @param radius The initial radius of the Circle collider.
     */
    public Circle(Vector3D position, float radius) {
        super(position);
        this.radius = radius;
    }

    /**
     * Sets a new radius for the Circle.
     * @param radius The new radius of the Circle.
     */
    public void setRadius(float radius) {
        this.radius = radius;
    }

    /**
     *
     * @return The current radius of the Circle.
     */
    public float getRadius() {
        return radius;
    }
}
