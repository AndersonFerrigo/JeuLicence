package com.pt.jeulicense.handlers;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class ReverseAccelerometerHandler implements SensorEventListener {
    private float reverseAxisX;
    private float reverseAxisY;
    private float reverseAxisZ;

    public ReverseAccelerometerHandler(Activity activity) {
        SensorManager sensorManager = (SensorManager)activity.getSystemService(Context.SENSOR_SERVICE );
        Sensor reverseAccelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(this, reverseAccelerometer, 1);
    }

    public synchronized void onSensorChanged(SensorEvent event) {
        this.reverseAxisX = - event.values[0];
        this.reverseAxisY = - event.values[1];
        this.reverseAxisZ = - event.values[2];
    }

    public synchronized float getReverseAxisX() {
        return reverseAxisX;
    }

    public synchronized float getReverseAxisY() {
        return reverseAxisY;
    }

    public synchronized float getReverseAxisZ() {
        return this.reverseAxisZ;
    }

    public void onAccuracyChanged(Sensor sensor, int accuracy) {   }
}
