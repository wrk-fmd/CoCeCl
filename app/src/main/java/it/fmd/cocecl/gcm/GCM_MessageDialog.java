package it.fmd.cocecl.gcm;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public class GCM_MessageDialog {

    public void showMessageDialog(Context context, String messagebody) {
        AlertDialog alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle("Nachricht");
        alertDialog.setMessage(messagebody);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }
}
