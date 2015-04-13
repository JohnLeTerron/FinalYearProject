package com.leterronapps.finalyearproject.game3d;

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

    public static enum InvaderType {
        TYPE_ONE,
        TYPE_TWO
    }

    public Invader(HFScene scene, Vector3D position) {
        super(scene, position);
        setTexture(CoreAssets.scifiPanel);
        setCollider(new Circle(position, 1f));
        movement = -3f;
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        position.add(movement * deltaTime,0,0);
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

    public void setMovement(float movement) {
        this.movement = movement;
    }

}
