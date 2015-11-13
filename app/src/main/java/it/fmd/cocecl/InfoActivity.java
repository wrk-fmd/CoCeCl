package it.fmd.cocecl;

import android.app.ActionBar;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

import it.fmd.cocecl.helper.SQLiteHandler;
import it.fmd.cocecl.helper.SessionManager;
import it.fmd.cocecl.utilclass.ConnectionManager;

//START PAGE after LogIn//
public class InfoActivity extends MainActivity {

    private TextView ambname;
    private TextView unitname;
    private Button start;


    private TextView txtName;
    private TextView txtEmail;
    //private Button btnLogout;

    private SQLiteHandler db;
    private SessionManager session;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        ambname = (TextView)findViewById(R.id.textView2);
        unitname = (TextView)findViewById(R.id.textView3);
        start = (Button)findViewById(R.id.button14);

        txtName = (TextView) findViewById(R.id.textView95);
        txtEmail = (TextView) findViewById(R.id.textView96);
        //btnLogout = (Button) findViewById(R.id.btnLogout);

        // SqLite database handler
        db = new SQLiteHandler(getApplicationContext());

        // session manager
        session = new SessionManager(getApplicationContext());
/*
        if (!session.isLoggedIn()) {
            logoutUser();
        }
*/
        // Fetching user details from sqlite
        HashMap<String, String> user = db.getUserDetails();

        String name = user.get("name");
        String email = user.get("email");

        // Displaying the user details on the screen
        txtName.setText(name);
        txtEmail.setText(email);
/*
        // Logout button click event
        btnLogout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                logoutUser();
            }
        });
*/
/*
    /**
     * Logging out the user. Will set isLoggedIn flag to false in shared
     * preferences Clears the user data from sqlite users table
     **
    private void logoutUser() {
        session.setLogin(false);

        db.deleteUsers();

        // Launching the login activity
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
*/
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
        if (v.getId() == R.id.button20) {
            Button button20 = (Button) findViewById(R.id.button20);
            button20.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish(); System.exit(0);
                }
            });
        }
    }
}