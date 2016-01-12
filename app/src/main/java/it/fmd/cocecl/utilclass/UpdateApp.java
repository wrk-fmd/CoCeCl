package it.fmd.cocecl.utilclass;


import android.os.AsyncTask;
import android.os.Environment;

import java.io.BufferedInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * Update later over PlayStore
 */

public class UpdateApp extends AsyncTask<String, Void, String> {

    public UpdateApp() {
    }

    protected String doInBackground(String... urls) {

        String path = Environment.getExternalStorageDirectory() + "/cocecl.apk";

        //download the apk from your server and save to sdk card here
        try {
            URL url = new URL(urls[0]);
            URLConnection connection = url.openConnection();
            connection.connect();

            // download the file
            InputStream input = new BufferedInputStream(url.openStream());
            OutputStream output = new FileOutputStream(path);

            byte data[] = new byte[1024];
            int count;
            while ((count = input.read(data)) != -1) {
                output.write(data, 0, count);
            }

            output.flush();
            output.close();
            input.close();
        } catch (Exception e) {
        }

        return path;
    }

    @Override
    protected void onPostExecute(String path) {
        Process process = null;

        try {
            process = Runtime.getRuntime().exec("su");
            DataOutputStream outs = new DataOutputStream(process.getOutputStream());

            String cmd = "pm install -r " + path;

            outs.writeBytes(cmd + "\n");
        } catch (IOException e) {
        }
    }
}

/*
 String url = "http://mls.com/api/protected/cocecl.apk";
 new ApkUpdateAsyncTask().execute(url);
 */

