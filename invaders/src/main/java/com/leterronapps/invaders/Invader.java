package com.leterronapps.invaders;

import com.leterronapps.hyperfour.game.SceneObject;
import com.leterronapps.hyperfour.graphics.HFScene;
import com.leterronapps.hyperfour.util.Circle;
import com.leterronapps.hyperfour.util.CoreAssets;
import com.leterronapps.hyperfour.util.Vector3D;

/**
 * Created by williamlea on 07/04/15.
 */
public class Invader extends SceneObject {

    private float movement;

    private int minX;
    private int maxX;

    public static enum InvaderType {
        TYPE_ONE,
        TYPE_TWO
    }

    public Invader(HFScene scene, Vector3D position) {
        super(scene, position);
        setTexture(CoreAssets.scifiPanel);
        setCollider(new Circle(position, 1f));
        movement = 3f;
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        position.add(movement * deltaTime,0,0);
        if(position.x < minX) {
            movement = 3f;
            position.x = minX;
            position.z += 2;
        } else if(position.x > maxX) {
            movement = -3f;
            position.x = maxX;
            position.z += 2;
        }
    }

    @Override
    public void onCollide(SceneObject other) {
        super.onCollide(other);
        destroy();
    }

    public void setType(InvaderType type) {
        if(type == InvaderType.TYPE_ONE) {
            vertices = InvaderAssets.invaderOne;
        } else if(type == InvaderType.TYPE_TWO) {
            vertices = InvaderAssets.invaderTwo;
        }
    }

    public void setMinX(int minX) {
        this.minX = minX;
    }

    public void setMaxX(int maxX) {
        this.maxX = maxX;
    }

}
