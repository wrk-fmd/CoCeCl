package it.fmd.cocecl.utilclass;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import it.fmd.cocecl.R;

import static android.graphics.Color.GREEN;
import static android.graphics.Color.RED;

/**
 * Connectivity Icons on Toolbar in MainActivity
 * MLS Connection State TextView on LoginActivity
 */

public class ToolbarIconStates extends Activity {

    public TextView wifitext;
    public TextView mobiletext;

    public void gpsenabled() {
        TextView gpstext = (TextView) findViewById(R.id.textView108);
        gpstext.setBackgroundColor(GREEN);
    }

    public void gpsdisabled() {
        TextView gpstext = (TextView) findViewById(R.id.textView108);
        gpstext.setBackgroundColor(RED);
    }

    public void setwifigreen() {
        wifitext = (TextView) findViewById(R.id.textView7);
        wifitext.setBackgroundColor(GREEN);
    }

    public void setmobilegreen() {
        mobiletext = (TextView) findViewById(R.id.textView83);
        mobiletext.setBackgroundColor(GREEN);
    }

    public void setred() {
        wifitext = (TextView) findViewById(R.id.textView7);
        mobiletext = (TextView) findViewById(R.id.textView83);
        wifitext.setBackgroundColor(RED);
        mobiletext.setBackgroundColor(RED);
    }

    // MLS SERVER
    public void mlsonline() {
        TextView errormsg = (TextView) findViewById(R.id.textView94);
        if (errormsg != null)
            errormsg.setText("MLS Online");
        errormsg.setTextColor(Color.GREEN);

    }

    public void mlsoffline() {
        TextView errormsg = (TextView) findViewById(R.id.textView94);
        if (errormsg != null)
            errormsg.setText("MLS Offline");
        errormsg.setTextColor(Color.RED);
    }

    public void mlsonlineicon() {
        ImageView mlscon = (ImageView) findViewById(R.id.imageView_mlscon);
        if (mlscon != null)
            mlscon.setImageResource(R.drawable.connected64);
    }

    public void mlsofflineicon() {
        ImageView mlscon = (ImageView) findViewById(R.id.imageView_mlscon);
        if (mlscon != null)
            mlscon.setImageResource(R.drawable.disconnected64);
    }


    /**
     * Animated SyncIcons in AppBar
     */

    //Animated sync symbol in toolbar//
    //rotate imageview animation
    public void onSyncIconStart() {
        ImageView syncicon = (ImageView) findViewById(R.id.imageView2);

        RotateAnimation r = new RotateAnimation(360, 0, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);

        r.setDuration((long) 2 * 1000);
        r.setRepeatCount(Animation.INFINITE);
        syncicon.startAnimation(r);
    }

    public void onSyncIconStop() {
        ImageView syncicon = (ImageView) findViewById(R.id.imageView2);
        final TextView serveranswer = (TextView) findViewById(R.id.textView49);

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
        final ImageView syncicon = (ImageView) findViewById(R.id.imageView2);
        final ImageView erroricon = (ImageView) findViewById(R.id.erroriconView);
        final TextView serveranswer = (TextView) findViewById(R.id.textView49);

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
