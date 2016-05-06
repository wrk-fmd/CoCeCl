package it.fmd.cocecl.utilclass;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Adapter;
import android.widget.RelativeLayout;

import java.util.ArrayList;

import it.fmd.cocecl.R;
import it.fmd.cocecl.contentviews.GCMMessageAdapter;
import it.fmd.cocecl.dataStorage.GCMMessage;

public class GCMMessagesDialog {

    /*
    Shows all received messages
     */

    public Activity activity;

    public GCMMessagesDialog(Activity _activity) {
        this.activity = _activity;
    }

    GCMMessageAdapter adapter;

    public void getArray(Intent intent) {

        try {
            // Get the Bundle Object
            Bundle bundleObject = intent.getExtras();

            // Get ArrayList Bundle
            ArrayList<GCMMessage> messageArrayList = (ArrayList<GCMMessage>) bundleObject.getSerializable("gcmmsgarray");

            // Create the adapter to convert the array to views
            adapter = new GCMMessageAdapter(activity, messageArrayList);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void openGCMMessageDialog(Intent intent) {

        getArray(intent);

        RelativeLayout gcmmessagelayout = (RelativeLayout) activity.getLayoutInflater().inflate(R.layout.gcm_message_listview, null);
        /*
        // Construct the data source // TODO:get array from GCMListener storeMessage()
        ArrayList<GCMMessage> messageArrayList = new ArrayList<GCMMessage>();

        // Create the adapter to convert the array to views
        GCMMessageAdapter adapter = new GCMMessageAdapter(activity, messageArrayList);
*/
        AlertDialog.Builder dlgBuilder = new AlertDialog.Builder(activity);
        dlgBuilder.setTitle("Nachrichten");
        dlgBuilder.setCancelable(true);

        dlgBuilder.setView(gcmmessagelayout);

        dlgBuilder.setAdapter(adapter, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                //TODO: set checked
            }
        });

        dlgBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.cancel();
                    }
                }

        );

        AlertDialog alert = dlgBuilder.create();
        alert.show();
    }
}
