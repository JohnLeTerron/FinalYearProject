package com.leterronapps.hyperfour.util;

/**
 * Created by williamlea on 05/02/15.
 */
public class Vector3D {

    public float x, y, z;

    public Vector3D() {
        x = 0.0f;
        y = 0.0f;
        z = 0.0f;
    }

    public Vector3D(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector3D add(Vector3D other) {
        this.x += other.x;
        this.y += other.y;
        this.z += other.z;
        return this;
    }

    public Vector3D add(float x, float y, float z) {
        this.x += x;
        this.y += y;
        this.z += z;
        return this;
    }

    public Vector3D subtract(Vector3D other) {
        this.x -= other.x;
        this.y -= other.y;
        this.z -= other.z;
        return this;
    }

    public Vector3D subtract(float x, float y, float z) {
        this.x -= x;
        this.y -= y;
        this.z -= z;
        return this;
    }

    public Vector3D multiply(float scalar) {
        this.x *= scalar;
        this.y *= scalar;
        this.z *= scalar;
        return this;
    }

    public Vector3D divide(float scalar) {
        this.x /= scalar;
        this.y /= scalar;
        this.z /= scalar;
        return this;
    }

    public float dot(Vector3D other) {
        return (this.x * other.x) + (this.y * other.y) + (this.z * other.z);
    }

    public Vector3D cross(Vector3D other) {
        this.x = this.y * other.z - this.z * other.y;
        this.y = this.z * other.x - this.x * other.z;
        this.z = this.x * other.y - this.y * other.x;
        return this;
    }

    public float magnitude() {
        return (float) Math.sqrt(dot(this));
    }
}
