package com.example.pacmancontroller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements SensorEventListener{

    TextView textView1;
    TextView textView2;
    TextView textView3;

    private SensorManager sensorManager;
    private Sensor accelerometer;

    private Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView1 = findViewById(R.id.textView3);
        textView2 = findViewById(R.id.textView2);
        textView3 = findViewById(R.id.textView);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                //updateValues();
            }
        }, 0, 100);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener((SensorEventListener) this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];

            // Aquí puedes utilizar los valores de x, y, z para realizar la lógica necesaria en tu proyecto
            textView1.setText("X: " + String.format("%.2f", x));
            textView2.setText("Y: " + String.format("%.2f", y));
            textView3.setText("Z: " + String.format("%.2f", z));

        }
    }

    /*private void updateValues() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                textView1.setText("X: ");
                textView2.setText("Y: ");
                textView3.setText("Z: ");
            }
        });
    }*/

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // No necesitas implementar este método para utilizar el acelerómetro
    }
}