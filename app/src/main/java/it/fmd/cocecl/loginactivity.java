package it.fmd.cocecl;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.net.URL;
import java.net.URLConnection;


public class loginactivity extends MainActivity {

   //private connectionmanager connman = new connectionmanager();


    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        //return netInfo != null && netInfo.isConnectedOrConnecting();
        TextView textView13 = (TextView) findViewById(R.id.textView13);
        ImageView imageView2 = (ImageView) findViewById(R.id.imageView2);
        // connection symbol //
        if (cm.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTED ||

                cm.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTING ||
                cm.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTING ||
                cm.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTED) {

            imageView2.setImageResource(R.drawable.ic_network_cell_black_24dp);
            textView13.setText(R.string.con);
            Toast.makeText(this, R.string.con, Toast.LENGTH_LONG).show();
            return true;

        } else if (
                cm.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.DISCONNECTED ||
                        cm.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.DISCONNECTED) {

            imageView2.setImageResource(R.drawable.ic_signal_cellular_off_black_24dp);
            textView13.setText(R.string.discon);
            Toast.makeText(this, R.string.discon, Toast.LENGTH_LONG).show();
            return false;
        }
        return false;
    }


    public boolean isConnectedToServer() {

        TextView textView49 = (TextView) findViewById(R.id.textView49);
        ImageView imageView3 = (ImageView) findViewById(R.id.imageView3);
        //ImageView imageView4 = (ImageView) findViewById(R.id.imageView3);

        // URL MLS Server
        try {
            URL myUrl = new URL("http://google.at");
            URLConnection connection = myUrl.openConnection();
            connection.setConnectTimeout(10 * 1000);
            connection.connect();

            imageView3.setImageResource(R.drawable.ic_network_cell_black_24dp);
            //imageView4.setImageResource(R.drawable.ic_network_cell_black_24dp);
            textView49.setText(R.string.mlscon);
            Toast.makeText(this, R.string.mlscon, Toast.LENGTH_LONG).show();
            Log.wtf("Connection to MLS established", "Success!");
            return true;

        } catch (Exception e) {

            imageView3.setImageResource(R.drawable.ic_signal_cellular_off_black_24dp);
            //imageView4.setImageResource(R.drawable.ic_signal_cellular_off_black_24dp);
            textView49.setText(R.string.mlsdiscon);
            Toast.makeText(this, R.string.mlsdiscon, Toast.LENGTH_LONG).show();
            return false;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        isOnline();
        isConnectedToServer();

       // connman.isOnline();
       // connman.isConnectedToServer();

    // LogIn form //
    final int counter = 3;
    final Button dnr_sign_in_button = (Button) findViewById(R.id.dnr_sign_in_button);

    final EditText dnrlogin = (EditText) findViewById(R.id.dnrlogin);
    final EditText password = (EditText) findViewById(R.id.password);

    dnr_sign_in_button.setOnClickListener(new View.OnClickListener()

    {
        @Override
        public void onClick (View v){
        if (dnrlogin.getText().toString().equals("0000") && password.getText().toString().equals("admin")) {

            //starts infoActivity activity//
            Intent iinfoact = new Intent(getApplicationContext(), infoActivity.class);
            startActivity(iinfoact);

            Toast.makeText(getApplicationContext(), "Starting Application", Toast.LENGTH_SHORT).show();
        } else {

            Toast.makeText(getApplicationContext(), "Wrong Credentials", Toast.LENGTH_SHORT).show();


            if (counter == 0) {
                dnr_sign_in_button.setEnabled(false);
            }
        }
    }
    }

    );
       /* {
            //Displaying TextInputLayout Error
            TextInputLayout lNameLayout = (TextInputLayout) findViewById(R.id.dnrlog);
            lNameLayout.setErrorEnabled(true);
            lNameLayout.setError("Min 2 chars required");

            //Displaying both TextInputLayout and EditText Errors
            TextInputLayout phoneLayout = (TextInputLayout) findViewById(R.id.phoneLayout);
            phoneLayout.setErrorEnabled(true);
            phoneLayout.setError("Please enter a phone number");
            EditText phone = (EditText) findViewById(R.id.phone);
            phone.setError("Required");
        }*/
    }
}
