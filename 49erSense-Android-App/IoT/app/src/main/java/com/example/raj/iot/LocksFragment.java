package com.example.raj.iot;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.ImageButton;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;



/**
 * A simple {@link Fragment} subclass.
 */
public class LocksFragment extends Fragment implements View.OnClickListener{
    ArrayList<String> selection = new ArrayList<String>();
    ImageButton button;
    RadioButton front,back,garage;
    View view;
    String door,status;

    public LocksFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        String final_selection = "";
        view = inflater.inflate(R.layout.fragment_locks, container, false);
        button = (ImageButton) view.findViewById(R.id.locked);
        button.setOnClickListener(this);
        button = (ImageButton) view.findViewById(R.id.unlocked);
        button.setOnClickListener(this);
        front = (RadioButton) view.findViewById(R.id.front);
        back = (RadioButton) view.findViewById(R.id.back);
        garage = (RadioButton) view.findViewById(R.id.garage);
        front.setOnClickListener(RadioButtonClickListener);
        back.setOnClickListener(RadioButtonClickListener);
        garage.setOnClickListener(RadioButtonClickListener);

        for(String Selections : selection){
            final_selection = final_selection + Selections + "\n";
        }
        return view;
    }

    View.OnClickListener RadioButtonClickListener = new View.OnClickListener() {

        public void onClick(View view) {
            boolean checked = ((RadioButton) view).isChecked();

            switch (view.getId()) {
                case R.id.front:
                    if (checked)
                        selection.add("front");
                    else
                        selection.remove("front");
                    break;

                case R.id.back:
                    if (checked)
                        selection.add("back");
                    else
                        selection.remove("back");
                    break;

                case R.id.garage:
                    if (checked)
                        selection.add("garage");
                    else
                        selection.remove("garage");
                    break;
            }
        }
    };

    public void onClick(View v)
    {
        String status1 = "";
        switch (v.getId())
        {
            case R.id.unlocked:
                if(front.isChecked()){
                    Toast.makeText(getActivity(),"Front Door Unlocked", Toast.LENGTH_SHORT).show();
                    String door ="Front Door";
                    String status = "Unlocked";
                    status1=getConnection(door, status);
                }
                else if(back.isChecked()) {
                    Toast.makeText(getActivity(), "Back Door Unlocked", Toast.LENGTH_SHORT).show();
                    String door = "Back Door";
                    String status = "Unlocked";
                    status1= getConnection(door, status);
                }
                else if(garage.isChecked()) {
                    Toast.makeText(getActivity(), "Garage Door Unlocked", Toast.LENGTH_SHORT).show();
                    String door ="Garage Door";
                    String status = "Unlocked";
                    status1 = getConnection(door, status);
                }
                else {
                    Toast.makeText(getActivity(), "Please Select Door", Toast.LENGTH_SHORT).show();

                }
                break;
            case R.id.locked:
                if(front.isChecked()) {
                    Toast.makeText(getActivity(), "Front Door Locked", Toast.LENGTH_SHORT).show();
                    String door ="Front Door";
                    String status = "Locked";
                    status1 = getConnection(door, status);
                   // arm = getConnection(arm);
                }
                else if(back.isChecked()) {
                    Toast.makeText(getActivity(), "Back Door Locked", Toast.LENGTH_SHORT).show();
                    String door ="Back Door";
                    String status = "Locked";
                    status1 = getConnection(door, status);

                }
                else if(garage.isChecked()){
                    Toast.makeText(getActivity(),"Garage Door Locked", Toast.LENGTH_SHORT).show();
                    String door ="Garage Door";
                    String status = "Locked";
                    status1=getConnection(door, status);
                }
                else{
                    Toast.makeText(getActivity(),"Select Door", Toast.LENGTH_SHORT).show();
                  //  arm = getConnection(arm);
                }
                break;


        }
        if(status1.contains("Pi"))
            Toast.makeText(getActivity(),"Pi Not Connected",Toast.LENGTH_SHORT).show();
    }

    public String getConnection(String door, String status){

        InputStream inputStream = null;
        String result = "";
        ArrayList<NameValuePair> nameValuePairs1 = new ArrayList<NameValuePair>();
        nameValuePairs1.add(new BasicNameValuePair("door",door));
        nameValuePairs1.add(new BasicNameValuePair("status",status));


        //http postappSpinners
        try{
            HttpClient httpclient = new DefaultHttpClient();

            // have to change the ip here to correct ip
            HttpPost httppost = new HttpPost("http://"+Login.ip+"/locks.php");
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs1));
            HttpResponse response = httpclient.execute(httppost);
            HttpEntity entity = response.getEntity();
            inputStream = entity.getContent();
        }
        catch(Exception e){
            Log.e("log_tag", "Error in http connection "+e.toString());
            Toast.makeText(getActivity(), "Server Not Responding", Toast.LENGTH_SHORT).show();
            return "";
        }
        //convert response to string
        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"),8);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            inputStream.close();
            result=sb.toString();
        }
        catch(Exception e){
            Log.e("log_tag", "Error converting result "+e.toString());
        }
        return result;

    }


}
