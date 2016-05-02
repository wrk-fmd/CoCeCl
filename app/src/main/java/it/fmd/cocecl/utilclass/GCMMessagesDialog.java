package it.fmd.cocecl.utilclass;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import it.fmd.cocecl.R;

public class GCMMessagesDialog {

    public Activity activity;

    public GCMMessagesDialog(Activity _activity) {
        this.activity = _activity;
    }

    public void openGCMMessageDialog() {

        RelativeLayout gcmmessagelayout = (RelativeLayout) activity.getLayoutInflater().inflate(R.layout.gcm_message_listview, null);

        AlertDialog.Builder dlgBuilder = new AlertDialog.Builder(activity);
        dlgBuilder.setTitle("Nachrichten");
        dlgBuilder.setCancelable(true);

        dlgBuilder.setView(gcmmessagelayout);

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
