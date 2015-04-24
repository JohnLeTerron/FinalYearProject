package com.leterronapps.finalyearproject;

import android.util.Log;

import com.leterronapps.hyperfour.game.HFGame;
import com.leterronapps.hyperfour.game.SceneObject;
import com.leterronapps.hyperfour.game.Sprite;
import com.leterronapps.hyperfour.graphics.HFScene;
import com.leterronapps.hyperfour.graphics.SpriteAnimation;
import com.leterronapps.hyperfour.util.Rectangle;
import com.leterronapps.hyperfour.util.Vector3D;

/**
 * Created by williamlea on 04/03/15.
 */
public class Bomb extends Sprite {

    private float movementSpeed = 55f;

    public Bomb(HFScene scene, Vector3D position, float width, float height) {
        super(scene, position, width, height);
        setCollider(new Rectangle(position, width, height));
        setTexture(CatchAssets.bombs);
        anim = new SpriteAnimation(this, 3, 1.0f);
        anim.setup(texture);
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        position.y -= movementSpeed * deltaTime;
        if(anim != null) {
            anim.play(deltaTime);
        }
    }

    @Override
    public void onCollide(SceneObject other) {
        super.onCollide(other);

        if(other instanceof Catcher) {
            Log.d(HFGame.DEBUG_TAG, "Bomb collided with Catcher");
            this.movementSpeed = 0;
            CatchGameScene gameScene = (CatchGameScene) scene;
            setTexture(CatchAssets.explosion);
            setAnimation(new SpriteAnimation(this, 3, 1.0f));
            anim.setup(texture);
            anim.setLooping(false);
            gameScene.controller.decrementLife();
            destroy();
        }
    }

    public void setMovementSpeed(float movementSpeed) {
        this.movementSpeed = movementSpeed;
    }
}
