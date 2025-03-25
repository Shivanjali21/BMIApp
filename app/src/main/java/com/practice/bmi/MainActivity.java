package com.practice.bmi;

import static android.app.PendingIntent.FLAG_IMMUTABLE;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.practice.bmi.databinding.ActivityMainBinding;
import java.util.Objects;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityMainBinding activityMainBinding;
    AlarmManager alarmManager;
    String stringTime;
    private static final int ALARM_REQ_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = activityMainBinding.getRoot();
        setContentView(view);

        activityMainBinding.btnSetAlarm.setOnClickListener(this);
        alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnSetAlarm) {

            stringTime = Objects.requireNonNull(activityMainBinding.etTimer.getText()).toString();
            if(stringTime.isEmpty()){
                Toast.makeText(this, "Enter Valid Time", Toast.LENGTH_SHORT).show();
                return;
            }else {
               activityMainBinding.etTimer.setText("");
                // Close the soft keyboard
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null) {
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }


            int timer = Integer.parseInt(stringTime);
            long triggerTime = System.currentTimeMillis() + (timer * 1000L);
            Intent intentBoardCast = new Intent(MainActivity.this, AlarmBoardCastReceiver.class);
            PendingIntent pi = PendingIntent.getBroadcast(MainActivity.this, ALARM_REQ_CODE, intentBoardCast, FLAG_IMMUTABLE);

            alarmManager.set(AlarmManager.RTC_WAKEUP,triggerTime, pi);
            Toast.makeText(this, "Alarm is set for " + timer + " seconds from now", Toast.LENGTH_SHORT).show();
            startService(new Intent(MainActivity.this, AlarmBoardCastReceiver.class));
        } else {
           stopService(new Intent(MainActivity.this, AlarmBoardCastReceiver.class));
        }
    }
}
