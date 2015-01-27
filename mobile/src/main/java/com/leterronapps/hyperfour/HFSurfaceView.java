package com.leterronapps.hyperfour;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.Log;
import android.view.MotionEvent;

/**
 * Created by williamlea on 27/01/15.
 */
public class HFSurfaceView extends GLSurfaceView {


    public HFSurfaceView(Context context) {
       super(context);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d(HFGame.DEBUG_TAG, "Surface Touched");
        return true;
    }

}
