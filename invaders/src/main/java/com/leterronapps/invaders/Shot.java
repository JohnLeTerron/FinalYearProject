package com.leterronapps.invaders;

import android.util.Log;

import com.leterronapps.hyperfour.game.HFGame;
import com.leterronapps.hyperfour.game.SceneObject;
import com.leterronapps.hyperfour.graphics.HFScene;
import com.leterronapps.hyperfour.util.Circle;
import com.leterronapps.hyperfour.util.CoreAssets;
import com.leterronapps.hyperfour.util.Vector3D;

/**
 * Created by williamlea on 12/04/15.
 */
public class Shot extends SceneObject {

    private float movement = 0f;

    private SceneObject owner;

    public Shot(HFScene scene, Vector3D position) {
        super(scene, position);
        setCollider(new Circle(position, 0.5f));
        vertices = InvaderAssets.shot;
        texture = CoreAssets.scifiPanel;
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        position.z += movement * deltaTime;
        if(position.z < GameController.getInstance().MIN_Z) {
            destroy();
        } else if(position.z > GameController.getInstance().MAX_Z) {
            destroy();
        }
    }

    @Override
    public void onCollide(SceneObject other) {
        super.onCollide(other);
        movement = 0f;

        if(other instanceof Invader) {
            Log.d(HFGame.DEBUG_TAG, "Shot collided with invader");
            GameController.getInstance().upScore();
            other.onCollide(null);
        } else if(other instanceof Spaceship) {
            Log.d(HFGame.DEBUG_TAG, "Shot collided with ship");
            GameController.getInstance().takeLife();
        }
        destroy();
    }

    public void setMovement(float movement) {
        this.movement = movement;
    }

    public void setOwner(SceneObject owner) {
        this.owner = owner;
    }

    public SceneObject getOwner() {
        return owner;
    }
}
