package polytechnice.ihm.projet;


import android.content.res.Configuration;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.sothree.slidinguppanel.SlidingUpPanelLayout;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;

import io.fabric.sdk.android.Fabric;
import polytechnice.ihm.projet.siPresentation.SIPresentationFragment;

/**
 * @author Anthony Loroscio
 */
public class MainActivity extends AppCompatActivity{
    private DrawerLayout mDrawer;
    private NavigationView nvDrawer;
    private ActionBarDrawerToggle drawerToggle;
    private Toolbar toolbar;
    private int surfaceOrientation;

    //Hidden layout
    private SlidingUpPanelLayout mLayout;
    private TextView textView;

    //Twitter
    private static final String TWITTER_KEY = "uoS5Row3TjfSQmwiFs7qKrQgj";
    private static final String TWITTER_SECRET = "4VXVrzsPgxfohiqzG5kIZrxCf4B14daEqM9gVToDO0VanjeZdd";

    //Orientation mode
    private static final int ORIENTATION_PORTRAIT = 1;
    private static final int ORIENTATION_LANDSCAPE = 2;
    private boolean isLandingPage = true;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new Twitter(authConfig));

        setContentView(R.layout.activity_main);

        init();



        // Set a Toolbar to replace the ActionBar.
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Find our drawer view
        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerToggle = setupDrawerToggle();

        // Tie DrawerLayout events to the ActionBarToggle
        mDrawer.setDrawerListener(drawerToggle);

        // Find our drawer view
        nvDrawer = (NavigationView) findViewById(R.id.nvView);
        // Setup drawer view
        setupDrawerContent(nvDrawer);

        //Hide the title in the landing page
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        LandingPageFragment fragment = new LandingPageFragment();
        fragmentTransaction.add(R.id.main_container, fragment);
        fragmentTransaction.commit();

        //surfaceOrientation = getResources().getConfiguration().orientation;

    }

    private ActionBarDrawerToggle setupDrawerToggle() {
        return new ActionBarDrawerToggle(this, mDrawer, toolbar, R.string.drawer_open,  R.string.drawer_close);
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        selectDrawerItem(menuItem);
                        return true;
                    }
                });
    }


        public void selectDrawerItem(MenuItem menuItem) {

        // Create a new fragment and specify the fragment to show based on nav item clicked
        Fragment fragment = null;
        Class fragmentClass;

        switch(menuItem.getItemId()) {
            case R.id.nav_first_fragment: // actualities
                fragmentClass = ActualitiesFragment.class;
                break;

            case R.id.nav_twitter_fragment: // onTwitter
                fragmentClass = TwitterFragment.class;
                break;

            case R.id.nav_contact: // contacts
                fragmentClass = ContactsFragment.class;
                break;

            case R.id.nav_si_presentation: // presentation
                fragmentClass = SIPresentationFragment.class;
                break;

            default:
                fragmentClass = ActualitiesFragment.class;
                break;
        }

        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Show the toolbar
        showToolBar();
        isLandingPage = false;

        // Insert the fragment by replacing any existing fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.main_container, fragment).commit();

        // Highlight the selected item has been done by NavigationView
        menuItem.setChecked(true);
        // Set action bar title
        setTitle(menuItem.getTitle());
        // Close the navigation drawer
        mDrawer.closeDrawers();
    }

    /**
     * Initialization of the textview and SlidingUpPanelLayout
     */
    public void init(){

        mLayout = (SlidingUpPanelLayout) findViewById(R.id.sliding_layout);
        //textView = (TextView) findViewById(R.id.TEST);

    }

    public void showToolBar(){
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        toolbar.setBackgroundDrawable(new ColorDrawable(0xff8BC34A));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // `onPostCreate` called when activity start-up is complete after `onStart()`
    // NOTE! Make sure to override the method with only a single `Bundle` argument
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggles
        drawerToggle.onConfigurationChanged(newConfig);

        // if we are on the landingPage we do not show the toolbar
        /*if (!isLandingPage) {
            showToolBar();
        }*/

    }
}
