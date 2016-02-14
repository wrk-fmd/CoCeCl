package it.fmd.cocecl.utilclass;


import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import it.fmd.cocecl.MainActivity;
import it.fmd.cocecl.R;

/**
 * Validate Input on RegisterUser Dialog
 */

public class CustomDialog_registeruser extends MainActivity {

    @Override
    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_main);

        //displayDialogWindow();
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
                EditText email = (EditText) f.findViewById(R.id.registeremail);
                EditText password = (EditText) f.findViewById(R.id.registerpassword);

                String dnr = full_name.getText().toString();
                String email = email_address.getText().toString();

                boolean validDNr = ValidateInput.isValidDnr(dnr);
                boolean validEmail = ValidateInput.isValidEmail(email);

                if (!validDNr || !validEmail) {
                    if (!validDNr) {
                        full_name.setError("Please enter a valid name");
                    }

                    if (!validEmail) {
                        email_address.setError("Please enter a valid email address");
                    }
                } else {
                    //send data to the database
                    //basically like dismissing the dialog window here (you can start a new intent)

                }
            }

            )

            loginDialog.show()
        }
    }
}

