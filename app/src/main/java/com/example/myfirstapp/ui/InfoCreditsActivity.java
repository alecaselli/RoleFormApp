package com.example.myfirstapp.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.myfirstapp.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class InfoCreditsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_credits);

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(getAssets().open("info_credits"), StandardCharsets.UTF_8))) {

            String line;
            StringBuilder info_credit = new StringBuilder();
            while (((line = reader.readLine()) != null) && (line.compareTo("----") != 0)) {
                info_credit.append(line);
                info_credit.append("\n");
            }
            info_credit.deleteCharAt(info_credit.lastIndexOf("\n"));

            TextView txt = (TextView) findViewById(R.id.info);
            txt.setText(info_credit);

            info_credit = new StringBuilder();
            while (((line = reader.readLine()) != null)) {
                info_credit.append(line);
                info_credit.append("\n");
            }
            info_credit.deleteCharAt(info_credit.lastIndexOf("\n"));

            txt = (TextView) findViewById(R.id.credits);
            txt.setText(info_credit);

        } catch (IOException e) {
            Log.e("CREDITS", "fsiled asset read", e);

        }
    }

}