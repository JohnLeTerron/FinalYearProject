package com.leterronapps.hyperfour;

import android.view.MotionEvent;

import java.util.Vector;

/**
 * Created by williamlea on 29/01/15.
 */
public class InputManager {

    private EventPool<MotionEvent> touchEvents;

    public InputManager() {
        touchEvents = new EventPool<>();
    }

    public void addTouchEvent(MotionEvent event) {
        touchEvents.addEvent(event);
    }

    public void clearEventPools() {
        touchEvents.clear();
    }

    public Vector<MotionEvent> getTouchEvents() {
        return touchEvents.getEvents();
    }

}
