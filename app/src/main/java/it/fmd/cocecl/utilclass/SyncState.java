package it.fmd.cocecl.utilclass;

import android.app.Application;
import android.os.Handler;
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
 *  Animated SyncIcons in AppBar
 */

public class SyncState extends MainActivity {

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
        ImageView syncicon = (ImageView)findViewById(R.id.imageView2);
        final TextView serveranswer = (TextView)findViewById(R.id.textView49);

        RotateAnimation r = new RotateAnimation(0, 0, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);

        r.setDuration((long) 2*3000);
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
        final ImageView syncicon = (ImageView)findViewById(R.id.imageView2);
        final ImageView erroricon = (ImageView)findViewById(R.id.erroriconView);
        final TextView serveranswer = (TextView)findViewById(R.id.textView49);

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
