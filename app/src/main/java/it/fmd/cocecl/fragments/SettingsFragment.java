package it.fmd.cocecl.fragments;


import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Locale;

import it.fmd.cocecl.R;
import it.fmd.cocecl.utilclass.Phonecalls;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsFragment extends PreferenceFragment {

    private static final String TAG = "SettingsActivity";

    public SettingsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Load the preferences from an XML resource
        addPreferencesFromResource(R.xml.preferences);

        findPreference("pref_callfmdit");
        Preference callfmdit = findPreference("pref_callfmdit");

        callfmdit.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                Phonecalls phc = new Phonecalls();
                phc.callfmdit(getActivity());
                return false;
            }
        });
    }
}
