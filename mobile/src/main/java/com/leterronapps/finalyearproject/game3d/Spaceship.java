package com.leterronapps.finalyearproject.game3d;

import android.util.Log;

import com.leterronapps.hyperfour.game.HFGame;
import com.leterronapps.hyperfour.game.SceneObject;
import com.leterronapps.hyperfour.graphics.HFScene;
import com.leterronapps.hyperfour.io.InputManager;
import com.leterronapps.hyperfour.util.Circle;
import com.leterronapps.hyperfour.util.CoreAssets;
import com.leterronapps.hyperfour.util.Vector3D;

/**
 * Created by williamlea on 02/04/15.
 */
public class Spaceship extends SceneObject {

    public Spaceship(HFScene scene, Vector3D position) {
        super(scene, position);
        vertices = InvaderAssets.spaceship;
        setCollider(new Circle(position, 1f));
        setTexture(CoreAssets.scifiPanel);
        rotation.subtract(0,180,0);
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        float x = InputManager.getAccelY();
        position.x += x * 1.5 * deltaTime;

        if(position.x < GameController.getInstance().MIN_X) {
            position.x = GameController.getInstance().MIN_X;
        } else if(position.x > GameController.getInstance().MAX_X) {
            position.x = GameController.getInstance().MAX_X;
        }
    }

    @Override
    public void onCollide(SceneObject other) {
        super.onCollide(other);
        destroy();
    }

    public void shoot() {
        Log.d(HFGame.DEBUG_TAG, "Shot fired");
        Shot shot = new Shot(scene, new Vector3D(position.x, position.y, position.z));
        shot.rotation.subtract(90, 0, 0);
        shot.scale.subtract(0.5f, 0.5f, 0.5f);
        shot.setMovement(-4f);
        shot.setOwner(this);
        scene.getSceneObjects().add(shot);
    }

}
