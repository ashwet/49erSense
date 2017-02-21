package com.example.raj.iot;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class VideoFragment extends Fragment  {

    Button indoor,outdoor;
    View view;

    public VideoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        Toast.makeText(getActivity(), "Live Video Feed!", Toast.LENGTH_SHORT).show();

        return inflater.inflate(R.layout.fragment_video, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        indoor = (Button)getActivity().findViewById(R.id.indoor);
        indoor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse("http://192.168.1.111:8888"));
                getActivity().startActivity(i);

            }

        });

        outdoor = (Button)getActivity().findViewById(R.id.outdoor);
        outdoor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse("http://192.168.1.114:8081"));
                getActivity().startActivity(i);

            }
        });
    }


}
