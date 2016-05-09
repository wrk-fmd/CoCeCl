package it.fmd.cocecl.utilclass;

import android.app.Activity;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;

import it.fmd.cocecl.R;

public class Animations {

    public Activity activity;

    public Animations(Activity _activity) {
        this.activity = _activity;
    }

    private Boolean isFabOpen = false;
    private FloatingActionButton fab, fab1, fab2, fab3;
    private Animation fab_open, fab_close, rotate_forward, rotate_backward;

    //Animations

    Animation show_fab_1;
    Animation hide_fab_1;
    Animation show_fab_2;
    Animation hide_fab_2;
    Animation show_fab_3;
    Animation hide_fab_3;

    public void animateFAB() {

        fab = (FloatingActionButton) activity.findViewById(R.id.fab_map);
        fab1 = (FloatingActionButton) activity.findViewById(R.id.fab1_map);
        fab2 = (FloatingActionButton) activity.findViewById(R.id.fab2_map);
        fab3 = (FloatingActionButton) activity.findViewById(R.id.fab3_map);

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

    public void expandFAB() {

        fab = (FloatingActionButton) activity.findViewById(R.id.fab_map);
        fab1 = (FloatingActionButton) activity.findViewById(R.id.fab1_map);
        fab2 = (FloatingActionButton) activity.findViewById(R.id.fab2_map);
        fab3 = (FloatingActionButton) activity.findViewById(R.id.fab3_map);

        //Animations
        show_fab_1 = AnimationUtils.loadAnimation(activity, R.anim.fab1_show);
        hide_fab_1 = AnimationUtils.loadAnimation(activity, R.anim.fab1_hide);
        show_fab_2 = AnimationUtils.loadAnimation(activity, R.anim.fab2_show);
        hide_fab_2 = AnimationUtils.loadAnimation(activity, R.anim.fab2_hide);
        show_fab_3 = AnimationUtils.loadAnimation(activity, R.anim.fab3_show);
        hide_fab_3 = AnimationUtils.loadAnimation(activity, R.anim.fab3_hide);

        //Floating Action Button 1
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) fab1.getLayoutParams();
        layoutParams.rightMargin += (int) (fab1.getWidth() * 1.7);
        layoutParams.bottomMargin += (int) (fab1.getHeight() * 0.25);
        fab1.setLayoutParams(layoutParams);
        fab1.startAnimation(show_fab_1);
        fab1.setClickable(true);

        //Floating Action Button 2
        FrameLayout.LayoutParams layoutParams2 = (FrameLayout.LayoutParams) fab2.getLayoutParams();
        layoutParams2.rightMargin += (int) (fab2.getWidth() * 1.5);
        layoutParams2.bottomMargin += (int) (fab2.getHeight() * 1.5);
        fab2.setLayoutParams(layoutParams2);
        fab2.startAnimation(show_fab_2);
        fab2.setClickable(true);

        //Floating Action Button 3
        FrameLayout.LayoutParams layoutParams3 = (FrameLayout.LayoutParams) fab3.getLayoutParams();
        layoutParams3.rightMargin += (int) (fab3.getWidth() * 0.25);
        layoutParams3.bottomMargin += (int) (fab3.getHeight() * 1.7);
        fab3.setLayoutParams(layoutParams3);
        fab3.startAnimation(show_fab_3);
        fab3.setClickable(true);
    }


    public void hideFAB() {

        fab = (FloatingActionButton) activity.findViewById(R.id.fab_map);
        fab1 = (FloatingActionButton) activity.findViewById(R.id.fab1_map);
        fab2 = (FloatingActionButton) activity.findViewById(R.id.fab2_map);
        fab3 = (FloatingActionButton) activity.findViewById(R.id.fab3_map);

        //Animations
        show_fab_1 = AnimationUtils.loadAnimation(activity, R.anim.fab1_show);
        hide_fab_1 = AnimationUtils.loadAnimation(activity, R.anim.fab1_hide);
        show_fab_2 = AnimationUtils.loadAnimation(activity, R.anim.fab2_show);
        hide_fab_2 = AnimationUtils.loadAnimation(activity, R.anim.fab2_hide);
        show_fab_3 = AnimationUtils.loadAnimation(activity, R.anim.fab3_show);
        hide_fab_3 = AnimationUtils.loadAnimation(activity, R.anim.fab3_hide);

        //Floating Action Button 1
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) fab1.getLayoutParams();
        layoutParams.rightMargin -= (int) (fab1.getWidth() * 1.7);
        layoutParams.bottomMargin -= (int) (fab1.getHeight() * 0.25);
        fab1.setLayoutParams(layoutParams);
        fab1.startAnimation(hide_fab_1);
        fab1.setClickable(false);

        //Floating Action Button 2
        FrameLayout.LayoutParams layoutParams2 = (FrameLayout.LayoutParams) fab2.getLayoutParams();
        layoutParams2.rightMargin -= (int) (fab2.getWidth() * 1.5);
        layoutParams2.bottomMargin -= (int) (fab2.getHeight() * 1.5);
        fab2.setLayoutParams(layoutParams2);
        fab2.startAnimation(hide_fab_2);
        fab2.setClickable(false);

        //Floating Action Button 3
        FrameLayout.LayoutParams layoutParams3 = (FrameLayout.LayoutParams) fab3.getLayoutParams();
        layoutParams3.rightMargin -= (int) (fab3.getWidth() * 0.25);
        layoutParams3.bottomMargin -= (int) (fab3.getHeight() * 1.7);
        fab3.setLayoutParams(layoutParams3);
        fab3.startAnimation(hide_fab_3);
        fab3.setClickable(false);
    }

    public void blinkingBtn() {
        final Animation animation = new AlphaAnimation(1, 0); // Change alpha from fully visible to invisible
        animation.setDuration(500); // duration - half a second
        animation.setInterpolator(new LinearInterpolator()); // do not alter animation rate
        animation.setRepeatCount(Animation.INFINITE); // Repeat animation infinitely
        animation.setRepeatMode(Animation.REVERSE); // Reverse animation at the end so the button will fade back in

        /*
        final Button btn = (Button) findViewById(R.id.your_btn);
    btn.startAnimation(animation);
    btn.setOnClickListener(new OnClickListener() {
        @Override
        public void onClick(final View view) {
            view.clearAnimation();
        }
    });
         */
    }
}
