package com.leterronapps.finalyearproject.game3d;

import com.leterronapps.hyperfour.game.SceneObject;
import com.leterronapps.hyperfour.graphics.HFScene;
import com.leterronapps.hyperfour.util.Vector3D;

/**
 * Created by williamlea on 12/04/15.
 */
public class Shot extends SceneObject {

    private float movement = 4f;

    public Shot(HFScene scene, Vector3D position) {
        super(scene, position);
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);

        position.z -= movement * deltaTime;
    }

    @Override
    public void onCollide(SceneObject other) {
        super.onCollide(other);
        movement = 0f;

        if(other instanceof Invader) {
            GameController.getInstance().upScore();
        } else if(other instanceof Spaceship) {
            GameController.getInstance().takeLife();
        }

        destroy();
    }
}
