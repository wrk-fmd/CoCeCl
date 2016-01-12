package it.fmd.cocecl.utilclass;

import android.Manifest;
import android.app.Application;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import it.fmd.cocecl.APPConstants;
import it.fmd.cocecl.MainActivity;
import it.fmd.cocecl.R;

public class Phonecalls extends MainActivity {

    Intent callIntent = new Intent(Intent.ACTION_CALL);
    PackageManager pm = getPackageManager();

    public void lsmaincall() {
        callIntent.setData(Uri.parse("tel:" + APPConstants.mlsmain));
        startActivity(callIntent);

        if (pm.checkPermission(Manifest.permission.CALL_PHONE, getPackageName()) == PackageManager.PERMISSION_GRANTED) {

        } else {

        }
    }

    public void lsbvcall() {
        callIntent.setData(Uri.parse("tel:" + APPConstants.mlsbv));
        startActivity(callIntent);

        if (pm.checkPermission(Manifest.permission.CALL_PHONE, getPackageName()) == PackageManager.PERMISSION_GRANTED) {

        } else {

        }
    }

    public void khcall() {
        //TODO: get number from hospital ao
        callIntent.setData(Uri.parse("tel:" + APPConstants.mlsbv));
        startActivity(callIntent);

        if (pm.checkPermission(Manifest.permission.CALL_PHONE, getPackageName()) == PackageManager.PERMISSION_GRANTED) {

        } else {

        }
    }

    public void chkhcall() {
        // Call btn on commFragment
        TextView commphone = (TextView) findViewById(R.id.commphone);
        String listnumber = commphone.getText().toString();

        if (commphone.getText().toString().trim().length() > 0) {

            callIntent.setData(Uri.parse("tel:" + listnumber));
            startActivity(callIntent);

            if (pm.checkPermission(Manifest.permission.CALL_PHONE, getPackageName()) == PackageManager.PERMISSION_GRANTED) {

            } else {

            }

        } else {
            Toast.makeText(getApplicationContext(), "Keine Nummer ausgewählt!", Toast.LENGTH_LONG).show();
        }
    }
/*
    public void lscall() {
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        PackageManager pm = getPackageManager();
        switch(getId()) {

            case R.id.button17:

                callIntent.setData(Uri.parse("tel:" + APPConstants.mlsbv));
                startActivity(callIntent);

                if (pm.checkPermission(Manifest.permission.CALL_PHONE, getPackageName()) == PackageManager.PERMISSION_GRANTED) {

                } else {

                }
                break;

            case R.id.button47:
                callIntent.setData(Uri.parse("tel:" + APPConstants.mlsmain));
                startActivity(callIntent);

                if (pm.checkPermission(Manifest.permission.CALL_PHONE, getPackageName()) == PackageManager.PERMISSION_GRANTED) {

                } else {

                }
                break;

            case R.id.button43:
                TextView commphone = (TextView)findViewById(R.id.commphone);
                String listnumber = commphone.getText().toString();

                if(commphone.getText().toString().trim().length() > 0) {

                    callIntent.setData(Uri.parse("tel:" + listnumber));
                    startActivity(callIntent);

                    if (pm.checkPermission(Manifest.permission.CALL_PHONE, getPackageName()) == PackageManager.PERMISSION_GRANTED) {

                    } else {

                    }

                } else {
                    Toast.makeText(getApplicationContext(), "Keine Nummer ausgewählt!", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }
    */
}