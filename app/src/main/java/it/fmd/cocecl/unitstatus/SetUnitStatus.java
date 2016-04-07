package it.fmd.cocecl.unitstatus;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import it.fmd.cocecl.R;
import it.fmd.cocecl.dataStorage.UnitStatus;

/**
 * emergency and radio call server function
 *
 * Selectiv, Emergency, EB, NEB, AD
 * NEB Addition: Refuel, get Material, Pause, Other
 */

public class SetUnitStatus implements View.OnClickListener {

    UnitStatus us = new UnitStatus();

    public Activity activity;

    public SetUnitStatus(Activity _activity) {

        this.activity = _activity;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button5:
                selectivbtn();
                break;

            case R.id.button12:
                emergencybtn();
                break;

            case R.id.button38:
                st1();
                EBstoreStatus();
                break;

            case R.id.button39:
                st6();
                NEBstoreStatus();
                break;

            case R.id.button40:
                st9();
                ADstoreStatus();
                break;
        }
    }

    //Store in UnitStatus

    public void EBstoreStatus() {
        us.setUstatus("EB");
    }

    public void NEBstoreStatus() {
        us.setUstatus("NEB");
    }

    public void ADstoreStatus() {
        us.setUstatus("AD");
    }

    public void RefuelInfoStore() {
        us.setUstaddition("Tanken");
    }

    public void GetMatInfoStore() {
        us.setUstaddition("Material nachfassen");
    }

    public void BreakInfoStore() {
        us.setUstaddition("Bereitschaft");
    }

    public void OtherInfoStore() {
        us.setUstaddition("anderer Grund");
    }

    //Radio
    //SelectivRuf
    public void selectivbtn() {
        final AlertDialog.Builder dlgBuilder = new AlertDialog.Builder(this.activity);
        dlgBuilder.setMessage("Selektivruf senden?");
        dlgBuilder.setCancelable(false);
        dlgBuilder.setPositiveButton("Ja", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                selectivmt();
            }
        });
        dlgBuilder.setNegativeButton("Nein", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        dlgBuilder.create().show();
    }

    public void selectivmt() {

        final Button button5 = (Button) this.activity.findViewById(R.id.button5);
        final Button button12 = (Button) this.activity.findViewById(R.id.button12);
        final TextView textView112 = (TextView) this.activity.findViewById(R.id.textView112);

        button5.setEnabled(false);
        button5.setClickable(false);
        //button5.setBackgroundColor(Color.YELLOW);
        button5.setBackground(this.activity.getResources().getDrawable(R.drawable.button_yellow_pressed));
        //Toast.makeText(getApplicationContext(), "Selektivruf gesendet", Toast.LENGTH_SHORT).show();
        textView112.setVisibility(View.VISIBLE);
        textView112.setText("Selektivruf gesendet");

        Handler h = new Handler();
        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                selectivsetback();
            }
        }, 30000);
    }

    private void selectivsetback() {

        final Button button5 = (Button) this.activity.findViewById(R.id.button5);
        final TextView textView112 = (TextView) this.activity.findViewById(R.id.textView112);

        button5.setEnabled(true);
        button5.setClickable(true);
        //button5.setBackgroundColor(Color.parseColor("#bdbdbd"));
        //button5.setBackgroundResource(android.R.drawable.btn_default);
        button5.setBackground(this.activity.getResources().getDrawable(R.drawable.custom_button_normal));
        textView112.setText("");
        textView112.setVisibility(View.GONE);
    }


    //NOTRUF
    public void emergencybtn() {
        final AlertDialog.Builder dlgBuilder = new AlertDialog.Builder(this.activity);
        dlgBuilder.setMessage("NOTRUF senden?");
        dlgBuilder.setCancelable(false);
        dlgBuilder.setPositiveButton("Ja", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                emergencymt();
            }
        });

        dlgBuilder.setNegativeButton("Nein", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        dlgBuilder.create().show();
    }

    public void emergencymt() {

        final Button button5 = (Button) this.activity.findViewById(R.id.button5);
        final Button button12 = (Button) this.activity.findViewById(R.id.button12);
        final TextView textView112 = (TextView) this.activity.findViewById(R.id.textView112);

        button12.setEnabled(false);
        button12.setClickable(false);
        //button12.setBackgroundColor(RED);
        button12.setBackground(this.activity.getResources().getDrawable(R.drawable.button_red_pressed));
        //Toast.makeText(getApplicationContext(), "NOTRUF gesendet", Toast.LENGTH_LONG).show();
        textView112.setVisibility(View.VISIBLE);
        textView112.setText("NOTRUF gesendet");

        Handler h = new Handler();
        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                emergencysetback();
            }
        }, 45000);
    }

    private void emergencysetback() {

        final Button button12 = (Button) this.activity.findViewById(R.id.button12);
        final TextView textView112 = (TextView) this.activity.findViewById(R.id.textView112);

        button12.setEnabled(true);
        button12.setClickable(true);
        //button12.setBackgroundColor(Color.parseColor("#bdbdbd"));
        //button5.setBackgroundResource(android.R.drawable.btn_default);
        button12.setBackground(this.activity.getResources().getDrawable(R.drawable.custom_button_normal));
        textView112.setText("");
        textView112.setVisibility(View.GONE);
    }

    // Status EB NEB AD mainstatusFragment //
    // TODO: set by server ebst(); nebst(); adst(); not by user (for now)

    public void st1() {

        AlertDialog.Builder dlgBuilder = new AlertDialog.Builder(this.activity);
        dlgBuilder.setMessage("Einheit EB melden?");
        dlgBuilder.setCancelable(false);
        dlgBuilder.setPositiveButton("Ja", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                ebst();

                //Toast.makeText(getActivity(), "Im Dienst", Toast.LENGTH_SHORT).show();
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


    public void ebst() {

        Button button38 = (Button) this.activity.findViewById(R.id.button38);
        Button button39 = (Button) this.activity.findViewById(R.id.button39);
        Button button40 = (Button) this.activity.findViewById(R.id.button40);

        CardView cardviewst = (CardView) this.activity.findViewById(R.id.cardviewst);
        LinearLayout statusbtnlinlay = (LinearLayout) this.activity.findViewById(R.id.statusbtnlinlay);

        TextView textView111 = (TextView) this.activity.findViewById(R.id.textView111);

        button38.setEnabled(false);
        button38.setClickable(false);
        //button38.setBackgroundColor(GREEN);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            button38.setBackground(this.activity.getDrawable(R.drawable.button_green_pressed));
        }

        button39.setEnabled(true);
        button39.setClickable(false);
        //button39.setBackgroundColor(Color.parseColor("#bdbdbd"));
        //button39.setBackgroundResource(android.R.drawable.btn_default);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            button39.setBackground(this.activity.getDrawable(R.drawable.custom_button_normal));
        }

        button40.setEnabled(true);
        button40.setClickable(false);
        //button40.setBackgroundColor(Color.parseColor("#bdbdbd"));
        //button40.setBackgroundResource(android.R.drawable.btn_default);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            button40.setBackground(this.activity.getDrawable(R.drawable.custom_button_normal));
        }

        //cardviewst.setCardBackgroundColor(GREEN);
        statusbtnlinlay.setBackgroundColor(Color.parseColor("#76FF03"));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            statusbtnlinlay.setBackgroundColor(this.activity.getColor(R.color.btnclr_pdgreen));
        }

        textView111.setVisibility(View.VISIBLE);
        textView111.setText(R.string.eb);

    }

    public void st6() {

        final TextView textView111 = (TextView) this.activity.findViewById(R.id.textView111);

        AlertDialog.Builder dlgbuilder = new AlertDialog.Builder(this.activity);
        dlgbuilder.setTitle("Einheit nicht einsatzbereit melden?");
        dlgbuilder.setItems(new CharSequence[]
                        {"NEB (andere Grund)", "Tanken", "Material nachfassen", "Bereitschaft", "Nein"},

                new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {

                        switch (which) {
                            case 0:
                                nebst();
                                //Toast.makeText(getActivity(), "Nicht EB", Toast.LENGTH_SHORT).show();
                                textView111.setVisibility(View.VISIBLE);
                                textView111.setText("Nicht EB");
                                OtherInfoStore();
                                break;
                            case 1:
                                nebst();
                                //Toast.makeText(getActivity(), "Tanken", Toast.LENGTH_SHORT).show();
                                textView111.setVisibility(View.VISIBLE);
                                textView111.setText("Tanken");
                                RefuelInfoStore();
                                break;
                            case 2:
                                nebst();
                                //Toast.makeText(getActivity(), "Mat. nachfassen", Toast.LENGTH_SHORT).show();
                                textView111.setVisibility(View.VISIBLE);
                                textView111.setText("Material nachfassen");
                                GetMatInfoStore();
                                break;
                            case 3:
                                nebst();
                                //Toast.makeText(getActivity(), "Bereitschaft", Toast.LENGTH_SHORT).show();
                                textView111.setVisibility(View.VISIBLE);
                                textView111.setText("Bereitschaft");
                                BreakInfoStore();
                                break;
                            case 4:
                                //Toast.makeText(getActivity(), "weiter EB", Toast.LENGTH_SHORT).show();
                                textView111.setVisibility(View.VISIBLE);
                                textView111.setText("weiter EB");
                                break;
                        }
                    }
                });
        dlgbuilder.create().show();
    }


    public void nebst() {

        Button button38 = (Button) this.activity.findViewById(R.id.button38);
        Button button39 = (Button) this.activity.findViewById(R.id.button39);
        Button button40 = (Button) this.activity.findViewById(R.id.button40);

        CardView cardviewst = (CardView) this.activity.findViewById(R.id.cardviewst);
        LinearLayout statusbtnlinlay = (LinearLayout) this.activity.findViewById(R.id.statusbtnlinlay);

        button38.setEnabled(true);
        button38.setClickable(false);
        //button38.setBackgroundColor(Color.parseColor("#bdbdbd"));
        //button38.setBackgroundResource(android.R.drawable.btn_default);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            button38.setBackground(this.activity.getDrawable(R.drawable.custom_button_normal));
        }

        button39.setEnabled(false);
        button39.setClickable(false);
        //button39.setBackgroundColor(Color.parseColor("#EF6C00"));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            button39.setBackground(this.activity.getDrawable(R.drawable.button_yellow_pressed));
        }

        button40.setEnabled(true);
        button40.setClickable(false);
        //button40.setBackgroundColor(Color.parseColor("#bdbdbd"));
        //button40.setBackgroundResource(android.R.drawable.btn_default);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            button40.setBackground(this.activity.getDrawable(R.drawable.custom_button_normal));
        }

        //cardviewst.setCardBackgroundColor(Color.parseColor("#EF6C00"));
        statusbtnlinlay.setBackgroundColor(Color.parseColor("#EF6C00"));
    }

    public void st9() {

        AlertDialog.Builder dlgBuilder = new AlertDialog.Builder(this.activity);
        dlgBuilder.setMessage("Einheit ausser Dienst stellen?");
        dlgBuilder.setCancelable(false);
        dlgBuilder.setPositiveButton("Ja", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                adst();
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


    public void adst() {

        Button button38 = (Button) this.activity.findViewById(R.id.button38);
        Button button39 = (Button) this.activity.findViewById(R.id.button39);
        Button button40 = (Button) this.activity.findViewById(R.id.button40);

        CardView cardviewst = (CardView) this.activity.findViewById(R.id.cardviewst);
        LinearLayout statusbtnlinlay = (LinearLayout) this.activity.findViewById(R.id.statusbtnlinlay);

        TextView textView111 = (TextView) this.activity.findViewById(R.id.textView111);

        button38.setEnabled(true);
        button38.setClickable(false);
        //button38.setBackgroundColor(Color.parseColor("#bdbdbd"));
        //button38.setBackgroundResource(android.R.drawable.btn_default);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            button38.setBackground(this.activity.getDrawable(R.drawable.custom_button_normal));
        }

        button39.setEnabled(true);
        button39.setClickable(false);
        //button39.setBackgroundColor(Color.parseColor("#bdbdbd"));
        //button39.setBackgroundResource(android.R.drawable.btn_default);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            button39.setBackground(this.activity.getDrawable(R.drawable.custom_button_normal));
        }

        button40.setEnabled(false);
        button40.setClickable(false);
        //button40.setBackgroundColor(Color.parseColor("#9C27B0"));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            button40.setBackground(this.activity.getDrawable(R.drawable.button_purple_pressed));
        }

        textView111.setVisibility(View.VISIBLE);
        textView111.setText("Ausser Dienst");

        //cardviewst.setCardBackgroundColor(Color.parseColor("#9C27B0"));
        statusbtnlinlay.setBackgroundColor(Color.parseColor("#9C27B0"));
    }


}
