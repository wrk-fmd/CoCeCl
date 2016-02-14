package it.fmd.cocecl.incidentaction;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import it.fmd.cocecl.MainActivity;
import it.fmd.cocecl.R;

import static android.graphics.Color.GREEN;
import static android.graphics.Color.RED;
import static android.graphics.Color.YELLOW;

public class StatusReport extends MainActivity {

    /**
     * NOT USED anymore
     **/

    // Button state & color functions START //
    // Status Tastenfeld //

    final Button button = (Button) findViewById(R.id.button);
    final Button button2 = (Button) findViewById(R.id.button2);
    final Button button3 = (Button) findViewById(R.id.button3);
    final Button button4 = (Button) findViewById(R.id.button4);
    final Button button5 = (Button) findViewById(R.id.button5);
    final Button button6 = (Button) findViewById(R.id.button6);
    final Button button7 = (Button) findViewById(R.id.button7);
    final Button button8 = (Button) findViewById(R.id.button8);
    final Button button9 = (Button) findViewById(R.id.button9);
    final Button button10 = (Button) findViewById(R.id.button10);
    final Button button11 = (Button) findViewById(R.id.button11);
    final Button button12 = (Button) findViewById(R.id.button12);
    final Button button13 = (Button) findViewById(R.id.button13);
    final Button button42 = (Button) findViewById(R.id.button42);


    public void st1(View v) {
        if (v.getId() == R.id.button) {
            button.setEnabled(true);
            button.setClickable(false);
            button.setBackgroundColor(GREEN);

            button2.setEnabled(true);
            button2.setClickable(true);
            button2.setBackgroundResource(android.R.drawable.btn_default);

            button3.setEnabled(true);
            button3.setClickable(true);
            button3.setBackgroundResource(android.R.drawable.btn_default);

            button4.setEnabled(false);
            button4.setClickable(false);
            button4.setBackgroundResource(android.R.drawable.btn_default);

            //button5.setEnabled(true);
            //button5.setClickable(true);
            //button5.setBackgroundResource(android.R.drawable.btn_default);

            button6.setEnabled(false);
            button6.setClickable(false);
            button6.setBackgroundResource(android.R.drawable.btn_default);

            button7.setEnabled(false);
            button7.setClickable(false);
            button7.setBackgroundResource(android.R.drawable.btn_default);

            button8.setEnabled(false);
            button8.setClickable(false);
            button8.setBackgroundResource(android.R.drawable.btn_default);

            button9.setEnabled(false);
            button9.setClickable(false);
            button9.setBackgroundResource(android.R.drawable.btn_default);

            button10.setEnabled(false);
            button10.setClickable(false);
            button10.setBackgroundResource(android.R.drawable.btn_default);

            button11.setEnabled(false);
            button11.setClickable(false);
            button11.setBackgroundResource(android.R.drawable.btn_default);

            //button12.setEnabled(true);
            //button12.setClickable(true);
            //button12.setBackgroundResource(android.R.drawable.btn_default);

            button13.setEnabled(false);
            button13.setClickable(false);
            button13.setBackgroundResource(android.R.drawable.btn_default);
        }
    }

    public void st2(View v) {
        if (v.getId() == R.id.button2) {
            button.setEnabled(true);
            button.setClickable(true);
            button.setBackgroundResource(android.R.drawable.btn_default);

            button2.setEnabled(true);
            button2.setClickable(false);
            button2.setBackgroundColor(GREEN);

            button3.setEnabled(true);
            button3.setClickable(true);
            button3.setBackgroundResource(android.R.drawable.btn_default);

            button4.setEnabled(false);
            button4.setClickable(false);
            button4.setBackgroundResource(android.R.drawable.btn_default);

            //button5.setEnabled(true);
            //button5.setClickable(true);
            //button5.setBackgroundResource(android.R.drawable.btn_default);

            button6.setEnabled(true);
            button6.setClickable(true);
            button6.setBackgroundResource(android.R.drawable.btn_default);

            button7.setEnabled(false);
            button7.setClickable(false);
            button7.setBackgroundResource(android.R.drawable.btn_default);

            button8.setEnabled(false);
            button8.setClickable(false);
            button8.setBackgroundResource(android.R.drawable.btn_default);

            button9.setEnabled(true);
            button9.setClickable(true);
            button9.setBackgroundResource(android.R.drawable.btn_default);

            button10.setEnabled(false);
            button10.setClickable(false);
            button10.setBackgroundResource(android.R.drawable.btn_default);

            button11.setEnabled(false);
            button11.setClickable(false);
            button11.setBackgroundResource(android.R.drawable.btn_default);

            //button12.setEnabled(true);
            //button12.setClickable(true);
            //button12.setBackgroundResource(android.R.drawable.btn_default);

            button13.setEnabled(false);
            button13.setClickable(false);
            button13.setBackgroundResource(android.R.drawable.btn_default);
        }
    }

    public void st3(View v) {
        if (v.getId() == R.id.button3) {
            button.setEnabled(true);
            button.setClickable(false);
            button.setBackgroundResource(android.R.drawable.btn_default);

            button2.setEnabled(false);
            button2.setClickable(false);
            button2.setBackgroundResource(android.R.drawable.btn_default);

            button3.setEnabled(true);
            button3.setClickable(false);
            button3.setBackgroundColor(GREEN);

            button4.setEnabled(true);
            button4.setClickable(true);
            button4.setBackgroundResource(android.R.drawable.btn_default);

            //button5.setEnabled(true);
            //button5.setClickable(true);
            //button5.setBackgroundResource(android.R.drawable.btn_default);

            button6.setEnabled(false);
            button6.setClickable(false);
            button6.setBackgroundResource(android.R.drawable.btn_default);

            button7.setEnabled(false);
            button7.setClickable(false);
            button7.setBackgroundResource(android.R.drawable.btn_default);

            button8.setEnabled(false);
            button8.setClickable(false);
            button8.setBackgroundResource(android.R.drawable.btn_default);

            button9.setEnabled(false);
            button9.setClickable(false);
            button9.setBackgroundResource(android.R.drawable.btn_default);

            button10.setEnabled(false);
            button10.setClickable(false);
            button10.setBackgroundResource(android.R.drawable.btn_default);

            button11.setEnabled(false);
            button11.setClickable(false);
            button11.setBackgroundResource(android.R.drawable.btn_default);

            //button12.setEnabled(true);
            //button12.setClickable(true);
            //button12.setBackgroundResource(android.R.drawable.btn_default);

            button13.setEnabled(false);
            button13.setClickable(false);
            button13.setBackgroundResource(android.R.drawable.btn_default);
        }
    }

    public void st4(View v) {
        if (v.getId() == R.id.button4) {
            button.setEnabled(false);
            button.setClickable(false);
            button.setBackgroundResource(android.R.drawable.btn_default);

            button2.setEnabled(false);
            button2.setClickable(false);
            button2.setBackgroundResource(android.R.drawable.btn_default);

            button3.setEnabled(false);
            button3.setClickable(false);
            button3.setBackgroundResource(android.R.drawable.btn_default);

            button4.setEnabled(true);
            button4.setClickable(false);
            button4.setBackgroundColor(GREEN);

            //button5.setEnabled(true);
            //button5.setClickable(true);
            //button5.setBackgroundResource(android.R.drawable.btn_default);

            button6.setEnabled(false);
            button6.setClickable(false);
            button6.setBackgroundResource(android.R.drawable.btn_default);

            button7.setEnabled(true);
            button7.setClickable(true);
            button7.setBackgroundResource(android.R.drawable.btn_default);

            button8.setEnabled(false);
            button8.setClickable(false);
            button8.setBackgroundResource(android.R.drawable.btn_default);

            button9.setEnabled(false);
            button9.setClickable(false);
            button9.setBackgroundResource(android.R.drawable.btn_default);

            button10.setEnabled(true);
            button10.setClickable(true);
            button10.setBackgroundResource(android.R.drawable.btn_default);

            button11.setEnabled(true);
            button11.setClickable(true);
            button11.setBackgroundResource(android.R.drawable.btn_default);

            //button12.setEnabled(true);
            //button12.setClickable(true);
            //button12.setBackgroundResource(android.R.drawable.btn_default);

            button13.setEnabled(true);
            button13.setClickable(true);
            button13.setBackgroundResource(android.R.drawable.btn_default);
        }
    }

    public void st5(View v) {
        if (v.getId() == R.id.button5) {

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

            AlertDialog.Builder dlgbuilder = new AlertDialog.Builder(getApplicationContext());
            dlgbuilder.setTitle("Einheit nicht einsatzbereit melden?");
            dlgbuilder.setItems(new CharSequence[]
                            {"NEB (andere Grund)", "Tanken", "Material nachfassen", "Bereitschaft", "Nein"},

                    new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int which) {

                            switch (which) {
                                case 0:
                                    button.setEnabled(true);
                                    button.setClickable(true);
                                    button.setBackgroundResource(android.R.drawable.btn_default);

                                    button2.setEnabled(true);
                                    button2.setClickable(true);
                                    button2.setBackgroundResource(android.R.drawable.btn_default);

                                    button3.setEnabled(false);
                                    button3.setClickable(false);
                                    button3.setBackgroundResource(android.R.drawable.btn_default);

                                    button4.setEnabled(false);
                                    button4.setClickable(false);
                                    button4.setBackgroundResource(android.R.drawable.btn_default);

                                    //Button button5 = (Button) findViewById(R.id.button5);
                                    //button5.setEnabled(true);
                                    //button5.setClickable(true);
                                    //button5.setBackgroundResource(android.R.drawable.btn_default);

                                    button6.setEnabled(true);
                                    button6.setClickable(false);
                                    button6.setBackgroundColor(Color.parseColor("#9C27B0"));

                                    button7.setEnabled(false);
                                    button7.setClickable(false);
                                    button7.setBackgroundResource(android.R.drawable.btn_default);

                                    button8.setEnabled(false);
                                    button8.setClickable(false);
                                    button8.setBackgroundResource(android.R.drawable.btn_default);

                                    button9.setEnabled(true);
                                    button9.setClickable(true);
                                    button9.setBackgroundResource(android.R.drawable.btn_default);

                                    button10.setEnabled(false);
                                    button10.setClickable(false);
                                    button10.setBackgroundResource(android.R.drawable.btn_default);

                                    button11.setEnabled(false);
                                    button11.setClickable(false);
                                    button11.setBackgroundResource(android.R.drawable.btn_default);

                                    //button12.setEnabled(true);
                                    //button12.setClickable(true);
                                    //button12.setBackgroundResource(android.R.drawable.btn_default);

                                    button13.setEnabled(false);
                                    button13.setClickable(false);
                                    button13.setBackgroundResource(android.R.drawable.btn_default);

                                    Toast.makeText(getApplicationContext(), "Nicht EB", Toast.LENGTH_SHORT).show();
                                    break;
                                case 1:
                                    button.setEnabled(true);
                                    button.setClickable(true);
                                    button.setBackgroundResource(android.R.drawable.btn_default);

                                    button2.setEnabled(true);
                                    button2.setClickable(true);
                                    button2.setBackgroundResource(android.R.drawable.btn_default);

                                    button3.setEnabled(false);
                                    button3.setClickable(false);
                                    button3.setBackgroundResource(android.R.drawable.btn_default);

                                    button4.setEnabled(false);
                                    button4.setClickable(false);
                                    button4.setBackgroundResource(android.R.drawable.btn_default);

                                    //button5.setEnabled(true);
                                    //button5.setClickable(true);
                                    //button5.setBackgroundResource(android.R.drawable.btn_default);

                                    button6.setEnabled(true);
                                    button6.setClickable(false);
                                    button6.setBackgroundColor(Color.parseColor("#9C27B0"));

                                    button7.setEnabled(false);
                                    button7.setClickable(false);
                                    button7.setBackgroundResource(android.R.drawable.btn_default);

                                    button8.setEnabled(false);
                                    button8.setClickable(false);
                                    button8.setBackgroundResource(android.R.drawable.btn_default);

                                    button9.setEnabled(true);
                                    button9.setClickable(true);
                                    button9.setBackgroundResource(android.R.drawable.btn_default);

                                    button10.setEnabled(false);
                                    button10.setClickable(false);
                                    button10.setBackgroundResource(android.R.drawable.btn_default);

                                    button11.setEnabled(false);
                                    button11.setClickable(false);
                                    button11.setBackgroundResource(android.R.drawable.btn_default);

                                    //button12.setEnabled(true);
                                    //button12.setClickable(true);
                                    //button12.setBackgroundResource(android.R.drawable.btn_default);

                                    button13.setEnabled(false);
                                    button13.setClickable(false);
                                    button13.setBackgroundResource(android.R.drawable.btn_default);

                                    Toast.makeText(getApplicationContext(), "Tanken", Toast.LENGTH_SHORT).show();
                                    break;
                                case 2:
                                    button.setEnabled(true);
                                    button.setClickable(true);
                                    button.setBackgroundResource(android.R.drawable.btn_default);

                                    button2.setEnabled(true);
                                    button2.setClickable(true);
                                    button2.setBackgroundResource(android.R.drawable.btn_default);

                                    button3.setEnabled(false);
                                    button3.setClickable(false);
                                    button3.setBackgroundResource(android.R.drawable.btn_default);

                                    button4.setEnabled(false);
                                    button4.setClickable(false);
                                    button4.setBackgroundResource(android.R.drawable.btn_default);

                                    //button5.setEnabled(true);
                                    //button5.setClickable(true);
                                    //button5.setBackgroundResource(android.R.drawable.btn_default);

                                    button6.setEnabled(true);
                                    button6.setClickable(false);
                                    button6.setBackgroundColor(Color.parseColor("#9C27B0"));

                                    button7.setEnabled(false);
                                    button7.setClickable(false);
                                    button7.setBackgroundResource(android.R.drawable.btn_default);

                                    button8.setEnabled(false);
                                    button8.setClickable(false);
                                    button8.setBackgroundResource(android.R.drawable.btn_default);

                                    button9.setEnabled(true);
                                    button9.setClickable(true);
                                    button9.setBackgroundResource(android.R.drawable.btn_default);

                                    button10.setEnabled(false);
                                    button10.setClickable(false);
                                    button10.setBackgroundResource(android.R.drawable.btn_default);

                                    button11.setEnabled(false);
                                    button11.setClickable(false);
                                    button11.setBackgroundResource(android.R.drawable.btn_default);

                                    //button12.setEnabled(true);
                                    //button12.setClickable(true);
                                    //button12.setBackgroundResource(android.R.drawable.btn_default);

                                    button13.setEnabled(false);
                                    button13.setClickable(false);
                                    button13.setBackgroundResource(android.R.drawable.btn_default);

                                    Toast.makeText(getApplicationContext(), "Mat. nachfassen", Toast.LENGTH_SHORT).show();
                                    break;
                                case 3:
                                    button.setEnabled(true);
                                    button.setClickable(true);
                                    button.setBackgroundResource(android.R.drawable.btn_default);

                                    button2.setEnabled(true);
                                    button2.setClickable(true);
                                    button2.setBackgroundResource(android.R.drawable.btn_default);

                                    button3.setEnabled(false);
                                    button3.setClickable(false);
                                    button3.setBackgroundResource(android.R.drawable.btn_default);

                                    button4.setEnabled(false);
                                    button4.setClickable(false);
                                    button4.setBackgroundResource(android.R.drawable.btn_default);

                                    //button5.setEnabled(true);
                                    //button5.setClickable(true);
                                    //button5.setBackgroundResource(android.R.drawable.btn_default);

                                    button6.setEnabled(true);
                                    button6.setClickable(false);
                                    button6.setBackgroundColor(Color.parseColor("#9C27B0"));

                                    button7.setEnabled(false);
                                    button7.setClickable(false);
                                    button7.setBackgroundResource(android.R.drawable.btn_default);

                                    button8.setEnabled(false);
                                    button8.setClickable(false);
                                    button8.setBackgroundResource(android.R.drawable.btn_default);

                                    button9.setEnabled(true);
                                    button9.setClickable(true);
                                    button9.setBackgroundResource(android.R.drawable.btn_default);

                                    button10.setEnabled(false);
                                    button10.setClickable(false);
                                    button10.setBackgroundResource(android.R.drawable.btn_default);

                                    button11.setEnabled(false);
                                    button11.setClickable(false);
                                    button11.setBackgroundResource(android.R.drawable.btn_default);

                                    //button12.setEnabled(true);
                                    //button12.setClickable(true);
                                    //button12.setBackgroundResource(android.R.drawable.btn_default);

                                    button13.setEnabled(false);
                                    button13.setClickable(false);
                                    button13.setBackgroundResource(android.R.drawable.btn_default);

                                    Toast.makeText(getApplicationContext(), "Bereitschaft", Toast.LENGTH_SHORT).show();
                                    break;
                                case 4:
                                    Toast.makeText(getApplicationContext(), "weiter EB", Toast.LENGTH_SHORT).show();
                                    break;
                            }
                        }
                    });

            dlgbuilder.create().show();

        }
    }

    public void st7(View v) {
        if (v.getId() == R.id.button7) {
            button.setEnabled(false);
            button.setClickable(false);
            button.setBackgroundResource(android.R.drawable.btn_default);

            button2.setEnabled(false);
            button2.setClickable(false);
            button2.setBackgroundResource(android.R.drawable.btn_default);

            button3.setEnabled(false);
            button3.setClickable(false);
            button3.setBackgroundResource(android.R.drawable.btn_default);

            button4.setEnabled(false);
            button4.setClickable(false);
            button4.setBackgroundResource(android.R.drawable.btn_default);

            //button5.setEnabled(true);
            //button5.setClickable(true);
            //button5.setBackgroundResource(android.R.drawable.btn_default);

            button6.setEnabled(false);
            button6.setClickable(false);
            button6.setBackgroundResource(android.R.drawable.btn_default);

            button7.setEnabled(true);
            button7.setClickable(false);
            button7.setBackgroundColor(GREEN);

            button8.setEnabled(true);
            button8.setClickable(true);
            button8.setBackgroundResource(android.R.drawable.btn_default);

            button9.setEnabled(false);
            button9.setClickable(false);
            button9.setBackgroundResource(android.R.drawable.btn_default);

            button10.setEnabled(false);
            button10.setClickable(false);
            button10.setBackgroundResource(android.R.drawable.btn_default);

            button11.setEnabled(false);
            button11.setClickable(false);
            button11.setBackgroundResource(android.R.drawable.btn_default);

            //button12.setEnabled(true);
            //button12.setClickable(true);
            //button12.setBackgroundResource(android.R.drawable.btn_default);

            button13.setEnabled(false);
            button13.setClickable(false);
            button13.setBackgroundResource(android.R.drawable.btn_default);
        }
    }

    public void st8(View v) {
        if (v.getId() == R.id.button8) {
            button.setEnabled(true);
            button.setClickable(true);
            button.setBackgroundResource(android.R.drawable.btn_default);

            button2.setEnabled(false);
            button2.setClickable(false);
            button2.setBackgroundResource(android.R.drawable.btn_default);

            button3.setEnabled(false);
            button3.setClickable(true);
            button3.setBackgroundResource(android.R.drawable.btn_default);

            button4.setEnabled(false);
            button4.setClickable(false);
            button4.setBackgroundResource(android.R.drawable.btn_default);

            //button5.setEnabled(true);
            //button5.setClickable(true);
            //button5.setBackgroundResource(android.R.drawable.btn_default);

            button6.setEnabled(false);
            button6.setClickable(false);
            button6.setBackgroundResource(android.R.drawable.btn_default);

            button7.setEnabled(false);
            button7.setClickable(false);
            button7.setBackgroundResource(android.R.drawable.btn_default);

            button8.setEnabled(true);
            button8.setClickable(false);
            button8.setBackgroundColor(GREEN);

            button9.setEnabled(false);
            button9.setClickable(false);
            button9.setBackgroundResource(android.R.drawable.btn_default);

            button10.setEnabled(false);
            button10.setClickable(false);
            button10.setBackgroundResource(android.R.drawable.btn_default);

            button11.setEnabled(false);
            button11.setClickable(false);
            button11.setBackgroundResource(android.R.drawable.btn_default);

            //button12.setEnabled(true);
            //button12.setClickable(true);
            //button12.setBackgroundResource(android.R.drawable.btn_default);

            button13.setEnabled(false);
            button13.setClickable(false);
            button13.setBackgroundResource(android.R.drawable.btn_default);
        }
    }

    public void st9(View v) {
        if (v.getId() == R.id.button9) {

            AlertDialog.Builder dlgBuilder = new AlertDialog.Builder(getApplicationContext());
            dlgBuilder.setMessage("Einheit ausser Dienst stellen?");
            dlgBuilder.setCancelable(false);
            dlgBuilder.setPositiveButton("Ja", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {

                    button.setEnabled(false);
                    button.setClickable(false);
                    button.setBackgroundResource(android.R.drawable.btn_default);

                    button2.setEnabled(true);
                    button2.setClickable(true);
                    button2.setBackgroundResource(android.R.drawable.btn_default);

                    button3.setEnabled(false);
                    button3.setClickable(false);
                    button3.setBackgroundResource(android.R.drawable.btn_default);

                    button4.setEnabled(false);
                    button4.setClickable(false);
                    button4.setBackgroundResource(android.R.drawable.btn_default);

                    //button5.setEnabled(true);
                    //button5.setClickable(true);
                    //button5.setBackgroundResource(android.R.drawable.btn_default);

                    button6.setEnabled(true);
                    button6.setClickable(true);
                    button6.setBackgroundResource(android.R.drawable.btn_default);

                    button7.setEnabled(false);
                    button7.setClickable(false);
                    button7.setBackgroundResource(android.R.drawable.btn_default);

                    button8.setEnabled(false);
                    button8.setClickable(false);
                    button8.setBackgroundResource(android.R.drawable.btn_default);

                    button9.setEnabled(true);
                    button9.setClickable(false);
                    button9.setBackgroundColor(Color.parseColor("#9C27B0"));

                    button10.setEnabled(false);
                    button10.setClickable(false);
                    button10.setBackgroundResource(android.R.drawable.btn_default);

                    button11.setEnabled(false);
                    button11.setClickable(false);
                    button11.setBackgroundResource(android.R.drawable.btn_default);

                    //button12.setEnabled(true);
                    //button12.setClickable(true);
                    //button12.setBackgroundResource(android.R.drawable.btn_default);

                    button13.setEnabled(false);
                    button13.setClickable(false);
                    button13.setBackgroundResource(android.R.drawable.btn_default);

                    Toast.makeText(getApplicationContext(), "Ausser Dienst", Toast.LENGTH_SHORT).show();
                }
            });

            dlgBuilder.setNegativeButton("Nein", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
            });

            AlertDialog alert = dlgBuilder.create();
            alert.show();
        }
    }

    public void st10(View v) {
        if (v.getId() == R.id.button10) {

            AlertDialog.Builder dlgBuilder = new AlertDialog.Builder(getApplicationContext());
            dlgBuilder.setMessage("Intervention unterblieben?");
            dlgBuilder.setCancelable(false);
            dlgBuilder.setPositiveButton("Ja", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {

                    button.setEnabled(true);
                    button.setClickable(true);
                    button.setBackgroundResource(android.R.drawable.btn_default);

                    button10.setEnabled(true);
                    button10.setClickable(false);
                    button10.setBackgroundColor(GREEN);

                    button11.setEnabled(false);
                    button11.setClickable(false);
                    button11.setBackgroundResource(android.R.drawable.btn_default);

                    button13.setEnabled(false);
                    button13.setClickable(false);
                    button13.setBackgroundResource(android.R.drawable.btn_default);

                    button7.setEnabled(false);
                    button7.setClickable(false);
                    button7.setBackgroundResource(android.R.drawable.btn_default);

                    Toast.makeText(getApplicationContext(), "keine Intervention", Toast.LENGTH_SHORT).show();
                }
            });

            dlgBuilder.setNegativeButton("Nein", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
            });

            AlertDialog alert = dlgBuilder.create();
            alert.show();
        }
    }

    public void st11(View v) {
        if (v.getId() == R.id.button11) {

            AlertDialog.Builder dlgbuilder = new AlertDialog.Builder(getApplicationContext());
            dlgbuilder.setTitle("Patient belassen/verweigert?");
            dlgbuilder.setItems(new CharSequence[]
                            {"Patient belassen", "Patient verweigert", "Nein"},

                    new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int which) {

                            switch (which) {

                                case 0:
                                    button.setEnabled(true);
                                    button.setClickable(true);
                                    button.setBackgroundResource(android.R.drawable.btn_default);

                                    button11.setEnabled(true);
                                    button11.setClickable(false);
                                    button11.setBackgroundColor(GREEN);

                                    button10.setEnabled(false);
                                    button10.setClickable(false);
                                    button10.setBackgroundResource(android.R.drawable.btn_default);

                                    button13.setEnabled(false);
                                    button13.setClickable(false);
                                    button13.setBackgroundResource(android.R.drawable.btn_default);

                                    button7.setEnabled(false);
                                    button7.setClickable(false);
                                    button7.setBackgroundResource(android.R.drawable.btn_default);
                                    Toast.makeText(getApplicationContext(), "Belassung", Toast.LENGTH_SHORT).show();
                                    break;
                                case 1:
                                    button.setEnabled(true);
                                    button.setClickable(true);
                                    button.setBackgroundResource(android.R.drawable.btn_default);

                                    button11.setEnabled(true);
                                    button11.setClickable(false);
                                    button11.setBackgroundColor(GREEN);

                                    button10.setEnabled(false);
                                    button10.setClickable(false);
                                    button10.setBackgroundResource(android.R.drawable.btn_default);

                                    button13.setEnabled(false);
                                    button13.setClickable(false);
                                    button13.setBackgroundResource(android.R.drawable.btn_default);

                                    button7.setEnabled(false);
                                    button7.setClickable(false);
                                    button7.setBackgroundResource(android.R.drawable.btn_default);
                                    Toast.makeText(getApplicationContext(), "Patient verweigert", Toast.LENGTH_SHORT).show();
                                    break;
                                case 2:
                                    break;
                            }
                        }
                    });

            dlgbuilder.create().show();

        }
    }

    public void st12(View v) {
        if (v.getId() == R.id.button12) {

            AlertDialog.Builder dlgBuilder = new AlertDialog.Builder(getApplicationContext());
            dlgBuilder.setMessage("NOTRUF senden?");
            dlgBuilder.setCancelable(false);
            dlgBuilder.setPositiveButton("Ja", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {

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
                }
            });

            AlertDialog alert = dlgBuilder.create();
            alert.show();


        }
    }

    public void st13(View v) {
        if (v.getId() == R.id.button13) {

            AlertDialog.Builder dlgBuilder = new AlertDialog.Builder(getApplicationContext());
            dlgBuilder.setMessage("Patient an anderes Rettungsmittel übergeben?");
            dlgBuilder.setCancelable(false);
            dlgBuilder.setPositiveButton("Ja", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {

                    button.setEnabled(true);
                    button.setClickable(true);
                    button.setBackgroundResource(android.R.drawable.btn_default);

                    button13.setEnabled(true);
                    button13.setClickable(false);
                    button13.setBackgroundColor(GREEN);

                    button10.setEnabled(false);
                    button10.setClickable(false);
                    button10.setBackgroundResource(android.R.drawable.btn_default);

                    button11.setEnabled(false);
                    button11.setClickable(false);
                    button11.setBackgroundResource(android.R.drawable.btn_default);

                    button7.setEnabled(false);
                    button7.setClickable(false);
                    button7.setBackgroundResource(android.R.drawable.btn_default);

                    Toast.makeText(getApplicationContext(), "Übergabe an anderes Rettungsmittel", Toast.LENGTH_SHORT).show();
                }
            });

            dlgBuilder.setNegativeButton("Nein", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
            });

            AlertDialog alert = dlgBuilder.create();
            alert.show();
        }
    }

    public void st14(View v) {
        if (v.getId() == R.id.button42) {

            AlertDialog.Builder dlgBuilder = new AlertDialog.Builder(getApplicationContext());
            dlgBuilder.setMessage("Neuen Einsatz bei derzeitiger Position melden?");
            dlgBuilder.setCancelable(false);
            dlgBuilder.setPositiveButton("Ja", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {

                    button42.setEnabled(false);
                    button42.setClickable(false);
                    button42.setBackgroundColor(YELLOW);

                    Toast.makeText(getApplicationContext(), "Neuen Einsatz an Leitstelle gemeldet", Toast.LENGTH_SHORT).show();
                }
            });

            dlgBuilder.setNegativeButton("Nein", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
            });

            AlertDialog alert = dlgBuilder.create();
            alert.show();
        }
    }
}
