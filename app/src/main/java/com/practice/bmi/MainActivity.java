package com.practice.bmi;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.practice.bmi.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private ActivityMainBinding activityMainBinding;
    SensorManager sensorManager;
    Sensor acclerSensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = activityMainBinding.getRoot();
        setContentView(view);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        if(sensorManager != null){
          acclerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
          if(acclerSensor != null){
            sensorManager.registerListener(this, acclerSensor, SensorManager.SENSOR_DELAY_NORMAL);
          }
        }else {
            Toast.makeText(this, "Sensor is not supported in this device", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
      if(event.sensor.getType() == Sensor.TYPE_ACCELEROMETER){
       activityMainBinding.tvSensorValue.setText(String.format("X: %s, Y: %s, Z: %s", event.values[0], event.values[1], event.values[2]));
      }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {}
}
