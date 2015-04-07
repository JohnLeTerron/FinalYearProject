package com.leterronapps.finalyearproject.game3d;

import com.leterronapps.hyperfour.game.Camera;
import com.leterronapps.hyperfour.game.HFGame;
import com.leterronapps.hyperfour.graphics.HFScene;
import com.leterronapps.hyperfour.util.Vector3D;

/**
 * Created by williamlea on 30/03/15.
 */
public class InvaderScene extends HFScene {
    public InvaderScene(HFGame game) {
        super(game);
    }

    @Override
    public void init() {
        super.init();
        camera = new Camera(game, new Vector3D(0,0,0), Camera.MODE_3D, 360, 480);

        Spaceship cube = new Spaceship(this, new Vector3D(0,0,-20));
        sceneObjects.add(cube);

        Invader invader = new Invader(this, new Vector3D(0,-5,-20));
        invader.setType(Invader.InvaderType.TYPE_TWO);
        sceneObjects.add(invader);
    }
}
