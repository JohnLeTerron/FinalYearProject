package com.leterronapps.hyperfour.util;

import com.leterronapps.hyperfour.game.SceneObject;

import java.util.Vector;

/**
 * Created by williamlea on 18/02/15.
 */
public class CollisionDetector {

    public static boolean circlesColliding(Circle object, Circle other) {
        float distX = object.position.x - other.position.x;
        float distY = object.position.y - other.position.y;
        float distance = distX * distX + distY * distY;
        float sumRadii = object.getRadius() + other.getRadius();
        return distance <= sumRadii * sumRadii;
    }

    public static boolean pointInCircle(Circle object, Vector3D point) {
        float distX = object.position.x - point.x;
        float distY = object.position.y - point.y;
        float distance = distX * distX + distY * distY;
        return distance < object.getRadius() * object.getRadius();
    }

    public static boolean pointInCircle(Circle object, float x, float y) {
        float distX = object.position.x - x;
        float distY = object.position.y - y;
        float distance = distX * distX + distY * distY;
        return distance < object.getRadius() * object.getRadius();
    }

    public static boolean rectanglesColliding(Rectangle object, Rectangle other) {
        Vector2D lowerLeft1 = new Vector2D(object.position.x - object.getWidth() /2, object.position.y - object.getHeight() /2);
        Vector2D lowerLeft2 = new Vector2D(other.position.x - other.getWidth() /2, other.position.y - other.getHeight() /2);

        if(lowerLeft1.x < lowerLeft2.x + other.getWidth() &&
           lowerLeft1.x + object.getWidth() > lowerLeft2.x &&
           lowerLeft1.y < lowerLeft2.y + other.getHeight() &&
           lowerLeft1.y + object.getHeight() > lowerLeft2.y) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean pointInRectangle(Rectangle object, Vector3D point) {
        Vector2D lowerLeft = new Vector2D(object.position.x - object.getWidth() /2, object.position.y - object.getHeight() /2);
        return lowerLeft.x <= point.x && lowerLeft.x + object.getWidth() >= point.x &&
               lowerLeft.y <= point.y && lowerLeft.y + object.getHeight() >= point.y;
    }

    public static boolean pointInRectangle(Rectangle object, float x, float y) {
        Vector2D lowerLeft = new Vector2D(object.position.x - object.getWidth() /2, object.position.y - object.getHeight() /2);
        return lowerLeft.x <= x && lowerLeft.x + object.getWidth() >= x &&
                lowerLeft.y <= y && lowerLeft.y + object.getHeight() >= y;
    }

}
