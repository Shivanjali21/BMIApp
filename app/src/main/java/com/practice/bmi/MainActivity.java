package com.practice.bmi;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    Spinner spinner;
    AutoCompleteTextView acTv;

    ArrayList<String> arrName = new ArrayList<>();
    ArrayList<String> arrIdProof = new ArrayList<>();
    ArrayList<String> arrLanguages = new ArrayList<>();
    private static final String CHANNEL_ID = "0";
    private static final int Notification_ID = 1;
    private static final int REQ_CODE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*Notification*/
        Drawable drawable = ResourcesCompat.getDrawable(getResources(), R.drawable.android, null);
        BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
        Bitmap icon = bitmapDrawable.getBitmap();

        NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Notification notification;

        /*For Opening Activity on Click of Notification */
        Intent iNotify = new Intent(getApplicationContext(), MainActivity.class);
        iNotify.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pi = PendingIntent.getActivity(this, REQ_CODE, iNotify, PendingIntent.FLAG_IMMUTABLE);
        /* End */

        /* Big Picture Style */
        Notification.BigPictureStyle bigPictureNotify = new Notification.BigPictureStyle()
                .bigPicture(((BitmapDrawable) (ResourcesCompat.getDrawable(getResources(), R.drawable.android, null))).getBitmap())
                .bigLargeIcon(icon)
                .setBigContentTitle("Img sent by Android!")
                .setSummaryText("Img Message");
        /* End Of */

        /* Inbox style */
        Notification.InboxStyle inboxStyle = new Notification.InboxStyle()
                .addLine("A")
                .addLine("B")
                .addLine("C")
                .addLine("D")
                .addLine("E")
                .addLine("F")
                .addLine("G")
                .addLine("H")
                .addLine("I")
                .addLine("J")
                .addLine("K")
                .addLine("L")
                .setBigContentTitle("Full Message")
                .setSummaryText("From Android!!");

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.O) {
            notification = new Notification.Builder(this)
                    .setLargeIcon(icon)
                    .setSmallIcon(R.drawable.android)
                    .setContentText("Android Message")
                    .setContentTitle("Hi, I'm Android")
                    .setChannelId(CHANNEL_ID)
                    .setOngoing(true)
                    .setAutoCancel(false)
                    .setContentIntent(pi)
                    .setStyle(inboxStyle)
                    .build();
            nm.createNotificationChannel(new NotificationChannel(CHANNEL_ID, "CHANNEL_NAME", NotificationManager.IMPORTANCE_HIGH));
        } else {
            notification = new Notification.Builder(this)
                    .setLargeIcon(icon)
                    .setSmallIcon(R.drawable.android)
                    .setContentText("Android Message")
                    .setContentTitle("Hi, I'm Android")
                    .setOngoing(true)
                    .setAutoCancel(false)
                    .setContentIntent(pi)
                    .setStyle(inboxStyle)
                    .build();
        }
        nm.notify(Notification_ID, notification);

        /*End Of Notification*/

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