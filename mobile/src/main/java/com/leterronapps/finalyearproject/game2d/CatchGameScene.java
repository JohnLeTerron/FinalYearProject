package com.leterronapps.finalyearproject.game2d;

import android.util.Log;

import com.leterronapps.hyperfour.game.HFGame;
import com.leterronapps.hyperfour.graphics.HFScene;
import com.leterronapps.hyperfour.util.CoreAssets;
import com.leterronapps.hyperfour.util.Vector3D;

/**
 * Created by williamlea on 16/02/15.
 */
public class CatchGameScene extends HFScene {

    Ball ball;
    Catcher catcher;

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

        sceneObjects.add(catcher);
        sceneObjects.add(ball);
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        Log.d(HFGame.DEBUG_TAG, "Ball pos Y: " + ball.position.y);
        Log.d(HFGame.DEBUG_TAG, "Catcher pos X: "+ catcher.position.x);
    }
}
