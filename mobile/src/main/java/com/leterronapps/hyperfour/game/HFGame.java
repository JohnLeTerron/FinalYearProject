package com.leterronapps.hyperfour.game;

import android.app.Activity;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView.Renderer;
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

    private float lastFrameTime;

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


        lastFrameTime = System.nanoTime();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //soundManager.pauseMusic();
        currentScene.pause();
        if(isFinishing()) {
            //soundManager.stopMusic();
            currentScene.destroy();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        //soundManager.playMusic();
        currentScene.resume();
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
        GLES20.glClearColor(1.0f, 0.0f, 0.0f, 1.0f);
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {

    }

    @Override
    public void onDrawFrame(GL10 gl) {
        float deltaTime = (System.nanoTime() - lastFrameTime) / 1000000000.0f;
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);

        if(currentScene != null) {
            currentScene.update(deltaTime);
            currentScene.render();
        }

        inputManager.clearEventPools();
    }

    public abstract HFScene getStartScene();

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
