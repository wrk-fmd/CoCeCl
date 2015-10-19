package it.fmd.cocecl;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.net.URL;
import java.net.URLConnection;


public class connectionmanager extends loginactivity {

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

            imageView3.setImageResource(R.drawable.connected64);
            //imageView4.setImageResource(R.drawable.connected64);
            textView49.setText(R.string.mlscon);
            Toast.makeText(this, R.string.mlscon, Toast.LENGTH_LONG).show();
            Log.wtf("Connection to MLS established", "Success!");
            return true;

        } catch (Exception e) {

            imageView3.setImageResource(R.drawable.disconnected64);
            //imageView4.setImageResource(R.drawable.disconnected64);
            textView49.setText(R.string.mlsdiscon);
            Toast.makeText(this, R.string.mlsdiscon, Toast.LENGTH_LONG).show();
            return false;
        }
    }

    /*
     public boolean isConnectedToServer() {

     TextView textView49 = (TextView)findViewById(R.id.textView13);
     ImageView imageView3 = (ImageView)findViewById(R.id.imageView2);

     try{
     URL myUrl = new URL("https://saturn.kuga28.at"); // String url "https://saturn.kuga28.at"
     URLConnection connection = myUrl.openConnection();
     connection.setRequestProperty("Connection", "close");
     connection.setConnectTimeout(10*1000); //10s int timeout 10*1000
     connection.connect();

     if (connection.getResponseCode() == 200) {        // 200 = successful response
     Log.wtf("Connection to MLS established", "Success!");
     return true;
     } else {
     return false;
     }

     imageView3.setImageResource(R.drawable.connected64);
     textView49.setText(R.string.mlscon);
     Toast.makeText(this, R.string.mlscon, Toast.LENGTH_LONG).show();
     return true;

     } catch (Exception e) {

     // exceptions

     } catch (MalformedURLException e1) {
     return false;
     } catch (IOException e) {
     return false;
     }

     imageView3.setImageResource(R.drawable.disconnected64);
     textView49.setText(R.string.mlsdiscon);
     Toast.makeText(this, R.string.mlsdiscon, Toast.LENGTH_LONG).show();
     return false;
     }
     */
}
