package com.pt.jeulicense.handlers;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class AccelerometerHandler implements SensorEventListener {

    private float axisX;
    private float axisY;
    private float axisZ;

    public AccelerometerHandler(Activity activity) {
        SensorManager sensorManager = (SensorManager)activity.getSystemService(Context.SENSOR_SERVICE );
        Sensor accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(this, accelerometer, 1);
    }

    public synchronized void onSensorChanged(SensorEvent event) {
        this.axisX =  event.values[0];
        this.axisY =  event.values[1];
        this.axisZ =  event.values[2];
    }

    public synchronized float getAxisX() {
        return axisX;
    }

    public synchronized float getAxisY() {
        return axisY;
    }

    public synchronized float getAxisZ() {
        return this.axisZ;
    }

    public void onAccuracyChanged(Sensor sensor, int accuracy) { }
}
