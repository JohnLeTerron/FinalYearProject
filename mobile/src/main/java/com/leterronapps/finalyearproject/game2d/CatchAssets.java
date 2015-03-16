package com.leterronapps.finalyearproject.game2d;

import com.leterronapps.hyperfour.game.HFGame;
import com.leterronapps.hyperfour.graphics.HFSubTexture;
import com.leterronapps.hyperfour.graphics.HFTexture;
import com.leterronapps.hyperfour.graphics.SpriteAnimation;
import com.leterronapps.hyperfour.util.AssetLoader;

/**
 * Created by williamlea on 12/02/15.
 */
public class CatchAssets implements AssetLoader {

    public static HFTexture catchLogo;
    public static HFTexture playButton;
    public static HFTexture background;
    public static HFTexture balls;
    public static HFTexture catcher;
    public static HFTexture pauseHudButton;
    public static HFTexture resumeHudButton;
    public static HFTexture stopwatch;
    public static HFTexture bombs;
    public static HFTexture explosion;

    public static HFSubTexture playSubTex;
    public static HFSubTexture ball_bb;
    public static HFSubTexture ball_wg;

    @Override
    public void load(HFGame game) {
        catchLogo = new HFTexture(game, "catch_logo.png");
        catchLogo.load();
        playButton = new HFTexture(game, "catch_play.png");
        playButton.load();
        playSubTex = new HFSubTexture(playButton, 0, 0, playButton.getWidth(), playButton.getHeight());
        background = new HFTexture(game, "game_background.png");
        background.load();
        balls = new HFTexture(game, "balls.png");
        balls.load();
        ball_bb = new HFSubTexture(balls, 0, 0, balls.getWidth() / 2, balls.getHeight());
        ball_wg = new HFSubTexture(balls, balls.getWidth() / 2, 0, balls.getWidth() / 2, balls.getHeight());
        catcher = new HFTexture(game, "catcher.png");
        catcher.load();
        pauseHudButton = new HFTexture(game, "pause.png");
        pauseHudButton.load();
        resumeHudButton = new HFTexture(game, "resume.png");
        resumeHudButton.load();
        stopwatch = new HFTexture(game, "stopwatch.png");
        stopwatch.load();
        bombs = new HFTexture(game, "bombs.png");
        bombs.load();
        explosion = new HFTexture(game, "explosion.png");
        explosion.load();
    }
}
