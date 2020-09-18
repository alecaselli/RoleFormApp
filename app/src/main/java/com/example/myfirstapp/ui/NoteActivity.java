package com.example.myfirstapp.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.myfirstapp.R;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class NoteActivity extends AppCompatActivity {

    RelativeLayout expandableView;
    Button expandButton;
    CardView cardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        expandableView = findViewById(R.id.note_alignment_expandableView);
        expandButton = findViewById(R.id.note_alignment_expandButton);
        cardView = findViewById(R.id.note_alignment_cardView);

        expandButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (expandableView.getVisibility()==View.GONE){
                    TransitionManager.beginDelayedTransition((ViewGroup) cardView.getParent().getParent(), new AutoTransition());
                    expandableView.setVisibility(View.VISIBLE);
                    expandButton.setBackgroundResource(R.drawable.ic_arrow_up);
                } else {
                    TransitionManager.beginDelayedTransition((ViewGroup) cardView.getParent().getParent(), new AutoTransition());
                    expandableView.setVisibility(View.GONE);
                    expandButton.setBackgroundResource(R.drawable.ic_arrow_down);
                }
            }
        });

    }



}