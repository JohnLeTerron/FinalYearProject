package com.leterronapps.finalyearproject;

import android.opengl.GLES20;
import android.opengl.Matrix;
import android.util.Log;
import android.view.MotionEvent;

import com.leterronapps.hyperfour.game.CoreAssets;
import com.leterronapps.hyperfour.game.HFGame;
import com.leterronapps.hyperfour.graphics.*;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.Vector;

/**
 * Created by williamlea on 02/02/15.
 */
public class TestScene extends HFScene {

    private HFShader shader;

    private int positionHandle;
    private int MVPMatrixHandle;

    private final float[] mMVPMatrix = new float[16];
    private final float[] mProjectionMatrix = new float[16];
    private final float[] mViewMatrix = new float[16];

    private FloatBuffer vertexBuffer;
    private final int COORDS_PER_VERTEX = 3;
    private float triangleCoords[] = {
            0.0f,  0.622008459f, 0.0f,
            -0.5f, -0.311004243f, 0.0f,
            0.5f, -0.311004243f, 0.0f
    };
    private final int vertexCount = triangleCoords.length / COORDS_PER_VERTEX;
    private final int vertexStride = COORDS_PER_VERTEX * 4;

    float color[] = { 0.63671875f, 0.76953125f, 0.22265625f, 0.0f };

    private final String vertexShaderSrc =
            // This matrix member variable provides a hook to manipulate
            // the coordinates of the objects that use this vertex shader
            "uniform mat4 uMVPMatrix;" +
                    "attribute vec4 vPosition;" +
                    "void main() {" +
                    // the matrix must be included as a modifier of gl_Position
                    // Note that the uMVPMatrix factor *must be first* in order
                    // for the matrix multiplication product to be correct.
                    "  gl_Position = vPosition;" +
                    "}";

    private final String fragShaderSrc =
            "precision mediump float;" +
                    "uniform vec4 vColor;" +
                    "void main() {" +
                    "  gl_FragColor = vColor;" +
                    "}";

    public TestScene(HFGame game) {
        super(game);
        game.getSoundManager().loadMusic(CoreAssets.bgMusic);
        shader = new HFShader(vertexShaderSrc, fragShaderSrc);

        ByteBuffer bb = ByteBuffer.allocateDirect(triangleCoords.length * 4);
        bb.order(ByteOrder.nativeOrder());

        vertexBuffer = bb.asFloatBuffer();
        vertexBuffer.put(triangleCoords);
        vertexBuffer.position(0);
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

        positionHandle = GLES20.glGetAttribLocation(shader.getProgram(), "vPosition");
        GLES20.glEnableVertexAttribArray(positionHandle);

        GLES20.glVertexAttribPointer(
                positionHandle, COORDS_PER_VERTEX,
                GLES20.GL_FLOAT, false,
                vertexStride, vertexBuffer);

        int mColorHandle = GLES20.glGetUniformLocation(shader.getProgram(), "vColor");

        // Set color for drawing the triangle
        GLES20.glUniform4fv(mColorHandle, 1, color, 0);

        MVPMatrixHandle = GLES20.glGetUniformLocation(shader.getProgram(), "uMVPMatrix");
        checkGlError("glGetUniformLocation");
        GLES20.glUniformMatrix4fv(MVPMatrixHandle, 1, false, mMVPMatrix, 0);
        checkGlError("glUniformMatrix4fv");

        GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, vertexCount);
        GLES20.glDisableVertexAttribArray(positionHandle);
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

    private void checkGlError(String glOperation) {
        int error;
        while ((error = GLES20.glGetError()) != GLES20.GL_NO_ERROR) {
            Log.e(game.DEBUG_TAG, glOperation + ": glError " + error);
            throw new RuntimeException(glOperation + ": glError " + error);
        }
    }
}
