package it.fmd.cocecl.incidentaction;


import android.app.Activity;
import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import it.fmd.cocecl.MainActivity;
import it.fmd.cocecl.R;

import static android.graphics.Color.BLUE;

public class IncidentTaskTypeSetting {

    public Activity activity;

    public IncidentTaskTypeSetting(Activity _activity) {

        this.activity = _activity;
    }

    public void noTask() {

        TextView nbrtext = (TextView) activity.findViewById(R.id.textView113);
        TextView incitext = (TextView) activity.findViewById(R.id.textView114);

        if (nbrtext.getText().toString().trim().length() > 0) {

            incitext.setText("derzeit kein Auftrag/Einsatz");

        }
    }

    /**
     * Sets CardView Backgroundcolor, denpending on TASKTYPE
     */

    public void tasktypeCardcolor() {

        TextView tasktype = (TextView) activity.findViewById(R.id.tasktype);
        CardView estatcard = (CardView) activity.findViewById(R.id.estatcard);

        if (tasktype.getText() == "EINSATZ") {

            estatcard.setBackgroundColor(Color.parseColor("#BBDEFB"));
        }

        if (tasktype.getText() == "AUFTRAG") {

            estatcard.setBackgroundColor(Color.parseColor("#B2DFDB"));
        }

        if (tasktype.getText() == "STANDORTVERLEGUNG") {

            estatcard.setBackgroundColor(Color.parseColor("#B39DDB"));
        }

        if (tasktype.getText() == "TRANSFER") {

            estatcard.setBackgroundColor(Color.parseColor("#B2DFDB"));
        }
    }

    public void tasktypeemergencytabcolor() {
        TabLayout tabLayout = (TabLayout) activity.findViewById(R.id.tab_layout);
        tabLayout.setBackgroundColor(Color.parseColor("#1565C0"));
    }

    // set emergency/priority //
    public void setEmergency(View v) {

        CheckBox checkBox = (CheckBox) activity.findViewById(R.id.emergencyBox);
        checkBox.setEnabled(true);
        checkBox.setClickable(false);
        checkBox.setTextColor(BLUE);
    }
}
