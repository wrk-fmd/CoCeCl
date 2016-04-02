package it.fmd.cocecl.fragments;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import it.fmd.cocecl.R;
import it.fmd.cocecl.contentviews.AssignedUnits;
import it.fmd.cocecl.contentviews.AssignedUnitsAdapter;
import it.fmd.cocecl.contentviews.GridViewUtil;
import it.fmd.cocecl.dataStorage.IncidentData;
import it.fmd.cocecl.gmapsnav.StartNavigation;
import it.fmd.cocecl.patadminaction.CreatePat;
import it.fmd.cocecl.patadminaction.PatStatus;
import it.fmd.cocecl.unitstatus.SetIncidentStatus;

public class incidentFragment extends Fragment {

    Activity activity;

    TextView boaddress;
    TextView boinfo;
    TextView tasktype;
    TextView bgrund;
    TextView caller;
    TextView statusView;
    CheckBox emergencyBox;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_incident, container, false);

        final Button button10 = (Button) v.findViewById(R.id.button10);
        final Button button11 = (Button) v.findViewById(R.id.button11);
        final Button button13 = (Button) v.findViewById(R.id.button13);
        final Button statusbtn = (Button) v.findViewById(R.id.button41);
        final Button createpatbtn = (Button) v.findViewById(R.id.button46);
        final Button navbo = (Button) v.findViewById(R.id.button18);

        button10.setOnClickListener(new PatStatus(getActivity()));

        button11.setOnClickListener(new PatStatus(getActivity()));

        button13.setOnClickListener(new PatStatus(getActivity()));

        // Set status
        statusbtn.setOnClickListener(new SetIncidentStatus(getActivity()));

        //Create Patient
        createpatbtn.setOnClickListener(new CreatePat(getActivity()));

        //Nav
        navbo.setOnClickListener(new StartNavigation(getActivity()));

        bgrund = (TextView) v.findViewById(R.id.bgfield);
        boaddress = (TextView) v.findViewById(R.id.bofield);
        boinfo = (TextView) v.findViewById(R.id.infofield);
        tasktype = (TextView) v.findViewById(R.id.textView127);
        caller = (TextView) v.findViewById(R.id.brfrfield);
        emergencyBox = (CheckBox) v.findViewById(R.id.emergencyBox);

        statusView = (TextView) v.findViewById(R.id.statusView);

        return v;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        setUnitsGVData();

        setIncidentData();
    }

    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);

        setIncidentData();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = getActivity();

        //itts.tasktypeCardcolor();
    }

    public void setIncidentData() {

        SetIncidentStatus sis = new SetIncidentStatus(getActivity());

        String stboaddress = IncidentData.getInstance().getBoaddress();
        String stinfo = IncidentData.getInstance().getBoinfo();
        String sttasktype = IncidentData.getInstance().getTasktype();
        String stbgrund = IncidentData.getInstance().getBogrund();
        String stcaller = IncidentData.getInstance().getCaller();
        Boolean stemergency = IncidentData.getInstance().getEmergency();

        String incistatus = IncidentData.getInstance().getIncistatus();

        tasktype.setText(sttasktype);
        bgrund.setText(stbgrund);
        bgrund.setVisibility(View.VISIBLE);
        boaddress.setText(stboaddress);
        boinfo.setText(stinfo);
        boinfo.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        caller.setText(stcaller);

        if (emergencyBox == null) {
            emergencyBox.setChecked(false);
            if (stemergency != null) {
                emergencyBox.setChecked(stemergency);
            }
            emergencyBox.setVisibility(View.VISIBLE);
        }

        if (emergencyBox.isChecked()) {
            emergencyBox.setTextColor(Color.BLUE);
        }

        if (incistatus.equals("QU")) {
            sis.qu();
        }

        if (incistatus.equals("ZBO")) {
            sis.st3();
        }

        if (incistatus.equals("ABO")) {
            sis.st4();
        }

        if (incistatus.equals("ZAO")) {
            sis.st7();
        }

        if (incistatus.equals("AAO")) {
            sis.st8();
        }
    }

    public void setUnitsGVData() {

        // Construct the data source

        ArrayList<AssignedUnits> arrayOfUnitses = new ArrayList<AssignedUnits>();

        // Create the adapter to convert the array to views

        AssignedUnitsAdapter adapter = new AssignedUnitsAdapter(getContext(), arrayOfUnitses);

        // Attach the adapter to a GridView

        //final GridView gridView = (GridView) getActivity().findViewById(R.id.asUnitGV);
        final GridViewUtil gridView = (GridViewUtil) getActivity().findViewById(R.id.asUnitGV);

        gridView.setExpanded(true);
        gridView.setNumColumns(2);

        gridView.setAdapter(adapter);

        // Add item to adapter
        // TODO: TEST DATA - later from server JSON, stored in shared prefs
        AssignedUnits newUnits = new AssignedUnits();

        adapter.add(newUnits);

        // OnClick Event load Incident Data from Storage (if more than one) to fields in incidentFragment
        gridView.setClickable(true);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {

                Object unitclick = gridView.getItemAtPosition(position);

            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
