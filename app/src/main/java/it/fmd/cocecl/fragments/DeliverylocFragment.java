package it.fmd.cocecl.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import it.fmd.cocecl.R;
import it.fmd.cocecl.TabletFeatures;
import it.fmd.cocecl.dataStorage.PatData;
import it.fmd.cocecl.gmapsnav.StartNavigation;
import it.fmd.cocecl.patadminaction.CreatePat;

public class DeliverylocFragment extends Fragment {

    PatData pd = new PatData();

    String patplsnr;
    String patfname;
    String patlname;
    String patbdate;
    String patgender;
    String patsvnr;
    String patward;

    TextView patplstv;
    TextView patfnametv;
    TextView patlnametv;
    TextView patsvbdtv;
    TextView patgendertv;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_deliveryloc, container, false);

        //Pat Table View
        patplstv = (TextView) v.findViewById(R.id.textView106);//PLS
        patfnametv = (TextView) v.findViewById(R.id.textView177);//Nachname
        patlnametv = (TextView) v.findViewById(R.id.textView178);//Vorname
        patsvbdtv = (TextView) v.findViewById(R.id.textView179);//SVNR + Geb.Tag
        patgendertv = (TextView) v.findViewById(R.id.textView180);//Gender


        final Button changepatbtn = (Button) v.findViewById(R.id.changepatbtn);
        Button navao = (Button) v.findViewById(R.id.button19);

        //Nav
        navao.setOnClickListener(new StartNavigation(getActivity()));

        changepatbtn.setOnClickListener(new CreatePat(getActivity()));

        TabletFeatures tf = new TabletFeatures(getActivity());

        //TODO NPE
        //tf.patman_enable();

        setPatDatatoTV();

        return v;
    }

    public void setPatDatatoTV() {
        patplsnr = pd.getPatplsnr();
        patfname = pd.getPatfname();
        patlname = pd.getPatname();
        patbdate = pd.getPatbdate();
        patgender = pd.getPatgender();
        patsvnr = pd.getPatsvnr();
        patward = pd.getPatward();


        patplstv.setText(patplsnr);
        patlnametv.setText(patlname);
        patfnametv.setText(patfname);
        patsvbdtv.setText(patsvnr + " - " + patbdate);
        patgendertv.setText(patgender);
    }
}
