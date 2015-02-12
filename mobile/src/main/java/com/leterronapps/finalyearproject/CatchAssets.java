package com.leterronapps.finalyearproject;

import com.leterronapps.hyperfour.game.HFGame;
import com.leterronapps.hyperfour.graphics.HFTexture;
import com.leterronapps.hyperfour.util.AssetLoader;

/**
 * Created by williamlea on 12/02/15.
 */
public class CatchAssets implements AssetLoader {

    public static HFTexture catchLogo;
    public static HFTexture playButton;

    @Override
    public void load(HFGame game) {
        catchLogo = new HFTexture(game, "catch_logo.png");
        playButton = new HFTexture(game, "catch_play.png");
    }
}
