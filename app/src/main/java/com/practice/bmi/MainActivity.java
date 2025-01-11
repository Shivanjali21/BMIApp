package com.practice.bmi;

import android.app.Activity;
import android.content.Context;
import android.inputmethodservice.Keyboard;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

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
        setContentView(R.layout.activity_main);

        edtWeight = findViewById(R.id.edtWeight);
        edtHeightFt = findViewById(R.id.edtHeightFt);
        edtHeightIn = findViewById(R.id.edtHeightIn);
        tvResult = findViewById(R.id.tvResult);
        llMain = findViewById(R.id.llMain);
        btnCalculateBMI = findViewById(R.id.btnCalculateBMI);
        btnCalculateBMI.setOnClickListener(v -> {
           hideKeyboardFrom(this,btnCalculateBMI);
           int wt = Integer.parseInt(edtWeight.getText().toString());
           int ft = Integer.parseInt(edtHeightFt.getText().toString());
           int in = Integer.parseInt(edtHeightIn.getText().toString());

           int totalIn = ft * 12 + in;
           double totalCm = totalIn * 2.53;
           double totalM = totalCm/100;
           double bmi = wt/(totalM*totalM);

           if(bmi > 25){
             tvResult.setText("You are overweight!");
             llMain.setBackgroundColor(getResources().getColor(R.color.color_ow));
           } else if (bmi < 18) {
              tvResult.setText("You are underweight!");
               llMain.setBackgroundColor(getResources().getColor(R.color.color_uw));
               tvResult.setTextColor(getResources().getColor(R.color.black));
           } else {
              tvResult.setText("You are healthy!");
              llMain.setBackgroundColor(getResources().getColor(R.color.color_h));
           }

        });
    }

    public static void hideKeyboardFrom(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}