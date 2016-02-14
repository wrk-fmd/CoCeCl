package it.fmd.cocecl;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.view.View;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import java.util.Locale;


public class SettingsActivity extends MainActivity {
    private View mDecorView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

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
}

