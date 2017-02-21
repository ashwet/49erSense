package com.example.raj.iot;

import android.app.Activity;

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

public class Login extends Activity {

    static String userString;
    static String pwdString;
    public static final String ip = "192.168.1.116";
    /** Called when the activity is first created. */
    private static final String TAG = "LoginActivity";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.main);
        Log.d(TAG, "In Login");
        setContentView(R.layout.login);

        final EditText userName= (EditText)findViewById(R.id.editText1);
        final EditText password= (EditText)findViewById(R.id.editText2);
        //password.setTransformationMethod(new PasswordTransformationMethod());
        password.setHint(R.string.password_hint);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);

        final Button register_button = (Button) findViewById(R.id.button3);
        register_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(isNetworkAvailable()){
                    Log.d("test","Network available");
                    Intent regActivity = new Intent(getBaseContext(),RegisterActivity.class);
                    startActivity(regActivity);
                }

                else{
                    Toast.makeText(getBaseContext(), R.string.noInternet, Toast.LENGTH_SHORT).show();
                }
            }
        });
        final Button login_button = (Button) findViewById(R.id.button2);
        login_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                userString = userName.getText().toString();
                pwdString = password.getText().toString();

                if(userString.equals("") ||pwdString.equals("")){
                    Toast.makeText(getBaseContext(),"Enter Username and Password!", Toast.LENGTH_SHORT).show();
                }
                else if(isNetworkAvailable()){
                    Log.d("test","Network available");

                    String passwd = getConnection(userString, pwdString);

                    if(!((passwd.equals("null\n")) || (passwd.equals("")))){

                        if(passwd.equals("true\n")){

                            Toast.makeText(getBaseContext(),"Login Successful!", Toast.LENGTH_SHORT).show();
                            Bundle bundle = new Bundle();
                            bundle.putString("username", userString);
                            Intent userHomeActivity = new Intent(getBaseContext(),MainActivity.class);

                            userHomeActivity.putExtras(bundle);
                            startActivity(userHomeActivity);

                        }
                        else
                            Toast.makeText(getBaseContext(),"Invalid Username/Password!", Toast.LENGTH_SHORT).show();
                    }
                    else
                        Toast.makeText(getBaseContext(),"Login Failed!", Toast.LENGTH_SHORT).show();
                }

                else{
                    Toast.makeText(getBaseContext(), R.string.noInternet, Toast.LENGTH_SHORT).show();
                }
            }
        });

        final Button Reset = (Button) findViewById(R.id.button1);
        Reset.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                userName.setText("");
                password.setText("");
            }
        });

        final Button exit_button = (Button) findViewById(R.id.button5);
        exit_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });
        final Button forgotButton = (Button) findViewById(R.id.button4);
        forgotButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent forgotIntent = new Intent(getBaseContext(),ForgotPasswordActivity.class);
                startActivity(forgotIntent);
            }
        });

    }


    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();

        final EditText userName= (EditText)findViewById(R.id.editText1);
        final EditText password= (EditText)findViewById(R.id.editText2);
        userName.setText("");
        password.setText("");
    }

    public boolean isNetworkAvailable() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        // if no network is available networkInfo will be null, otherwise check if we are connected
        if (networkInfo != null && networkInfo.isConnected()) {
            return true;
        }
        return false;
    }


    public String getConnection(String usr, String pwd){

        InputStream inputStream = null;
        String result = "";
        ArrayList<NameValuePair> nameValuePairs1 = new ArrayList<NameValuePair>();
        nameValuePairs1.add(new BasicNameValuePair("username",usr));
        nameValuePairs1.add(new BasicNameValuePair("password",pwd));

        //http postappSpinners
        try{
            HttpClient httpclient = new DefaultHttpClient();

            // have to change the ip here to correct ip
            HttpPost httppost = new HttpPost("http://"+Login.ip+"/login.php");
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
