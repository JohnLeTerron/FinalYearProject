package com.leterronapps.hyperfour.game;

import android.app.Activity;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView.Renderer;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.view.View;

import com.leterronapps.hyperfour.audio.SoundManager;
import com.leterronapps.hyperfour.graphics.HFScene;
import com.leterronapps.hyperfour.io.FileManager;
import com.leterronapps.hyperfour.io.InputManager;
import com.leterronapps.hyperfour.util.AssetLoader;
import com.leterronapps.hyperfour.util.CoreAssets;

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
    private int screenWidth;
    private int screenHeight;

    protected CoreAssets coreAssets;
    protected AssetLoader gameAssets;

    private long lastFrameTime;

    private GameState currentState = GameState.Init;
    private final Object stateLock = new Object();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(DEBUG_TAG, "HFGame - Create");

        fileManager = new FileManager(getAssets());
        soundManager = new SoundManager(this);
        inputManager = new InputManager(this);

        coreAssets = new CoreAssets();

        surfaceView = new HFSurfaceView(this);
        surfaceView.setRenderer(this);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(surfaceView);

    }

    @Override
    protected void onPause() {
        Log.d(DEBUG_TAG, "HFGame - Pause");
        synchronized(stateLock) {
            if(isFinishing()) {
                currentState = GameState.Finishing;
                soundManager.releasePlayer();
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
    protected void onStop() {
        Log.d(DEBUG_TAG, "HFGame - Stop");
        super.onStop();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(DEBUG_TAG, "HFGame - Resume");
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
        Log.d(DEBUG_TAG, "Surface Created");
        Log.d(DEBUG_TAG, currentState.toString());
        synchronized(stateLock) {
            if (currentState == GameState.Init) {
                currentScene = getStartScene();
                coreAssets.load(this);
                if(gameAssets != null) {
                    gameAssets.load(this);
                }
                currentScene.init();
            } else if(currentState == GameState.Paused) {
                coreAssets.load(this);
                if(gameAssets != null) {
                    gameAssets.load(this);
                }
            }

        }

    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        Log.d(DEBUG_TAG, "Surface Changed");
        GLES20.glViewport(0, 0, width, height);
        screenWidth = width;
        screenHeight = height;
        Log.d(DEBUG_TAG, "HFGame - Screen Width: " + screenWidth + " Screen Height: " + screenHeight);
        synchronized(stateLock) {
            currentState = GameState.Running;
            currentScene.resume();
            lastFrameTime = System.nanoTime();
        }
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
            throw new IllegalArgumentException("scene can't be null");
        }

        this.currentScene.pause();
        this.currentScene.destroy();
        scene.init();
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

    public int getScreenWidth() {
        return screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }
}
