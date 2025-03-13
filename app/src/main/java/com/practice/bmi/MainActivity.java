package com.practice.bmi;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.practice.bmi.databinding.ActivityMainBinding;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding activityMainBinding;
    private final int CAMERA_REQ_CODE = 1;
    private final int GALLERY_REQ_CODE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = activityMainBinding.getRoot();
        setContentView(view);

        activityMainBinding.btnOpenCamera.setOnClickListener(v -> {
            Intent iCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(iCamera, CAMERA_REQ_CODE);
        });

        activityMainBinding.btnOpenGallery.setOnClickListener(v-> {
           Intent iGallery = new Intent(Intent.ACTION_PICK);
           iGallery.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
           startActivityForResult(iGallery, GALLERY_REQ_CODE);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK){
           if(requestCode == CAMERA_REQ_CODE){
             assert data != null;
             Bitmap imgBitmap = (Bitmap) Objects.requireNonNull(data.getExtras()).get("data");
             activityMainBinding.ivCameraGallery.setImageBitmap(imgBitmap);
           }else if(requestCode == GALLERY_REQ_CODE){
             activityMainBinding.ivCameraGallery.setImageURI(data.getData());
           }
        }
    }
}
