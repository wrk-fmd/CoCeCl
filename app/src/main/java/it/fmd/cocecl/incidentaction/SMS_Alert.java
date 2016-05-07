package it.fmd.cocecl.incidentaction;

import android.app.Activity;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.widget.TextView;

import it.fmd.cocecl.APPConstants;
import it.fmd.cocecl.R;

/**
 *  Used when Alert sent via SMS, if no network connection available
 */

public class SMS_Alert extends Activity {

    static String phoneNumber1;
    static String SMSBody1;

    //SMS RECEIVER
    TextView SMSm;
    private CoordinatorLayout coordinatorLayout;

    public static void getSmsDetails(String phoneNumber, String SMSBody) {
        phoneNumber1 = phoneNumber;
        SMSBody1 = SMSBody;
    }

    // SMS Alert //

    public void setSMS() {
        //SMS Alert// write content to incident fields

        if (phoneNumber1.equals(APPConstants.MLS_SMS_GATEWAY)) {
            SMSm = (TextView) findViewById(R.id.bofield);

            SMSm.setText(SMSBody1);

            Snackbar snackbar = Snackbar.make(coordinatorLayout, SMSBody1, Snackbar.LENGTH_LONG);

            snackbar.show();
        }
    }
}
