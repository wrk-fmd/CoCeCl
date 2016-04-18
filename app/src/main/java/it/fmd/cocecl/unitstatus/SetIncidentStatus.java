package it.fmd.cocecl.unitstatus;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

import it.fmd.cocecl.R;
import it.fmd.cocecl.dataStorage.IncidentData;
import it.fmd.cocecl.gmapsnav.StAskOnLocChange;


public class SetIncidentStatus implements View.OnClickListener {

    IncidentData id = new IncidentData();

    public Activity activity;

    public SetIncidentStatus(Activity _activity) {

        this.activity = _activity;
    }

    final String status = id.getIncistatus();
    final String aoaddress = id.getAoaddress();

    final Calendar cal = Calendar.getInstance(TimeZone.getDefault());
    final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss", Locale.GERMAN);

    @Override
    public void onClick(View v) {

        final Button button41 = (Button) activity.findViewById(R.id.button41);
        final TextView textView85 = (TextView) activity.findViewById(R.id.textView85);
        final TextView statusView = (TextView) activity.findViewById(R.id.statusView);


        AlertDialog.Builder dlgBuilder = new AlertDialog.Builder(activity);
        dlgBuilder.setTitle(R.string.stwe);
        dlgBuilder.setMessage(button41.getText().toString());
        dlgBuilder.setCancelable(false);

        dlgBuilder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                button41.setEnabled(false);
                button41.setClickable(false);
                //button41.setBackgroundColor(YELLOW);
                button41.setBackground(activity.getResources().getDrawable(R.drawable.button_yellow_pressed));

                // QU Einsatz übernehmen
                if (statusView.getText().equals("") || statusView.getText().equals("EB")) {

                    qu();
                    id.setIncistatus("QU");
                    textView85.setText(sdf.format(cal.getTime()));

                    // ZBO
                } else if (statusView.getText().equals("QU")) {

                    st3();
                    id.setIncistatus("ZBO");
                    textView85.setText(sdf.format(cal.getTime()));

                    // ABO
                } else if (statusView.getText().equals("ZBO")) {

                    st4();
                    id.setIncistatus("ABO");
                    textView85.setText(sdf.format(cal.getTime()));

                    //Set BO location coordiantes
                    StAskOnLocChange saolc = new StAskOnLocChange();
                    saolc.locA();

                    // ZAO
                } else if (statusView.getText().equals("ABO")) {

                    st7();

                    if (aoaddress != null) {
                        id.setIncistatus("ZAO");
                        textView85.setText(sdf.format(cal.getTime()));
                    }
                    // AAO
                } else if (statusView.getText().equals("ZAO")) {

                    st8();
                    id.setIncistatus("AAO");
                    textView85.setText(sdf.format(cal.getTime()));

                    // Einsatz abschliessen
                } else if (statusView.getText().equals("AAO")) {


                    endIncident();
                    textView85.setText(sdf.format(cal.getTime()));
                    //IncidentData.getInstance().setIncistatus("");

                } else if (statusView.getText().equals("ENDE")) {

                    removeIncident();
                    id.setIncistatus("auto_detach");
                }

                //Reset pressed button
                Handler h = new Handler();
                h.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        resetButton();
                    }
                }, 5000);
            }
        });

        dlgBuilder.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        AlertDialog alert = dlgBuilder.create();
        alert.show();
    }


    // Status Button IncidentFragment //
    // change unit status
    //TODO: set fragments on status change?? / sync with server

    public void qu() {

        final Button button41 = (Button) activity.findViewById(R.id.button41);
        final TextView statusView = (TextView) activity.findViewById(R.id.statusView);

        // QU Einsatz übernehmen

        button41.setText(R.string.zbo);
        statusView.setText("QU");
        button41.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_fast_forward_black_18dp, 0, 0, 0);

    }

    public void st1() {

    }

    public void st2() {

    }

    public void st3() {

        final Button button41 = (Button) activity.findViewById(R.id.button41);
        final TextView statusView = (TextView) activity.findViewById(R.id.statusView);

        // ZBO

        button41.setText(R.string.abo);
        statusView.setText("ZBO");
        button41.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_skip_next_black_18dp, 0, 0, 0);

    }

    public void st4() {

        final Button button41 = (Button) activity.findViewById(R.id.button41);
        final TextView statusView = (TextView) activity.findViewById(R.id.statusView);

        Button button10 = (Button) activity.findViewById(R.id.button10);
        Button button11 = (Button) activity.findViewById(R.id.button11);
        Button button13 = (Button) activity.findViewById(R.id.button13);
        Button button46 = (Button) activity.findViewById(R.id.button46);

        // ABO

        button41.setText(R.string.zao);
        statusView.setText("ABO");
        button41.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_call_made_black_18dp, 0, 0, 0);

        button10.setEnabled(true);
        button10.setClickable(true);
        button11.setEnabled(true);
        button11.setClickable(true);
        button13.setEnabled(true);
        button13.setClickable(true);
        button46.setEnabled(true);
        button46.setClickable(true);

    }

    public void st5() {
        // Selektivruf
    }

    public void st6() {
        // NEB
    }

    public void st7() {

        final Button button41 = (Button) activity.findViewById(R.id.button41);
        final TextView statusView = (TextView) activity.findViewById(R.id.statusView);
        final TextView statuserror = (TextView) activity.findViewById(R.id.textView129);

        Button button10 = (Button) activity.findViewById(R.id.button10);
        Button button11 = (Button) activity.findViewById(R.id.button11);
        Button button13 = (Button) activity.findViewById(R.id.button13);
        Button button46 = (Button) activity.findViewById(R.id.button46);

        // ZAO
        if (aoaddress != null) {
            button41.setEnabled(true);
            button41.setClickable(true);
            //button41.setBackgroundColor(YELLOW);
            button41.setBackground(activity.getResources().getDrawable(R.drawable.button_yellow_pressed));
            button41.setText(R.string.aao);
            statusView.setText("ZAO");
            button41.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_local_hospital_black_18dp, 0, 0, 0);

            button10.setEnabled(false);
            button10.setClickable(false);
            button11.setEnabled(false);
            button11.setClickable(false);
            button13.setEnabled(false);
            button13.setClickable(false);
            button46.setEnabled(false);
            button46.setClickable(false);
        } else {
            button41.setEnabled(false);
            button41.setClickable(false);
            statuserror.setText("Kein Abgabeort eingetragen");
            statuserror.setVisibility(View.VISIBLE);
        }

        Handler h = new Handler();
        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                statuserror.setText("");
                statuserror.setVisibility(View.GONE);
            }
        }, 3000);

    }

    public void st8() {

        final Button button41 = (Button) activity.findViewById(R.id.button41);
        final TextView statusView = (TextView) activity.findViewById(R.id.statusView);

        // AAO

        button41.setText(R.string.eb);
        statusView.setText("AAO");
        button41.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_play_arrow_black_18dp, 0, 0, 0);

    }

    public void endIncident() {

        final Button button41 = (Button) activity.findViewById(R.id.button41);
        final TextView statusView = (TextView) activity.findViewById(R.id.statusView);

        // Einsatz abschliessen

        button41.setText("Einsatz abschliessen");
        statusView.setText("ENDE");

        removeIncident();

    }

    public void removeIncident() {

        // TODO Remove Incident (& deliveryloc) Tab, when Incident accomplished
        /*
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.incidentfraglayout);
        if (fragment != null)
            getSupportFragmentManager().beginTransaction().remove(fragment).commit();
*/
    }

    public void st9() {
        // AD
    }

    public void resetButton() {

        final Button button41 = (Button) activity.findViewById(R.id.button41);
        button41.setEnabled(true);
        button41.setClickable(true);
        //button41.setBackgroundResource(android.R.drawable.btn_default);
        button41.setBackground(activity.getResources().getDrawable(R.drawable.custom_button_normal));
    }
}

