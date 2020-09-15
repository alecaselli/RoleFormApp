package com.example.myfirstapp.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.myfirstapp.R;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class CreditsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credits);

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(getAssets().open("credits"), StandardCharsets.UTF_8))) {


            String line;
            StringBuilder credits = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                credits.append(line);
                credits.append("\n");
            }
            credits.deleteCharAt(credits.lastIndexOf("\n"));

            TextView txt = (TextView) findViewById(R.id.credits);
            txt.setText(credits);

        } catch (IOException e) {
            Log.e("CREDITS", "fsiled asset read", e);
        }
    }

}