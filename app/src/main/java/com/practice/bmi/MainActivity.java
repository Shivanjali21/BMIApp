package com.practice.bmi;

import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;

public class MainActivity extends AppCompatActivity {

    AppCompatEditText edtWeight, edtHeightFt, edtHeightIn;
    AppCompatTextView tvResult;
    AppCompatButton btnCalculateBMI;
    LinearLayout llMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        edtWeight = findViewById(R.id.edtWeight);
        edtHeightFt = findViewById(R.id.edtHeightFt);
        edtHeightIn = findViewById(R.id.edtHeightIn);
        tvResult = findViewById(R.id.tvResult);
        btnCalculateBMI = findViewById(R.id.btnCalculateBMI);
        llMain = findViewById(R.id.llMain);

        btnCalculateBMI.setOnClickListener( v-> {
           int wt = Integer.parseInt(edtWeight.getText().toString());
           int ft = Integer.parseInt(edtHeightFt.getText().toString());
           int in = Integer.parseInt(edtHeightIn.getText().toString());

           int totalIn = ft * 12 + in;
           double totalCM = totalIn * 2.53;
           double totalIM = totalCM / 100;
           double bmi = wt/(totalIM*totalIM);
           if(bmi > 25) {
             tvResult.setText("You are overweight");
             llMain.setBackgroundColor(getResources().getColor(R.color.color_ow));
             tvResult.setTextColor(getResources().getColor(R.color.black));
           }else if(bmi < 18){
               tvResult.setText("You are underweight");
               llMain.setBackgroundColor(getResources().getColor(R.color.color_uw));
               tvResult.setTextColor(getResources().getColor(R.color.white));
           }else {
               tvResult.setText("You are healthy");
               llMain.setBackgroundColor(getResources().getColor(R.color.color_h));
               tvResult.setTextColor(getResources().getColor(R.color.white));
           }
        });
    }
}