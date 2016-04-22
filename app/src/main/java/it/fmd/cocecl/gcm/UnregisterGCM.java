package it.fmd.cocecl.gcm;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.gcm.GcmPubSub;
import com.google.android.gms.gcm.GoogleCloudMessaging;

import java.io.IOException;

import it.fmd.cocecl.APPConstants;
import it.fmd.cocecl.R;

public class UnregisterGCM {

    private Context context;

    public UnregisterGCM(Context context) {
        this.context = context;
    }

    private static final String TAG = "MyActivity";
    private static final String TOPIC_PREFIX = "/topics/";
    private GoogleCloudMessaging gcm;
    private GcmPubSub pubSub;
    private String token;

    public void unregisterClient() {
        gcm = GoogleCloudMessaging.getInstance(context);
        pubSub = GcmPubSub.getInstance(context);

        String senderId = context.getString(R.string.gcm_defaultSenderId);
        if (!("".equals(senderId))) {
            // Create the bundle for registration with the server.
            Bundle registration = new Bundle();
            registration.putString(APPConstants.ACTION, APPConstants.UNREGISTER_CLIENT);
            registration.putString(APPConstants.REGISTRATION_TOKEN, token);

            try {
                gcm.send(getServerUrl(senderId),
                        String.valueOf(System.currentTimeMillis()), registration);
            } catch (IOException e) {
                Log.e(TAG, "Message failed", e);
                //updateUI("Unregistration FAILED", true);
            }
        }
    }

    public static String getServerUrl(String senderId) {
        return senderId + "@gcm.googleapis.com";
    }
}
