package it.fmd.cocecl;

import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;

import it.fmd.cocecl.utilclass.SessionManagement;

//START PAGE after LogIn//
public class InfoActivity extends MainActivity {

    private TextView ambname;
    private TextView unitname;
    private Button start;


    private TextView txtName;
    private TextView txtEmail;
    //private Button btnLogout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        loadambinf();

        ambname = (TextView) findViewById(R.id.textView2);
        unitname = (TextView) findViewById(R.id.textView3);
        start = (Button) findViewById(R.id.button14);

        //removed
        //txtName = (TextView) findViewById(R.id.textView95);
        //txtEmail = (TextView) findViewById(R.id.textView96);

        //btnLogout = (Button) findViewById(R.id.btnLogout);

        // Displaying the user details from login on the screen
        //txtName.setText(name);
        //txtEmail.setText(email);

        //shows version//
        PackageInfo pInfo = null;
        try {
            pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        String version = pInfo.versionName;
        TextView versionText = (TextView) findViewById(R.id.versionText);
        versionText.setText("Version: " + version);

        //redirects to START page btn14 & SETTINGS page btn15//
        acbtnClick();


        if ((ambname.getText().toString().trim().length() > 0) || (unitname.getText().toString().trim().length() > 0)) {

            start.setEnabled(true);
            start.setClickable(true);

        } else {
            //TODO: remove // when app finished
            //start.setEnabled(false);
            //start.setClickable(false);
        }
    }

    public void acbtnClick() {

        Button button14 = (Button) findViewById(R.id.button14);
        button14.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent imain = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(imain);
            }
        });

        Button button15 = (Button) findViewById(R.id.button15);
        button15.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg1) {
                Intent isett = new Intent(getApplicationContext(), SettingsActivity.class);
                startActivity(isett);
            }
        });
    }

    // stop/exit button //

    public void exitbtn(View v) {
        final SessionManagement SM = new SessionManagement(getApplicationContext());

        if (v.getId() == R.id.button20) {
            Button button20 = (Button) findViewById(R.id.button20);
            button20.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    SM.logoutUser();
                    finish();
                    System.exit(0);
                }
            });

        }
    }

    public void btnunitinfo(View v) {
        if (v.getId() == R.id.button63) {
            unitinfo();
        }
    }

    public void unitinfo() {

        // Create custom dialog object
        final Dialog dialog = new Dialog(InfoActivity.this);
        // Include dialog.xml file
        dialog.setContentView(R.layout.unit_info_layout);
        // Set dialog title
        dialog.setTitle("Einheit - Information");


        dialog.show();
/*
            Button declineButton = (Button) dialog.findViewById(R.id.declineButton);
            // if decline button is clicked, close the custom dialog
            declineButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Close dialog
                    dialog.dismiss();
                }
            });*/
    }

    public void loadambinf() {
        WebView wv;
        wv = (WebView) findViewById(R.id.webView);
        wv.setVisibility(View.VISIBLE);
        wv.loadUrl("file:///android_asset/ambinf.html");
    }
}
