package com.practice.bmi;

import android.content.Context;
import android.os.Bundle;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;
import java.util.Objects;

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
           int wt = Integer.parseInt(Objects.requireNonNull(edtWeight.getText()).toString());
           int ft = Integer.parseInt(Objects.requireNonNull(edtHeightFt.getText()).toString());
           int in = Integer.parseInt(Objects.requireNonNull(edtHeightIn.getText()).toString());

           int totalIn = ft * 12 + in;
           double totalCM = totalIn * 2.53;
           double totalIM = totalCM / 100;
           double bmi = wt/(totalIM*totalIM);
           if(bmi > 25) {
             tvResult.setText(String.format("Your BMI is " + bmi + "\n" + getResources().getText(R.string.ow)));
             llMain.setBackgroundColor(getResources().getColor(R.color.color_ow));
             tvResult.setTextColor(getResources().getColor(R.color.black));
           }else if(bmi < 18){
               tvResult.setText(String.format("Your BMI is " + bmi + "\n" + getResources().getText(R.string.uw)));
               llMain.setBackgroundColor(getResources().getColor(R.color.color_uw));
               tvResult.setTextColor(getResources().getColor(R.color.white));
           }else {
               tvResult.setText(String.format("Your BMI is " + bmi + "\n" + getResources().getText(R.string.h)));
               llMain.setBackgroundColor(getResources().getColor(R.color.color_h));
               tvResult.setTextColor(getResources().getColor(R.color.white));
           }
           closeKeyboard();
        });
    }

    private void closeKeyboard() {
      InputMethodManager inputManager = (InputMethodManager)this.getSystemService(Context.INPUT_METHOD_SERVICE);
      inputManager.hideSoftInputFromWindow(Objects.requireNonNull(this.getCurrentFocus()).getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }
}