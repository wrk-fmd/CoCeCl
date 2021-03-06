package it.fmd.cocecl;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.location.Location;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.LocalBroadcastManager;
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
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;

import it.fmd.cocecl.contentviews.NavDrawerItem;
import it.fmd.cocecl.contentviews.NavDrawerListAdapter;
import it.fmd.cocecl.dataStorage.IncidentData;
import it.fmd.cocecl.dataStorage.MsgArray;
import it.fmd.cocecl.fragments.MapFragment;
import it.fmd.cocecl.gmapsnav.gpstracker.GPSTrackListener;
import it.fmd.cocecl.incidentaction.IncidentTaskTypeSetting;
import it.fmd.cocecl.unitstatus.UnitInfoDialog;
import it.fmd.cocecl.utilclass.CheckPlayServices;
import it.fmd.cocecl.utilclass.ConnectionBroadcastReceiver;
import it.fmd.cocecl.utilclass.ConnectionManager;
import it.fmd.cocecl.utilclass.DialogAmbInfo;
import it.fmd.cocecl.utilclass.DialogPTCAInfo;
import it.fmd.cocecl.utilclass.GCMMessagesDialog;
import it.fmd.cocecl.utilclass.GPSToolbarIcon;
import it.fmd.cocecl.utilclass.GetDateTime;
import it.fmd.cocecl.utilclass.NotifiBarIcon;
import it.fmd.cocecl.utilclass.Phonecalls;
import it.fmd.cocecl.utilclass.SessionManagement;
import it.fmd.cocecl.utilclass.ToolbarIconStates;
import it.fmd.cocecl.viewpager.TabPagerAdapter;


public class MainActivity extends AppCompatActivity {

    // VIEWPAGER //
    TabLayout tabLayout;
    ViewPager viewPager;
    PagerAdapter pageradapter;

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

    private Boolean patman;
    // slide menu items END

    public CheckPlayServices cps = new CheckPlayServices();

    IncidentTaskTypeSetting itts = new IncidentTaskTypeSetting(this);

    IncidentData idata = new IncidentData();

    private CoordinatorLayout coordinatorLayout;

    ConnectionManager cm;
    ConnectionBroadcastReceiver cbr;

    GPSToolbarIcon gpsti;
    ToolbarIconStates tis;

    private boolean cmRegistered;
    private boolean cbrRegistered;

    // Location
    public static Location loc;
    private static double longitude;
    private static double latitude;
    private static String lngString = String.valueOf(longitude);
    private static String latString = String.valueOf(latitude);

    //GCM 3.0
    private BroadcastReceiver mRegistrationBroadcastReceiver;
    private boolean isReceiverRegistered;

    //Snackbar
    private Snackbar snackbar;

    // OnCreate Method // ------------------------------------- //
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cm = new ConnectionManager(getApplicationContext());
        tis = new ToolbarIconStates(getApplicationContext(), this);
        cbr = new ConnectionBroadcastReceiver(getApplicationContext(), this);
        gpsti = new GPSToolbarIcon(getApplicationContext(), this);

        // register
        registerReceiver(cm.mReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        //registerReceiver(cbr.toolbarBReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        cmRegistered = true;

        //Register GCM 3.0
        mRegistrationBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                SharedPreferences sharedPreferences =
                        PreferenceManager.getDefaultSharedPreferences(context);
                boolean sentToken = sharedPreferences
                        .getBoolean(APPConstants.SENT_TOKEN_TO_SERVER, false);
                if (sentToken) {
                } else {
                }
            }
        };

        registerGCMReceiver();

        //GPS Tracker Service
        // register
        startService(new Intent(this, GPSTrackListener.class));

        // Icon
        NotifiBarIcon nbi = new NotifiBarIcon(this);
        nbi.StatusBarAppIcon();

        // Get Version
        PackageInfo pInfo = null;
        try {
            pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        final String version = pInfo.versionName;

        // Snackbar
        {
            //Coordinator Layout for SnackBar//
            coordinatorLayout = (CoordinatorLayout) findViewById(R.id
                    .coordinatorLayout);

            snackbar = Snackbar
                    .make(coordinatorLayout, "Welcome to CoCeCl", Snackbar.LENGTH_LONG)
                    .setAction("OK", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Snackbar snackbar1 = Snackbar.make(coordinatorLayout, "Version " + version, Snackbar.LENGTH_SHORT);
                            snackbar1.show();
                        }
                    });
            snackbar.show();

        }


        // Custom Toolbar
        {
            Toolbar toolbar = (Toolbar) findViewById(R.id.appbar);
            setSupportActionBar(toolbar);
            //setTitle(R.string.app_name);
        }

        // VIEWPAGER //
        {
            tabLayout = (TabLayout) findViewById(R.id.tab_layout);

            if ((getResources().getConfiguration().screenLayout &
                    Configuration.SCREENLAYOUT_SIZE_MASK) >=
                    Configuration.SCREENLAYOUT_SIZE_LARGE) {

                // Tablet screen - LARGE
                this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

                tabLayout.addTab(tabLayout.newTab().setText("Main"));
                tabLayout.addTab(tabLayout.newTab().setText("Status"));
                tabLayout.addTab(tabLayout.newTab().setText("AO"));
            }

            if ((getResources().getConfiguration().screenLayout &
                    Configuration.SCREENLAYOUT_SIZE_MASK) <=
                    Configuration.SCREENLAYOUT_SIZE_NORMAL) {

                // Phone screen - NORMAL
                this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                tabLayout.addTab(tabLayout.newTab().setText("Main"), 0);
                tabLayout.addTab(tabLayout.newTab().setText("Status"), 1);
                tabLayout.addTab(tabLayout.newTab().setText("AO"), 2);
                tabLayout.addTab(tabLayout.newTab().setText("Map"), 3);
            }

            // Disabled in primary version
            //tabLayout.addTab(tabLayout.newTab().setText("Com"));
            tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

            //tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

            viewPager = (ViewPager) findViewById(R.id.viewPager);
            pageradapter = new TabPagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
            viewPager.setAdapter(pageradapter);
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

        SessionManagement SM = new SessionManagement(this);
        HashMap<String, String> user = SM.getUserDetails();

        // get dnr
        String dnr = user.get(SessionManagement.KEY_DNR);

        GetDateTime dateTime = new GetDateTime();
        String day = dateTime.getcurrentDay();

        TabletFeatures tf = new TabletFeatures(this);
        patman = tf.patman_enable();

        String online;
        if (cm.isOnline(this)) {
            online = "online";
        } else {
            online = "offlne";
        }

        // adding nav drawer items to array
        // 0 Home
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[0], navMenuIcons.getResourceId(0, -1), true, online));
        // 1 Settings
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[1], navMenuIcons.getResourceId(1, -1)));
        // 2 Kommunikation
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[2], navMenuIcons.getResourceId(2, -1)));
        // 3 AmbulanzInfo
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[3], navMenuIcons.getResourceId(3, -1)));
        // 4 User
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[4], navMenuIcons.getResourceId(4, -1), true, dnr));
        // 5 PATMAN
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[5], navMenuIcons.getResourceId(5, -1), true, "" + patman));
        // 6 ICD-10
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[6], navMenuIcons.getResourceId(6, -1)));
        // 7 PTCA Plan
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[7], navMenuIcons.getResourceId(7, -1), true, day));
        // 8 KH Pläne
        //navDrawerItems.add(new NavDrawerItem(navMenuTitles[8], navMenuIcons.getResourceId(8, -1)));
        // 9 MessageLOG
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[8], navMenuIcons.getResourceId(8, -1), true, ""/*message counter*/));

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
                //R.drawable.ic_home, //nav menu toggle icon
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

                navDrawerItems.get(8).setCount("" + MsgArray.gcmMessages.size());
                adapter.notifyDataSetChanged();
            }
        };
        mDrawerLayout.addDrawerListener(mDrawerToggle);

        if (savedInstanceState == null) {
            // on first time display view for first nav item
            //displayView(0);
            navDrawerAction(0);
        }

        //Initialize GCM & SMS Msg ArrayList
        /* Stored until MainActivity closes */
        MsgArray.gcmMessages = new ArrayList<>();
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
                // goto Settings Activity
                Intent isett = new Intent(getApplicationContext(), SettingsActivity.class);
                startActivity(isett);
                break;
            case 2:
                // goto/Load Communication fragment
                //tabLayout.addTab(tabLayout.newTab().setText("Com"));
                break;
            case 3:
                // Info Webview from InfoActivity
                DialogAmbInfo dai = new DialogAmbInfo(this);
                dai.openAmbInfo();
                break;
            case 4:
                // UserDialog
                showuserdialog();
                break;
            case 5:
                if (patman) {
                    Intent ipatman = new Intent(getApplicationContext(), PatmanActivity.class);
                    startActivity(ipatman);
                } else {
                    snackbar = Snackbar
                            .make(coordinatorLayout, "PatMan disabled, only on tablet", Snackbar.LENGTH_LONG)
                            .setAction("OK", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    snackbar.dismiss();
                                }
                            });
                    snackbar.show();
                }

                break;
            case 6:
                // load icd-10 website in browser
                gotoicd();
                break;
            case 7:
                // PTCA Dialog
                DialogPTCAInfo ptca = new DialogPTCAInfo(this);
                ptca.openPTCAPlan();
                break;
            case 8:
                // GCM Messages
                GCMMessagesDialog gcmmd = new GCMMessagesDialog(this);
                gcmmd.openGCMMessageDialog(this);
                break;

            default:
                break;
        }

        // update selected item and title, then close the drawer
        mDrawerList.setItemChecked(position, true);
        mDrawerList.setSelection(position);
        setTitle(navMenuTitles[position]);
        //mDrawerLayout.closeDrawer(mDrawerList);

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

        // Broadcast Receiver Connection State
        // register
        registerReceiver(cm.mReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        // register toolbar receiver
        registerReceiver(cbr.toolbarBReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        cbrRegistered = true;
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
                UnitInfoDialog uid = new UnitInfoDialog(this);
                uid.unitinfo();
        }

        return super.onOptionsItemSelected(item);
    }

    public void showuserdialog() {
        SessionManagement SM = new SessionManagement(getApplicationContext());
        //String user = SM.getUserDetails().values().toString();
        Boolean loggedin = SM.isLoggedIn();

        // get user data from session
        HashMap<String, String> user = SM.getUserDetails();

        // get name
        String name = user.get(SessionManagement.KEY_NAME);
        // get dnr
        String dnr = user.get(SessionManagement.KEY_DNR);

        AlertDialog.Builder dlgBuilder = new AlertDialog.Builder(MainActivity.this);
        dlgBuilder.setCancelable(false);
        dlgBuilder.setTitle("Benutzer");
        dlgBuilder.setMessage("LoggedIn: " + loggedin + "\n" + dnr + ", " + name);

        dlgBuilder.setNeutralButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        dlgBuilder.show();
    }

    @Override
    protected void onStart() {
        super.onStart();

        // register toolbar receiver
        //registerReceiver(cbr.toolbarBReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }

    @Override
    protected void onResume() {
        super.onResume();
        cps.checkPlayServices(this);

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

                ft.add(R.id.framelayout_1, new MainstatusFragment());
            }

            if (findViewById(R.id.framelayout_2) != null) {

                ft.add(R.id.framelayout_2, new IncidentFragment());
            }
*/
            if (findViewById(R.id.framelayout_3) != null) {

                ft.add(R.id.framelayout_3, new MapFragment());
            }

            ft.addToBackStack(null);
            // Transaction commit
            ft.commit();
        }

        // register BReceivers
        registerReceiver(cm.mReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        cmRegistered = true;
        //registerReceiver(cbr.toolbarBReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));

        //GCM 3.0
        registerGCMReceiver();

    }

    @Override
    protected void onPause() {
        super.onPause();

        // unregister the receiver
        if (cmRegistered) {
            unregisterReceiver(cm.mReceiver);
            cmRegistered = false;
        }
        if (cbrRegistered) {
            try {
                unregisterReceiver(cbr.toolbarBReceiver);
                cbrRegistered = false;
            } catch (Exception e) {
                Log.e("MainActivity", "unregisterReceiver Exception" + e);
            }
        }

        //GCM
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mRegistrationBroadcastReceiver);
        isReceiverRegistered = false;
    }

    @Override
    protected void onStop() {
        super.onStop();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // remove persistent appbar icon
        NotifiBarIcon nbi = new NotifiBarIcon(this);
        nbi.removeSBAI();

        //stop GPS service
        stopService(new Intent(this, GPSTrackListener.class));
    }

    // SavedInstanceState
/*
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

        TextView textView111 = (TextView) findViewById(R.id.textView111);
        Button buttonEB = (Button) findViewById(R.id.button38);
        Button buttonNEB = (Button) findViewById(R.id.button39);
        Button buttonAD = (Button) findViewById(R.id.button40);

        if (textView111 != null) {
            CharSequence statusText = textView111.getText();
            savedInstanceState.putCharSequence("statusText", statusText);
        }

        if (buttonEB != null) {
            Boolean buttonEBstate = buttonEB.isPressed();
            savedInstanceState.putBoolean("EBButtonState", buttonEBstate);
        }
        if (buttonEB != null) {
            Boolean buttonNEBstate = buttonNEB.isPressed();
            savedInstanceState.putBoolean("NEBButtonState", buttonNEBstate);
        }
        if (buttonEB != null) {
            Boolean buttonADstate = buttonAD.isPressed();
            savedInstanceState.putBoolean("ADButtonState", buttonADstate);
        }
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        TextView textView111 = (TextView) findViewById(R.id.textView111);
        Button buttonEB = (Button) findViewById(R.id.button38);
        Button buttonNEB = (Button) findViewById(R.id.button39);
        Button buttonAD = (Button) findViewById(R.id.button40);

        CharSequence statusText = savedInstanceState.getCharSequence("StatusText");
        textView111.setText(statusText);

        Boolean buttonEBstate = savedInstanceState.getBoolean("EBButtonState");
        buttonEB.setEnabled(buttonEBstate);

        Boolean buttonNEBstate = savedInstanceState.getBoolean("NEBButtonState");
        buttonNEB.setEnabled(buttonNEBstate);

        Boolean buttonADstate = savedInstanceState.getBoolean("ADButtonState");
        buttonAD.setEnabled(buttonADstate);

    }
*/
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

    private void registerGCMReceiver() {
        if (!isReceiverRegistered) {
            LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver,
                    new IntentFilter(APPConstants.REGISTRATION_COMPLETE));
            isReceiverRegistered = true;
        }
    }

    // Buttons //

    // PhoneCalls
    public void lscall(View view) {

        Phonecalls phcl = new Phonecalls();

        switch (view.getId()) {

            //lsbv
            case R.id.button17:
                phcl.lsbvcall(this);
                break;

            //call kh on commFragment
            case R.id.button43:
                phcl.chkhcall(getApplicationContext());
                break;

            //lsmain
            case R.id.button47:
                phcl.lsmaincall(this);
                break;
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

