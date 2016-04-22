package it.fmd.cocecl.gcm;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.Context;
import android.support.v4.app.NotificationCompat;

import it.fmd.cocecl.R;

public class GCM_MessageNotification {

    public void messageNotification(Context context, String messagetitle, String messagebody) {

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.mipmap.coceclstbar)
                .setContentTitle(messagetitle)
                .setContentText(messagebody)
                .setOngoing(false);

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(992, mBuilder.build());
    }
}
