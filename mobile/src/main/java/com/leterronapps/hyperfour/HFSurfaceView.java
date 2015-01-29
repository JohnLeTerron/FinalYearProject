package com.leterronapps.hyperfour;

import android.opengl.GLSurfaceView;
import android.util.Log;
import android.view.MotionEvent;

/**
 * Created by williamlea on 27/01/15.
 */
public class HFSurfaceView extends GLSurfaceView {

    private HFGame game;


    public HFSurfaceView(HFGame context) {
        super(context);
        game = context;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d(HFGame.DEBUG_TAG, "Surface Touched");

        if(event.getAction() == MotionEvent.ACTION_UP) {
            game.inputManager.addTouchEvent(event);
        }

        return true;
    }

}
