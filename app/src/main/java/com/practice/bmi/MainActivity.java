package com.practice.bmi;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.practice.bmi.databinding.ActivityMainBinding;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding activityMainBinding;
    MediaPlayer mp;
    String localPath, onlinePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = activityMainBinding.getRoot();
        setContentView(view);

        mp = new MediaPlayer();
        mp.setAudioStreamType(AudioManager.STREAM_MUSIC);

        localPath = "android.resource://"+getPackageName()+"/raw/relaxing_piano";
        onlinePath = "";
        Uri pathParse = Uri.parse(localPath);

        try {
            mp.setDataSource(this, pathParse);
            mp.prepare();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        activityMainBinding.btnPlay.setOnClickListener(v -> mp.start());

        activityMainBinding.btnPause.setOnClickListener(v-> mp.pause());

        activityMainBinding.btnStop.setOnClickListener(v -> {
          mp.pause();
          mp.seekTo(0);
        });
    }
}
