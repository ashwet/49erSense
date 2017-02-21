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
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class MotionSensorFragment extends Fragment implements View.OnClickListener {
    ArrayList<String> selection = new ArrayList<String>();
    ImageButton button;
    RadioButton main,up;
    View view;

    public MotionSensorFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        String final_selection = "";
        view = inflater.inflate(R.layout.fragment_motion_sensor, container, false);
        button = (ImageButton) view.findViewById(R.id.active);
        button.setOnClickListener(this);
        button = (ImageButton) view.findViewById(R.id.inactive);
        button.setOnClickListener(this);
        main = (RadioButton) view.findViewById(R.id.main);
        up = (RadioButton) view.findViewById(R.id.up);
        main.setOnClickListener(RadioButtonClickListener);
        up.setOnClickListener(RadioButtonClickListener);

        for(String Selections : selection){
            final_selection = final_selection + Selections + "\n";
        }
        return view;
    }

    View.OnClickListener RadioButtonClickListener = new View.OnClickListener() {

        public void onClick(View view) {
            boolean checked = ((RadioButton) view).isChecked();

            switch (view.getId()) {
                case R.id.main:
                    if (checked)
                        selection.add("main");
                    else
                        selection.remove("main");
                    break;

                case R.id.up:
                    if (checked)
                        selection.add("up");
                    else
                        selection.remove("up");
                    break;
            }
        }
    };

    public void onClick(View v)
    {
        String user_id = "1";
        String status = "";
        switch (v.getId())
        {
            case R.id.active:
                if(main.isChecked()) {
                    Toast.makeText(getActivity(), "1st Floor Motion Detector ON", Toast.LENGTH_SHORT).show();
                    status = getConnection(user_id, "1st Floor", "ON");
                }
                else if(up.isChecked()) {
                    Toast.makeText(getActivity(), "2nd Floor Motion Detector ON", Toast.LENGTH_SHORT).show();
                    status=getConnection(user_id, "2nd Floor", "ON");
                }
                else
                    Toast.makeText(getActivity(),"Select Floor", Toast.LENGTH_SHORT).show();

                break;
            case R.id.inactive:
                if(main.isChecked()) {
                    Toast.makeText(getActivity(), "1st Floor Motion Detector OFF", Toast.LENGTH_SHORT).show();
                    status=getConnection(user_id, "1st Floor", "OFF");
                }
                else if(up.isChecked()) {
                    Toast.makeText(getActivity(), "2nd Floor Motion Detector OFF", Toast.LENGTH_SHORT).show();
                    status=getConnection(user_id, "2nd Floor", "OFF");
                }
                else
                    Toast.makeText(getActivity(),"Select Floor", Toast.LENGTH_SHORT).show();
                break;

        }

        if(status.contains("Pi"))
            Toast.makeText(getActivity(),"Pi Not Connected",Toast.LENGTH_SHORT).show();
    }
    public String getConnection(String user_id, String floor, String status){

        InputStream inputStream = null;
        String result = "";
        ArrayList<NameValuePair> nameValuePairs1 = new ArrayList<NameValuePair>();
        nameValuePairs1.add(new BasicNameValuePair("user_id",user_id));
        nameValuePairs1.add(new BasicNameValuePair("floor",floor));
        nameValuePairs1.add(new BasicNameValuePair("status",status));


        //http postappSpinners
        try{
            HttpClient httpclient = new DefaultHttpClient();

            // have to change the ip here to correct ip
            HttpPost httppost = new HttpPost("http://"+Login.ip+"/motion_sensor.php");
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
