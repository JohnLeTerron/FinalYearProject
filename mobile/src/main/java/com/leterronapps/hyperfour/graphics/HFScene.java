package com.leterronapps.hyperfour.graphics;

import android.opengl.GLES20;
import android.opengl.Matrix;
import android.util.Log;

import com.leterronapps.hyperfour.game.HFGame;
import com.leterronapps.hyperfour.game.SceneObject;
import com.leterronapps.hyperfour.util.Vector3D;

import java.util.Vector;

/**
 * Created by williamlea on 02/02/15.
 */
public abstract class HFScene {

    protected HFGame game;

    protected HFShader shader;

    protected Vector<SceneObject> sceneObjects;

    private Vector3D lightPos = new Vector3D(10.0f, 10f, -3f);

    public HFScene(HFGame game) {
        this.game = game;
        shader = new HFShader();
        sceneObjects = new Vector<>();
    }

    public void update(float deltaTime) {
        GLES20.glUseProgram(shader.getProgram());
        GLES20.glClearColor(0f, 0f, 0f, 1.0f);

        if(!sceneObjects.isEmpty()) {
            for(SceneObject sceneObject : sceneObjects) {
                sceneObject.update(deltaTime);
            }
        }
    }

    public void render() {
        GLES20.glEnable(GLES20.GL_DEPTH_TEST);
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);

        float ratio = (float) game.getScreenWidth() / game.getScreenHeight();
        Matrix.setIdentityM(shader.pMatrix, 0);
        Matrix.orthoM(shader.pMatrix, 0,
                -(game.getScreenWidth() / 2),
                (game.getScreenWidth() / 2),
                -(game.getScreenHeight() / 2),
                (game.getScreenHeight() / 2), -1f, 1);
        //Matrix.perspectiveM(shader.pMatrix, 0, 60.0f, ratio, 0.1f, 100.0f);

        Matrix.setIdentityM(shader.camMatrix, 0);
        Matrix.translateM(shader.camMatrix, 0, 0, 0, 0);


        if(!sceneObjects.isEmpty()) {
            for(SceneObject sceneObject : sceneObjects) {
                GLES20.glUniform3f(shader.getHandle("pointLightPos"), lightPos.x, lightPos.y, lightPos.z);
                sceneObject.render(shader);
            }
        }

        GLES20.glDisable(GLES20.GL_DEPTH_TEST);
    }

    public void resume() {
        Log.d(HFGame.DEBUG_TAG, "HFScene - Resume");
        Log.d(HFGame.DEBUG_TAG, "HFScene - Screen Width: " + game.getScreenWidth() + " Screen Height: " + game.getScreenHeight());
    }

    public void pause() {
        Log.d(HFGame.DEBUG_TAG, "HFScene - Pause");
        sceneObjects.clear();
    }

    public void destroy() {
        Log.d(HFGame.DEBUG_TAG, "HFScene - Destroy");
    }
}
