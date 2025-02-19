package com.practice.bmi;

import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DBHelper dbHelper = new DBHelper(this);
        /*
        dbHelper.addContact("Ram", "1234567890");
        dbHelper.addContact("Lakshman", "0123456789");
        dbHelper.addContact("Aman", "0123456789");
        dbHelper.addContact("Jasprit", "0123456789");*/

        ArrayList<ContactModel> contactModels = dbHelper.fetchContact();
        for(int i = 0; i < contactModels.size(); i++) {
          Log.d("MainActivity", "Name: " + contactModels.get(i).name + "Mobile: " + contactModels.get(i).mobile_no);
        }
    }
}