package it.fmd.cocecl.utilclass;


import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import it.fmd.cocecl.MainActivity;
import it.fmd.cocecl.R;

/**
 * Validate Input on RegisterUser Dialog
 */

public class CustomDialog_registeruser extends Activity {

    @Override
    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_main2);

        displayDialogWindow();
    }

    public void displayDialogWindow() {
        final AlertDialog.Builder loginDialog = new AlertDialog.Builder(new ContextThemeWrapper(this, android.R.style.Theme_DeviceDefault_Light_Dialog));
        LayoutInflater factory = LayoutInflater.from(this);
        final View f = factory.inflate(R.layout.register_user_layout, null);

        loginDialog.setTitle("Please enter your credentials");
        loginDialog.setView(f);

        Button submit = (Button) f.findViewById(R.id.btnRegister);

        submit.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View v) {

                                          EditText familyname = (EditText) f.findViewById(R.id.registerfamilyname);
                                          EditText name = (EditText) f.findViewById(R.id.registername);
                                          EditText dnr = (EditText) f.findViewById(R.id.registerdnr);
                                          EditText regmail = (EditText) f.findViewById(R.id.registeremail);
                                          EditText password = (EditText) f.findViewById(R.id.registerpassword);

                                          String fname = familyname.getText().toString();
                                          String email = regmail.getText().toString();

                                          boolean validDNr = ValidateInput.isValidDnr(fname);
                                          boolean validEmail = ValidateInput.isValidEmail(email);

                                          if (!validDNr || !validEmail) {
                                              if (!validDNr) {
                                                  familyname.setError("Please enter a valid name");
                                              }

                                              if (!validEmail) {
                                                  regmail.setError("Please enter a valid email address");
                                              }
                                          } else {

                                              //send data to the database
                                              //basically like dismissing the dialog window here (you can start a new intent)

                                          }
                                      }
                                  }
        );
        loginDialog.show();
    }
}