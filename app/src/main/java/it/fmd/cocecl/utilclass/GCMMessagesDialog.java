package it.fmd.cocecl.utilclass;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.RelativeLayout;
import android.widget.Toast;

import it.fmd.cocecl.R;
import it.fmd.cocecl.contentviews.GCMMessageAdapter;
import it.fmd.cocecl.dataStorage.MsgArray;

public class GCMMessagesDialog {

    /*
    Shows all received messages, including SMS from MCC GATEWAY
     */

    public Activity activity;

    public GCMMessagesDialog(Activity _activity) {
        this.activity = _activity;
    }

    public void openGCMMessageDialog(Context context) {

        if (MsgArray.gcmMessages.isEmpty()) {
            Toast.makeText(context, "Keine Nachrichten", Toast.LENGTH_SHORT).show();

        } else {

            RelativeLayout gcmmessagelayout = (RelativeLayout) activity.getLayoutInflater().inflate(R.layout.gcm_message_listview, null);

            // Create the adapter to convert the array to views
            GCMMessageAdapter adapter = new GCMMessageAdapter(activity, MsgArray.gcmMessages);

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
}
