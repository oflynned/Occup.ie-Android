package com.syzible.occupie;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.google.firebase.iid.FirebaseInstanceId;
import com.syzible.occupie.Common.Authentication.CreateAccountActivity;
import com.syzible.occupie.Common.Authentication.FCMTokenService;
import com.syzible.occupie.Common.Persistence.LocalPrefs;
import com.syzible.occupie.Common.Persistence.OAuthUtils;
import com.syzible.occupie.Common.Persistence.Target;
import com.syzible.occupie.Tenant.Applications.ApplicationsFragment;
import com.syzible.occupie.Tenant.Favourites.FavouritesFragment;
import com.syzible.occupie.Tenant.FindProperty.Common.PropertyType;
import com.syzible.occupie.Tenant.FindProperty.Results.HouseShareResults.FindHouseShareFragment;
import com.syzible.occupie.Tenant.FindProperty.Results.RentalResults.FindRentalFragment;
import com.syzible.occupie.Tenant.Settings.SettingsActivity;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private Target currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        currentUser = LocalPrefs.getCurrentProfile(this).equals("Landlord") ? Target.landlord : Target.user;

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.inflateMenu(currentUser == Target.landlord ?
                R.menu.activity_main_drawer_landlord : R.menu.activity_main_drawer_tenant);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.getMenu().getItem(0).setChecked(true);

        View headerView = navigationView.getHeaderView(0);
        TextView nameNavView = headerView.findViewById(R.id.nav_header_name);
        TextView currentProfile = headerView.findViewById(R.id.nav_header_state);

        nameNavView.setText(LocalPrefs.getFullName(this));
        currentProfile.setText(LocalPrefs.getCurrentProfile(this));

        if (currentUser == Target.landlord) {
            // TODO set default landlord page to be own property listings manager
        } else {
            setFragment(getFragmentManager(), FindRentalFragment.getInstance(PropertyType.rent));
        }

        Intent fcmService = new Intent(this, FCMTokenService.class);
        startService(fcmService);
        System.out.println(FirebaseInstanceId.getInstance().getToken());
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            int fragmentSize = getFragmentManager().getBackStackEntryCount();
            if (fragmentSize == 1) {
                new AlertDialog.Builder(this)
                        .setTitle(String.format("Close %s?", getString(R.string.app_name)))
                        .setMessage(String.format("Are you sure you want to close %s? Click close to confirm.", getString(R.string.app_name)))
                        .setPositiveButton("Close", (dialog, which) -> finish())
                        .setNegativeButton("Cancel", null)
                        .create()
                        .show();
            } else {
                if (fragmentSize > 1) {
                    getFragmentManager().popBackStack();
                } else {
                    super.onBackPressed();
                }
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            startActivity(new Intent(this, SettingsActivity.class));
            return true;
        } else if (id == R.id.logout_user) {
            logout(Target.user);
        } else if (id == R.id.logout_landlord) {
            logout(Target.landlord);
        } else if (id == R.id.clear_prefs) {
            for (LocalPrefs.Pref p : LocalPrefs.Pref.values())
                LocalPrefs.purgePref(p, this);

            logout(Target.user);
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (currentUser == Target.landlord) {
            if (id == R.id.nav_my_listings) {

            } else if (id == R.id.nav_requests) {

            } else if (id == R.id.nav_switch_to_tenant) {
                new AlertDialog.Builder(this)
                        .setTitle("Switch to Tenant Profile?")
                        .setMessage("Are you sure you want to switch to a tenant profile?")
                        .setPositiveButton("OK", (dialog, which) -> logout(Target.landlord))
                        .setNegativeButton("Cancel", null)
                        .show();
            }
        } else {
            if (id == R.id.nav_find_rental) {
                setFragment(getFragmentManager(), FindRentalFragment.getInstance(PropertyType.rent));
            } else if (id == R.id.nav_find_house_share) {
                setFragment(getFragmentManager(), FindHouseShareFragment.getInstance(PropertyType.house_share));
            } else if (id == R.id.nav_favourites) {
                setFragment(getFragmentManager(), FavouritesFragment.getInstance());
            } else if (id == R.id.nav_applications) {
                setFragment(getFragmentManager(), ApplicationsFragment.getInstance());
            } else if (id == R.id.nav_switch_to_landlord) {
                new AlertDialog.Builder(this)
                        .setTitle("Switch to Landlord Profile?")
                        .setMessage("Are you sure you want to switch to a landlord profile?")
                        .setPositiveButton("OK", (dialog, which) -> logout(Target.user))
                        .setNegativeButton("Cancel", null)
                        .show();
            }
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public static void setFragment(FragmentManager fragmentManager, Fragment fragment) {
        if (fragmentManager != null) {
            fragmentManager.beginTransaction()
                    .replace(R.id.frame_layout, fragment)
                    .addToBackStack(null)
                    .commit();
        }
    }

    public static void setFragmentBackstack(FragmentManager fragmentManager, Fragment fragment) {
        if (fragmentManager != null) {
            String tag = fragment.getClass().getSimpleName();
            fragmentManager.beginTransaction()
                    .replace(R.id.frame_layout, fragment)
                    .addToBackStack(tag)
                    .commit();
        }
    }

    private void logout(Target target) {
        LocalPrefs.purgePref(LocalPrefs.Pref.current_account, this);
        OAuthUtils.deleteFacebookToken(this, target);
        finish();
        startActivity(new Intent(this, CreateAccountActivity.class));
    }
}
