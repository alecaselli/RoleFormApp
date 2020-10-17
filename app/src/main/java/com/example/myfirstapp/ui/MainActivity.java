package com.example.myfirstapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfirstapp.utilities.CardGiocatore;
import com.example.myfirstapp.R;
import com.example.myfirstapp.adapter.CardGiocatoreAdapter;
import com.example.myfirstapp.database.CampiComuni;
import com.example.myfirstapp.database.DBManager;
import com.example.myfirstapp.database.TabellaGiocatore;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private ArrayList<CardGiocatore> mCardGiocatoreList;

    private DBManager dbManager;
    private String nomecamp;
    private String nomeg;

    private RecyclerView mRecyclerView;
    private CardGiocatoreAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.createListCardGiocatore();
        this.setRecyclerView();

    }

    @Override
    protected void onResume() {
        super.onResume();
        mAdapter.notifyDataSetChanged();
    }

    public void createListCardGiocatore() {

        dbManager = new DBManager(this);
        /*dbManager.dropDB(this);*/

        List<List<String>> datilist = dbManager.leggiDatiMenu(TabellaGiocatore.TBL_NOME, CampiComuni.FIELD_LIVELLO, TabellaGiocatore.FIELD_NOMECAMPAGNA, TabellaGiocatore.FIELD_NOMEG);

        mCardGiocatoreList = new ArrayList<>();
        if (datilist != null)
            for (List<String> dati : datilist) {
                mCardGiocatoreList.add(new CardGiocatore(R.drawable.ic_baseline_image, dati.get(0), dati.get(1), "livello" + " " + dati.get(2)));
            }

    }

    public void setRecyclerView() {
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager((this));
        mAdapter = new CardGiocatoreAdapter(mCardGiocatoreList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new CardGiocatoreAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                openCharacterActivity(position);
            }

            @Override
            public void onDeleteClick(int position) {
                eliminaGiocatore(position);
            }
        });
    }

    public void openCharacterActivity(int position) {
        nomecamp = mCardGiocatoreList.get(position).getNomecampagna();
        nomeg = mCardGiocatoreList.get(position).getNomegiocatore();
        Intent intent = new Intent(this, CharacterActivity.class);
        intent.putExtra("nomecamp", nomecamp);
        intent.putExtra("nomeg", nomeg);
        startActivity(intent);

    }

    public void eliminaGiocatore(int position) {
        nomecamp = mCardGiocatoreList.get(position).getNomecampagna();
        nomeg = mCardGiocatoreList.get(position).getNomegiocatore();
        if (dbManager.eliminaGiocatore(nomecamp, nomeg))
            Toast.makeText(this, "giocatore eliminato", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(this, "eliminazione fallita", Toast.LENGTH_LONG).show();

        mCardGiocatoreList.remove(position);
        mAdapter.notifyItemRemoved(position);
    }

    public void openCreateNewCharacter(View view) {
        Intent intent = new Intent(this, CreateNewCharacterActivity.class);
        startActivity(intent);
        finish();
    }

    public void openInfoCredits(View view) {
        Intent intent = new Intent(this, InfoCreditsActivity.class);
        startActivity(intent);
    }

    public void openEdit(View view) {
        Intent intent = new Intent(this, EditActivity.class);
        startActivity(intent);
    }

}