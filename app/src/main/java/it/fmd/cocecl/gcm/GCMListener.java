package it.fmd.cocecl.gcm;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AlertDialog;
import android.text.Html;
import android.text.TextUtils;
import android.util.Config;
import android.util.Log;
import android.widget.ListView;

import com.google.android.gms.gcm.GcmListenerService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

import it.fmd.cocecl.MainActivity;
import it.fmd.cocecl.R;
import it.fmd.cocecl.contentviews.GCMMessageAdapter;
import it.fmd.cocecl.dataStorage.GCMMessage;
import it.fmd.cocecl.utilclass.CheckBackground;

public class GCMListener extends GcmListenerService {

    private static final String TAG = "GCMListener";

    private String messagesender, messagetype, messagetitle, messagebody;

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
        messagetype = data.getString("mtype");
        messagetitle = data.getString("mtitle");
        messagebody = data.getString("mbody");

        Log.d(TAG, "From: " + from);
        Log.d(TAG, "Messagetype: " + messagetype);
        Log.d(TAG, "Messagebody: " + messagebody);


        if (from.startsWith("/topics/")) {
            // TODO: get topic from server and subscribe
            // message received from some topic.
        } else {
            // normal downstream message.
        }

        if (!CheckBackground.isAppIsInBackground(getApplicationContext())) {

            // app is in foreground, broadcast the push message
            //TODO: show as alertdialog
            /*
            Intent pushNotification = new Intent(Config.PUSH_NOTIFICATION);
            pushNotification.putExtra("message", message);
            LocalBroadcastManager.getInstance(this).sendBroadcast(pushNotification);
            */
            // play notification sound
            /*
            NotificationUtils notificationUtils = new NotificationUtils();
            notificationUtils.playNotificationSound();
            */
        } else {

            //TODO: create notification or snackbar

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

            // store message & show in listview dialog
            //storeMessage();
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

    public static void showMessageAlert(Context _context, String title, String message, int icon, DialogInterface.OnClickListener ackHandler) {
        AlertDialog.Builder builder = new AlertDialog.Builder(_context);
        builder.setTitle(title);
        builder.setMessage(Html.fromHtml(message));
        builder.setCancelable(false);
        builder.setPositiveButton("Acknowledged", ackHandler);
        builder.setIcon(icon);
        builder.show();
    }

    private ArrayList<GCMMessage> messageArrayList;

    public void storeMessage() {

        // Construct the data source
        ArrayList<GCMMessage> messageArrayList = new ArrayList<GCMMessage>();
        // Create the adapter to convert the array to views
        GCMMessageAdapter adapter = new GCMMessageAdapter(this, messageArrayList);
        // Attach the adapter to a ListView
        //ListView listView = (ListView) findViewById(R.id.listView3);
        //listView.setAdapter(adapter);

        final Calendar cal = Calendar.getInstance(TimeZone.getDefault());
        final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss", Locale.GERMAN);
        String currenttime = (sdf.format(cal.getTime()));

        GCMMessage message = new GCMMessage();
        message.setId(messagesender);
        message.setTitle(messagetitle);
        message.setMessage(messagebody);
        message.setCreatedAt(currenttime);

        messageArrayList.add(message);
    }
}
