package com.leterronapps.finalyearproject;

import android.view.MotionEvent;

import com.leterronapps.hyperfour.game.SceneObject;
import com.leterronapps.hyperfour.util.CoreAssets;
import com.leterronapps.hyperfour.game.HFGame;
import com.leterronapps.hyperfour.graphics.*;

import java.util.Vector;

/**
 * Created by williamlea on 02/02/15.
 */
public class TestScene extends HFScene {

    private float triangleCoords[] = {
            0.0f,  0.622008459f, 0.0f,
            -0.5f, -0.311004243f, 0.0f,
            0.5f, -0.311004243f, 0.0f
    };

    private float triangleNormals[] = {
            0.0f, 0.0f, -1.0f,
            0.0f, 0.0f, -1.0f,
            0.0f, 0.0f, -1.0f
    };

    private float triangleTexCoords[] = {
            0.0f, 0.0f,
            0.0f, 1.0f,
            1.0f, 1.0f
    };

    private Vertices triangle;

    public TestScene(HFGame game) {
        super(game);
        game.getSoundManager().loadMusic(CoreAssets.bgMusic);
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);

        Vector<MotionEvent> events = game.getInputManager().getTouchEvents();
        for(MotionEvent event : events) {
            if(event.getAction() == MotionEvent.ACTION_UP) {
                game.getSoundManager().playSound(CoreAssets.tickSound);
                break;
            }
        }
    }

    @Override
    public void render() {
        super.render();
        triangle.bind();
        triangle.draw();
        triangle.unbind();
    }

    @Override
    public void resume() {
        super.resume();
        game.getSoundManager().playMusic();
        triangle = new Vertices(triangleCoords, triangleNormals, triangleTexCoords);
    }

    @Override
    public void pause() {
        super.pause();
        game.getSoundManager().pauseMusic();
    }

    @Override
    public void destroy() {
        game.getSoundManager().stopMusic();
    }

}
