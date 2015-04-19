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
 * Created by williamlea on 29/01/15.
 */
public class InputManager implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor accelerometer;

    public static final float[] accelAxes = new float[3];
    public static final EventPool<MotionEvent> touchEvents = new EventPool<>();

    public InputManager(Activity activity) {
        sensorManager = (SensorManager) activity.getSystemService(Context.SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_GAME);

    }

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

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    public static float getAccelX() {
        return accelAxes[0];
    }

    public static float getAccelY() {
        return accelAxes[1];
    }

    public static float getAccelZ() {
        return accelAxes[2];
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
