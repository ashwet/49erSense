package com.example.raj.iot;

/**
 * Created by raj on 10/27/16.
 */
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

/**
 * Created by kdeek on 10/27/2016.
 */
public class setThermo extends AsyncTask<String,Void,String> {

    int thermostatID, fanmode,currenttemp,controltemp;
    String thermomode;
    setThermoInterface activity = null;

    public setThermo(setThermoInterface activity,int thermostatID, int fanmode, int currenttemp, int controltemp,String thermomode) {
        this.activity = activity ;
        this.thermomode = thermomode;
        this.thermostatID = thermostatID+1;
        this.controltemp = controltemp;
        this.currenttemp = currenttemp;
        this.fanmode = fanmode;
        Log.d("demo",
                "this.thermomode"+thermomode+
                        "this.thermostatID"+thermostatID+
                        "this.controltemp"+controltemp+
                        "this.currenttemp"+currenttemp
                        +"this.fanmode"+fanmode);

    }

    @Override
    protected String doInBackground(String... strings) {

        String data = null;
        try {
            data = URLEncoder.encode("thermostatID", "UTF-8")
                    + "=" + URLEncoder.encode(thermostatID+"", "UTF-8");

            data += "&" + URLEncoder.encode("themomode", "UTF-8") + "="
                    + URLEncoder.encode(thermomode, "UTF-8");

            data += "&" + URLEncoder.encode("fanmode", "UTF-8") + "="
                    + URLEncoder.encode(fanmode+"", "UTF-8");

            data += "&" + URLEncoder.encode("currenttemp", "UTF-8") + "="
                    + URLEncoder.encode(currenttemp+"", "UTF-8");

            data += "&" + URLEncoder.encode("controltemp", "UTF-8") + "="
                    + URLEncoder.encode(controltemp+"", "UTF-8");

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        String text = "";
        BufferedReader reader=null;

        try
        {

            // Defined URL  where to send data
            URL url = new URL("http://"+Login.ip+"/thermostat.php");
            URLConnection conn = url.openConnection();
            conn.setDoOutput(true);
            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
            wr.write( data );
            wr.flush();

            reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line = null;

            // Read Server Response
            while((line = reader.readLine()) != null)
            {
                // Append server response in string
                sb.append(line + "\n");
            }
            text = sb.toString();
        }

        catch(Exception ex)
        {
            Log.e("log_tag", "Error in thermo http connection "+ex.toString());
        }
        finally
        {
            try
            {
                reader.close();
            }

            catch(Exception ex) {}
        }

        return text;
    }



    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        activity.getResult(s);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    public static interface setThermoInterface{
        public void getResult(String resultpass);
    }

}