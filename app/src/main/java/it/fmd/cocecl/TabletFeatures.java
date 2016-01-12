package it.fmd.cocecl;

import android.app.Application;
import android.content.res.Configuration;
import android.widget.Button;

public class TabletFeatures extends Application {

    MainActivity MA = new MainActivity();

    // Toolbar
    //TODO aditional toolbar textfields

    // disable phone if no gsm available
    //TODO set phonecall buttons gone

    // enable PATMAN Documentation System

    public void patman_enable() {

        Button button21 = (Button) MA.findViewById(R.id.button21);

        if ((getResources().getConfiguration().screenLayout &
                Configuration.SCREENLAYOUT_SIZE_MASK) >=
                Configuration.SCREENLAYOUT_SIZE_LARGE) {

            button21.setEnabled(true);
            button21.setClickable(true);

        } else {

            button21.setEnabled(false);
            button21.setClickable(false);

        }
    }
}
