package com.example.raj.iot;


import android.media.Image;
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
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class SensorsFragment extends Fragment implements View.OnClickListener{
    ArrayList<String> selection = new ArrayList<String>();
    ImageButton button;
    RadioButton sensor_main,sensor_up,main_door,window1,window3, up_door;
    View view;

    public SensorsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        String final_selection = "";
        view = inflater.inflate(R.layout.fragment_sensors, container, false);
        button = (ImageButton) view.findViewById(R.id.on);
        button.setOnClickListener(this);
        button = (ImageButton) view.findViewById(R.id.off);
        button.setOnClickListener(this);
        sensor_main = (RadioButton) view.findViewById(R.id.sensor_main);
        main_door = (RadioButton) view.findViewById(R.id.main_door);
        window1 = (RadioButton) view.findViewById(R.id.window1);

        sensor_up = (RadioButton) view.findViewById(R.id.sensor_up);

        sensor_main.setOnClickListener(RadioButtonClickListener);
        main_door.setOnClickListener(RadioButtonClickListener);
        window1.setOnClickListener(RadioButtonClickListener);
        sensor_up.setOnClickListener(RadioButtonClickListener);

        for(String Selections : selection){
            final_selection = final_selection + Selections + "\n";
        }
        return view;
    }

    View.OnClickListener RadioButtonClickListener = new View.OnClickListener() {

        public void onClick(View view) {
            boolean checked = ((RadioButton) view).isChecked();

            switch (view.getId()) {
                case R.id.sensor_main:
                    if (checked)
                        selection.add("sensor_main");
                    else
                        selection.remove("sensor_main");
                    break;
                case R.id.main_door:
                    if(sensor_main.isChecked()) {
                        if (checked)
                            selection.add("main_door");
                        else
                            selection.remove("main_door");
                    }
                    else
                        Toast.makeText(getActivity(),"Select Floor", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.window1:
                    if(sensor_main.isChecked()) {
                        if (checked)
                            selection.add("window1");
                        else
                            selection.remove("window1");
                    }
                    else
                        Toast.makeText(getActivity(),"Select Floor", Toast.LENGTH_SHORT).show();
                    break;

                case R.id.sensor_up:
                    if (checked)
                        selection.add("sensor_up");
                    else
                        selection.remove("sensor_up");
                    break;

            }
        }
    };
    public void onClick(View v)
    {
        String user_id = "1";
        String status1 = "";
        switch (v.getId())
        {
            case R.id.on:
                if (sensor_main.isChecked()) {

                    if (main_door.isChecked()) {
                        Toast.makeText(getActivity(), "1st Floor Door is Open", Toast.LENGTH_SHORT).show();
                        status1 = getConnection(user_id, "1st Floor", "Door", "Open");
                    } else if (window1.isChecked()) {
                        Toast.makeText(getActivity(), "1st Floor Window is Open", Toast.LENGTH_SHORT).show();
                        status1 = getConnection(user_id, "1st Floor", "Window", "Open");
                    }
                    else {
                        Toast.makeText(getActivity(), "Door/Window not selected", Toast.LENGTH_SHORT).show();

                    }

                }

                if (sensor_up.isChecked()) {

                    if (main_door.isChecked()) {
                        Toast.makeText(getActivity(), "2nd Floor Door is Open", Toast.LENGTH_SHORT).show();
                        status1 = getConnection(user_id, "2nd Floor", "Door", "Open");
                    } else if (window1.isChecked()) {
                        Toast.makeText(getActivity(), "2nd Floor Window is Open", Toast.LENGTH_SHORT).show();
                        status1 = getConnection(user_id, "2nd Floor", "Window", "Open");
                    }
                    else {
                        Toast.makeText(getActivity(), "Door/Window not selected", Toast.LENGTH_SHORT).show();

                    }
                }


                break;
            case R.id.off:
                if(sensor_main.isChecked()) {
                    if (main_door.isChecked()) {
                        Toast.makeText(getActivity(), "1st Floor Door Closed", Toast.LENGTH_SHORT).show();
                        status1 = getConnection(user_id, "1st Floor", "Door", "Closed");
                    } else if (window1.isChecked()) {
                        Toast.makeText(getActivity(), "1st Floor Window Closed", Toast.LENGTH_SHORT).show();
                        status1 = getConnection(user_id, "1st Floor", "Window", "Closed");
                    }
                    else
                        Toast.makeText(getActivity(),"Door/Window not selected", Toast.LENGTH_SHORT).show();
                    break;
                }
                if(sensor_up.isChecked()){
                    if (main_door.isChecked()) {
                        Toast.makeText(getActivity(), "2nd Floor Door Closed", Toast.LENGTH_SHORT).show();
                        status1 = getConnection(user_id, "2nd Floor", "Door", "Closed");
                    } else if (window1.isChecked()) {
                        Toast.makeText(getActivity(), "2nd Floor Window Closed", Toast.LENGTH_SHORT).show();
                        status1 = getConnection(user_id, "2nd Floor", "Window", "Closed");
                    }
                    else
                        Toast.makeText(getActivity(),"Door/Window not selected", Toast.LENGTH_SHORT).show();
                    break;

                }
        }
        if(status1.contains("Pi"))
            Toast.makeText(getActivity(),"Pi Not Connected",Toast.LENGTH_SHORT).show();
    }

    public String getConnection(String user_id, String floor, String location, String status){

        InputStream inputStream = null;
        String result = "";
        ArrayList<NameValuePair> nameValuePairs1 = new ArrayList<NameValuePair>();
        nameValuePairs1.add(new BasicNameValuePair("user_id",user_id));
        nameValuePairs1.add(new BasicNameValuePair("floor",floor));
        nameValuePairs1.add(new BasicNameValuePair("location",location));
        nameValuePairs1.add(new BasicNameValuePair("status",status));



        //http postappSpinners
        try{
            HttpClient httpclient = new DefaultHttpClient();

            // have to change the ip here to correct ip
            HttpPost httppost = new HttpPost("http://"+Login.ip+"/sensors.php");
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
