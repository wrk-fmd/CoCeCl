package it.fmd.cocecl.gcm;

import android.content.Context;
import android.content.SharedPreferences;

public class GCMStoreMessages {

    private String TAG = GCMStoreMessages.class.getSimpleName();

    // Shared Preferences
    SharedPreferences gcm_messages;

    // Editor for Shared preferences
    SharedPreferences.Editor editor;

    // Context
    Context _context;

    // Shared pref mode
    int PRIVATE_MODE = 0;

    // Sharedpref file name
    private static final String PREF_NAME = "gcm_messages";

    // Shared Preferences Keys
    private static final String KEY_NOTIFICATIONS = "notifications";

    // Constructor
    public GCMStoreMessages(Context context) {
        this._context = context;
        gcm_messages = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = gcm_messages.edit();
    }

    public void addNotification(String notification) {

        // get old notifications
        String oldNotifications = getNotifications();

        if (oldNotifications != null) {
            oldNotifications += "|" + notification;
        } else {
            oldNotifications = notification;
        }

        editor.putString(KEY_NOTIFICATIONS, oldNotifications);
        editor.commit();
    }

    public String getNotifications() {
        return gcm_messages.getString(KEY_NOTIFICATIONS, null);
    }

    public void clear() {
        editor.clear();
        editor.commit();
    }
}
