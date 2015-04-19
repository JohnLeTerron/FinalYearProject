package com.leterronapps.hyperfour.graphics;

import android.opengl.GLES20;
import android.opengl.Matrix;
import android.util.Log;

import com.leterronapps.hyperfour.game.Camera;
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

    protected Camera camera;
    protected Vector<SceneObject> sceneObjects;

    private Vector3D lightPos = new Vector3D(-155.0f, 220f, -50f);

    protected boolean playing = true;

    public HFScene(HFGame game) {
        this.game = game;
    }

    public void update(float deltaTime) {
        GLES20.glUseProgram(shader.getProgram());
        GLES20.glClearColor(0, 0, 0, 1.0f);

        if(!sceneObjects.isEmpty()) {
            if(playing) {
                for (int i = 0; i < sceneObjects.size(); i++) {
                    sceneObjects.get(i).update(deltaTime);
                }
            }

            for(int i = 0; i < sceneObjects.size(); i++) {
                if(sceneObjects.get(i).isMarkedForDeath()) {
                    sceneObjects.remove(i);
                }
            }
        }
    }

    public void render() {
        Matrix.setIdentityM(shader.pMatrix, 0);
        if(camera.getMode() == Camera.MODE_2D) {
            GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);
            Matrix.orthoM(shader.pMatrix, 0,
                    camera.position.x - (camera.getFrustumWidth() * camera.zoom / 2),
                    camera.position.x + (camera.getFrustumWidth() * camera.zoom / 2),
                    camera.position.y - (camera.getFrustumHeight() * camera.zoom / 2),
                    camera.position.y + (camera.getFrustumHeight() * camera.zoom / 2), -1f, 1);
        } else if(camera.getMode() == Camera.MODE_3D) {
            GLES20.glEnable(GLES20.GL_DEPTH_TEST);
            GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);
            float ratio = (float) game.getScreenWidth() / game.getScreenHeight();
            Matrix.perspectiveM(shader.pMatrix, 0, 60.0f, ratio, 0.1f, 100.0f);
        }

        Matrix.setIdentityM(shader.camMatrix, 0);
        Matrix.translateM(shader.camMatrix, 0, camera.position.x, camera.position.y, camera.position.z);
        Matrix.rotateM(shader.camMatrix, 0, -camera.rotation.x, 1,0,0);
        Matrix.rotateM(shader.camMatrix, 0, -camera.rotation.y, 0,1,0);
        Matrix.rotateM(shader.camMatrix, 0, -camera.rotation.z, 0,0,1);

        if(!sceneObjects.isEmpty()) {
            for(SceneObject sceneObject : sceneObjects) {
                GLES20.glUniform3f(shader.getHandle("pointLightPos"), lightPos.x, lightPos.y, lightPos.z);
                sceneObject.render(shader);
            }
        }
        if(camera.getMode() == Camera.MODE_3D) {
            GLES20.glDisable(GLES20.GL_DEPTH_TEST);
        }

        /*
        Matrix.setIdentityM(shader.pMatrix, 0);
        Matrix.orthoM(shader.pMatrix, 0,
                camera.position.x - (camera.getFrustumWidth() * camera.zoom / 2),
                camera.position.x + (camera.getFrustumWidth() * camera.zoom / 2),
                camera.position.y - (camera.getFrustumHeight() * camera.zoom / 2),
                camera.position.y + (camera.getFrustumHeight() * camera.zoom / 2), -1f, 1);
        camera.hud.render(shader);
        */
    }

    public void init() {
        Log.d(HFGame.DEBUG_TAG, "HFScene - Init");
        camera = new Camera(game, new Vector3D(0,0,0), Camera.MODE_2D, 360, 480);
        sceneObjects = new Vector<>();
        playing = true;
    }

    public void resume() {
        Log.d(HFGame.DEBUG_TAG, "HFScene - Resume");
        shader = new HFShader();
    }

    public void pause() {
        Log.d(HFGame.DEBUG_TAG, "HFScene - Pause");
        playing = false;
    }

    public void destroy() {
        Log.d(HFGame.DEBUG_TAG, "HFScene - Destroy");
        sceneObjects.clear();
    }

    public Vector<SceneObject> getSceneObjects() {
        return sceneObjects;
    }
}
