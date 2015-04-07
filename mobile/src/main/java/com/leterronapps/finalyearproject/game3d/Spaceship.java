package com.leterronapps.finalyearproject.game3d;

import com.leterronapps.hyperfour.game.SceneObject;
import com.leterronapps.hyperfour.graphics.HFScene;
import com.leterronapps.hyperfour.graphics.Vertices;
import com.leterronapps.hyperfour.util.CoreAssets;
import com.leterronapps.hyperfour.util.Vector3D;

/**
 * Created by williamlea on 02/04/15.
 */
public class Spaceship extends SceneObject {

    public Spaceship(HFScene scene, Vector3D position) {
        super(scene, position);
        vertices = InvaderAssets.spaceship;
        setTexture(CoreAssets.scifiPanel);
        rotation.subtract(90,0,0);
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
    }

}
