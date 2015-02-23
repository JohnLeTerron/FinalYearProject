package com.leterronapps.finalyearproject.game2d;

import com.leterronapps.hyperfour.game.HFGame;
import com.leterronapps.hyperfour.game.Sprite;
import com.leterronapps.hyperfour.graphics.HFScene;
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
        Sprite logo = new Sprite(new Vector3D(0f, 400f, 0), game.getScreenWidth() - 100, 420f);
        logo.setTexture(CatchAssets.catchLogo);
        Sprite playButton = new Sprite(new Vector3D(0f, -400f, 0), game.getScreenWidth() - 150, 300f);
        playButton.setTexture(CatchAssets.playButton);
        sceneObjects.add(logo);
        sceneObjects.add(playButton);
    }
}
