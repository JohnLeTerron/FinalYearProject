package com.leterronapps.hyperfour.game;

import android.app.Activity;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView.Renderer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

import com.leterronapps.hyperfour.audio.SoundManager;
import com.leterronapps.hyperfour.graphics.HFScene;
import com.leterronapps.hyperfour.io.FileManager;
import com.leterronapps.hyperfour.io.InputManager;
import com.leterronapps.hyperfour.util.AssetLoader;
import com.leterronapps.hyperfour.util.CoreAssets;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * The base class for game activities in the HyperFour engine.
 * In order to make your own game using the engine your application's launcher activity must extend this class.
 */
public abstract class HFGame extends Activity implements Renderer {

    /** The Debug tag for the Engine to be used in LogCat. */
    public static final String DEBUG_TAG = "HyperFour Engine";

    /** The file manager. */
    protected FileManager fileManager;

    /** The sound manager. */
    protected SoundManager soundManager;

    /** The input manager. */
    protected InputManager inputManager;

    private HFSurfaceView surfaceView;

    /** The current scene being shown to the player. */
    protected HFScene currentScene;

    private int screenWidth;
    private int screenHeight;

    /** The engine's core assets. */
    protected CoreAssets coreAssets;

    /** The custom assets a programmer adds for their game. */
    protected AssetLoader gameAssets;

    private long lastFrameTime;

    private GameState currentState = GameState.Init;
    private final Object stateLock = new Object();

    /**
     * Initializes the application and the managers and sets up the rendering surface.
     * @param savedInstanceState
     */
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

    /**
     * Called when the player pauses the application. Sets the GameState to paused or if the application
     * is being closed sets the GameState to finishing.
     */
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

    /**
     * Sets the application into fullscreen mode.
     * @param hasFocus
     */
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

    /**
     * Sets up the rendering surface and loads the engine's core assets and the game assets.
     * @param gl This parameter is unused.
     * @param config This parameter is unused.
     */
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

    /**
     * Sets the new screen dimensions for the game if they change.
     * @param gl This parameter is unused.
     * @param width The new surface width.
     * @param height The new surface height.
     */
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

    /**
     * Called on a separate rendering thread by the Android system. Renders and updates the current scene.
     * @param gl This parameter is unused.
     */
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

    /**
     * Override this method to set the starting scene a for game.
     * @return The starting scene of the game.
     */
    public abstract HFScene getStartScene();

    /**
     * Disposes of the current scene and sets a the current scene to a specified new scene.
     * @param scene The new scene.
     */
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

    /**
     *
     * @return The game's file manager.
     */
    public FileManager getFileManager() {
        return fileManager;
    }

    /**
     *
     * @return The game's sound manager.
     */
    public SoundManager getSoundManager() {
        return soundManager;
    }

    /**
     *
     * @return The game's input manager.
     */
    public InputManager getInputManager() {
        return inputManager;
    }

    /**
     *
     * @return The width of the rendering surface.
     */
    public int getScreenWidth() {
        return screenWidth;
    }

    /**
     *
     * @return The height of the rendering surface.
     */
    public int getScreenHeight() {
        return screenHeight;
    }
}
