package com.example.myfirstapp.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myfirstapp.R;
import com.example.myfirstapp.adapter.CardBoolAdapter;
import com.example.myfirstapp.database.DBManager;
import com.example.myfirstapp.database.TabellaEquipaggiamento;
import com.example.myfirstapp.domain.Equipaggiamento;
import com.example.myfirstapp.domain.Giocatore;
import com.example.myfirstapp.utilities.CardBool;

import java.util.ArrayList;
import java.util.List;

public class CharacterBagActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private TextView text;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private CardBoolAdapter mAdapter;
    private Spinner itemSpinner;

    private ArrayList<CardBool> cardBoolList;
    private String nomecamp;
    private String nomeg;
    private Giocatore giocatore;
    private DBManager dbManager;
    private final String AGGIUNGI = "aggiungi";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_bag_temporaneo);

        this.estraiGiocatore();
        this.createCardBorsaList();
        this.setView();
        this.setRecyclerView();
        this.setSpinner();
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
        if (giocatore.getBorsa().size() != 0) {
            text = (TextView) findViewById(R.id.bag_empty);
            text.setText("");
        }
    }

    public void setView() {
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
                openEquipment(position);
            }

            @Override
            public void onBoolClick(int position) {
                changeEquipaggiamento(position);
            }
        });

    }

    public void setSpinner() {
        List<String> equipaggiamentoList = dbManager.leggiPK(TabellaEquipaggiamento.TBL_NOME, TabellaEquipaggiamento.FIELD_NOMEE);
        ArrayAdapter<String> ItemSpinnerAdapter = new ArrayAdapter<String>(this, R.layout.spinner_custom_item);
        ItemSpinnerAdapter.add(AGGIUNGI);
        ItemSpinnerAdapter.addAll(equipaggiamentoList);
        ItemSpinnerAdapter.setDropDownViewResource(R.layout.spinner_dropdown_custom_item);
        itemSpinner = findViewById(R.id.bag_add_item_spinner);
        itemSpinner.setAdapter(ItemSpinnerAdapter);
        itemSpinner.setOnItemSelectedListener(this);
    }

    public void openEquipment(int position) {
        /*Intent intent = new Intent(this, EquipmentActivity.class);
        intent.putExtra("nomee", cardBoolList.get(position).getNome());
        startActivity(intent);*/
    }

    public void changeEquipaggiamento(int position) {

        Equipaggiamento equipaggiare = giocatore.getBorsa(cardBoolList.get(position).getNome());
        if (!equipaggiare.getTipo().equals("oggetto")) {
            if (dbManager.aggiornaHage(nomecamp, nomeg, cardBoolList.get(position).getNome(), false)) {
                cardBoolList.get(position).swapBool();

                Equipaggiamento disequipaggiare = giocatore.getEquipaggiato(equipaggiare.getTipo());
                if (disequipaggiare != null) {
                    giocatore.eliminaEquipaggiato(disequipaggiare);
                    giocatore.aggiungiBorsa(disequipaggiare);
                    cardBoolList.add(new CardBool(disequipaggiare.getNome(), false));
                    mAdapter.notifyItemInserted(cardBoolList.size() - 1);
                }

                giocatore.eliminaBorsa(equipaggiare);
                giocatore.aggiungiEquipaggiato(equipaggiare);
                cardBoolList.remove(position);

                mAdapter.notifyItemRemoved(position);
                this.setView();
            } else {
                Toast.makeText(this, "equipaggiamento fallito", Toast.LENGTH_LONG).show();
            }

        } else Toast.makeText(this, "oggetto non equipaggiabile", Toast.LENGTH_LONG).show();
    }

    public void addItem() {
        String nomee = itemSpinner.getSelectedItem().toString();
        Equipaggiamento aggiunto = dbManager.leggiEquipaggiamento(nomee);
        if (nomee.equals(AGGIUNGI)) return;
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
            cardBoolList.add(new CardBool(aggiunto.getNome(), false));
            mAdapter.notifyItemInserted(cardBoolList.size() - 1);
        } else {
            Toast.makeText(this, "aggiunta fallita", Toast.LENGTH_LONG).show();
        }
    }

    public void openCreateNewItem(View view) {
        Intent intent = new Intent(this, CreateNewItemActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        this.addItem();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

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



/*giocatore.getBorsa(cardBoolList.get(position).getNome())
        giocatore.eliminaEquipaggiato(new ArrayList<Equipaggiamento>(Arrays.asList()));


        cardBoolList.get(position).swapBool();
        mAdapter.notifyItemChanged(position);

        if (!dbManager.aggiornaHage(nomecamp, nomeg, cardBoolList.get(position).getNome(), cardBoolList.get(position).getaBoolean())) {
            Toast.makeText(this, "aggiornamento fallito", Toast.LENGTH_LONG).show();
            cardBoolList.get(position).swapBool();
            mAdapter.notifyItemChanged(position);
        }*/