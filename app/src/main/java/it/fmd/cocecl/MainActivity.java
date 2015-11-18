package it.fmd.cocecl;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import it.fmd.cocecl.fragments.communicationFragment;
import it.fmd.cocecl.fragments.deliverylocFragment;
import it.fmd.cocecl.fragments.incidentFragment;
import it.fmd.cocecl.fragments.mainstatusFragment;
import it.fmd.cocecl.fragments.mapFragment;
import it.fmd.cocecl.utilclass.ConnectionManager;
import it.fmd.cocecl.utilclass.GPSManager;
import it.fmd.cocecl.utilclass.GeoWebViewActivity;
import it.fmd.cocecl.utilclass.JSONParser;

import static android.graphics.Color.BLUE;
import static android.graphics.Color.GREEN;
import static android.graphics.Color.RED;
import static android.graphics.Color.YELLOW;


public class MainActivity extends AppCompatActivity {

    ConnectionManager conman = new ConnectionManager();

    TextView SMSm;
    static String phoneNumber1;
    static String SMSBody1;

    public static void getSmsDetails(String phoneNumber, String SMSBody) {
        phoneNumber1 = phoneNumber;
        SMSBody1 = SMSBody;
    }

    // Google Map
    private GoogleMap googleMap;

    private FragmentTabHost mTabHost;

    private CoordinatorLayout coordinatorLayout;

    // Location
    public static Location loc;
    private static double longitude;
    private static double latitude;
    private static String lngString = String.valueOf(longitude);
    private static String latString = String.valueOf(latitude);

    // JSON TODO: remove after test
    public static String TAG = MainActivity.class.getSimpleName();
    private Button btnMakeObjectRequest, btnMakeArrayRequest;
    // Progress dialog
    private ProgressDialog pDialog;
    private TextView txtResponse;
    // temporary string to show the parsed response
    private String jsonResponse;



    //Shared Preferences
    SharedPreferences spref;
    String patfirstname,patlastname,patdatebirth,patsvnr,patplsnr,patgender,patward;
    String getpatfirstname,getpatlastname,getpatdatebirth,getpatsvnr,getpatplsnr,getpatgender,getpatward;


    // OnCreate Method // ------------------------------------- //
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    {
        //Shared Prefs// Create Patient
        spref = getSharedPreferences("PatData", MODE_PRIVATE);

    }

        {
            //Coordinator Layout for SnackBar//
            coordinatorLayout = (CoordinatorLayout) findViewById(R.id
                    .coordinatorLayout);

            Snackbar snackbar = Snackbar
                    .make(coordinatorLayout, "Welcome to CoCeCl", Snackbar.LENGTH_LONG);

            snackbar.show();

            // No Inet connection - show in snackbar
            Snackbar.make(coordinatorLayout, "No internet connection!", Snackbar.LENGTH_LONG)
                    .setAction("RETRY", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                        }
                    });

            // Changing message text color
            snackbar.setActionTextColor(Color.RED);

            // Changing action button text color
            View sbView = snackbar.getView();
            TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
            textView.setTextColor(Color.YELLOW);

            snackbar.show();
        }

        {
            Toolbar toolbar = (Toolbar) findViewById(R.id.appbar);
            setSupportActionBar(toolbar);
            //setTitle(R.string.app_name);
        }

        // GPS Coordinates // send continuous updates //

        //new

        GPSManager gps = new GPSManager(MainActivity.this);
        double latitude = gps.getLatitude();
        double longitude = gps.getLongitude();

        Handler h = new Handler();
        h.postDelayed(new

                              Runnable() {
                                  @Override
                                  public void run() {
                                      //TODO: send coordinates every 10 sec
                                  }
                              }

                , 10000);

            // FRAGMENT MANAGER //
        {
            //TODO: Fragmentmanager does not crash anymore; tablet mode still does not work
            if ((getResources().getConfiguration().screenLayout &
                    Configuration.SCREENLAYOUT_SIZE_MASK) ==
                    Configuration.SCREENLAYOUT_SIZE_LARGE) {
                // on a large screen device ...
/*
                Fragment mainstatusfrag = new mainstatusFragment();
                Fragment incidentFrag = new incidentFragment();
                Fragment fielddataFrag = new fielddataFragment();
                Fragment mapFrag = new mapFragment();
*/
                FragmentManager fm = getSupportFragmentManager();

                //getSupportFragmentManager().findFragmentById(R.id.fragment_status);


                // Transaction start
                FragmentTransaction ft = fm.beginTransaction();

                ft.add(R.id.framelayout_1, new mainstatusFragment());

                if (findViewById(R.id.framelayout_2) != null) {

                    ft.add(R.id.framelayout_2, new incidentFragment());
                }

                if (findViewById(R.id.framelayout_3) != null) {

                    ft.add(R.id.framelayout_3, new mapFragment());
                }

                ft.addToBackStack(null);
                // Transaction commit
                ft.commit();
            }
        }

        // TABHOST CONTROLLER //

        if ((getResources().getConfiguration().screenLayout &
                Configuration.SCREENLAYOUT_SIZE_MASK) ==
                Configuration.SCREENLAYOUT_SIZE_NORMAL) {
            // on a normal screen device ...

            mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
            mTabHost.setup(this, getSupportFragmentManager(), android.R.id.tabcontent);

            // TABS
            //TODO: set Tabs depending on unit status
            mTabHost.addTab(
                    mTabHost.newTabSpec("tab1").setIndicator("Status", null),
                    mainstatusFragment.class, null);
            mTabHost.addTab(
                    mTabHost.newTabSpec("tab2").setIndicator("Einsatzdaten", null),
                    incidentFragment.class, null); /*
            mTabHost.addTab(
                    mTabHost.newTabSpec("tab1").setIndicator("Status", null),
                    statusFragment.class, null);
            mTabHost.addTab(
                    mTabHost.newTabSpec("tab2").setIndicator("Einsatzdaten", null),
                    fielddataFragment.class, null); */
            mTabHost.addTab(
                    mTabHost.newTabSpec("tab3").setIndicator("Abgabeort", null),
                    deliverylocFragment.class, null);
            mTabHost.addTab(
                    mTabHost.newTabSpec("tab4").setIndicator("Karte", null),
                    mapFragment.class, null);
            mTabHost.addTab(
                    mTabHost.newTabSpec("tab5").setIndicator("Komm", null),
                    communicationFragment.class, null);
        }
    }



    // TabHost Methods //
    private void addMethod() {

        //mTabHost.addTab(mtabHost.newTabSpec(webSiteName + Integer.toString(z)).setIndicator(webSiteName).setContent(openBrowser));

    }

    private void removeMethod() {

        // method 1
        mTabHost.getTabWidget().removeView(mTabHost.getTabWidget().getChildTabViewAt(1));
        //or
        mTabHost.removeAllViews();
        // method 2
        int position = mTabHost.getCurrentTab();

        if (position != 0 ) {

            mTabHost.getCurrentTabView().setVisibility(View.GONE);
            mTabHost.setCurrentTab(0);
        }
    }
    /*
    public void clearAllTabs() {
        mTabWidget.removeAllViews();
        initTabHost();
        mTabContent.removeAllViews();
        mTabSpecs.clear();
        requestLayout();
        invalidate();
    }
*/

    // OPTIONS MENU //

    // Menu on the right of ActionBar/TitleBar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    // Item onClick
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //Animated sync symbol in toolbar//
    //rotate imageview animation

    public void onSyncIconStart() {
        ImageView syncicon = (ImageView) findViewById(R.id.imageView2);

        RotateAnimation r = new RotateAnimation(360, 0, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);

        r.setDuration((long) 2*1000);
        r.setRepeatCount(Animation.INFINITE);
        syncicon.startAnimation(r);
    }

    public void onSyncIconStop() {
        ImageView syncicon = (ImageView)findViewById(R.id.imageView2);
        final TextView serveranswer = (TextView)findViewById(R.id.textView49);

        RotateAnimation r = new RotateAnimation(0, 0, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);

        r.setDuration((long) 2*3000);
        r.setRepeatCount(Animation.INFINITE);
        syncicon.startAnimation(r);

        serveranswer.setText("Empfangen");
        serveranswer.setTextColor(GREEN);

        Handler h = new Handler();
        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                serveranswer.setText("");

            }
        }, 5000);
    }

    public void onSyncError() {
        final ImageView syncicon = (ImageView)findViewById(R.id.imageView2);
        final TextView serveranswer = (TextView)findViewById(R.id.textView49);

        RotateAnimation r = new RotateAnimation(0, 0, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);

        r.setDuration((long) 2*3000);
        r.setRepeatCount(Animation.INFINITE);
        syncicon.startAnimation(r);

        syncicon.setBackgroundColor(RED);
        serveranswer.setText("Error");
        serveranswer.setTextColor(RED);

        Handler h = new Handler();
        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                serveranswer.setText("");
                syncicon.setBackgroundColor(View.GONE);

            }
        }, 5000);
    }

    // SMS Alert //
    // sets SMS content from specific alert number to bofield
    public void setSMS() {
        //SMS Alert// write content to incident fields

        if(phoneNumber1 == "+144") {
            SMSm = (TextView) findViewById(R.id.bofield);

            SMSm.setText(SMSBody1);

            Snackbar snackbar = Snackbar
                    .make(coordinatorLayout, SMSBody1, Snackbar.LENGTH_LONG);

            snackbar.show();
        }
    }

    // ToolBar status icons //
    public void checkMLSConnection() {
            // ToolBar mls connection state icon //
            ImageView mlscon = (ImageView) findViewById(R.id.imageView_mlscon);
            {
                // check if you are connected or not

                if (conman.isConnectedToServer()) {

                    mlscon.setImageResource(R.drawable.connected64);

                } else {

                    mlscon.setImageResource(R.drawable.disconnected64);
                }
            }
        }

    private void showpDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hidepDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }


    //TODO: create method to save app/fragment state
    /*
        @Override
        public void onSaveInstanceState(Bundle outState) {
            super.onSaveInstanceState(outState);
            System.out.println("TAG, onSaveInstanceState");

            outState.putString("message", "This is my message to be reloaded");

            final TextView textView83 = (TextView)findViewById(R.id.textView83);
            final Button button41 = (Button)findViewById(R.id.button41);

            CharSequence button41text = button41.getText();
            CharSequence stateText = textView83.getText();
            outState.putCharSequence("savedbuttonText", button41text);
            outState.putCharSequence("savedstateText", stateText);
        }

        protected void onRestoreInstanceState(Bundle savedState) {
            System.out.println("TAG, onRestoreInstanceState");

            final TextView textView83 = (TextView)findViewById(R.id.textView83);
            final Button button41 = (Button) findViewById(R.id.button41);

            CharSequence button41text = savedState.getCharSequence("savedbuttonText");
            CharSequence stateText = savedState.getCharSequence("savedstateText");
            button41.setText(button41text);
            textView83.setText(stateText);
        }
    */
    //TODO: create app life cycle

    @Override
    public void onStart(){
        super.onStart();
        setSMS();
        //checkMLSConnection();
    }
    @Override
    public void onResume() {
        super.onResume();
        //initilizeMap();
        //checkPlayServices();
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
        super.onDestroy();


    }

    // Buttons //
    // TODO: implementing second app / not needed in first testversion
    public void ptt(View v) {
        if (v.getId() == R.id.button61) {
            AlertDialog.Builder dlgBuilder = new AlertDialog.Builder(MainActivity.this);
            dlgBuilder.setMessage("ptt app");
            dlgBuilder.setTitle("PTT App");

            dlgBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });

            AlertDialog alert = dlgBuilder.create();
            alert.show();

        }
    }

    //Call LS Buttons//
    // start direct call

    public void lscall(View view) {
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        PackageManager pm = getPackageManager();
        switch(view.getId()) {

            case R.id.button17:

                callIntent.setData(Uri.parse("tel:" + R.string.lsbv));
                startActivity(callIntent);

                if (pm.checkPermission(Manifest.permission.CALL_PHONE, getPackageName()) == PackageManager.PERMISSION_GRANTED) {

                } else {

                }
                break;

            case R.id.button47:
                callIntent.setData(Uri.parse("tel:" + R.string.lsallg));
                startActivity(callIntent);

                if (pm.checkPermission(Manifest.permission.CALL_PHONE, getPackageName()) == PackageManager.PERMISSION_GRANTED) {

                } else {

                }
                break;

            case R.id.button43:
                TextView commphone = (TextView)findViewById(R.id.commphone);
                String listnumber = commphone.getText().toString();

                if(commphone.getText().toString().trim().length() > 0) {

                    callIntent.setData(Uri.parse("tel:" + listnumber));
                    startActivity(callIntent);

                    if (pm.checkPermission(Manifest.permission.CALL_PHONE, getPackageName()) == PackageManager.PERMISSION_GRANTED) {

                    } else {

                    }

                } else {
                    Toast.makeText(MainActivity.this, "Keine Nummer ausgewählt!", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }

    // Alert Push Notification Manager //
    // alerts on incoming incident, gcm server!!
    //TODO: later: function for new incident alert !!! check again
    public void taskalert(View v) {

        final LayoutInflater inci = getLayoutInflater();
        final View incidentView = inci.inflate(R.layout.fragment_incident, null);
        final MediaPlayer mp = MediaPlayer.create(MainActivity.this, R.raw.alert_newemergency);
        //TODO: remove button
        if (v.getId() == R.id.button) {

            Button alertbtntest = (Button) findViewById(R.id.button);
            alertbtntest.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View arg5) {

                    final TextView bofield = (TextView) incidentView.findViewById(R.id.bofield);
                    final TextView brfrfield = (TextView) incidentView.findViewById(R.id.brfrfield);
                    final TextView infofield = (TextView) incidentView.findViewById(R.id.infofield);

                    final Button button41 = (Button) incidentView.findViewById(R.id.button41);
                    final TextView textView83 = (TextView) incidentView.findViewById(R.id.textView83);
                    final TextView textView85 = (TextView) incidentView.findViewById(R.id.textView85);

                    final Calendar cal = Calendar.getInstance(TimeZone.getDefault());
                    final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

                    AlertDialog.Builder dlgBuilder = new AlertDialog.Builder(MainActivity.this);
                    dlgBuilder.setCancelable(false);
                    dlgBuilder.setTitle("EINSATZ/AUFTRAG");
                    dlgBuilder.setMessage("Addresse\nBerufungsgrund");

                    //dlgBuilder.setView(R.id.alertbox_layout);

                    dlgBuilder.setPositiveButton("Einsatz übernehmen", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            button41.setText(R.string.zbo);
                            textView83.setText("QU");
                            textView85.setText(sdf.format(cal.getTime()));
                            button41.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_fast_forward_black_18dp, 0, 0, 0);
                            mp.stop();

                            Toast.makeText(MainActivity.this, "Einsatz übernommen", Toast.LENGTH_SHORT).show();
                        }
                    });

                    AlertDialog alert = dlgBuilder.create();
                    alert.show();

/*
                String title = bgfield.getText().toString().trim();
                String subject = bofield.getText().toString().trim();
                String body = infofield.getText().toString().trim();
*/
                    Intent intent = new Intent(MainActivity.this, MainActivity.class);
                    PendingIntent contentIntent = PendingIntent.getActivity(MainActivity.this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

                    NotificationCompat.Builder mBuilder =
                            new NotificationCompat.Builder(MainActivity.this);
                                /*
                                .setSmallIcon(R.drawable.ic_warning_black_18dp)
                                .setContentTitle(infofield.getText().toString())
                                .setContentText(brfrfield.getText().toString());
                                //.setContentIntent(pendingIntent); // below Gingerbread
*/
                    mBuilder.setAutoCancel(true)
                            .setDefaults(Notification.DEFAULT_ALL)
                            .setWhen(System.currentTimeMillis())
                            .setSmallIcon(R.drawable.ic_warning_black_18dp)
                            .setTicker("Alert new Incident")
                            .setContentTitle("Alert + Code")
                            .setContentText("AddressStreet")
                            .setDefaults(Notification.DEFAULT_LIGHTS | Notification.DEFAULT_SOUND)
                            .setContentIntent(contentIntent)
                            .setContentInfo("Detail Code");

                    // AlertSound
                    //mBuilder.setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM));

                    mp.setLooping(true);
                    mp.start();

                    NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                    notificationManager.notify(1, mBuilder.build());
                }
            });
        }
    }

    // SnackBar // for incident update notification // on sdk23 only
    // show updates on incident in snackbar
    public void onIncidentUpdate() {

        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorLayout);
        Snackbar snackbar = Snackbar.make(coordinatorLayout, R.string.inciupdate, Snackbar.LENGTH_INDEFINITE);

        snackbar.show();
    }


    // Button state & color functions START //
    // Status EB NEB AD mainstatusFragment //
    // TODO: set by server

    public void ebst(View v) {
        if (v.getId() == R.id.button38) {

            Button button38 = (Button) findViewById(R.id.button38);
            Button button39 = (Button) findViewById(R.id.button39);
            Button button40 = (Button) findViewById(R.id.button40);

            button38.setEnabled(true);
            button38.setClickable(false);
            button38.setBackgroundColor(GREEN);

            button39.setEnabled(false);
            button39.setClickable(false);
            button39.setBackgroundResource(android.R.drawable.btn_default);

            button40.setEnabled(false);
            button40.setClickable(false);
            button40.setBackgroundResource(android.R.drawable.btn_default);

        }
    }

    public void nebst(View v) {
        if (v.getId() == R.id.button39) {

            Button button38 = (Button) findViewById(R.id.button38);
            Button button39 = (Button) findViewById(R.id.button39);
            Button button40 = (Button) findViewById(R.id.button40);

            button38.setEnabled(false);
            button38.setClickable(false);
            button38.setBackgroundResource(android.R.drawable.btn_default);

            button39.setEnabled(false);
            button39.setClickable(false);
            button39.setBackgroundColor(Color.parseColor("#9C27B0"));

            button40.setEnabled(false);
            button40.setClickable(false);
            button40.setBackgroundResource(android.R.drawable.btn_default);

        }
    }

    public void adst(View v) {
        if (v.getId() == R.id.button40) {

            Button button38 = (Button) findViewById(R.id.button38);
            Button button39 = (Button) findViewById(R.id.button39);
            Button button40 = (Button) findViewById(R.id.button40);

            button38.setEnabled(false);
            button38.setClickable(false);
            button38.setBackgroundResource(android.R.drawable.btn_default);

            button39.setEnabled(false);
            button39.setClickable(false);
            button39.setBackgroundResource(android.R.drawable.btn_default);

            button40.setEnabled(false);
            button40.setClickable(false);
            button40.setBackgroundColor(Color.parseColor("#9C27B0"));

        }
    }

    //Status Buttons//TODO: move methods to IncidentAction
    //Radio
    public void radiostatusbtn(View view) {
        final AlertDialog.Builder dlgBuilder = new AlertDialog.Builder(MainActivity.this);
        switch(view.getId()) {
            case R.id.button5://SelectivRuf

                final Button button5 = (Button) findViewById(R.id.button5);
                button5.setEnabled(true);
                button5.setClickable(false);
                button5.setBackgroundColor(Color.YELLOW);
                Toast.makeText(getApplicationContext(), "SelectivRuf", Toast.LENGTH_SHORT).show();

                Handler h = new Handler();
                h.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        button5.setEnabled(true);
                        button5.setClickable(true);
                        button5.setBackgroundResource(android.R.drawable.btn_default);
                    }
                }, 30000);
                break;

            case R.id.button12://NOTRUF
                dlgBuilder.setMessage("NOTRUF senden?");
                dlgBuilder.setCancelable(false);
                dlgBuilder.setPositiveButton("Ja", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        final Button button12 = (Button) findViewById(R.id.button12);
                        button12.setEnabled(true);
                        button12.setClickable(false);
                        button12.setBackgroundColor(RED);
                        Toast.makeText(getApplicationContext(), "NOTRUF gesendet", Toast.LENGTH_LONG).show();

                        Handler h = new Handler();
                        h.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                button12.setEnabled(true);
                                button12.setClickable(true);
                                button12.setBackgroundResource(android.R.drawable.btn_default);
                            }
                        }, 45000);
                    }
                });

                dlgBuilder.setNegativeButton("Nein", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });

                dlgBuilder.create().show();
                break;
        }
    }

    //PatStatus am BO
    public void patstatusbtns(View view) {
        final Button button41 = (Button)findViewById(R.id.button41);
        final TextView textView83 = (TextView) findViewById(R.id.textView83);
        final AlertDialog.Builder dlgBuilder = new AlertDialog.Builder(MainActivity.this);
        switch(view.getId()) {

            case R.id.button10://INTUNT
                dlgBuilder.setMessage("Intervention unterblieben?");
                dlgBuilder.setCancelable(false);
                dlgBuilder.setPositiveButton("Ja", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Button button10 = (Button) findViewById(R.id.button10);
                        button10.setEnabled(true);
                        button10.setClickable(false);
                        button10.setBackgroundColor(GREEN);

                        Button button11 = (Button) findViewById(R.id.button11);
                        button11.setEnabled(false);
                        button11.setClickable(false);
                        button11.setBackgroundResource(android.R.drawable.btn_default);

                        Button button13 = (Button) findViewById(R.id.button13);
                        button13.setEnabled(false);
                        button13.setClickable(false);
                        button13.setBackgroundResource(android.R.drawable.btn_default);

                        button41.setEnabled(true);
                        button41.setClickable(true);
                        button41.setBackgroundResource(android.R.drawable.btn_default);
                        button41.setText(R.string.eb);
                        button41.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_play_arrow_black_18dp, 0, 0, 0);
                        textView83.setText("EB");

                        Toast.makeText(MainActivity.this, "keine Intervention", Toast.LENGTH_SHORT).show();
                    }
                });

                dlgBuilder.setNegativeButton("Nein", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });

                AlertDialog alert = dlgBuilder.create();
                alert.show();
                break;

            case R.id.button11://BEL/VER
                dlgBuilder.setTitle("Patient belassen/verweigert?");
                dlgBuilder.setItems(new CharSequence[]
                                {"Patient belassen", "Patient verweigert", "Nein"},

                        new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int which) {

                                switch (which) {

                                    case 0:

                                        Button button11 = (Button) findViewById(R.id.button11);
                                        button11.setEnabled(true);
                                        button11.setClickable(false);
                                        button11.setBackgroundColor(GREEN);

                                        Button button10 = (Button) findViewById(R.id.button10);
                                        button10.setEnabled(false);
                                        button10.setClickable(false);
                                        button10.setBackgroundResource(android.R.drawable.btn_default);

                                        Button button13 = (Button) findViewById(R.id.button13);
                                        button13.setEnabled(false);
                                        button13.setClickable(false);
                                        button13.setBackgroundResource(android.R.drawable.btn_default);

                                        button41.setEnabled(true);
                                        button41.setClickable(true);
                                        button41.setBackgroundResource(android.R.drawable.btn_default);
                                        button41.setText(R.string.eb);
                                        button41.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_play_arrow_black_18dp, 0, 0, 0);
                                        textView83.setText("EB");

                                        Toast.makeText(MainActivity.this, "Belassung", Toast.LENGTH_SHORT).show();
                                        break;
                                    case 1:

                                        button11 = (Button) findViewById(R.id.button11);
                                        button11.setEnabled(true);
                                        button11.setClickable(false);
                                        button11.setBackgroundColor(GREEN);

                                        button10 = (Button) findViewById(R.id.button10);
                                        button10.setEnabled(false);
                                        button10.setClickable(false);
                                        button10.setBackgroundResource(android.R.drawable.btn_default);

                                        button13 = (Button) findViewById(R.id.button13);
                                        button13.setEnabled(false);
                                        button13.setClickable(false);
                                        button13.setBackgroundResource(android.R.drawable.btn_default);

                                        button41.setEnabled(true);
                                        button41.setClickable(true);
                                        button41.setBackgroundResource(android.R.drawable.btn_default);
                                        button41.setText(R.string.eb);
                                        button41.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_play_arrow_black_18dp, 0, 0, 0);
                                        textView83.setText("EB");

                                        Toast.makeText(MainActivity.this, "Patient verweigert", Toast.LENGTH_SHORT).show();
                                        break;
                                    case 2:
                                        break;
                                }
                            }
                        });

                dlgBuilder.create().show();
                break;

            case R.id.button13://Anderes RM
                dlgBuilder.setMessage("Patient an anderes Rettungsmittel übergeben?");
                dlgBuilder.setCancelable(false);
                dlgBuilder.setPositiveButton("Ja", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Button button13 = (Button) findViewById(R.id.button13);
                        button13.setEnabled(true);
                        button13.setClickable(false);
                        button13.setBackgroundColor(GREEN);

                        Button button10 = (Button) findViewById(R.id.button10);
                        button10.setEnabled(false);
                        button10.setClickable(false);
                        button10.setBackgroundResource(android.R.drawable.btn_default);

                        Button button11 = (Button) findViewById(R.id.button11);
                        button11.setEnabled(false);
                        button11.setClickable(false);
                        button11.setBackgroundResource(android.R.drawable.btn_default);

                        button41.setEnabled(true);
                        button41.setClickable(true);
                        button41.setBackgroundResource(android.R.drawable.btn_default);
                        button41.setText(R.string.abo);
                        button41.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_play_arrow_black_18dp, 0, 0, 0);
                        textView83.setText("EB");

                        Toast.makeText(MainActivity.this, "Übergabe an anderes Rettungsmittel", Toast.LENGTH_SHORT).show();
                    }
                });

                dlgBuilder.setNegativeButton("Nein", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });

                dlgBuilder.create().show();
                break;
        }
    }

    // Status Button incidentFragment //
    // change unit status
    //TODO: set fragments on status change?? / sync with server

    public void stbtnClick(View v) {

        final RelativeLayout deliveryloclayout = (RelativeLayout)getLayoutInflater().inflate(R.layout.fragment_deliveryloc, null);

        final Calendar cal = Calendar.getInstance(TimeZone.getDefault());
        final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

        Button button41 = (Button) findViewById(R.id.button41);

        AlertDialog.Builder dlgBuilder = new AlertDialog.Builder(MainActivity.this);
        dlgBuilder.setTitle(R.string.stwe);
        dlgBuilder.setMessage(button41.getText().toString());
        dlgBuilder.setCancelable(false);
        dlgBuilder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {

            final Button button41 = (Button) findViewById(R.id.button41);
            final TextView textView83 = (TextView) findViewById(R.id.textView83);
            final TextView textView85 = (TextView) findViewById(R.id.textView85);
            final TextView aofield = (TextView) deliveryloclayout.findViewById(R.id.aofield);

            Button button10 = (Button) findViewById(R.id.button10);
            Button button11 = (Button) findViewById(R.id.button11);
            Button button13 = (Button) findViewById(R.id.button13);
            Button button46 = (Button) findViewById(R.id.button46);

            @Override
            public void onClick(DialogInterface dialog, int which) {

                if (textView83.getText().equals("") || textView83.getText().equals("EB")) {

                    button41.setEnabled(false);
                    button41.setClickable(false);
                    button41.setBackgroundColor(YELLOW);
                    button41.setText(R.string.zbo);
                    textView83.setText("QU");
                    textView85.setText(sdf.format(cal.getTime()));
                    button41.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_fast_forward_black_18dp, 0, 0, 0);

                    Handler h = new Handler();
                    h.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            button41.setEnabled(true);
                            button41.setClickable(true);
                            button41.setBackgroundResource(android.R.drawable.btn_default);
                        }
                    }, 3000);

                } else if (textView83.getText().equals("QU")) {

                    button41.setEnabled(false);
                    button41.setClickable(false);
                    button41.setBackgroundColor(YELLOW);
                    button41.setText(R.string.abo);
                    textView83.setText("ZBO");
                    textView85.setText(sdf.format(cal.getTime()));
                    button41.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_skip_next_black_18dp, 0, 0, 0);

                    Handler h = new Handler();
                    h.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            button41.setEnabled(true);
                            button41.setClickable(true);
                            button41.setBackgroundResource(android.R.drawable.btn_default);
                        }
                    }, 10000);

                } else if (textView83.getText().equals("ZBO")) {

                    button41.setEnabled(false);
                    button41.setClickable(false);
                    button41.setBackgroundColor(YELLOW);
                    button41.setText(R.string.zao);
                    textView83.setText("ABO");
                    textView85.setText(sdf.format(cal.getTime()));
                    button41.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_call_made_black_18dp, 0, 0, 0);

                    button10.setEnabled(true);
                    button10.setClickable(true);
                    button11.setEnabled(true);
                    button11.setClickable(true);
                    button13.setEnabled(true);
                    button13.setClickable(true);
                    button46.setEnabled(true);
                    button46.setClickable(true);
/*
                    mTabHost.getTabWidget().removeView(mTabHost.getTabWidget().getChildTabViewAt(2));

                    mTabHost.addTab(
                            mTabHost.newTabSpec("tab4").setIndicator("Abgabeort", null),
                            deliverylocFragment.class, null);
*/
                    Handler h = new Handler();
                    h.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            button41.setEnabled(true);
                            button41.setClickable(true);
                            button41.setBackgroundResource(android.R.drawable.btn_default);
                        }
                    }, 10000);

                } else if ((textView83.getText().equals("ABO")) && (aofield.getText().toString().trim().length() > 0)) {
                    //TODO: redundante funktion
                    if (aofield.getText().toString().trim().length() > 0) {
                        button41.setEnabled(true);
                        button41.setClickable(true);
                        button41.setBackgroundColor(YELLOW);
                        button41.setText(R.string.aao);
                        textView83.setText("ZAO");
                        textView85.setText(sdf.format(cal.getTime()));
                        button41.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_local_hospital_black_18dp, 0, 0, 0);

                        button10.setEnabled(false);
                        button10.setClickable(false);
                        button11.setEnabled(false);
                        button11.setClickable(false);
                        button13.setEnabled(false);
                        button13.setClickable(false);
                        button46.setEnabled(false);
                        button46.setClickable(false);
                    } else {
                        button41.setEnabled(false);
                        button41.setClickable(false);
                        Toast.makeText(MainActivity.this, "Kein Abgabeort eingetragen", Toast.LENGTH_LONG).show();
                    }
/*
                    mTabHost.getTabWidget().removeView(mTabHost.getTabWidget().getChildTabViewAt(1));
*/
                    Handler h = new Handler();
                    h.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            button41.setEnabled(true);
                            button41.setClickable(true);
                            button41.setBackgroundResource(android.R.drawable.btn_default);
                        }
                    }, 10000);

                } else if (textView83.getText().equals("ZAO")) {

                    button41.setEnabled(false);
                    button41.setClickable(false);
                    button41.setBackgroundColor(YELLOW);
                    button41.setText(R.string.eb);
                    textView83.setText("AAO");
                    textView85.setText(sdf.format(cal.getTime()));
                    button41.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_play_arrow_black_18dp, 0, 0, 0);

                    Handler h = new Handler();
                    h.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            button41.setEnabled(true);
                            button41.setClickable(true);
                            button41.setBackgroundResource(android.R.drawable.btn_default);
                        }
                    }, 10000);

                } else if (textView83.getText().equals("AAO")) {

                    button41.setEnabled(false);
                    button41.setClickable(false);
                    button41.setBackgroundColor(YELLOW);
                    button41.setText("QU");
                    textView83.setText("EB");
                    textView85.setText(sdf.format(cal.getTime()));
/*
                    mTabHost.removeAllViews();

                    mTabHost.addTab(
                            mTabHost.newTabSpec("tab1").setIndicator("Status", null),
                            mainstatusFragment.class, null);
                    mTabHost.addTab(
                            mTabHost.newTabSpec("tab2").setIndicator("Einsatzdaten", null),
                            incidentFragment.class, null);
*/
                    Handler h = new Handler();
                    h.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            button41.setEnabled(true);
                            button41.setClickable(true);
                            button41.setBackgroundResource(android.R.drawable.btn_default);
                        }
                    }, 10000);

                }
            }
        });

        dlgBuilder.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        AlertDialog alert = dlgBuilder.create();
        alert.show();
    }

    // Emergency light yes/no //
    public void setEmergency(View v) {
        if (v.getId() == R.id.checkBox) {
            CheckBox checkBox = (CheckBox) findViewById(R.id.checkBox);
            checkBox.setEnabled(true);
            checkBox.setClickable(false);
            checkBox.setHintTextColor(BLUE);
        }
    }

    // Button state & color functions END

    // Patienten Management dialog builder //
    // data stored in shared preferences

    public void createpat(View v) {

        final RelativeLayout patmanlayout = (RelativeLayout)getLayoutInflater().inflate(R.layout.patman, null);
        final Button bettbtn = (Button) patmanlayout.findViewById(R.id.bettbtn);
        final TextView textView11 = (TextView) patmanlayout.findViewById(R.id.textView11);

        final EditText addpatfirstname = (EditText) patmanlayout.findViewById(R.id.editText2);
        final EditText addpatlastname = (EditText) patmanlayout.findViewById(R.id.editText);
        final EditText addpatdatebirth = (EditText) patmanlayout.findViewById(R.id.editText3);
        //final EditText addpatsvnr = (EditText) patmanlayout.findViewById();
        final EditText addpatplsnr = (EditText) patmanlayout.findViewById(R.id.editText4);
        final TextView addpatward = (TextView) patmanlayout.findViewById(R.id.textView11);
        final Spinner addpatgender = (Spinner) patmanlayout.findViewById(R.id.spinner);

        switch(v.getId()) {

            case R.id.button46:

                    AlertDialog.Builder dlgBuilder = new AlertDialog.Builder(MainActivity.this);
                    //dlgBuilder.setMessage("Patient anlegen");
                    dlgBuilder.setTitle("PATADMIN");
                    //LayoutInflater inflater = (MainActivity.this.getLayoutInflater());

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

                                    patedit.commit();

                                    //TODO: send data

                                    Toast.makeText(MainActivity.this, "Patient angelegt", Toast.LENGTH_SHORT).show();

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

                                    patedit.commit();


                                }
                            }

                    );

                    AlertDialog alert = dlgBuilder.create();
                    alert.show();
                break;

            case R.id.changepatbtn:

                    dlgBuilder = new AlertDialog.Builder(MainActivity.this);
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

                            //Toast.makeText(MainActivity.this, "Pat. Daten geändert", Toast.LENGTH_SHORT).show();

                        }
                    });

                    dlgBuilder.setNegativeButton("Zurück", new DialogInterface.OnClickListener()

                            {

                                @Override
                                public void onClick(DialogInterface dialog, int which) {


                                }
                            }

                    );

                    alert = dlgBuilder.create();
                    alert.show();

                }

        //remove layout after dialog creation
        View viewToRemove = findViewById(R.id.patmanrelayout);
        if (viewToRemove != null && viewToRemove.getParent() != null && viewToRemove instanceof ViewGroup)
            ((ViewGroup) viewToRemove.getParent()).removeView(viewToRemove);
    }

    // Bett abbuchen btn //
    // TODO: method not needed in first test version

    public void bettbuchen(View v) {

        final RelativeLayout patmanlayout = (RelativeLayout)getLayoutInflater().inflate(R.layout.patman, null);
        /*
        LayoutInflater inflater = getLayoutInflater();
        getWindow().addContentView(inflater.inflate(R.layout.patman, null), new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
*/
        if (v.getId() == R.id.bettbtn) {

            AlertDialog.Builder dlgbuilder = new AlertDialog.Builder(MainActivity.this);
            dlgbuilder.setTitle("Abteilung auswählen");
            dlgbuilder.setItems(new CharSequence[]
                            {"Intern", "Unfall", "Chirurgie", "HNO", "Dermatologie", "Spezialbett", "andere Abteilung"},

                    new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int which) {

                            TextView abtedit = (TextView) patmanlayout.findViewById(R.id.textView11);

                            switch (which) {

                                case 0:
                                    abtedit.setText(R.string.intern);
                                    Toast.makeText(MainActivity.this, R.string.intern, Toast.LENGTH_SHORT).show();
                                    break;

                                case 1:
                                    abtedit.setText(R.string.unfall, TextView.BufferType.EDITABLE);
                                    Toast.makeText(MainActivity.this, R.string.unfall, Toast.LENGTH_SHORT).show();
                                    break;

                                case 2:
                                    abtedit.setText(R.string.chir, TextView.BufferType.EDITABLE);
                                    Toast.makeText(MainActivity.this, R.string.chir, Toast.LENGTH_SHORT).show();
                                    break;

                                case 3:
                                    abtedit.setText(R.string.hno, TextView.BufferType.EDITABLE);
                                    Toast.makeText(MainActivity.this, R.string.hno, Toast.LENGTH_SHORT).show();
                                    break;

                                case 4:
                                    abtedit.setText(R.string.derma, TextView.BufferType.EDITABLE);
                                    Toast.makeText(MainActivity.this, R.string.derma, Toast.LENGTH_SHORT).show();
                                    break;

                                case 5:
                                    abtedit.setText(R.string.spezbett, TextView.BufferType.EDITABLE);
                                    Toast.makeText(MainActivity.this, R.string.lsbvanrufen, Toast.LENGTH_LONG).show();
                                    break;

                                case 6:
                                    abtedit.setText(R.string.andbett, TextView.BufferType.EDITABLE);
                                    Toast.makeText(MainActivity.this, R.string.lsbvanrufen, Toast.LENGTH_LONG).show();
                                    break;
                            }
                        }
                    }
            );

            dlgbuilder.create().

                    show();
        }
    }

    // PatMan start btn //
    // TODO: not needed in test version

    public void patmanstart(View v) {
        if (v.getId() == R.id.button21) {

            Button button21 = (Button) findViewById(R.id.button21);
            button21.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View arg0) {
                    Intent ipatman = new Intent(getApplicationContext(), PatmanActivity.class);
                    startActivity(ipatman);
                }
            });

        }
    }

    // Store Pat Data // with shared prefs

    // Store Pat Data end //

    // Navigate to provided address //
    // TODO: choose between built-in maps api and own navigation app
    public void navigate(View view) {

        final TextView botext = (TextView) findViewById(R.id.bofield);
        final TextView aotext = (TextView) findViewById(R.id.aofield);
        final TextView custom = (TextView) findViewById(R.id.commaddress);

        switch(view.getId())
        {
            case R.id.button18:
                if(botext.getText().toString().trim().length() > 0) {

                    String navadress = "google.navigation:" + botext.getText().toString();
                    Intent nav = new Intent(android.content.Intent.ACTION_VIEW);
                    nav.setData(Uri.parse(navadress));
                    startActivity(nav);

                } else {
                    Toast.makeText(MainActivity.this, "Kein Berufungsort eingetragen!", Toast.LENGTH_LONG).show();
                }
                break;

            case R.id.button19:
                if (aotext.getText().toString().trim().length() > 0) {

                    String navadress = "google.navigation:" + aotext.getText().toString();
                    Intent nav = new Intent(android.content.Intent.ACTION_VIEW);
                    nav.setData(Uri.parse(navadress));
                    startActivity(nav);

                } else {
                    Toast.makeText(MainActivity.this, "Kein Abgabeort eingetragen!", Toast.LENGTH_LONG).show();
                }
                break;

            case R.id.button45: //commfragment, custom navigation
                if (custom.getText().toString().trim().length() > 0) {

                    String navadress = "google.navigation:" + custom.getText().toString();
                    Intent nav = new Intent(android.content.Intent.ACTION_VIEW);
                    nav.setData(Uri.parse(navadress));
                    startActivity(nav);

                } else {
                    Toast.makeText(MainActivity.this, "Kein POI ausgewählt!", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }

    // Map Fragment //
    // user can choose between leaflet(webview) and google maps(fragment api)
        public void showmap(View v) {
            if (v.getId() == R.id.button30) {

                WebView gisView = (WebView) findViewById(R.id.gisView);

                gisView.getSettings().setJavaScriptEnabled(true);
                gisView.getSettings().getAllowFileAccessFromFileURLs();
                gisView.getSettings().setAllowUniversalAccessFromFileURLs(true);
                gisView.getSettings().setGeolocationEnabled(true);

                gisView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
                gisView.getSettings().setBuiltInZoomControls(true);

                gisView.setWebViewClient(new GeoWebViewActivity.GeoWebViewClient());
                gisView.setWebChromeClient(new GeoWebViewActivity.GeoWebChromeClient());

                gisView.loadUrl("file:///android_asset/leaflet.html");
            }
        }

    public void startmaps (View v) {
        if (v.getId() == R.id.button31) {
            try {
                // Loading map
                initilizeMap();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void initilizeMap() {
        if (googleMap == null) {
            googleMap = ((MapFragment) getFragmentManager().findFragmentById(
                    R.id.map)).getMap();

            // check if map is created successfully or not
            if (googleMap == null) {
                Toast.makeText(getApplicationContext(),
                        "Sorry! unable to create maps", Toast.LENGTH_SHORT)
                        .show();
            }
        }
    }

    public void initializeMapLocationSettings() {
        googleMap.setMyLocationEnabled(true);
    }

    public void initializeMapTraffic() {
        googleMap.setTrafficEnabled(true);
    }

    public void initializeMapType() {
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
    }

    /*
        // create marker
        MarkerOptions marker = new MarkerOptions().position(new LatLng(latitude, longitude)).title("");
        // adding marker
        googleMap.addMarker(marker);
    */
    public void setmapmarker() {
        Marker nodo = googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(48.1907634, 16.411198))
                .title("NODO"));

        Marker kss = googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(48.2671734, 16.4019968))
                .title("KSS"));
        //.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic)));
    }

    public void mapcamera () {
        //unit position
        CameraPosition cameraPosition = new CameraPosition.Builder().target(
                new LatLng(latitude, longitude)).zoom(12).build();

        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }

    // MAP fragment END //

    // Report incident method on mainstatus fragment //
    // Get coordinates, and nearest address
        public void reportincident(View v) {

        RelativeLayout reportincident = (RelativeLayout)getLayoutInflater().inflate(R.layout.reportincident, null);

            if (v.getId() == R.id.button42) {

                final EditText editText24 = (EditText) reportincident.findViewById(R.id.editText24);
                final TextView textView86 = (TextView) reportincident.findViewById(R.id.textView86);
                final TextView textView93 = (TextView) reportincident.findViewById(R.id.textView93);
                final Button button42 = (Button) findViewById(R.id.button42);

                AlertDialog.Builder dlgBuilder = new AlertDialog.Builder(MainActivity.this);
                dlgBuilder.setMessage("Neuen Einsatz bei derzeitiger Position melden?");
                dlgBuilder.setCancelable(false);

                dlgBuilder.setView(reportincident);

                /*
                GPSManager.LocationResult locationResult = new GPSManager.LocationResult() {
                    @Override
                    public void gotLocation(Location location) {
                        loc = location;
                        latitude = loc.getLatitude();
                        longitude = loc.getLongitude();
                    }
                };

                GPSManager mylocation = new GPSManager();
                mylocation.getLocation(MainActivity.this, locationResult);
*/
                GPSManager gps = new GPSManager(MainActivity.this);
                double latitude = gps.getLatitude();
                double longitude = gps.getLongitude();

                Geocoder geocoder = new Geocoder(this, Locale.getDefault());

                try {
                    List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);

                    if(addresses != null && addresses.size() > 0) {
                        Address returnedAddress = addresses.get(0);
                        StringBuilder strReturnedAddress = new StringBuilder();
                        for(int i=0; i<returnedAddress.getMaxAddressLineIndex(); i++) {
                            strReturnedAddress.append(returnedAddress.getAddressLine(i)).append("\n");
                        }
                        strReturnedAddress.append(returnedAddress.getLocality()).append("\n");
                        //strReturnedAddress.append(returnedAddress.getPostalCode()).append("\n");
                        //strReturnedAddress.append(returnedAddress.getCountryName());

                        editText24.setText(strReturnedAddress.toString());

                    } else {

                        editText24.setText("No Address found!");
                    }
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    editText24.setText("Cannot get Address!");
                }

/*
                Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
                String result = null;
                try {
                    List<Address> addressList = geocoder.getFromLocation(
                            latitude, longitude, 1);
                    if (addressList != null && addressList.size() > 0) {
                        Address address = addressList.get(0);
                        StringBuilder sb = new StringBuilder();
                        for (int i = 0; i < address.getMaxAddressLineIndex(); i++) {
                            sb.append(address.getAddressLine(i)).append("\n");
                        }
                        sb.append(address.getLocality()).append("\n");
                        sb.append(address.getPostalCode()).append("\n");
                        sb.append(address.getCountryName());
                        result = sb.toString();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
*/

                    //editText24.setText(locationAddress);
                    textView86.setText("lat: " + latitude);
                    textView93.setText("lon: " + longitude);

                dlgBuilder.setPositiveButton("Ja", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                button42.setEnabled(false);
                                button42.setClickable(false);
                                button42.setBackgroundColor(YELLOW);

                                Handler h = new Handler();
                                h.postDelayed(new

                                                      Runnable() {
                                                          @Override
                                                          public void run() {
                                                              button42.setEnabled(true);
                                                              button42.setClickable(true);
                                                              button42.setBackgroundResource(android.R.drawable.btn_default);
                                                              editText24.setText("");
                                                              textView86.setText("");
                                                              textView93.setText("");
                                                          }
                                                      }

                                        , 30000);

                                Toast.makeText(MainActivity.this, "Neuen Einsatz an Leitstelle gemeldet", Toast.LENGTH_SHORT).

                                        show();
                            }
                        }

                );

                dlgBuilder.setNegativeButton("Nein", new DialogInterface.OnClickListener()

                        {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        }

                );

                AlertDialog alert = dlgBuilder.create();
                alert.show();

                // remove layout
                View viewToRemove = findViewById(R.id.reportincidentrelayout);
                if (viewToRemove != null && viewToRemove.getParent() != null && viewToRemove instanceof ViewGroup)
                    ((ViewGroup) viewToRemove.getParent()).removeView(viewToRemove);


            }
        }

    //JSON GET and POST method//
    // Async Task

    class PostAsync extends AsyncTask<String, String, JSONObject> {

        JSONParser jsonParser = new JSONParser();

        private ProgressDialog pDialog;

        private static final String TAG_SUCCESS = "success";
        private static final String TAG_MESSAGE = "message";


        @Override
        protected void onPreExecute() {
            pDialog = new ProgressDialog(MainActivity.this);
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
                Toast.makeText(MainActivity.this, json.toString(),
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
            pDialog = new ProgressDialog(MainActivity.this);
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
                Toast.makeText(MainActivity.this, json.toString(),
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
/*
    //Spinner on commFragment
    //TODO: not working, should provide Address and Phonenumber, put in onCreate method
    public void spinnertest() {
        // Spinner element
        Spinner spinner = (Spinner) findViewById(R.id.spinner2);

        // Spinner click listener
        spinner.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);

        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("Krankenhäuser");
        categories.add("Rettungsstationen");
        categories.add("Polizeiinspektionen");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);
    }

    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
    }
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }
    */
}

