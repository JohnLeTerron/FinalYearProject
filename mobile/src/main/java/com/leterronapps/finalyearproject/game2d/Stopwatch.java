package com.leterronapps.finalyearproject.game2d;

import android.util.Log;

import com.leterronapps.hyperfour.game.HFGame;
import com.leterronapps.hyperfour.game.SceneObject;
import com.leterronapps.hyperfour.game.Sprite;
import com.leterronapps.hyperfour.graphics.HFScene;
import com.leterronapps.hyperfour.util.Rectangle;
import com.leterronapps.hyperfour.util.Vector3D;

/**
 * Created by williamlea on 04/03/15.
 */
public class Stopwatch extends Sprite {

    private float movementSpeed = 45f;

    public Stopwatch(HFScene scene, Vector3D position, float width, float height) {
        super(scene, position, width, height);
        setCollider(new Rectangle(position, width, height));
        setTexture(CatchAssets.stopwatch);
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
            Log.d(HFGame.DEBUG_TAG, "Stopwatch collided with Catcher");
            CatchGameScene gameScene = (CatchGameScene) scene;
            gameScene.controller.incrementTime(5);

        }
    }

    public void setMovementSpeed(float movementSpeed) {
        this.movementSpeed = movementSpeed;
    }
}
