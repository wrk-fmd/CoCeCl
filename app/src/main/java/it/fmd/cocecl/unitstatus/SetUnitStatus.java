package it.fmd.cocecl.unitstatus;


import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import it.fmd.cocecl.MainActivity;
import it.fmd.cocecl.R;

import static android.graphics.Color.RED;

public class SetUnitStatus extends MainActivity {

    @Override
    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_main);

    }

    /*
            final TextView textView112 = (TextView) findViewById(R.id.textView112);
            final Button button5 = (Button) findViewById(R.id.button5);
            final Button button12 = (Button) findViewById(R.id.button12);
    */
    //Radio
    //SelectivRuf
    public void selectivmt() {

        final TextView textView112 = (TextView) findViewById(R.id.textView112);
        final Button button5 = (Button) findViewById(R.id.button5);
        final Button button12 = (Button) findViewById(R.id.button12);

        button5.setEnabled(true);
        button5.setClickable(false);
        button5.setBackgroundColor(Color.YELLOW);
        Toast.makeText(getApplicationContext(), "Selektivruf gesendet", Toast.LENGTH_SHORT).show();
        textView112.setVisibility(View.VISIBLE);
        textView112.setText("Selektivruf gesendet");

        Handler h = new Handler();
        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                button5.setEnabled(true);
                button5.setClickable(true);
                button5.setBackgroundColor(Color.parseColor("#bdbdbd"));
                //button5.setBackgroundResource(android.R.drawable.btn_default);
                textView112.setText("");
                textView112.setVisibility(View.GONE);
            }
        }, 30000);
    }


    //NOTRUF
    public void emergencymt() {

        final TextView textView112 = (TextView) findViewById(R.id.textView112);
        final Button button5 = (Button) findViewById(R.id.button5);
        final Button button12 = (Button) findViewById(R.id.button12);

        button12.setEnabled(true);
        button12.setClickable(false);
        button12.setBackgroundColor(RED);
        Toast.makeText(getApplicationContext(), "NOTRUF gesendet", Toast.LENGTH_LONG).show();
        textView112.setVisibility(View.VISIBLE);
        textView112.setText("NOTRUF gesendet");

        Handler h = new Handler();
        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                button12.setEnabled(true);
                button12.setClickable(true);
                button12.setBackgroundColor(Color.parseColor("#bdbdbd"));
                //button12.setBackgroundResource(android.R.drawable.btn_default);
                textView112.setText("");
                textView112.setVisibility(View.GONE);
            }
        }, 45000);
    }
}
