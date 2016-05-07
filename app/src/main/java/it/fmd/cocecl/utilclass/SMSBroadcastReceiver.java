package it.fmd.cocecl.utilclass;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

import it.fmd.cocecl.APPConstants;
import it.fmd.cocecl.dataStorage.GCMMessage;
import it.fmd.cocecl.dataStorage.MsgArray;
import it.fmd.cocecl.dataStorage.SMSData;
import it.fmd.cocecl.incidentaction.SMS_Alert;

public class SMSBroadcastReceiver extends BroadcastReceiver {

    public static final String SMS_BUNDLE = "pdus";
    final SmsManager sms = SmsManager.getDefault();

    private String smsBody;
    private String smsAddress;

    SMSData sd = new SMSData();

    public void onReceive(Context context, Intent intent) {

        Bundle intentExtras = intent.getExtras();
        if (intentExtras != null) {
            Object[] sms = (Object[]) intentExtras.get(SMS_BUNDLE);
            String smsMessageStr = "";
            for (int i = 0; i < sms.length; ++i) {
                SmsMessage smsMessage = SmsMessage.createFromPdu((byte[]) sms[i]);

                smsBody = smsMessage.getMessageBody();
                smsAddress = smsMessage.getOriginatingAddress();

                smsMessageStr += "SMS From: " + smsAddress + "\n";
                smsMessageStr += smsBody + "\n";

                SMS_Alert.getSmsDetails(smsAddress, smsBody);

                sd.setSmscontent(smsBody);
            }


            /* If SMS sent by MCM store msg in ArrayList until app closed */
            if (smsAddress.equals(APPConstants.MLS_SMS_GATEWAY)) {
                storeSMS();
            }
        }
    }

    private void storeSMS() {
        // write msg in arraylist
        GetDateTime dateTime = new GetDateTime();

        String messagesender = "MLS";
        String messagetitle = "SMS";

        GCMMessage message = new GCMMessage();
        message.setId(messagesender);
        message.setTitle(messagetitle);
        message.setMessage(smsBody);
        message.setCreatedAt(dateTime.getcurrentTime());

        MsgArray.gcmMessages.add(message);
    }
}
