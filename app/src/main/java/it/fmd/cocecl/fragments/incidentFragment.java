package it.fmd.cocecl.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.TimeZone;

import it.fmd.cocecl.R;
import it.fmd.cocecl.contentviews.AssignedUnits;
import it.fmd.cocecl.contentviews.AssignedUnitsAdapter;
import it.fmd.cocecl.patadminaction.CreatePat;
import it.fmd.cocecl.unitstatus.SetIncidentStatus;

import static android.graphics.Color.GREEN;

public class incidentFragment extends Fragment {

    SetIncidentStatus sis = new SetIncidentStatus();
    CreatePat cp = new CreatePat();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_incident2, container, false);

        final Button button10 = (Button) v.findViewById(R.id.button10);
        final Button button11 = (Button) v.findViewById(R.id.button11);
        final Button button13 = (Button) v.findViewById(R.id.button13);
        final Button statusbtn = (Button) v.findViewById(R.id.button41);
        final Button createpatbtn = (Button) v.findViewById(R.id.button46);

        button10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intunt();
            }
        });

        button11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                belver();
            }
        });

        button13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                andrm();
            }
        });

        statusbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stbtnClick();
            }
        });

        //Create Patient
        createpatbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //cp.createpat();
            }
        });

        return v;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        setUnitsGVData();
    }

    public void setUnitsGVData() {

        // Construct the data source

        ArrayList<AssignedUnits> arrayOfUnitses = new ArrayList<AssignedUnits>();

        // Create the adapter to convert the array to views

        AssignedUnitsAdapter adapter = new AssignedUnitsAdapter(getContext(), arrayOfUnitses);

        // Attach the adapter to a GridView

        final GridView gridView = (GridView) getActivity().findViewById(R.id.asUnitGV);

        gridView.setAdapter(adapter);

        // Add item to adapter
        // TEST DATA - later from server JSON, stored in shared prefs
        AssignedUnits newUnits = new AssignedUnits("RTW-01");
        AssignedUnits newUnits2 = new AssignedUnits("RTW-02");
        AssignedUnits newUnits3 = new AssignedUnits("NEF-01");
        AssignedUnits newUnits4 = new AssignedUnits("VOK");


        adapter.add(newUnits);
        adapter.add(newUnits2);
        adapter.add(newUnits3);
        adapter.add(newUnits4);


        // OnClick Event load Incident Data from Storage (if more than one) to fields in incidentFragment
        gridView.setClickable(true);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {

                Object unitclick = gridView.getItemAtPosition(position);
    /* write you handling code like...
    String st = "sdcard/";
    File f = new File(st+o.toString());
    // do whatever u want to do with 'f' File object
    */
            }
        });

    }
/*
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //MA.setSMS();
    }*/

    //PatStatus am BO

    //INTUNT
    public void intunt() {
        final Button button41 = (Button) getActivity().findViewById(R.id.button41);
        final TextView textView83 = (TextView) getActivity().findViewById(R.id.statusView);

        final TextView patstattv = (TextView) getActivity().findViewById(R.id.textView116);

        final AlertDialog.Builder dlgBuilder = new AlertDialog.Builder(getActivity());

        dlgBuilder.setMessage("Intervention unterblieben?");
        dlgBuilder.setCancelable(false);
        dlgBuilder.setPositiveButton("Ja", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                Button button10 = (Button) getActivity().findViewById(R.id.button10);
                button10.setEnabled(true);
                button10.setClickable(false);
                button10.setBackgroundColor(GREEN);

                Button button11 = (Button) getActivity().findViewById(R.id.button11);
                button11.setEnabled(false);
                button11.setClickable(false);
                button11.setBackgroundColor(Color.parseColor("#bdbdbd"));
                //button11.setBackgroundResource(android.R.drawable.btn_default);

                Button button13 = (Button) getActivity().findViewById(R.id.button13);
                button13.setEnabled(false);
                button13.setClickable(false);
                button13.setBackgroundColor(Color.parseColor("#bdbdbd"));
                //button13.setBackgroundResource(android.R.drawable.btn_default);

                button41.setEnabled(true);
                button41.setClickable(true);
                button41.setBackgroundColor(Color.parseColor("#bdbdbd"));
                //button41.setBackgroundResource(android.R.drawable.btn_default);
                button41.setText("Einsatz abschliessen");
                button41.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_play_arrow_black_18dp, 0, 0, 0);
                textView83.setText("EB");

                //Toast.makeText(MainActivity.this, "keine Intervention", Toast.LENGTH_SHORT).show();
                patstattv.setVisibility(View.VISIBLE);
                patstattv.setText("Intervention unterblieben");
            }
        });

        dlgBuilder.setNegativeButton("Nein", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        AlertDialog alert = dlgBuilder.create();
        alert.show();
    }

    //BEL/VER
    public void belver() {
        final Button button41 = (Button) getActivity().findViewById(R.id.button41);
        final TextView textView83 = (TextView) getActivity().findViewById(R.id.statusView);

        final TextView patstattv = (TextView) getActivity().findViewById(R.id.textView116);

        final AlertDialog.Builder dlgBuilder = new AlertDialog.Builder(getActivity());
        dlgBuilder.setTitle("Patient belassen/verweigert?");
        dlgBuilder.setItems(new CharSequence[]
                        {"Patient belassen", "Patient verweigert", "Nein"},

                new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {

                        switch (which) {

                            case 0:

                                Button button11 = (Button) getActivity().findViewById(R.id.button11);
                                button11.setEnabled(true);
                                button11.setClickable(false);
                                button11.setBackgroundColor(GREEN);

                                Button button10 = (Button) getActivity().findViewById(R.id.button10);
                                button10.setEnabled(false);
                                button10.setClickable(false);
                                button10.setBackgroundColor(Color.parseColor("#bdbdbd"));
                                //button10.setBackgroundResource(android.R.drawable.btn_default);

                                Button button13 = (Button) getActivity().findViewById(R.id.button13);
                                button13.setEnabled(false);
                                button13.setClickable(false);
                                button13.setBackgroundColor(Color.parseColor("#bdbdbd"));
                                //button13.setBackgroundResource(android.R.drawable.btn_default);

                                button41.setEnabled(true);
                                button41.setClickable(true);
                                button41.setBackgroundColor(Color.parseColor("#bdbdbd"));
                                //button41.setBackgroundResource(android.R.drawable.btn_default);
                                button41.setText("Einsatz abschliessen");
                                button41.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_play_arrow_black_18dp, 0, 0, 0);
                                textView83.setText("EB");

                                //Toast.makeText(MainActivity.this, "Belassung", Toast.LENGTH_SHORT).show();
                                patstattv.setVisibility(View.VISIBLE);
                                patstattv.setText("Belassung");
                                break;
                            case 1:

                                button11 = (Button) getActivity().findViewById(R.id.button11);
                                button11.setEnabled(true);
                                button11.setClickable(false);
                                button11.setBackgroundColor(GREEN);

                                button10 = (Button) getActivity().findViewById(R.id.button10);
                                button10.setEnabled(false);
                                button10.setClickable(false);
                                button10.setBackgroundColor(Color.parseColor("#bdbdbd"));
                                //button10.setBackgroundResource(android.R.drawable.btn_default);

                                button13 = (Button) getActivity().findViewById(R.id.button13);
                                button13.setEnabled(false);
                                button13.setClickable(false);
                                button13.setBackgroundColor(Color.parseColor("#bdbdbd"));
                                //button13.setBackgroundResource(android.R.drawable.btn_default);

                                button41.setEnabled(true);
                                button41.setClickable(true);
                                button41.setBackgroundColor(Color.parseColor("#bdbdbd"));
                                //button41.setBackgroundResource(android.R.drawable.btn_default);
                                button41.setText("Einsatz abschliessen");
                                button41.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_play_arrow_black_18dp, 0, 0, 0);
                                textView83.setText("EB");

                                //Toast.makeText(MainActivity.this, "Patient verweigert", Toast.LENGTH_SHORT).show();
                                patstattv.setVisibility(View.VISIBLE);
                                patstattv.setText("Patient verweigert");
                                break;
                            case 2:
                                break;
                        }
                    }
                });

        dlgBuilder.create().show();
    }

    //Anderes RM
    public void andrm() {
        final Button button41 = (Button) getActivity().findViewById(R.id.button41);
        final TextView textView83 = (TextView) getActivity().findViewById(R.id.statusView);

        final TextView patstattv = (TextView) getActivity().findViewById(R.id.textView116);

        final AlertDialog.Builder dlgBuilder = new AlertDialog.Builder(getActivity());
        dlgBuilder.setMessage("Patient an anderes Rettungsmittel übergeben?");
        dlgBuilder.setCancelable(false);
        dlgBuilder.setPositiveButton("Ja", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                Button button13 = (Button) getActivity().findViewById(R.id.button13);
                button13.setEnabled(true);
                button13.setClickable(false);
                button13.setBackgroundColor(GREEN);

                Button button10 = (Button) getActivity().findViewById(R.id.button10);
                button10.setEnabled(false);
                button10.setClickable(false);
                button10.setBackgroundColor(Color.parseColor("#bdbdbd"));
                //button10.setBackgroundResource(android.R.drawable.btn_default);

                Button button11 = (Button) getActivity().findViewById(R.id.button11);
                button11.setEnabled(false);
                button11.setClickable(false);
                button11.setBackgroundColor(Color.parseColor("#bdbdbd"));
                //button11.setBackgroundResource(android.R.drawable.btn_default);

                button41.setEnabled(true);
                button41.setClickable(true);
                button41.setBackgroundColor(Color.parseColor("#bdbdbd"));
                //button41.setBackgroundResource(android.R.drawable.btn_default);
                button41.setText("Einsatz abschliessen");
                button41.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_play_arrow_black_18dp, 0, 0, 0);
                textView83.setText("EB");

                //Toast.makeText(MainActivity.this, "Übergabe an anderes Rettungsmittel", Toast.LENGTH_SHORT).show();
                patstattv.setVisibility(View.VISIBLE);
                patstattv.setText("Übergabe an anderes Rettungsmittel");
            }
        });

        dlgBuilder.setNegativeButton("Nein", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        dlgBuilder.create().show();
    }

    public void stbtnClick() {

        //final RelativeLayout deliveryloclayout = (RelativeLayout)getLayoutInflater().inflate(R.layout.fragment_deliveryloc, null);

        final Calendar cal = Calendar.getInstance(TimeZone.getDefault());
        final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

        Button button41 = (Button) getActivity().findViewById(R.id.button41);

        AlertDialog.Builder dlgBuilder = new AlertDialog.Builder(getContext());
        dlgBuilder.setTitle(R.string.stwe);
        dlgBuilder.setMessage(button41.getText().toString());
        dlgBuilder.setCancelable(false);
        dlgBuilder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {

            final Button button41 = (Button) getActivity().findViewById(R.id.button41);
            final TextView textView83 = (TextView) getActivity().findViewById(R.id.statusView);
            final TextView textView85 = (TextView) getActivity().findViewById(R.id.textView85);
            //final TextView aofield = (TextView) deliveryloclayout.findViewById(R.id.aofield);

            final TextView statuserror = (TextView) getActivity().findViewById(R.id.textView129);

            Button button10 = (Button) getActivity().findViewById(R.id.button10);
            Button button11 = (Button) getActivity().findViewById(R.id.button11);
            Button button13 = (Button) getActivity().findViewById(R.id.button13);
            Button button46 = (Button) getActivity().findViewById(R.id.button46);

            @Override
            public void onClick(DialogInterface dialog, int which) {

                // QU Einsatz übernehmen
                if (textView83.getText().equals("") || textView83.getText().equals("EB")) {

                    /*sis.*/
                    qu();

                    // ZBO
                } else if (textView83.getText().equals("QU")) {

                    /*sis.*/
                    st3();

                    // ABO
                } else if (textView83.getText().equals("ZBO")) {

                    /*sis.*/
                    st4();

                    // ZAO
                } else if ((textView83.getText().equals("ABO")) /*&& (aofield.getText().toString().trim().length() > 0)*/) {
                    //TODO: redundante funktion
                    /*sis.*/
                    st7();

                    // AAO
                } else if (textView83.getText().equals("ZAO")) {

                    /*sis.*/
                    st8();

                    // Einsatz abschliessen
                } else if (textView83.getText().equals("AAO")) {

                    /*sis.*/
                    endIncident();

                } else if (button41.getText().equals("Einsatz abschliessen")) {

                    /*sis.*/
                    removeIncident();
                }
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

    // TODO REMOVE when setup SetIcident class ----------------------------------------------------

    public void qu() {

        final Button button41 = (Button) getActivity().findViewById(R.id.button41);
        final TextView textView83 = (TextView) getActivity().findViewById(R.id.statusView);
        final TextView textView85 = (TextView) getActivity().findViewById(R.id.textView85);

        final Calendar cal = Calendar.getInstance(TimeZone.getDefault());
        final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

        // QU Einsatz übernehmen
        button41.setEnabled(false);
        button41.setClickable(false);
        //button41.setBackgroundColor(YELLOW);
        button41.setBackground(getResources().getDrawable(R.drawable.button_yellow_pressed));
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
                //button41.setBackgroundResource(android.R.drawable.btn_default);
                button41.setBackground(getResources().getDrawable(R.drawable.custom_button_normal));
            }
        }, 3000);

    }

    public void st1() {

    }

    public void st2() {

    }

    public void st3() {

        final Button button41 = (Button) getActivity().findViewById(R.id.button41);
        final TextView textView83 = (TextView) getActivity().findViewById(R.id.statusView);
        final TextView textView85 = (TextView) getActivity().findViewById(R.id.textView85);

        final Calendar cal = Calendar.getInstance(TimeZone.getDefault());
        final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

        // ZBO
        button41.setEnabled(false);
        button41.setClickable(false);
        //button41.setBackgroundColor(YELLOW);
        button41.setBackground(getResources().getDrawable(R.drawable.button_yellow_pressed));
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
                //button41.setBackgroundResource(android.R.drawable.btn_default);
                button41.setBackground(getResources().getDrawable(R.drawable.custom_button_normal));
            }
        }, 10000);

    }

    public void st4() {

        final Button button41 = (Button) getActivity().findViewById(R.id.button41);
        final TextView textView83 = (TextView) getActivity().findViewById(R.id.statusView);
        final TextView textView85 = (TextView) getActivity().findViewById(R.id.textView85);

        final Calendar cal = Calendar.getInstance(TimeZone.getDefault());
        final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

        Button button10 = (Button) getActivity().findViewById(R.id.button10);
        Button button11 = (Button) getActivity().findViewById(R.id.button11);
        Button button13 = (Button) getActivity().findViewById(R.id.button13);
        Button button46 = (Button) getActivity().findViewById(R.id.button46);

        // ABO
        button41.setEnabled(false);
        button41.setClickable(false);
        //button41.setBackgroundColor(YELLOW);
        button41.setBackground(getResources().getDrawable(R.drawable.button_yellow_pressed));
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
                //button41.setBackgroundResource(android.R.drawable.btn_default);
                button41.setBackground(getResources().getDrawable(R.drawable.custom_button_normal));
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

        final RelativeLayout deliveryloclayout = (RelativeLayout) getActivity().getLayoutInflater().inflate(R.layout.fragment_deliveryloc, null);

        final Calendar cal = Calendar.getInstance(TimeZone.getDefault());
        final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

        final Button button41 = (Button) getActivity().findViewById(R.id.button41);
        final TextView textView83 = (TextView) getActivity().findViewById(R.id.statusView);
        final TextView textView85 = (TextView) getActivity().findViewById(R.id.textView85);
        final TextView aofield = (TextView) deliveryloclayout.findViewById(R.id.aofield);

        final TextView statuserror = (TextView) getActivity().findViewById(R.id.textView129);

        Button button10 = (Button) getActivity().findViewById(R.id.button10);
        Button button11 = (Button) getActivity().findViewById(R.id.button11);
        Button button13 = (Button) getActivity().findViewById(R.id.button13);
        Button button46 = (Button) getActivity().findViewById(R.id.button46);

        // ZAO
        //if (aofield.getText().toString().trim().length() > 0) {
        button41.setEnabled(true);
        button41.setClickable(true);
        //button41.setBackgroundColor(YELLOW);
        button41.setBackground(getResources().getDrawable(R.drawable.button_yellow_pressed));
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
        /*} else {
            button41.setEnabled(false);
            button41.setClickable(false);
            //Toast.makeText(MainActivity.this, "Kein Abgabeort eingetragen", Toast.LENGTH_LONG).show();
            statuserror.setText("Kein Abgabeort eingetragen");
            statuserror.setVisibility(View.VISIBLE);
        }*/

        Handler h = new Handler();
        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                button41.setEnabled(true);
                button41.setClickable(true);
                //button41.setBackgroundResource(android.R.drawable.btn_default);
                button41.setBackground(getResources().getDrawable(R.drawable.custom_button_normal));
                statuserror.setText("");
                statuserror.setVisibility(View.GONE);
            }
        }, 10000);

    }

    public void st8() {

        final Calendar cal = Calendar.getInstance(TimeZone.getDefault());
        final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

        final Button button41 = (Button) getActivity().findViewById(R.id.button41);
        final TextView textView83 = (TextView) getActivity().findViewById(R.id.statusView);
        final TextView textView85 = (TextView) getActivity().findViewById(R.id.textView85);

        // AAO
        button41.setEnabled(false);
        button41.setClickable(false);
        //button41.setBackgroundColor(YELLOW);
        button41.setBackground(getResources().getDrawable(R.drawable.button_yellow_pressed));
        button41.setText("Abschliessen");
        textView83.setText("AAO");
        textView85.setText(sdf.format(cal.getTime()));
        button41.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_play_arrow_black_18dp, 0, 0, 0);

        Handler h = new Handler();
        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                button41.setEnabled(true);
                button41.setClickable(true);
                //button41.setBackgroundResource(android.R.drawable.btn_default);
                button41.setBackground(getResources().getDrawable(R.drawable.custom_button_normal));
            }
        }, 10000);

    }

    public void endIncident() {

        final Calendar cal = Calendar.getInstance(TimeZone.getDefault());
        final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

        final Button button41 = (Button) getActivity().findViewById(R.id.button41);
        final TextView textView83 = (TextView) getActivity().findViewById(R.id.statusView);
        final TextView textView85 = (TextView) getActivity().findViewById(R.id.textView85);

        final AlertDialog.Builder dlgBuilder = new AlertDialog.Builder(getActivity());
        dlgBuilder.setMessage("Einsatzbereit melden?");
        dlgBuilder.setCancelable(false);
        dlgBuilder.setPositiveButton("Ja", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                // Einsatz abschliessen
                button41.setEnabled(false);
                button41.setClickable(false);
                //button41.setBackgroundColor(YELLOW);
                button41.setBackground(getResources().getDrawable(R.drawable.button_yellow_pressed));
                button41.setText("");
                textView83.setText("ENDE");
                textView85.setText(sdf.format(cal.getTime()));

                Handler h = new Handler();
                h.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        button41.setEnabled(true);
                        button41.setClickable(true);
                        //button41.setBackgroundResource(android.R.drawable.btn_default);
                        button41.setBackground(getResources().getDrawable(R.drawable.custom_button_normal));
                    }
                }, 10000);

            }
        });
        dlgBuilder.setNegativeButton("Nein", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                mainstatusFragment mF = new mainstatusFragment();
                mF.st6();
            }
        });

        dlgBuilder.create().show();
    }

    public void removeIncident() {

        // TODO Remove Incident (& deliveryloc) Tab, when Incident accomplished
        Fragment fragment = getActivity().getSupportFragmentManager().findFragmentById(R.id.incidentfraglayout);
        if (fragment != null)
            getActivity().getSupportFragmentManager().beginTransaction().remove(fragment).commit();

    }

    public void st9() {
        // AD
    }
}