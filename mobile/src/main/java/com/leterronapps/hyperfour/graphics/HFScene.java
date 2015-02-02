package com.leterronapps.hyperfour.graphics;

import com.leterronapps.hyperfour.game.HFGame;

/**
 * Created by williamlea on 02/02/15.
 */
public abstract class HFScene {

    protected HFGame game;

    public HFScene(HFGame game) {
        this.game = game;
    }

    public abstract void update(float deltaTime);

    public abstract void render();

    public abstract void resume();

    public abstract void pause();

    public abstract void destroy();
}
