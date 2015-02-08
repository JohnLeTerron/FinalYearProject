package com.leterronapps.hyperfour.graphics;

import android.opengl.GLES20;
import android.opengl.Matrix;
import android.util.Log;

import com.leterronapps.hyperfour.game.HFGame;
import com.leterronapps.hyperfour.game.SceneObject;

import java.util.Vector;

/**
 * Created by williamlea on 02/02/15.
 */
public abstract class HFScene {

    protected HFGame game;

    protected HFShader shader;

    protected Vector<SceneObject> sceneObjects;

    private final float[] MVPMatrix = new float[16];
    private final float[] projectionMatrix = new float[16];
    private final float[] viewMatrix = new float[16];

    public HFScene(HFGame game) {
        this.game = game;
        shader = new HFShader();
        sceneObjects = new Vector<>();
    }

    public void update(float deltaTime) {
        GLES20.glUseProgram(shader.getProgram());
        GLES20.glClearColor(0.3f, 0.2f, 0.6f, 1.0f);

        float ratio = (float) game.getScreenWidth() / game.getScreenHeight();
        Matrix.perspectiveM(projectionMatrix, 0, 60.0f, ratio, 0.1f, 100.0f);

        Matrix.setLookAtM(viewMatrix, 0, 0, 0, -5f, 0f, 0f, 0f, 0f, 1.0f, 0.0f);
        Matrix.multiplyMM(MVPMatrix, 0, projectionMatrix, 0, viewMatrix, 0);
        int MVPMatrixHandle = shader.getHandle("matrixHandle");
        GLES20.glUniformMatrix4fv(MVPMatrixHandle, 1, false, MVPMatrix, 0);

        if(!sceneObjects.isEmpty()) {
            for(SceneObject sceneObject : sceneObjects) {
                sceneObject.update(deltaTime);
            }
        }
    }

    public void render() {
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);;

        if(!sceneObjects.isEmpty()) {
            for(SceneObject sceneObject : sceneObjects) {
                sceneObject.render(shader);
            }
        }


    }

    public void resume() {
        Log.d(HFGame.DEBUG_TAG, "HFScene - Screen Width: " + game.getScreenWidth() + " Screen Height: " + game.getScreenHeight());
    }

    public void pause() {
        sceneObjects.clear();
    }

    public abstract void destroy();
}
