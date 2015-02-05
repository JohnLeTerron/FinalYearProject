package com.leterronapps.hyperfour.graphics;

import android.opengl.GLES20;
import android.opengl.Matrix;

import com.leterronapps.hyperfour.game.SceneObject;

/**
 * Created by williamlea on 05/02/15.
 */
public class HFCamera extends SceneObject {

    public static final int MODE_2D = 0;
    public static final int MODE_3D = 1;

    private int frustumWidth;
    private int frustumHeight;
    private int mode;

    private HFShader shader;
    private final float[] MVPMatrix = new float[16];
    private final float[] projectionMatrix = new float[16];
    private final float[] viewMatrix = new float[16];

    public HFCamera(int mode, int frustumWidth, int frustumHeight) {
        this.mode = mode;
        this.frustumWidth = frustumWidth;
        this.frustumHeight = frustumHeight;
    }

    @Override
    public void update(float deltaTime) {
        GLES20.glClearColor(0.3f, 0.2f, 0.6f, 1.0f);
        GLES20.glUseProgram(shader.getProgram());
        float ratio = (float) frustumWidth / frustumHeight;
        Matrix.frustumM(projectionMatrix, 0, -ratio, ratio, -1, 1, 3, 7);

        Matrix.setLookAtM(viewMatrix, 0, 0, 0, -3f, 0f, 0f, 0f, 0f, 1.0f, 0.0f);
        Matrix.multiplyMM(MVPMatrix, 0, projectionMatrix, 0, viewMatrix, 0);

        int MVPMatrixHandle = shader.getHandle("matrixHandle");
        GLES20.glUniformMatrix4fv(MVPMatrixHandle, 1, false, MVPMatrix, 0);
    }

    @Override
    public void render() {
        // Camera doesn't need to render itself
    }

    public void setShader(HFShader shader) {
        this.shader = shader;
    }

    public void setMode(int mode) {
        if(mode > 1 || mode < 0) {
            throw new IllegalArgumentException("Invalid Mode");
        }
        this.mode = mode;
    }

    public int getMode() {
        return mode;
    }

}
