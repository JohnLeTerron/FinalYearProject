package com.leterronapps.finalyearproject.game2d;

import com.leterronapps.hyperfour.game.HFGame;
import com.leterronapps.hyperfour.graphics.HFScene;
import com.leterronapps.hyperfour.util.CoreAssets;
import com.leterronapps.hyperfour.util.Vector3D;

/**
 * Created by williamlea on 16/02/15.
 */
public class CatchGameScene extends HFScene {

    public CatchGameScene(HFGame game) {
        super(game);
    }

    @Override
    public void resume() {
        super.resume();
        Ball ball = new Ball(new Vector3D(0f, 250f, 0f), 20, 20);
        ball.setTexture(CoreAssets.scifiPanel);

        Catcher catcher = new Catcher(new Vector3D(0f,-(camera.getFrustumHeight() /2) + 100, 0f), 50f, 50f);
        catcher.setTexture(CoreAssets.scifiPanel);

        sceneObjects.add(catcher);
        sceneObjects.add(ball);
    }

}
