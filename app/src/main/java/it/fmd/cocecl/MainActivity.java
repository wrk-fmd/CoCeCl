package it.fmd.cocecl;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import static android.graphics.Color.BLUE;
import static android.graphics.Color.GREEN;
import static android.graphics.Color.RED;


public class MainActivity extends FragmentActivity {
    private FragmentTabHost mTabHost;
    private View mDecorView;

    // TabHost Controller // // status einsatzdaten abgabeort

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        {
            // add PhoneStateListener //
            PhoneCallListener phoneListener = new PhoneCallListener();
            TelephonyManager telephonyManager = (TelephonyManager) this
                    .getSystemService(Context.TELEPHONY_SERVICE);
            telephonyManager.listen(phoneListener, PhoneStateListener.LISTEN_CALL_STATE);
        }


        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), android.R.id.tabcontent);

        mTabHost.addTab(
                mTabHost.newTabSpec("tab1").setIndicator("Status", null),
                status.class, null);
        mTabHost.addTab(
                mTabHost.newTabSpec("tab2").setIndicator("Einsatzdaten", null),
                fielddata.class, null);
        mTabHost.addTab(
                mTabHost.newTabSpec("tab3").setIndicator("Abgabeort", null),
                deliveryloc.class, null);

    }
        // TABHOST END


        // (Toggle) Fullscreen START

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.activity_main, container, false);

        mDecorView = getWindow().getDecorView();

        Switch sw3 = (Switch) getWindow().findViewById(R.id.switch3);

        sw3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {

                    // The toggle is enabled
                    mDecorView.setSystemUiVisibility(
                            View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION //hides nav bar
                                    // | View.SYSTEM_UI_FLAG_FULLSCREEN // hides status bar
                                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                                    | View.INVISIBLE);
                } else {

                    // The toggle is disabled
                    mDecorView.setSystemUiVisibility(0);
                    getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
                }
            }

        });
        return rootView;
    }



        //exit button on infopage//

    public void exitbtn(View v) {
        if (v.getId() == R.id.button20) {
            Button button20 = (Button) findViewById(R.id.button20);
            button20.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish(); System.exit(0);
                }
            });
        }
    }
/**
    // Fullscreen //
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            //| View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }
*/
    // (Toggle) Fullscreen END


    // Phone Call Manager //


    //monitor phone call activities
    private class PhoneCallListener extends PhoneStateListener {

        private boolean isPhoneCalling = false;

        String LOG_TAG = "LOGGING 123";

        @Override
        public void onCallStateChanged(int state, String incomingNumber) {

            if (TelephonyManager.CALL_STATE_RINGING == state) {
                // phone ringing
                Log.i(LOG_TAG, "RINGING, number: " + incomingNumber);
            }

            if (TelephonyManager.CALL_STATE_OFFHOOK == state) {
                // active
                Log.i(LOG_TAG, "OFFHOOK");

                isPhoneCalling = true;
            }

            if (TelephonyManager.CALL_STATE_IDLE == state) {
                // run when class initial and phone call ended,
                // need detect flag from CALL_STATE_OFFHOOK
                Log.i(LOG_TAG, "IDLE");

                if (isPhoneCalling) {

                    Log.i(LOG_TAG, "restart app");

                    // restart app
                    Intent restartph = getBaseContext().getPackageManager()
                            .getLaunchIntentForPackage(
                                    getBaseContext().getPackageName());
                    restartph.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(restartph);

                    isPhoneCalling = false;
                }
            }
        }
    }

    //Call LS Button on deliveryloc//

    public void lscall(View v) {
        if (v.getId() == R.id.button17) {

            Button button17 = (Button) findViewById(R.id.button17);
            button17.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {

                    Intent callIntent = new Intent(Intent.ACTION_CALL);
                    callIntent.setData(Uri.parse("tel:+436604898783"));

                    startActivity(callIntent);
                }
            });

        }
    }


    // Button state & color functions START

    public void st1(View v) {
        if (v.getId() == R.id.button) {
            Button button = (Button) findViewById(R.id.button);
            button.setEnabled(true);
            button.setClickable(false);
            button.setBackgroundColor(GREEN);

            Button button2 = (Button) findViewById(R.id.button2);
            button2.setEnabled(true);
            button2.setClickable(true);
            button2.setBackgroundResource(android.R.drawable.btn_default);

            Button button3 = (Button) findViewById(R.id.button3);
            button3.setEnabled(true);
            button3.setClickable(true);
            button3.setBackgroundResource(android.R.drawable.btn_default);

            Button button4 = (Button) findViewById(R.id.button4);
            button4.setEnabled(false);
            button4.setClickable(false);
            button4.setBackgroundResource(android.R.drawable.btn_default);

            //Button button5 = (Button) findViewById(R.id.button5);
            //button5.setEnabled(true);
            //button5.setClickable(true);
            //button5.setBackgroundResource(android.R.drawable.btn_default);

            Button button6 = (Button) findViewById(R.id.button6);
            button6.setEnabled(false);
            button6.setClickable(false);
            button6.setBackgroundResource(android.R.drawable.btn_default);

            Button button7 = (Button) findViewById(R.id.button7);
            button7.setEnabled(false);
            button7.setClickable(false);
            button7.setBackgroundResource(android.R.drawable.btn_default);

            Button button8 = (Button) findViewById(R.id.button8);
            button8.setEnabled(false);
            button8.setClickable(false);
            button8.setBackgroundResource(android.R.drawable.btn_default);

            Button button9 = (Button) findViewById(R.id.button9);
            button9.setEnabled(false);
            button9.setClickable(false);
            button9.setBackgroundResource(android.R.drawable.btn_default);

            Button button10 = (Button) findViewById(R.id.button10);
            button10.setEnabled(false);
            button10.setClickable(false);
            button10.setBackgroundResource(android.R.drawable.btn_default);

            Button button11 = (Button) findViewById(R.id.button11);
            button11.setEnabled(false);
            button11.setClickable(false);
            button11.setBackgroundResource(android.R.drawable.btn_default);

            //Button button12 = (Button) findViewById(R.id.button12);
            //button12.setEnabled(true);
            //button12.setClickable(true);
            //button12.setBackgroundResource(android.R.drawable.btn_default);

            Button button13 = (Button) findViewById(R.id.button13);
            button13.setEnabled(false);
            button13.setClickable(false);
            button13.setBackgroundResource(android.R.drawable.btn_default);
        }
    }

    public void st2(View v) {
        if (v.getId() == R.id.button2) {
            Button button = (Button) findViewById(R.id.button);
            button.setEnabled(true);
            button.setClickable(true);
            button.setBackgroundResource(android.R.drawable.btn_default);

            Button button2 = (Button) findViewById(R.id.button2);
            button2.setEnabled(true);
            button2.setClickable(false);
            button2.setBackgroundColor(GREEN);

            Button button3 = (Button) findViewById(R.id.button3);
            button3.setEnabled(true);
            button3.setClickable(true);
            button3.setBackgroundResource(android.R.drawable.btn_default);

            Button button4 = (Button) findViewById(R.id.button4);
            button4.setEnabled(false);
            button4.setClickable(false);
            button4.setBackgroundResource(android.R.drawable.btn_default);

            //Button button5 = (Button) findViewById(R.id.button5);
            //button5.setEnabled(true);
            //button5.setClickable(true);
            //button5.setBackgroundResource(android.R.drawable.btn_default);

            Button button6 = (Button) findViewById(R.id.button6);
            button6.setEnabled(true);
            button6.setClickable(true);
            button6.setBackgroundResource(android.R.drawable.btn_default);

            Button button7 = (Button) findViewById(R.id.button7);
            button7.setEnabled(false);
            button7.setClickable(false);
            button7.setBackgroundResource(android.R.drawable.btn_default);

            Button button8 = (Button) findViewById(R.id.button8);
            button8.setEnabled(false);
            button8.setClickable(false);
            button8.setBackgroundResource(android.R.drawable.btn_default);

            Button button9 = (Button) findViewById(R.id.button9);
            button9.setEnabled(true);
            button9.setClickable(true);
            button9.setBackgroundResource(android.R.drawable.btn_default);

            Button button10 = (Button) findViewById(R.id.button10);
            button10.setEnabled(false);
            button10.setClickable(false);
            button10.setBackgroundResource(android.R.drawable.btn_default);

            Button button11 = (Button) findViewById(R.id.button11);
            button11.setEnabled(false);
            button11.setClickable(false);
            button11.setBackgroundResource(android.R.drawable.btn_default);

            //Button button12 = (Button) findViewById(R.id.button12);
            //button12.setEnabled(true);
            //button12.setClickable(true);
            //button12.setBackgroundResource(android.R.drawable.btn_default);

            Button button13 = (Button) findViewById(R.id.button13);
            button13.setEnabled(false);
            button13.setClickable(false);
            button13.setBackgroundResource(android.R.drawable.btn_default);
        }
    }

    public void st3(View v) {
        if (v.getId() == R.id.button3) {
            Button button = (Button) findViewById(R.id.button);
            button.setEnabled(true);
            button.setClickable(false);
            button.setBackgroundResource(android.R.drawable.btn_default);

            Button button2 = (Button) findViewById(R.id.button2);
            button2.setEnabled(false);
            button2.setClickable(false);
            button2.setBackgroundResource(android.R.drawable.btn_default);

            Button button3 = (Button) findViewById(R.id.button3);
            button3.setEnabled(true);
            button3.setClickable(false);
            button3.setBackgroundColor(GREEN);

            Button button4 = (Button) findViewById(R.id.button4);
            button4.setEnabled(true);
            button4.setClickable(true);
            button4.setBackgroundResource(android.R.drawable.btn_default);

            //Button button5 = (Button) findViewById(R.id.button5);
            //button5.setEnabled(true);
            //button5.setClickable(true);
            //button5.setBackgroundResource(android.R.drawable.btn_default);

            Button button6 = (Button) findViewById(R.id.button6);
            button6.setEnabled(false);
            button6.setClickable(false);
            button6.setBackgroundResource(android.R.drawable.btn_default);

            Button button7 = (Button) findViewById(R.id.button7);
            button7.setEnabled(false);
            button7.setClickable(false);
            button7.setBackgroundResource(android.R.drawable.btn_default);

            Button button8 = (Button) findViewById(R.id.button8);
            button8.setEnabled(false);
            button8.setClickable(false);
            button8.setBackgroundResource(android.R.drawable.btn_default);

            Button button9 = (Button) findViewById(R.id.button9);
            button9.setEnabled(false);
            button9.setClickable(false);
            button9.setBackgroundResource(android.R.drawable.btn_default);

            Button button10 = (Button) findViewById(R.id.button10);
            button10.setEnabled(false);
            button10.setClickable(false);
            button10.setBackgroundResource(android.R.drawable.btn_default);

            Button button11 = (Button) findViewById(R.id.button11);
            button11.setEnabled(false);
            button11.setClickable(false);
            button11.setBackgroundResource(android.R.drawable.btn_default);

            //Button button12 = (Button) findViewById(R.id.button12);
            //button12.setEnabled(true);
            //button12.setClickable(true);
            //button12.setBackgroundResource(android.R.drawable.btn_default);

            Button button13 = (Button) findViewById(R.id.button13);
            button13.setEnabled(false);
            button13.setClickable(false);
            button13.setBackgroundResource(android.R.drawable.btn_default);
        }
    }

    public void st4(View v) {
        if (v.getId() == R.id.button4) {
            Button button = (Button) findViewById(R.id.button);
            button.setEnabled(false);
            button.setClickable(false);
            button.setBackgroundResource(android.R.drawable.btn_default);

            Button button2 = (Button) findViewById(R.id.button2);
            button2.setEnabled(false);
            button2.setClickable(false);
            button2.setBackgroundResource(android.R.drawable.btn_default);

            Button button3 = (Button) findViewById(R.id.button3);
            button3.setEnabled(false);
            button3.setClickable(false);
            button3.setBackgroundResource(android.R.drawable.btn_default);

            Button button4 = (Button) findViewById(R.id.button4);
            button4.setEnabled(true);
            button4.setClickable(false);
            button4.setBackgroundColor(GREEN);

            //Button button5 = (Button) findViewById(R.id.button5);
            //button5.setEnabled(true);
            //button5.setClickable(true);
            //button5.setBackgroundResource(android.R.drawable.btn_default);

            Button button6 = (Button) findViewById(R.id.button6);
            button6.setEnabled(false);
            button6.setClickable(false);
            button6.setBackgroundResource(android.R.drawable.btn_default);

            Button button7 = (Button) findViewById(R.id.button7);
            button7.setEnabled(true);
            button7.setClickable(true);
            button7.setBackgroundResource(android.R.drawable.btn_default);

            Button button8 = (Button) findViewById(R.id.button8);
            button8.setEnabled(false);
            button8.setClickable(false);
            button8.setBackgroundResource(android.R.drawable.btn_default);

            Button button9 = (Button) findViewById(R.id.button9);
            button9.setEnabled(false);
            button9.setClickable(false);
            button9.setBackgroundResource(android.R.drawable.btn_default);

            Button button10 = (Button) findViewById(R.id.button10);
            button10.setEnabled(true);
            button10.setClickable(true);
            button10.setBackgroundResource(android.R.drawable.btn_default);

            Button button11 = (Button) findViewById(R.id.button11);
            button11.setEnabled(true);
            button11.setClickable(true);
            button11.setBackgroundResource(android.R.drawable.btn_default);

            //Button button12 = (Button) findViewById(R.id.button12);
            //button12.setEnabled(true);
            //button12.setClickable(true);
            //button12.setBackgroundResource(android.R.drawable.btn_default);

            Button button13 = (Button) findViewById(R.id.button13);
            button13.setEnabled(true);
            button13.setClickable(true);
            button13.setBackgroundResource(android.R.drawable.btn_default);
        }
    }

    public void st5(View v) {
        if (v.getId() == R.id.button5) {

            final Button button5 = (Button) findViewById(R.id.button5);
            button5.setEnabled(true);
            button5.setClickable(false);
            button5.setBackgroundColor(Color.YELLOW);
            Toast.makeText(getApplicationContext(), "SelectivRuf", Toast.LENGTH_SHORT).show();

            Handler h = new Handler();
            h.postDelayed(new Runnable() {
                @Override
                public void run() {
                    button5.setEnabled(true);
                    button5.setClickable(true);
                    button5.setBackgroundResource(android.R.drawable.btn_default);
                }
            }, 30000);
        }
    }

    public void st6(View v) {
        if (v.getId() == R.id.button6) {

            AlertDialog.Builder dlgbuilder = new AlertDialog.Builder(MainActivity.this);
            dlgbuilder.setTitle("Einheit nicht einsatzbereit melden?");
            dlgbuilder.setItems(new CharSequence[]
                            {"NEB (andere Grund)", "Tanken", "Material nachfassen", "Nein"},

                    new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int which) {

                            switch (which) {
                                case 0:
                                    Button button = (Button) findViewById(R.id.button);
                                    button.setEnabled(true);
                                    button.setClickable(true);
                                    button.setBackgroundResource(android.R.drawable.btn_default);

                                    Button button2 = (Button) findViewById(R.id.button2);
                                    button2.setEnabled(true);
                                    button2.setClickable(true);
                                    button2.setBackgroundResource(android.R.drawable.btn_default);

                                    Button button3 = (Button) findViewById(R.id.button3);
                                    button3.setEnabled(false);
                                    button3.setClickable(false);
                                    button3.setBackgroundResource(android.R.drawable.btn_default);

                                    Button button4 = (Button) findViewById(R.id.button4);
                                    button4.setEnabled(false);
                                    button4.setClickable(false);
                                    button4.setBackgroundResource(android.R.drawable.btn_default);

                                    //Button button5 = (Button) findViewById(R.id.button5);
                                    //button5.setEnabled(true);
                                    //button5.setClickable(true);
                                    //button5.setBackgroundResource(android.R.drawable.btn_default);

                                    Button button6 = (Button) findViewById(R.id.button6);
                                    button6.setEnabled(true);
                                    button6.setClickable(false);
                                    button6.setBackgroundColor(Color.parseColor("#9C27B0"));

                                    Button button7 = (Button) findViewById(R.id.button7);
                                    button7.setEnabled(false);
                                    button7.setClickable(false);
                                    button7.setBackgroundResource(android.R.drawable.btn_default);

                                    Button button8 = (Button) findViewById(R.id.button8);
                                    button8.setEnabled(false);
                                    button8.setClickable(false);
                                    button8.setBackgroundResource(android.R.drawable.btn_default);

                                    Button button9 = (Button) findViewById(R.id.button9);
                                    button9.setEnabled(true);
                                    button9.setClickable(true);
                                    button9.setBackgroundResource(android.R.drawable.btn_default);

                                    Button button10 = (Button) findViewById(R.id.button10);
                                    button10.setEnabled(false);
                                    button10.setClickable(false);
                                    button10.setBackgroundResource(android.R.drawable.btn_default);

                                    Button button11 = (Button) findViewById(R.id.button11);
                                    button11.setEnabled(false);
                                    button11.setClickable(false);
                                    button11.setBackgroundResource(android.R.drawable.btn_default);

                                    //Button button12 = (Button) findViewById(R.id.button12);
                                    //button12.setEnabled(true);
                                    //button12.setClickable(true);
                                    //button12.setBackgroundResource(android.R.drawable.btn_default);

                                    Button button13 = (Button) findViewById(R.id.button13);
                                    button13.setEnabled(false);
                                    button13.setClickable(false);
                                    button13.setBackgroundResource(android.R.drawable.btn_default);

                                    Toast.makeText(MainActivity.this, "Nicht EB", Toast.LENGTH_SHORT).show();
                                    break;
                                case 1:
                                    button = (Button) findViewById(R.id.button);
                                    button.setEnabled(true);
                                    button.setClickable(true);
                                    button.setBackgroundResource(android.R.drawable.btn_default);

                                    button2 = (Button) findViewById(R.id.button2);
                                    button2.setEnabled(true);
                                    button2.setClickable(true);
                                    button2.setBackgroundResource(android.R.drawable.btn_default);

                                    button3 = (Button) findViewById(R.id.button3);
                                    button3.setEnabled(false);
                                    button3.setClickable(false);
                                    button3.setBackgroundResource(android.R.drawable.btn_default);

                                    button4 = (Button) findViewById(R.id.button4);
                                    button4.setEnabled(false);
                                    button4.setClickable(false);
                                    button4.setBackgroundResource(android.R.drawable.btn_default);

                                    //Button button5 = (Button) findViewById(R.id.button5);
                                    //button5.setEnabled(true);
                                    //button5.setClickable(true);
                                    //button5.setBackgroundResource(android.R.drawable.btn_default);

                                    button6 = (Button) findViewById(R.id.button6);
                                    button6.setEnabled(true);
                                    button6.setClickable(false);
                                    button6.setBackgroundColor(Color.parseColor("#9C27B0"));

                                    button7 = (Button) findViewById(R.id.button7);
                                    button7.setEnabled(false);
                                    button7.setClickable(false);
                                    button7.setBackgroundResource(android.R.drawable.btn_default);

                                    button8 = (Button) findViewById(R.id.button8);
                                    button8.setEnabled(false);
                                    button8.setClickable(false);
                                    button8.setBackgroundResource(android.R.drawable.btn_default);

                                    button9 = (Button) findViewById(R.id.button9);
                                    button9.setEnabled(true);
                                    button9.setClickable(true);
                                    button9.setBackgroundResource(android.R.drawable.btn_default);

                                    button10 = (Button) findViewById(R.id.button10);
                                    button10.setEnabled(false);
                                    button10.setClickable(false);
                                    button10.setBackgroundResource(android.R.drawable.btn_default);

                                    button11 = (Button) findViewById(R.id.button11);
                                    button11.setEnabled(false);
                                    button11.setClickable(false);
                                    button11.setBackgroundResource(android.R.drawable.btn_default);

                                    //button12 = (Button) findViewById(R.id.button12);
                                    //button12.setEnabled(true);
                                    //button12.setClickable(true);
                                    //button12.setBackgroundResource(android.R.drawable.btn_default);

                                    button13 = (Button) findViewById(R.id.button13);
                                    button13.setEnabled(false);
                                    button13.setClickable(false);
                                    button13.setBackgroundResource(android.R.drawable.btn_default);

                                    Toast.makeText(MainActivity.this, "Tanken", Toast.LENGTH_SHORT).show();
                                    break;
                                case 2:
                                    button = (Button) findViewById(R.id.button);
                                    button.setEnabled(true);
                                    button.setClickable(true);
                                    button.setBackgroundResource(android.R.drawable.btn_default);

                                    button2 = (Button) findViewById(R.id.button2);
                                    button2.setEnabled(true);
                                    button2.setClickable(true);
                                    button2.setBackgroundResource(android.R.drawable.btn_default);

                                    button3 = (Button) findViewById(R.id.button3);
                                    button3.setEnabled(false);
                                    button3.setClickable(false);
                                    button3.setBackgroundResource(android.R.drawable.btn_default);

                                    button4 = (Button) findViewById(R.id.button4);
                                    button4.setEnabled(false);
                                    button4.setClickable(false);
                                    button4.setBackgroundResource(android.R.drawable.btn_default);

                                    //Button button5 = (Button) findViewById(R.id.button5);
                                    //button5.setEnabled(true);
                                    //button5.setClickable(true);
                                    //button5.setBackgroundResource(android.R.drawable.btn_default);

                                    button6 = (Button) findViewById(R.id.button6);
                                    button6.setEnabled(true);
                                    button6.setClickable(false);
                                    button6.setBackgroundColor(Color.parseColor("#9C27B0"));

                                    button7 = (Button) findViewById(R.id.button7);
                                    button7.setEnabled(false);
                                    button7.setClickable(false);
                                    button7.setBackgroundResource(android.R.drawable.btn_default);

                                    button8 = (Button) findViewById(R.id.button8);
                                    button8.setEnabled(false);
                                    button8.setClickable(false);
                                    button8.setBackgroundResource(android.R.drawable.btn_default);

                                    button9 = (Button) findViewById(R.id.button9);
                                    button9.setEnabled(true);
                                    button9.setClickable(true);
                                    button9.setBackgroundResource(android.R.drawable.btn_default);

                                    button10 = (Button) findViewById(R.id.button10);
                                    button10.setEnabled(false);
                                    button10.setClickable(false);
                                    button10.setBackgroundResource(android.R.drawable.btn_default);

                                    button11 = (Button) findViewById(R.id.button11);
                                    button11.setEnabled(false);
                                    button11.setClickable(false);
                                    button11.setBackgroundResource(android.R.drawable.btn_default);

                                    //button12 = (Button) findViewById(R.id.button12);
                                    //button12.setEnabled(true);
                                    //button12.setClickable(true);
                                    //button12.setBackgroundResource(android.R.drawable.btn_default);

                                    button13 = (Button) findViewById(R.id.button13);
                                    button13.setEnabled(false);
                                    button13.setClickable(false);
                                    button13.setBackgroundResource(android.R.drawable.btn_default);

                                    Toast.makeText(MainActivity.this, "Mat. nachfassen", Toast.LENGTH_SHORT).show();
                                    break;
                                case 3:
                                    Toast.makeText(MainActivity.this, "weiter EB", Toast.LENGTH_SHORT).show();
                                    break;
                            }
                        }
                    });

            dlgbuilder.create().show();

        }
    }

    public void st7(View v) {
        if (v.getId() == R.id.button7) {
            Button button = (Button) findViewById(R.id.button);
            button.setEnabled(false);
            button.setClickable(false);
            button.setBackgroundResource(android.R.drawable.btn_default);

            Button button2 = (Button) findViewById(R.id.button2);
            button2.setEnabled(false);
            button2.setClickable(false);
            button2.setBackgroundResource(android.R.drawable.btn_default);

            Button button3 = (Button) findViewById(R.id.button3);
            button3.setEnabled(false);
            button3.setClickable(false);
            button3.setBackgroundResource(android.R.drawable.btn_default);

            Button button4 = (Button) findViewById(R.id.button4);
            button4.setEnabled(false);
            button4.setClickable(false);
            button4.setBackgroundResource(android.R.drawable.btn_default);

            //Button button5 = (Button) findViewById(R.id.button5);
            //button5.setEnabled(true);
            //button5.setClickable(true);
            //button5.setBackgroundResource(android.R.drawable.btn_default);

            Button button6 = (Button) findViewById(R.id.button6);
            button6.setEnabled(false);
            button6.setClickable(false);
            button6.setBackgroundResource(android.R.drawable.btn_default);

            Button button7 = (Button) findViewById(R.id.button7);
            button7.setEnabled(true);
            button7.setClickable(false);
            button7.setBackgroundColor(GREEN);

            Button button8 = (Button) findViewById(R.id.button8);
            button8.setEnabled(true);
            button8.setClickable(true);
            button8.setBackgroundResource(android.R.drawable.btn_default);

            Button button9 = (Button) findViewById(R.id.button9);
            button9.setEnabled(false);
            button9.setClickable(false);
            button9.setBackgroundResource(android.R.drawable.btn_default);

            Button button10 = (Button) findViewById(R.id.button10);
            button10.setEnabled(false);
            button10.setClickable(false);
            button10.setBackgroundResource(android.R.drawable.btn_default);

            Button button11 = (Button) findViewById(R.id.button11);
            button11.setEnabled(false);
            button11.setClickable(false);
            button11.setBackgroundResource(android.R.drawable.btn_default);

            //Button button12 = (Button) findViewById(R.id.button12);
            //button12.setEnabled(true);
            //button12.setClickable(true);
            //button12.setBackgroundResource(android.R.drawable.btn_default);

            Button button13 = (Button) findViewById(R.id.button13);
            button13.setEnabled(false);
            button13.setClickable(false);
            button13.setBackgroundResource(android.R.drawable.btn_default);
        }
    }

    public void st8(View v) {
        if (v.getId() == R.id.button8) {
            Button button = (Button) findViewById(R.id.button);
            button.setEnabled(true);
            button.setClickable(true);
            button.setBackgroundResource(android.R.drawable.btn_default);

            Button button2 = (Button) findViewById(R.id.button2);
            button2.setEnabled(false);
            button2.setClickable(false);
            button2.setBackgroundResource(android.R.drawable.btn_default);

            Button button3 = (Button) findViewById(R.id.button3);
            button3.setEnabled(false);
            button3.setClickable(true);
            button3.setBackgroundResource(android.R.drawable.btn_default);

            Button button4 = (Button) findViewById(R.id.button4);
            button4.setEnabled(false);
            button4.setClickable(false);
            button4.setBackgroundResource(android.R.drawable.btn_default);

            //Button button5 = (Button) findViewById(R.id.button5);
            //button5.setEnabled(true);
            //button5.setClickable(true);
            //button5.setBackgroundResource(android.R.drawable.btn_default);

            Button button6 = (Button) findViewById(R.id.button6);
            button6.setEnabled(false);
            button6.setClickable(false);
            button6.setBackgroundResource(android.R.drawable.btn_default);

            Button button7 = (Button) findViewById(R.id.button7);
            button7.setEnabled(false);
            button7.setClickable(false);
            button7.setBackgroundResource(android.R.drawable.btn_default);

            Button button8 = (Button) findViewById(R.id.button8);
            button8.setEnabled(true);
            button8.setClickable(false);
            button8.setBackgroundColor(GREEN);

            Button button9 = (Button) findViewById(R.id.button9);
            button9.setEnabled(false);
            button9.setClickable(false);
            button9.setBackgroundResource(android.R.drawable.btn_default);

            Button button10 = (Button) findViewById(R.id.button10);
            button10.setEnabled(false);
            button10.setClickable(false);
            button10.setBackgroundResource(android.R.drawable.btn_default);

            Button button11 = (Button) findViewById(R.id.button11);
            button11.setEnabled(false);
            button11.setClickable(false);
            button11.setBackgroundResource(android.R.drawable.btn_default);

            //Button button12 = (Button) findViewById(R.id.button12);
            //button12.setEnabled(true);
            //button12.setClickable(true);
            //button12.setBackgroundResource(android.R.drawable.btn_default);

            Button button13 = (Button) findViewById(R.id.button13);
            button13.setEnabled(false);
            button13.setClickable(false);
            button13.setBackgroundResource(android.R.drawable.btn_default);
        }
    }

    public void st9(View v) {
        if (v.getId() == R.id.button9) {

            AlertDialog.Builder dlgBuilder = new AlertDialog.Builder(MainActivity.this);
            dlgBuilder.setMessage("Einheit ausser Dienst stellen?");
            dlgBuilder.setCancelable(false);
            dlgBuilder.setPositiveButton("Ja", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {

                    Button button = (Button) findViewById(R.id.button);
                    button.setEnabled(false);
                    button.setClickable(false);
                    button.setBackgroundResource(android.R.drawable.btn_default);

                    Button button2 = (Button) findViewById(R.id.button2);
                    button2.setEnabled(true);
                    button2.setClickable(true);
                    button2.setBackgroundResource(android.R.drawable.btn_default);

                    Button button3 = (Button) findViewById(R.id.button3);
                    button3.setEnabled(false);
                    button3.setClickable(false);
                    button3.setBackgroundResource(android.R.drawable.btn_default);

                    Button button4 = (Button) findViewById(R.id.button4);
                    button4.setEnabled(false);
                    button4.setClickable(false);
                    button4.setBackgroundResource(android.R.drawable.btn_default);

                    //Button button5 = (Button) findViewById(R.id.button5);
                    //button5.setEnabled(true);
                    //button5.setClickable(true);
                    //button5.setBackgroundResource(android.R.drawable.btn_default);

                    Button button6 = (Button) findViewById(R.id.button6);
                    button6.setEnabled(true);
                    button6.setClickable(true);
                    button6.setBackgroundResource(android.R.drawable.btn_default);

                    Button button7 = (Button) findViewById(R.id.button7);
                    button7.setEnabled(false);
                    button7.setClickable(false);
                    button7.setBackgroundResource(android.R.drawable.btn_default);

                    Button button8 = (Button) findViewById(R.id.button8);
                    button8.setEnabled(false);
                    button8.setClickable(false);
                    button8.setBackgroundResource(android.R.drawable.btn_default);

                    Button button9 = (Button) findViewById(R.id.button9);
                    button9.setEnabled(true);
                    button9.setClickable(false);
                    button9.setBackgroundColor(Color.parseColor("#9C27B0"));

                    Button button10 = (Button) findViewById(R.id.button10);
                    button10.setEnabled(false);
                    button10.setClickable(false);
                    button10.setBackgroundResource(android.R.drawable.btn_default);

                    Button button11 = (Button) findViewById(R.id.button11);
                    button11.setEnabled(false);
                    button11.setClickable(false);
                    button11.setBackgroundResource(android.R.drawable.btn_default);

                    //Button button12 = (Button) findViewById(R.id.button12);
                    //button12.setEnabled(true);
                    //button12.setClickable(true);
                    //button12.setBackgroundResource(android.R.drawable.btn_default);

                    Button button13 = (Button) findViewById(R.id.button13);
                    button13.setEnabled(false);
                    button13.setClickable(false);
                    button13.setBackgroundResource(android.R.drawable.btn_default);

                    Toast.makeText(MainActivity.this, "Ausser Dienst", Toast.LENGTH_SHORT).show();
                }
            });

            dlgBuilder.setNegativeButton("Nein", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(MainActivity.this, "Nein", Toast.LENGTH_SHORT).show();
                }
            });

            AlertDialog alert = dlgBuilder.create();
            alert.show();
        }
    }

    public void st10(View v) {
        if (v.getId() == R.id.button10) {

            AlertDialog.Builder dlgBuilder = new AlertDialog.Builder(MainActivity.this);
            dlgBuilder.setMessage("Intervention unterblieben?");
            dlgBuilder.setCancelable(false);
            dlgBuilder.setPositiveButton("Ja", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {

                    Button button = (Button) findViewById(R.id.button);
                    button.setEnabled(true);
                    button.setClickable(true);
                    button.setBackgroundResource(android.R.drawable.btn_default);

                    Button button10 = (Button) findViewById(R.id.button10);
                    button10.setEnabled(true);
                    button10.setClickable(false);
                    button10.setBackgroundColor(GREEN);

                    Button button11 = (Button) findViewById(R.id.button11);
                    button11.setEnabled(false);
                    button11.setClickable(false);
                    button11.setBackgroundResource(android.R.drawable.btn_default);

                    Button button13 = (Button) findViewById(R.id.button13);
                    button13.setEnabled(false);
                    button13.setClickable(false);
                    button13.setBackgroundResource(android.R.drawable.btn_default);

                    Button button7 = (Button) findViewById(R.id.button7);
                    button7.setEnabled(false);
                    button7.setClickable(false);
                    button7.setBackgroundResource(android.R.drawable.btn_default);

                    Toast.makeText(MainActivity.this, "keine Intervention", Toast.LENGTH_SHORT).show();
                }
            });

            dlgBuilder.setNegativeButton("Nein", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(MainActivity.this, "Nein", Toast.LENGTH_SHORT).show();
                }
            });

            AlertDialog alert = dlgBuilder.create();
            alert.show();
        }
    }

    public void st11(View v) {
        if (v.getId() == R.id.button11) {

            AlertDialog.Builder dlgbuilder = new AlertDialog.Builder(MainActivity.this);
            dlgbuilder.setTitle("Patient belassen/verweigert?");
            dlgbuilder.setItems(new CharSequence[]
                            {"Patient belassen", "Patient verweigert", "Nein"},

                    new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int which) {

                            switch (which) {

                                case 0:
                                    Button button = (Button) findViewById(R.id.button);
                                    button.setEnabled(true);
                                    button.setClickable(true);
                                    button.setBackgroundResource(android.R.drawable.btn_default);

                                    Button button11 = (Button) findViewById(R.id.button11);
                                    button11.setEnabled(true);
                                    button11.setClickable(false);
                                    button11.setBackgroundColor(GREEN);

                                    Button button10 = (Button) findViewById(R.id.button10);
                                    button10.setEnabled(false);
                                    button10.setClickable(false);
                                    button10.setBackgroundResource(android.R.drawable.btn_default);

                                    Button button13 = (Button) findViewById(R.id.button13);
                                    button13.setEnabled(false);
                                    button13.setClickable(false);
                                    button13.setBackgroundResource(android.R.drawable.btn_default);

                                    Button button7 = (Button) findViewById(R.id.button7);
                                    button7.setEnabled(false);
                                    button7.setClickable(false);
                                    button7.setBackgroundResource(android.R.drawable.btn_default);
                                    Toast.makeText(MainActivity.this, "Belassung", Toast.LENGTH_SHORT).show();
                                    break;
                                case 1:
                                    button = (Button) findViewById(R.id.button);
                                    button.setEnabled(true);
                                    button.setClickable(true);
                                    button.setBackgroundResource(android.R.drawable.btn_default);

                                    button11 = (Button) findViewById(R.id.button11);
                                    button11.setEnabled(true);
                                    button11.setClickable(false);
                                    button11.setBackgroundColor(GREEN);

                                    button10 = (Button) findViewById(R.id.button10);
                                    button10.setEnabled(false);
                                    button10.setClickable(false);
                                    button10.setBackgroundResource(android.R.drawable.btn_default);

                                    button13 = (Button) findViewById(R.id.button13);
                                    button13.setEnabled(false);
                                    button13.setClickable(false);
                                    button13.setBackgroundResource(android.R.drawable.btn_default);

                                    button7 = (Button) findViewById(R.id.button7);
                                    button7.setEnabled(false);
                                    button7.setClickable(false);
                                    button7.setBackgroundResource(android.R.drawable.btn_default);
                                    Toast.makeText(MainActivity.this, "Patient verweigert", Toast.LENGTH_SHORT).show();
                                    break;
                                case 2:
                                    Toast.makeText(MainActivity.this, "Nein", Toast.LENGTH_SHORT).show();
                                    break;
                            }
                        }
                    });

            dlgbuilder.create().show();

        }
    }

    public void st12(View v) {
        if (v.getId() == R.id.button12) {

            AlertDialog.Builder dlgBuilder = new AlertDialog.Builder(MainActivity.this);
            dlgBuilder.setMessage("NOTRUF senden?");
            dlgBuilder.setCancelable(false);
            dlgBuilder.setPositiveButton("Ja", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {

                    final Button button12 = (Button) findViewById(R.id.button12);
                    button12.setEnabled(true);
                    button12.setClickable(false);
                    button12.setBackgroundColor(RED);
                    Toast.makeText(getApplicationContext(), "NOTRUF gesendet", Toast.LENGTH_LONG).show();

                    Handler h = new Handler();
                    h.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            button12.setEnabled(true);
                            button12.setClickable(true);
                            button12.setBackgroundResource(android.R.drawable.btn_default);
                        }
                    }, 45000);
                }
            });

            dlgBuilder.setNegativeButton("Nein", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(MainActivity.this, "Nein", Toast.LENGTH_SHORT).show();
                }
            });

            AlertDialog alert = dlgBuilder.create();
            alert.show();


        }
    }

    /**
     * TODO: Alertbuilder with options Trupp/KTW/RTW/NAW/NEF/RTH
     */
    public void st13(View v) {
        if (v.getId() == R.id.button13) {

            AlertDialog.Builder dlgBuilder = new AlertDialog.Builder(MainActivity.this);
            dlgBuilder.setMessage("Patient an anderes Rettungsmittel übergeben?");
            dlgBuilder.setCancelable(false);
            dlgBuilder.setPositiveButton("Ja", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {

                    Button button = (Button) findViewById(R.id.button);
                    button.setEnabled(true);
                    button.setClickable(true);
                    button.setBackgroundResource(android.R.drawable.btn_default);

                    Button button13 = (Button) findViewById(R.id.button13);
                    button13.setEnabled(true);
                    button13.setClickable(false);
                    button13.setBackgroundColor(GREEN);

                    Button button10 = (Button) findViewById(R.id.button10);
                    button10.setEnabled(false);
                    button10.setClickable(false);
                    button10.setBackgroundResource(android.R.drawable.btn_default);

                    Button button11 = (Button) findViewById(R.id.button11);
                    button11.setEnabled(false);
                    button11.setClickable(false);
                    button11.setBackgroundResource(android.R.drawable.btn_default);

                    Button button7 = (Button) findViewById(R.id.button7);
                    button7.setEnabled(false);
                    button7.setClickable(false);
                    button7.setBackgroundResource(android.R.drawable.btn_default);

                    Toast.makeText(MainActivity.this, "JA", Toast.LENGTH_SHORT).show();
                }
            });

            dlgBuilder.setNegativeButton("Nein", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(MainActivity.this, "NEIN", Toast.LENGTH_SHORT).show();
                }
            });

            AlertDialog alert = dlgBuilder.create();
            alert.show();
        }
    }

    // Patienten Management dialog builder //

    public void patmanstart(View v) {
        if (v.getId() == R.id.patmanbtn) {

            AlertDialog.Builder dlgBuilder = new AlertDialog.Builder(MainActivity.this);
            dlgBuilder.setMessage("Patient anlegen");

            LayoutInflater inflater = (MainActivity.this.getLayoutInflater());

            dlgBuilder.setView(inflater.inflate(R.layout.patman, null))

                    .setPositiveButton("zurück", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });

            AlertDialog alert = dlgBuilder.create();
            alert.show();

        }
    }

    // Bett abbuchen btn //

    public void bettbuchen(View v) {
        setContentView(R.layout.patman);
        if (v.getId() == R.id.bettbtn) {

            AlertDialog.Builder dlgbuilder = new AlertDialog.Builder(MainActivity.this);
            dlgbuilder.setTitle("Abteilung auswählen");
            dlgbuilder.setItems(new CharSequence[]
                            {"Intern", "Unfall", "Chirurgie", "HNO", "Dermatologie", "Spezialbett", "andere Abteilung"},

                    new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int which) {

                            switch (which) {

                                case 0:
                                    TextView abtedit = (TextView) findViewById(R.id.textView11);
                                    abtedit.setText(R.string.intern, TextView.BufferType.EDITABLE);
                                    Toast.makeText(MainActivity.this, "Intern", Toast.LENGTH_SHORT).show();
                                    break;

                                case 1:
                                    abtedit = (TextView) findViewById(R.id.textView11);
                                    abtedit.setText(R.string.unfall, TextView.BufferType.EDITABLE);
                                    Toast.makeText(MainActivity.this, "Unfall", Toast.LENGTH_SHORT).show();
                                    break;

                                case 2:
                                    abtedit = (TextView) findViewById(R.id.textView11);
                                    abtedit.setText(R.string.chir, TextView.BufferType.EDITABLE);
                                    Toast.makeText(MainActivity.this, "Chirurgie", Toast.LENGTH_SHORT).show();
                                    break;

                                case 3:
                                    abtedit = (TextView) findViewById(R.id.textView11);
                                    abtedit.setText(R.string.hno, TextView.BufferType.EDITABLE);
                                    Toast.makeText(MainActivity.this, "HNO", Toast.LENGTH_SHORT).show();
                                    break;

                                case 4:
                                    abtedit = (TextView) findViewById(R.id.textView11);
                                    abtedit.setText(R.string.derma, TextView.BufferType.EDITABLE);
                                    Toast.makeText(MainActivity.this, "Dermatologie", Toast.LENGTH_SHORT).show();
                                    break;

                                case 5:
                                    abtedit = (TextView) findViewById(R.id.textView11);
                                    abtedit.setText(R.string.spezbett, TextView.BufferType.EDITABLE);
                                    Toast.makeText(MainActivity.this, "Leitstelle BV anrufen!", Toast.LENGTH_LONG).show();
                                    break;

                                case 6:
                                    abtedit = (TextView) findViewById(R.id.textView11);
                                    abtedit.setText(R.string.andbett, TextView.BufferType.EDITABLE);
                                    Toast.makeText(MainActivity.this, "Leitstelle BV anrufen!", Toast.LENGTH_LONG).show();
                                    break;
                            }
                        }
                    }

            );

            dlgbuilder.create().

                    show();
        }
    }

    public void checkBox(View v) {
        if (v.getId() == R.id.checkBox) {
            CheckBox checkBox = (CheckBox) findViewById(R.id.checkBox);
            checkBox.setEnabled(true);
            checkBox.setClickable(false);
            checkBox.setHintTextColor(BLUE);
        }
    }
}

 // Button state & color functions END
