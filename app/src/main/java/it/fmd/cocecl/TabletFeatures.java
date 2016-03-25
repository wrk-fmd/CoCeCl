package it.fmd.cocecl;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.telephony.TelephonyManager;
import android.widget.Button;

public class TabletFeatures {

    public Activity activity;

    public TabletFeatures(Activity _activity) {

        this.activity = _activity;
    }

    // Toolbar
    //TODO aditional toolbar textfields
    // Radio stream

    // disable phonecall buttons if no gsm module available (Tablet)
    //TODO set phonecall buttons gone

    public void checkGSM() {
        Button button17 = (Button) this.activity.findViewById(R.id.button17);
        Button button43 = (Button) this.activity.findViewById(R.id.button43);
        Button button47 = (Button) this.activity.findViewById(R.id.button47);

        TelephonyManager manager = (TelephonyManager) this.activity.getSystemService(Context.TELEPHONY_SERVICE);

        if (manager.getPhoneType() == TelephonyManager.PHONE_TYPE_GSM) {

            button17.setEnabled(true);
            button43.setEnabled(true);
            button47.setEnabled(true);

            button17.setClickable(true);
            button43.setClickable(true);
            button47.setClickable(true);

        } else {

            button17.setEnabled(false);
            button43.setEnabled(false);
            button47.setEnabled(false);

            button17.setClickable(false);
            button43.setClickable(false);
            button47.setClickable(false);
        }
    }

    // enable PATMAN Documentation System on Tablet

    public void patman_enable() {

        Button button21 = (Button) this.activity.findViewById(R.id.button21);

        if ((this.activity.getResources().getConfiguration().screenLayout &
                Configuration.SCREENLAYOUT_SIZE_MASK) >=
                Configuration.SCREENLAYOUT_SIZE_LARGE) {

            button21.setEnabled(true);
            button21.setClickable(true);

        } else {

            button21.setEnabled(false);
            button21.setClickable(false);

        }
    }
}
