package com.example.raj.iot;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
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
public class LightsFragment extends Fragment {
    private static SeekBar lightbar1;
    private static SeekBar lightbar2;

    private static SeekBar lightbar4;
    private static SeekBar lightbar5;
    private static TextView textView1;
    private static TextView textView2;

    private static TextView textView4;
    private static TextView textView5;
    private static ImageButton button;
    int button_living_flag = -1;
    int button_bedroom_flag = -1;
    int button_upbedroom_flag = -1;
    int button_kitchen_flag = -1;
    int button_basement_flag = -1;
    String user_id="1";
    //int progress_val1;
    //int progress_val2;
    //int progress_val3;
    //int progress_val4;
    //int progress_val5;

    public LightsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_lights, container, false);
        lightbar1 = (SeekBar)v.findViewById(R.id.seekbar_living);
        textView1 = (TextView)v.findViewById(R.id.progress_living);

        lightbar2 = (SeekBar)v.findViewById(R.id.seekbar_bedroom);
        textView2 = (TextView)v.findViewById(R.id.progress_bedroom);


        lightbar4= (SeekBar)v.findViewById(R.id.seekbar_kitchen);
        textView4 = (TextView)v.findViewById(R.id.progress_kitchen);

        lightbar5 = (SeekBar)v.findViewById(R.id.seekbar_basement);
        textView5 = (TextView)v.findViewById(R.id.progress_basement);
        button = (ImageButton) v.findViewById(R.id.living_on);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button_living_flag = 1;

                getConnection(user_id,"Living Room",textView1.getText().toString().replace("%",""));
                Toast.makeText(getActivity(),"Living Room Lights ON"+textView1.getText().toString().replace("%",""), Toast.LENGTH_SHORT).show();
            }
        });
        button = (ImageButton) v.findViewById(R.id.living_off);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button_living_flag = 0;
                getConnection(user_id,"Living Room",0+"");
                Toast.makeText(getActivity(),"Living Room Lights OFF", Toast.LENGTH_SHORT).show();
            }
        });
        button = (ImageButton) v.findViewById(R.id.bedroom_on);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getConnection(user_id,"Bedroom",textView2.getText().toString().replace("%","")+"");
                Toast.makeText(getActivity(),"Bedroom Lights ON"+textView2.getText().toString().replace("%","")+"", Toast.LENGTH_SHORT).show();
            }
        });
        button = (ImageButton) v.findViewById(R.id.bedroom_off);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getConnection(user_id,"Bedroom",0+"");
                Toast.makeText(getActivity(),"Bedroom Lights OFF", Toast.LENGTH_SHORT).show();
            }
        });

        button = (ImageButton) v.findViewById(R.id.kitchen_on);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getConnection(user_id,"Kitchen",textView4.getText().toString().replace("%","")+"");
                Toast.makeText(getActivity(),"Kitchen Lights ON", Toast.LENGTH_SHORT).show();
            }
        });
        button = (ImageButton) v.findViewById(R.id.kitchen_off);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getConnection(user_id,"Kitchen",0+"");
                Toast.makeText(getActivity(),"Kitchen Lights OFF", Toast.LENGTH_SHORT).show();
            }
        });

        button = (ImageButton) v.findViewById(R.id.basement_on);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getConnection(user_id,"Basement",textView5.getText().toString().replace("%","")+"");
                Toast.makeText(getActivity(),"Basement Lights ON"+lightbar5.getProgress(), Toast.LENGTH_SHORT).show();
            }
        });
        button = (ImageButton) v.findViewById(R.id.basement_off);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getConnection(user_id,"Basement",0+"");
                Toast.makeText(getActivity(),"Basement Lights OFF", Toast.LENGTH_SHORT).show();
            }
        });



        seek();

        return v;
    }


    public void seek() {
        textView1.setText(lightbar1.getProgress() + "%");
        textView2.setText(lightbar2.getProgress() + "%");

        textView4.setText(lightbar4.getProgress() + "%");
        textView5.setText(lightbar5.getProgress() + "%");
        //  if(button_living_flag == 1) {
        lightbar1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progress_val1;

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progress_val1 = progress;
                textView1.setText(progress + "%");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                textView1.setText(progress_val1 + "%");
            }
        });
        // }
        // else if(button_living_flag == 0)
        //    Toast.makeText(getActivity(),"Living Room Lights are OFF", Toast.LENGTH_SHORT).show();


        //textView2.setText(lightbar2.getProgress() + "%");
        // if(button_bedroom_flag == 1) {
        lightbar2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progress_val2;

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progress_val2 = progress;
                textView2.setText(progress + "%");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                textView2.setText(progress_val2 + "%");
            }
        });
        //}
        //else if(button_bedroom_flag == 0)
        //  Toast.makeText(getActivity(),"Bedroom Lights are OFF", Toast.LENGTH_SHORT).show();


        // textView3.setText(lightbar3.getProgress() + "%");
        //if(button_upbedroom_flag == 1) {
        lightbar4.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progress_val4;

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progress_val4 = progress;
                textView4.setText(progress + "%");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                textView4.setText(progress_val4 + "%");
            }
        });
        //}
        //else if(button_kitchen_flag == 0)
        //  Toast.makeText(getActivity(),"Kitchen Lights are OFF", Toast.LENGTH_SHORT).show();


        // textView5.setText(lightbar5.getProgress() + "%");
        //if(button_basement_flag == 1){
        lightbar5.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progress_val5;

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progress_val5 = progress;
                textView5.setText(progress + "%");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                textView5.setText(progress_val5 + "%");
            }
        });
        //}
        //else if(button_basement_flag == 0)
        //  Toast.makeText(getActivity(),"Basement Lights are OFF", Toast.LENGTH_SHORT).show();

    }
    public String getConnection(String user_id, String location, String dimmer_level){

        InputStream inputStream = null;
        String result = "";
        ArrayList<NameValuePair> nameValuePairs1 = new ArrayList<NameValuePair>();
        nameValuePairs1.add(new BasicNameValuePair("user_id",user_id));
        nameValuePairs1.add(new BasicNameValuePair("location",location));
        nameValuePairs1.add(new BasicNameValuePair("dimmer_level",dimmer_level));

        Toast.makeText(getActivity(),"Basement Lights ON"+nameValuePairs1, Toast.LENGTH_SHORT);
        //http postappSpinners
        try{
            HttpClient httpclient = new DefaultHttpClient();

            // have to change the ip here to correct ip
            HttpPost httppost = new HttpPost("http://"+Login.ip+"/lights.php");
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

