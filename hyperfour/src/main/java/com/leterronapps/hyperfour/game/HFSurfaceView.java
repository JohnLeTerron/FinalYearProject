package com.leterronapps.hyperfour.game;

import android.opengl.GLSurfaceView;
import android.view.MotionEvent;

/**
 * The View where the game graphics are rendered.
 * This View also passes input events onto the InputManager for the engine.
 */
public class HFSurfaceView extends GLSurfaceView {

    private HFGame game;

    /**
     * Constructs the View.
     * @param context The Context needed for the Super-class constructor.
     */
    public HFSurfaceView(HFGame context) {
        super(context);
        game = context;
        setEGLContextClientVersion(2);
    }

    /**
     * Collects MotionEvents and stores them in the InputManager.
     * @param event The event being captured.
     * @return true.
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch(event.getAction()) {
            case MotionEvent.ACTION_UP:
                game.inputManager.addTouchEvent(event);
                break;
            case MotionEvent.ACTION_DOWN:
                game.inputManager.addTouchEvent(event);
                break;
            case MotionEvent.ACTION_MOVE:
                game.inputManager.addTouchEvent(event);
                break;
        }

        return true;
    }

}
