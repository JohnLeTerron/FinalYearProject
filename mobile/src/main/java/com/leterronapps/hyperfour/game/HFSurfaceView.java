package com.leterronapps.hyperfour.game;

import android.opengl.GLSurfaceView;
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
