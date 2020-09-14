package com.example.myfirstapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfirstapp.ExampleItem;
import com.example.myfirstapp.R;
import com.example.myfirstapp.adapter.ExampleAdapter;
import com.example.myfirstapp.database.CampiComuni;
import com.example.myfirstapp.database.DBManager;
import com.example.myfirstapp.database.TabellaGiocatore;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private ExampleAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private ImageButton createNewCharacter;
    private ImageButton editItems;
    private ImageButton info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DBManager dbManager = new DBManager(this);
        dbManager.dropDB(this);
        List<List<String>> datilist = dbManager.leggiDatiMenu(TabellaGiocatore.TBL_NOME, CampiComuni.FIELD_LIVELLO, TabellaGiocatore.FIELD_NOMECAMPAGNA,TabellaGiocatore.FIELD_NOMEG);
        ArrayList<ExampleItem> exampleList = new ArrayList<>();

        if(datilist != null)
            for(List<String> dati : datilist){
                exampleList.add(new ExampleItem(R.drawable.ic_baseline_image, dati.get(0),dati.get(1),"level " + dati.get(2)));
            }

        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager=new LinearLayoutManager((this));
        mAdapter=new ExampleAdapter(exampleList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener (new ExampleAdapter.OnItemClickListener() {
                                                        @Override
                                                        public void onItemClick(View view) {
                                                        TextView nomecampagna = findViewById(R.id.campaign_name);
                                                        TextView nomegiocatore = findViewById(R.id.character_name);
                                                        String nomecamp = nomecampagna.getText().toString();
                                                        String nomeg = nomegiocatore.getText().toString();
                                                        openCharacterActivity(nomecamp, nomeg);
                                                        }});
    }

    public void openCharacterActivity(String nomecamp, String nomeg){

        Intent intent = new Intent(this, CharacterActivity.class);
        intent.putExtra("nomecamp", nomecamp);
        intent.putExtra("nomeg", nomeg);
        startActivity(intent);
    }
    public void openCreateNewCharacterActivity(View view){
        Intent intent =new Intent (this, CreateNewCharacterActivity.class);
        startActivity(intent);
    }
    public void openInfo(View view){

    }
    public void openEdit(View view){

    }

}