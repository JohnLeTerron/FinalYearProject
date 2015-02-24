package com.leterronapps.hyperfour.graphics;

import android.opengl.GLES20;
import android.opengl.Matrix;
import android.util.Log;

import com.leterronapps.hyperfour.game.Camera;
import com.leterronapps.hyperfour.game.HFGame;
import com.leterronapps.hyperfour.game.SceneObject;
import com.leterronapps.hyperfour.util.CollisionDetector;
import com.leterronapps.hyperfour.util.Rectangle;
import com.leterronapps.hyperfour.util.Vector3D;

import java.util.Vector;

/**
 * Created by williamlea on 02/02/15.
 */
public abstract class HFScene {

    protected HFGame game;

    protected HFShader shader;

    protected Camera camera;
    protected Vector<SceneObject> sceneObjects;

    private Vector3D lightPos = new Vector3D(10.0f, 10f, -3f);

    public HFScene(HFGame game) {
        this.game = game;
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


        Matrix.setIdentityM(shader.pMatrix, 0);
        if(camera.getMode() == Camera.MODE_2D) {
            Matrix.orthoM(shader.pMatrix, 0,
                    camera.position.x - (camera.getFrustumWidth() * camera.zoom / 2),
                    camera.position.x + (camera.getFrustumWidth() * camera.zoom / 2),
                    camera.position.y - (camera.getFrustumHeight() * camera.zoom / 2),
                    camera.position.y + (camera.getFrustumHeight() * camera.zoom / 2), -1f, 1);
        } else if(camera.getMode() == Camera.MODE_3D) {
            float ratio = (float) game.getScreenWidth() / game.getScreenHeight();
            Matrix.perspectiveM(shader.pMatrix, 0, 60.0f, ratio, 0.1f, 100.0f);
        }

        Matrix.setIdentityM(shader.camMatrix, 0);
        Matrix.translateM(shader.camMatrix, 0, camera.position.x, camera.position.y, camera.position.z);


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
        shader = new HFShader();
        camera = new Camera(game, new Vector3D(0,0,0), Camera.MODE_2D, 360, 480);
        sceneObjects = new Vector<>();
    }

    public void pause() {
        Log.d(HFGame.DEBUG_TAG, "HFScene - Pause");
        sceneObjects.clear();
    }

    public void destroy() {
        Log.d(HFGame.DEBUG_TAG, "HFScene - Destroy");
    }
}
