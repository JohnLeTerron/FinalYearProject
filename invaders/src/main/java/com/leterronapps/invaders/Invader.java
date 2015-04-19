package com.leterronapps.invaders;

import com.leterronapps.hyperfour.game.SceneObject;
import com.leterronapps.hyperfour.graphics.HFScene;
import com.leterronapps.hyperfour.util.Circle;
import com.leterronapps.hyperfour.util.CoreAssets;
import com.leterronapps.hyperfour.util.Vector3D;

import java.util.Random;

/**
 * Created by williamlea on 07/04/15.
 */
public class Invader extends SceneObject {

    private float movement;

    private int minX;
    private int maxX;

    private float fireTime;
    private float fireTick = 0f;

    public static enum InvaderType {
        TYPE_ONE,
        TYPE_TWO
    }

    public Invader(HFScene scene, Vector3D position) {
        super(scene, position);
        setTexture(CoreAssets.scifiPanel);
        setCollider(new Circle(position, 1f));
        movement = 3f;
        setFireTime();
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

        fireTick += deltaTime;
        if(fireTick > fireTime) {
            shoot();
            fireTick = 0f;
            setFireTime();
        }
    }

    @Override
    public void onCollide(SceneObject other) {
        super.onCollide(other);
        destroy();
    }

    private void shoot() {
        Shot shot = new Shot(scene, new Vector3D(position.x, position.y, position.z));
        shot.rotation.add(90, 0, 0);
        shot.scale.subtract(0.5f, 0.5f, 0.5f);
        shot.setMovement(10f);
        shot.setOwner(this);
        scene.getSceneObjects().add(shot);
    }

    private void setFireTime() {
        Random random = new Random();
        fireTime = 5f + random.nextFloat() * 20;
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
