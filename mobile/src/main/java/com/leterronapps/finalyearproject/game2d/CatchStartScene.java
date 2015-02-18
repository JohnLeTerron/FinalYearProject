package com.leterronapps.finalyearproject.game2d;

import com.leterronapps.hyperfour.game.HFGame;
import com.leterronapps.hyperfour.game.game2d.Sprite;
import com.leterronapps.hyperfour.graphics.HFScene;
import com.leterronapps.hyperfour.util.Vector2D;

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
        Sprite logo = new Sprite(new Vector2D(0f, 0f), 1f, 1f);
        logo.setTexture(CatchAssets.catchLogo);
        Sprite playButton = new Sprite(new Vector2D(0f, -3f), 3f, 3f);
        playButton.setTexture(CatchAssets.playButton);
        sceneObjects.add(logo);
        sceneObjects.add(playButton);
    }
}
