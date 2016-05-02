package it.fmd.cocecl.gcm;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.gcm.GcmPubSub;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.iid.InstanceID;

import java.io.IOException;

import it.fmd.cocecl.R;

public class SubscribeToTopicGCM {

    private GcmPubSub pubSub;
    private String token;
    private final String TAG = "SubscribeToTopicGCM";

    /**
     * RegisterGCM subscribeToTopic method
     */

    private class SubscribeToTopicTask extends AsyncTask<String, Void, Boolean> {

        private String topic;

        private static final String TAG = "SubscribeToTopicGCM";
        private static final String TOPIC_PREFIX = "/topics/";

        @Override
        protected Boolean doInBackground(String... params) {
            if (params.length > 0) {
                topic = params[0];
                try {
                    pubSub.subscribe(token, topic, null);
                    return true;
                } catch (IOException e) {
                    Log.e(TAG, "Subscribe to topic failed", e);
                }
            }
            return false;
        }

        @Override
        protected void onPostExecute(Boolean succeed) {
            if (succeed) {
                //TODO: show subscription on account view (showuserDialog, MainActivity)
                //updateUI("Subscribed to topic: " + topic, true);
            } else {
                //updateUI("Subscription to topic failed: " + topic, false);
            }
        }

        private void updateUI(String status, boolean registered) {
            // Set status and token text
            /*
            statusView.setText(status);
            registrationTokenFieldView.setText(token);

            // Button enabling
            registerButton.setEnabled(!registered);
            unregisterButton.setEnabled(registered);

            // Upstream message enabling
            upstreamMessageField.setEnabled(registered);
            sendButton.setEnabled(registered);

            // Topic subscription enabled
            topicField.setEnabled(registered);
            subscribeTopicButton.setEnabled(registered);
            */
        }
    }

    /**
     * Subscribe to a topic
     */
    public void subscribeToTopic(Context context, String topic) {
        GcmPubSub pubSub = GcmPubSub.getInstance(context);
        InstanceID instanceID = InstanceID.getInstance(context);
        String token = null;
        try {
            token = instanceID.getToken(context.getString(R.string.gcm_defaultSenderId),
                    GoogleCloudMessaging.INSTANCE_ID_SCOPE, null);
            if (token != null) {
                pubSub.subscribe(token, "/topics/" + topic, null);
                Log.e(TAG, "Subscribed to topic: " + topic);
            } else {
                Log.e(TAG, "error: gcm registration id is null");
            }
        } catch (IOException e) {
            Log.e(TAG, "Topic subscribe error. Topic: " + topic + ", error: " + e.getMessage());
            Toast.makeText(context, "Topic subscribe error. Topic: " + topic + ", error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public void unsubscribeFromTopic(Context context, String topic) {
        GcmPubSub pubSub = GcmPubSub.getInstance(context);
        InstanceID instanceID = InstanceID.getInstance(context);
        String token = null;
        try {
            token = instanceID.getToken(context.getString(R.string.gcm_defaultSenderId),
                    GoogleCloudMessaging.INSTANCE_ID_SCOPE, null);
            if (token != null) {
                pubSub.unsubscribe(token, "");
                Log.e(TAG, "Unsubscribed from topic: " + topic);
            } else {
                Log.e(TAG, "error: gcm registration id is null");
            }
        } catch (IOException e) {
            Log.e(TAG, "Topic unsubscribe error. Topic: " + topic + ", error: " + e.getMessage());
            Toast.makeText(context, "Topic subscribe error. Topic: " + topic + ", error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    // subscribing to global topic
    /*
    private void subscribeToGlobalTopic() {
        Intent intent = new Intent(this, GcmIntentService.class);
        intent.putExtra(GcmIntentService.KEY, GcmIntentService.SUBSCRIBE);
        intent.putExtra(GcmIntentService.TOPIC, Config.TOPIC_GLOBAL);
        startService(intent);
    }
    */
}
