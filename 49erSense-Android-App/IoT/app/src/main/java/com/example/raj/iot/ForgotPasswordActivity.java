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
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ForgotPasswordActivity extends Activity{

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgot_password);

        Button backButton = (Button)findViewById(R.id.button1);
        Button submitButton = (Button)findViewById(R.id.button2);

        backButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                finish();
            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                EditText email = (EditText) findViewById(R.id.editText1);
                String emailString = email.getText().toString();
                boolean emailCheck = android.util.Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches();
                if (emailString.length() == 0){
                    email.setError(getResources().getString(R.string.email_error_message));
                }
                else if (!emailCheck){
                    email.setError(getResources().getString(R.string.emailpattern_error_message));
                }
                else{
                    String resetPasswordStatus = getConnection(emailString);

                    if(resetPasswordStatus.equals("true\n")){
                        Toast.makeText(getBaseContext(),"Your new login details has been emailed to you. Please check your inbox!", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                    else if(resetPasswordStatus.equals("false_mail\n"))
                        Toast.makeText(getBaseContext(),"Error sending email! Please Contact Administrator", Toast.LENGTH_SHORT).show();
                    else if(resetPasswordStatus.equals("false_update\n"))
                        Toast.makeText(getBaseContext(),"Error resetting password! Please Contact Administrator", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(getBaseContext(),"Email address not registered!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public String getConnection(String email){

        InputStream inputStream = null;
        String result = "";
        ArrayList<NameValuePair> nameValuePairs1 = new ArrayList<NameValuePair>();
        nameValuePairs1.add(new BasicNameValuePair("email",email));

        //http postappSpinners
        try{
            HttpClient httpclient = new DefaultHttpClient();

            // have to change the ip here to your ip
            HttpPost httppost = new HttpPost("http://"+Login.ip+"/sendmail.php");
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs1));
            HttpResponse response = httpclient.execute(httppost);
            HttpEntity entity = response.getEntity();
            inputStream = entity.getContent();
        }
        catch(Exception e){
            Log.e("log_tag", "Error in http connection "+e.toString());
            Toast.makeText(getBaseContext(), "Server Not Responding!", Toast.LENGTH_SHORT).show();
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