package com.leterronapps.finalyearproject;

import android.opengl.GLES20;
import android.view.MotionEvent;

import com.leterronapps.hyperfour.game.CoreAssets;
import com.leterronapps.hyperfour.game.HFGame;
import com.leterronapps.hyperfour.graphics.HFScene;

import java.util.Vector;

/**
 * Created by williamlea on 02/02/15.
 */
public class TestScene extends HFScene {

    public TestScene(HFGame game) {
        super(game);
        game.getSoundManager().loadMusic(CoreAssets.bgMusic);
    }

    @Override
    public void update(float deltaTime) {
        GLES20.glClearColor(0.3f, 0.2f, 0.6f, 1.0f);
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
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);
    }

    @Override
    public void resume() {
        game.getSoundManager().playMusic();
    }

    @Override
    public void pause() {
        game.getSoundManager().pauseMusic();
    }

    @Override
    public void destroy() {
        game.getSoundManager().stopMusic();
    }
}
