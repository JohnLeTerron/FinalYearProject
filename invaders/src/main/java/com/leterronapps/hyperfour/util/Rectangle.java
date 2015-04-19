package com.leterronapps.hyperfour.util;

/**
 * Created by williamlea on 24/02/15.
 */
public class Rectangle extends Collider {

    private float width;
    private float height;

    public Rectangle(Vector3D position, float width, float height) {
        super(position);
        this.width = width;
        this.height = height;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getWidth() {
        return width;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getHeight() {
        return height;
    }
}
