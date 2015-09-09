package it.fmd.cocecl;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class patmanActivity extends FragmentActivity {
    private FragmentTabHost mTabHost;

    private static final String TAG = "DialogActivity";

    private static final int DLG_Allergien = 0;
    private static final int DLG_Medikamente = 1;

    private static final int TEXT_Allergien = 0;
    private static final int TEXT_Medikamente = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patman);

        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), android.R.id.tabcontent);

        mTabHost.addTab(
                mTabHost.newTabSpec("tab1").setIndicator("PatDat", null),
                patmanFragment.class, null);
        mTabHost.addTab(
                mTabHost.newTabSpec("tab2").setIndicator("ABCDE", null),
                abcde.class, null);
        mTabHost.addTab(
                mTabHost.newTabSpec("tab3").setIndicator("Trauma", null),
                trauma.class, null);
        /**mTabHost.addTab(
         mTabHost.newTabSpec("tab4").setIndicator("Anamnese&Diagnose", null),
         trauma.class, null);*/
    }

    public void btnClick() {
        // Creates the dialog if necessary, then shows it.
        // Will show the same dialog if called multiple times.

        Button button24 = (Button) findViewById(R.id.button24);
        button24.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                showDialog(DLG_Allergien);
            }
        });

        Button button25 = (Button) findViewById(R.id.button25);
        button25.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg1) {
                showDialog(DLG_Medikamente);
            }
        });
    }


    /**
     * Called to create a dialog to be shown.
     */
    @Override
    protected Dialog onCreateDialog(int id) {

        switch (id) {
            case DLG_Allergien:
                return createDialog();
            case DLG_Medikamente:
                return createDialog2();
            default:
                return null;
        }
    }

    /**
     * If a dialog has already been created,
     * this is called to reset the dialog
     * before showing it a 2nd time. Optional.
     */
    @Override
    protected void onPrepareDialog(int id, Dialog dialog) {

        switch (id) {
            case 0:
                // Clear the input box.
                EditText text = (EditText) dialog.findViewById(TEXT_Allergien);
                text.setText("");
                break;
            case 1:
                // Clear the input box.
                EditText text2 = (EditText) dialog.findViewById(TEXT_Medikamente);
                text2.setText("");
                break;
        }
    }

    /**
     * Create and return an example alert dialog with an edit text box.
     */
    private Dialog createDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Allergien");
        //builder.setMessage("");

        // Use an EditText view to get user input.
        final EditText input = new EditText(this);
        input.setId(TEXT_Allergien);
        builder.setView(input);

        builder.setPositiveButton("Speichern", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int whichButton) {
                String value = input.getText().toString();
                Log.d(TAG, "User name: " + value);
                return;
            }
        });

        builder.setNegativeButton("Abbrechen", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                return;
            }
        });

        return builder.create();
    }

    private Dialog createDialog2() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Medikamente");
        //builder.setMessage("");

        // Use an EditText view to get user input.
        final EditText input = new EditText(this);
        input.setId(TEXT_Medikamente);
        builder.setView(input);

        builder.setPositiveButton("Speichern", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int whichButton) {
                String value = input.getText().toString();
                Log.d(TAG, "User name: " + value);
                return;
            }
        });

        builder.setNegativeButton("Abbrechen", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                return;
            }
        });

        return builder.create();
    }

    public void gcsbtn(View v) {
        if (v.getId() == R.id.button23) {

            AlertDialog.Builder dlgBuilder = new AlertDialog.Builder(patmanActivity.this);
            dlgBuilder.setMessage("GCS eintragen");

            LayoutInflater inflater = (patmanActivity.this.getLayoutInflater());

            dlgBuilder.setView(inflater.inflate(R.layout.gcs, null))

                    .setPositiveButton("speichern&zurück", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });

            AlertDialog alert = dlgBuilder.create();
            alert.show();

        }
    }

    public void onCheckboxClicked(View view) {

        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();

        // Check which checkbox was clicked
        switch (view.getId()) {

            case R.id.checkBox2:
                if (checked) {
                    Toast.makeText(patmanActivity.this,
                            "checked", Toast.LENGTH_LONG).show();

                    CheckBox checkBox2 = (CheckBox) findViewById(R.id.checkBox2);
                    //checkBox2.setEnabled(true);
                    checkBox2.setClickable(true);

                    CheckBox checkBox3 = (CheckBox) findViewById(R.id.checkBox3);
                    //checkBox3.setEnabled(false);
                    checkBox3.setClickable(false);

                    CheckBox checkBox4 = (CheckBox) findViewById(R.id.checkBox4);
                    //checkBox4.setEnabled(false);
                    checkBox4.setClickable(false);

                    CheckBox checkBox5 = (CheckBox) findViewById(R.id.checkBox5);
                    //checkBox5.setEnabled(false);
                    checkBox5.setClickable(false);
                }
                    else
                {
                    Toast.makeText(patmanActivity.this,
                            "unchecked", Toast.LENGTH_LONG).show();

                    CheckBox checkBox2 = (CheckBox) findViewById(R.id.checkBox2);
                    //checkBox2.setEnabled(true);
                    checkBox2.setClickable(true);

                    CheckBox checkBox3 = (CheckBox) findViewById(R.id.checkBox3);
                    //checkBox3.setEnabled(true);
                    checkBox3.setClickable(true);

                    CheckBox checkBox4 = (CheckBox) findViewById(R.id.checkBox4);
                    //checkBox4.setEnabled(true);
                    checkBox4.setClickable(true);

                    CheckBox checkBox5 = (CheckBox) findViewById(R.id.checkBox5);
                    //checkBox5.setEnabled(true);
                    checkBox5.setClickable(true);
                }
                    break;

                    case R.id.checkBox3:
                        if (checked) {
                            // Cheese me
                        }
                        else {
                            // I'm lactose intolerant
                        }
                        break;
                    case R.id.checkBox4:
                        if (checked) {
                            // Cheese me
                        }
                        else {
                            // I'm lactose intolerant
                        }
                        break;
                    case R.id.checkBox5:
                        if (checked) {
                            // Cheese me
                        }
                        else {
                            // I'm lactose intolerant
                        }
                        break;
                    // TODO: Veggie sandwich
                }
        }

/**
        checkBox2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (checkBox2.isChecked()) {
                    System.out.println("Checked");
                } else {
                    System.out.println("Un-Checked");
                }
            }
        });
    }
 */

    public void checkBox3(View v) {
        if (v.getId() == R.id.checkBox3) {

            CheckBox checkBox2 = (CheckBox) findViewById(R.id.checkBox2);
            checkBox2.setEnabled(false);
            checkBox2.setClickable(false);

            CheckBox checkBox3 = (CheckBox) findViewById(R.id.checkBox3);
            checkBox3.setEnabled(true);
            checkBox3.setClickable(true);

            CheckBox checkBox4 = (CheckBox) findViewById(R.id.checkBox4);
            checkBox4.setEnabled(false);
            checkBox4.setClickable(false);

            CheckBox checkBox5 = (CheckBox) findViewById(R.id.checkBox5);
            checkBox5.setEnabled(false);
            checkBox5.setClickable(false);
        }
    }

    public void checkBox4(View v) {
        if (v.getId() == R.id.checkBox4) {

            CheckBox checkBox2 = (CheckBox) findViewById(R.id.checkBox2);
            checkBox2.setEnabled(false);
            checkBox2.setClickable(false);

            CheckBox checkBox3 = (CheckBox) findViewById(R.id.checkBox3);
            checkBox3.setEnabled(false);
            checkBox3.setClickable(false);

            CheckBox checkBox4 = (CheckBox) findViewById(R.id.checkBox4);
            checkBox4.setEnabled(true);
            checkBox4.setClickable(false);

            CheckBox checkBox5 = (CheckBox) findViewById(R.id.checkBox5);
            checkBox5.setEnabled(false);
            checkBox5.setClickable(false);
        }
    }

    public void checkBox5(View v) {
        if (v.getId() == R.id.checkBox5) {

            CheckBox checkBox2 = (CheckBox) findViewById(R.id.checkBox2);
            checkBox2.setEnabled(false);
            checkBox2.setClickable(false);

            CheckBox checkBox3 = (CheckBox) findViewById(R.id.checkBox3);
            checkBox3.setEnabled(false);
            checkBox3.setClickable(false);

            CheckBox checkBox4 = (CheckBox) findViewById(R.id.checkBox4);
            checkBox4.setEnabled(false);
            checkBox4.setClickable(false);

            CheckBox checkBox5 = (CheckBox) findViewById(R.id.checkBox5);
            checkBox5.setEnabled(true);
            checkBox5.setClickable(false);
        }
    }
}



