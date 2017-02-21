package com.example.raj.iot;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

/**
 * Created by raj on 11/13/16.
 */


public class ProduceActivity extends AppCompatActivity {
    Toolbar toolbar;
    TabLayout tablayout;
    ViewPager viewPager;
    viewPagerAdapter viewpageradapter;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.produce_activity);
        toolbar = (Toolbar)findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        tablayout = (TabLayout)findViewById(R.id.tabLayout);
        viewPager = (ViewPager)findViewById(R.id.viewPager);
        viewpageradapter = new viewPagerAdapter(getSupportFragmentManager());
        viewpageradapter.addfrags(new SecurityFragment(), "Security Systems");
        viewpageradapter.addfrags(new GarageFragment(), "Garage Doors");
        viewpageradapter.addfrags(new ThermostatFragment(), "Thermo Stat");
        viewpageradapter.addfrags(new LightsFragment(), "Light Control");
        viewpageradapter.addfrags(new LocksFragment(), "Locks");
        viewPager.setAdapter(viewpageradapter);
        tablayout.setupWithViewPager(viewPager);
    }
}