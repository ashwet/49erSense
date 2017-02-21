package com.example.raj.iot;


import android.os.Bundle;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import static com.example.raj.iot.R.id.container;


/**
 * A simple {@link Fragment} subclass.
 */
public class ManageAccountFragment extends Fragment {
View view;

    public ManageAccountFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        String user = getArguments().getString("username");

// getting/mapping the values from the main layout
        final EditText usrName = (EditText) view.findViewById(R.id.editText1);
        final EditText pwd = (EditText) view.findViewById(R.id.editText2);
        final EditText verifyPwd = (EditText) view.findViewById(R.id.editText3);
        final EditText firstName = (EditText) view.findViewById(R.id.editText4);
        final EditText lastName = (EditText) view.findViewById(R.id.editText5);
        final EditText eMail = (EditText) view.findViewById(R.id.editText6);
        final EditText phone = (EditText) view.findViewById(R.id.editText7);
        final TextView agreement = (TextView) view.findViewById(R.id.textView8);

        //bundle = getIntent().getExtras();


        String conResult = getConnection ("http://"+Login.ip+"/manageAccount.php","retrieve",user,"","","","","");
        try{
            JSONArray jArray = new JSONArray(conResult);

            //for(int i=0;i<jArray.length();i++){
            for(int i=0;i<jArray.length();i++){
                JSONObject json_data = jArray.getJSONObject(i);

                String userName =json_data.getString("username");
                String password = json_data.getString("password");
                String firstname =json_data.getString("firstname");
                String lastname =json_data.getString("lastname");
                String email =json_data.getString("email");
                String phoneNo =json_data.getString("phone");

                usrName.setText(userName);
                pwd.setText(password);
                verifyPwd.setText(password);
                firstName.setText(firstname);
                lastName.setText(lastname);
                eMail.setText(email);
                phone.setText(phoneNo);

            }
        }

        catch(JSONException e){
            //Log.e("log_tag", "Error parsing data "+e.toString());
            Log.e("log_tag", "Error parsing data "+e.toString());
        }

        usrName.setEnabled(false);


        Button resetButton = (Button) view.findViewById(R.id.button1);
        Button updateButton = (Button)view.findViewById(R.id.button2);
        Button backButton = (Button) view.findViewById(R.id.button3);
        // setting functions for the reset button
        resetButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                usrName.setText("");
                pwd.setText("");
                verifyPwd.setText("");
                firstName.setText("");
                lastName.setText("");
                phone.setText("");
                eMail.setText("");

            }
        });
        // setting functions for the endApp button. goes to main screen


        updateButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                //gettting current string values from the view
                String userName = usrName.getText().toString();
                String password = pwd.getText().toString();
                String vPassword = verifyPwd.getText().toString();
                String first_Name = firstName.getText().toString();
                String last_Name = lastName.getText().toString();
                String mail = eMail.getText().toString();
                String phone_No = phone.getText().toString();



                //boolean value to check the email id format
                boolean emailCheck = android.util.Patterns.EMAIL_ADDRESS.matcher(eMail.getText().toString()).matches();

                // all the validations are done in sequence
                if (password.length() == 0){
                    pwd.setError(getResources().getString(R.string.password_error_message));
                }
				/*else if ((password.length() < 6)){
				pwd.setError(getResources().getString(R.string.minimum_length_error));
			}*/
                else if (vPassword.length() == 0){
                    verifyPwd.setError(getResources().getString(R.string.password_error_message));
                }
                else if (!password.equals(vPassword)){
                    verifyPwd.setError(getResources().getString(R.string.passwordmatch_error_message));
                }
                else if (first_Name.length() == 0){
                    firstName.setError(getResources().getString(R.string.fName_error_message));
                }
                else if (last_Name.length() == 0){
                    lastName.setError(getResources().getString(R.string.lName_error_message));
                }
                else if (mail.length() == 0){
                    eMail.setError(getResources().getString(R.string.email_error_message));
                }
                else if (!emailCheck){
                    eMail.setError(getResources().getString(R.string.emailpattern_error_message));
                }
                else if (phone_No.length() == 0){
                    eMail.setError(getResources().getString(R.string.phone_error_message));
                }
                //finally on successfull verification the appropriate message is displayed
                else {

                    getConnection("http://"+Login.ip+"/manageAccount.php","update",userName,password,first_Name, last_Name, mail, phone_No);
                    Toast.makeText(getActivity(),"Account Details Updated!", Toast.LENGTH_SHORT).show();

                }
            }
        });
        return inflater.inflate(R.layout.fragment_manage_account, container, false);
    }

    public String getConnection(String url, String request, String usr, String pwd,
                                String fName, String lName,String mail, String phone){

        Bundle bundle = new Bundle();
        Message msg = new Message();
        InputStream inputStream = null;
        String result = "";
        ArrayList<NameValuePair> nameValuePairs1 = new ArrayList<NameValuePair>();
        nameValuePairs1.add(new BasicNameValuePair("request",request));
        nameValuePairs1.add(new BasicNameValuePair("username",usr));
        if(request.equals("update")){

            nameValuePairs1.add(new BasicNameValuePair("password",pwd));
            nameValuePairs1.add(new BasicNameValuePair("firstname",fName));
            nameValuePairs1.add(new BasicNameValuePair("lastname",lName));
            nameValuePairs1.add(new BasicNameValuePair("email",mail));
            nameValuePairs1.add(new BasicNameValuePair("phone",phone));
        }

        //http postappSpinners
        try{
            HttpClient httpclient = new DefaultHttpClient();

            // have to change the ip here to your ip
            HttpPost httppost = new HttpPost(url);
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs1));
            HttpResponse response = httpclient.execute(httppost);
            HttpEntity entity = response.getEntity();
            inputStream = entity.getContent();
        }
        catch(Exception e){
            Log.e("log_tag", "Error in http connection "+e.toString());
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
