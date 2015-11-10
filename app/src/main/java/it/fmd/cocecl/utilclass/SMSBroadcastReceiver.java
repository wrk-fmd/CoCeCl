package it.fmd.cocecl.utilclass;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.widget.Toast;

import it.fmd.cocecl.MainActivity;

public class SMSBroadcastReceiver extends BroadcastReceiver {

    final SmsManager sms = SmsManager.getDefault();
    MainActivity ourSMS;

    public static final String SMS_BUNDLE = "pdus";

    public void onReceive(Context context, Intent intent) {

        Bundle intentExtras = intent.getExtras();
        if (intentExtras != null) {
            Object[] sms = (Object[]) intentExtras.get(SMS_BUNDLE);
            String smsMessageStr = "";
            for (int i = 0; i < sms.length; ++i) {
                SmsMessage smsMessage = SmsMessage.createFromPdu((byte[]) sms[i]);

                String smsBody = smsMessage.getMessageBody().toString();
                String address = smsMessage.getOriginatingAddress();

                smsMessageStr += "SMS From: " + address + "\n";
                smsMessageStr += smsBody + "\n";

                MainActivity.getSmsDetails(address, smsBody);
            }

            Toast.makeText(context, smsMessageStr, Toast.LENGTH_LONG).show();
/*
            //this will update the UI with message
            SmsActivity inst = SmsActivity.instance();
            inst.updateList(smsMessageStr);
*/
        }
    }
}
