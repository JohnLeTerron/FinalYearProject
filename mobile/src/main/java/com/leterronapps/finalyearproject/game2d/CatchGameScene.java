package com.leterronapps.finalyearproject.game2d;

import com.leterronapps.hyperfour.game.HFGame;
import com.leterronapps.hyperfour.graphics.HFScene;
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
        Catcher catcher = new Catcher(new Vector3D(0f,0f, 0f), 0f, 0f);
        sceneObjects.add(catcher);
    }
}
