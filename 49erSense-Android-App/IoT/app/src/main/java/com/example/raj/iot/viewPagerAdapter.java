package com.example.raj.iot;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import java.util.ArrayList;

/**
 * Created by raj on 11/13/16.
 */

public class viewPagerAdapter extends FragmentPagerAdapter {
    ArrayList<Fragment> frag = new ArrayList<>();
    ArrayList<String> titles = new ArrayList<>();

    public viewPagerAdapter(FragmentManager fm) {
        super(fm);
    }
    public void addfrags(Fragment fragment, String tabtitles){
        this.frag.add(fragment);
        this.titles.add(tabtitles);
    }
    @Override
    public Fragment getItem(int position) {
        return frag.get(position);
    }

    @Override
    public int getCount() {
        return titles.size();
    }
    public CharSequence getPageTitle(int position){

        return titles.get(position);
    }
}
