package it.fmd.cocecl.utilclass;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;
import android.webkit.WebView;
import android.widget.RelativeLayout;

import it.fmd.cocecl.APPConstants;
import it.fmd.cocecl.R;

public class DialogAmbInfo {

    public Activity activity;

    public DialogAmbInfo(Activity _activity) {

        this.activity = _activity;
    }

    public void openAmbInfo() {

        RelativeLayout ambinfolayout = (RelativeLayout) activity.getLayoutInflater().inflate(R.layout.amb_info_layout, null);

        WebView wv;
        wv = (WebView) ambinfolayout.findViewById(R.id.webView);
        wv.setBackgroundColor(0x00000000);
        wv.setVisibility(View.VISIBLE);
        wv.loadUrl(APPConstants.cocecl_html);

        AlertDialog.Builder dlgBuilder = new AlertDialog.Builder(activity);
        dlgBuilder.setTitle("Information");
        dlgBuilder.setCancelable(true);

        dlgBuilder.setView(ambinfolayout);

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
