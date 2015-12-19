package it.fmd.cocecl.IncidentAction;

import android.app.Application;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.widget.TextView;

import it.fmd.cocecl.MainActivity;
import it.fmd.cocecl.R;

/**
 * Class used when Alert comes via SMS, if no network connection available
 */

public class SMS_Alert extends MainActivity {

    private CoordinatorLayout coordinatorLayout;

    //SMS RECEIVER
    TextView SMSm;
    static String phoneNumber1;
    static String SMSBody1;

    public static void getSmsDetails(String phoneNumber, String SMSBody) {
        phoneNumber1 = phoneNumber;
        SMSBody1 = SMSBody;
    }

    // SMS Alert //

    public void setSMS() {
        //SMS Alert// write content to incident fields

        if(phoneNumber1 == "+144") {
            SMSm = (TextView) findViewById(R.id.bofield);

            SMSm.setText(SMSBody1);

            Snackbar snackbar = Snackbar.make(coordinatorLayout, SMSBody1, Snackbar.LENGTH_LONG);

            snackbar.show();
        }
    }
}
