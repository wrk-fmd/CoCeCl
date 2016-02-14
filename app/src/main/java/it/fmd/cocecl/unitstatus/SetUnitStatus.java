package it.fmd.cocecl.unitstatus;


import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import it.fmd.cocecl.R;

import static android.graphics.Color.RED;

public class SetUnitStatus extends Activity {

    TextView textView112;
    Button button5;
    Button button12;

    @Override
    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        //setContentView(R.layout.fragment_mainstatus2);

        textView112 = (TextView) findViewById(R.id.textView112);
        button5 = (Button) findViewById(R.id.button5);
        button12 = (Button) findViewById(R.id.button12);

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_mainstatus2, container, false);
/*
        textView112 = (TextView) v.findViewById(R.id.textView112);
        button5 = (Button) v.findViewById(R.id.button5);
        button12 = (Button) v.findViewById(R.id.button12);
*/
        return v;
    }

    //Radio
    //SelectivRuf
    public void selectivmt() {

        button5.setEnabled(true);
        button5.setClickable(false);
        //button5.setBackgroundColor(Color.YELLOW);
        button5.setBackground(getResources().getDrawable(R.drawable.button_yellow_pressed));
        //Toast.makeText(getApplicationContext(), "Selektivruf gesendet", Toast.LENGTH_SHORT).show();
        textView112.setVisibility(View.VISIBLE);
        textView112.setText("Selektivruf gesendet");

        Handler h = new Handler();
        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                button5.setEnabled(true);
                button5.setClickable(true);
                //button5.setBackgroundColor(Color.parseColor("#bdbdbd"));
                //button5.setBackgroundResource(android.R.drawable.btn_default);
                button5.setBackground(getResources().getDrawable(R.drawable.custom_button_normal));
                textView112.setText("");
                textView112.setVisibility(View.GONE);
            }
        }, 30000);
    }


    //NOTRUF
    public void emergencymt() {

        button12.setEnabled(true);
        button12.setClickable(false);
        //button12.setBackgroundColor(RED);
        button12.setBackground(getResources().getDrawable(R.drawable.button_red_pressed));
        //Toast.makeText(getApplicationContext(), "NOTRUF gesendet", Toast.LENGTH_LONG).show();
        textView112.setVisibility(View.VISIBLE);
        textView112.setText("NOTRUF gesendet");

        Handler h = new Handler();
        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                button12.setEnabled(true);
                button12.setClickable(true);
                //button12.setBackgroundColor(Color.parseColor("#bdbdbd"));
                //button5.setBackgroundResource(android.R.drawable.btn_default);
                button5.setBackground(getResources().getDrawable(R.drawable.custom_button_normal));
                textView112.setText("");
                textView112.setVisibility(View.GONE);
            }
        }, 45000);
    }
}
