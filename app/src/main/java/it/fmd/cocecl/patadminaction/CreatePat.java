package it.fmd.cocecl.patadminaction;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import it.fmd.cocecl.R;
import it.fmd.cocecl.dataStorage.PatData;

public class CreatePat implements View.OnClickListener {

    PatData pd = new PatData();

    public Activity activity;

    public CreatePat(Activity _activity) {

        this.activity = _activity;
    }

    RelativeLayout patmanlayout;
    Button bettbtn;
    Button button46;

    TextView textView116;

    LinearLayout patmanbtnlinlay;

    EditText addpatplsnr;

    Spinner addpatgender;

    EditText addpatfirstname;
    EditText addpatlastname;
    EditText addpatdatebirth;
    EditText addpatsvnr;

    Spinner addpatward;

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

    //Spinner int
    int spinnerint;

    SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy", Locale.GERMAN);
    Calendar c = Calendar.getInstance();
    int startYear = c.get(Calendar.YEAR);
    int startMonth = c.get(Calendar.MONTH);
    int startDay = c.get(Calendar.DAY_OF_MONTH);

    // Patient Management dialog builder //
    // Pat. anlegen
    public void createpat() {

        patmanlayout = (RelativeLayout) activity.getLayoutInflater().inflate(R.layout.patman, null);
        bettbtn = (Button) patmanlayout.findViewById(R.id.bettbtn);
        button46 = (Button) activity.findViewById(R.id.button46);

        textView116 = (TextView) activity.findViewById(R.id.textView116);

        patmanbtnlinlay = (LinearLayout) activity.findViewById(R.id.patmanbtnlinlay);

        addpatplsnr = (EditText) patmanlayout.findViewById(R.id.editText4);

        addpatgender = (Spinner) patmanlayout.findViewById(R.id.spinner);

        addpatfirstname = (EditText) patmanlayout.findViewById(R.id.editText2);
        addpatlastname = (EditText) patmanlayout.findViewById(R.id.editText);
        addpatdatebirth = (EditText) patmanlayout.findViewById(R.id.editText3);
        addpatsvnr = (EditText) patmanlayout.findViewById(R.id.editText32);

        addpatward = (Spinner) patmanlayout.findViewById(R.id.spinner17);

        addpatdatebirth.setText("01.01.1900");

        // Create Patient
        AlertDialog.Builder dlgBuilder = new AlertDialog.Builder(activity);
        //dlgBuilder.setMessage("Patient anlegen");
        dlgBuilder.setTitle("PATADMIN");
        //LayoutInflater inflater = (MainActivity.this.getLayoutInflater());
        bettbtn.setEnabled(true);
        dlgBuilder.setView(patmanlayout);
/*
        dlgBuilder.setNegativeButton("Zurück", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();
                    }
                }

        );
*/
        AlertDialog alert = dlgBuilder.create();
        alert.show();
    }

    private void createNewPat() {

        patplsnr = addpatplsnr.getText().toString();
        patlastname = addpatlastname.getText().toString();
        patfirstname = addpatfirstname.getText().toString();
        patdatebirth = addpatdatebirth.getText().toString();
        patsvnr = addpatsvnr.getText().toString();
        patgender = addpatgender.getSelectedItem().toString();
        patward = addpatward.getSelectedItem().toString();

        spinnerint = addpatgender.getSelectedItemPosition();


        PatData patdat = new PatData();
        patdat.setPatbdate(patdatebirth);
        patdat.setPatsvnr(patsvnr);
        patdat.setPatgender(""); // TODO get value from spinner
        patdat.setPatname(patlastname); // last-/familyname
        patdat.setPatfname(patfirstname); // firstname
        patdat.setPatID(""); // TODO: get from server
        patdat.setPatgender(patgender);
        patdat.setPatplsnr(patplsnr);
        patdat.setPatward(patward);
        //patdat.setPatdiagnosis();
    }

    public void storePatData() {
        // Store in Instance State
        patplsnr = addpatplsnr.getText().toString();
        patlastname = addpatlastname.getText().toString();
        patfirstname = addpatfirstname.getText().toString();
        patdatebirth = addpatdatebirth.getText().toString();
        patsvnr = addpatsvnr.getText().toString();
        patgender = addpatgender.getSelectedItem().toString();
        patward = addpatward.getSelectedItem().toString();

        spinnerint = addpatgender.getSelectedItemPosition();


        if (!patplsnr.isEmpty())
            pd.setPatplsnr(patplsnr);

        if (!patlastname.isEmpty())
            pd.setPatfname(patlastname);
        if (!patfirstname.isEmpty())
            pd.setPatname(patfirstname);
        if (!patdatebirth.isEmpty())
            pd.setPatbdate(patdatebirth);
        if (!patsvnr.isEmpty())
            pd.setPatsvnr(patsvnr);
        if (!patgender.isEmpty())
            pd.setPatgender(patgender);

        if (!patward.isEmpty())
            pd.setPatward(patward);

        textView116.setVisibility(View.VISIBLE);
        textView116.setText("Patient angelegt");

        // Set Pat. Management Buttons visible
        patmanbtnlinlay.setVisibility(View.VISIBLE);

        button46.setEnabled(false);
        button46.setClickable(false);
    }

    public void changepat() {

        RelativeLayout patmanlayout = (RelativeLayout) activity.getLayoutInflater().inflate(R.layout.patman, null);
        Button bettbtn = (Button) patmanlayout.findViewById(R.id.bettbtn);
        Button sendPat = (Button) patmanlayout.findViewById(R.id.sendPat);

        final TextView textView120 = (TextView) activity.findViewById(R.id.textView120);

        final LinearLayout patmanbtnlinlay = (LinearLayout) activity.findViewById(R.id.patmanbtnlinlay);

        final EditText addpatplsnr = (EditText) patmanlayout.findViewById(R.id.editText4);

        final Spinner addpatgender = (Spinner) patmanlayout.findViewById(R.id.spinner);

        addpatward = (Spinner) patmanlayout.findViewById(R.id.spinner17);

        final EditText addpatfirstname = (EditText) patmanlayout.findViewById(R.id.editText2);
        final EditText addpatlastname = (EditText) patmanlayout.findViewById(R.id.editText);
        final EditText addpatdatebirth = (EditText) patmanlayout.findViewById(R.id.editText3);
        final EditText addpatsvnr = (EditText) patmanlayout.findViewById(R.id.editText32);

        TextView textView4 = (TextView) patmanlayout.findViewById(R.id.textView4);

        AlertDialog.Builder dlgBuilder = new AlertDialog.Builder(activity);
        //dlgBuilder.setMessage("Patient anlegen");
        dlgBuilder.setTitle("PATADMIN");

        //Change layout to match "change patient"
        bettbtn.setEnabled(false);
        sendPat.setText("Pat. aktualisieren");
        addpatward.setVisibility(View.GONE);
        textView4.setVisibility(View.GONE);

        dlgBuilder = new AlertDialog.Builder(activity);
        //dlgBuilder.setMessage("Patient anlegen");
        dlgBuilder.setTitle("PATADMIN");

        //LayoutInflater inflater = (MainActivity.this.getLayoutInflater());

        dlgBuilder.setView(patmanlayout);
        //bettbtn.setEnabled(false);
        //bettbtn.setClickable(false);
        bettbtn.setVisibility(View.GONE);

        // Get Patient
        patplsnr = pd.getPatplsnr();
        patlastname = pd.getPatfname();
        patfirstname = pd.getPatname();
        patdatebirth = pd.getPatbdate();
        patsvnr = pd.getPatsvnr();
        patgender = pd.getPatgender();
        patward = pd.getPatward();

        addpatplsnr.setText(patplsnr);
        addpatlastname.setText(patlastname);
        addpatfirstname.setText(patfirstname);
        addpatdatebirth.setText(patdatebirth);
        addpatsvnr.setText(patsvnr);
        addpatgender.setSelection(spinnerint);

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
        View viewToRemove = activity.findViewById(R.id.patmanrelayout);
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

