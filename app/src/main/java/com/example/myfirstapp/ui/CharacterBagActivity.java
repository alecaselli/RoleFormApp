package com.example.myfirstapp.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myfirstapp.R;
import com.example.myfirstapp.adapter.CardEquipAdapter;
import com.example.myfirstapp.database.DBManager;
import com.example.myfirstapp.databasetabelle.TabellaEquipaggiamento;
import com.example.myfirstapp.domain.Armatura;
import com.example.myfirstapp.domain.Equipaggiamento;
import com.example.myfirstapp.domain.Giocatore;
import com.example.myfirstapp.utilities.CardEquip;
import com.example.myfirstapp.utilities.MyDBException;

import java.util.ArrayList;
import java.util.List;

public class CharacterBagActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private TextView text;
    private RecyclerView mRecyclerView;
    private GridLayoutManager mGridLayoutManager;
    private CardEquipAdapter mAdapter;
    private Spinner itemSpinner;

    private ArrayList<CardEquip> cardEquipList;
    private String nomecamp;
    private String nomeg;
    private Giocatore giocatore;
    private DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_bag_temporaneo);

        this.estraiGiocatore();
        this.createCardBorsaList();
        this.setView();
        this.setRecyclerView();
        this.setSpinner();
        this.aggiornaBorsa();
    }

    public void estraiGiocatore() {
        Intent intent = getIntent();
        nomecamp = intent.getStringExtra("nomecamp");
        nomeg = intent.getStringExtra("nomeg");
        dbManager = new DBManager(this);
        giocatore = dbManager.leggiGiocatore(nomecamp, nomeg);
    }

    public void createCardBorsaList() {
        cardEquipList = new ArrayList<>();
        if (giocatore.getBorsa() != null){
            this.aggiornaBorsa();
            for (Equipaggiamento equipaggiamento : giocatore.getBorsa())
                if (equipaggiamento != null)
                    cardEquipList.add(new CardEquip(equipaggiamento.getNome(),equipaggiamento.getTipo()));
            if (giocatore.getBorsa().size() != 0) {
                this.aggiornaBorsa();
            }
        }
    }

    public void aggiornaBorsa() {
        if (giocatore.getBorsa().size() != 0) {
            (findViewById(R.id.bag_empty)).setVisibility(View.GONE);
        } else {
            (findViewById(R.id.bag_empty)).setVisibility(View.VISIBLE);
        }
    }

    public void setView() {
        Equipaggiamento equipaggiamento = giocatore.getEquipaggiato("armatura");
        String nome;
        if (equipaggiamento != null) nome = equipaggiamento.getNome();
        else nome = getString(R.string.non_equip);
        text = findViewById(R.id.bag_armor);
        text.setText(nome);

        equipaggiamento = giocatore.getEquipaggiato("scudo");
        if (equipaggiamento != null) nome = equipaggiamento.getNome();
        else nome = getString(R.string.non_equip);
        text = findViewById(R.id.bag_shield);
        text.setText(nome);

        equipaggiamento = giocatore.getEquipaggiato("arma");
        if (equipaggiamento != null) nome = equipaggiamento.getNome();
        else nome = getString(R.string.non_equip);
        text = findViewById(R.id.bag_weapon);
        text.setText(nome);
    }

    public void setRecyclerView() {
        mRecyclerView = findViewById(R.id.bag_recyclerView);
        mRecyclerView.setHasFixedSize(true);

        mGridLayoutManager = new GridLayoutManager(this,3);
        mAdapter = new CardEquipAdapter(cardEquipList);

        mRecyclerView.setLayoutManager(mGridLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new CardEquipAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                openEquipment(position);
            }

            @Override
            public void onEquipClick(int position) {
                equipaggia(position);
            }

            @Override
            public void onRemoveClick(int position) {
                rimuovi(position);
            }
        });

    }

    public void setSpinner() {
        itemSpinner = findViewById(R.id.bag_add_item_spinner);
        itemSpinner.setPrompt("Selezione l'oggetto da aggiungere");

        List<String> equipaggiamentoList = dbManager.leggiPK(TabellaEquipaggiamento.TBL_NOME, TabellaEquipaggiamento.FIELD_NOMEE);
        ArrayAdapter<String> ItemSpinnerAdapter = new ArrayAdapter<String>(this, R.layout.spinner_custom_item);
        ItemSpinnerAdapter.add(getString(R.string.aggiungi));
        ItemSpinnerAdapter.addAll(equipaggiamentoList);
        ItemSpinnerAdapter.setDropDownViewResource(R.layout.spinner_dropdown_custom_item);
        itemSpinner = findViewById(R.id.bag_add_item_spinner);
        itemSpinner.setAdapter(ItemSpinnerAdapter);
        itemSpinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        this.addItem();
        this.aggiornaBorsa();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void equipaggia(int position) {
        Equipaggiamento equipaggiare = giocatore.getBorsa(cardEquipList.get(position).getNome());
        if (!equipaggiare.getTipo().equals(Equipaggiamento.getTipobase().get(3))) { // tipo==oggetto
            if (dbManager.aggiornaHage(nomecamp, nomeg, equipaggiare.getNome(), false)) {

                try {
                    this.disequipaggia(equipaggiare.getTipo());
                    giocatore.eliminaBorsa(equipaggiare);
                    giocatore.aggiungiEquipaggiato(equipaggiare);
                    cardEquipList.remove(position);
                    mAdapter.notifyItemRemoved(position);
                    if(equipaggiare instanceof Armatura) {
                        Armatura equip = (Armatura) equipaggiare;
                        giocatore.aggiornaClasseArmatura(equip.getModificatoreCA());
                        dbManager.aggiornaDettagliGiocatore(giocatore);
                    }
                    this.setView();
                }catch (MyDBException e){
                    dbManager.aggiornaHage(nomecamp, nomeg, equipaggiare.getNome(), true);
                }
            } else Toast.makeText(this, "equipaggiamento fallito", Toast.LENGTH_LONG).show();
        } else Toast.makeText(this, "oggetto non equipaggiabile", Toast.LENGTH_LONG).show();
    }

    public void rimuovi(int position) {
        Equipaggiamento rimuovere = giocatore.getBorsa(cardEquipList.get(position).getNome());
        if (dbManager.eliminaHage(nomecamp, nomeg, rimuovere.getNome())) {
            cardEquipList.remove(position);
            mAdapter.notifyItemRemoved(position);
        } else Toast.makeText(this, "eliminazione fallito", Toast.LENGTH_LONG).show();
    }

    public void openEquipment(int position) {
        /*Intent intent = new Intent(this, EquipmentActivity.class);
        intent.putExtra("nomee", cardBoolList.get(position).getNome());
        startActivity(intent);
        finish();*/
    }

    public void addItem() {
        String nomee = itemSpinner.getSelectedItem().toString();
        if (nomee.equals(getString(R.string.aggiungi))) return;
        Equipaggiamento aggiunto = dbManager.leggiEquipaggiamento(nomee);
        switch (aggiunto.getTipo()) {
            case "arma":
                aggiunto = dbManager.leggiArma(nomee);
                break;
            case "armatura":
            case "scudo":
                aggiunto = dbManager.leggiArmatura(nomee);
                break;
            default:
                break;
        }

        if (dbManager.aggiungiHage(nomecamp, nomeg, nomee, true)) {
            giocatore.aggiungiBorsa(aggiunto);
            CardEquip nuovo = new CardEquip(aggiunto.getNome(), aggiunto.getTipo());
            cardEquipList.add(nuovo);
            mAdapter.notifyItemInserted(cardEquipList.size() - 1);
        } else Toast.makeText(this, "aggiunta fallita", Toast.LENGTH_LONG).show();
    }

    public void disequipaggiaArma(View view) {
        try {
            this.disequipaggia(Equipaggiamento.getTipobase().get(0));
            this.disequipaggia(R.id.bag_weapon);
        }catch (MyDBException ignored){}
    }

    public void disequipaggiaArmatura(View view) {
        try {
            this.disequipaggia(Equipaggiamento.getTipobase().get(1));
            this.disequipaggia(R.id.bag_armor);
        }catch (MyDBException ignored){}

    }

    public void disequipaggiaScudo(View view) {
        try {
            this.disequipaggia(Equipaggiamento.getTipobase().get(2));
            this.disequipaggia(R.id.bag_shield);
        }catch (MyDBException ignored){}
    }

    public void disequipaggia(int id){
        text = findViewById(id);
        text.setText(getString(R.string.non_equip));
    }
    public void disequipaggia(String tipo) throws MyDBException {
        Equipaggiamento disequipaggiare = giocatore.getEquipaggiato(tipo);
        if (disequipaggiare != null) {
            if (dbManager.aggiornaHage(nomecamp, nomeg, disequipaggiare.getNome(), true)) {
                giocatore.eliminaEquipaggiato(disequipaggiare);
                giocatore.aggiungiBorsa(disequipaggiare);
                cardEquipList.add(new CardEquip(disequipaggiare.getNome(), disequipaggiare.getTipo()));
                mAdapter.notifyItemInserted(cardEquipList.size() - 1);
                if(disequipaggiare instanceof Armatura){
                    Armatura dis = (Armatura) disequipaggiare;
                    giocatore.aggiornaClasseArmatura(-dis.getModificatoreCA());
                    dbManager.aggiornaDettagliGiocatore(giocatore);
                }
            } else{
                Toast.makeText(this, "disequipaggiamento fallito", Toast.LENGTH_LONG).show();
                throw (new MyDBException());
            }

        }
    }

    public void openCreateNewItem(View view) {
        Intent intent = new Intent(this, CreateNewItemActivity.class);
        intent.putExtra("nomecamp", nomecamp);
        intent.putExtra("nomeg", nomeg);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, CharacterActivity.class);
        intent.putExtra("nomecamp", nomecamp);
        intent.putExtra("nomeg", nomeg);
        startActivity(intent);
        finish();
    }
}