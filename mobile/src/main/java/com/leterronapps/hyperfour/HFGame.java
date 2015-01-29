package com.leterronapps.hyperfour;

import android.app.Activity;
import android.opengl.GLSurfaceView.Renderer;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.WindowManager;
import android.view.View;

import com.leterronapps.hyperfour.audio.SoundClip;
import com.leterronapps.hyperfour.audio.SoundManager;
import com.leterronapps.hyperfour.io.FileManager;
import com.leterronapps.hyperfour.io.InputManager;

import java.util.Vector;

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

    private SoundClip tickSound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        fileManager = new FileManager(getAssets());
        soundManager = new SoundManager(this);
        inputManager = new InputManager(this);

        surfaceView = new HFSurfaceView(this);
        surfaceView.setRenderer(this);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(surfaceView);

        tickSound = soundManager.newSoundClip("tick.mp3");

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

    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {

    }

    @Override
    public void onDrawFrame(GL10 gl) {
        Vector<MotionEvent> events = inputManager.getTouchEvents();
        for(MotionEvent event : events) {
            if(event.getAction() == MotionEvent.ACTION_UP) {
                soundManager.playSound(tickSound);
                break;
            }
        }
        inputManager.clearEventPools();
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
