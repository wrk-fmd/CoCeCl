package it.fmd.cocecl.patadminaction;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import it.fmd.cocecl.MainActivity;
import it.fmd.cocecl.R;

public class CreatePat extends MainActivity {

    private Button bettbtn;
    private TextView textView11;
    private EditText addpatfirstname;
    private EditText addpatlastname;
    private EditText addpatdatebirth;
    private EditText addpatplsnr;
    private TextView addpatward;
    private Spinner addpatgender;

    private AlertDialog.Builder dlgBuilder;

    private LinearLayout patmanbtnlinlay;
    private RelativeLayout patmanlayout;

    //Shared Preferences
    SharedPreferences spref;
    String patfirstname, patlastname, patdatebirth, patsvnr, patplsnr, patgender, patward;
    String getpatfirstname, getpatlastname, getpatdatebirth, getpatsvnr, getpatplsnr, getpatgender, getpatward;
    public static final String PatData = "patprefs";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Text fields
        patmanlayout = (RelativeLayout) getLayoutInflater().inflate(R.layout.patman, null);
        bettbtn = (Button) patmanlayout.findViewById(R.id.bettbtn);
        textView11 = (TextView) patmanlayout.findViewById(R.id.textView11);

        patmanbtnlinlay = (LinearLayout) findViewById(R.id.patmanbtnlinlay);

        addpatfirstname = (EditText) patmanlayout.findViewById(R.id.editText2);
        addpatlastname = (EditText) patmanlayout.findViewById(R.id.editText);
        addpatdatebirth = (EditText) patmanlayout.findViewById(R.id.editText3);
        //final EditText addpatsvnr = (EditText) patmanlayout.findViewById(R.id.);
        addpatplsnr = (EditText) patmanlayout.findViewById(R.id.editText4);
        addpatward = (TextView) patmanlayout.findViewById(R.id.textView11);
        addpatgender = (Spinner) patmanlayout.findViewById(R.id.spinner);
        dlgBuilder = new AlertDialog.Builder(getApplicationContext());

        //Shared Prefs// Create Patient
        spref = getSharedPreferences(PatData, Context.MODE_PRIVATE);
    }

    // Patient Management dialog builder //
    // data stored in shared preferences

    // Pat. anlegen
    public void createpat() {

        AlertDialog.Builder dlgBuilder = new AlertDialog.Builder(getApplicationContext());
        //dlgBuilder.setMessage("Patient anlegen");
        dlgBuilder.setTitle("PATADMIN");
        //LayoutInflater inflater = (MainActivity.this.getLayoutInflater());
        bettbtn.setEnabled(false);

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

                Toast.makeText(getApplicationContext(), "Patient angelegt", Toast.LENGTH_SHORT).show();

                // Set Pat. Management Buttons visible
                patmanbtnlinlay.setVisibility(View.VISIBLE);

                bettbtn.setEnabled(false);


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

                        bettbtn.setEnabled(true);
                    }
                }

        );

        AlertDialog alert = dlgBuilder.create();
        alert.show();
    }

    public void changepat() {

        AlertDialog.Builder dlgBuilder = new AlertDialog.Builder(getApplicationContext());
        //dlgBuilder.setMessage("Patient anlegen");
        dlgBuilder.setTitle("PATADMIN");
        //LayoutInflater inflater = (MainActivity.this.getLayoutInflater());
        bettbtn.setEnabled(false);

        dlgBuilder = new AlertDialog.Builder(getApplicationContext());
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
                View viewToRemove = findViewById(R.id.patmanrelayout);
                if (viewToRemove != null && viewToRemove.getParent() != null && viewToRemove instanceof ViewGroup)
                    ((ViewGroup) viewToRemove.getParent()).removeView(viewToRemove);

                //TODO: store again in sharedprefs / send pat data to server

                Toast.makeText(getApplicationContext(), "Pat. Daten geändert", Toast.LENGTH_SHORT).show();

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

    /*
            //remove layout after dialog creation
            View viewToRemove = findViewById(R.id.patmanrelayout);
            if (viewToRemove != null && viewToRemove.getParent() != null && viewToRemove instanceof ViewGroup)
                ((ViewGroup) viewToRemove.getParent()).removeView(viewToRemove);
        }
    */
    // Abteilung buchen
    public void bettbuchen(View v) {

        final RelativeLayout patmanlayout = (RelativeLayout) getLayoutInflater().inflate(R.layout.patman, null);
        /*
        LayoutInflater inflater = getLayoutInflater();
        getWindow().addContentView(inflater.inflate(R.layout.patman, null), new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
*/
        if (v.getId() == R.id.bettbtn) {

            AlertDialog.Builder dlgbuilder = new AlertDialog.Builder(getApplicationContext());
            dlgbuilder.setTitle("Abteilung auswählen");
            dlgbuilder.setItems(new CharSequence[]
                            {"Intern", "Unfall", "Chirurgie", "HNO", "Dermatologie", "Spezialbett", "andere Abteilung"},

                    new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int which) {

                            TextView abtedit = (TextView) patmanlayout.findViewById(R.id.textView11);

                            switch (which) {

                                case 0:
                                    abtedit.setText(R.string.intern);
                                    Toast.makeText(getApplicationContext(), R.string.intern, Toast.LENGTH_SHORT).show();
                                    break;

                                case 1:
                                    abtedit.setText(R.string.unfall, TextView.BufferType.EDITABLE);
                                    Toast.makeText(getApplicationContext(), R.string.unfall, Toast.LENGTH_SHORT).show();
                                    break;

                                case 2:
                                    abtedit.setText(R.string.chir, TextView.BufferType.EDITABLE);
                                    Toast.makeText(getApplicationContext(), R.string.chir, Toast.LENGTH_SHORT).show();
                                    break;

                                case 3:
                                    abtedit.setText(R.string.hno, TextView.BufferType.EDITABLE);
                                    Toast.makeText(getApplicationContext(), R.string.hno, Toast.LENGTH_SHORT).show();
                                    break;

                                case 4:
                                    abtedit.setText(R.string.derma, TextView.BufferType.EDITABLE);
                                    Toast.makeText(getApplicationContext(), R.string.derma, Toast.LENGTH_SHORT).show();
                                    break;

                                case 5:
                                    abtedit.setText(R.string.spezbett, TextView.BufferType.EDITABLE);
                                    Toast.makeText(getApplicationContext(), R.string.lsbvanrufen, Toast.LENGTH_LONG).show();
                                    break;

                                case 6:
                                    abtedit.setText(R.string.andbett, TextView.BufferType.EDITABLE);
                                    Toast.makeText(getApplicationContext(), R.string.lsbvanrufen, Toast.LENGTH_LONG).show();
                                    break;
                            }
                        }
                    }
            );

            dlgbuilder.create().

                    show();
        }
    }
}
