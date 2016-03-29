package it.fmd.cocecl.fragments;

import android.app.NotificationManager;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

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
import it.fmd.cocecl.dataStorage.IncidentData;
import it.fmd.cocecl.dataStorage.UnitStatus;
import it.fmd.cocecl.incidentaction.IncidentTaskTypeSetting;
import it.fmd.cocecl.unitstatus.ReportIncident;
import it.fmd.cocecl.unitstatus.SetUnitStatus;

public class mainstatusFragment extends Fragment {

    IncidentTaskTypeSetting itts = new IncidentTaskTypeSetting(getActivity());

    TextView countlv;
    ArrayList<Incidents> arrayOfIncidentses;

    //Status Views&Buttons
    TextView statustv;
    Button button38;
    Button button39;
    Button button40;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_mainstatus, container, false);

        button38 = (Button) v.findViewById(R.id.button38);
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

        countlv = (TextView) v.findViewById(R.id.textView113);
        statustv = (TextView) v.findViewById(R.id.textView111);

        return v;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        setIncidentLVData();
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
        final String bggrund = IncidentData.getInstance().getBogrund();
        final String boaddress = IncidentData.getInstance().getBoaddress();
        String info = IncidentData.getInstance().getBoinfo();
        // Berufungsgrund, Info, Adresse, Status
        Incidents newIncidents = new Incidents("Sturz", "unklar", "Neubaugasse 64", "QU");
        Incidents newIncidents1 = new Incidents("Auftrag", "Material holen", "Nottendorfer Gasse 21-23, 1030", "ZBO");
        Incidents newIncidents2 = new Incidents(bggrund, info, boaddress, "ZBO");


        adapter.add(newIncidents);
        adapter.add(newIncidents1);
        adapter.add(newIncidents2);

        // OnClick Event load Incident Data from Storage (if more than one) to fields in incidentFragment
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

        UnitStatus.getInstance().getUstatus();

    }
}


