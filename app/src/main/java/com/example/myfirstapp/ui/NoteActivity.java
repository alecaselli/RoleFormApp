package com.example.myfirstapp.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.myfirstapp.R;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class NoteActivity extends AppCompatActivity {

    private RelativeLayout expandableView;
    private Button expandButton;
    private CardView cardView;
    private EditText allineamentoeditText;
    private EditText backgroundeditText;
    private String filename;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        Intent intent = getIntent();
        filename = intent.getStringExtra("filename");

        allineamentoeditText = findViewById(R.id.note_alignment);
        backgroundeditText = findViewById(R.id.note_background);

        this.create();
        this.load();

/*
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
        }); */

    }


    public void expandAlignmentCard(View view) {
        expandableView = findViewById(R.id.note_alignment_expandableView);
        expandButton = findViewById(R.id.note_alignment_expandButton);
        cardView = findViewById(R.id.note_alignment_cardView);

        expandButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (expandableView.getVisibility() == View.GONE) {
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

    public void expandBackgroundCard(View view) {
        expandableView = findViewById(R.id.note_background_expandableView);
        expandButton = findViewById(R.id.note_background_expandButton);
        cardView = findViewById(R.id.note_background_cardView);

        expandButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (expandableView.getVisibility() == View.GONE) {
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

    public void create(){
        FileInputStream fis = null;
        try {
            fis = openFileInput(filename);
        } catch (FileNotFoundException e) {
            FileOutputStream fos = null;
            try {
                fos = openFileOutput(filename, MODE_PRIVATE);
            } catch (FileNotFoundException e2) {
                e2.printStackTrace();
            } finally {
                if (fos != null) {
                    try {
                        fos.close();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                }
            }
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void load() {
        FileInputStream fis = null;

        try {
            fis = openFileInput(filename);
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
            StringBuilder builder = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null && (line.compareTo("----") != 0)) {
                builder.append(line).append("\n");
            }
            allineamentoeditText.setText(builder.toString());

            builder = new StringBuilder();
            while ((line = reader.readLine()) != null && (line.compareTo("----") != 0)) {
                builder.append(line).append("\n");
            }
            backgroundeditText.setText(builder.toString());

        } catch (FileNotFoundException e) {
            FileOutputStream fos = null;
            try {
                fos = openFileOutput(filename, MODE_PRIVATE);
            } catch (FileNotFoundException e2) {
                e2.printStackTrace();
            } finally {
                if (fos != null) {
                    try {
                        fos.close();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                }
            }
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void save(View view) {
        String allineameto = allineamentoeditText.getText().toString();
        String background = backgroundeditText.getText().toString();
        FileOutputStream fos = null;
        try {
            fos = openFileOutput(filename, MODE_PRIVATE);
            fos.write(allineameto.getBytes());
            fos.write("\n".getBytes());
            fos.write("----".getBytes());
            fos.write("\n".getBytes());
            fos.write(background.getBytes());

            Toast.makeText(this, "Note salvate", Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}