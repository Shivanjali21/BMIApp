package com.practice.bmi;

import static android.app.PendingIntent.FLAG_IMMUTABLE;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import com.practice.bmi.databinding.ActivityMainBinding;

import java.util.Objects;

import www.sanju.motiontoast.MotionToast;
import www.sanju.motiontoast.MotionToastStyle;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding activityMainBinding;
    AlarmManager alarmManager;
    private static final int ALARM_REQ_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = activityMainBinding.getRoot();
        setContentView(view);

        activityMainBinding.btnSetAlarm.setOnClickListener(v -> startAlert());
    }

    public void startAlert() {
        if (Objects.requireNonNull(activityMainBinding.etTimer.getText()).toString().isEmpty()) {
            MotionToast.Companion.createColorToast(MainActivity.this, "", "Please enter Time.", MotionToastStyle.ERROR, MotionToast.GRAVITY_BOTTOM, MotionToast.SHORT_DURATION, ResourcesCompat.getFont(this, R.font.atkinson_medium));
        } else {
            int x = Integer.parseInt(activityMainBinding.etTimer.getText().toString());
            Intent iBoardCast = new Intent(this, AlarmBoardCastReceiver.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(
                    this.getApplicationContext(), ALARM_REQ_CODE, iBoardCast, FLAG_IMMUTABLE);
            alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
            alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()
                    + (x * 1000), pendingIntent);

            activityMainBinding.etTimer.setText("");
            hideKeyboardFrom(MainActivity.this, activityMainBinding.getRoot());
            MotionToast.Companion.createColorToast(MainActivity.this, "", "Alarm set in " + x + " seconds", MotionToastStyle.INFO, MotionToast.GRAVITY_BOTTOM, MotionToast.SHORT_DURATION, ResourcesCompat.getFont(this, R.font.atkinson_medium));
        }
    }

    public static void hideKeyboardFrom(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}

