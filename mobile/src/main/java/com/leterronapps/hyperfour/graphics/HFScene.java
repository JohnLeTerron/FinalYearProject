package com.leterronapps.hyperfour.graphics;

import android.opengl.GLES20;
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
    protected HFCamera camera;

    protected Vector<SceneObject> sceneObjects;

    public HFScene(HFGame game) {
        this.game = game;
        shader = new HFShader();
        sceneObjects = new Vector<>();
    }

    public void update(float deltaTime) {
        camera.update(deltaTime);

        if(!sceneObjects.isEmpty()) {
            for(SceneObject sceneObject : sceneObjects) {
                sceneObject.update(deltaTime);
            }
        }
    }

    public void render() {
        if(camera.getMode() == HFCamera.MODE_2D) {
            GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);
        } else {
            GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);
        }

        if(!sceneObjects.isEmpty()) {
            for(SceneObject sceneObject : sceneObjects) {
                sceneObject.render();
            }
        }
    }

    public void resume() {
        Log.d(HFGame.DEBUG_TAG, "HFScene - Screen Width: " + game.getScreenWidth() + " Screen Height: " + game.getScreenHeight());
        camera = new HFCamera(HFCamera.MODE_3D, game.getScreenWidth(), game.getScreenHeight());
        camera.setShader(shader);
    }

    public abstract void pause();

    public abstract void destroy();
}
