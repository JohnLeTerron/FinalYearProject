package com.leterronapps.hyperfour.util;

/**
 * Created by williamlea on 05/02/15.
 */
public class Vector2D {

    public float x, y;

    public Vector2D() {
        x = 0.0f;
        y = 0.0f;
    }

    public Vector2D(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Vector2D add(Vector2D other) {
        this.x += other.x;
        this.y += other.y;
        return this;
    }

    public Vector2D subtract(Vector2D other) {
        this.x -= other.x;
        this.y -= other.y;
        return this;
    }

    public Vector2D multiply(float scalar) {
        this.x *= scalar;
        this.y *= scalar;
        return this;
    }

    public Vector2D divide(float scalar) {
        this.x /= scalar;
        this.y /= scalar;
        return this;
    }

    public float dot(Vector2D other) {
        return (this.x * other.x) + (this.y * other.y);
    }

    public float magnitude() {
        return (float) Math.sqrt(dot(this));
    }


}
