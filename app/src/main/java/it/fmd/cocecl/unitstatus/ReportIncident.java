package it.fmd.cocecl.unitstatus;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import it.fmd.cocecl.R;
import it.fmd.cocecl.utilclass.GPSGeolocation;
import it.fmd.cocecl.utilclass.GPSManager;

/**
 * Report new incident on route ZBO & ZAO
 */

public class ReportIncident implements View.OnClickListener {

    public Activity activity;

    public ReportIncident(Activity _activity) {

        this.activity = _activity;
    }

    @Override
    public void onClick(View v) {

        RelativeLayout reportincident = (RelativeLayout) activity.getLayoutInflater().inflate(R.layout.reportincident, null);

        final TextView textView86 = (TextView) reportincident.findViewById(R.id.textView86);
        final TextView textView93 = (TextView) reportincident.findViewById(R.id.textView93);
        final Button button42 = (Button) activity.findViewById(R.id.button42);

        AlertDialog.Builder dlgBuilder = new AlertDialog.Builder(activity);
        dlgBuilder.setMessage("Neuen Einsatz bei derzeitiger Position melden?");
        dlgBuilder.setCancelable(false);

        button42.setClickable(false);

        dlgBuilder.setView(reportincident);

        GPSManager gps = new GPSManager(activity);
        double latitude = gps.getLatitude();
        double longitude = gps.getLongitude();

        GPSGeolocation.getAddressFromLocation(latitude, longitude,
                activity, new GeocoderHandler());

        textView86.setText("lat: " + latitude);
        textView93.setText("lon: " + longitude);

        dlgBuilder.setPositiveButton("Ja", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        onPositive();
                        dialog.cancel();
                    }
                }

        );

        dlgBuilder.setNegativeButton("Nein", new DialogInterface.OnClickListener()

                {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.cancel();

                    }
                }

        );

        AlertDialog alert = dlgBuilder.create();
        alert.show();
        button42.setClickable(true);

        // remove layout
        View viewToRemove = this.activity.findViewById(R.id.reportincidentrelayout);
        if (viewToRemove != null && viewToRemove.getParent() != null && viewToRemove instanceof ViewGroup)
            ((ViewGroup) viewToRemove.getParent()).removeView(viewToRemove);


    }

    public void onPositive() {

        final TextView textView112 = (TextView) this.activity.findViewById(R.id.textView112);
        final Button button42 = (Button) this.activity.findViewById(R.id.button42);

        button42.setEnabled(false);
        button42.setClickable(false);
        //button42.setBackgroundColor(YELLOW);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            button42.setBackground(this.activity.getDrawable(R.drawable.button_yellow_pressed));
        }

        Handler h = new Handler();
        h.postDelayed(new

                              Runnable() {
                                  @Override
                                  public void run() {
                                      refreshDialog();
                                  }
                              }

                , 30000);

        Toast.makeText(this.activity, "Neuen Einsatz an Leitstelle gemeldet", Toast.LENGTH_SHORT).show();
        textView112.setVisibility(View.VISIBLE);
        textView112.setText("Einsatz gemeldet");

    }

    public void refreshDialog() {
        RelativeLayout reportincident = (RelativeLayout) this.activity.getLayoutInflater().inflate(R.layout.reportincident, null);

        final EditText editText24 = (EditText) reportincident.findViewById(R.id.editText24);
        final TextView textView86 = (TextView) reportincident.findViewById(R.id.textView86);
        final TextView textView93 = (TextView) reportincident.findViewById(R.id.textView93);
        final TextView textView112 = (TextView) this.activity.findViewById(R.id.textView112);
        final Button button42 = (Button) this.activity.findViewById(R.id.button42);

        button42.setEnabled(true);
        button42.setClickable(true);
        //button42.setBackgroundColor(Color.parseColor("#bdbdbd"));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            button42.setBackground(this.activity.getDrawable(R.drawable.custom_button_normal));
        }

        editText24.setText("");
        textView86.setText("");
        textView93.setText("");
        textView112.setText("");
        textView112.setVisibility(View.GONE);
    }

    public class GeocoderHandler extends Handler {

        RelativeLayout reportincident = (RelativeLayout) activity.getLayoutInflater().inflate(R.layout.reportincident, null);
        final EditText editText24 = (EditText) reportincident.findViewById(R.id.editText24);

        @Override
        public void handleMessage(Message message) {
            String locationAddress;
            switch (message.what) {
                case 1:
                    Bundle bundle = message.getData();
                    locationAddress = bundle.getString("address");
                    break;
                default:
                    locationAddress = null;
            }
            editText24.setText(locationAddress);
        }
    }
}

