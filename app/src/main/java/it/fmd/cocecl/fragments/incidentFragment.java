package it.fmd.cocecl.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;

import java.util.ArrayList;

import it.fmd.cocecl.MainActivity;
import it.fmd.cocecl.R;
import it.fmd.cocecl.contentviews.AssignedUnits;
import it.fmd.cocecl.contentviews.AssignedUnitsAdapter;
import it.fmd.cocecl.contentviews.IncidentAdapter;
import it.fmd.cocecl.contentviews.Incidents;

public class incidentFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) { super.onCreate(savedInstanceState); }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_incident2, container, false);
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
}