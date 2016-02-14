package it.fmd.cocecl.utilclass;

import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Environment;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Properties;

import it.fmd.cocecl.R;

/**
 * Get Config file from Server
 */
public class GetConfigFile extends AsyncTask<String, Void, String> {

    protected String doInBackground(String... urls) {

        String path = Environment.getExternalStorageDirectory() + "/cocecl_config";

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
            loadConfig();

        } catch (IOException e) {

        }
    }

    public void loadConfig() {

        // either from assets or raw
        Resources resources = this.getResources();
        AssetManager assetManager = resources.getAssets();

        // Read from the /assets directory
        try {
            InputStream inputStream = assetManager.open("cocecl.properties");
            Properties properties = new Properties();
            properties.load(inputStream);
            System.out.println("The properties are now loaded");
            System.out.println("properties: " + properties);
        } catch (IOException e) {
            System.err.println("Failed to open property file");
            e.printStackTrace();
        }
        // Read from the /res/raw directory
        try {
            InputStream rawResource = resources.openRawResource(R.raw.cocecl_config);
            Properties properties = new Properties();
            properties.load(rawResource);
            System.out.println("The properties are now loaded");
            System.out.println("properties: " + properties);
        } catch (Resources.NotFoundException e) {
            System.err.println("Did not find raw resource: " + e);
        } catch (IOException e) {
            System.err.println("Failed to open property file");
        }

        // write properties from file to setting
        //TODO: config
    }
}

