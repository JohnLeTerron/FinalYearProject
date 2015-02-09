package com.leterronapps.finalyearproject;

import android.opengl.GLES20;
import android.opengl.Matrix;
import android.view.MotionEvent;

import com.leterronapps.hyperfour.util.CoreAssets;
import com.leterronapps.hyperfour.game.HFGame;
import com.leterronapps.hyperfour.graphics.*;

import java.util.Vector;

/**
 * Created by williamlea on 02/02/15.
 */
public class TestScene extends HFScene {

    private float triangleCoords[] = {
            -0.5f,  0.5f, 0.0f,
            -0.5f, -0.5f, 0.0f,
            0.5f, -0.5f, 0.0f,
            0.5f, 0.5f, 0.0f
    };

    private float triangleNormals[] = {
            0.0f, 0.0f, -1.0f,
            0.0f, 0.0f, -1.0f,
            0.0f, 0.0f, -1.0f,
            0.0f, 0.0f, -1.0f
    };

    private float triangleTexCoords[] = {
            0.0f, 0.0f,
            0.0f, 1.0f,
            1.0f, 1.0f,
            1.0f, 0.0f
    };

    private short[] indices = {
            0,1,2,2,3,0
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
        Matrix.setIdentityM(modelViewMatrix, 0);
        Matrix.translateM(modelViewMatrix, 0, 0.5f, 0, 0);
        GLES20.glUniformMatrix4fv(shader.getHandle("pMatrix"), 0, false, pMatrix, 0);
        GLES20.glUniformMatrix4fv(shader.getHandle("camMatrix"), 0, false, camMatrix, 0);
        GLES20.glUniformMatrix4fv(shader.getHandle("mvMatrix"), 0, false, modelViewMatrix, 0);
        triangle.bind(shader);
        triangle.draw();
        triangle.unbind(shader);
    }

    @Override
    public void resume() {
        super.resume();
        game.getSoundManager().playMusic();
        triangle = new Vertices(triangleCoords, triangleNormals, triangleTexCoords, indices);
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
