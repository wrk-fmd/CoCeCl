package it.fmd.cocecl;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import it.fmd.cocecl.contentviews.NavDrawerItem;
import it.fmd.cocecl.contentviews.NavDrawerListAdapter;
import it.fmd.cocecl.fragments.mapFragment;
import it.fmd.cocecl.utilclass.ConnectionManager;
import it.fmd.cocecl.utilclass.GPSManager;
import it.fmd.cocecl.utilclass.JSONParser;
import it.fmd.cocecl.utilclass.Phonecalls;
import it.fmd.cocecl.utilclass.SessionManagement;
import it.fmd.cocecl.utilclass.TabPagerAdapter;

import static android.graphics.Color.GREEN;
import static android.graphics.Color.RED;


public class MainActivity extends AppCompatActivity {

    // NAV DRAWER //
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;

    // nav drawer title
    private CharSequence mDrawerTitle;

    // used to store app title
    private CharSequence mTitle;

    // slide menu items
    private String[] navMenuTitles;
    private TypedArray navMenuIcons;

    private ArrayList<NavDrawerItem> navDrawerItems;
    private NavDrawerListAdapter adapter;

    ConnectionManager conman = new ConnectionManager();

    private CoordinatorLayout coordinatorLayout;

    // Location
    public static Location loc;
    private static double longitude;
    private static double latitude;
    private static String lngString = String.valueOf(longitude);
    private static String latString = String.valueOf(latitude);


    // OnCreate Method // ------------------------------------- //
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        {
            //Coordinator Layout for SnackBar//
            coordinatorLayout = (CoordinatorLayout) findViewById(R.id
                    .coordinatorLayout);

            Snackbar snackbar = Snackbar
                    .make(coordinatorLayout, "Welcome to CoCeCl", Snackbar.LENGTH_LONG);

            snackbar.show();

        }

        {
            Toolbar toolbar = (Toolbar) findViewById(R.id.appbar);
            setSupportActionBar(toolbar);
            //setTitle(R.string.app_name);
        }

        // GPS Coordinates //

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

        // VIEWPAGER //
        {
            TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);

            if ((getResources().getConfiguration().screenLayout &
                    Configuration.SCREENLAYOUT_SIZE_MASK) >=
                    Configuration.SCREENLAYOUT_SIZE_LARGE) {

                // on a normal screen device ...
                this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

                //TODO: set tabs depending on unit status

                tabLayout.addTab(tabLayout.newTab().setText("Main"));
                tabLayout.addTab(tabLayout.newTab().setText("Status"));
                tabLayout.addTab(tabLayout.newTab().setText("AO"));
            }

            if ((getResources().getConfiguration().screenLayout &
                    Configuration.SCREENLAYOUT_SIZE_MASK) <=
                    Configuration.SCREENLAYOUT_SIZE_NORMAL) {

                this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                tabLayout.addTab(tabLayout.newTab().setText("Main"));
                tabLayout.addTab(tabLayout.newTab().setText("Status"));
                tabLayout.addTab(tabLayout.newTab().setText("AO"));
                tabLayout.addTab(tabLayout.newTab().setText("Map"));
            }
            //tabLayout.addTab(tabLayout.newTab().setText("Com"));
            tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

            //tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

            //TODO: on incident emergency set tablayout_color to blue #1565C0
            //tabLayout.setBackgroundColor(BLUE);

            final ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
            final PagerAdapter adapter = new TabPagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
            viewPager.setAdapter(adapter);
            viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
            tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {
                    viewPager.setCurrentItem(tab.getPosition());
                }

                @Override
                public void onTabUnselected(TabLayout.Tab tab) {

                }

                @Override
                public void onTabReselected(TabLayout.Tab tab) {

                }
            });
        }

        // NAVIGATION DRAWER //

        mTitle = mDrawerTitle = getTitle();

        // load slide menu items
        navMenuTitles = getResources().getStringArray(R.array.nav_drawer_items);

        // nav drawer icons from resources
        navMenuIcons = getResources().obtainTypedArray(R.array.nav_drawer_icons);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.list_slidermenu);

        navDrawerItems = new ArrayList<NavDrawerItem>();

        // adding nav drawer items to array
        // 0 Home
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[0], navMenuIcons.getResourceId(0, -1)));
        // 1 Settings
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[1], navMenuIcons.getResourceId(1, -1), true, "7"));
        // 2 Kommunikation
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[2], navMenuIcons.getResourceId(2, -1)));
        // 3 AmbulanzInfo
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[3], navMenuIcons.getResourceId(3, -1)));
        // 4 User
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[4], navMenuIcons.getResourceId(4, -1)));
        // 5 PATMAN
        //navDrawerItems.add(new NavDrawerItem(navMenuTitles[5], navMenuIcons.getResourceId(5, -1)));
        // 6 ICD-10
        //navDrawerItems.add(new NavDrawerItem(navMenuTitles[6], navMenuIcons.getResourceId(6, -1), true, "50+"));
        // 7 PTCA Plan
        //navDrawerItems.add(new NavDrawerItem(navMenuTitles[7], navMenuIcons.getResourceId(7, -1)));


        // Recycle the typed array
        navMenuIcons.recycle();

        mDrawerList.setOnItemClickListener(new SlideMenuClickListener());

        // setting the nav drawer list adapter
        adapter = new NavDrawerListAdapter(getApplicationContext(), navDrawerItems);
        mDrawerList.setAdapter(adapter);

        // enabling action bar app icon and behaving it as toggle button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                //R.drawable.menu_24, //nav menu toggle icon
                R.string.app_name, // nav drawer open - description for accessibility
                R.string.app_name // nav drawer close - description for accessibility
        ) {
            public void onDrawerClosed(View view) {
                getSupportActionBar().setTitle(mTitle);
                // calling onPrepareOptionsMenu() to show action bar icons
                invalidateOptionsMenu();
            }

            public void onDrawerOpened(View drawerView) {
                getSupportActionBar().setTitle(mDrawerTitle);
                // calling onPrepareOptionsMenu() to hide action bar icons
                invalidateOptionsMenu();
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        if (savedInstanceState == null) {
            // on first time display view for first nav item
            //displayView(0);
            navDrawerAction(0);
        }

        // additional
        inetcon();
        checkGPS();
    }

    //ONCREATE END ------------------------------------------------------------------------------

    //NAV DRAWER //

    /**
     * Slide menu item click listener
     */
    private class SlideMenuClickListener implements
            ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
                                long id) {
            // display view for selected nav drawer item
            // displayView(position);
            navDrawerAction(position);

        }
    }

    public void gotoicd() {
        //open link icd-10
        Uri icd10 = Uri.parse("http://www.icd-code.de/");
        Intent openicd = new Intent(Intent.ACTION_VIEW, icd10);
        startActivity(openicd);
    }

    private void navDrawerAction(int position) {
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        switch (position) {
            case 0:
                //HOME VIEWPAGER First Tab
                //MainStatus Fragment
                break;
            case 1:
                Intent isett = new Intent(getApplicationContext(), SettingsActivity.class);
                startActivity(isett);
                break;
            case 2:
                tabLayout.addTab(tabLayout.newTab().setText("Com"));
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                Intent ipatman = new Intent(getApplicationContext(), PatmanActivity.class);
                startActivity(ipatman);
                break;
            case 6:
                gotoicd();
                break;
            case 7:
                break;

            default:
                break;
        }

        // update selected item and title, then close the drawer
        mDrawerList.setItemChecked(position, true);
        mDrawerList.setSelection(position);
        setTitle(navMenuTitles[position]);
        mDrawerLayout.closeDrawer(mDrawerList);

    }

    /***
     * Called when invalidateOptionsMenu() is triggered
     */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // if nav drawer is opened, hide the action items
        boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
        //menu.findItem(R.id.action_settings).setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);
    }

    /**
     * Diplaying fragment view for selected nav drawer list item
     */
    /*
    private void displayView(int position) {
        // update the main content by replacing fragments
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new HomeFragment();
                break;
            case 1:
                fragment = new FindPeopleFragment();
                break;
            case 2:
                fragment = new PhotosFragment();
                break;
            case 3:
                fragment = new CommunityFragment();
                break;
            case 4:
                fragment = new PagesFragment();
                break;
            case 5:
                fragment = new WhatsHotFragment();
                break;

            default:
                break;
        }

        if (fragment != null) {
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.frame_container, fragment).commit();

            // update selected item and title, then close the drawer
            mDrawerList.setItemChecked(position, true);
            mDrawerList.setSelection(position);
            setTitle(navMenuTitles[position]);
            mDrawerLayout.closeDrawer(mDrawerList);
        } else {
            // error in creating fragment
            Log.e("MainActivity", "Error in creating fragment");
        }
    }
*/
    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getSupportActionBar().setTitle(mTitle);
    }


    /**
     * When using the ActionBarDrawerToggle, you must call it during
     * onPostCreate() and onConfigurationChanged()...
     */

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    //NAV DRAWER END//


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

        // toggle nav drawer on selecting action bar app icon/title
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent isett = new Intent(getApplicationContext(), SettingsActivity.class);
            startActivity(isett);
            return true;
        }

        if (id == R.id.action_user) {
            return true;
        }
        SessionManagement SM = new SessionManagement(getApplicationContext());
        switch (item.getItemId()) {
            case R.id.logoutuser:
                SM.logoutUser();
                break;
            case R.id.showuser:
                showuserdialog();
                break;
            case R.id.unitinfo:
                //TODO: get unitinfo dialog from infoactivity
                InfoActivity IA = new InfoActivity();
                IA.unitinfo();
        }

        return super.onOptionsItemSelected(item);
    }

    public void showuserdialog() {
        //TODO: get user info
        SessionManagement SM = new SessionManagement(getApplicationContext());
        String user = SM.getUserDetails().values().toString();

        AlertDialog.Builder dlgBuilder = new AlertDialog.Builder(MainActivity.this);
        dlgBuilder.setCancelable(false);
        dlgBuilder.setTitle("Benutzer");
        dlgBuilder.setMessage(user);

        dlgBuilder.setNeutralButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        AlertDialog alert = dlgBuilder.create();
        alert.show();
    }


    /*
        public void setConnectionIcons() {
        //TODO: if on fullscreen set connection icons on toolbar visible, else invisible

            //requestWindowFeature(Window.FEATURE_NO_TITLE); getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

            TextView wifitext = (TextView)findViewById(R.id.textView7);
            TextView mobiletext = (TextView)findViewById(R.id.textView83);

            Window window = this.getWindow();
            window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
            window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
            if () {

                wifitext.setVisibility(View.VISIBLE);
                mobiletext.setVisibility(View.VISIBLE);

            }else{

                wifitext.setVisibility(View.INVISIBLE);
                mobiletext.setVisibility(View.INVISIBLE);

            }
        }
    */
    // ConnectionManager //
    //TODO: use broadcast receiver from connectionmanager class
    private void registerReceivers() {
        //conman.registerReceiver(conman.mReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }

    // ToolBar status icons //
    public void checkMLSConnection() {

        // ToolBar mls connection state icon
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

    public void inetcon() {
        //TODO: use from Connection manager class
        TextView wifitext = (TextView) findViewById(R.id.textView7);
        TextView mobiletext = (TextView) findViewById(R.id.textView83);
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork != null) { // connected to the internet
            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {
                // connected to wifi
                wifitext.setBackgroundColor(GREEN);
                //Toast.makeText(context, activeNetwork.getTypeName(), Toast.LENGTH_SHORT).show();
            } else if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
                // connected to the mobile provider's data plan
                mobiletext.setBackgroundColor(GREEN);
                //Toast.makeText(context, activeNetwork.getTypeName(), Toast.LENGTH_SHORT).show();
            }
        } else {
            // not connected to the internet
            wifitext.setBackgroundColor(RED);
            mobiletext.setBackgroundColor(RED);
        }
    }

    public void checkInetConnection() {

        // TextView in ToolBar
        TextView wifitext = (TextView) findViewById(R.id.textView7);
        TextView mobiletext = (TextView) findViewById(R.id.textView83);

        if (conman.isOnline()) {

            wifitext.setBackgroundColor(GREEN);
            mobiletext.setBackgroundColor(GREEN);

        } else {

            wifitext.setBackgroundColor(RED);
            mobiletext.setBackgroundColor(RED);

        }
    }

    // Check if GPS enabled / show icon in appbar

    public void checkGPS() {

        TextView gpstext = (TextView) findViewById(R.id.textView108);

        LocationManager lm = (LocationManager) getApplicationContext().getSystemService(Context.LOCATION_SERVICE);
        boolean gps_enabled = false;

        try {
            gps_enabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);

        } catch (Exception ex) {
        }

        if (!gps_enabled) {
            gpstext.setBackgroundColor(RED);
        }

        if (gps_enabled) {
            gpstext.setBackgroundColor(GREEN);
        }
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
    public void onStart() {
        super.onStart();
        //setSMS();
        //checkMLSConnection();
    }

    @Override
    public void onResume() {
        super.onResume();
        checkPlayServices();
        //checkMLSConnection();

        // FRAGMENT MANAGER //
        //TODO: Design on Tablet needs to be changed
        if ((getResources().getConfiguration().screenLayout &
                Configuration.SCREENLAYOUT_SIZE_MASK) >=
                Configuration.SCREENLAYOUT_SIZE_LARGE) {
/*
            //TODO: set tabs depending on unit status
            TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
            tabLayout.addTab(tabLayout.newTab().setText("Main"));
            tabLayout.addTab(tabLayout.newTab().setText("Status"));
            tabLayout.addTab(tabLayout.newTab().setText("AO"));
            //tabLayout.addTab(tabLayout.newTab().setText("Map"));
            //tabLayout.addTab(tabLayout.newTab().setText("Com"));
            tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

            //tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

            //TODO: on incident emergency set tablayout_color to blue #1565C0
            //tabLayout.setBackgroundColor(BLUE);

            final ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
            final PagerAdapter adapter = new TabPagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
            viewPager.setAdapter(adapter);
            viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
            tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {
                    viewPager.setCurrentItem(tab.getPosition());
                }

                @Override
                public void onTabUnselected(TabLayout.Tab tab) {

                }

                @Override
                public void onTabReselected(TabLayout.Tab tab) {

                }
            });


            // on a large screen device ...
            this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
*/
            // FM support manager
            FragmentManager fm = getSupportFragmentManager();
            // Transaction start
            FragmentTransaction ft = fm.beginTransaction();
/*
            // Begin the transaction
            if (findViewById(R.id.framelayout_1) != null) {

                ft.add(R.id.framelayout_1, new mainstatusFragment());
            }

            if (findViewById(R.id.framelayout_2) != null) {

                ft.add(R.id.framelayout_2, new incidentFragment());
            }
*/
            if (findViewById(R.id.framelayout_3) != null) {

                ft.add(R.id.framelayout_3, new mapFragment());
            }

            ft.addToBackStack(null);
            // Transaction commit
            ft.commit();
        }
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

    // PhoneCalls
    public void lscall(View view) {

        Phonecalls phcl = new Phonecalls();

        switch (view.getId()) {

            //lsbv
            case R.id.button17:
                phcl.lsbvcall();
                break;

            //call kh on commFragment
            case R.id.button43:
                phcl.chkhcall();
                break;

            //lsmain
            case R.id.button47:
                phcl.lsmaincall();
                break;
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


    // Report incident method on mainstatus fragment //
    // Get coordinates, and nearest address
    public void reportincident(View v) {

        RelativeLayout reportincident = (RelativeLayout) getLayoutInflater().inflate(R.layout.reportincident, null);

        if (v.getId() == R.id.button42) {

            final EditText editText24 = (EditText) reportincident.findViewById(R.id.editText24);
            final TextView textView86 = (TextView) reportincident.findViewById(R.id.textView86);
            final TextView textView93 = (TextView) reportincident.findViewById(R.id.textView93);
            final TextView textView112 = (TextView) findViewById(R.id.textView112);
            final Button button42 = (Button) findViewById(R.id.button42);

            AlertDialog.Builder dlgBuilder = new AlertDialog.Builder(MainActivity.this);
            dlgBuilder.setMessage("Neuen Einsatz bei derzeitiger Position melden?");
            dlgBuilder.setCancelable(false);

            button42.setClickable(false);

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

                if (addresses != null && addresses.size() > 0) {
                    Address returnedAddress = addresses.get(0);
                    StringBuilder strReturnedAddress = new StringBuilder();
                    for (int i = 0; i < returnedAddress.getMaxAddressLineIndex(); i++) {
                        strReturnedAddress.append(returnedAddress.getAddressLine(i)).append("\n");
                    }

                    //strReturnedAddress.append(returnedAddress.getLocality()).append("\n");
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

            //editText24.setText(locationAddress);
            textView86.setText("lat: " + latitude);
            textView93.setText("lon: " + longitude);

            dlgBuilder.setPositiveButton("Ja", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            button42.setEnabled(false);
                            button42.setClickable(false);
                            //button42.setBackgroundColor(YELLOW);
                            button42.setBackground(getResources().getDrawable(R.drawable.button_yellow_pressed));

                            Handler h = new Handler();
                            h.postDelayed(new

                                                  Runnable() {
                                                      @Override
                                                      public void run() {
                                                          button42.setEnabled(true);
                                                          button42.setClickable(true);
                                                          //button42.setBackgroundColor(Color.parseColor("#bdbdbd"));
                                                          button42.setBackground(getResources().getDrawable(R.drawable.custom_button_normal));
                                                          editText24.setText("");
                                                          textView86.setText("");
                                                          textView93.setText("");
                                                          textView112.setText("");
                                                          textView112.setVisibility(View.GONE);
                                                      }
                                                  }

                                    , 30000);

                            Toast.makeText(MainActivity.this, "Neuen Einsatz an Leitstelle gemeldet", Toast.LENGTH_SHORT).show();
                            textView112.setVisibility(View.VISIBLE);
                            textView112.setText("Einsatz gemeldet");

                            dialog.cancel();
                        }
                    }

            );

            dlgBuilder.setNegativeButton("Nein", new DialogInterface.OnClickListener()

                    {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            dialog.cancel();

                        }
                    }

            );

            AlertDialog alert = dlgBuilder.create();
            alert.show();
            button42.setClickable(true);

            // remove layout
            View viewToRemove = findViewById(R.id.reportincidentrelayout);
            if (viewToRemove != null && viewToRemove.getParent() != null && viewToRemove instanceof ViewGroup)
                ((ViewGroup) viewToRemove.getParent()).removeView(viewToRemove);


        }
    }

    // GCM Message/Notification/Snackbar update --------------------------
    //
    //
    private final static int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;

    public void gmcmessage() {
        // Intent Message sent from Broadcast Receiver
        String str = getIntent().getStringExtra("msg");

        // Get Email ID from Shared preferences
        SharedPreferences prefs = getSharedPreferences("UserDetails",
                Context.MODE_PRIVATE);
        String eMailId = prefs.getString("eMailId", "");
        // Set Title
        //usertitleET = (TextView) findViewById(R.id.usertitle);

        // Check if Google Play Service is installed in Device
        // Play services is needed to handle GCM stuffs
        if (!checkPlayServices()) {
            Toast.makeText(
                    getApplicationContext(),
                    "This device doesn't support Play services, App will not work normally",
                    Toast.LENGTH_LONG).show();
        }

        //usertitleET.setText("Hello " + eMailId + " !");
        // When Message sent from Broadcase Receiver is not empty
        if (str != null) {
            // Set the message
            //msgET = (TextView) findViewById(R.id.message);
            //msgET.setText(str);
        }
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
                        getApplicationContext(),
                        "This device doesn't support Play services, App will not work normally",
                        Toast.LENGTH_LONG).show();
                finish();
            }
            return false;
        } else {
            /*Toast.makeText(
                    getApplicationContext(),
                    "This device supports Play services, App will work normally",
                    Toast.LENGTH_LONG).show();*/
        }
        return true;
    }


    //JSON GET and POST method// ----------------------------------------------------
    // Async Task

    //POST
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
            } else {
                Log.d("Failure", message);
            }
        }

    }

    //GET
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
            } else {
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

