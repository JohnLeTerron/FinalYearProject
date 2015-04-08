package com.leterronapps.finalyearproject.game3d;

import android.view.MotionEvent;

import com.leterronapps.hyperfour.game.Camera;
import com.leterronapps.hyperfour.game.HFGame;
import com.leterronapps.hyperfour.graphics.HFScene;
import com.leterronapps.hyperfour.io.InputManager;
import com.leterronapps.hyperfour.util.Vector3D;

/**
 * Created by williamlea on 30/03/15.
 */
public class InvaderScene extends HFScene {

    Spaceship ship;

    public InvaderScene(HFGame game) {
        super(game);
    }

    @Override
    public void init() {
        super.init();
        camera = new Camera(game, new Vector3D(0,0,0), Camera.MODE_3D, 360, 480);
        camera.rotation.subtract(35, 0, 0);
        ship = new Spaceship(this, new Vector3D(0,-10,-7));
        sceneObjects.add(ship);

        int spawnZ = -35;
        for(int i = 0; i < 5; i++) {
            InvaderRow row = new InvaderRow(this, new Vector3D(0, -10, spawnZ));
            sceneObjects.add(row);
            spawnZ -= 10;
        }
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        for(MotionEvent event : InputManager.touchEvents.getEvents()) {
            if(event.getAction() == MotionEvent.ACTION_UP) {
                ship.shoot();
            }
        }
    }
}
