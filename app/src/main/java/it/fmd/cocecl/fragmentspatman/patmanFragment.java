package it.fmd.cocecl.fragmentspatman;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import it.fmd.cocecl.R;


public class patmanFragment extends Fragment {

    public patmanFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_patman2, container, false);
    }
}
