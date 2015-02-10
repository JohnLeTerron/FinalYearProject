package com.leterronapps.finalyearproject;

import com.leterronapps.hyperfour.game.Sprite;
import com.leterronapps.hyperfour.util.Vector3D;

/**
 * Created by williamlea on 09/02/15.
 */
public class TestSprite extends Sprite {


    public TestSprite(Vector3D position, float width, float height) {
        super(position, width, height);
    }

    @Override
    public void update(float deltaTime) {
        position.add(new Vector3D(1.5f * deltaTime, 0, 0));

        if(position.x > 6.0f) {
            position.x = -6.0f;
        }
    }

}
