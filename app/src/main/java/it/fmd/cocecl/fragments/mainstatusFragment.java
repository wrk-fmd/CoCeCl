package it.fmd.cocecl.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import it.fmd.cocecl.MainActivity;
import it.fmd.cocecl.R;

import static android.graphics.Color.GREEN;

public class mainstatusFragment extends Fragment {

/*
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
*/
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_mainstatus, container, false);

        final Button button38 = (Button) v.findViewById(R.id.button38);
        final Button button39 = (Button) v.findViewById(R.id.button39);
        final Button button40 = (Button) v.findViewById(R.id.button40);

        return v;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // TODO Auto-generated method stub
    }


    // Button state & color functions START //
    // Status EB NEB AD mainstatusFragment //
    // TODO: set by server

    public void ebst() {

        Button button38 = (Button) getActivity().findViewById(R.id.button38);
        Button button39 = (Button) getActivity().findViewById(R.id.button39);
        Button button40 = (Button) getActivity().findViewById(R.id.button40);

            button38.setEnabled(true);
            button38.setClickable(false);
            button38.setBackgroundColor(GREEN);

            button39.setEnabled(false);
            button39.setClickable(false);
            button39.setBackgroundResource(android.R.drawable.btn_default);

            button40.setEnabled(false);
            button40.setClickable(false);
            button40.setBackgroundResource(android.R.drawable.btn_default);
    }

    public void nebst() {

        Button button38 = (Button) getActivity().findViewById(R.id.button38);
        Button button39 = (Button) getActivity().findViewById(R.id.button39);
        Button button40 = (Button) getActivity().findViewById(R.id.button40);

            button38.setEnabled(false);
            button38.setClickable(false);
            button38.setBackgroundResource(android.R.drawable.btn_default);

            button39.setEnabled(false);
            button39.setClickable(false);
            button39.setBackgroundColor(Color.parseColor("#9C27B0"));

            button40.setEnabled(false);
            button40.setClickable(false);
            button40.setBackgroundResource(android.R.drawable.btn_default);
    }

    public void adst() {

        Button button38 = (Button) getActivity().findViewById(R.id.button38);
        Button button39 = (Button) getActivity().findViewById(R.id.button39);
        Button button40 = (Button) getActivity().findViewById(R.id.button40);

            button38.setEnabled(false);
            button38.setClickable(false);
            button38.setBackgroundResource(android.R.drawable.btn_default);

            button39.setEnabled(false);
            button39.setClickable(false);
            button39.setBackgroundResource(android.R.drawable.btn_default);

            button40.setEnabled(false);
            button40.setClickable(false);
            button40.setBackgroundColor(Color.parseColor("#9C27B0"));
    }
}