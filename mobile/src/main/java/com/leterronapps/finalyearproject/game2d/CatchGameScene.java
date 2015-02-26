package com.leterronapps.finalyearproject.game2d;

import android.util.Log;

import com.leterronapps.hyperfour.game.HFGame;
import com.leterronapps.hyperfour.graphics.HFScene;
import com.leterronapps.hyperfour.util.CollisionDetector;
import com.leterronapps.hyperfour.util.CoreAssets;
import com.leterronapps.hyperfour.util.Rectangle;
import com.leterronapps.hyperfour.util.Vector3D;

/**
 * Created by williamlea on 16/02/15.
 */
public class CatchGameScene extends HFScene {

    private Ball ball;
    private Catcher catcher;
    private Spawner spawner;

    public CatchGameScene(HFGame game) {
        super(game);
    }

    @Override
    public void resume() {
        super.resume();
        ball = new Ball(new Vector3D(0f, 250f, 0f), 20, 20);
        ball.setTexture(CoreAssets.scifiPanel);

        catcher = new Catcher(new Vector3D(0f,-(camera.getFrustumHeight() /2) + 100, 0f), 50f, 50f);
        catcher.setTexture(CoreAssets.scifiPanel);

        spawner = new Spawner(this, new Vector3D(0, 250, 0));

        sceneObjects.add(catcher);
        sceneObjects.add(ball);
        sceneObjects.add(spawner);
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);

        if(CollisionDetector.rectanglesColliding((Rectangle)ball.getCollider(), (Rectangle)catcher.getCollider())) {
            Log.d(HFGame.DEBUG_TAG, "Ball collided");
        }
    }
}
