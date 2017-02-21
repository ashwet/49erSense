package com.example.raj.iot;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Switch;


/**
 * A simple {@link Fragment} subclass.
 */
public class ThermostatFragment extends Fragment implements setThermo.setThermoInterface{

    View view;
    Button mode;
    ImageButton contUp,contDown;
    EditText curTemp,contTemp;
    static int curTempInt,contTempInt;
    Switch therSel,fanMode;
    static int therSelInt=0,fanModeInt=0;
    String modeString = "Heat";


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_thermostat, container, false);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mode = (Button) getView().findViewById(R.id.Modethermo);
        contUp = (ImageButton) getView().findViewById(R.id.contTempUp);
        contDown = (ImageButton) getView().findViewById(R.id.contTempDown);
        therSel = (Switch) getView().findViewById(R.id.thermoSelect);
        fanMode = (Switch) getView().findViewById(R.id.fanSel);
        curTemp = (EditText) getView().findViewById(R.id.curTemp);
        contTemp = (EditText) getView().findViewById(R.id.controlTemp);

        contTemp.setClickable(false);

        mode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(modeString.equals("Heat")) modeString = "Cold";
                else if(modeString.equals("Cold")) modeString = "Off";
                else if(modeString.equals("Off")) modeString = "Heat";
                mode.setText(modeString);
                //setThermoInterface activity,int thermostatID, int fanmode, int currenttemp, int controltemp,String thermomode
                if (therSel.isChecked())therSelInt = 1;
                else    therSelInt = 0;

                if (fanMode.isChecked())fanModeInt = 1;
                else    fanModeInt = 0;

                new setThermo(ThermostatFragment.this,therSelInt,fanModeInt,Integer.parseInt(curTemp.getText().toString()),Integer.parseInt(contTemp.getText().toString()),modeString).execute();
            }
        });


        therSel.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (therSel.isChecked())therSelInt = 1;
                else    therSelInt = 0;
                new setThermo(ThermostatFragment.this,therSelInt,fanModeInt,Integer.parseInt(curTemp.getText().toString()),Integer.parseInt(contTemp.getText().toString()),modeString).execute();

            }
        });
        fanMode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (fanMode.isChecked())fanModeInt = 1;
                else    fanModeInt = 0;
                new setThermo(ThermostatFragment.this,therSelInt,fanModeInt,Integer.parseInt(curTemp.getText().toString()),Integer.parseInt(contTemp.getText().toString()),modeString).execute();


            }
        });

        contUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contTemp.setText( (Integer.parseInt(contTemp.getText().toString())+1)+"");
                new setThermo(ThermostatFragment.this,therSelInt,fanModeInt,Integer.parseInt(curTemp.getText().toString()),Integer.parseInt(contTemp.getText().toString()),modeString).execute();
            }
        });

        contDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contTemp.setText( (Integer.parseInt(contTemp.getText().toString())-1)+"");
                new setThermo(ThermostatFragment.this,therSelInt,fanModeInt,Integer.parseInt(curTemp.getText().toString()),Integer.parseInt(contTemp.getText().toString()),modeString).execute();
            }
        });

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void getResult(String resultpass) {

    }
}
