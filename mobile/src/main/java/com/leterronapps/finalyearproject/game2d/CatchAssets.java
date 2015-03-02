package com.leterronapps.finalyearproject.game2d;

import com.leterronapps.hyperfour.game.HFGame;
import com.leterronapps.hyperfour.graphics.HFTexture;
import com.leterronapps.hyperfour.util.AssetLoader;

/**
 * Created by williamlea on 12/02/15.
 */
public class CatchAssets implements AssetLoader {

    public static HFTexture catchLogo;
    public static HFTexture playButton;
    public static HFTexture ball_bb;
    public static HFTexture ball_wg;
    public static HFTexture background;
    public static HFTexture catcher;

    @Override
    public void load(HFGame game) {
        catchLogo = new HFTexture(game, "catch_logo.png");
        catchLogo.load();
        playButton = new HFTexture(game, "catch_play.png");
        playButton.load();
        ball_bb = new HFTexture(game, "ball_bb.png");
        ball_bb.load();
        ball_wg = new HFTexture(game, "ball_wg.png");
        ball_wg.load();
        background = new HFTexture(game, "game_background.png");
        background.load();
        catcher = new HFTexture(game, "catcher.png");
        catcher.load();
    }
}
