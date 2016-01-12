package it.fmd.cocecl.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;

import it.fmd.cocecl.R;
import it.fmd.cocecl.contentviews.AssignedUnits;
import it.fmd.cocecl.contentviews.AssignedUnitsAdapter;
import it.fmd.cocecl.utilclass.Phonecalls;

import static android.graphics.Color.GREEN;

public class incidentFragment extends Fragment {

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

        //return v;

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
}