package it.fmd.cocecl.gmapsnav.gpstracker;

import android.os.AsyncTask;
import android.util.Log;

import com.google.api.client.http.HttpResponse;

import org.apache.http.params.HttpParams;

import java.io.IOException;

public class SendGPSLoc /*extends AsyncTask<String, String, Boolean>*/ {

    public SendGPSLoc() {
        // TODO Auto-generated constructor stub
    }
/*
    @Override
    protected Boolean doInBackground(String... arg0) {

        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("location", arg0[1]));

        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost(arg0[0]);
        HttpParams httpParameters = new BasicHttpParams();

        httpclient = new DefaultHttpClient(httpParameters);

        try {
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

            HttpResponse response;
            response = httpclient.execute(httppost);
            StatusLine statusLine = response.getStatusLine();
            if (statusLine.getStatusCode() == HttpStatus.SC_OK) {

                Log.e("Google", "Server Responded OK");

            } else {

                response.getEntity().getContent().close();
                throw new IOException(statusLine.getReasonPhrase());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }*/
}
