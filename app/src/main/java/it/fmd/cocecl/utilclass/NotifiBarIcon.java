package it.fmd.cocecl.utilclass;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.Context;
import android.support.v4.app.NotificationCompat;

import it.fmd.cocecl.R;

public class NotifiBarIcon {

    public Activity activity;

    public NotifiBarIcon(Activity _activity) {

        this.activity = _activity;
    }

    private int NOTIFICATION_ID = 991;

    public void StatusBarAppIcon() {

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(activity)
                .setSmallIcon(R.mipmap.coceclstbar)
                .setContentTitle("CoCeCl")
                .setContentText("keine Einsatz")
                .setOngoing(true);

        NotificationManager notificationManager = (NotificationManager) activity.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(NOTIFICATION_ID, mBuilder.build());
    }

    public void removeSBAI() {
        NotificationManager notificationManager = (NotificationManager) activity.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.cancel(NOTIFICATION_ID);
    }
}