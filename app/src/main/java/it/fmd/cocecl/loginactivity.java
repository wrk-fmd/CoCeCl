package it.fmd.cocecl;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;



public class loginactivity extends MainActivity {

    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        //return netInfo != null && netInfo.isConnectedOrConnecting();
        TextView textView13 = (TextView)findViewById(R.id.textView13);
        ImageView imageView2 = (ImageView)findViewById(R.id.imageView2);
        // connection symbol //
        if (cm.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTED ||

                cm.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTING ||
                cm.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTING ||
                cm.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTED) {
            imageView2.setImageResource(R.drawable.connected64);
            textView13.setText(R.string.con);
            Toast.makeText(this, R.string.con, Toast.LENGTH_LONG).show();
            return true;

        } else if (
                cm.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.DISCONNECTED ||
                        cm.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.DISCONNECTED) {
            imageView2.setImageResource(R.drawable.disconnected64);
            textView13.setText(R.string.discon);
            Toast.makeText(this, R.string.discon, Toast.LENGTH_LONG).show();
            return false;
        }
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        isOnline();

    // LogIn form //
    final int counter = 3;
    final Button dnr_sign_in_button = (Button) findViewById(R.id.dnr_sign_in_button);

    final EditText dnrlogin = (EditText) findViewById(R.id.dnrlogin);
    final EditText password = (EditText) findViewById(R.id.password);

    dnr_sign_in_button.setOnClickListener(new View.OnClickListener()

    {
        @Override
        public void onClick (View v){
        if (dnrlogin.getText().toString().equals("1460") && password.getText().toString().equals("admin")) {

            //starts info activity//
            Intent iinfoact = new Intent(getApplicationContext(), info.class);
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
}
}