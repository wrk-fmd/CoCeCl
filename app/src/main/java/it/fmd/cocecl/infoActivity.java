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

//START PAGE after LogIn//
public class infoActivity extends MainActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

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

        assert mActionBar != null;
        mActionBar.setCustomView(mCustomView);
        mActionBar.setDisplayShowCustomEnabled(true);

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

        // get connection state / write into action bar / class connectionmanager
        connectionmanager conman = new connectionmanager();

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