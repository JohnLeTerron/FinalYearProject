package com.leterronapps.finalyearproject.game3d;

import com.leterronapps.hyperfour.game.HFGame;
import com.leterronapps.hyperfour.graphics.HFTexture;
import com.leterronapps.hyperfour.graphics.ObjLoader;
import com.leterronapps.hyperfour.graphics.Vertices;
import com.leterronapps.hyperfour.util.AssetLoader;

/**
 * Created by williamlea on 07/04/15.
 */
public class InvaderAssets implements AssetLoader {

    public static Vertices spaceship;
    public static Vertices invaderOne;
    public static Vertices invaderTwo;
    public static Vertices shot;

    public static HFTexture logo;

    @Override
    public void load(HFGame game) {
        spaceship = ObjLoader.load(game, "spaceship.obj");
        invaderOne = ObjLoader.load(game, "invader_one.obj");
        invaderTwo = ObjLoader.load(game, "invader_two.obj");
        shot = ObjLoader.load(game, "shot.obj");
        logo = new HFTexture(game, "invaders_logo.png", true);
        logo.load();
    }
}
