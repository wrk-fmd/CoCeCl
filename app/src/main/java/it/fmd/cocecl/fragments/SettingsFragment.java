package it.fmd.cocecl.fragments;


import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.SwitchPreference;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

import java.util.Locale;

import it.fmd.cocecl.R;
import it.fmd.cocecl.utilclass.Phonecalls;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsFragment extends PreferenceFragment implements SharedPreferences.OnSharedPreferenceChangeListener {
    private View mDecorView;

    private static final String TAG = "SettingsActivity";

    private static final String KEY_FULLSCREEN = "pref_fullscreen";
    private static final String KEY_GPS = "pref_gps";
    private static final String KEY_RADIO = "pref_radio";
    private static final String KEY_ALARMSOUNDS = "pref_alarmsound";
    private static final String KEY_LIGHT_DARK = "pref_darklight";

    private static final String KEY_AUTONAVIGATION = "pref_autorouting";

    private static final String KEY_LANGUAGE = "pref_lang";

    //Preferences
    Preference call_fmdit;
    ListPreference langlp;
    SwitchPreference sw_fullscreen;


    //Language Setting
    private String language;
    Locale locale_de;
    Locale locale_en;

    private static final String lang_de = "de-DE";
    private static final String lang_en = "en-US";

    Configuration config_de;
    //Language Setting END

    public SettingsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Load the preferences from an XML resource
        addPreferencesFromResource(R.xml.preferences);


        //Call fmd-it in preference screen
        findPreference("pref_callfmdit");
        call_fmdit = findPreference("pref_callfmdit");

        call_fmdit.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                Phonecalls phc = new Phonecalls();
                phc.callfmdit(getActivity());
                return false;
            }
        });

        mDecorView = getActivity().getWindow().getDecorView();

        loadPreferences();
    }

    private void loadPreferences() {
        sw_fullscreen = (SwitchPreference) findPreference(KEY_FULLSCREEN);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences preferences, String key) {
        Log.i(TAG, "key" + key);

        if (key.equals(KEY_LANGUAGE)) {
            langlp = (ListPreference) findPreference(KEY_LANGUAGE);
            language = langlp.getValue();
            Log.i(TAG, language);

            switch (language) {
                case lang_de:
                    locale_de = new Locale(language);
                    Locale.setDefault(locale_de);
                    config_de = new Configuration();
                    config_de.locale = locale_de;
                    getActivity().getResources().updateConfiguration(config_de, getActivity().getResources().getDisplayMetrics());
                    break;
                case lang_en:
                    locale_en = new Locale(language);
                    Locale.setDefault(locale_en);
                    Configuration config_en = new Configuration();
                    config_en.locale = locale_en;
                    getActivity().getResources().updateConfiguration(config_en, getActivity().getResources().getDisplayMetrics());
                default:
                    locale_de = new Locale(language);
                    Locale.setDefault(locale_de);
                    config_de = new Configuration();
                    config_de.locale = locale_de;
                    getActivity().getResources().updateConfiguration(config_de, getActivity().getResources().getDisplayMetrics());
                    break;
            }
        }

        if (key.equals(KEY_FULLSCREEN)) {
            if (preferences.getBoolean(key, false)) {
                /*
                SharedPreferences.Editor edit = preferences.edit();

                edit.putBoolean(KEY_FULLSCREEN, false);
                edit.apply();

                sw_fullscreen.setChecked(false);
*/
                mDecorView.setSystemUiVisibility(
                        View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION //hides nav bar
                                // | View.SYSTEM_UI_FLAG_FULLSCREEN // hides statusFragment bar
                                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                                | View.INVISIBLE);
            } else {
                // The toggle is disabled
                mDecorView.setSystemUiVisibility(0);
                getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
            }
        }

        if (key.equals(KEY_AUTONAVIGATION)) {
            if (preferences.getBoolean(key, false)) {

            }
        }
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onResume() {
        super.onResume();
        getPreferenceManager().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);

    }

    @Override
    public void onPause() {
        getPreferenceManager().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
        super.onPause();
    }
}
