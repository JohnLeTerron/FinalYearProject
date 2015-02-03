package com.leterronapps.hyperfour.game;

import android.app.Activity;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView.Renderer;
import android.opengl.Matrix;
import android.os.Bundle;
import android.view.WindowManager;
import android.view.View;

import com.leterronapps.hyperfour.audio.SoundManager;
import com.leterronapps.hyperfour.graphics.HFScene;
import com.leterronapps.hyperfour.io.FileManager;
import com.leterronapps.hyperfour.io.InputManager;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by williamlea on 27/01/15.
 */
public abstract class HFGame extends Activity implements Renderer {

    public static final String DEBUG_TAG = "HyperFour Engine";

    protected FileManager fileManager;
    protected SoundManager soundManager;
    protected InputManager inputManager;

    private HFSurfaceView surfaceView;
    protected HFScene currentScene;

    protected CoreAssets coreAssets;

    private long lastFrameTime;

    private GameState currentState = GameState.Init;
    private Object stateLock = new Object();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        fileManager = new FileManager(getAssets());
        soundManager = new SoundManager(this);
        inputManager = new InputManager(this);

        coreAssets = new CoreAssets();
        coreAssets.load(this);

        surfaceView = new HFSurfaceView(this);
        surfaceView.setRenderer(this);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(surfaceView);

    }

    @Override
    protected void onPause() {
        synchronized(stateLock) {
            if(isFinishing()) {
                currentState = GameState.Finishing;
            } else {
                currentState = GameState.Paused;
            }

            while(true) {
                try{
                    stateLock.wait();
                    break;
                } catch(InterruptedException ex) {
                    // Nothing to do here
                }
            }
        }
        surfaceView.onPause();
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        surfaceView.onResume();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        // If the current activity has focus, enable immersive mode.
        if (hasFocus) {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        synchronized(stateLock) {
            if (currentState == GameState.Init) {
                currentScene = getStartScene();
            }
            currentState = GameState.Running;
            currentScene.resume();
            lastFrameTime = System.nanoTime();
        }

    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        GLES20.glViewport(0, 0, width, height);
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        switch(currentState) {
            case Running:
                float deltaTime = (System.nanoTime() - lastFrameTime) / 1000000000.0f;
                lastFrameTime = System.nanoTime();
                currentScene.update(deltaTime);
                currentScene.render();
                break;
            case Paused:
                currentScene.pause();
                synchronized(stateLock) {
                    stateLock.notifyAll();
                }
                break;
            case Finishing:
                currentScene.pause();
                currentScene.destroy();
                synchronized(stateLock) {
                    stateLock.notifyAll();
                }
                break;
        }

        inputManager.clearEventPools();
    }

    public abstract HFScene getStartScene();

    public void setScene(HFScene scene) {
        if(scene == null) {
            throw new IllegalArgumentException("Screen can't be null");
        }

        this.currentScene.pause();
        this.currentScene.destroy();
        scene.resume();
        scene.update(0);
        this.currentScene = scene;
    }

    public FileManager getFileManager() {
        return fileManager;
    }

    public SoundManager getSoundManager() {
        return soundManager;
    }

    public InputManager getInputManager() {
        return inputManager;
    }
}