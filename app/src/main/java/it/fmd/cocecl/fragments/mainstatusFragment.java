package it.fmd.cocecl.fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;

import it.fmd.cocecl.APPConstants;
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
        View v = inflater.inflate(R.layout.fragment_mainstatus, container, false);

        final Button button38 = (Button) v.findViewById(R.id.button38);
        final Button button39 = (Button) v.findViewById(R.id.button39);
        final Button button40 = (Button) v.findViewById(R.id.button40);

        final Button button5 = (Button) v.findViewById(R.id.button5);
        final Button button12 = (Button) v.findViewById(R.id.button12);

        final Button button42 = (Button) v.findViewById(R.id.button42);

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
        //final ListView incidentlv = (ListView) getActivity().findViewById(R.id.activeincidentlv);
        final ListViewUtil incidentlv = (ListViewUtil) getActivity().findViewById(R.id.activeincidentlv);

        // Adapt LV size, LV in ScrollView bug
        incidentlv.setExpanded(true);

        incidentlv.setAdapter(adapter);

        // Add item to adapter
        // TEST DATA - later from server JSON, stored in shared prefs
        // Berufungsgrund, Info, Adresse, Status
        Incidents newIncidents = new Incidents("Sturz", "unklar", "Neubaugasse 64", "QU");
        Incidents newIncidents1 = new Incidents("Auftrag", "Material holen", "Nottendorfer Gasse 21-23, 1030", "ZBO");
        Incidents newIncidents2 = new Incidents("Auftrag", "Dienstfahrt", "Zentrale, 1030", "ZBO");

        adapter.add(newIncidents);
        adapter.add(newIncidents1);
        adapter.add(newIncidents2);

        // OnClick Event load Incident Data from Storage (if more than one) to fields in incidentFragment
        incidentlv.setClickable(true);
        incidentlv.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);
        incidentlv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {

                //Object incidentclick = listView.getItemAtPosition(position);
                incidentlv.setItemChecked(position, true);


            }
        });
    }
}


