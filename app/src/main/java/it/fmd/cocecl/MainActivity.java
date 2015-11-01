package it.fmd.cocecl;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NotificationCompat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import static android.graphics.Color.BLUE;
import static android.graphics.Color.GREEN;
import static android.graphics.Color.YELLOW;


public class MainActivity extends FragmentActivity {

    private FragmentTabHost mTabHost;

    public static Location loc;
    private static double longitude;
    private static double latitude;
    private static String lngString = String.valueOf(longitude);
    private static String latString = String.valueOf(latitude);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if( savedInstanceState != null ) {
            Toast.makeText(this, savedInstanceState .getString("message"), Toast.LENGTH_LONG).show();
        }

        // GPS Coordinates // send continuous updates //TODO: app crash after timer on gpsmanager runs out
/*
        gpsmanager.LocationResult locationResult = new gpsmanager.LocationResult(){
            @Override
            public void gotLocation(Location location){
                loc = location;
                latitude = loc.getLatitude();
                longitude = loc.getLongitude();
            }
        };

        gpsmanager mylocation = new gpsmanager();
        mylocation.getLocation(MainActivity.this, locationResult);

        // Translate coordinates into address

        Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
        try {
            List<Address> listAddresses = geocoder.getFromLocation(latitude, longitude, 1);
            if (null != listAddresses && listAddresses.size() > 0) {
                String LocationAdd = listAddresses.get(0).getAddressLine(0);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
/*
        Handler h = new Handler();
        h.postDelayed(new

                              Runnable() {
                                  @Override
                                  public void run() {
                                      //send coordinates every 10 sec
                                  }
                              }

                , 10000);
*/
        {
            // FRAGMENT MANAGER //
//TODO: Fragmentmanager doesnt crash anymore; tablet mode still doesnt work
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
        // OPTIONS MENU //
/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = new MenuInflater(this);
        inflater.inflate(R.menu.search, menu);
        return true;
    }
*/

        // TABHOST CONTROLLER //

        if ((getResources().getConfiguration().screenLayout &
                Configuration.SCREENLAYOUT_SIZE_MASK) ==
                Configuration.SCREENLAYOUT_SIZE_NORMAL) {
            // on a normal screen device ...

            mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
            mTabHost.setup(this, getSupportFragmentManager(), android.R.id.tabcontent);

            // TABS

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
    @Override
    public void onResume(){
        super.onResume();

    }

    @Override
    public void onPause(){
        super.onPause();
        /*gpsmanager mylocation = new gpsmanager();
        mylocation.cancelTimer();*/

    }

    @Override
    public void onStop(){
        super.onStop();


    }

    @Override
    public void onDestroy(){
        super.onDestroy();


    }

    // Menu on the right of ActionBar/TitleBar //
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }


    // Actionbar custom view //
/*
    {
        LayoutInflater inflater = (LayoutInflater) getActionBar().getThemedContext()
                .getSystemService(LAYOUT_INFLATER_SERVICE);

        final View customActionBarView = inflater.inflate(
                R.layout.actionbar_layout, null);

        // Show the custom action bar view and hide the normal Home icon and title
        final ActionBar actionBar = getActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setCustomView(customActionBarView);
        actionBar.setDisplayShowCustomEnabled(true);

    }
*/

    public void ptt (View v) {
        if (v.getId() == R.id.button61) {
            AlertDialog.Builder dlgBuilder = new AlertDialog.Builder(MainActivity.this);
            dlgBuilder.setMessage("implement manet ptt source code");
            dlgBuilder.setTitle("MANET PTT App");

                    dlgBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });

            AlertDialog alert = dlgBuilder.create();
            alert.show();

        }
    }

    //Call LS Button on deliverylocFragment//

    public void lscall(View v) {

        if (v.getId() == R.id.button17) {

            Button button17 = (Button) findViewById(R.id.button17);
            button17.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {

                    Intent callIntent = new Intent(Intent.ACTION_CALL);
                    callIntent.setData(Uri.parse("tel:" + R.string.lsbv));
                    startActivity(callIntent);

                    PackageManager pm = getPackageManager();
                    if (pm.checkPermission(Manifest.permission.CALL_PHONE, getPackageName()) == PackageManager.PERMISSION_GRANTED) {

                    } else {

                    }
                }
            });
        }

        if (v.getId() == R.id.button47) {

            Button button47 = (Button) findViewById(R.id.button47);
            button47.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {

                    Intent callIntent = new Intent(Intent.ACTION_CALL);
                    callIntent.setData(Uri.parse("tel:" + R.string.lsallg));
                    startActivity(callIntent);

                    PackageManager pm = getPackageManager();
                    if (pm.checkPermission(Manifest.permission.CALL_PHONE, getPackageName()) == PackageManager.PERMISSION_GRANTED) {

                    } else {

                    }

                }
            });
        }
    }

    // Alert Push Notification Manager //
    //TODO: later function for new incident alert !!! check again
    public void alertbtn(View v) {

        if (v.getId() == R.id.button22) {

            Button b22 = (Button) findViewById(R.id.button22);
            b22.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View arg5) {

                    final TextView bofield = (TextView) findViewById(R.id.bofield);
                    final TextView brfrfield = (TextView) findViewById(R.id.brfrfield);
                    final TextView infofield = (TextView) findViewById(R.id.infofield);

                    final Button button41 = (Button) findViewById(R.id.button41);
                    final TextView textView83 = (TextView) findViewById(R.id.textView83);
                    final TextView textView85 = (TextView) findViewById(R.id.textView85);

                    final Calendar cal = Calendar.getInstance(TimeZone.getDefault());
                    final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

                    AlertDialog.Builder dlgBuilder = new AlertDialog.Builder(MainActivity.this);
                    dlgBuilder.setTitle("EINSATZ");
                    dlgBuilder.setMessage("Addresse & Berufungsgrund");

                    dlgBuilder.setPositiveButton("Einsatz übernehmen", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            button41.setText(R.string.zbo);
                            textView83.setText("QU");
                            textView85.setText(sdf.format(cal.getTime()) );
                            button41.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_fast_forward_black_18dp, 0, 0, 0);

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
                            .setTicker("Alert")
                            .setContentTitle("Alert + Code")
                            .setContentText("AddressStreet")
                            .setDefaults(Notification.DEFAULT_LIGHTS | Notification.DEFAULT_SOUND)
                            .setContentIntent(contentIntent)
                            .setContentInfo("Detail Code");

                    // AlertSound
                    mBuilder.setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION));

                    NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                    notificationManager.notify(1, mBuilder.build());
                }
            });
        }
    }

/* outdated version
                NotificationManager notif = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                Notification notify = new Notification(R.drawable.ic_warning_black_18dp, title, System.currentTimeMillis());
                PendingIntent pending = PendingIntent.getActivity(getApplicationContext(), 0, new Intent(), 0);

                notify.setLatestEventInfo(getApplicationContext(), subject, body, pending);
                notif.notify(0, notify);
            }
        });

        */



    // Button state & color functions START //

    // Status EB NEB AD mainstatusFragment //

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

    // Status weiterschalten incidentFragment //

    public void stbtnClick(View v) {

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
            final TextView aofield = (TextView) findViewById(R.id.aofield);

            Button button10 = (Button) findViewById(R.id.button10);
            Button button11 = (Button) findViewById(R.id.button11);
            Button button13 = (Button) findViewById(R.id.button13);
            Button button46 = (Button) findViewById(R.id.button46);

            @Override
            public void onClick(DialogInterface dialog, int which) {

                if (textView83.getText().equals("") || textView83.getText().equals("EB")) {

                    button41.setEnabled(true);
                    button41.setClickable(false);
                    button41.setBackgroundColor(YELLOW);
                    button41.setText(R.string.zbo);
                    textView83.setText("QU");
                    textView85.setText(sdf.format(cal.getTime()) );
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

                    button41.setEnabled(true);
                    button41.setClickable(true);
                    button41.setBackgroundColor(YELLOW);
                    button41.setText(R.string.abo);
                    textView83.setText("ZBO");
                    textView85.setText(sdf.format(cal.getTime()) );
                    button41.setCompoundDrawablesWithIntrinsicBounds( R.drawable.ic_skip_next_black_18dp, 0, 0, 0);

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

                    button41.setEnabled(true);
                    button41.setClickable(true);
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

                } else if (textView83.getText().equals("ABO") /*&& (aofield.getText() != (""))*/) {

                    button41.setEnabled(true);
                    button41.setClickable(true);
                    button41.setBackgroundColor(YELLOW);
                    button41.setText(R.string.aao);
                    textView83.setText("ZAO");
                    textView85.setText(sdf.format(cal.getTime()) );
                    button41.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_local_hospital_black_18dp, 0, 0, 0);

                    button10.setEnabled(false);
                    button10.setClickable(false);
                    button11.setEnabled(false);
                    button11.setClickable(false);
                    button13.setEnabled(false);
                    button13.setClickable(false);
                    button46.setEnabled(false);
                    button46.setClickable(false);
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

                    button41.setEnabled(true);
                    button41.setClickable(true);
                    button41.setBackgroundColor(YELLOW);
                    button41.setText(R.string.eb);
                    textView83.setText("AAO");
                    textView85.setText(sdf.format(cal.getTime()) );
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

                    button41.setEnabled(true);
                    button41.setClickable(true);
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

    // Button state & color functions END

    // Patienten Management dialog builder //

    public void createpat(View v) {
        if (v.getId() == R.id.button46) {

            AlertDialog.Builder dlgBuilder = new AlertDialog.Builder(MainActivity.this);
            //dlgBuilder.setMessage("Patient anlegen");
            dlgBuilder.setTitle("PATADMIN");

            LayoutInflater inflater = (MainActivity.this.getLayoutInflater());

            dlgBuilder.setView(inflater.inflate(R.layout.patman, null))

                    .setPositiveButton("Senden", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                                //remove layout
                                View viewToRemove= findViewById(R.id.patmanrelayout);
                                if (viewToRemove != null && viewToRemove.getParent() != null && viewToRemove instanceof ViewGroup)
                                    ((ViewGroup) viewToRemove.getParent()).removeView(viewToRemove);

                                //send data

                                Toast.makeText(MainActivity.this, "Patient angelegt", Toast.LENGTH_SHORT).show();

                        }
                    });

            dlgBuilder.setNegativeButton("Zurück", new DialogInterface.OnClickListener()

                    {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            //remove layout
                            View viewToRemove= findViewById(R.id.patmanrelayout);
                            if (viewToRemove != null && viewToRemove.getParent() != null && viewToRemove instanceof ViewGroup)
                                ((ViewGroup) viewToRemove.getParent()).removeView(viewToRemove);
                        }
                    }

            );

            AlertDialog alert = dlgBuilder.create();
            alert.show();

        }
    }

    // Bett abbuchen btn //

    public void bettbuchen(View v) {

        LayoutInflater inflater = getLayoutInflater();
        getWindow().addContentView(inflater.inflate(R.layout.patman, null), new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));

        if (v.getId() == R.id.bettbtn) {

            AlertDialog.Builder dlgbuilder = new AlertDialog.Builder(MainActivity.this);
            dlgbuilder.setTitle("Abteilung auswählen");
            dlgbuilder.setItems(new CharSequence[]
                            {"Intern", "Unfall", "Chirurgie", "HNO", "Dermatologie", "Spezialbett", "andere Abteilung"},

                    new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int which) {

                            TextView abtedit = (TextView) findViewById(R.id.textView11);

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

    public void patmanstart (View v) {
        if (v.getId() == R.id.button21) {

            Button button21 = (Button) findViewById(R.id.button21);
            button21.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View arg0) {
                    Intent ipatman = new Intent(getApplicationContext(), patmanActivity.class);
                    startActivity(ipatman);
                }
            });

        }
    }

    // Emergency light yes/no //
    public void checkBox(View v) {
        if (v.getId() == R.id.checkBox) {
            CheckBox checkBox = (CheckBox) findViewById(R.id.checkBox);
            checkBox.setEnabled(true);
            checkBox.setClickable(false);
            checkBox.setHintTextColor(BLUE);
        }
    }

    public void navigate(View v) {

        if (v.getId() == R.id.button18) {

            Button button18 = (Button) findViewById(R.id.button18);
            button18.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {

                    TextView text = (TextView)findViewById(R.id.bofield);
                    String navadress = "google.navigation:" + text.getText().toString();
                    Intent nav = new Intent(android.content.Intent.ACTION_VIEW);
                    nav.setData(Uri.parse(navadress));
                    startActivity(nav);
                }
            });
        }

        if (v.getId() == R.id.button19) {

            Button button19 = (Button) findViewById(R.id.button19);
            button19.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {

                    TextView text = (TextView) findViewById(R.id.aofield);
                    String navadress = "google.navigation:" + text.getText().toString();
                    Intent nav = new Intent(android.content.Intent.ACTION_VIEW);
                    nav.setData(Uri.parse(navadress));
                    startActivity(nav);
                }
            });
        }

    }

    public void showmap (View v) {
        if (v.getId() == R.id.button30) {

            WebView gisView = (WebView) findViewById(R.id.gisView);

            gisView.getSettings().setJavaScriptEnabled(true);
            gisView.getSettings().getAllowFileAccessFromFileURLs();
            gisView.getSettings().setAllowUniversalAccessFromFileURLs(true);

            gisView.loadUrl("file:///android_asset/leaflet.html");
        }
    }

    public void st14(View v) {

        LayoutInflater inflater = getLayoutInflater();
        getWindow().addContentView(inflater.inflate(R.layout.reportincident, null), new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));

        if (v.getId() == R.id.button42) {

            final EditText editText24 = (EditText) findViewById(R.id.editText24);
            final TextView textView86 = (TextView) findViewById(R.id.textView86);
            final TextView textView93 = (TextView) findViewById(R.id.textView93);
            final Button button42 = (Button) findViewById(R.id.button42);

            AlertDialog.Builder dlgBuilder = new AlertDialog.Builder(MainActivity.this);
            dlgBuilder.setMessage("Neuen Einsatz bei derzeitiger Position melden?");
            dlgBuilder.setCancelable(false);

            dlgBuilder.setView(inflater.inflate(R.layout.reportincident, null));
//TODO: GPS Coordinates working but not shown in custom layout. Geocoder not working??
            gpsmanager.LocationResult locationResult = new gpsmanager.LocationResult(){
                @Override
                public void gotLocation(Location location){
                    loc = location;
                    latitude = loc.getLatitude();
                    longitude = loc.getLongitude();
                }
            };

            gpsmanager mylocation = new gpsmanager();
            mylocation.getLocation(MainActivity.this, locationResult);

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

            editText24.setText(result);
            textView86.setText("" + latitude);
            textView93.setText("" + longitude);

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
}




