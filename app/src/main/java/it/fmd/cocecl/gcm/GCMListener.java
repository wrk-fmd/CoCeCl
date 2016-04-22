package it.fmd.cocecl.gcm;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.android.gms.gcm.GcmListenerService;

import it.fmd.cocecl.MainActivity;

public class GCMListener extends GcmListenerService {

    private static final String TAG = "GCMListener";

    /**
     * Called when message is received.
     *
     * @param from SenderID of the sender.
     * @param data Data bundle containing message data as key/value pairs.
     *             For Set of keys use data.keySet().
     */
    // [START receive_message]
    @Override
    public void onMessageReceived(String from, Bundle data) {
        //String message = data.getString("message");
        String messagetype = data.getString("mtype");
        String messagetitle = data.getString("mtitle");
        String messagebody = data.getString("mbody");

        Log.d(TAG, "From: " + from);
        Log.d(TAG, "Messagetype: " + messagetype);
        Log.d(TAG, "Messagebody: " + messagebody);

        if (from.startsWith("/topics/")) {
            // TODO: get topic from server and subscribe
            // message received from some topic.
        } else {
            // normal downstream message.
        }

        // [START_EXCLUDE]
        /**
         * Production applications would usually process the message here.
         * Eg: - Syncing with server.
         *     - Store message in local database.
         *     - Update UI.
         */

        /**
         * In some cases it may be useful to show a notification indicating to the user
         * that a message was received.
         */
        //sendNotification(message);
        // [END_EXCLUDE]

        if (messagetype == "typemessage") {
            GCM_MessageDialog gcmmd = new GCM_MessageDialog();
            gcmmd.showMessageDialog(getApplicationContext(), messagebody);
        }

        if (messagetype == "WakeUPCall") {
            //TODO estabish server connection
        }

        if (messagetype == "typenotification") {
            sendNotification(messagetitle, messagebody);

            GCM_MessageNotification gcmmn = new GCM_MessageNotification();
            //gcmmn.messageNotification(getApplicationContext(), messagetitle, messagebody);
        }
    }
    // [END receive_message]

    /**
     * Create and show a simple notification containing the received GCM message.
     *
     * @param messagebody GCM message received.
     */
    private void sendNotification(String messagetitle, String messagebody) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                PendingIntent.FLAG_ONE_SHOT);

        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                //.setSmallIcon(R.drawable.ic_stat_ic_notification)
                .setContentTitle(messagetitle)
                .setContentText(messagebody)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
    }
}
