package com.leterronapps.finalyearproject;

import com.leterronapps.hyperfour.game.Camera;
import com.leterronapps.hyperfour.game.HFGame;
import com.leterronapps.hyperfour.game.Sprite;
import com.leterronapps.hyperfour.graphics.HFScene;
import com.leterronapps.hyperfour.util.CoreAssets;
import com.leterronapps.hyperfour.util.Vector3D;

/**
 * Created by williamlea on 30/03/15.
 */
public class Test3D extends HFScene {
    public Test3D(HFGame game) {
        super(game);
    }

    @Override
    public void init() {
        super.init();
        camera = new Camera(game, new Vector3D(0,0,-8), Camera.MODE_3D, 360, 480);

        Sprite testSprite = new Sprite(this, new Vector3D(0,0,0), 3f, 3f);
        testSprite.setTexture(CoreAssets.scifiPanel);
        testSprite.rotation.y = 5.0f;

        //sceneObjects.add(testSprite);

        Cube cube = new Cube(this, new Vector3D(0,0,0));
        cube.rotation.y = 15.0f;
        cube.rotation.x = 15.0f;
        sceneObjects.add(cube);
    }
}
