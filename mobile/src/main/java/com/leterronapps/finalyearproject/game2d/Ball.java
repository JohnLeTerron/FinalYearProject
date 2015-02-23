package com.leterronapps.finalyearproject.game2d;

import com.leterronapps.hyperfour.game.Sprite;
import com.leterronapps.hyperfour.util.Vector3D;

/**
 * Created by williamlea on 16/02/15.
 */
public class Ball extends Sprite {

    private float movementSpeed = 3f;

    public Ball(Vector3D position, float width, float height) {
        super(position, width, height);
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        position.y -= movementSpeed * deltaTime;
    }
}
