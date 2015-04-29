package com.leterronapps.hyperfour.util;

/**
 * A bounding rectangle collision component for SceneObjects.
 * This class can also be used for cuboid collision.
 */
public class Rectangle extends Collider {

    private float width;
    private float height;

    /**
     * Constructs a Rectangle collider with an initial position, width and height.
     * @param position The initial position of the Rectangle collider.
     * @param width The initial width of the Rectangle collider.
     * @param height The initial height of the Rectangle collider.
     */
    public Rectangle(Vector3D position, float width, float height) {
        super(position);
        this.width = width;
        this.height = height;
    }

    /**
     * Sets a new width for the Rectangle.
     * @param width The new width for the Rectangle.
     */
    public void setWidth(float width) {
        this.width = width;
    }

    /**
     *
     * @return The current width of the Rectangle.
     */
    public float getWidth() {
        return width;
    }

    /**
     * Sets a new height for the Rectangle.
     * @param height The new height of the Rectangle.
     */
    public void setHeight(float height) {
        this.height = height;
    }

    /**
     *
     * @return The current height of the Rectangle.
     */
    public float getHeight() {
        return height;
    }
}
