package com.leterronapps.hyperfour;

import android.app.Activity;
import android.opengl.GLSurfaceView.Renderer;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.view.View;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by williamlea on 27/01/15.
 */
public abstract class HFGame extends Activity implements Renderer {

    public static final String DEBUG_TAG = "HyperFour Engine";

    protected FileManager fileManager;
    protected SoundManager soundManager;

    private HFSurfaceView surfaceView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        fileManager = new FileManager(getAssets());
        soundManager = new SoundManager(this);

        surfaceView = new HFSurfaceView(this);
        surfaceView.setRenderer(this);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(surfaceView);

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
        Log.d(DEBUG_TAG, Integer.toString(surfaceView.getWidth()));
        Log.d(DEBUG_TAG, Integer.toString(surfaceView.getHeight()));
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        Log.d(DEBUG_TAG, "Surface Changed");
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        //Log.d(DEBUG_TAG, "DRAW!!");
    }

    public FileManager getFileManager() {
        return fileManager;
    }

    public SoundManager getSoundManager() {
        return soundManager;
    }
}
