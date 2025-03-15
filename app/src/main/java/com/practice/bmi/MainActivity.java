package com.practice.bmi;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.MediaController;

import androidx.appcompat.app.AppCompatActivity;
import com.practice.bmi.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding activityMainBinding;
    String localPath, onlinePath;
    MediaController mc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = activityMainBinding.getRoot();
        setContentView(view);

        localPath = "android.resource://"+getPackageName()+"/raw/android_video_player";
        onlinePath = "";
        Uri pathParse = Uri.parse(localPath);
        activityMainBinding.videoScreen.setVideoURI(pathParse);
        activityMainBinding.videoScreen.start();
        //activityMainBinding.videoScreen.setVideoPath(localPath); //one option

        mc = new MediaController(this);
        activityMainBinding.videoScreen.setMediaController(mc);
        mc.setAnchorView(activityMainBinding.videoScreen);
    }
}
