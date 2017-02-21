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
public class GarageFragment extends Fragment implements View.OnClickListener{
    ArrayList<String> selection = new ArrayList<String>();
    ImageButton button;
    RadioButton car1,car2;
    View view;


    public GarageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        String final_selection = "";
        view = inflater.inflate(R.layout.fragment_garage, container, false);
        button = (ImageButton) view.findViewById(R.id.open);
        button.setOnClickListener(this);
        button = (ImageButton) view.findViewById(R.id.close);
        button.setOnClickListener(this);
        car1 = (RadioButton) view.findViewById(R.id.car1);
        car2 = (RadioButton) view.findViewById(R.id.car2);
        car1.setOnClickListener(RadioButtonClickListener);
        car2.setOnClickListener(RadioButtonClickListener);

        for(String Selections : selection){
            final_selection = final_selection + Selections + "\n";
        }
        return view;
    }

    View.OnClickListener RadioButtonClickListener = new View.OnClickListener() {

        public void onClick(View view) {
            boolean checked = ((RadioButton) view).isChecked();

            switch (view.getId()) {
                case R.id.car1:
                    if (checked)
                        selection.add("car1");
                    else
                        selection.remove("car1");
                    break;

                case R.id.car2:
                    if (checked)
                        selection.add("car2");
                    else
                        selection.remove("car2");
                    break;
            }
        }
    };

    public void onClick(View v)
    {
        String user_id = "1";
        String status="";
        switch (v.getId())
        {
            case R.id.open:
                if(car1.isChecked()) {
                    Toast.makeText(getActivity(), "1-Car Garage Door Opened", Toast.LENGTH_SHORT).show();
                    String status1 = "Open";
                    String status2 = "";
                    status=getConnection(user_id,status1,status2);
                }
                else if(car2.isChecked()) {
                    Toast.makeText(getActivity(), "2-Car Garage Door Opened", Toast.LENGTH_SHORT).show();
                    //String car1 = " ";
                    String status1 = "";
                    // String car2 = "2-Car Door";
                    String status2 = "Open";
                    status=getConnection(user_id,status1,status2);
                }
                else
                    Toast.makeText(getActivity(),"Select Garage Door", Toast.LENGTH_SHORT).show();
                break;
            case R.id.close:
                if(car1.isChecked()) {
                    Toast.makeText(getActivity(), "1-Car Garage Door Closed", Toast.LENGTH_SHORT).show();
                    //String car1 = "1-Car Door";
                    String status1 = "Close";
                    //String car2 = " ";
                    String status2 = "";
                    //Toast.makeText(getActivity(), status1 + status2, Toast.LENGTH_SHORT).show();;
                    status=getConnection(user_id,status1,status2);
                }
                else if(car2.isChecked()) {
                    Toast.makeText(getActivity(), "2-Car Garage Door Closed", Toast.LENGTH_SHORT).show();
                    // String car1 = " ";
                    String status1 = "";
                    // String car2 = "2-Car Door";
                    String status2 = "Close";
                    status=getConnection(user_id,status1,status2);
                }
                else
                    Toast.makeText(getActivity(),"Select Garage Door", Toast.LENGTH_SHORT).show();
                break;

        }
        if(status.contains("Pi"))
            Toast.makeText(getActivity(),"Pi Not Connected",Toast.LENGTH_SHORT).show();
    }

    public String getConnection(String user_id, String status1, String status2){

        InputStream inputStream = null;
        String result = "";
        ArrayList<NameValuePair> nameValuePairs1 = new ArrayList<NameValuePair>();
        //nameValuePairs1.add(new BasicNameValuePair("car1",car1));

        nameValuePairs1.add(new BasicNameValuePair("user_id",user_id));
        nameValuePairs1.add(new BasicNameValuePair("status1",status1));
        nameValuePairs1.add(new BasicNameValuePair("status2",status2));



        try{
            HttpClient httpclient = new DefaultHttpClient();

            // have to change the ip here to correct ip
            HttpPost httppost = new HttpPost("http://"+Login.ip+"/garage.php");
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