package com.leterronapps.hyperfour.util;

/**
 * Created by williamlea on 18/02/15.
 */
public class Circle extends Collider {

    private float radius;

    public Circle(Vector3D position, float radius) {
        super(position);
        this.radius = radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    public float getRadius() {
        return radius;
    }
}
