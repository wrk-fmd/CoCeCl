package it.fmd.cocecl;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;

import it.fmd.cocecl.utilclass.CheckPlayServices;
import it.fmd.cocecl.utilclass.JSONParser;
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

    //GCM
    ProgressDialog prgDialog;
    RequestParams params = new RequestParams();
    GoogleCloudMessaging gcmObj;
    Context applicationContext;
    String regId = "";

    CheckPlayServices cps = new CheckPlayServices();


    // OnCreate Method // -------------------------------------- //
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

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
                SignIn();
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
        applicationContext = getApplicationContext();

        prgDialog = new ProgressDialog(this);
        // Set Progress Dialog Text
        prgDialog.setMessage("Please wait...");
        // Set Cancelable as False
        prgDialog.setCancelable(false);

        SharedPreferences prefs = getSharedPreferences("UserDetails",
                Context.MODE_PRIVATE);
        String registrationId = prefs.getString(REG_ID, "");

/* User still needs to Login
        //When Email ID is set in Sharedpref, User will be taken to HomeActivity
        if (!TextUtils.isEmpty(registrationId)) {
            Intent i = new Intent(applicationContext, InfoActivity.class);
            i.putExtra("regId", registrationId);
            startActivity(i);
            finish();
        }
*/
        //Floating Labels//
        inputLayoutDnr = (TextInputLayout) findViewById(R.id.input_layout_dnr);
        inputLayoutPassword = (TextInputLayout) findViewById(R.id.input_layout_password);
        logindnr = (EditText) findViewById(R.id.logindnr);
        loginpassword = (EditText) findViewById(R.id.loginpassword);

        //final LinearLayout registeruserlayout = (LinearLayout) getLayoutInflater().inflate(R.layout.register_user_layout, null);

        //Displaying TextInputLayout Error
        TextInputLayout registernameLayout = (TextInputLayout) registeruserlayout.findViewById(R.id.registernameLayout);
        registernameLayout.setErrorEnabled(true);
        registernameLayout.setError("Min 2 chars required");

        //Displaying EditText Error
        EditText name = (EditText) registeruserlayout.findViewById(R.id.registername);
        name.setError("Required");

        //Displaying both TextInputLayout and EditText Errors
        TextInputLayout emailLayout = (TextInputLayout) registeruserlayout.findViewById(R.id.registeremailLayout);
        emailLayout.setErrorEnabled(true);
        emailLayout.setError("Please enter EMailAddress");

        EditText email = (EditText) registeruserlayout.findViewById(R.id.registeremail);
        email.setError("Required");

        // LogIn to Server//
        //POST and GET
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
                    Log.d("Success!", message);
                } else {
                    Log.d("Failure", message);
                }
            }

        }


        class GetAsync extends AsyncTask<String, String, JSONObject> {

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
                            APPConstants.URL_LOGIN, "GET", params);

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
                    Log.d("Success!", message);
                } else {
                    Log.d("Failure", message);
                }
            }

        }

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

        // Register TextChangeListener
        registerfamilyname.addTextChangedListener(new RegisterEntryWatcher(registerfamilyname));

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
        //checkMLSConnection();
    }

    @Override
    public void onPause() {
        super.onPause();

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

    // SignIn --------------

    public void SignIn() {

        submitForm();
        /*
        String dnr = logindnr.getText().toString().trim();
        String password = loginpassword.getText().toString().trim();

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

    // Validate SignIn Form

    private void submitForm() {
        if (!validateDnr()) {
            return;
        }
/*
        if (!validateEmail()) {
            return;
        }
*/
        if (!validatePassword()) {
            return;
        }

        //Toast.makeText(getApplicationContext(), "Validated", Toast.LENGTH_SHORT).show();
        //TODO send to server and login
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

    /*
        private boolean validateEmail() {

            String email = registeremail.getText().toString().trim();

            if (email.isEmpty() || !ValidateInput.isValidEmail(email)) {
                inputLayoutEmail.setError(getString(R.string.err_msg_email));
                requestFocus(registeremail);
                return false;
            } else {
                inputLayoutEmail.setErrorEnabled(false);
            }

            return true;
        }
    */
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
/*
                // registerfields
                case R.id.registerfamilyname:
                    validatePassword();
                    break;

                case R.id.registername:
                    validatePassword();
                    break;

                case R.id.registerdnr:
                    validatePassword();
                    break;

                case R.id.registeremail:
                    validatePassword();
                    break;

                case R.id.registerpassword:
                    validatePassword();
                    break;
                    */
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

            switch (view.getId()) {
                // registerfields
                case R.id.registerfamilyname:
                    validateFamilyname();
                    break;

                case R.id.registername:
                    break;

                case R.id.registerdnr:
                    validateDnr();
                    break;

                case R.id.registeremail:
                    validateEmail();
                    break;

                case R.id.registerpassword:
                    validatePassword();
                    break;
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

            final LinearLayout registeruserlayout = (LinearLayout) getLayoutInflater().inflate(R.layout.register_user_layout, null);
            final EditText registerpassword = (EditText) registeruserlayout.findViewById(R.id.registerpassword);

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
        }

    }

    private boolean validateEmail() {
/*
        final LinearLayout registeruserlayout = (LinearLayout) getLayoutInflater().inflate(R.layout.register_user_layout, null);
        final EditText registeremail = (EditText) registeruserlayout.findViewById(R.id.registeremail);

        String email = registeremail.getText().toString().trim();
*/
        if (email.isEmpty() || !ValidateInput.isValidEmail(email)) {
            inputLayoutEmail.setError(getString(R.string.err_msg_email));
            requestFocus(registeremail);
            return false;
        } else {
            inputLayoutEmail.setErrorEnabled(false);
        }

        return true;
    }

    public void validateFamilyname() {
/*
        final LinearLayout registeruserlayout = (LinearLayout) getLayoutInflater().inflate(R.layout.register_user_layout, null);
        final EditText registerfamilyname = (EditText) registeruserlayout.findViewById(R.id.registerfamilyname);
*/
        if (registerfamilyname.getText().toString().trim().isEmpty()) {
            registerfamilyname.setError("Invalid Input");
            requestFocus(registerfamilyname);
        }
    }
    // Link to Register Screen ------------------------------------------
    // Dialog for user registering

    public void gotoRegisterScreen() {

        familyname = registerfamilyname.getText().toString().trim();
        name = registername.getText().toString().trim();
        dnr = registerdnr.getText().toString().trim();
        email = registeremail.getText().toString().trim();
        password = registerpassword.getText().toString().trim();

        LayoutInflater factory = LayoutInflater.from(this);
        final View f = factory.inflate(R.layout.register_user_layout, null);

        //Dialog
        AlertDialog.Builder dlgBuilder = new AlertDialog.Builder(LoginActivity.this);

        //Title
        dlgBuilder.setTitle("Register User");

        //View registeruserlayout = getLayoutInflater().inflate(R.layout.register_user_layout, null);

        //dlgBuilder.setView(registeruserlayout);
        dlgBuilder.setView(f);

        //Buttons
        dlgBuilder.setPositiveButton("Register", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                RegisterUserGCM();
                RegisterUserMLS();
/*
                //remove layout
                View viewToRemove = findViewById(R.id.registeruserrelaout);
                if (viewToRemove != null && viewToRemove.getParent() != null && viewToRemove instanceof ViewGroup)
                    ((ViewGroup) viewToRemove.getParent()).removeView(viewToRemove);
                    */
            }

        });

        dlgBuilder.setNegativeButton("Abbrechen", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
/*
                        //remove layout
                        View viewToRemove = findViewById(R.id.registeruserrelaout);
                        if (viewToRemove != null && viewToRemove.getParent() != null && viewToRemove instanceof ViewGroup)
                            ((ViewGroup) viewToRemove.getParent()).removeView(viewToRemove);
*/
/*
                Intent i = new Intent(getApplicationContext(),
                        InfoActivity.class);
                startActivity(i);
                finish();
*/
                    }
                }
        );

        AlertDialog alert = dlgBuilder.create();
        alert.show();
    }

    //TODO: remove when AppLogin finished
    public void bypasslogin(View v) {
        if (v.getId() == R.id.button22) {
            Intent i = new Intent(getApplicationContext(),
                    InfoActivity.class);
            startActivity(i);
            finish();
        }
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


    // GCM Login/Registration //
    // --------------------------------------------------------------- //
    //
    //
    //
    //TODO: unregister on logout

    AsyncTask<Void, Void, String> createRegIdTask;

    public static final String REG_ID = "regId";
    public static final String EMAIL_ID = "eMailId";

    // When Register Me button is clicked
    //public void RegisterUser(View view) {
    public void RegisterUserGCM() {

        if (!TextUtils.isEmpty(email) && ValidateInput.isValidEmail(email)) {

            // Check if Google Play Service is installed in Device
            // Play services is needed to handle GCM stuffs
            if (cps.checkPlayServices(this)) {

                // Register Device in GCM Server
                registerInBackground(email);
            }
        }
        // When Email is invalid
        else {
            Toast.makeText(applicationContext, "Please enter valid email",
                    Toast.LENGTH_LONG).show();
        }

    }

    // AsyncTask to register Device in GCM Server
    private void registerInBackground(final String email) {
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... params) {
                String msg = "";
                try {
                    if (gcmObj == null) {
                        gcmObj = GoogleCloudMessaging
                                .getInstance(applicationContext);
                    }
                    regId = gcmObj
                            .register(APPConstants.GOOGLE_PROJ_ID);
                    msg = "Registration ID :" + regId;

                } catch (IOException ex) {
                    msg = "Error :" + ex.getMessage();
                }
                return msg;
            }

            @Override
            protected void onPostExecute(String msg) {
                if (!TextUtils.isEmpty(regId)) {
                    // Store RegId created by GCM Server in SharedPref
                    storeRegIdinSharedPref(applicationContext, regId, email);

                    //Toast.makeText(applicationContext, "Registered with GCM Server successfully.nn" + msg, Toast.LENGTH_SHORT).show();

                } else {
                    /*
                    Toast.makeText(
                            applicationContext,
                            "Reg ID Creation Failed.nnEither you haven't enabled Internet or GCM server is busy right now. Make sure you enabled Internet and try registering again after some time."
                                    + msg, Toast.LENGTH_LONG).show();
                                    */

                    errormsgtxt.setText(msg);
                    Handler h = new Handler();
                    h.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            errormsgtxt.setText("");
                        }
                    }, 5000);
                }
            }
        }.execute(null, null, null);
    }

    // Store  RegId and Email entered by User in SharedPref
    private void storeRegIdinSharedPref(Context context, String regId,
                                        String email) {
        SharedPreferences prefs = getSharedPreferences("UserDetails",
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(REG_ID, regId);
        editor.putString(EMAIL_ID, email);
        editor.commit();
        storeRegIdinServer();

    }

    // Share RegID with GCM Server Application (Php)
    private void storeRegIdinServer() {
        prgDialog.show();
        params.put("regId", regId);
        // Make RESTful webservice call using AsyncHttpClient object
        AsyncHttpClient client = new AsyncHttpClient();
        client.post(APPConstants.APP_SERVER_URL, params,
                new AsyncHttpResponseHandler() {
                    // When the response returned by REST has Http
                    // response code '200'
                    public void onSuccess(String response) {
                        // Hide Progress Dialog
                        prgDialog.hide();
                        if (prgDialog != null) {
                            prgDialog.dismiss();
                        }

                        /*
                        Toast.makeText(applicationContext, "Reg Id shared successfully with Web App ", Toast.LENGTH_LONG).show();
                        Intent i = new Intent(applicationContext, MainActivity.class);
                        i.putExtra("regId", regId);
                        startActivity(i);
                        finish();
                        */
                    }

                    //TODO: log errors
                    // When the response returned by REST has Http
                    // response code other than '200' such as '404',
                    // '500' or '403' etc
                    public void onFailure(int statusCode, Throwable error,
                                          String content) {
                        // Hide Progress Dialog
                        prgDialog.hide();
                        if (prgDialog != null) {
                            prgDialog.dismiss();
                        }
                        // When Http response code is '404'
                        if (statusCode == 404) {
                            /*
                            Toast.makeText(applicationContext,
                                    "Requested resource not found",
                                    Toast.LENGTH_LONG).show();
                                    */
                            errormsgtxt.setText("404");
                        }
                        // When Http response code is '500'
                        else if (statusCode == 500) {
                            /*
                            Toast.makeText(applicationContext,
                                    "Something went wrong at server end",
                                    Toast.LENGTH_LONG).show();
                                    */
                            errormsgtxt.setText("500");
                        }
                        // When Http response code other than 404, 500
                        else {

                            /*
                            Toast.makeText(
                                    applicationContext,
                                    "Unexpected Error occcured! [Most common Error: Device might "
                                            + "not be connected to Internet or remote server is not up and running], check for other errors as well",
                                    Toast.LENGTH_LONG).show();
                                    */
                            errormsgtxt.setText("Unexpected error");
                        }
                    }
                });
    }
}
