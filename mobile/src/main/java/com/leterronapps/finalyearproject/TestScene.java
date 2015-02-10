package com.leterronapps.finalyearproject;

import android.view.MotionEvent;

import com.leterronapps.hyperfour.util.CoreAssets;
import com.leterronapps.hyperfour.game.HFGame;
import com.leterronapps.hyperfour.graphics.*;
import com.leterronapps.hyperfour.util.Vector3D;

import java.util.Vector;

/**
 * Created by williamlea on 02/02/15.
 */
public class TestScene extends HFScene {

    private TestSprite testSprtie;

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
                testSprtie.position = new Vector3D(event.getX(), event.getY(), 0f);
                break;
            }

        }
    }

    @Override
    public void render() {
        super.render();
        testSprtie.render(shader);
    }

    @Override
    public void resume() {
        super.resume();
        game.getSoundManager().playMusic();
        testSprtie = new TestSprite(new Vector3D(0f, 0.5f, 0f), 1.5f, 1.5f);
        sceneObjects.add(testSprtie);
        CoreAssets.scifiPanel.reload();
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
