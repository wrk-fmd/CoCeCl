package it.fmd.cocecl.patadminaction;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import it.fmd.cocecl.R;

public class CreatePat implements View.OnClickListener {

    public Activity activity;

    public CreatePat(Activity _activity) {

        this.activity = _activity;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //CreatePat
            case R.id.button46:
                createpat();
                break;
            //ChangePat
            case R.id.changepatbtn:
                changepat();
                break;
            case R.id.bettbtn:
                //bettbuchen();
                break;

        }
    }

    //Shared Preferences
    String patfirstname, patlastname, patdatebirth, patsvnr, patplsnr, patgender, patward;
    String getpatfirstname, getpatlastname, getpatdatebirth, getpatsvnr, getpatplsnr, getpatgender, getpatward;
    public static final String PatData = "patprefs";


    // Patient Management dialog builder //
    // data stored in shared preferences

    // Pat. anlegen
    public void createpat() {

        RelativeLayout patmanlayout = (RelativeLayout) this.activity.getLayoutInflater().inflate(R.layout.patman, null);
        final Button bettbtn = (Button) patmanlayout.findViewById(R.id.bettbtn);
        final Button button46 = (Button) this.activity.findViewById(R.id.button46);

        final TextView textView116 = (TextView) this.activity.findViewById(R.id.textView116);
        TextView textView120 = (TextView) this.activity.findViewById(R.id.textView120);


        final LinearLayout patmanbtnlinlay = (LinearLayout) this.activity.findViewById(R.id.patmanbtnlinlay);

        final EditText addpatfirstname = (EditText) patmanlayout.findViewById(R.id.editText2);
        final EditText addpatlastname = (EditText) patmanlayout.findViewById(R.id.editText);
        final EditText addpatdatebirth = (EditText) patmanlayout.findViewById(R.id.editText3);
        //final EditText addpatsvnr = (EditText) patmanlayout.findViewById(R.id.);
        final EditText addpatplsnr = (EditText) patmanlayout.findViewById(R.id.editText4);
        final TextView addpatward = (TextView) patmanlayout.findViewById(R.id.textView11);
        //Spinner addpatgender = (Spinner) patmanlayout.findViewById(R.id.spinner);

        //Shared Prefs// Create Patient
        final SharedPreferences spref = this.activity.getSharedPreferences(PatData, Context.MODE_PRIVATE);

        AlertDialog.Builder dlgBuilder = new AlertDialog.Builder(this.activity);
        //dlgBuilder.setMessage("Patient anlegen");
        dlgBuilder.setTitle("PATADMIN");
        //LayoutInflater inflater = (MainActivity.this.getLayoutInflater());
        bettbtn.setEnabled(true);

        dlgBuilder.setView(patmanlayout).setPositiveButton("Senden", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                // Store in sharedprefs
                patfirstname = addpatfirstname.getText().toString();
                patlastname = addpatlastname.getText().toString();
                patdatebirth = addpatdatebirth.getText().toString();
                //patsvnr = addactoradd.getText().toString();
                patplsnr = addpatplsnr.getText().toString();
                //patgender = addpatgender.getText().toString();
                patward = addpatward.getText().toString();


                SharedPreferences.Editor patedit = spref.edit();

                patedit.putString("patfirstname", patfirstname);
                patedit.putString("patlastname", patlastname);
                patedit.putString("patdatebirth", patdatebirth);
                //patedit.putString("patsvnr", patsvnr);
                patedit.putString("patplsnr", patplsnr);
                //patedit.putString("patgender", patgender);
                patedit.putString("patward", patward);

                patedit.apply();

                //TODO: send data

                textView116.setVisibility(View.VISIBLE);
                textView116.setText("Patient angelegt");

                // Set Pat. Management Buttons visible
                patmanbtnlinlay.setVisibility(View.VISIBLE);

                button46.setEnabled(false);
                button46.setClickable(false);


            }
        });

        dlgBuilder.setNegativeButton("Zurück", new DialogInterface.OnClickListener()

                {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        // Store in sharedprefs
                        patfirstname = addpatfirstname.getText().toString();
                        patlastname = addpatlastname.getText().toString();
                        patdatebirth = addpatdatebirth.getText().toString();
                        //patsvnr = addactoradd.getText().toString();
                        patplsnr = addpatplsnr.getText().toString();
                        //patgender = addpatgender.getText().toString();
                        patward = addpatward.getText().toString();


                        SharedPreferences.Editor patedit = spref.edit();

                        patedit.putString("patfirstname", patfirstname);
                        patedit.putString("patlastname", patlastname);
                        patedit.putString("patdatebirth", patdatebirth);
                        //patedit.putString("patsvnr", patsvnr);
                        patedit.putString("patplsnr", patplsnr);
                        //patedit.putString("patgender", patgender);
                        patedit.putString("patward", patward);

                        patedit.apply();

                        button46.setEnabled(true);
                        button46.setClickable(true);
                    }
                }

        );
/*
        dlgBuilder.setNeutralButton("Abteilung", new DialogInterface.OnClickListener()

                {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        //bettbuchen();

                    }
                }

        );
*/
        AlertDialog alert = dlgBuilder.create();
        alert.show();
    }

    public void changepat() {

        RelativeLayout patmanlayout = (RelativeLayout) this.activity.getLayoutInflater().inflate(R.layout.patman, null);
        Button bettbtn = (Button) patmanlayout.findViewById(R.id.bettbtn);
        TextView textView11 = (TextView) patmanlayout.findViewById(R.id.textView11);

        final TextView textView120 = (TextView) this.activity.findViewById(R.id.textView120);


        LinearLayout patmanbtnlinlay = (LinearLayout) this.activity.findViewById(R.id.patmanbtnlinlay);

        EditText addpatfirstname = (EditText) patmanlayout.findViewById(R.id.editText2);
        EditText addpatlastname = (EditText) patmanlayout.findViewById(R.id.editText);
        EditText addpatdatebirth = (EditText) patmanlayout.findViewById(R.id.editText3);
        //final EditText addpatsvnr = (EditText) patmanlayout.findViewById(R.id.);
        EditText addpatplsnr = (EditText) patmanlayout.findViewById(R.id.editText4);
        TextView addpatward = (TextView) patmanlayout.findViewById(R.id.textView11);
        Spinner addpatgender = (Spinner) patmanlayout.findViewById(R.id.spinner);

        //Shared Prefs// Create Patient
        SharedPreferences spref = this.activity.getSharedPreferences(PatData, Context.MODE_PRIVATE);

        AlertDialog.Builder dlgBuilder = new AlertDialog.Builder(this.activity);
        //dlgBuilder.setMessage("Patient anlegen");
        dlgBuilder.setTitle("PATADMIN");
        //LayoutInflater inflater = (MainActivity.this.getLayoutInflater());
        bettbtn.setEnabled(false);

        dlgBuilder = new AlertDialog.Builder(this.activity);
        //dlgBuilder.setMessage("Patient anlegen");
        dlgBuilder.setTitle("PATADMIN");

        //LayoutInflater inflater = (MainActivity.this.getLayoutInflater());

        dlgBuilder.setView(patmanlayout);
        //bettbtn.setEnabled(false);
        //bettbtn.setClickable(false);
        bettbtn.setVisibility(View.GONE);
        textView11.setVisibility(View.GONE);

        //Getting Stored data from SharedPreferences
        getpatfirstname = spref.getString("patfirstname", "");
        getpatlastname = spref.getString("patlastname", "");
        getpatdatebirth = spref.getString("patdatebirth", "");
        //getpatsvnr = spref.getString("patsvnr", "");
        getpatplsnr = spref.getString("patplsnr", "");
        //getpatgender = spref.getString("patgender", "");
        getpatward = spref.getString("patward", "");

        //write to textview
        addpatfirstname.setText("" + getpatfirstname);
        addpatlastname.setText("" + getpatlastname);
        addpatdatebirth.setText("" + getpatdatebirth);
        //addpatsvnr.setText(""+getpatsvnr);
        addpatplsnr.setText("" + getpatplsnr);
        addpatward.setText("" + getpatgender);
        //addpatgender.setText(""+getpatward);

        dlgBuilder.setPositiveButton("Senden", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                //remove layout
                removelayout();

                //TODO: store again in sharedprefs / send pat data to server

                textView120.setVisibility(View.VISIBLE);
                textView120.setText("Pat. Daten geändert");

            }
        });

        dlgBuilder.setNegativeButton("Zurück", new DialogInterface.OnClickListener()

                {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                    }
                }

        );

        AlertDialog alert = dlgBuilder.create();
        alert.show();

    }

    public void removelayout() {
        View viewToRemove = this.activity.findViewById(R.id.patmanrelayout);
        if (viewToRemove != null && viewToRemove.getParent() != null && viewToRemove instanceof ViewGroup)
            ((ViewGroup) viewToRemove.getParent()).removeView(viewToRemove);
    }
/*
    // Abteilung buchen
    public void bettbuchen() {

        final RelativeLayout patmanlayout = (RelativeLayout) this.activity.getLayoutInflater().inflate(R.layout.patman, null);

        final TextView textView116 = (TextView) this.activity.findViewById(R.id.textView116);

        AlertDialog.Builder dlgbuilder = new AlertDialog.Builder(this.activity);
            dlgbuilder.setTitle("Abteilung auswählen");
            dlgbuilder.setItems(new CharSequence[]
                            {"Intern", "Unfall", "Chirurgie", "HNO", "Dermatologie", "Spezialbett", "andere Abteilung"},

                    new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int which) {

                            TextView abtedit = (TextView) patmanlayout.findViewById(R.id.textView11);

                            switch (which) {

                                case 0:
                                    abtedit.setText(R.string.intern);
                                    textView116.setText(R.string.intern);
                                    //Toast.makeText(getApplicationContext(), R.string.intern, Toast.LENGTH_SHORT).show();
                                    break;

                                case 1:
                                    abtedit.setText(R.string.unfall, TextView.BufferType.EDITABLE);
                                    //Toast.makeText(getApplicationContext(), R.string.unfall, Toast.LENGTH_SHORT).show();
                                    break;

                                case 2:
                                    abtedit.setText(R.string.chir, TextView.BufferType.EDITABLE);
                                    //Toast.makeText(getApplicationContext(), R.string.chir, Toast.LENGTH_SHORT).show();
                                    break;

                                case 3:
                                    abtedit.setText(R.string.hno, TextView.BufferType.EDITABLE);
                                    //Toast.makeText(getApplicationContext(), R.string.hno, Toast.LENGTH_SHORT).show();
                                    break;

                                case 4:
                                    abtedit.setText(R.string.derma, TextView.BufferType.EDITABLE);
                                    //Toast.makeText(getApplicationContext(), R.string.derma, Toast.LENGTH_SHORT).show();
                                    break;

                                case 5:
                                    abtedit.setText(R.string.spezbett, TextView.BufferType.EDITABLE);
                                    //Toast.makeText(getApplicationContext(), R.string.lsbvanrufen, Toast.LENGTH_LONG).show();
                                    break;

                                case 6:
                                    abtedit.setText(R.string.andbett, TextView.BufferType.EDITABLE);
                                    //Toast.makeText(getApplicationContext(), R.string.lsbvanrufen, Toast.LENGTH_LONG).show();
                                    break;
                            }
                        }
                    }
            );

            dlgbuilder.create().

                    show();
        }*/
}

