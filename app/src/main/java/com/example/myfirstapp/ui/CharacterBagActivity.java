package com.example.myfirstapp.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myfirstapp.R;
import com.example.myfirstapp.adapter.CardBoolAdapter;
import com.example.myfirstapp.database.DBManager;
import com.example.myfirstapp.domain.Equipaggiamento;
import com.example.myfirstapp.domain.Giocatore;
import com.example.myfirstapp.utilities.CardBool;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class CharacterBagActivity extends AppCompatActivity {

    private TextView text;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private CardBoolAdapter mAdapter;

    private ArrayList<CardBool> cardBoolList;
    private String nomecamp;
    private String nomeg;
    private Giocatore giocatore;
    private DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_bag);

        this.estraiGiocatore();
        this.createCardBorsaList();
        this.setView();
        this.setRecyclerView();
    }

    public void estraiGiocatore() {
        Intent intent = getIntent();
        nomecamp = intent.getStringExtra("nomecamp");
        nomeg = intent.getStringExtra("nomeg");
        dbManager = new DBManager(this);
        giocatore = dbManager.leggiGiocatore(nomecamp, nomeg);
    }

    public void createCardBorsaList() {
        cardBoolList = new ArrayList<>();
        if (giocatore.getBorsa() != null)
            for (Equipaggiamento equipaggiamento : giocatore.getBorsa())
                cardBoolList.add(new CardBool(equipaggiamento.getNome(), false));
        if (giocatore.getEquipaggiato() != null)
            for (Equipaggiamento equipaggiamento : giocatore.getEquipaggiato())
                cardBoolList.add(new CardBool(equipaggiamento.getNome(), true));
        if (giocatore.getEquipaggiato().size() != 0 || giocatore.getBorsa().size() != 0) {
            text = (TextView) findViewById(R.id.bag_empty);
            text.setText("");
        }
    }

    public void setView(){
        Equipaggiamento equipaggiamento = giocatore.getEquipaggiato("armatura");
        String nome;
        if (equipaggiamento != null) nome = equipaggiamento.getNome();
        else nome = "Non equipaggiato";
        text = (TextView) findViewById(R.id.bag_armor);
        text.setText(nome);

        equipaggiamento = giocatore.getEquipaggiato("scudo");
        if (equipaggiamento != null) nome = equipaggiamento.getNome();
        else nome = "Non equipaggiato";
        text = (TextView) findViewById(R.id.bag_shield);
        text.setText(nome);

        equipaggiamento = giocatore.getEquipaggiato("arma");
        if (equipaggiamento != null) nome = equipaggiamento.getNome();
        else nome = "Non equipaggiato";
        text = (TextView) findViewById(R.id.bag_weapon);
        text.setText(nome);
    }

    public void setRecyclerView() {
        mRecyclerView = findViewById(R.id.bag_recyclerView);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new CardBoolAdapter(cardBoolList);
        mLayoutManager = new LinearLayoutManager((this));
        mAdapter = new CardBoolAdapter(cardBoolList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new CardBoolAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                openEquipmentActivity(position);
            }

            @Override
            public void onBoolClick(int position) {
                changeEquipaggiamento(position);
            }
        });

    }

    public void openEquipmentActivity(int position) {
        /*Intent intent = new Intent(this, EquipmentActivity.class);
        intent.putExtra("nomee", cardBoolList.get(position).getNome());
        startActivity(intent);*/
    }

    public void changeEquipaggiamento(int position) {

        Equipaggiamento selezionato = giocatore.getBorsa(cardBoolList.get(position).getNome());
        if (!selezionato.getTipo().equals("oggetto")) {
            cardBoolList.get(position).swapBool();

            if (dbManager.aggiornaHage(nomecamp, nomeg, cardBoolList.get(position).getNome(), cardBoolList.get(position).getaBoolean())) {

                Equipaggiamento rimosso = giocatore.getEquipaggiato(selezionato.getTipo());

                giocatore.eliminaEquipaggiato(rimosso);
                giocatore.aggiungiBorsa(rimosso);

                giocatore.eliminaBorsa(selezionato);
                giocatore.aggiungiEquipaggiato(selezionato);

                cardBoolList.remove(position);
                mAdapter.notifyItemRemoved(position);
                this.setView();
            }
            else{
                Toast.makeText(this, "equipaggiamento fallito", Toast.LENGTH_LONG).show();
                cardBoolList.get(position).swapBool();
            }

        }
        else Toast.makeText(this, "oggetto non equipaggiabile", Toast.LENGTH_LONG).show();
    }

    public void onBackPressed() {
        Intent intent = new Intent(this, CharacterActivity.class);
        intent.putExtra("nomecamp", nomecamp);
        intent.putExtra("nomeg", nomeg);
        startActivity(intent);
        finish();
    }
}



/*giocatore.getBorsa(cardBoolList.get(position).getNome())
        giocatore.eliminaEquipaggiato(new ArrayList<Equipaggiamento>(Arrays.asList()));


        cardBoolList.get(position).swapBool();
        mAdapter.notifyItemChanged(position);

        if (!dbManager.aggiornaHage(nomecamp, nomeg, cardBoolList.get(position).getNome(), cardBoolList.get(position).getaBoolean())) {
            Toast.makeText(this, "aggiornamento fallito", Toast.LENGTH_LONG).show();
            cardBoolList.get(position).swapBool();
            mAdapter.notifyItemChanged(position);
        }*/