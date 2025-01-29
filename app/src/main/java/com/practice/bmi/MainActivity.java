package com.practice.bmi;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    Spinner spinner;
    AutoCompleteTextView acTv;

    ArrayList<String> arrName = new ArrayList<>();
    ArrayList<String> arrIdProof = new ArrayList<>();
    ArrayList<String> arrLanguages = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        spinner = findViewById(R.id.spinner);
        acTv = findViewById(R.id.aCTv);

        /* List View */
        arrName.add("Rohit");
        arrName.add("Shubham");
        arrName.add("Aditya");
        arrName.add("Virat");
        arrName.add("Jasprit");
        arrName.add("Taran");
        arrName.add("SKY");
        arrName.add("NKR");
        arrName.add("Yuvraj");
        arrName.add("MSD");
        arrName.add("GG");
        arrName.add("Rahul Dravid");
        arrName.add("Md. Siraj");
        arrName.add("Md. Shami");
        arrName.add("YJB");
        arrName.add("Dhruv Jurel");
        arrName.add("Kapil Dev");
        arrName.add("Ajit Agarkar");
        arrName.add("Hardik Pandya");
        arrName.add("Axar Patel");
        arrName.add("Ravindra Jadeja");
        arrName.add("R. Ashwin");

        ArrayAdapter<String> nameAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, arrName);
        listView.setAdapter(nameAdapter);
        listView.setOnItemClickListener((parent, view, position, id) -> {
            if(position == 0) {
              Toast.makeText(MainActivity.this, "Clicked First Item", Toast.LENGTH_SHORT).show();
            }
        });
        /* End Of View */


        /* Spinner */
        arrIdProof.add("Class 10th Marksheets");
        arrIdProof.add("Class 12th Marksheets");
        arrIdProof.add("Aadhaar Card");
        arrIdProof.add("Voter Card");
        arrIdProof.add("Ration Card");
        arrIdProof.add("Driving License");
        arrIdProof.add("Passport");
        arrIdProof.add("PAN Card");
        ArrayAdapter<String> idProofAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, arrIdProof);
        spinner.setAdapter(idProofAdapter);
        /* End Of Spinner */


        /* AutoCompleteTextView */
        arrLanguages.add("ASP.Net");
        arrLanguages.add("C");
        arrLanguages.add("C++");
        arrLanguages.add("C#");
        arrLanguages.add("Dart");
        arrLanguages.add("Go");
        arrLanguages.add("Go Lang");
        arrLanguages.add("HTML");
        arrLanguages.add("Java");
        arrLanguages.add("JavaScript");
        arrLanguages.add("Kotlin");
        arrLanguages.add("Python");
        arrLanguages.add("Php");
        arrLanguages.add("Swift");
        ArrayAdapter<String> languageAdapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, arrLanguages);
        acTv.setAdapter(languageAdapter);
        acTv.setThreshold(1);
        /* End Of AutoCompleteTextView */
    }

}