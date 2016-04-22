package it.fmd.cocecl.gcm;

import android.os.AsyncTask;
import android.util.Log;

import com.google.android.gms.gcm.GcmPubSub;

import java.io.IOException;

public class SubscribeToTopicGCM {

    private GcmPubSub pubSub;
    private String token;

    /**
     * RegisterGCM subscribeToTopic method
     */

    private class SubscribeToTopicTask extends AsyncTask<String, Void, Boolean> {

        private String topic;

        private static final String TAG = "MyActivity";
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
}
