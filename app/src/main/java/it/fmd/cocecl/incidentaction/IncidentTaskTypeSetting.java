package it.fmd.cocecl.incidentaction;


import android.app.Activity;
import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.CardView;
import android.widget.CheckBox;
import android.widget.TextView;

import it.fmd.cocecl.R;
import it.fmd.cocecl.dataStorage.IncidentData;

import static android.graphics.Color.BLUE;

public class IncidentTaskTypeSetting {

    IncidentData id = new IncidentData();

    public Activity activity;

    public IncidentTaskTypeSetting(Activity _activity) {

        this.activity = _activity;
    }

    public void noTask() {

        TextView incitext = (TextView) activity.findViewById(R.id.textView114);

            incitext.setText("derzeit kein Auftrag/Einsatz");
    }

    /**
     * Sets CardView Backgroundcolor, denpending on TASKTYPE
     */

    public void tasktypeCardcolor() {

        String tasktype = id.getTasktype();

        CardView estatcard = (CardView) activity.findViewById(R.id.estatcard);

        if (tasktype.equals("EINSATZ")) {

            estatcard.setBackgroundColor(Color.parseColor("#BBDEFB"));
        }

        if (tasktype == "AUFTRAG") {

            estatcard.setBackgroundColor(Color.parseColor("#B2DFDB"));
        }

        if (tasktype == "STANDORTVERLEGUNG") {

            estatcard.setBackgroundColor(Color.parseColor("#B39DDB"));
        }

        if (tasktype == "TRANSFER") {

            estatcard.setBackgroundColor(Color.parseColor("#B2DFDB"));
        }
    }

    public void tasktypeemergencytabcolor() {
        TabLayout tabLayout = (TabLayout) activity.findViewById(R.id.tab_layout);
        tabLayout.setBackgroundColor(Color.parseColor("#1565C0"));
    }

    // set emergency/priority //
    public void setEmergency() {

        CheckBox checkBox = (CheckBox) activity.findViewById(R.id.emergencyBox);
        checkBox.setEnabled(true);
        checkBox.setClickable(false);
        checkBox.setTextColor(BLUE);
    }
}
