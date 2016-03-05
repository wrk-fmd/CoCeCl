package it.fmd.cocecl.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import it.fmd.cocecl.R;
import it.fmd.cocecl.gmapsnav.StartNavigation;

public class deliverylocFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_deliveryloc, container, false);

        final Button changepatbtn = (Button) v.findViewById(R.id.changepatbtn);
        Button navao = (Button) v.findViewById(R.id.button19);

        //Nav
        navao.setOnClickListener(new StartNavigation(getActivity()));

        changepatbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        return v;
    }
}
