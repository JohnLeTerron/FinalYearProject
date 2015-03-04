package com.leterronapps.finalyearproject.game2d;

import com.leterronapps.hyperfour.game.Sprite;
import com.leterronapps.hyperfour.io.InputManager;
import com.leterronapps.hyperfour.util.Rectangle;
import com.leterronapps.hyperfour.util.Vector3D;

/**
 * Created by williamlea on 16/02/15.
 */
public class Catcher extends Sprite {

    public Catcher(Vector3D position, float width, float height) {
        super(position, width, height);
        setCollider(new Rectangle(position, width, height - 20));
        setTexture(CatchAssets.catcher);
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        float x = -InputManager.getAccelX();
        position.x += x * 20f * deltaTime;
    }
}
