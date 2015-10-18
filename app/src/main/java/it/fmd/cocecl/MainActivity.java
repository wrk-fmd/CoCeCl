package it.fmd.cocecl;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import static android.graphics.Color.BLUE;
import static android.graphics.Color.GREEN;
import static android.graphics.Color.RED;


public class MainActivity extends FragmentActivity {

    private FragmentTabHost mTabHost;

    private WebView gisView;
    private TextView latituteField;
    private TextView longitudeField;
    private LocationManager locationManager;
    private String provider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
/*
        {
            // FRAGMENT MANAGER //

            Fragment statusFrag = new statusFragment();
            Fragment fielddataFrag = new fielddataFragment();

            android.support.v4.app.FragmentManager fm = getSupportFragmentManager();

            //getSupportFragmentManager().findFragmentById(R.id.fragment_status);


            // Transaction start
            android.support.v4.app.FragmentTransaction ft = fm.beginTransaction();

            ft.add(R.id.framelayout_1, statusFrag);

            if (findViewById(R.id.framelayout_2) != null) {

                ft.add(R.id.framelayout_2, fielddataFrag);
            }

            // Transaction commit
            ft.commit();
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
        // GPS CONTROLLER //
        // Location Manager //
/*
        LocationManager service = (LocationManager) getSystemService(LOCATION_SERVICE);
        boolean enabled = service
                .isProviderEnabled(LocationManager.GPS_PROVIDER);

        if (!enabled) {
            Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivity(intent);
        }

        latituteField = (TextView) findViewById(R.id.TextView02);
        longitudeField = (TextView) findViewById(R.id.TextView04);

        // Get the location manager
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        // Define the criteria how to select the locatioin provider -> use
        // default
        Criteria criteria = new Criteria();
        provider = locationManager.getBestProvider(criteria, false);
        Location location = locationManager.getLastKnownLocation(provider);

        // Initialize the location fields
        if (location != null) {
            System.out.println("Provider " + provider + " has been selected.");
            onLocationChanged(location);
        } else {
            latituteField.setText("Location not available");
            longitudeField.setText("Location not available");
        }


    // Request updates at startup //
    @Override
    protected void onResume() {
        super.onResume();
        locationManager.requestLocationUpdates(provider, 400, 1, this);
    }

    // Remove the locationlistener updates when Activity is paused //
    @Override
    protected void onPause() {
        super.onPause();
        locationManager.removeUpdates(this);
    }

    @Override
    public void onLocationChanged(Location location) {
        int lat = (int) (location.getLatitude());
        int lng = (int) (location.getLongitude());
        latituteField.setText(String.valueOf(lat));
        longitudeField.setText(String.valueOf(lng));
    }

    @Override
    public void onStatusChanged(String provider, int statusFragment, Bundle extras) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onProviderEnabled(String provider) {
        Toast.makeText(this, "Enabled new provider " + provider,
                Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onProviderDisabled(String provider) {
        Toast.makeText(this, "Disabled provider " + provider,
                Toast.LENGTH_SHORT).show();
    }
**/
        // GPS END //

        // PHONE STATE MANAGER //

        {
            //PhoneStateListener//
            PhoneCallListener phoneListener = new PhoneCallListener();
            TelephonyManager telephonyManager = (TelephonyManager) this
                    .getSystemService(Context.TELEPHONY_SERVICE);
            telephonyManager.listen(phoneListener, PhoneStateListener.LISTEN_CALL_STATE);
        }


        // TABHOST CONTROLLER //

        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), android.R.id.tabcontent);

        mTabHost.addTab(
                mTabHost.newTabSpec("tab1").setIndicator("Status", null),
                statusFragment.class, null);
        mTabHost.addTab(
                mTabHost.newTabSpec("tab2").setIndicator("Einsatzdaten", null),
                fielddataFragment.class, null);
        mTabHost.addTab(
                mTabHost.newTabSpec("tab3").setIndicator("Abgabeort", null),
                deliverylocFragment.class, null);
        mTabHost.addTab(
                mTabHost.newTabSpec("tab4").setIndicator("Karte", null),
                mapFragment.class, null);
        // Funk Telefonbuch Einheiten//
       /** mTabHost.addTab(
                mTabHost.newTabSpec("tab5").setIndicator("Kommunikation", null),
                communication.class, null);
        */

    }

    // Menu on the right of ActionBar/TitleBar //
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    // Actionbar custom view //
/**
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
**/
    //action bar version2
/**
    public void addTextToActionBar( String textToSet ) {

        mActionbar.setDisplayShowCustomEnabled( true );

        // Inflate the custom view
        LayoutInflater inflater = LayoutInflater.from( this );
        View header = inflater.inflate( R.layout.actionbar_layout, null );

        //Here do whatever you need to do with the view (set text if it's a textview or whatever)
        TextView tv = (TextView) header.findViewById( R.id.program_title );
        tv.setText( textToSet );

        // Magic happens to center it.
        int actionBarWidth = DeviceHelper.getDeviceWidth( this ); //Google for this method. Kinda easy.

        tv.measure( 0, 0 );
        int tvSize = tv.getMeasuredWidth();

        try
        {
            int leftSpace = 0;

            View homeButton = findViewById( android.R.id.home );
            final ViewGroup holder = (ViewGroup) homeButton.getParent();

            View firstChild =  holder.getChildAt( 0 );
            View secondChild =  holder.getChildAt( 1 );

            leftSpace = firstChild.getWidth()+secondChild.getWidth();
        }
        catch ( Exception ignored )

        {}

        mActionbar.setCustomView( header );

        if ( null != header )
        {
            ActionBar.LayoutParams params = (ActionBar.LayoutParams) header.getLayoutParams();

            if ( null != params )
            {
                int leftMargin =  ( actionBarWidth / 2 - ( leftSpace ) ) - ( tvSize / 2 ) ;

                params.leftMargin = 0 >= leftMargin ? 0 : leftMargin;
            }
        }
    }

**/
    // PHONE CALL MANAGER //
    //monitor phone call activities
    private class PhoneCallListener extends PhoneStateListener {

        private boolean isPhoneCalling = false;

        String LOG_TAG = "LOGGING 123";

        @Override
        public void onCallStateChanged(int state, String incomingNumber) {

            if (TelephonyManager.CALL_STATE_RINGING == state) {
                // phone ringing
                Log.i(LOG_TAG, "RINGING, number: " + incomingNumber);
            }

            if (TelephonyManager.CALL_STATE_OFFHOOK == state) {
                // active
                Log.i(LOG_TAG, "OFFHOOK");

                isPhoneCalling = true;
            }

            if (TelephonyManager.CALL_STATE_IDLE == state) {
                // run when class initial and phone call ended,
                // need detect flag from CALL_STATE_OFFHOOK
                Log.i(LOG_TAG, "IDLE");

                if (isPhoneCalling) {

                    Log.i(LOG_TAG, "restart app");

                    // restart app
                    /**
                    Intent restartph = getBaseContext().getPackageManager()
                            .getLaunchIntentForPackage(
                                    getBaseContext().getPackageName());
                    restartph.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(restartph);
*/
                    isPhoneCalling = false;
                }
            }
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
                    callIntent.setData(Uri.parse("tel:"));

                    startActivity(callIntent);
                }
            });

        }
    }

    // Alert Push Notification Manager //
/**
    public void onClick(View v) {
        String tittle=ed1.getText().toString().trim();
        String subject=ed2.getText().toString().trim();
        String body=ed3.getText().toString().trim();

        NotificationManager notif=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notify=new Notification(R.drawable.noti,tittle,System.currentTimeMillis());
        PendingIntent pending= PendingIntent.getActivity(getApplicationContext(), 0, new Intent(), 0);

        notify.setLatestEventInfo(getApplicationContext(),subject,body,pending);
        notif.notify(0, notify);
    }
});
*/

    // Button state & color functions START //

    public void st1(View v) {
        if (v.getId() == R.id.button) {
            Button button = (Button) findViewById(R.id.button);
            button.setEnabled(true);
            button.setClickable(false);
            button.setBackgroundColor(GREEN);

            Button button2 = (Button) findViewById(R.id.button2);
            button2.setEnabled(true);
            button2.setClickable(true);
            button2.setBackgroundResource(android.R.drawable.btn_default);

            Button button3 = (Button) findViewById(R.id.button3);
            button3.setEnabled(true);
            button3.setClickable(true);
            button3.setBackgroundResource(android.R.drawable.btn_default);

            Button button4 = (Button) findViewById(R.id.button4);
            button4.setEnabled(false);
            button4.setClickable(false);
            button4.setBackgroundResource(android.R.drawable.btn_default);

            //Button button5 = (Button) findViewById(R.id.button5);
            //button5.setEnabled(true);
            //button5.setClickable(true);
            //button5.setBackgroundResource(android.R.drawable.btn_default);

            Button button6 = (Button) findViewById(R.id.button6);
            button6.setEnabled(false);
            button6.setClickable(false);
            button6.setBackgroundResource(android.R.drawable.btn_default);

            Button button7 = (Button) findViewById(R.id.button7);
            button7.setEnabled(false);
            button7.setClickable(false);
            button7.setBackgroundResource(android.R.drawable.btn_default);

            Button button8 = (Button) findViewById(R.id.button8);
            button8.setEnabled(false);
            button8.setClickable(false);
            button8.setBackgroundResource(android.R.drawable.btn_default);

            Button button9 = (Button) findViewById(R.id.button9);
            button9.setEnabled(false);
            button9.setClickable(false);
            button9.setBackgroundResource(android.R.drawable.btn_default);

            Button button10 = (Button) findViewById(R.id.button10);
            button10.setEnabled(false);
            button10.setClickable(false);
            button10.setBackgroundResource(android.R.drawable.btn_default);

            Button button11 = (Button) findViewById(R.id.button11);
            button11.setEnabled(false);
            button11.setClickable(false);
            button11.setBackgroundResource(android.R.drawable.btn_default);

            //Button button12 = (Button) findViewById(R.id.button12);
            //button12.setEnabled(true);
            //button12.setClickable(true);
            //button12.setBackgroundResource(android.R.drawable.btn_default);

            Button button13 = (Button) findViewById(R.id.button13);
            button13.setEnabled(false);
            button13.setClickable(false);
            button13.setBackgroundResource(android.R.drawable.btn_default);
        }
    }

    public void st2(View v) {
        if (v.getId() == R.id.button2) {
            Button button = (Button) findViewById(R.id.button);
            button.setEnabled(true);
            button.setClickable(true);
            button.setBackgroundResource(android.R.drawable.btn_default);

            Button button2 = (Button) findViewById(R.id.button2);
            button2.setEnabled(true);
            button2.setClickable(false);
            button2.setBackgroundColor(GREEN);

            Button button3 = (Button) findViewById(R.id.button3);
            button3.setEnabled(true);
            button3.setClickable(true);
            button3.setBackgroundResource(android.R.drawable.btn_default);

            Button button4 = (Button) findViewById(R.id.button4);
            button4.setEnabled(false);
            button4.setClickable(false);
            button4.setBackgroundResource(android.R.drawable.btn_default);

            //Button button5 = (Button) findViewById(R.id.button5);
            //button5.setEnabled(true);
            //button5.setClickable(true);
            //button5.setBackgroundResource(android.R.drawable.btn_default);

            Button button6 = (Button) findViewById(R.id.button6);
            button6.setEnabled(true);
            button6.setClickable(true);
            button6.setBackgroundResource(android.R.drawable.btn_default);

            Button button7 = (Button) findViewById(R.id.button7);
            button7.setEnabled(false);
            button7.setClickable(false);
            button7.setBackgroundResource(android.R.drawable.btn_default);

            Button button8 = (Button) findViewById(R.id.button8);
            button8.setEnabled(false);
            button8.setClickable(false);
            button8.setBackgroundResource(android.R.drawable.btn_default);

            Button button9 = (Button) findViewById(R.id.button9);
            button9.setEnabled(true);
            button9.setClickable(true);
            button9.setBackgroundResource(android.R.drawable.btn_default);

            Button button10 = (Button) findViewById(R.id.button10);
            button10.setEnabled(false);
            button10.setClickable(false);
            button10.setBackgroundResource(android.R.drawable.btn_default);

            Button button11 = (Button) findViewById(R.id.button11);
            button11.setEnabled(false);
            button11.setClickable(false);
            button11.setBackgroundResource(android.R.drawable.btn_default);

            //Button button12 = (Button) findViewById(R.id.button12);
            //button12.setEnabled(true);
            //button12.setClickable(true);
            //button12.setBackgroundResource(android.R.drawable.btn_default);

            Button button13 = (Button) findViewById(R.id.button13);
            button13.setEnabled(false);
            button13.setClickable(false);
            button13.setBackgroundResource(android.R.drawable.btn_default);
        }
    }

    public void st3(View v) {
        if (v.getId() == R.id.button3) {
            Button button = (Button) findViewById(R.id.button);
            button.setEnabled(true);
            button.setClickable(false);
            button.setBackgroundResource(android.R.drawable.btn_default);

            Button button2 = (Button) findViewById(R.id.button2);
            button2.setEnabled(false);
            button2.setClickable(false);
            button2.setBackgroundResource(android.R.drawable.btn_default);

            Button button3 = (Button) findViewById(R.id.button3);
            button3.setEnabled(true);
            button3.setClickable(false);
            button3.setBackgroundColor(GREEN);

            Button button4 = (Button) findViewById(R.id.button4);
            button4.setEnabled(true);
            button4.setClickable(true);
            button4.setBackgroundResource(android.R.drawable.btn_default);

            //Button button5 = (Button) findViewById(R.id.button5);
            //button5.setEnabled(true);
            //button5.setClickable(true);
            //button5.setBackgroundResource(android.R.drawable.btn_default);

            Button button6 = (Button) findViewById(R.id.button6);
            button6.setEnabled(false);
            button6.setClickable(false);
            button6.setBackgroundResource(android.R.drawable.btn_default);

            Button button7 = (Button) findViewById(R.id.button7);
            button7.setEnabled(false);
            button7.setClickable(false);
            button7.setBackgroundResource(android.R.drawable.btn_default);

            Button button8 = (Button) findViewById(R.id.button8);
            button8.setEnabled(false);
            button8.setClickable(false);
            button8.setBackgroundResource(android.R.drawable.btn_default);

            Button button9 = (Button) findViewById(R.id.button9);
            button9.setEnabled(false);
            button9.setClickable(false);
            button9.setBackgroundResource(android.R.drawable.btn_default);

            Button button10 = (Button) findViewById(R.id.button10);
            button10.setEnabled(false);
            button10.setClickable(false);
            button10.setBackgroundResource(android.R.drawable.btn_default);

            Button button11 = (Button) findViewById(R.id.button11);
            button11.setEnabled(false);
            button11.setClickable(false);
            button11.setBackgroundResource(android.R.drawable.btn_default);

            //Button button12 = (Button) findViewById(R.id.button12);
            //button12.setEnabled(true);
            //button12.setClickable(true);
            //button12.setBackgroundResource(android.R.drawable.btn_default);

            Button button13 = (Button) findViewById(R.id.button13);
            button13.setEnabled(false);
            button13.setClickable(false);
            button13.setBackgroundResource(android.R.drawable.btn_default);
        }
    }

    public void st4(View v) {
        if (v.getId() == R.id.button4) {
            Button button = (Button) findViewById(R.id.button);
            button.setEnabled(false);
            button.setClickable(false);
            button.setBackgroundResource(android.R.drawable.btn_default);

            Button button2 = (Button) findViewById(R.id.button2);
            button2.setEnabled(false);
            button2.setClickable(false);
            button2.setBackgroundResource(android.R.drawable.btn_default);

            Button button3 = (Button) findViewById(R.id.button3);
            button3.setEnabled(false);
            button3.setClickable(false);
            button3.setBackgroundResource(android.R.drawable.btn_default);

            Button button4 = (Button) findViewById(R.id.button4);
            button4.setEnabled(true);
            button4.setClickable(false);
            button4.setBackgroundColor(GREEN);

            //Button button5 = (Button) findViewById(R.id.button5);
            //button5.setEnabled(true);
            //button5.setClickable(true);
            //button5.setBackgroundResource(android.R.drawable.btn_default);

            Button button6 = (Button) findViewById(R.id.button6);
            button6.setEnabled(false);
            button6.setClickable(false);
            button6.setBackgroundResource(android.R.drawable.btn_default);

            Button button7 = (Button) findViewById(R.id.button7);
            button7.setEnabled(true);
            button7.setClickable(true);
            button7.setBackgroundResource(android.R.drawable.btn_default);

            Button button8 = (Button) findViewById(R.id.button8);
            button8.setEnabled(false);
            button8.setClickable(false);
            button8.setBackgroundResource(android.R.drawable.btn_default);

            Button button9 = (Button) findViewById(R.id.button9);
            button9.setEnabled(false);
            button9.setClickable(false);
            button9.setBackgroundResource(android.R.drawable.btn_default);

            Button button10 = (Button) findViewById(R.id.button10);
            button10.setEnabled(true);
            button10.setClickable(true);
            button10.setBackgroundResource(android.R.drawable.btn_default);

            Button button11 = (Button) findViewById(R.id.button11);
            button11.setEnabled(true);
            button11.setClickable(true);
            button11.setBackgroundResource(android.R.drawable.btn_default);

            //Button button12 = (Button) findViewById(R.id.button12);
            //button12.setEnabled(true);
            //button12.setClickable(true);
            //button12.setBackgroundResource(android.R.drawable.btn_default);

            Button button13 = (Button) findViewById(R.id.button13);
            button13.setEnabled(true);
            button13.setClickable(true);
            button13.setBackgroundResource(android.R.drawable.btn_default);
        }
    }

    public void st5(View v) {
        if (v.getId() == R.id.button5) {

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
        }
    }

    public void st6(View v) {
        if (v.getId() == R.id.button6) {

            AlertDialog.Builder dlgbuilder = new AlertDialog.Builder(MainActivity.this);
            dlgbuilder.setTitle("Einheit nicht einsatzbereit melden?");
            dlgbuilder.setItems(new CharSequence[]
                            {"NEB (andere Grund)", "Tanken", "Material nachfassen", "Bereitschaft", "Nein"},

                    new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int which) {

                            switch (which) {
                                case 0:
                                    Button button = (Button) findViewById(R.id.button);
                                    button.setEnabled(true);
                                    button.setClickable(true);
                                    button.setBackgroundResource(android.R.drawable.btn_default);

                                    Button button2 = (Button) findViewById(R.id.button2);
                                    button2.setEnabled(true);
                                    button2.setClickable(true);
                                    button2.setBackgroundResource(android.R.drawable.btn_default);

                                    Button button3 = (Button) findViewById(R.id.button3);
                                    button3.setEnabled(false);
                                    button3.setClickable(false);
                                    button3.setBackgroundResource(android.R.drawable.btn_default);

                                    Button button4 = (Button) findViewById(R.id.button4);
                                    button4.setEnabled(false);
                                    button4.setClickable(false);
                                    button4.setBackgroundResource(android.R.drawable.btn_default);

                                    //Button button5 = (Button) findViewById(R.id.button5);
                                    //button5.setEnabled(true);
                                    //button5.setClickable(true);
                                    //button5.setBackgroundResource(android.R.drawable.btn_default);

                                    Button button6 = (Button) findViewById(R.id.button6);
                                    button6.setEnabled(true);
                                    button6.setClickable(false);
                                    button6.setBackgroundColor(Color.parseColor("#9C27B0"));

                                    Button button7 = (Button) findViewById(R.id.button7);
                                    button7.setEnabled(false);
                                    button7.setClickable(false);
                                    button7.setBackgroundResource(android.R.drawable.btn_default);

                                    Button button8 = (Button) findViewById(R.id.button8);
                                    button8.setEnabled(false);
                                    button8.setClickable(false);
                                    button8.setBackgroundResource(android.R.drawable.btn_default);

                                    Button button9 = (Button) findViewById(R.id.button9);
                                    button9.setEnabled(true);
                                    button9.setClickable(true);
                                    button9.setBackgroundResource(android.R.drawable.btn_default);

                                    Button button10 = (Button) findViewById(R.id.button10);
                                    button10.setEnabled(false);
                                    button10.setClickable(false);
                                    button10.setBackgroundResource(android.R.drawable.btn_default);

                                    Button button11 = (Button) findViewById(R.id.button11);
                                    button11.setEnabled(false);
                                    button11.setClickable(false);
                                    button11.setBackgroundResource(android.R.drawable.btn_default);

                                    //Button button12 = (Button) findViewById(R.id.button12);
                                    //button12.setEnabled(true);
                                    //button12.setClickable(true);
                                    //button12.setBackgroundResource(android.R.drawable.btn_default);

                                    Button button13 = (Button) findViewById(R.id.button13);
                                    button13.setEnabled(false);
                                    button13.setClickable(false);
                                    button13.setBackgroundResource(android.R.drawable.btn_default);

                                    Toast.makeText(MainActivity.this, "Nicht EB", Toast.LENGTH_SHORT).show();
                                    break;
                                case 1:
                                    button = (Button) findViewById(R.id.button);
                                    button.setEnabled(true);
                                    button.setClickable(true);
                                    button.setBackgroundResource(android.R.drawable.btn_default);

                                    button2 = (Button) findViewById(R.id.button2);
                                    button2.setEnabled(true);
                                    button2.setClickable(true);
                                    button2.setBackgroundResource(android.R.drawable.btn_default);

                                    button3 = (Button) findViewById(R.id.button3);
                                    button3.setEnabled(false);
                                    button3.setClickable(false);
                                    button3.setBackgroundResource(android.R.drawable.btn_default);

                                    button4 = (Button) findViewById(R.id.button4);
                                    button4.setEnabled(false);
                                    button4.setClickable(false);
                                    button4.setBackgroundResource(android.R.drawable.btn_default);

                                    //Button button5 = (Button) findViewById(R.id.button5);
                                    //button5.setEnabled(true);
                                    //button5.setClickable(true);
                                    //button5.setBackgroundResource(android.R.drawable.btn_default);

                                    button6 = (Button) findViewById(R.id.button6);
                                    button6.setEnabled(true);
                                    button6.setClickable(false);
                                    button6.setBackgroundColor(Color.parseColor("#9C27B0"));

                                    button7 = (Button) findViewById(R.id.button7);
                                    button7.setEnabled(false);
                                    button7.setClickable(false);
                                    button7.setBackgroundResource(android.R.drawable.btn_default);

                                    button8 = (Button) findViewById(R.id.button8);
                                    button8.setEnabled(false);
                                    button8.setClickable(false);
                                    button8.setBackgroundResource(android.R.drawable.btn_default);

                                    button9 = (Button) findViewById(R.id.button9);
                                    button9.setEnabled(true);
                                    button9.setClickable(true);
                                    button9.setBackgroundResource(android.R.drawable.btn_default);

                                    button10 = (Button) findViewById(R.id.button10);
                                    button10.setEnabled(false);
                                    button10.setClickable(false);
                                    button10.setBackgroundResource(android.R.drawable.btn_default);

                                    button11 = (Button) findViewById(R.id.button11);
                                    button11.setEnabled(false);
                                    button11.setClickable(false);
                                    button11.setBackgroundResource(android.R.drawable.btn_default);

                                    //button12 = (Button) findViewById(R.id.button12);
                                    //button12.setEnabled(true);
                                    //button12.setClickable(true);
                                    //button12.setBackgroundResource(android.R.drawable.btn_default);

                                    button13 = (Button) findViewById(R.id.button13);
                                    button13.setEnabled(false);
                                    button13.setClickable(false);
                                    button13.setBackgroundResource(android.R.drawable.btn_default);

                                    Toast.makeText(MainActivity.this, "Tanken", Toast.LENGTH_SHORT).show();
                                    break;
                                case 2:
                                    button = (Button) findViewById(R.id.button);
                                    button.setEnabled(true);
                                    button.setClickable(true);
                                    button.setBackgroundResource(android.R.drawable.btn_default);

                                    button2 = (Button) findViewById(R.id.button2);
                                    button2.setEnabled(true);
                                    button2.setClickable(true);
                                    button2.setBackgroundResource(android.R.drawable.btn_default);

                                    button3 = (Button) findViewById(R.id.button3);
                                    button3.setEnabled(false);
                                    button3.setClickable(false);
                                    button3.setBackgroundResource(android.R.drawable.btn_default);

                                    button4 = (Button) findViewById(R.id.button4);
                                    button4.setEnabled(false);
                                    button4.setClickable(false);
                                    button4.setBackgroundResource(android.R.drawable.btn_default);

                                    //Button button5 = (Button) findViewById(R.id.button5);
                                    //button5.setEnabled(true);
                                    //button5.setClickable(true);
                                    //button5.setBackgroundResource(android.R.drawable.btn_default);

                                    button6 = (Button) findViewById(R.id.button6);
                                    button6.setEnabled(true);
                                    button6.setClickable(false);
                                    button6.setBackgroundColor(Color.parseColor("#9C27B0"));

                                    button7 = (Button) findViewById(R.id.button7);
                                    button7.setEnabled(false);
                                    button7.setClickable(false);
                                    button7.setBackgroundResource(android.R.drawable.btn_default);

                                    button8 = (Button) findViewById(R.id.button8);
                                    button8.setEnabled(false);
                                    button8.setClickable(false);
                                    button8.setBackgroundResource(android.R.drawable.btn_default);

                                    button9 = (Button) findViewById(R.id.button9);
                                    button9.setEnabled(true);
                                    button9.setClickable(true);
                                    button9.setBackgroundResource(android.R.drawable.btn_default);

                                    button10 = (Button) findViewById(R.id.button10);
                                    button10.setEnabled(false);
                                    button10.setClickable(false);
                                    button10.setBackgroundResource(android.R.drawable.btn_default);

                                    button11 = (Button) findViewById(R.id.button11);
                                    button11.setEnabled(false);
                                    button11.setClickable(false);
                                    button11.setBackgroundResource(android.R.drawable.btn_default);

                                    //button12 = (Button) findViewById(R.id.button12);
                                    //button12.setEnabled(true);
                                    //button12.setClickable(true);
                                    //button12.setBackgroundResource(android.R.drawable.btn_default);

                                    button13 = (Button) findViewById(R.id.button13);
                                    button13.setEnabled(false);
                                    button13.setClickable(false);
                                    button13.setBackgroundResource(android.R.drawable.btn_default);

                                    Toast.makeText(MainActivity.this, "Mat. nachfassen", Toast.LENGTH_SHORT).show();
                                    break;
                                case 3:
                                    button = (Button) findViewById(R.id.button);
                                    button.setEnabled(true);
                                    button.setClickable(true);
                                    button.setBackgroundResource(android.R.drawable.btn_default);

                                    button2 = (Button) findViewById(R.id.button2);
                                    button2.setEnabled(true);
                                    button2.setClickable(true);
                                    button2.setBackgroundResource(android.R.drawable.btn_default);

                                    button3 = (Button) findViewById(R.id.button3);
                                    button3.setEnabled(false);
                                    button3.setClickable(false);
                                    button3.setBackgroundResource(android.R.drawable.btn_default);

                                    button4 = (Button) findViewById(R.id.button4);
                                    button4.setEnabled(false);
                                    button4.setClickable(false);
                                    button4.setBackgroundResource(android.R.drawable.btn_default);

                                    //Button button5 = (Button) findViewById(R.id.button5);
                                    //button5.setEnabled(true);
                                    //button5.setClickable(true);
                                    //button5.setBackgroundResource(android.R.drawable.btn_default);

                                    button6 = (Button) findViewById(R.id.button6);
                                    button6.setEnabled(true);
                                    button6.setClickable(false);
                                    button6.setBackgroundColor(Color.parseColor("#9C27B0"));

                                    button7 = (Button) findViewById(R.id.button7);
                                    button7.setEnabled(false);
                                    button7.setClickable(false);
                                    button7.setBackgroundResource(android.R.drawable.btn_default);

                                    button8 = (Button) findViewById(R.id.button8);
                                    button8.setEnabled(false);
                                    button8.setClickable(false);
                                    button8.setBackgroundResource(android.R.drawable.btn_default);

                                    button9 = (Button) findViewById(R.id.button9);
                                    button9.setEnabled(true);
                                    button9.setClickable(true);
                                    button9.setBackgroundResource(android.R.drawable.btn_default);

                                    button10 = (Button) findViewById(R.id.button10);
                                    button10.setEnabled(false);
                                    button10.setClickable(false);
                                    button10.setBackgroundResource(android.R.drawable.btn_default);

                                    button11 = (Button) findViewById(R.id.button11);
                                    button11.setEnabled(false);
                                    button11.setClickable(false);
                                    button11.setBackgroundResource(android.R.drawable.btn_default);

                                    //button12 = (Button) findViewById(R.id.button12);
                                    //button12.setEnabled(true);
                                    //button12.setClickable(true);
                                    //button12.setBackgroundResource(android.R.drawable.btn_default);

                                    button13 = (Button) findViewById(R.id.button13);
                                    button13.setEnabled(false);
                                    button13.setClickable(false);
                                    button13.setBackgroundResource(android.R.drawable.btn_default);

                                    Toast.makeText(MainActivity.this, "Bereitschaft", Toast.LENGTH_SHORT).show();
                                    break;
                                case 4:
                                    Toast.makeText(MainActivity.this, "weiter EB", Toast.LENGTH_SHORT).show();
                                    break;
                            }
                        }
                    });

            dlgbuilder.create().show();

        }
    }

    public void st7(View v) {
        if (v.getId() == R.id.button7) {
            Button button = (Button) findViewById(R.id.button);
            button.setEnabled(false);
            button.setClickable(false);
            button.setBackgroundResource(android.R.drawable.btn_default);

            Button button2 = (Button) findViewById(R.id.button2);
            button2.setEnabled(false);
            button2.setClickable(false);
            button2.setBackgroundResource(android.R.drawable.btn_default);

            Button button3 = (Button) findViewById(R.id.button3);
            button3.setEnabled(false);
            button3.setClickable(false);
            button3.setBackgroundResource(android.R.drawable.btn_default);

            Button button4 = (Button) findViewById(R.id.button4);
            button4.setEnabled(false);
            button4.setClickable(false);
            button4.setBackgroundResource(android.R.drawable.btn_default);

            //Button button5 = (Button) findViewById(R.id.button5);
            //button5.setEnabled(true);
            //button5.setClickable(true);
            //button5.setBackgroundResource(android.R.drawable.btn_default);

            Button button6 = (Button) findViewById(R.id.button6);
            button6.setEnabled(false);
            button6.setClickable(false);
            button6.setBackgroundResource(android.R.drawable.btn_default);

            Button button7 = (Button) findViewById(R.id.button7);
            button7.setEnabled(true);
            button7.setClickable(false);
            button7.setBackgroundColor(GREEN);

            Button button8 = (Button) findViewById(R.id.button8);
            button8.setEnabled(true);
            button8.setClickable(true);
            button8.setBackgroundResource(android.R.drawable.btn_default);

            Button button9 = (Button) findViewById(R.id.button9);
            button9.setEnabled(false);
            button9.setClickable(false);
            button9.setBackgroundResource(android.R.drawable.btn_default);

            Button button10 = (Button) findViewById(R.id.button10);
            button10.setEnabled(false);
            button10.setClickable(false);
            button10.setBackgroundResource(android.R.drawable.btn_default);

            Button button11 = (Button) findViewById(R.id.button11);
            button11.setEnabled(false);
            button11.setClickable(false);
            button11.setBackgroundResource(android.R.drawable.btn_default);

            //Button button12 = (Button) findViewById(R.id.button12);
            //button12.setEnabled(true);
            //button12.setClickable(true);
            //button12.setBackgroundResource(android.R.drawable.btn_default);

            Button button13 = (Button) findViewById(R.id.button13);
            button13.setEnabled(false);
            button13.setClickable(false);
            button13.setBackgroundResource(android.R.drawable.btn_default);
        }
    }

    public void st8(View v) {
        if (v.getId() == R.id.button8) {
            Button button = (Button) findViewById(R.id.button);
            button.setEnabled(true);
            button.setClickable(true);
            button.setBackgroundResource(android.R.drawable.btn_default);

            Button button2 = (Button) findViewById(R.id.button2);
            button2.setEnabled(false);
            button2.setClickable(false);
            button2.setBackgroundResource(android.R.drawable.btn_default);

            Button button3 = (Button) findViewById(R.id.button3);
            button3.setEnabled(false);
            button3.setClickable(true);
            button3.setBackgroundResource(android.R.drawable.btn_default);

            Button button4 = (Button) findViewById(R.id.button4);
            button4.setEnabled(false);
            button4.setClickable(false);
            button4.setBackgroundResource(android.R.drawable.btn_default);

            //Button button5 = (Button) findViewById(R.id.button5);
            //button5.setEnabled(true);
            //button5.setClickable(true);
            //button5.setBackgroundResource(android.R.drawable.btn_default);

            Button button6 = (Button) findViewById(R.id.button6);
            button6.setEnabled(false);
            button6.setClickable(false);
            button6.setBackgroundResource(android.R.drawable.btn_default);

            Button button7 = (Button) findViewById(R.id.button7);
            button7.setEnabled(false);
            button7.setClickable(false);
            button7.setBackgroundResource(android.R.drawable.btn_default);

            Button button8 = (Button) findViewById(R.id.button8);
            button8.setEnabled(true);
            button8.setClickable(false);
            button8.setBackgroundColor(GREEN);

            Button button9 = (Button) findViewById(R.id.button9);
            button9.setEnabled(false);
            button9.setClickable(false);
            button9.setBackgroundResource(android.R.drawable.btn_default);

            Button button10 = (Button) findViewById(R.id.button10);
            button10.setEnabled(false);
            button10.setClickable(false);
            button10.setBackgroundResource(android.R.drawable.btn_default);

            Button button11 = (Button) findViewById(R.id.button11);
            button11.setEnabled(false);
            button11.setClickable(false);
            button11.setBackgroundResource(android.R.drawable.btn_default);

            //Button button12 = (Button) findViewById(R.id.button12);
            //button12.setEnabled(true);
            //button12.setClickable(true);
            //button12.setBackgroundResource(android.R.drawable.btn_default);

            Button button13 = (Button) findViewById(R.id.button13);
            button13.setEnabled(false);
            button13.setClickable(false);
            button13.setBackgroundResource(android.R.drawable.btn_default);
        }
    }

    public void st9(View v) {
        if (v.getId() == R.id.button9) {

            AlertDialog.Builder dlgBuilder = new AlertDialog.Builder(MainActivity.this);
            dlgBuilder.setMessage("Einheit ausser Dienst stellen?");
            dlgBuilder.setCancelable(false);
            dlgBuilder.setPositiveButton("Ja", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {

                    Button button = (Button) findViewById(R.id.button);
                    button.setEnabled(false);
                    button.setClickable(false);
                    button.setBackgroundResource(android.R.drawable.btn_default);

                    Button button2 = (Button) findViewById(R.id.button2);
                    button2.setEnabled(true);
                    button2.setClickable(true);
                    button2.setBackgroundResource(android.R.drawable.btn_default);

                    Button button3 = (Button) findViewById(R.id.button3);
                    button3.setEnabled(false);
                    button3.setClickable(false);
                    button3.setBackgroundResource(android.R.drawable.btn_default);

                    Button button4 = (Button) findViewById(R.id.button4);
                    button4.setEnabled(false);
                    button4.setClickable(false);
                    button4.setBackgroundResource(android.R.drawable.btn_default);

                    //Button button5 = (Button) findViewById(R.id.button5);
                    //button5.setEnabled(true);
                    //button5.setClickable(true);
                    //button5.setBackgroundResource(android.R.drawable.btn_default);

                    Button button6 = (Button) findViewById(R.id.button6);
                    button6.setEnabled(true);
                    button6.setClickable(true);
                    button6.setBackgroundResource(android.R.drawable.btn_default);

                    Button button7 = (Button) findViewById(R.id.button7);
                    button7.setEnabled(false);
                    button7.setClickable(false);
                    button7.setBackgroundResource(android.R.drawable.btn_default);

                    Button button8 = (Button) findViewById(R.id.button8);
                    button8.setEnabled(false);
                    button8.setClickable(false);
                    button8.setBackgroundResource(android.R.drawable.btn_default);

                    Button button9 = (Button) findViewById(R.id.button9);
                    button9.setEnabled(true);
                    button9.setClickable(false);
                    button9.setBackgroundColor(Color.parseColor("#9C27B0"));

                    Button button10 = (Button) findViewById(R.id.button10);
                    button10.setEnabled(false);
                    button10.setClickable(false);
                    button10.setBackgroundResource(android.R.drawable.btn_default);

                    Button button11 = (Button) findViewById(R.id.button11);
                    button11.setEnabled(false);
                    button11.setClickable(false);
                    button11.setBackgroundResource(android.R.drawable.btn_default);

                    //Button button12 = (Button) findViewById(R.id.button12);
                    //button12.setEnabled(true);
                    //button12.setClickable(true);
                    //button12.setBackgroundResource(android.R.drawable.btn_default);

                    Button button13 = (Button) findViewById(R.id.button13);
                    button13.setEnabled(false);
                    button13.setClickable(false);
                    button13.setBackgroundResource(android.R.drawable.btn_default);

                    Toast.makeText(MainActivity.this, "Ausser Dienst", Toast.LENGTH_SHORT).show();
                }
            });

            dlgBuilder.setNegativeButton("Nein", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
            });

            AlertDialog alert = dlgBuilder.create();
            alert.show();
        }
    }

    public void st10(View v) {
        if (v.getId() == R.id.button10) {

            AlertDialog.Builder dlgBuilder = new AlertDialog.Builder(MainActivity.this);
            dlgBuilder.setMessage("Intervention unterblieben?");
            dlgBuilder.setCancelable(false);
            dlgBuilder.setPositiveButton("Ja", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {

                    Button button = (Button) findViewById(R.id.button);
                    button.setEnabled(true);
                    button.setClickable(true);
                    button.setBackgroundResource(android.R.drawable.btn_default);

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

                    Button button7 = (Button) findViewById(R.id.button7);
                    button7.setEnabled(false);
                    button7.setClickable(false);
                    button7.setBackgroundResource(android.R.drawable.btn_default);

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
        }
    }

    public void st11(View v) {
        if (v.getId() == R.id.button11) {

            AlertDialog.Builder dlgbuilder = new AlertDialog.Builder(MainActivity.this);
            dlgbuilder.setTitle("Patient belassen/verweigert?");
            dlgbuilder.setItems(new CharSequence[]
                            {"Patient belassen", "Patient verweigert", "Nein"},

                    new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int which) {

                            switch (which) {

                                case 0:
                                    Button button = (Button) findViewById(R.id.button);
                                    button.setEnabled(true);
                                    button.setClickable(true);
                                    button.setBackgroundResource(android.R.drawable.btn_default);

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

                                    Button button7 = (Button) findViewById(R.id.button7);
                                    button7.setEnabled(false);
                                    button7.setClickable(false);
                                    button7.setBackgroundResource(android.R.drawable.btn_default);
                                    Toast.makeText(MainActivity.this, "Belassung", Toast.LENGTH_SHORT).show();
                                    break;
                                case 1:
                                    button = (Button) findViewById(R.id.button);
                                    button.setEnabled(true);
                                    button.setClickable(true);
                                    button.setBackgroundResource(android.R.drawable.btn_default);

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

                                    button7 = (Button) findViewById(R.id.button7);
                                    button7.setEnabled(false);
                                    button7.setClickable(false);
                                    button7.setBackgroundResource(android.R.drawable.btn_default);
                                    Toast.makeText(MainActivity.this, "Patient verweigert", Toast.LENGTH_SHORT).show();
                                    break;
                                case 2:
                                    break;
                            }
                        }
                    });

            dlgbuilder.create().show();

        }
    }

    public void st12(View v) {
        if (v.getId() == R.id.button12) {

            AlertDialog.Builder dlgBuilder = new AlertDialog.Builder(MainActivity.this);
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

            AlertDialog alert = dlgBuilder.create();
            alert.show();


        }
    }

    public void st13(View v) {
        if (v.getId() == R.id.button13) {

            AlertDialog.Builder dlgBuilder = new AlertDialog.Builder(MainActivity.this);
            dlgBuilder.setMessage("Patient an anderes Rettungsmittel übergeben?");
            dlgBuilder.setCancelable(false);
            dlgBuilder.setPositiveButton("Ja", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {

                    Button button = (Button) findViewById(R.id.button);
                    button.setEnabled(true);
                    button.setClickable(true);
                    button.setBackgroundResource(android.R.drawable.btn_default);

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

                    Button button7 = (Button) findViewById(R.id.button7);
                    button7.setEnabled(false);
                    button7.setClickable(false);
                    button7.setBackgroundResource(android.R.drawable.btn_default);

                    Toast.makeText(MainActivity.this, "Übergabe an anderes Rettungsmittel", Toast.LENGTH_SHORT).show();
                }
            });

            dlgBuilder.setNegativeButton("Nein", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
            });

            AlertDialog alert = dlgBuilder.create();
            alert.show();
        }
    }
    // Button state & color functions END

    // Patienten Management dialog builder //

    public void patmanstart(View v) {
        if (v.getId() == R.id.patmanbtn) {

            AlertDialog.Builder dlgBuilder = new AlertDialog.Builder(MainActivity.this);
            dlgBuilder.setMessage("Patient anlegen");

            LayoutInflater inflater = (MainActivity.this.getLayoutInflater());

            dlgBuilder.setView(inflater.inflate(R.layout.patman, null))

                    .setPositiveButton("zurück", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });

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

    // send Patienten Daten btn //

    public void sendpatdat (View v) {
        if (v.getId() == R.id.button22) {
            v.setVisibility(View.GONE);

            //remove layout
            View viewToRemove= findViewById(R.id.patmanrelayout);
            if (viewToRemove != null && viewToRemove.getParent() != null && viewToRemove instanceof ViewGroup)
                ((ViewGroup) viewToRemove.getParent()).removeView(viewToRemove);

            //send data

            Toast.makeText(MainActivity.this, "Patient angelegt", Toast.LENGTH_SHORT).show();
        }
    }

    // PatMan start btn //

    public void patmanstr (View v) {
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

    public void navbo (View v) {
        if (v.getId()==R.id.button18) {

            TextView text = (TextView)findViewById(R.id.bofield);
            String navadress = "google.navigation:" + text.getText().toString();
            Intent nav = new Intent(android.content.Intent.ACTION_VIEW);
            nav.setData(Uri.parse(navadress));
            startActivity(nav);

/**
            Intent nav = new Intent(android.content.Intent.ACTION_VIEW,
                    Uri.parse("google.navigation:" + navadress));
            startActivity(nav);
 */
        }
    }

    public void navao (View v) {
        if (v.getId() == R.id.button19) {

            TextView text = (TextView) findViewById(R.id.aofield);
            String navadress = "google.navigation:" + text.getText().toString();
            Intent nav = new Intent(android.content.Intent.ACTION_VIEW);
            nav.setData(Uri.parse(navadress));
            startActivity(nav);
        }
    }

    public void showgis (View v) {
        if (v.getId() == R.id.button30) {

            WebView gisView = (WebView) findViewById(R.id.gisView);

            gisView.getSettings().setJavaScriptEnabled(true);
            gisView.getSettings().getAllowFileAccessFromFileURLs();
            gisView.getSettings().setAllowUniversalAccessFromFileURLs(true);

            gisView.loadUrl("file:///android_asset/viennagissite.html");

            Toast.makeText(MainActivity.this, "Stadtplan Wien GIS", Toast.LENGTH_SHORT).show();
        }
    }
}




