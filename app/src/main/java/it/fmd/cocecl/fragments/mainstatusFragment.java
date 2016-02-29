package it.fmd.cocecl.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import it.fmd.cocecl.R;
import it.fmd.cocecl.contentviews.IncidentAdapter;
import it.fmd.cocecl.contentviews.Incidents;
import it.fmd.cocecl.contentviews.ListViewUtil;
import it.fmd.cocecl.unitstatus.ReportIncident;
import it.fmd.cocecl.unitstatus.SetUnitStatus;

public class mainstatusFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_mainstatus2, container, false);

        final Button button38 = (Button) v.findViewById(R.id.button38);
        final Button button39 = (Button) v.findViewById(R.id.button39);
        final Button button40 = (Button) v.findViewById(R.id.button40);

        final Button button5 = (Button) v.findViewById(R.id.button5);
        final Button button12 = (Button) v.findViewById(R.id.button12);

        final Button button42 = (Button) v.findViewById(R.id.button42);

        //return v;

        //Status EB
        button38.setOnClickListener(new SetUnitStatus(getActivity()));

        //Status NEB
        button39.setOnClickListener(new SetUnitStatus(getActivity()));

        //Status AD
        button40.setOnClickListener(new SetUnitStatus(getActivity()));

        //Status 5 Selektivruf
        button5.setOnClickListener(new SetUnitStatus(getActivity()));

        //Status NOTRUF
        button12.setOnClickListener(new SetUnitStatus(getActivity()));


        // ReportIncident PlacesAutoComplete
        button42.setOnClickListener(new ReportIncident(getActivity()));

        return v;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        setIncidentLVData();
    }

    public void setIncidentLVData() {

        // Construct the data source
        ArrayList<Incidents> arrayOfIncidentses = new ArrayList<Incidents>();

        // Create the adapter to convert the array to views
        IncidentAdapter adapter = new IncidentAdapter(getContext(), arrayOfIncidentses);

        // Attach the adapter to a ListView
        final ListView listView = (ListView) getActivity().findViewById(R.id.activeincidentlv);

        // Adapt LV size, LV in ScrollView bug
        ListViewUtil.setListViewHeightBasedOnChildren(listView);

        listView.setAdapter(adapter);

        // Add item to adapter
        // TEST DATA - later from server JSON, stored in shared prefs
        Incidents newIncidents = new Incidents("Sturz", "unklar", "Neubaugasse 64", "QU");

        adapter.add(newIncidents);

        // OnClick Event load Incident Data from Storage (if more than one) to fields in incidentFragment
        listView.setClickable(true);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {

                //Object incidentclick = listView.getItemAtPosition(position);

                arg0.setSelected(true);
            }
        });

    }
}