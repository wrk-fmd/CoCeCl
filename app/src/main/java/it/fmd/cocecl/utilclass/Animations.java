package it.fmd.cocecl.utilclass;

import android.app.Activity;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import it.fmd.cocecl.R;

public class Animations {

    public Activity activity;

    public Animations(Activity _activity) {

        this.activity = _activity;
    }

    private Boolean isFabOpen = false;
    private FloatingActionButton fab, fab1, fab2;
    private Animation fab_open, fab_close, rotate_forward, rotate_backward;

    public void animateFAB() {

        fab = (FloatingActionButton) activity.findViewById(R.id.fab_map);
        fab1 = (FloatingActionButton) activity.findViewById(R.id.fab1_map);
        fab2 = (FloatingActionButton) activity.findViewById(R.id.fab2_map);
        fab_open = AnimationUtils.loadAnimation(activity, R.anim.fab_open);
        fab_close = AnimationUtils.loadAnimation(activity, R.anim.fab_close);
        rotate_forward = AnimationUtils.loadAnimation(activity, R.anim.rotate_forward);
        rotate_backward = AnimationUtils.loadAnimation(activity, R.anim.rotate_backward);

        if (isFabOpen) {

            fab.startAnimation(rotate_backward);
            fab1.startAnimation(fab_close);
            fab2.startAnimation(fab_close);
            fab1.setClickable(false);
            fab2.setClickable(false);
            isFabOpen = false;
            Log.d("map_fab", "close");

        } else {

            fab.startAnimation(rotate_forward);
            fab1.startAnimation(fab_open);
            fab2.startAnimation(fab_open);
            fab1.setClickable(true);
            fab2.setClickable(true);
            isFabOpen = true;
            Log.d("map_fab", "open");

        }
    }
}
