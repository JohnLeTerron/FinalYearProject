package com.leterronapps.hyperfour;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.Log;
import android.view.MotionEvent;

/**
 * Created by williamlea on 27/01/15.
 */
public class HFSurfaceView extends GLSurfaceView {

    private HFGame game;

    private SoundClip tickClip;

    public HFSurfaceView(HFGame context) {
       super(context);
        game = context;
        tickClip = game.soundManager.newSoundClip("tick.mp3");
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d(HFGame.DEBUG_TAG, "Surface Touched");

        if(event.getAction() == MotionEvent.ACTION_UP) {
            game.soundManager.playSound(tickClip);
        }

        return true;
    }

}
