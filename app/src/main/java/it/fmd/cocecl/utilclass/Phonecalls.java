package it.fmd.cocecl.utilclass;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.net.ConnectException;

import it.fmd.cocecl.APPConstants;
import it.fmd.cocecl.MainActivity;
import it.fmd.cocecl.R;

public class Phonecalls extends MainActivity {

    Intent callIntent = new Intent(Intent.ACTION_CALL);

    public void lsmaincall(Context context) {
        PackageManager pm = context.getPackageManager();
        callIntent.setData(Uri.parse("tel:" + APPConstants.mlsmain));
        context.startActivity(callIntent);
/*
        if (pm.checkPermission(Manifest.permission.CALL_PHONE, getPackageName()) == PackageManager.PERMISSION_GRANTED) {

        } else {

        }*/
    }

    public void lsbvcall(Context context) {
        PackageManager pm = context.getPackageManager();
        callIntent.setData(Uri.parse("tel:" + APPConstants.mlsbv));
        context.startActivity(callIntent);

        /*
        if (pm.checkPermission(Manifest.permission.CALL_PHONE, getPackageName()) == PackageManager.PERMISSION_GRANTED) {

        } else {

        }*/
    }

    public void khcall(Context context) {
        //TODO: get number from hospital ao
        PackageManager pm = context.getPackageManager();
        callIntent.setData(Uri.parse("tel:" + APPConstants.mlsbv));
        context.startActivity(callIntent);

        /*
        if (pm.checkPermission(Manifest.permission.CALL_PHONE, getPackageName()) == PackageManager.PERMISSION_GRANTED) {

        } else {

        }*/
    }

    public void chkhcall(Context context) {
        // Call btn on commFragment
        PackageManager pm = context.getPackageManager();
        TextView commphone = (TextView) findViewById(R.id.commphone);
        String listnumber = commphone.getText().toString();

        if (commphone.getText().toString().trim().length() > 0) {

            callIntent.setData(Uri.parse("tel:" + listnumber));
            context.startActivity(callIntent);

            /*
        if (pm.checkPermission(Manifest.permission.CALL_PHONE, getPackageName()) == PackageManager.PERMISSION_GRANTED) {

        } else {

        }*/

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