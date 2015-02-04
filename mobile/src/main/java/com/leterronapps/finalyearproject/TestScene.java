package com.leterronapps.finalyearproject;

import android.opengl.GLES20;
import android.opengl.Matrix;
import android.view.MotionEvent;

import com.leterronapps.hyperfour.game.CoreAssets;
import com.leterronapps.hyperfour.game.HFGame;
import com.leterronapps.hyperfour.graphics.*;

import java.util.Vector;

/**
 * Created by williamlea on 02/02/15.
 */
public class TestScene extends HFScene {

    private HFShader shader;

    private int MVPMatrixHandle;

    private final float[] mMVPMatrix = new float[16];
    private final float[] mProjectionMatrix = new float[16];
    private final float[] mViewMatrix = new float[16];

    private float triangleCoords[] = {
            0.0f,  0.622008459f, 0.0f,
            -0.5f, -0.311004243f, 0.0f,
            0.5f, -0.311004243f, 0.0f
    };

    float color[] = { 0.63671875f, 0.76953125f, 0.22265625f, 1.0f };

    private Vertices triangle;

    public TestScene(HFGame game) {
        super(game);
        game.getSoundManager().loadMusic(CoreAssets.bgMusic);
        shader = new HFShader();

        triangle = new Vertices(triangleCoords, 3, false);
        triangle.setShader(shader);
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
        int width = 320, height = 480;
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);

        GLES20.glViewport(0, 0, width, height);

        float ratio = (float) width / height;

        Matrix.frustumM(mProjectionMatrix, 0, -ratio, ratio, -1, 1, 3, 7);

        Matrix.setLookAtM(mViewMatrix, 0, 0, 0, -3f, 0f, 0f, 0f, 0f, 1.0f, 0.0f);
        Matrix.multiplyMM(mMVPMatrix, 0, mProjectionMatrix, 0, mViewMatrix, 0);

        GLES20.glUseProgram(shader.getProgram());

        triangle.bind();

        MVPMatrixHandle = shader.getHandle("matrixHandle");
        GLES20.glUniformMatrix4fv(MVPMatrixHandle, 1, false, mMVPMatrix, 0);

        triangle.draw();
        triangle.unbind();
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
