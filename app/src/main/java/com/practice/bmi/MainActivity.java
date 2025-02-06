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
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

public class MainActivity extends AppCompatActivity {

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
    }
}