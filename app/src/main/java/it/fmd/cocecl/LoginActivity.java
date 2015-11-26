package it.fmd.cocecl;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.AlertDialog;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;

import it.fmd.cocecl.gcm.Utility;
import it.fmd.cocecl.utilclass.JSONParser;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class LoginActivity extends MainActivity {

    // Login Layout
    private Button signinbtn;
    private Button gotoregisterbtn;
    private EditText logindnr;
    private EditText loginpassword;

    private TextView errormsgtxt;

    // RegisterLayout
    private EditText registername;
    private EditText registerdnr;
    private EditText registeremail;
    private EditText registerpassword;
    private Button btnRegister;

    // Floating Labels
    private Toolbar toolbar;
    private TextInputLayout inputLayoutDnr, inputLayoutEmail, inputLayoutPassword;

    //GCM
    ProgressDialog prgDialog;
    RequestParams params = new RequestParams();
    GoogleCloudMessaging gcmObj;
    Context applicationContext;
    String regId = "";



    // OnCreate Method // -------------------------------------- //
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //GCM//

            applicationContext = getApplicationContext();
            emailET = (EditText) findViewById(R.id.registeremail);

            prgDialog = new ProgressDialog(this);
            // Set Progress Dialog Text
            prgDialog.setMessage("Please wait...");
            // Set Cancelable as False
            prgDialog.setCancelable(false);

            SharedPreferences prefs = getSharedPreferences("UserDetails",
                    Context.MODE_PRIVATE);
            String registrationId = prefs.getString(REG_ID, "");

            //When Email ID is set in Sharedpref, User will be taken to HomeActivity
            if (!TextUtils.isEmpty(registrationId)) {
                Intent i = new Intent(applicationContext, InfoActivity.class);
                i.putExtra("regId", registrationId);
                startActivity(i);
                finish();
            }

        //Floating Labels//
        inputLayoutDnr = (TextInputLayout) findViewById(R.id.input_layout_dnr);
        inputLayoutPassword = (TextInputLayout) findViewById(R.id.input_layout_password);
        logindnr = (EditText) findViewById(R.id.logindnr);
        loginpassword = (EditText) findViewById(R.id.loginpassword);
/*
        inputDNr.addTextChangedListener(new MyTextWatcher(inputDNr));
        inputEmail.addTextChangedListener(new MyTextWatcher(inputEmail));
        inputPassword.addTextChangedListener(new MyTextWatcher(inputPassword));
*/
/*
        // GET config file from server //
        // load file
        //TODO: get file from server

        // either from assets or raw
        Resources resources = this.getResources();
        AssetManager assetManager = resources.getAssets();

        // Read from the /assets directory
        try {
            InputStream inputStream = assetManager.open("cocecl.properties");
            Properties properties = new Properties();
            properties.load(inputStream);
            System.out.println("The properties are now loaded");
            System.out.println("properties: " + properties);
        } catch (IOException e) {
            System.err.println("Failed to open property file");
            e.printStackTrace();
        }
        // Read from the /res/raw directory
        try {
            InputStream rawResource = resources.openRawResource(R.raw.cocecl_config);
            Properties properties = new Properties();
            properties.load(rawResource);
            System.out.println("The properties are now loaded");
            System.out.println("properties: " + properties);
        } catch (Resources.NotFoundException e) {
            System.err.println("Did not find raw resource: "+e);
        } catch (IOException e) {
            System.err.println("Failed to open property file");
        }
        // write properties from file to setting
        //TODO: config
*/

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
                }else{
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
                }else{
                    Log.d("Failure", message);
                }
            }

        }

        //inputEmail = (EditText) findViewById(R.id.loginemail);
        logindnr = (EditText) findViewById(R.id.logindnr);
        loginpassword = (EditText) findViewById(R.id.loginpassword);
        signinbtn = (Button) findViewById(R.id.signinbtn);
        gotoregisterbtn = (Button) findViewById(R.id.gotoregisterbtn);

        errormsgtxt = (TextView)findViewById(R.id.textView94);

        // Login button Click Event
        signinbtn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                String dnr = registerdnr.getText().toString().trim();
                String password = registerpassword.getText().toString().trim();

                // Check for empty data in the form
                if (!dnr.isEmpty() && !password.isEmpty()) {
                    // login user

                } else {
                    // Prompt user to enter credentials
                    Toast.makeText(getApplicationContext(),
                            "Please enter the credentials!", Toast.LENGTH_LONG)
                            .show();
                    errormsgtxt.setText("Please enter the credentials!");
                }
            }

        });

        // Link to Register Screen
        // Dialog for user registering
        gotoregisterbtn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                final LinearLayout registeruserlayout = (LinearLayout)getLayoutInflater().inflate(R.layout.register_user_layout, null);
                AlertDialog.Builder dlgBuilder = new AlertDialog.Builder(LoginActivity.this);
                dlgBuilder.setTitle("Register User");

                registername = (EditText) registeruserlayout.findViewById(R.id.registername);
                registeremail = (EditText) registeruserlayout.findViewById(R.id.registeremail);
                registerpassword = (EditText) registeruserlayout.findViewById(R.id.loginpassword);
                btnRegister = (Button) registeruserlayout.findViewById(R.id.btnRegister);
                //btnLinkToLogin = (Button) registeruserlayout.findViewById(R.id.btnLinkToLoginScreen);
/*
                dlgBuilder.setView(registeruserlayout).setPositiveButton("Senden", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        //remove layout
                        View viewToRemove = findViewById(R.id.registeruserrelaout);
                        if (viewToRemove != null && viewToRemove.getParent() != null && viewToRemove instanceof ViewGroup)
                            ((ViewGroup) viewToRemove.getParent()).removeView(viewToRemove);
                    }

                });
*/
                    dlgBuilder.setView(registeruserlayout).setNegativeButton("Abbrechen", new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                    //remove layout
                                    View viewToRemove = findViewById(R.id.registeruserrelaout);
                                    if (viewToRemove != null && viewToRemove.getParent() != null && viewToRemove instanceof ViewGroup)
                                        ((ViewGroup) viewToRemove.getParent()).removeView(viewToRemove);

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
        });
    }

    @Override
    public void onStart(){
        super.onStart();
        //checkMLSConnection();
    }
    @Override
    public void onResume() {
        super.onResume();
        checkPlayServices();
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
/*
    private class MyTextWatcher implements TextWatcher {

        private View view;

        private MyTextWatcher(View view) {
            this.view = view;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void afterTextChanged(Editable editable) {
            switch (view.getId()) {
                case R.id.dnrlogin:
                    validateName();
                    break;
                case R.id.password:
                    validatePassword();
                    break;
            }
        }
    }
*/
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


    public void register(View v) {
        if (v.getId() == R.id.btnRegister) {
            String name = registername.getText().toString().trim();
            String email = registeremail.getText().toString().trim();
            String password = registerpassword.getText().toString().trim();

            if (!name.isEmpty() && /*!dnr.isEmpty() && */!email.isEmpty() && !password.isEmpty()) {
                //register user
            } else {
                Toast.makeText(getApplicationContext(),
                        "Please enter your details!", Toast.LENGTH_LONG)
                        .show();
            }
        }
    }


    // GCM Login/Registration //
    // --------------------------------------------------------------- //
    //
    //
    //
    //TODO: unregister on logout

    private final static int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;

    AsyncTask<Void, Void, String> createRegIdTask;

    public static final String REG_ID = "regId";
    public static final String EMAIL_ID = "eMailId";
    EditText emailET;

    // When Register Me button is clicked
    public void RegisterUser(View view) {
        String emailID = emailET.getText().toString();

        if (!TextUtils.isEmpty(emailID) && Utility.validate(emailID)) {

            // Check if Google Play Service is installed in Device
            // Play services is needed to handle GCM stuffs
            if (checkPlayServices()) {

                // Register Device in GCM Server
                registerInBackground(emailID);
            }
        }
        // When Email is invalid
        else {
            Toast.makeText(applicationContext, "Please enter valid email",
                    Toast.LENGTH_LONG).show();
        }

    }

    // AsyncTask to register Device in GCM Server
    private void registerInBackground(final String emailID) {
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
                    storeRegIdinSharedPref(applicationContext, regId, emailID);

                    //Toast.makeText(applicationContext, "Registered with GCM Server successfully.nn" + msg, Toast.LENGTH_SHORT).show();

                } else {
                    /*
                    Toast.makeText(
                            applicationContext,
                            "Reg ID Creation Failed.nnEither you haven't enabled Internet or GCM server is busy right now. Make sure you enabled Internet and try registering again after some time."
                                    + msg, Toast.LENGTH_LONG).show();
                                    */
                }
            }
        }.execute(null, null, null);
    }

    // Store  RegId and Email entered by User in SharedPref
    private void storeRegIdinSharedPref(Context context, String regId,
                                        String emailID) {
        SharedPreferences prefs = getSharedPreferences("UserDetails",
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(REG_ID, regId);
        editor.putString(EMAIL_ID, emailID);
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
                        }
                        // When Http response code is '500'
                        else if (statusCode == 500) {
                            /*
                            Toast.makeText(applicationContext,
                                    "Something went wrong at server end",
                                    Toast.LENGTH_LONG).show();
                                    */
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
                        }
                    }
                });
    }

    // Check if Google Playservices is installed in Device or not
    private boolean checkPlayServices() {
        int resultCode = GooglePlayServicesUtil
                .isGooglePlayServicesAvailable(this);
        // When Play services not found in device
        if (resultCode != ConnectionResult.SUCCESS) {
            if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
                // Show Error dialog to install Play services
                GooglePlayServicesUtil.getErrorDialog(resultCode, this,
                        PLAY_SERVICES_RESOLUTION_REQUEST).show();
            } else {
                Toast.makeText(
                        applicationContext,
                        "This device doesn't support Play services, App will not work normally",
                        Toast.LENGTH_LONG).show();
                finish();
            }
            return false;
        } else {
            /*
            Toast.makeText(
                    applicationContext,
                    "This device supports Play services, App will work normally",
                    Toast.LENGTH_SHORT).show();
                    */
        }
        return true;
    }
}
