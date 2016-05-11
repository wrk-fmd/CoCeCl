package it.fmd.cocecl;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.preference.ListPreference;
import android.support.design.widget.TabLayout;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

import it.fmd.cocecl.fragments.SettingsFragment;


public class SettingsActivity extends Activity implements SharedPreferences.OnSharedPreferenceChangeListener {
    private View mDecorView;

    private static final String TAG = "SettingsActivity";

    private String language = "de_DE";
    Locale locale_de;
    Locale locale_en;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_settings);

        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new SettingsFragment())
                .commit();
        //setDefaultLocale();
    }

    private void setDefaultLocale() {
        locale_de = new Locale(language);
        Locale.setDefault(locale_de);
        Configuration config_de = new Configuration();
        config_de.locale = locale_de;
        getBaseContext().getResources().updateConfiguration(config_de, getBaseContext().getResources().getDisplayMetrics());
    }

    public void onSharedPreferenceChanged(SharedPreferences preferences,
                                          String key) {

    }


/*
        // (Toggle) Fullscreen START
        mDecorView = getWindow().getDecorView();

        Switch sw3 = (Switch) getWindow().findViewById(R.id.switch3);

        sw3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {

                    // The toggle is enabled
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
                    getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
                }
            }

        });

        primarylang();
    }

    public void primarylang() {

        Locale locale_de = new Locale("de");
        Locale.setDefault(locale_de);
        Configuration config_de = new Configuration();
        config_de.locale = locale_de;
        getBaseContext().getResources().updateConfiguration(config_de, getBaseContext().getResources().getDisplayMetrics());
    }

    public void changelang(View v) {

        if (v.getId() == R.id.button37) {

            AlertDialog.Builder dlgbuilder = new AlertDialog.Builder(SettingsActivity.this);
            dlgbuilder.setTitle("change language");
            dlgbuilder.setItems(new CharSequence[]
                            {"English", "Deutsch", "Cancel"},

                    new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int which) {

                            switch (which) {
                                case 0:

                                    Locale locale_en = new Locale("en");
                                    Locale.setDefault(locale_en);
                                    Configuration config_en = new Configuration();
                                    config_en.locale = locale_en;
                                    getBaseContext().getResources().updateConfiguration(config_en, getBaseContext().getResources().getDisplayMetrics());

                                    SettingsActivity.this.setContentView(R.layout.activity_settings);//reset layout

                                    break;

                                case 1:

                                    Locale locale_de = new Locale("de");
                                    Locale.setDefault(locale_de);
                                    Configuration config_de = new Configuration();
                                    config_de.locale = locale_de;
                                    getBaseContext().getResources().updateConfiguration(config_de, getBaseContext().getResources().getDisplayMetrics());

                                    SettingsActivity.this.setContentView(R.layout.activity_settings);//reset layout

                                    break;

                                case 2:
                                    Toast.makeText(SettingsActivity.this, "no change", Toast.LENGTH_SHORT).show();
                                    break;
                            }
                        }
                    });

            dlgbuilder.create().show();

        }
    }

    public void mapsswitch() {

        Switch mapsw = (Switch) getWindow().findViewById(R.id.switch5);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);

        //tabLayout.removeTab(tabLayout.removeTab();

        mapsw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // enable maps fragment
                } else {
                    // disable maps fragment
                }
            }

        });
    }

    public void soundsw() {
        Switch sndsw = (Switch) getWindow().findViewById(R.id.switch4);

        sndsw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // enable app sounds
                } else {
                    // disable app sounds
                }
            }

        });
    }

    protected void onStop(Bundle savedInstanceState) {
        super.onStop();

    }

    // Set connection icons visible on fullscreen (optional)
    public void iconsvisibility_fullscreen() {

        //requestWindowFeature(Window.FEATURE_NO_TITLE); getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        TextView wifitext = (TextView) findViewById(R.id.textView7);
        TextView mobiletext = (TextView) findViewById(R.id.textView83);

        Window window = this.getWindow();
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        WindowManager.LayoutParams lp = getWindow().getAttributes();
        int i = lp.flags;

        if (i == 1) {

            wifitext.setVisibility(View.VISIBLE);
            mobiletext.setVisibility(View.VISIBLE);

        } else {

            wifitext.setVisibility(View.INVISIBLE);
            mobiletext.setVisibility(View.INVISIBLE);

        }
    }

    public void toggleHideyBar() {

        // BEGIN_INCLUDE (get_current_ui_flags)
        // The UI options currently enabled are represented by a bitfield.
        // getSystemUiVisibility() gives us that bitfield.
        int uiOptions = getWindow().getDecorView().getSystemUiVisibility();
        int newUiOptions = uiOptions;
        // END_INCLUDE (get_current_ui_flags)
        // BEGIN_INCLUDE (toggle_ui_flags)
        boolean isImmersiveModeEnabled =
                ((uiOptions | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY) == uiOptions);
        if (isImmersiveModeEnabled) {
            Log.i(TAG, "Turning immersive mode mode off. ");
        } else {
            Log.i(TAG, "Turning immersive mode mode on.");
        }

        // Navigation bar hiding:  Backwards compatible to ICS.
        if (Build.VERSION.SDK_INT >= 14) {
            newUiOptions ^= View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        }

        // Status bar hiding: Backwards compatible to Jellybean
        if (Build.VERSION.SDK_INT >= 16) {
            newUiOptions ^= View.SYSTEM_UI_FLAG_FULLSCREEN;
        }

        // Immersive mode: Backward compatible to KitKat.
        // Note that this flag doesn't do anything by itself, it only augments the behavior
        // of HIDE_NAVIGATION and FLAG_FULLSCREEN.  For the purposes of this sample
        // all three flags are being toggled together.
        // Note that there are two immersive mode UI flags, one of which is referred to as "sticky".
        // Sticky immersive mode differs in that it makes the navigation and status bars
        // semi-transparent, and the UI flag does not get cleared when the user interacts with
        // the screen.
        if (Build.VERSION.SDK_INT >= 18) {
            newUiOptions ^= View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        }

        getWindow().getDecorView().setSystemUiVisibility(newUiOptions);
        //END_INCLUDE (set_ui_flags)
    }*/

}

