package com.example.myfirstapp.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.myfirstapp.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class InfoCreditsActivity extends AppCompatActivity {

    private TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_credits);

        this.leggiInfoCredit();
    }

    public void leggiInfoCredit(){
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(getAssets().open("info_credits"), StandardCharsets.UTF_8))) {

            String line;
            StringBuilder info_credit = new StringBuilder();
            while (((line = reader.readLine()) != null) && (line.compareTo("----") != 0)) {
                info_credit.append(line).append("\n");
            }
            info_credit.deleteCharAt(info_credit.lastIndexOf("\n"));

            txt = (TextView) findViewById(R.id.info);
            txt.setText(info_credit);

            info_credit = new StringBuilder();
            while (((line = reader.readLine()) != null)) {
                info_credit.append(line).append("\n");
            }
            info_credit.deleteCharAt(info_credit.lastIndexOf("\n"));

            txt = (TextView) findViewById(R.id.credits);
            txt.setText(info_credit);

        } catch (IOException e) {
            Log.e("CREDITS", "fsiled asset read", e);

        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}