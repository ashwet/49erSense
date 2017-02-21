package com.example.raj.iot;

/**
 * Created by raj on 10/25/16.
 */



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
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends Activity{

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration);

        // getting/mapping the values from the main layout
        final EditText usrName = (EditText) findViewById(R.id.editText1);
        final EditText pwd = (EditText) findViewById(R.id.editText2);
        final EditText verifyPwd = (EditText) findViewById(R.id.editText3);
        final EditText firstName = (EditText) findViewById(R.id.editText4);
        final EditText lastName = (EditText) findViewById(R.id.editText5);
        final EditText eMail = (EditText) findViewById(R.id.editText6);
        final EditText phone = (EditText) findViewById(R.id.editText7);
        final CheckBox cBox = (CheckBox) findViewById(R.id.checkBox1);
        final TextView agreement = (TextView) findViewById(R.id.textView8);


        agreement.setTextColor(Color.BLUE);
        agreement.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                String url = "http://www.google.com/intl/en/policies/terms/";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);

            }
        });

        //mapping the button from the main layout
        Button resetButton = (Button) findViewById(R.id.button1);
        Button registerButton = (Button) findViewById(R.id.button2);
        Button backButton = (Button) findViewById(R.id.button3);

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
                cBox.setChecked(false);

            }
        });

        // setting functions for the endApp button. goes to main screen
        backButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();

            }
        });

        //setting functions to the register button
        // all validations are done here
        registerButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                //getting current string values from the view
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
                if (userName.length() == 0){
                    usrName.setError(getResources().getString(R.string.username_error_message));
                }
                else if (userName.length() < 6){
                    usrName.setError(getResources().getString(R.string.minimum_length_error));
                }
                else if (password.length() == 0){
                    pwd.setError(getResources().getString(R.string.password_error_message));
                }
                else if ((password.length() < 6)){
                    pwd.setError(getResources().getString(R.string.minimum_length_error));
                }
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
                else if(!cBox.isChecked()){
                    Toast.makeText(getBaseContext(),getResources().getString(R.string.agreement_error_message),Toast.LENGTH_SHORT).show();
                }
                //finally on successfull verification the appropriate message is displayed
                else {

                    String status = getConnection(userName,password,first_Name, last_Name, mail, phone_No);
                    if(status.equals("true\n")){
                        Toast.makeText(getBaseContext(),"Registration Successful!",Toast.LENGTH_SHORT).show();
                        finish();
                    }
                    else if(status.equals("false_user\n")){
                        Toast.makeText(getBaseContext(),"Username already exists. Please enter a different username!",Toast.LENGTH_SHORT).show();
                    }
                    else if(status.equals("false_email\n")){
                        Toast.makeText(getBaseContext(),"Email already registered. Please enter different email!",Toast.LENGTH_SHORT).show();
                    }
                    else

                        Toast.makeText(getBaseContext(),"Problem! Server returned invalid value",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public String getConnection(String usr, String pwd, String fName, String lName,String mail, String phone){

        InputStream inputStream = null;
        String result = "";
        ArrayList<NameValuePair> nameValuePairs1 = new ArrayList<NameValuePair>();
        nameValuePairs1.add(new BasicNameValuePair("username",usr));
        nameValuePairs1.add(new BasicNameValuePair("password",pwd));
        nameValuePairs1.add(new BasicNameValuePair("firstName",fName));
        nameValuePairs1.add(new BasicNameValuePair("lastName",lName));
        nameValuePairs1.add(new BasicNameValuePair("email",mail));
        nameValuePairs1.add(new BasicNameValuePair("phone",phone));

        //http postappSpinners
        try{
            HttpClient httpclient = new DefaultHttpClient();

            // have to change the ip here to correct ip
            HttpPost httppost = new HttpPost("http://"+Login.ip+"register.php");
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs1));
            HttpResponse response = httpclient.execute(httppost);
            HttpEntity entity = response.getEntity();
            inputStream = entity.getContent();
        }
        catch(Exception e){
            Log.e("log_tag", "Error in http connection "+e.toString());
            Toast.makeText(getBaseContext(), "Server Not Responding", Toast.LENGTH_SHORT).show();
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