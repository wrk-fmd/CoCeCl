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

    private TextView txtName;
    private TextView txtEmail;
    //private Button btnLogout;

    private SQLiteHandler db;
    private SessionManager session;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

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
        // custom action bar //

        ActionBar mActionBar = getActionBar();
        //mActionBar.setDisplayShowHomeEnabled(false);
        //mActionBar.setDisplayShowTitleEnabled(false);
        LayoutInflater mInflater = LayoutInflater.from(this);

        View mCustomView = mInflater.inflate(R.layout.custom_actionbar, null);

        ImageButton imageButton = (ImageButton) mCustomView.findViewById(R.id.imageButton);
        imageButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Refresh Clicked!",
                        Toast.LENGTH_SHORT).show();
            }
        });
/*
        assert mActionBar != null;
        mActionBar.setCustomView(mCustomView);
        mActionBar.setDisplayShowCustomEnabled(true);
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
    }


    public void onViewCreated(View v, Bundle savedInstanceState) {

        // get connection state / write into action bar / class ConnectionManager
        ConnectionManager conman = new ConnectionManager();

        ImageView imageView_con = (ImageView) findViewById(R.id.imageView_con);

        if (conman.isOnline()) {

            imageView_con.setImageResource(R.drawable.connected64);
            Toast.makeText(this, R.string.con, Toast.LENGTH_LONG).show();

        } else {

            imageView_con.setImageResource(R.drawable.disconnected64);
            Toast.makeText(this, R.string.discon, Toast.LENGTH_LONG).show();
        }

        // get connection to server state

        ImageView imageView_mlscon = (ImageView) findViewById(R.id.imageView_mlscon);

        if (conman.isConnectedToServer()) {

            imageView_mlscon.setImageResource(R.drawable.connected64);
            Toast.makeText(this, R.string.mlscon, Toast.LENGTH_LONG).show();

        } else {

            imageView_mlscon.setImageResource(R.drawable.disconnected64);
            Toast.makeText(this, R.string.mlsdiscon, Toast.LENGTH_LONG).show();

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