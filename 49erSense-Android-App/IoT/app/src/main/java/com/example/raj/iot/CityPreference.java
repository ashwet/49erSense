package com.example.raj.iot;

/**
 * Created by raj on 11/13/16.
 */

import android.app.Activity;
import android.content.SharedPreferences;

public class CityPreference {

    SharedPreferences prefs;

    public CityPreference(Activity activity){
        prefs = activity.getPreferences(Activity.MODE_PRIVATE);
    }


    public String getCity(){

        return prefs.getString("city", "Charlotte, NC");
    }

    void setCity(String city){
        prefs.edit().putString("city", city).commit();
    }

}