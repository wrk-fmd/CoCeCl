package it.fmd.cocecl.IncidentAction;

import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NotificationCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

import it.fmd.cocecl.MainActivity;
import it.fmd.cocecl.R;

public class NewIncident extends MainActivity {

    View.OnClickListener mOnClickListener;
    // Alerts/server sync on new incident
    // set tabhost open new fragment
    // alertdialog builder

    public void onNewIncident() {

        //TODO: GCMNotificationIntentService gcm class

    }

    /**
     * Show Updates from CallCenter during active Incident on Snackbar, onclick to dismiss
     */

    //Coordinator Layout for SnackBar//
    CoordinatorLayout coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorLayout);

    // change incident information/ snackbar update
    public void onIncidentUpdate() {

        final Snackbar snackbar = Snackbar
                .make(coordinatorLayout, "EINSATZ Update!", Snackbar.LENGTH_INDEFINITE)
                .setAction("OK", mOnClickListener);

        snackbar.setActionTextColor(Color.GREEN);

        View sbView = snackbar.getView();
        TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(Color.YELLOW);

        snackbar.show();

        mOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                snackbar.dismiss();
            }
        };
    }
    
    // Alert Push Notification Manager //
    // alerts on incoming incident, gcm server!!
    //
    //TODO: later: function for new incident alert !!!
    public void taskalert() {

        final LayoutInflater inci = getLayoutInflater();
        final View incidentView = inci.inflate(R.layout.fragment_incident, null);
        final MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.alert_newemergency);

        final TextView bofield = (TextView) incidentView.findViewById(R.id.bofield);
        final TextView brfrfield = (TextView) incidentView.findViewById(R.id.brfrfield);
        final TextView infofield = (TextView) incidentView.findViewById(R.id.infofield);

        final Button button41 = (Button) incidentView.findViewById(R.id.button41);
        final TextView textView83 = (TextView) incidentView.findViewById(R.id.statusView);
        final TextView textView85 = (TextView) incidentView.findViewById(R.id.textView85);

        final Calendar cal = Calendar.getInstance(TimeZone.getDefault());
        final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

        AlertDialog.Builder dlgBuilder = new AlertDialog.Builder(getApplicationContext());
        dlgBuilder.setCancelable(false);
        dlgBuilder.setTitle("EINSATZ/AUFTRAG");
        dlgBuilder.setMessage("Addresse\nBerufungsgrund");

        //dlgBuilder.setView(R.id.alertbox_layout);

        dlgBuilder.setPositiveButton("Einsatz übernehmen", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                button41.setText(R.string.zbo);
                textView83.setText("QU");
                textView85.setText(sdf.format(cal.getTime()));
                button41.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_fast_forward_black_18dp, 0, 0, 0);
                mp.stop();

                Toast.makeText(getApplicationContext(), "Einsatz übernommen", Toast.LENGTH_SHORT).show();
            }
        });

        AlertDialog alert = dlgBuilder.create();
        alert.show();

/*
                String title = bgfield.getText().toString().trim();
                String subject = bofield.getText().toString().trim();
                String body = infofield.getText().toString().trim();
*/
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(getApplicationContext());
                                /*
                                .setSmallIcon(R.drawable.ic_warning_black_18dp)
                                .setContentTitle(infofield.getText().toString())
                                .setContentText(brfrfield.getText().toString());
                                //.setContentIntent(pendingIntent); // below Gingerbread
*/
        mBuilder.setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_ALL)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.drawable.ic_warning_black_18dp)
                .setTicker("Alert new Incident")
                .setContentTitle("Alert + Code")
                .setContentText("AddressStreet")
                .setDefaults(Notification.DEFAULT_LIGHTS | Notification.DEFAULT_SOUND)
                .setContentIntent(contentIntent)
                .setContentInfo("Detail Code");

        // AlertSound
        //mBuilder.setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM));

        mp.setLooping(true);
        mp.start();

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1, mBuilder.build());
    }
}
