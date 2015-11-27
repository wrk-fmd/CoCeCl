package it.fmd.cocecl;

import android.graphics.Color;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.TextView;

public class IncidentAction extends MainActivity {

    View.OnClickListener mOnClickListener;
    // Alerts/server sync on new incident
    // set tabhost open new fragment
    // alertdialog builder

    public void onNewIncident() {

        //TODO: GCMNotificationIntentService gcm class

    }


    //Coordinator Layout for SnackBar//
    CoordinatorLayout coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorLayout);

    // change incident information/ snackbar update
    public void onchangeIncident() {

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
}
