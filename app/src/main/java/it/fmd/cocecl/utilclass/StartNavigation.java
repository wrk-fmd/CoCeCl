package it.fmd.cocecl.utilclass;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import it.fmd.cocecl.MainActivity;
import it.fmd.cocecl.R;

public class StartNavigation extends MainActivity {

    // Navigate to provided address //
    // TODO: choose between built-in maps api and own navigation app
    public void navigate(View view) {

        final TextView botext = (TextView) findViewById(R.id.bofield);
        final TextView aotext = (TextView) findViewById(R.id.aofield);
        final TextView custom = (TextView) findViewById(R.id.commaddress);

        switch (view.getId()) {
            case R.id.button18:
                if (botext.getText().toString().trim().length() > 0) {

                    String navadress = "google.navigation:" + botext.getText().toString();
                    Intent nav = new Intent(android.content.Intent.ACTION_VIEW);
                    nav.setData(Uri.parse(navadress));
                    startActivity(nav);

                } else {
                    Toast.makeText(getApplicationContext(), "Kein Berufungsort eingetragen!", Toast.LENGTH_LONG).show();
                }
                break;

            case R.id.button19:
                if (aotext.getText().toString().trim().length() > 0) {

                    String navadress = "google.navigation:" + aotext.getText().toString();
                    Intent nav = new Intent(android.content.Intent.ACTION_VIEW);
                    nav.setData(Uri.parse(navadress));
                    startActivity(nav);

                } else {
                    Toast.makeText(getApplicationContext(), "Kein Abgabeort eingetragen!", Toast.LENGTH_LONG).show();
                }
                break;

            case R.id.button45: //commfragment, custom navigation
                if (custom.getText().toString().trim().length() > 0) {

                    String navadress = "google.navigation:" + custom.getText().toString();
                    Intent nav = new Intent(android.content.Intent.ACTION_VIEW);
                    nav.setData(Uri.parse(navadress));
                    startActivity(nav);

                } else {
                    Toast.makeText(getApplicationContext(), "Kein POI ausgewählt!", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }
}

/*
    // City Map View //
    // user can choose between leaflet(webview) and google maps(fragment api)
        public void showmap(View v) {
            if (v.getId() == R.id.button30) {

                AlertDialog.Builder alert = new AlertDialog.Builder(this);
                alert.setTitle("Stadtplan");

                WebView gisView = new WebView(this);
                gisView.getSettings().setJavaScriptEnabled(true);
                gisView.getSettings().getAllowFileAccessFromFileURLs();
                gisView.getSettings().setAllowUniversalAccessFromFileURLs(true);
                gisView.getSettings().setGeolocationEnabled(true);

                gisView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
                gisView.getSettings().setBuiltInZoomControls(true);

                gisView.setWebViewClient(new GeoWebViewActivity.GeoWebViewClient());
                gisView.setWebChromeClient(new GeoWebViewActivity.GeoWebChromeClient());

                gisView.loadUrl("file:///android_asset/leaflet.html");
                gisView.setWebViewClient(new WebViewClient() {
                    @Override
                    public boolean shouldOverrideUrlLoading(WebView view, String url) {
                        view.loadUrl(url);

                        return true;
                    }
                });

                alert.setView(gisView);
                alert.setNegativeButton("Close", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });
                alert.show();
            }
        }
/*
                WebView gisView = (WebView) findViewById(R.id.gisView);

                AlertDialog.Builder dlgBuilder = new AlertDialog.Builder(MainActivity.this);
                dlgBuilder.setTitle("Stadtplan");
                dlgBuilder.setView(gisView);

                gisView.getSettings().setJavaScriptEnabled(true);
                gisView.getSettings().getAllowFileAccessFromFileURLs();
                gisView.getSettings().setAllowUniversalAccessFromFileURLs(true);
                gisView.getSettings().setGeolocationEnabled(true);

                gisView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
                gisView.getSettings().setBuiltInZoomControls(true);

                gisView.setWebViewClient(new GeoWebViewActivity.GeoWebViewClient());
                gisView.setWebChromeClient(new GeoWebViewActivity.GeoWebChromeClient());

                gisView.loadUrl("file:///android_asset/leaflet.html");

                dlgBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
/*
                MapFragment mapFragment = (MapFragment) this.getFragmentManager().findFragmentById(R.id.map);
                if (mapFragment != null)
                    this.getFragmentManager().beginTransaction().remove(mapFragment).commit();
*//*
                    }
                });
                dlgBuilder.create().show();
            }
        }

    public void removeWebView() {

        WebView gisView = (WebView) findViewById(R.id.gisView);
        gisView.removeAllViews();

        if(gisView != null) {
            gisView.clearHistory();
            gisView.clearCache(true);
            gisView.loadUrl("about:blank");
            gisView.freeMemory();
            gisView.pauseTimers();
            gisView = null;
        }

    }

    */
