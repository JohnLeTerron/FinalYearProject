package com.leterronapps.hyperfour.util;


/**
 * Created by williamlea on 18/02/15.
 */
public class CollisionDetector {

    public static boolean circlesColliding(Circle object, Circle other) {
        float distX = object.position.x - other.position.x;
        float distY = object.position.y - other.position.y;
        float distance = distX * distX + distY * distY;
        float sumRadii = object.radius + other.radius;
        return distance <= sumRadii * sumRadii;
    }

    public static boolean pointInCircle(Circle object, Vector2D point) {
        float distX = object.position.x - point.x;
        float distY = object.position.y - point.y;
        float distance = distX * distX + distY * distY;
        return distance < object.radius * object.radius;
    }

}
