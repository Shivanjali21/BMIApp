package com.practice.bmi;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import www.sanju.motiontoast.MotionToast;
import www.sanju.motiontoast.MotionToastStyle;

public class MainActivity extends AppCompatActivity {

    Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btnRestart;
    LinearLayout llMain;
    int flag = 0;
    int count = 0;
    String b1, b2, b3, b4, b5, b6, b7, b8, b9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init() {
        btn1 = findViewById(R.id.btn1);
        btn1.setOnClickListener(this::onButtonCheck);
        btn2 = findViewById(R.id.btn2);
        btn2.setOnClickListener(this::onButtonCheck);
        btn3 = findViewById(R.id.btn3);
        btn3.setOnClickListener(this::onButtonCheck);
        btn4 = findViewById(R.id.btn4);
        btn4.setOnClickListener(this::onButtonCheck);
        btn5 = findViewById(R.id.btn5);
        btn5.setOnClickListener(this::onButtonCheck);
        btn6 = findViewById(R.id.btn6);
        btn6.setOnClickListener(this::onButtonCheck);
        btn7 = findViewById(R.id.btn7);
        btn7.setOnClickListener(this::onButtonCheck);
        btn8 = findViewById(R.id.btn8);
        btn8.setOnClickListener(this::onButtonCheck);
        btn9 = findViewById(R.id.btn9);
        btn9.setOnClickListener(this::onButtonCheck);

        btnRestart = findViewById(R.id.btnRestart);
        btnRestart.setOnClickListener(this::onButtonCheck);
    }

    public void onButtonCheck(View view) {
        //Check current button hit.
        Button currentBtn = (Button) view;

        if (currentBtn.getText().toString().isEmpty()) {
            count++;

            if (flag == 0) {
                currentBtn.setText("X");
                flag = 1;
            } else {
                currentBtn.setText("O");
                flag = 0;
            }


            if (count > 4) {

                b1 = btn1.getText().toString();
                b2 = btn2.getText().toString();
                b3 = btn3.getText().toString();
                b4 = btn4.getText().toString();
                b5 = btn5.getText().toString();
                b6 = btn6.getText().toString();
                b7 = btn7.getText().toString();
                b8 = btn8.getText().toString();
                b9 = btn9.getText().toString();


                //Conditions
                if (b1.equals(b2) && b2.equals(b3) && !b1.isEmpty()) {
                    //1
                    Toast.makeText(this, String.format("Winner is " + b1), Toast.LENGTH_SHORT).show();
                    newGame();
                } else if (b4.equals(b5) && b5.equals(b6) && !b4.isEmpty()) {
                    //2
                    Toast.makeText(this, String.format("Winner is " + b4), Toast.LENGTH_SHORT).show();
                    newGame();
                } else if (b7.equals(b8) && b8.equals(b9) && !b7.isEmpty()) {
                    //3
                    Toast.makeText(this, String.format("Winner is " + b7), Toast.LENGTH_SHORT).show();
                    newGame();
                } else if (b1.equals(b4) && b4.equals(b7) && !b1.isEmpty()) {
                    //4
                    Toast.makeText(this, String.format("Winner is " + b1), Toast.LENGTH_SHORT).show();
                    newGame();
                } else if (b2.equals(b5) && b5.equals(b8) && !b2.isEmpty()) {
                    //5
                    Toast.makeText(this, String.format("Winner is " + b2), Toast.LENGTH_SHORT).show();
                    newGame();
                } else if (b3.equals(b6) && b6.equals(b9) && !b3.isEmpty()) {
                    //6
                    Toast.makeText(this, String.format("Winner is " + b3), Toast.LENGTH_SHORT).show();
                    newGame();
                } else if (b1.equals(b5) && b5.equals(b9) && !b1.isEmpty()) {
                    //7
                    Toast.makeText(this, String.format("Winner is " + b1), Toast.LENGTH_SHORT).show();
                    newGame();
                } else if (b3.equals(b5) && b5.equals(b7) && !b3.isEmpty()) {
                    //8
                    Toast.makeText(this, String.format("Winner is " + b3), Toast.LENGTH_SHORT).show();
                    newGame();
                } else if (count == 9) {
                    MotionToast.Companion.createToast(this, "", "Game is draw", MotionToastStyle.INFO, MotionToast.GRAVITY_BOTTOM, MotionToast.LONG_DURATION, ResourcesCompat.getFont(this, www.sanju.motiontoast.R.font.helvetica_regular));
                    newGame();
                }
            }
        }

        if(view.getId() == R.id.btnRestart){
          newGame();
        }

    }

    private void newGame() {
        btn1.setText("");
        btn2.setText("");
        btn3.setText("");
        btn4.setText("");
        btn5.setText("");
        btn6.setText("");
        btn7.setText("");
        btn8.setText("");
        btn9.setText("");
        flag = 0;
        count = 0;
    }
}