package it.fmd.cocecl;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.os.Bundle;
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
/*
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.en:
                Locale locale = new Locale("en");
                Locale.setDefault(locale);
                Configuration config = new Configuration();
                config.locale = locale;
                getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
                Toast.makeText(this, "Locale in English !", Toast.LENGTH_LONG).show();
                break;

            case R.id.pt:
                Locale locale2 = new Locale("pt");
                Locale.setDefault(locale2);
                Configuration config2 = new Configuration();
                config2.locale = locale2;
                getBaseContext().getResources().updateConfiguration(config2, getBaseContext().getResources().getDisplayMetrics());

                Toast.makeText(this, "Locale in Portugal !", Toast.LENGTH_LONG).show();
                break;

            case R.id.es:
                Locale locale3 = new Locale("es");
                Locale.setDefault(locale3);
                Configuration config3 = new Configuration();
                config3.locale = locale3;
                getBaseContext().getResources().updateConfiguration(config3, getBaseContext().getResources().getDisplayMetrics());

                Toast.makeText(this, "Locale in Spain !", Toast.LENGTH_LONG).show();
                break;


        }
        return super.onOptionsItemSelected(item);
    }
*/
    protected void onStop(Bundle savedInstanceState) {
        super.onStop();

    }
    }

