package it.fmd.cocecl.patadminaction;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import it.fmd.cocecl.R;

import static android.graphics.Color.GREEN;

public class PatStatus implements View.OnClickListener {

    public Activity activity;

    public PatStatus(Activity _activity) {

        this.activity = _activity;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //INTUNT
            case R.id.button10:
                intunt();
                break;
            //BEL/VER
            case R.id.button11:
                belver();
                break;
            //and RM
            case R.id.button13:
                andrm();
                break;
        }
    }

    //OnClick
    public void onclickintunt() {
        final Button button41 = (Button) this.activity.findViewById(R.id.button41);
        final TextView textView83 = (TextView) this.activity.findViewById(R.id.statusView);
        final TextView patstattv = (TextView) this.activity.findViewById(R.id.textView116);

        Button button10 = (Button) this.activity.findViewById(R.id.button10);
        button10.setEnabled(false);
        button10.setClickable(false);
        button10.setBackgroundColor(GREEN);

        Button button11 = (Button) this.activity.findViewById(R.id.button11);
        button11.setEnabled(false);
        button11.setClickable(false);
        button11.setBackgroundColor(Color.parseColor("#bdbdbd"));
        //button11.setBackgroundResource(android.R.drawable.btn_default);

        Button button13 = (Button) this.activity.findViewById(R.id.button13);
        button13.setEnabled(false);
        button13.setClickable(false);
        button13.setBackgroundColor(Color.parseColor("#bdbdbd"));
        //button13.setBackgroundResource(android.R.drawable.btn_default);

        button41.setEnabled(true);
        button41.setClickable(true);
        button41.setBackgroundColor(Color.parseColor("#bdbdbd"));
        //button41.setBackgroundResource(android.R.drawable.btn_default);
        button41.setText("Einsatz abschliessen");
        button41.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_play_arrow_black_18dp, 0, 0, 0);
        textView83.setText("EB");

        Button button46 = (Button) this.activity.findViewById(R.id.button13);
        button46.setEnabled(false);
        button46.setClickable(false);
        button46.setBackgroundColor(Color.parseColor("#bdbdbd"));
        //button46.setBackgroundResource(android.R.drawable.btn_default);
    }

    public void onclickandrm() {
        final Button button41 = (Button) this.activity.findViewById(R.id.button41);
        final TextView textView83 = (TextView) this.activity.findViewById(R.id.statusView);
        final TextView patstattv = (TextView) this.activity.findViewById(R.id.textView116);

        Button button10 = (Button) this.activity.findViewById(R.id.button10);
        button10.setEnabled(false);
        button10.setClickable(false);
        button10.setBackgroundColor(Color.parseColor("#bdbdbd"));

        Button button11 = (Button) this.activity.findViewById(R.id.button11);
        button11.setEnabled(false);
        button11.setClickable(false);
        button11.setBackgroundColor(Color.parseColor("#bdbdbd"));
        //button11.setBackgroundResource(android.R.drawable.btn_default);

        Button button13 = (Button) this.activity.findViewById(R.id.button13);
        button13.setEnabled(false);
        button13.setClickable(false);
        button13.setBackgroundColor(GREEN);
        //button13.setBackgroundResource(android.R.drawable.btn_default);

        button41.setEnabled(true);
        button41.setClickable(true);
        button41.setBackgroundColor(Color.parseColor("#bdbdbd"));
        //button41.setBackgroundResource(android.R.drawable.btn_default);
        button41.setText("Einsatz abschliessen");
        button41.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_play_arrow_black_18dp, 0, 0, 0);
        textView83.setText("EB");

        Button button46 = (Button) this.activity.findViewById(R.id.button13);
        button46.setEnabled(false);
        button46.setClickable(false);
        button46.setBackgroundColor(Color.parseColor("#bdbdbd"));
        //button46.setBackgroundResource(android.R.drawable.btn_default);
    }

    public void onclickbelver() {
        final Button button41 = (Button) this.activity.findViewById(R.id.button41);
        final TextView textView83 = (TextView) this.activity.findViewById(R.id.statusView);
        final TextView patstattv = (TextView) this.activity.findViewById(R.id.textView116);

        Button button10 = (Button) this.activity.findViewById(R.id.button10);
        button10.setEnabled(false);
        button10.setClickable(false);
        button10.setBackgroundColor(Color.parseColor("#bdbdbd"));

        Button button11 = (Button) this.activity.findViewById(R.id.button11);
        button11.setEnabled(false);
        button11.setClickable(false);
        button11.setBackgroundColor(GREEN);
        //button11.setBackgroundResource(android.R.drawable.btn_default);

        Button button13 = (Button) this.activity.findViewById(R.id.button13);
        button13.setEnabled(false);
        button13.setClickable(false);
        button13.setBackgroundColor(Color.parseColor("#bdbdbd"));
        //button13.setBackgroundResource(android.R.drawable.btn_default);

        button41.setEnabled(true);
        button41.setClickable(true);
        button41.setBackgroundColor(Color.parseColor("#bdbdbd"));
        //button41.setBackgroundResource(android.R.drawable.btn_default);
        button41.setText("Einsatz abschliessen");
        button41.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_play_arrow_black_18dp, 0, 0, 0);
        textView83.setText("EB");

        Button button46 = (Button) this.activity.findViewById(R.id.button13);
        button46.setEnabled(false);
        button46.setClickable(false);
        button46.setBackgroundColor(Color.parseColor("#bdbdbd"));
        //button46.setBackgroundResource(android.R.drawable.btn_default);
    }

    //INTUNT
    public void intunt() {
        final TextView patstattv = (TextView) this.activity.findViewById(R.id.textView116);
        final AlertDialog.Builder dlgBuilder = new AlertDialog.Builder(this.activity);

        dlgBuilder.setMessage("Intervention unterblieben?");
        dlgBuilder.setCancelable(false);
        dlgBuilder.setPositiveButton("Ja", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                onclickintunt();
                //Toast.makeText(MainActivity.this, "keine Intervention", Toast.LENGTH_SHORT).show();
                patstattv.setVisibility(View.VISIBLE);
                patstattv.setText("Intervention unterblieben");

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


    //BEL/VER
    public void belver() {
        final TextView patstattv = (TextView) this.activity.findViewById(R.id.textView116);

        final AlertDialog.Builder dlgBuilder = new AlertDialog.Builder(this.activity);
        dlgBuilder.setTitle("Patient belassen/verweigert?");
        dlgBuilder.setItems(new CharSequence[]
                        {"Patient belassen", "Patient verweigert", "Nein"},

                new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {

                        switch (which) {

                            case 0:
                                onclickbelver();
                                //Toast.makeText(MainActivity.this, "Belassung", Toast.LENGTH_SHORT).show();
                                patstattv.setVisibility(View.VISIBLE);
                                patstattv.setText("Belassung");
                                break;
                            case 1:
                                onclickbelver();
                                //Toast.makeText(MainActivity.this, "Patient verweigert", Toast.LENGTH_SHORT).show();
                                patstattv.setVisibility(View.VISIBLE);
                                patstattv.setText("Patient verweigert");
                                break;
                            case 2:
                                break;
                        }
                    }
                });

        dlgBuilder.create().show();
    }

    //Anderes RM
    public void andrm() {
        final Button button41 = (Button) this.activity.findViewById(R.id.button41);
        final TextView textView83 = (TextView) this.activity.findViewById(R.id.statusView);

        final TextView patstattv = (TextView) this.activity.findViewById(R.id.textView116);

        final AlertDialog.Builder dlgBuilder = new AlertDialog.Builder(this.activity);
        dlgBuilder.setMessage("Patient an anderes Rettungsmittel übergeben?");
        dlgBuilder.setCancelable(false);
        dlgBuilder.setPositiveButton("Ja", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                onclickandrm();
                //Toast.makeText(MainActivity.this, "Übergabe an anderes Rettungsmittel", Toast.LENGTH_SHORT).show();
                patstattv.setVisibility(View.VISIBLE);
                patstattv.setText("Übergabe an anderes Rettungsmittel");
            }
        });

        dlgBuilder.setNegativeButton("Nein", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        dlgBuilder.create().show();
    }
}
