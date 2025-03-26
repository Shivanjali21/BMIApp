package com.practice.bmi;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;

public class SMSReceiver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {

        Bundle bundle = intent.getExtras();
        Object[] smsObject = (Object[]) bundle.get("pdus");
        for (Object obj : smsObject) {
            SmsMessage smsMessage = SmsMessage.createFromPdu((byte[]) obj);
            String mobile = smsMessage.getOriginatingAddress();
            String msg = smsMessage.getMessageBody();

            Log.d("TAG", "MsgDetails: " + "Mobile No." + mobile + "Message is " + msg);

            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage("+91 9876543210", null, "Hello Android",
                    null, null);


        }
    }
}
