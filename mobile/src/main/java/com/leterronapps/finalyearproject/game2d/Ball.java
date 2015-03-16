package com.leterronapps.finalyearproject.game2d;

import android.util.Log;

import com.leterronapps.hyperfour.game.HFGame;
import com.leterronapps.hyperfour.game.SceneObject;
import com.leterronapps.hyperfour.game.Sprite;
import com.leterronapps.hyperfour.graphics.HFScene;
import com.leterronapps.hyperfour.util.Rectangle;
import com.leterronapps.hyperfour.util.Vector3D;

/**
 * Created by williamlea on 16/02/15.
 */
public class Ball extends Sprite {

    private float movementSpeed = 35f;

    public Ball(Vector3D position, float width, float height) {
        super(position, width, height);
        setCollider(new Rectangle(position, width, height));
    }

    public Ball(HFScene scene, Vector3D position, float width, float height) {
        super(scene, position, width, height);
        setCollider(new Rectangle(position, width, height));
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        position.y -= movementSpeed * deltaTime;
    }

    @Override
    public void onCollide(SceneObject other) {
        super.onCollide(other);

        if(other instanceof Catcher) {
            Log.d(HFGame.DEBUG_TAG, "Ball collided with Catcher");
            this.movementSpeed = 0;
            CatchGameScene gameScene = (CatchGameScene) scene;
            gameScene.controller.incrementScore();
        }
    }

    public void setMovementSpeed(float movementSpeed) {
        this.movementSpeed = movementSpeed;
    }
}
