package it.fmd.cocecl.unitstatus;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;

import it.fmd.cocecl.R;

public class UnitInfoDialog {

    public Activity activity;

    public UnitInfoDialog(Activity _activity) {

        this.activity = _activity;
    }

    public void unitinfo() {

        // Create custom dialog object
        final Dialog dialog = new Dialog(activity);
        // Include dialog.xml file
        dialog.setContentView(R.layout.unit_info_layout);
        // Set dialog title
        dialog.setTitle("Einheit - Information");


        dialog.show();
/*
            Button declineButton = (Button) dialog.findViewById(R.id.declineButton);
            // if decline button is clicked, close the custom dialog
            declineButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Close dialog
                    dialog.dismiss();
                }
            });*/
    }
}
