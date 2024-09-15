package com.example.headspace;



import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.BatchResultToken;
import com.google.android.material.search.SearchBar;

import java.net.CookieHandler;
import java.util.ArrayList;
import java.util.Locale;

import kotlin.Result;

public class WellnessFragment extends Fragment {

    private static final int REQUEST_CODE_SPEECH_INPUT = 1;
    //TextView tvText;
    ImageView ivMic;
    SearchView svWellness;
    TextToSpeech textToSpeech;
Toolbar tbWellness;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_wellness, container, false);
        // tvText = view.findViewById(R.id.tvText);
        ivMic = view.findViewById(R.id.ivWellnessMic);
        svWellness = view.findViewById(R.id.svWellness);
       // tbWellness=view.findViewById(R.id.tbWwllness);

        ivMic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                i.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                i.putExtra(RecognizerIntent.EXTRA_LANGUAGE,
                        Locale.getDefault());
                i.putExtra(RecognizerIntent.EXTRA_PROMPT, "Speak Now");
                try {
                    startActivityForResult(i, REQUEST_CODE_SPEECH_INPUT);
                } catch (Exception e) {
                    Toast.makeText(getActivity(), "" + e.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }
        });
        return view;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_SPEECH_INPUT) {
            if (resultCode == RESULT_OK && data != null) {
                ArrayList<String> Result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                svWellness.setQuery(Result.get(0), false);
                textToSpeech = new TextToSpeech(getActivity(), new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int status) {
                        textToSpeech.setLanguage(Locale.ENGLISH);
                        textToSpeech.speak(Result.get(0), TextToSpeech.QUEUE_FLUSH, null, null);
                    }
                });
            }
        }
    }

}