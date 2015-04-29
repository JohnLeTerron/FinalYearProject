package com.leterronapps.hyperfour.util;

/**
 * A utility class containing methods for detecting collisions between bounding geometry.
 * The methods in this class are statically implemented so do NOT instantiate this class.
 */
public class CollisionDetector {

    /**
     * Tests to see if two bounding circles are colliding with each other.
     * @param object The collision circle of the SceneObject having collision tested for.
     * @param other The collision circle of another SceneObject that object might be colliding with.
     * @return true if the Circles are colliding, else returns false.
     */
    public static boolean circlesColliding(Circle object, Circle other) {
        float distX = object.position.x - other.position.x;
        float distY = object.position.y - other.position.y;
        float distance = distX * distX + distY * distY;
        float sumRadii = object.getRadius() + other.getRadius();
        return distance <= sumRadii * sumRadii;
    }

    /**
     * Tests to see if a point coordinate is within a bounding circle. Useful for checking if a player
     * has touched a round button in the scene for example.
     * @param object The collision circle of the SceneObject having collision tested for.
     * @param point The point being tested for collision with object.
     * @return true if the point is within the bounding circle, else returns false.
     */
    public static boolean pointInCircle(Circle object, Vector3D point) {
        float distX = object.position.x - point.x;
        float distY = object.position.y - point.y;
        float distance = distX * distX + distY * distY;
        return distance < object.getRadius() * object.getRadius();
    }

    /**
     * Tests to see if a point coordinate is within a bounding circle. Useful for checking if a player
     * has touched a round button in the scene for example.
     * @param object The collision circle of the SceneObject having collision tested for.
     * @param x The X component of the point.
     * @param y The Y component of the point.
     * @return true if the point is within the bounding circle, else returns false.
     */
    public static boolean pointInCircle(Circle object, float x, float y) {
        float distX = object.position.x - x;
        float distY = object.position.y - y;
        float distance = distX * distX + distY * distY;
        return distance < object.getRadius() * object.getRadius();
    }

    /**
     * Tests to see if two bounding rectangles are colliding with each other.
     * @param object The collision rectangle of the SceneObject having collision tested for.
     * @param other The collision rectangle of another SceneObject that object might be colliding with.
     * @return true if the Rectangles are colliding, else returns false.
     */
    public static boolean rectanglesColliding(Rectangle object, Rectangle other) {
        Vector2D lowerLeft1 = new Vector2D(object.position.x - object.getWidth() /2, object.position.y - object.getHeight() /2);
        Vector2D lowerLeft2 = new Vector2D(other.position.x - other.getWidth() /2, other.position.y - other.getHeight() /2);

        if(lowerLeft1.x < lowerLeft2.x + other.getWidth() &&
           lowerLeft1.x + object.getWidth() > lowerLeft2.x &&
           lowerLeft1.y < lowerLeft2.y + other.getHeight() &&
           lowerLeft1.y + object.getHeight() > lowerLeft2.y) {
            return object.isActive();
        } else {
            return false;
        }
    }

    /**
     * Tests to see if a point coordinate is within a bounding rectangle. Useful for checking if a player
     * has touched a rectangular button in the scene for example.
     * @param object The collision rectangle of the SceneObject having collision tested for.
     * @param point The point being tested for collision with object.
     * @return true if the point is within the bounding rectangle, else returns false.
     */
    public static boolean pointInRectangle(Rectangle object, Vector3D point) {
        Vector2D lowerLeft = new Vector2D(object.position.x - object.getWidth() /2, object.position.y - object.getHeight() /2);
        return lowerLeft.x <= point.x && lowerLeft.x + object.getWidth() >= point.x &&
               lowerLeft.y <= point.y && lowerLeft.y + object.getHeight() >= point.y;
    }

    /**
     * Tests to see if a point coordinate is within a bounding rectangle. Useful for checking if a player
     * has touched a rectangular button in the scene for example.
     * @param object The collision rectangle of the SceneObject having collision tested for.
     * @param x The X component of the point.
     * @param y The Y component of the point.
     * @return true if the point is within the rectangle circle, else returns false.
     */
    public static boolean pointInRectangle(Rectangle object, float x, float y) {
        Vector2D lowerLeft = new Vector2D(object.position.x - object.getWidth() /2, object.position.y - object.getHeight() /2);
        return lowerLeft.x <= x && lowerLeft.x + object.getWidth() >= x &&
                lowerLeft.y <= y && lowerLeft.y + object.getHeight() >= y;
    }

    /**
     * Tests to see if two bounding spheres are colliding with each other.
     * This method reuses the Circle collider class but includes the Z component of their position in the calculation.
     * @param object The collision sphere of the SceneObject having collision tested for.
     * @param other The collision sphere of another SceneObject that object might be colliding with.
     * @return true if the spheres are colliding, else returns false.
     */
    public static boolean spheresColliding(Circle object, Circle other) {
        float distX = object.position.x - other.position.x;
        float distY = object.position.y - other.position.y;
        float distZ = object.position.z - other.position.z;
        float distance = distX * distX + distY * distY + distZ * distZ;
        float sumRadii = object.getRadius() + other.getRadius();
        return distance <= sumRadii * sumRadii;
    }

}
