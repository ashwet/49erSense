package com.example.raj.iot;

import android.os.Bundle;
import android.content.Intent;

import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageSwitcher;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.security.Security;

import static com.example.raj.iot.Login.userString;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = "MyActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }/* else {
            //super.onBackPressed();
           // HomeFragment homeFragment = new HomeFragment();
           // FragmentManager manager = getSupportFragmentManager();
          //  manager.beginTransaction().replace(R.id.content_main, homeFragment).commit();



        }*/
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        /*if(id == R.id.nav_home){
            Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show();
            HomeFragment homeFragment = new HomeFragment();
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.content_main, homeFragment).commit();
        }*/
        if (id == R.id.nav_security_system) {
            // Handle the camera action
            Toast.makeText(this, "Security System", Toast.LENGTH_SHORT).show();
            SecurityFragment securityFragment = new SecurityFragment();
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.content_main, securityFragment).commit();

        } else if (id == R.id.nav_images) {
            Toast.makeText(this, "Images", Toast.LENGTH_SHORT).show();
            ImageFragment imageFragment = new ImageFragment();
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.content_main, imageFragment).commit();

        } else if (id == R.id.nav_locks) {
            Toast.makeText(this, "Locks", Toast.LENGTH_SHORT).show();
            LocksFragment locksFragment = new LocksFragment();
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.content_main, locksFragment).commit();

        }
        else if (id == R.id.nav_thermostat) {
            Toast.makeText(this, "Thermostat", Toast.LENGTH_SHORT).show();
            ThermostatFragment thermostatFragment = new ThermostatFragment();
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.content_main, thermostatFragment).commit();

        }else if (id == R.id.nav_garage) {
            Toast.makeText(this, "Garage Doors", Toast.LENGTH_SHORT).show();
            GarageFragment garageFragment = new GarageFragment();
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.content_main, garageFragment).commit();

        } else if (id == R.id.nav_videos) {
            Toast.makeText(this, "Video", Toast.LENGTH_SHORT).show();
            VideoFragment videoFragment = new VideoFragment();
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.content_main, videoFragment).commit();


        } else if (id == R.id.nav_lights) {
            Toast.makeText(this, "Lights", Toast.LENGTH_SHORT).show();
            LightsFragment lightsFragment = new LightsFragment();
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.content_main, lightsFragment).commit();

        }
        else if (id == R.id.nav_motion_sensors) {
            Toast.makeText(this, "Motion Sensors", Toast.LENGTH_SHORT).show();
            MotionSensorFragment motionSensorFragment = new MotionSensorFragment();
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.content_main, motionSensorFragment).commit();

        }
        else if (id == R.id.nav_sensors) {
            Toast.makeText(this, "Sensors", Toast.LENGTH_SHORT).show();
            SensorsFragment sensorsFragment = new SensorsFragment();
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.content_main, sensorsFragment).commit();

        }

        else if (id == R.id.nav_weather) {
            Toast.makeText(this, "Weather", Toast.LENGTH_SHORT).show();
            WeatherFragment weatherFragment = new WeatherFragment();
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.content_main, weatherFragment).commit();

        }


        else if (id == R.id.nav_energy) {
            Toast.makeText(this, "Energy", Toast.LENGTH_SHORT).show();
            EnergyFragment energyFragment = new EnergyFragment();
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.content_main, energyFragment).commit();

        }
        else if(id == R.id.nav_logout) {
            Toast.makeText(this, "Logging Out", Toast.LENGTH_SHORT).show();


        }
        else if(id == R.id.nav_manage_account) {
            Toast.makeText(this, "Manage your Account", Toast.LENGTH_SHORT).show();
            ManageAccountFragment manageAccountFragment = new ManageAccountFragment();
            Bundle bundle = new Bundle();
            bundle.putString("username", userString);
            manageAccountFragment.setArguments(bundle);
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.content_main, manageAccountFragment).commit();


        }
        else if (id == R.id.nav_exit) {
            Toast.makeText(this, "Exiting", Toast.LENGTH_SHORT).show();
            finish();

        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
