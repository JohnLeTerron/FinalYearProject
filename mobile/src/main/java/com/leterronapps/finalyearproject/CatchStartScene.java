package com.leterronapps.finalyearproject;

import com.leterronapps.hyperfour.game.HFGame;
import com.leterronapps.hyperfour.game.Sprite;
import com.leterronapps.hyperfour.graphics.HFScene;
import com.leterronapps.hyperfour.util.CoreAssets;
import com.leterronapps.hyperfour.util.Vector3D;

/**
 * Created by williamlea on 12/02/15.
 */
public class CatchStartScene extends HFScene {

    public CatchStartScene(HFGame game) {
        super(game);
    }

    @Override
    public void resume() {
        super.resume();
        Sprite logo = new Sprite(new Vector3D(0f, 0f, 0f), 1f, 1f);
        logo.setTexture(CoreAssets.scifiPanel);
        Sprite playButton = new Sprite(new Vector3D(0f, -3f, 0f), 3f, 3f);
        sceneObjects.add(logo);
        sceneObjects.add(playButton);
    }
}
