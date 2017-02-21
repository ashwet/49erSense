package com.example.raj.iot;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

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
public class SecurityFragment extends Fragment implements View.OnClickListener {

    ImageButton button;
    ImageSwitcher sw;
    View view;
    public SecurityFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_security_, container, false);
        button = (ImageButton) view.findViewById(R.id.securityDisarmed);
        button.setOnClickListener(this);
        button = (ImageButton) view.findViewById(R.id.securityArmedAway);
        button.setOnClickListener(this);
        button = (ImageButton) view.findViewById(R.id.securityArmedStay);
        button.setOnClickListener(this);


        return view;
    }

    public void onClick(View v)
    {
        String user_id = "1";
        String status="";
        switch (v.getId())
        {
            case R.id.securityDisarmed:
                //sw.setImageResource(R.drawable.security);
                Toast.makeText(getActivity(),"Security System Disarmed",Toast.LENGTH_SHORT).show();
                status=getConnection(user_id, "Security System Disarmed");
                //Toast.makeText(getActivity(),status,Toast.LENGTH_SHORT).show();

                break;
            case R.id.securityArmedStay:
               // sw.setImageResource(R.drawable.security);
                status=getConnection(user_id, "Security System Armed (Stay)");
                Toast.makeText(getActivity(),"Security System Armed (Stay)",Toast.LENGTH_SHORT).show();
                //Toast.makeText(getActivity(),status,Toast.LENGTH_SHORT).show();

                break;
            case R.id.securityArmedAway:
             //   sw.setImageResource(R.drawable.security);
                status=getConnection(user_id, "Security System Armed (Away)");
                Toast.makeText(getActivity(),"Security System Armed (Away)",Toast.LENGTH_SHORT).show();
                //Toast.makeText(getActivity(),status,Toast.LENGTH_SHORT).show();

                break;
            default:
                Toast.makeText(getActivity(),"in Default",Toast.LENGTH_SHORT).show();
                break;

        }
        if(status.contains("Pi"))
            Toast.makeText(getActivity(),"Pi Not Connected",Toast.LENGTH_SHORT).show();
    }

    public String getConnection(String user_id, String status){

        InputStream inputStream = null;
        String result = "";
        ArrayList<NameValuePair> nameValuePairs1 = new ArrayList<NameValuePair>();
        nameValuePairs1.add(new BasicNameValuePair("user_id",user_id));
        nameValuePairs1.add(new BasicNameValuePair("status",status));


        //http postappSpinners
        try{
            HttpClient httpclient = new DefaultHttpClient();

            // have to change the ip here to correct ip
            HttpPost httppost = new HttpPost("http://"+Login.ip+"/security_system.php");
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
