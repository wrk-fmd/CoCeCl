package it.fmd.cocecl.utilclass;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;

import it.fmd.cocecl.dataStorage.SMSData;
import it.fmd.cocecl.incidentaction.SMS_Alert;

public class SMSBroadcastReceiver extends BroadcastReceiver {

    public static final String SMS_BUNDLE = "pdus";
    final SmsManager sms = SmsManager.getDefault();

    SMSData sd = new SMSData();

    public void onReceive(Context context, Intent intent) {

        Bundle intentExtras = intent.getExtras();
        if (intentExtras != null) {
            Object[] sms = (Object[]) intentExtras.get(SMS_BUNDLE);
            String smsMessageStr = "";
            for (int i = 0; i < sms.length; ++i) {
                SmsMessage smsMessage = SmsMessage.createFromPdu((byte[]) sms[i]);

                String smsBody = smsMessage.getMessageBody();
                String address = smsMessage.getOriginatingAddress();

                smsMessageStr += "SMS From: " + address + "\n";
                smsMessageStr += smsBody + "\n";

                SMS_Alert.getSmsDetails(address, smsBody);

                sd.setSmscontent(smsBody);
            }

            //Toast.makeText(context, smsMessageStr, Toast.LENGTH_LONG).show();
/*
            //this will update the UI with message
            SmsActivity inst = SmsActivity.instance();
            inst.updateList(smsMessageStr);
*/
        }
    }
}
