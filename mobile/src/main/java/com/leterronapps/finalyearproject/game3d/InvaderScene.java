package com.leterronapps.finalyearproject.game3d;

import com.leterronapps.hyperfour.game.Camera;
import com.leterronapps.hyperfour.game.HFGame;
import com.leterronapps.hyperfour.graphics.HFScene;
import com.leterronapps.hyperfour.util.Vector3D;

/**
 * Created by williamlea on 30/03/15.
 */
public class InvaderScene extends HFScene {

    private float spawnOffset = 5.0f;

    public InvaderScene(HFGame game) {
        super(game);
    }

    @Override
    public void init() {
        super.init();
        camera = new Camera(game, new Vector3D(0,-5,0), Camera.MODE_3D, 360, 480);
        //camera.rotation.x = 10;

        Spaceship cube = new Spaceship(this, new Vector3D(0,0,-15));
        sceneObjects.add(cube);

        int spawnX = -5;
        for(int i = 0; i < 5; i++) {
            Invader invader = new Invader(this, new Vector3D(spawnX, 0, -40));
            invader.setType(Invader.InvaderType.TYPE_TWO);
            sceneObjects.add(invader);
            spawnX += spawnOffset;
        }

        spawnX = -5;
        for(int i = 0; i < 5; i++) {
            Invader invader = new Invader(this, new Vector3D(spawnX, 2, -50));
            invader.setType(Invader.InvaderType.TYPE_TWO);
            sceneObjects.add(invader);
            spawnX += spawnOffset;
        }
    }

}
