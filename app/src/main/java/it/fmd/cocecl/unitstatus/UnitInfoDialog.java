package it.fmd.cocecl.unitstatus;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.widget.CheckBox;
import android.widget.TextView;

import it.fmd.cocecl.R;
import it.fmd.cocecl.dataStorage.UnitData;

public class UnitInfoDialog {

    public Activity activity;

    public UnitInfoDialog(Activity _activity) {

        this.activity = _activity;
    }

    TextView vplatetv;
    TextView unametv;
    TextView fkenntv;

    CheckBox mobileunitcb;
    CheckBox mdunitcb;

    public void unitinfo() {

        String uname = UnitData.getInstance().getUnitname();
        String unumber = UnitData.getInstance().getUnitnumber();
        String uvplate = UnitData.getInstance().getVehicleplate();

        Boolean mdunit = UnitData.getInstance().getMdunit();
        Boolean mobileunit = UnitData.getInstance().getMobileunit();

        // Create custom dialog object
        final Dialog dialog = new Dialog(activity);
        // Include dialog.xml file
        dialog.setContentView(R.layout.unit_info_layout);
        // Set dialog title
        dialog.setTitle("Einheit - Information");

        //Set TextViews
        vplatetv = (TextView) dialog.findViewById(R.id.textView100);
        vplatetv.setText(uvplate);

        unametv = (TextView) dialog.findViewById(R.id.textView99);
        unametv.setText(uname);

        mobileunitcb = (CheckBox) dialog.findViewById(R.id.checkBox18);
        mobileunitcb.setChecked(mobileunit);

        mdunitcb = (CheckBox) dialog.findViewById(R.id.checkBox19);
        mdunitcb.setChecked(mdunit);

        fkenntv = (TextView) dialog.findViewById(R.id.textView102);
        fkenntv.setText(unumber);

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
