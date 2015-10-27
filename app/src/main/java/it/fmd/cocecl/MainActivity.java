package it.fmd.cocecl;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
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

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

import static android.graphics.Color.BLUE;
import static android.graphics.Color.GREEN;
import static android.graphics.Color.YELLOW;


public class MainActivity extends FragmentActivity {

    private FragmentTabHost mTabHost;

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

        // TABHOST CONTROLLER //

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
    //action bar version2
/*
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

*/
    //Call LS Button on deliverylocFragment//

    public void lscall(View v) {

        if (v.getId() == R.id.button17) {

            Button button17 = (Button) findViewById(R.id.button17);
            button17.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {

                    Intent callIntent = new Intent(Intent.ACTION_CALL);
                    callIntent.setData(Uri.parse("tel:"+R.string.lsbv));
                    startActivity(callIntent);
                    /*
                    PackageManager pm = getPackageManager();
                    if (pm.checkPermission(Manifest.permission.CALL_PHONE, getPackageName()) == PackageManager.PERMISSION_GRANTED) {

                    } else {

                    }
*/
                }
            });

        }
    }

    // Alert Push Notification Manager //
/*
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

/*
    public static void main(String[] args) {
        statusFragment stFr = new statusFragment();

    }
*/
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
                    textView85.setText(sdf.format(cal.getTime()) );
                    button41.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_call_made_black_18dp, 0, 0, 0);
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


    // Status Tastenfeld // moved to Fragment status

    // Button state & color functions END

    // Patienten Management dialog builder //

    public void createpat(View v) {
        if (v.getId() == R.id.button46) {

            AlertDialog.Builder dlgBuilder = new AlertDialog.Builder(MainActivity.this);
            dlgBuilder.setMessage("Patient anlegen");
            dlgBuilder.setTitle("PATADMIN");

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



/*
        Button button46 = (Button) findViewById(R.id.button46);
        button46.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg3) {

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
        });

        Button createpatbtn = (Button) findViewById(R.id.createpatbtn);
        createpatbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg4) {

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
        });
    }
*/
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

            gisView.loadUrl("file:///android_asset/leaflet.html");
        }
    }

    public void st14 (View v) {
        if (v.getId() == R.id.button42) {

            TextView textView86 = (TextView) findViewById(R.id.textView86);

            gpsmanager.LocationResult locationResult = new gpsmanager.LocationResult(){
                @Override
                public void gotLocation(Location location){
                    //Got the location!

                }
            };
            gpsmanager myLocation = new gpsmanager();
            myLocation.getLocation(this, locationResult);

            textView86.setText(String.valueOf(locationResult));
        }
    }
/*
    private String GetAddress(Double lat, Double lon) {
        Geocoder geocoder = new Geocoder(context, Locale.ENGLISH);
        String ret = "";
        List<Address> addresses = null;
        try {
            addresses = geocoder.getFromLocation(lat, lon, 1);
            if (!addresses.equals(null)) {
                Address returnedAddress = addresses.get(0);
                StringBuilder strReturnedAddress = new StringBuilder("\n");
                for (int i = 0; i < returnedAddress.getMaxAddressLineIndex(); i++) {
                    strReturnedAddress
                            .append(returnedAddress.getAddressLine(i)).append(
                            "\n");
                }
                ret = "Around: " + strReturnedAddress.toString();
            } else {
                ret = "No Address returned!";
            }
        } catch (IOException e) {
            e.printStackTrace();
            ret = "Location: https://maps.google.co.in/maps?hl=en&q=" + lat
                    + "," + lon;
        } catch (NullPointerException e) {
            e.printStackTrace();
            ret = lat + "," + lon;
        }
        return ret;
    }
    */
}




