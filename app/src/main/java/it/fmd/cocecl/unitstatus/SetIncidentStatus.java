package it.fmd.cocecl.unitstatus;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

import it.fmd.cocecl.MainActivity;
import it.fmd.cocecl.R;

import static android.graphics.Color.YELLOW;


public class SetIncidentStatus extends MainActivity {

    @Override
    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_main);

    }
/*
        final RelativeLayout deliveryloclayout = (RelativeLayout) getLayoutInflater().inflate(R.layout.fragment_deliveryloc, null);

        final Calendar cal = Calendar.getInstance(TimeZone.getDefault());
        final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

        final Button button41 = (Button) findViewById(R.id.button41);
        final TextView textView83 = (TextView) findViewById(R.id.statusView);
        final TextView textView85 = (TextView) findViewById(R.id.textView85);
        final TextView aofield = (TextView) deliveryloclayout.findViewById(R.id.aofield);

        final TextView statuserror = (TextView) findViewById(R.id.textView129);

        Button button10 = (Button) findViewById(R.id.button10);
        Button button11 = (Button) findViewById(R.id.button11);
        Button button13 = (Button) findViewById(R.id.button13);
        Button button46 = (Button) findViewById(R.id.button46);

        AlertDialog.Builder dlgBuilder = new AlertDialog.Builder(getApplicationContext());
*/

    // Status Button incidentFragment //
    // change unit status
    //TODO: set fragments on status change?? / sync with server

    public void qu() {

        final Button button41 = (Button) findViewById(R.id.button41);
        final TextView textView83 = (TextView) findViewById(R.id.statusView);
        final TextView textView85 = (TextView) findViewById(R.id.textView85);

        final Calendar cal = Calendar.getInstance(TimeZone.getDefault());
        final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

        // QU Einsatz übernehmen
        button41.setEnabled(false);
        button41.setClickable(false);
        button41.setBackgroundColor(YELLOW);
        button41.setText(R.string.zbo);
        textView83.setText("QU");
        textView85.setText(sdf.format(cal.getTime()));
        button41.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_fast_forward_black_18dp, 0, 0, 0);

        Handler h = new Handler();
        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                button41.setEnabled(true);
                button41.setClickable(true);
                button41.setBackgroundResource(android.R.drawable.btn_default);
            }
        }, 3000);

    }

    public void st1() {

    }

    public void st2() {

    }

    public void st3() {

        final Button button41 = (Button) findViewById(R.id.button41);
        final TextView textView83 = (TextView) findViewById(R.id.statusView);
        final TextView textView85 = (TextView) findViewById(R.id.textView85);

        final Calendar cal = Calendar.getInstance(TimeZone.getDefault());
        final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

        // ZBO
        button41.setEnabled(false);
        button41.setClickable(false);
        button41.setBackgroundColor(YELLOW);
        button41.setText(R.string.abo);
        textView83.setText("ZBO");
        textView85.setText(sdf.format(cal.getTime()));
        button41.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_skip_next_black_18dp, 0, 0, 0);

        Handler h = new Handler();
        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                button41.setEnabled(true);
                button41.setClickable(true);
                button41.setBackgroundResource(android.R.drawable.btn_default);
            }
        }, 10000);

    }

    public void st4() {

        final Button button41 = (Button) findViewById(R.id.button41);
        final TextView textView83 = (TextView) findViewById(R.id.statusView);
        final TextView textView85 = (TextView) findViewById(R.id.textView85);

        final Calendar cal = Calendar.getInstance(TimeZone.getDefault());
        final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

        Button button10 = (Button) findViewById(R.id.button10);
        Button button11 = (Button) findViewById(R.id.button11);
        Button button13 = (Button) findViewById(R.id.button13);
        Button button46 = (Button) findViewById(R.id.button46);

        // ABO
        button41.setEnabled(false);
        button41.setClickable(false);
        button41.setBackgroundColor(YELLOW);
        button41.setText(R.string.zao);
        textView83.setText("ABO");
        textView85.setText(sdf.format(cal.getTime()));
        button41.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_call_made_black_18dp, 0, 0, 0);

        button10.setEnabled(true);
        button10.setClickable(true);
        button11.setEnabled(true);
        button11.setClickable(true);
        button13.setEnabled(true);
        button13.setClickable(true);
        button46.setEnabled(true);
        button46.setClickable(true);

        Handler h = new Handler();
        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                button41.setEnabled(true);
                button41.setClickable(true);
                button41.setBackgroundResource(android.R.drawable.btn_default);
            }
        }, 10000);

    }

    public void st5() {
        // Selektivruf
    }

    public void st6() {
        // NEB
    }

    public void st7() {

        final RelativeLayout deliveryloclayout = (RelativeLayout) getLayoutInflater().inflate(R.layout.fragment_deliveryloc, null);

        final Calendar cal = Calendar.getInstance(TimeZone.getDefault());
        final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

        final Button button41 = (Button) findViewById(R.id.button41);
        final TextView textView83 = (TextView) findViewById(R.id.statusView);
        final TextView textView85 = (TextView) findViewById(R.id.textView85);
        final TextView aofield = (TextView) deliveryloclayout.findViewById(R.id.aofield);

        final TextView statuserror = (TextView) findViewById(R.id.textView129);

        Button button10 = (Button) findViewById(R.id.button10);
        Button button11 = (Button) findViewById(R.id.button11);
        Button button13 = (Button) findViewById(R.id.button13);
        Button button46 = (Button) findViewById(R.id.button46);

        // ZAO
        if (aofield.getText().toString().trim().length() > 0) {
            button41.setEnabled(true);
            button41.setClickable(true);
            button41.setBackgroundColor(YELLOW);
            button41.setText(R.string.aao);
            textView83.setText("ZAO");
            textView85.setText(sdf.format(cal.getTime()));
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
            //Toast.makeText(MainActivity.this, "Kein Abgabeort eingetragen", Toast.LENGTH_LONG).show();
            statuserror.setText("Kein Abgabeort eingetragen");
            statuserror.setVisibility(View.VISIBLE);
        }

        Handler h = new Handler();
        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                button41.setEnabled(true);
                button41.setClickable(true);
                button41.setBackgroundResource(android.R.drawable.btn_default);
                statuserror.setText("");
                statuserror.setVisibility(View.GONE);
            }
        }, 10000);

    }

    public void st8() {

        final Calendar cal = Calendar.getInstance(TimeZone.getDefault());
        final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

        final Button button41 = (Button) findViewById(R.id.button41);
        final TextView textView83 = (TextView) findViewById(R.id.statusView);
        final TextView textView85 = (TextView) findViewById(R.id.textView85);

        // AAO
        button41.setEnabled(false);
        button41.setClickable(false);
        button41.setBackgroundColor(YELLOW);
        button41.setText(R.string.eb);
        textView83.setText("AAO");
        textView85.setText(sdf.format(cal.getTime()));
        button41.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_play_arrow_black_18dp, 0, 0, 0);

        Handler h = new Handler();
        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                button41.setEnabled(true);
                button41.setClickable(true);
                button41.setBackgroundResource(android.R.drawable.btn_default);
            }
        }, 10000);

    }

    public void endIncident() {

        final Calendar cal = Calendar.getInstance(TimeZone.getDefault());
        final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

        final Button button41 = (Button) findViewById(R.id.button41);
        final TextView textView83 = (TextView) findViewById(R.id.statusView);
        final TextView textView85 = (TextView) findViewById(R.id.textView85);

        // Einsatz abschliessen
        button41.setEnabled(false);
        button41.setClickable(false);
        button41.setBackgroundColor(YELLOW);
        button41.setText("Einsatz abschliessen");
        textView83.setText("ENDE");
        textView85.setText(sdf.format(cal.getTime()));

        Handler h = new Handler();
        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                button41.setEnabled(true);
                button41.setClickable(true);
                button41.setBackgroundResource(android.R.drawable.btn_default);
            }
        }, 10000);


    }

    public void removeIncident() {

        // TODO Remove Incident (& deliveryloc) Tab, when Incident accomplished
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.incidentfraglayout);
        if (fragment != null)
            getSupportFragmentManager().beginTransaction().remove(fragment).commit();

    }

    public void st9() {
        // AD
    }
}

