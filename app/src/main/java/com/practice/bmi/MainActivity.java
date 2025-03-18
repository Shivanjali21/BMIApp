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
    Sensor proxySensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = activityMainBinding.getRoot();
        setContentView(view);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        if(sensorManager != null){
          proxySensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
          if(proxySensor != null){
            sensorManager.registerListener(this, proxySensor, SensorManager.SENSOR_DELAY_NORMAL);
          }
        }else {
            Toast.makeText(this, "Sensor is not supported in this device", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
       if(event.sensor.getType() == Sensor.TYPE_PROXIMITY){
        activityMainBinding.tvSensorValue.setText(String.format("X: %s ", event.values[0]));
        if(event.values[0] > 0){
          Toast.makeText(this, "Object is Far.", Toast.LENGTH_SHORT).show();
        }else {
          Toast.makeText(this, "Object is Near.", Toast.LENGTH_SHORT).show();
        }
      }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {}
}
