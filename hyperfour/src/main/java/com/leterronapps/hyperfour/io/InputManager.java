package com.leterronapps.hyperfour.io;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.view.MotionEvent;

import java.util.Vector;

/**
 * The Input manager for the HyperFour engine. This class is the interface for player input.
 * InputManager stores inupt event data ready for use in the Game part of the engine.
 */
public class InputManager implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor accelerometer;

    public static final float[] accelAxes = new float[3];
    public static final EventPool<MotionEvent> touchEvents = new EventPool<>();

    /**
     * Constructs the InputManager.
     * @param activity The HFGame activity that helps set up the accelerometer.
     */
    public InputManager(Activity activity) {
        sensorManager = (SensorManager) activity.getSystemService(Context.SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_GAME);

    }

    /**
     * Collects accelerometer readings from the device.
     * @param event The event from the device sensor.
     */
    @Override
    public void onSensorChanged(SensorEvent event) {
        if(event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            // Accelerometer X Axis
            accelAxes[0] = event.values[0];

            // Accelerometer Y Axis
            accelAxes[1] = event.values[1];

            // Accelerometer Z Axis
            accelAxes[2] = event.values[2];
        }
    }

    /**
     * This method is unused.
     * @param sensor Unused.
     * @param accuracy Unused.
     */
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    /**
     *
     * @return The current X component of the accelerometer reading.
     */
    public static float getAccelX() {
        return accelAxes[0];
    }

    /**
     *
     * @return The current Y component of the accelerometer reading.
     */
    public static float getAccelY() {
        return accelAxes[1];
    }

    /**
     *
     * @return The current Z component of the accelerometer reading.
     */
    public static float getAccelZ() {
        return accelAxes[2];
    }

    /**
     * Adds a MotionEvent to the event pool i the Input manager.
     * @param event The event being stored in the Input manager's event pool.
     */
    public void addTouchEvent(MotionEvent event) {
        touchEvents.addEvent(event);
    }

    /**
     * Clears the event pools.
     */
    public void clearEventPools() {
        touchEvents.clear();
    }

    /**
     *
     * @return The pool of touch events.
     */
    public Vector<MotionEvent> getTouchEvents() {
        return touchEvents.getEvents();
    }

}
