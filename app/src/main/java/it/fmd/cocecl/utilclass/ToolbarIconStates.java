package it.fmd.cocecl.utilclass;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import it.fmd.cocecl.MainActivity;
import it.fmd.cocecl.R;

import static android.graphics.Color.GREEN;
import static android.graphics.Color.RED;

/**
 * Connectivity Icons on Toolbar in MainActivity
 * MLS Connection State TextView on LoginActivity //TODO: NPE
 */

public class ToolbarIconStates {

    public Activity activity;

    public ToolbarIconStates(Activity _activity) {

        this.activity = _activity;
    }

    public void gpsenabled() {
        TextView gpstext = (TextView) this.activity.findViewById(R.id.textView108);
        gpstext.setBackgroundColor(GREEN);
    }

    public void gpsdisabled() {
        TextView gpstext = (TextView) this.activity.findViewById(R.id.textView108);
        gpstext.setBackgroundColor(RED);
    }

    public void setwifigreen() {
        TextView wifitext = (TextView) this.activity.findViewById(R.id.textView7);
        wifitext.setBackgroundColor(GREEN);
    }

    public void setmobilegreen() {
        TextView mobiletext = (TextView) this.activity.findViewById(R.id.textView83);
        mobiletext.setBackgroundColor(GREEN);
    }

    public void setred() {
        TextView wifitext = (TextView) this.activity.findViewById(R.id.textView7);
        TextView mobiletext = (TextView) this.activity.findViewById(R.id.textView83);
        wifitext.setBackgroundColor(RED);
        mobiletext.setBackgroundColor(RED);
    }

    // MLS SERVER
    public void mlsonline() {
        TextView errormsg = (TextView) this.activity.findViewById(R.id.textView94);
        if (errormsg != null)
            errormsg.setText("MLS Online");
        errormsg.setTextColor(Color.GREEN);

    }

    public void mlsoffline() {
        TextView errormsg = (TextView) this.activity.findViewById(R.id.textView94);
        if (errormsg != null)
            errormsg.setText("MLS Offline");
        errormsg.setTextColor(Color.RED);
    }

    public void mlsonlineicon() {
        ImageView mlscon = (ImageView) this.activity.findViewById(R.id.imageView_mlscon);
        if (mlscon != null)
            mlscon.setImageResource(R.drawable.connected64);
    }

    public void mlsofflineicon() {
        ImageView mlscon = (ImageView) this.activity.findViewById(R.id.imageView_mlscon);
        if (mlscon != null)
            mlscon.setImageResource(R.drawable.disconnected64);
    }


    /**
     * Animated SyncIcons in AppBar
     */

    //Animated sync symbol in toolbar//
    //rotate imageview animation
    public void onSyncIconStart() {
        ImageView syncicon = (ImageView) this.activity.findViewById(R.id.imageView2);

        RotateAnimation r = new RotateAnimation(360, 0, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);

        r.setDuration((long) 2 * 1000);
        r.setRepeatCount(Animation.INFINITE);
        syncicon.startAnimation(r);
    }

    public void onSyncIconStop() {
        ImageView syncicon = (ImageView) this.activity.findViewById(R.id.imageView2);
        final TextView serveranswer = (TextView) this.activity.findViewById(R.id.textView49);

        RotateAnimation r = new RotateAnimation(0, 0, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);

        r.setDuration((long) 2 * 3000);
        r.setRepeatCount(Animation.INFINITE);
        syncicon.startAnimation(r);

        serveranswer.setText("Empfangen");
        serveranswer.setTextColor(GREEN);

        Handler h = new Handler();
        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                serveranswer.setText("");

            }
        }, 5000);
    }

    public void onSyncError() {
        final ImageView syncicon = (ImageView) this.activity.findViewById(R.id.imageView2);
        final ImageView erroricon = (ImageView) this.activity.findViewById(R.id.erroriconView);
        final TextView serveranswer = (TextView) this.activity.findViewById(R.id.textView49);

        RotateAnimation r = new RotateAnimation(0, 0, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);

        r.setDuration((long) 2 * 3000);
        r.setRepeatCount(Animation.INFINITE);
        syncicon.startAnimation(r);

        //syncicon.setBackgroundColor(RED);
        serveranswer.setText("Error");
        serveranswer.setTextColor(RED);
        erroricon.setImageResource(R.drawable.ic_warning_black_18dp);
        erroricon.setBackgroundColor(RED);

        Handler h = new Handler();
        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                serveranswer.setText("");
                //syncicon.setBackgroundColor(View.GONE);
                erroricon.setBackgroundColor(View.GONE);
                erroricon.setImageResource(android.R.color.transparent);

            }
        }, 5000);
    }
}
