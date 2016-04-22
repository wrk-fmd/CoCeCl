package it.fmd.cocecl.fragments;

import android.app.NotificationManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import it.fmd.cocecl.R;
import it.fmd.cocecl.contentviews.IncidentAdapter;
import it.fmd.cocecl.contentviews.Incidents;
import it.fmd.cocecl.contentviews.ListViewUtil;
import it.fmd.cocecl.dataStorage.IncidentData;
import it.fmd.cocecl.dataStorage.MainData;
import it.fmd.cocecl.dataStorage.UnitData;
import it.fmd.cocecl.dataStorage.UnitStatus;
import it.fmd.cocecl.incidentaction.IncidentTaskTypeSetting;
import it.fmd.cocecl.unitstatus.ReportIncident;
import it.fmd.cocecl.unitstatus.SetUnitStatus;

public class MainstatusFragment extends Fragment {

    IncidentTaskTypeSetting itts = new IncidentTaskTypeSetting(getActivity());

    IncidentData id = new IncidentData();
    MainData md = new MainData();
    UnitStatus us = new UnitStatus();
    UnitData ud = new UnitData();

    TextView countlv;
    ArrayList<Incidents> arrayOfIncidentses;

    //Status Views&Buttons
    TextView statustv;
    Button button5, button12, button38, button39, button40, button42;

    CharSequence statusText;

    TextView ambtypetv;
    TextView ambnametv;
    TextView unitnametv;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_mainstatus, container, false);

        button38 = (Button) v.findViewById(R.id.button38);
        button39 = (Button) v.findViewById(R.id.button39);
        button40 = (Button) v.findViewById(R.id.button40);

        button5 = (Button) v.findViewById(R.id.button5);
        button12 = (Button) v.findViewById(R.id.button12);

        button42 = (Button) v.findViewById(R.id.button42);

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
        button42.setOnClickListener(new ReportIncident(getActivity(), getContext()));

        countlv = (TextView) v.findViewById(R.id.textView113);
        statustv = (TextView) v.findViewById(R.id.textView111);

        ambnametv = (TextView) v.findViewById(R.id.textView79);
        ambtypetv = (TextView) v.findViewById(R.id.textView130);
        unitnametv = (TextView) v.findViewById(R.id.textView80);

        return v;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        /*
        setIncidentLVData();
        setUnitstatus();
        setAmbInfo();
        */
    }

    public void setIncidentLVData() {

        // Construct the data source
        arrayOfIncidentses = new ArrayList<Incidents>();

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
        final String bggrund = id.getBogrund();
        final String boaddress = id.getBoaddress();
        String info = id.getBoinfo();
        String incistatus = id.getIncistatus();

        // Berufungsgrund, Info, Adresse, Status
        Incidents newIncidents = new Incidents(bggrund, info, boaddress, incistatus);

        adapter.add(newIncidents);

        // OnClick Event load Incident Data from Storage (if more than one) to fields in IncidentFragment
        incidentlv.setClickable(true);
        incidentlv.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);
        incidentlv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {

                incidentlv.setItemChecked(position, true);

                //Update NotificationBar
                NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(getActivity())
                        .setSmallIcon(R.mipmap.coceclstbar)
                        .setContentTitle("CoCeCl")
                        .setContentText(bggrund + " - " + boaddress)
                        .setOngoing(true);

                NotificationManager notificationManager = (NotificationManager) getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
                notificationManager.notify(991, mBuilder.build());

            }
        });

        getLVCount();
        setNoTask();
    }

    public void getLVCount() {
        int itemCount = arrayOfIncidentses.size();
        countlv.setText("Anz.: " + String.valueOf(itemCount));
        if (itemCount == 1) {
            countlv.setVisibility(View.GONE);
        }
    }

    public void setNoTask() {
        if (countlv.getText().toString().trim().length() == 0) {
            itts.noTask();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

        statusText = statustv.getText();
        savedInstanceState.putCharSequence("statusText", statusText);

    }

    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);

        //setUnitstatus();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (savedInstanceState != null) {
            statusText = savedInstanceState.getCharSequence("statusText");
            statustv.setText(statusText);
        }
    }

    public void setUnitstatus() {

        SetUnitStatus sus = new SetUnitStatus(getActivity());

        String ustatus = us.getUstatus();
        String ustaddition = us.getUstaddition();

        if (ustatus.equals("EB")) {
            sus.ebst();
            statustv.setText(ustaddition);
        }

        if (ustatus.equals("NEB")) {
            sus.nebst();
            statustv.setText(ustaddition);
        }

        if (ustatus.equals("AD")) {
            sus.adst();
            statustv.setText(ustaddition);
        }

        if (ustaddition != null) {
            statustv.setVisibility(View.VISIBLE);
            statustv.setText(ustaddition);
        }
    }

    public void setAmbInfo() {

        String unit = ud.getUnitname();

        String ambname = md.getAmb();
        String ambtype = md.getAmbtype();

        unitnametv.setText(unit);

        ambnametv.setText(ambname);
        ambtypetv.setText(ambtype);


    }
}


