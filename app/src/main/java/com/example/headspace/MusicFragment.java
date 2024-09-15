package com.example.headspace;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;


public class MusicFragment extends Fragment {
    Button btnInstrumental;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

       View view=inflater.inflate(R.layout.fragment_music, container, false);
       btnInstrumental=view.findViewById(R.id.btnInstrumental);
       btnInstrumental.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent i=new Intent(getActivity(), instrumentalMusicActivity.class);
               startActivity(i);
           }
       });
        return view;
    }



}