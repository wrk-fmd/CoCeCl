package it.fmd.cocecl.incidentaction;


import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.widget.TextView;

import it.fmd.cocecl.MainActivity;
import it.fmd.cocecl.R;

public class IncidentTaskTypeSetting extends MainActivity {

    public void noTask() {

        TextView nbrtext = (TextView) findViewById(R.id.textView113);
        TextView incitext = (TextView) findViewById(R.id.textView114);

        if (nbrtext.getText().toString().trim().length() > 0) {

            incitext.setText("derzeit kein Auftrag/Einsatz");

        }
    }

    /**
     * Sets CardView Backgroundcolor, denpending on TASKTYPE
     */

    public void tasktypeCardcolor() {

        TextView tasktype = (TextView) findViewById(R.id.tasktype);
        CardView estatcard = (CardView) findViewById(R.id.estatcard);

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
}
