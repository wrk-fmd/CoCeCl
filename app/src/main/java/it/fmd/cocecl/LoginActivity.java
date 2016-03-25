package it.fmd.cocecl;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.HashMap;

import it.fmd.cocecl.gcm.RegisterGCM;
import it.fmd.cocecl.utilclass.CheckPlayServices;
import it.fmd.cocecl.utilclass.ConnectionManager;
import it.fmd.cocecl.utilclass.JSONParser;
import it.fmd.cocecl.utilclass.SessionManagement;
import it.fmd.cocecl.utilclass.ValidateInput;

/**
 * LogIn and Register Class
 * <p/>
 * Register Fields:
 * ETxt FamilyName
 * ETxt Name
 * ETxt DNR
 * ETxt EMail
 * ETxt Password
 * Button Register
 * <p/>
 * Login Fields:
 * ETxt DNR
 * ETxt Password
 * Button SignIn
 * Button gotoRegister
 * TxtV ErrorMessage
 * ProgressBar
 */

public class LoginActivity extends MainActivity {

    // Login Layout
    private Button signinbtn;
    private Button gotoregisterbtn;
    private EditText logindnr;
    private EditText loginpassword;

    private TextView errormsgtxt;
    private ProgressBar loginProgressBar;

    //Strings Register
    public String familyname;
    public String name;
    public String dnr;
    public String email;
    public String password;

    // RegisterLayout
    private LinearLayout registeruserlayout;

    private EditText registerfamilyname;
    private EditText registername;
    private EditText registerdnr;
    private EditText registeremail;
    private EditText registerpassword;

    private Button btnRegister;

    // Floating Labels & errorMessages
    private Toolbar toolbar;
    private TextInputLayout inputLayoutDnr, inputLayoutEmail, inputLayoutPassword;

    // User Session Manager Class
    SessionManagement session;


    //GCM
    private static final String TAG = "LoginActivity";

    private BroadcastReceiver mRegistrationBroadcastReceiver;
    private ProgressBar mRegistrationProgressBar;
    private TextView mInformationTextView;
    private boolean isReceiverRegistered;

    CheckPlayServices cps = new CheckPlayServices();
    ConnectionManager cm = new ConnectionManager();


    // OnCreate Method // -------------------------------------- //
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // User Session Manager
        session = new SessionManagement(getApplicationContext());

        //cm.ping();

        // Register Layout EditText
        registeruserlayout = (LinearLayout) getLayoutInflater().inflate(R.layout.register_user_layout, null);
        registerfamilyname = (EditText) registeruserlayout.findViewById(R.id.registerfamilyname);
        registername = (EditText) registeruserlayout.findViewById(R.id.registername);
        registerdnr = (EditText) registeruserlayout.findViewById(R.id.registerdnr);
        registeremail = (EditText) registeruserlayout.findViewById(R.id.registeremail);
        registerpassword = (EditText) registeruserlayout.findViewById(R.id.registerpassword);


        // OnClickListerners SignIn & Register
        signinbtn = (Button) findViewById(R.id.signinbtn);
        btnRegister = (Button) registeruserlayout.findViewById(R.id.btnRegister);
        gotoregisterbtn = (Button) findViewById(R.id.gotoregisterbtn);

        //SignIn
        signinbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckSignIn();
            }
        });

        //gotoRegisterScreen
        gotoregisterbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoRegisterScreen();
            }
        });
/*
        //Register
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegisterUserGCM();
                RegisterUserMLS();
            }
        });
*/

        // GCM Server //
        mRegistrationProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        mRegistrationBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                mRegistrationProgressBar.setVisibility(ProgressBar.GONE);
                SharedPreferences sharedPreferences =
                        PreferenceManager.getDefaultSharedPreferences(context);
                boolean sentToken = sharedPreferences
                        .getBoolean(APPConstants.SENT_TOKEN_TO_SERVER, false);
                if (sentToken) {
                    mInformationTextView.setText(getString(R.string.gcm_ok));
                    mInformationTextView.setTextColor(Color.GREEN);

                    Handler h = new Handler();
                    h.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mInformationTextView.setText("");
                            mInformationTextView.setTextColor(Color.BLACK);
                        }
                    }, 3000);

                } else {
                    mInformationTextView.setText(getString(R.string.gcm_error2));
                    mInformationTextView.setTextColor(Color.RED);

                    Handler h = new Handler();
                    h.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mInformationTextView.setText("");
                            mInformationTextView.setTextColor(Color.BLACK);
                        }
                    }, 3000);
                }
            }
        };
        mInformationTextView = (TextView) findViewById(R.id.textView94);

        // Registering BroadcastReceiver
        registerReceiver();

        if (cps.checkPlayServices(this)) {
            // Start IntentService to register this application with GCM.
            Intent intent = new Intent(this, RegisterGCM.class);
            startService(intent);
        }

        //Floating Labels//
        inputLayoutDnr = (TextInputLayout) findViewById(R.id.input_layout_dnr);
        inputLayoutPassword = (TextInputLayout) findViewById(R.id.input_layout_password);
        logindnr = (EditText) findViewById(R.id.logindnr);
        loginpassword = (EditText) findViewById(R.id.loginpassword);

        //inputEmail = (EditText) findViewById(R.id.loginemail);
        logindnr = (EditText) findViewById(R.id.logindnr);
        loginpassword = (EditText) findViewById(R.id.loginpassword);
        signinbtn = (Button) findViewById(R.id.signinbtn);
        gotoregisterbtn = (Button) findViewById(R.id.gotoregisterbtn);
        errormsgtxt = (TextView) findViewById(R.id.textView94);

        // LogIn TextChangeListener
        logindnr.addTextChangedListener(new LogInTextWatcher(logindnr));
        //inputEmail.addTextChangedListener(new LogInTextWatcher(inputEmail));
        loginpassword.addTextChangedListener(new LogInTextWatcher(loginpassword));

        // Register TextChangeListener //TODO: Focus loss on input
        //registerfamilyname.addTextChangedListener(new RegisterEntryWatcher(registerfamilyname));
        //registername.addTextChangedListener(new RegisterEntryWatcher(registername));

        if (!cps.checkPlayServices(this)) {
            signinbtn.setEnabled(false);
            signinbtn.setClickable(false);
            logindnr.setEnabled(false);
            loginpassword.setEnabled(false);
            //gotoregisterbtn.setEnabled(false);
            //gotoregisterbtn.setClickable(false);
            errormsgtxt.setText("App locked, Play Services needed!");
        }
    }

    // OnCreate END ----------------------------------------------------------------- //

    @Override
    public void onStart() {
        super.onStart();
        //checkMLSConnection();
    }

    @Override
    public void onResume() {
        super.onResume();
        cps.checkPlayServices(this);
        registerReceiver();
        //checkMLSConnection();
    }

    @Override
    public void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mRegistrationBroadcastReceiver);
        isReceiverRegistered = false;

    }

    @Override
    public void onStop() {
        super.onStop();

    }

    @Override
    public void onDestroy() {
/*
        try{
            if(mReceiver!=null)
                unregisterReceiver(mReceiver);
        }catch(Exception e)
        {

        }
        */
        super.onDestroy();
    }

    //GCM Receiver
    private void registerReceiver() {
        if (!isReceiverRegistered) {
            LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver,
                    new IntentFilter(APPConstants.REGISTRATION_COMPLETE));
            isReceiverRegistered = true;
        }
    }

    // LogIn to Server//
    //POST
    class PostAsync extends AsyncTask<String, String, JSONObject> {
        JSONParser jsonParser = new JSONParser();

        private ProgressDialog pDialog;

        private static final String TAG_SUCCESS = "success";
        private static final String TAG_MESSAGE = "message";


        @Override
        protected void onPreExecute() {
            pDialog = new ProgressDialog(LoginActivity.this);
            pDialog.setMessage("Attempting login...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        @Override
        protected JSONObject doInBackground(String... args) {

            try {

                HashMap<String, String> params = new HashMap<>();
                params.put("name", args[0]);
                params.put("password", args[1]);

                Log.d("request", "starting");

                JSONObject json = jsonParser.makeHttpRequest(
                        APPConstants.URL_LOGIN, "POST", params);

                if (json != null) {
                    Log.d("JSON result", json.toString());

                    return json;
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        protected void onPostExecute(JSONObject json) {

            int success = 0;
            String message = "";

            if (pDialog != null && pDialog.isShowing()) {
                pDialog.dismiss();
            }

            if (json != null) {
                Toast.makeText(LoginActivity.this, json.toString(),
                        Toast.LENGTH_LONG).show();

                try {
                    success = json.getInt(TAG_SUCCESS);
                    message = json.getString(TAG_MESSAGE);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            if (success == 1) {

                //LoginSuccess
                Log.d("Success!", message);
                //Create Session
                session.createUserLoginSession(familyname, dnr, email);
                //SignIn
                SignIn();

            } else {

                //ErrorLogin
                Log.d("Failure", message);
                FailureSignIn();

            }
        }
    }

    public void sendRegisterData() throws UnsupportedEncodingException {

        String FName, Name, Email, Dnr, Pass;

        // Get user defined values
        FName = registerfamilyname.getText().toString();
        Name = registername.getText().toString();
        Email = registeremail.getText().toString();
        Dnr = registerdnr.getText().toString();
        Pass = registerpassword.getText().toString();

        // Create data variable for sent values to server
        String data = URLEncoder.encode("fname", "UTF-8")
                + "=" + URLEncoder.encode(FName, "UTF-8");

        data += "&" + URLEncoder.encode("name", "UTF-8") + "="
                + URLEncoder.encode(Name, "UTF-8");

        data += "&" + URLEncoder.encode("email", "UTF-8") + "="
                + URLEncoder.encode(Email, "UTF-8");

        data += "&" + URLEncoder.encode("dnr", "UTF-8")
                + "=" + URLEncoder.encode(Dnr, "UTF-8");

        data += "&" + URLEncoder.encode("pass", "UTF-8")
                + "=" + URLEncoder.encode(Pass, "UTF-8");

        String text = "";
        BufferedReader reader = null;

        // Send data
        try {

            // Defined URL  where to send data
            URL url = new URL("http://10.0.2.2/httppost.php");

            // Send POST data request

            URLConnection conn = url.openConnection();
            conn.setDoOutput(true);
            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
            wr.write(data);
            wr.flush();

            // Get the server response

            reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line = null;

            // Read Server Response
            while ((line = reader.readLine()) != null) {
                // Append server response in string
                sb.append(line + "\n");
            }


            text = sb.toString();
        } catch (Exception ex) {

        } finally
        {
            try {

                reader.close();
            } catch (Exception ex) {
            }
        }

        // Show response on activity
        errormsgtxt.setText(text);
    }


    // SignIn --------------

    public void CheckSignIn() {

        //TODO add validations

        dnr = logindnr.getText().toString().trim();
        password = loginpassword.getText().toString().trim();

        String username = "testuser";
        //String password = "testpass";


        new PostAsync().execute(username, password);

        /*
        // Check for empty data in the form
        if (!dnr.isEmpty() && !password.isEmpty()) {

            // Connect with MlS SERVER
            // login user

        } else {
            // Prompt user to enter credentials
            Toast.makeText(getApplicationContext(),
                    "Please enter the credentials!", Toast.LENGTH_LONG)
                    .show();
            errormsgtxt.setText("Please enter the credentials!");

            // Return wrong/error login from Server
        }
*/

    }

    public void SignIn() {

        Intent i = new Intent(getApplicationContext(),
                InfoActivity.class);
        startActivity(i);
        finish();

    }

    public void FailureSignIn() {

        errormsgtxt.setText("Please enter correct credentials!");
    }

    // Validate SignIn Form

    private void submitForm() {
        /*
        if (!validateFamilyname()) {
            return;
        }

        if (!validateName()) {
            return;
        }

        if (!validateDnr()) {
            return;
        }
*//*
        if (!validateEmail()) {
            return;
        }

        if (!validatePassword()) {
            return;
        }
*/
        //Toast.makeText(getApplicationContext(), "Validated", Toast.LENGTH_SHORT).show();
        //TODO send to server and login
    }

    public void signincolor() {
        if (!logindnr.getText().toString().trim().isEmpty() && !loginpassword.getText().toString().trim().isEmpty()) {
            signinbtn.setBackgroundResource(R.drawable.button_green_pressed);
            signinbtn.setEnabled(true);
        } else {
            signinbtn.setBackgroundResource(R.drawable.button_red_pressed);
            signinbtn.setEnabled(false);
        }
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    private class LogInTextWatcher implements TextWatcher {

        private View view;

        private LogInTextWatcher(View view) {
            this.view = view;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void afterTextChanged(Editable editable) {

            signincolor();

            switch (view.getId()) {
                case R.id.logindnr:
                    validateDnr();
                    break;
                /*case R.id.input_email:
                    validateEmail();
                    break;*/
                case R.id.loginpassword:
                    validatePassword();
                    break;
            }
        }
    }

    private class RegisterEntryWatcher implements TextWatcher {

        private View view;

        private RegisterEntryWatcher(View view) {
            this.view = view;
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {

            switch (view.getId()) {
                // registerfields
                case R.id.registerfamilyname:
                    //validateFamilyname();
                    break;

                case R.id.registername:
                    break;

                case R.id.registerdnr:
                    validateDnr();
                    break;

                case R.id.registeremail:
                    //validateEmail();
                    break;

                case R.id.registerpassword:
                    validatePassword();
                    break;
            }

            AlertDialog.Builder dlgBuilder = new AlertDialog.Builder(LoginActivity.this);
            AlertDialog alert = dlgBuilder.create();
            alert.show();

            if (registerpassword.getText().toString().isEmpty()) {

                alert.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);
            } else {
                alert.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(true);
            }
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            AlertDialog.Builder dlgBuilder = new AlertDialog.Builder(LoginActivity.this);
            AlertDialog alert = dlgBuilder.create();
            alert.show();

            if (registerpassword.getText().toString().isEmpty()) {

                alert.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);
            } else {
                alert.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(true);
            }
        }

    }

    // Validations //

    public void validateFamilyname() {

        if (registerfamilyname.getText().toString().trim().isEmpty()) {
            registerfamilyname.setError("Invalid Input");
            requestFocus(registerfamilyname);
        }
    }

    public void validateName() {

        if (registername.getText().toString().trim().isEmpty()) {
            registername.setError("Invalid Input");
            requestFocus(registername);
        }
    }

    private boolean validateDnr() {
        if (logindnr.getText().toString().trim().isEmpty()) {
            inputLayoutDnr.setError(getString(R.string.err_msg_dnr));
            requestFocus(logindnr);
            return false;
        } else {
            inputLayoutDnr.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateEmail() {

        if (email.isEmpty() || !ValidateInput.isValidEmail(email)) {
            inputLayoutEmail.setError(getString(R.string.err_msg_email));
            requestFocus(registeremail);
            return false;
        } else {
            inputLayoutEmail.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validatePassword() {
        if (loginpassword.getText().toString().trim().isEmpty()) {
            inputLayoutPassword.setError(getString(R.string.err_msg_password));
            requestFocus(loginpassword);
            return false;
        } else {
            inputLayoutPassword.setErrorEnabled(false);
        }

        return true;
    }

    public void setRegisterAllErrorEnabled() {

        TextInputLayout registerfamilynameLayout = (TextInputLayout) registeruserlayout.findViewById(R.id.registerfamilynameLayout);
        TextInputLayout registernameLayout = (TextInputLayout) registeruserlayout.findViewById(R.id.registernameLayout);
        TextInputLayout registerdnrLayout = (TextInputLayout) registeruserlayout.findViewById(R.id.registerdnrLayout);
        TextInputLayout registeremailLayout = (TextInputLayout) registeruserlayout.findViewById(R.id.registeremailLayout);
        TextInputLayout registerpasswordLayout = (TextInputLayout) registeruserlayout.findViewById(R.id.registerpasswordLayout);

        registerfamilynameLayout.setErrorEnabled(true);
        registernameLayout.setErrorEnabled(true);
        registerdnrLayout.setErrorEnabled(true);
        registeremailLayout.setErrorEnabled(true);
        registerpasswordLayout.setErrorEnabled(true);
/*
        registerfamilynameLayout.setError("Required");
        registernameLayout.setError("Required");
        registerdnrLayout.setError("Required");
        registeremailLayout.setError("Required");
        registerpasswordLayout.setError("Required");
        */

        EditText famname = (EditText) registeruserlayout.findViewById(R.id.registerfamilyname);
        famname.setError("Required");

        EditText name = (EditText) registeruserlayout.findViewById(R.id.registername);
        name.setError("Required");

        EditText dnr = (EditText) registeruserlayout.findViewById(R.id.registerdnr);
        dnr.setError("Required");

        EditText email = (EditText) registeruserlayout.findViewById(R.id.registeremail);
        email.setError("Required");

        EditText pwd = (EditText) registeruserlayout.findViewById(R.id.registerpassword);
        pwd.setError("Required");
    }

    // Link to Register Screen ------------------------------------------
    // Dialog for user registering

    public void gotoRegisterScreen() {

        //Dialog
        final AlertDialog.Builder dlgBuilder = new AlertDialog.Builder(LoginActivity.this);

        //Title
        dlgBuilder.setTitle("Register User");

        dlgBuilder.setView(registeruserlayout);

        //Set Required Icon
        //setRegisterAllErrorEnabled();

        //Buttons
        dlgBuilder.setPositiveButton("Register", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                familyname = registerfamilyname.getText().toString().trim();
                name = registername.getText().toString().trim();
                dnr = registerdnr.getText().toString().trim();
                email = registeremail.getText().toString().trim();
                password = registerpassword.getText().toString().trim();

                //RegisterUserMLS();
                try {
                    sendRegisterData();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                    errormsgtxt.setText(" url exeption! ");
                }

                //remove layout
                View viewToRemove = registeruserlayout;
                if (viewToRemove != null && viewToRemove.getParent() != null && viewToRemove instanceof ViewGroup)
                    ((ViewGroup) viewToRemove.getParent()).removeView(viewToRemove);
            }

        });

        dlgBuilder.setNegativeButton("Abbrechen", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        //remove layout
                        View viewToRemove = registeruserlayout;
                        if (viewToRemove != null && viewToRemove.getParent() != null && viewToRemove instanceof ViewGroup)
                            ((ViewGroup) viewToRemove.getParent()).removeView(viewToRemove);

                        //finish();

                    }
                }
        );

        AlertDialog alert = dlgBuilder.create();
        alert.show();
    }

    //// REGISTER CLASS HERE ////
    // TODO: Register in App(those who aren´t from beginning(over MLS database)) and GCM

    public void RegisterUserMLS() {

        if (!familyname.isEmpty() && !name.isEmpty() && !dnr.isEmpty() && !email.isEmpty() && !password.isEmpty()) {

            //register user

            //TODO register in mls postgres db (alter existing tables)

        } else {

            Toast.makeText(getApplicationContext(),
                    "Please enter your details!", Toast.LENGTH_SHORT)
                    .show();

            errormsgtxt.setText("No credentials entered!");

            Handler h = new Handler();
            h.postDelayed(new Runnable() {
                @Override
                public void run() {
                    errormsgtxt.setText("");
                }
            }, 5000);
        }
    }
}
