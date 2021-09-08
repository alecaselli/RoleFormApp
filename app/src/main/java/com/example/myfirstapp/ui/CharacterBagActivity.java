package com.example.myfirstapp.ui;

import androidx.annotation.NonNull;
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
import com.example.myfirstapp.factory.BorsaEquipDBFactory;
import com.example.myfirstapp.factory.BorsaEquipFactory;
import com.example.myfirstapp.factory.InterfaceBorsaEquipDBFactory;
import com.example.myfirstapp.factory.InterfaceBorsaEquipFactory;
import com.example.myfirstapp.interactorbosa.BorsaDataStruct;
import com.example.myfirstapp.interactorbosa.EquipDataStruct;
import com.example.myfirstapp.interactorbosa.InterfaceBorsaGiocatoreInteractor;
import com.example.myfirstapp.interactorbosa.InterfaceEquipaggiatoGiocatoreInteractor;
import com.example.myfirstapp.interactorbosa.InterfaceOggettoDB;
import com.example.myfirstapp.presenter.BorsaGiocatorePresenter;
import com.example.myfirstapp.presenter.EquipViewStruct;
import com.example.myfirstapp.presenter.EquipaggiatoGiocatorePresenter;
import com.example.myfirstapp.presenter.InterfaceBorsaGiocatoreView;
import com.example.myfirstapp.presenter.InterfaceEquipaggiatoGiocatoreView;
import com.example.myfirstapp.utilities.CardEquip;
import com.example.myfirstapp.utilities.MyExceptionDB;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CharacterBagActivity extends AppCompatActivity implements InterfaceBorsaGiocatoreView, InterfaceEquipaggiatoGiocatoreView {

    private TextView text;
    private RecyclerView mRecyclerView;
    private GridLayoutManager mGridLayoutManager;
    private CardEquipAdapter mAdapter;
    private Spinner itemSpinner;

    private BorsaGiocatorePresenter borsaPresenter;
    private EquipaggiatoGiocatorePresenter equipaggiatoPresenter;
    private ArrayList<CardEquip> cardEquipList;
    private String nomecamp;
    private String nomeg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_bag_temporaneo);

        this.estraiPresenter();
        this.setView();
    }

    public void estraiPresenter() {
        Intent intent = getIntent();
        nomecamp = intent.getStringExtra("nomecamp");
        nomeg = intent.getStringExtra("nomeg");
        InterfaceBorsaEquipFactory factory = new BorsaEquipFactory();
        InterfaceBorsaEquipDBFactory dbFactory = new BorsaEquipDBFactory();
        try {
            InterfaceOggettoDB dbOggetto = dbFactory.createOggettoDB(this);
            InterfaceBorsaGiocatoreInteractor borsaInteractor = factory.createBorsaGiocatoreInteractor(dbOggetto, dbFactory.createBorsaDB(nomecamp, nomeg,this));
            InterfaceEquipaggiatoGiocatoreInteractor equipaggiatoInteractor = factory.createEquipaggiatoGiocatoreInteractor(borsaInteractor, dbOggetto, dbFactory.createEquipaggiatoDB(nomecamp, nomeg, this));
            borsaPresenter = factory.createBorsaGiocatorePresenter(borsaInteractor, this);
            equipaggiatoPresenter = factory.createEquipaggiatoGiocatorePresenter(equipaggiatoInteractor, this, this);
        } catch (MyExceptionDB exceptionDB) {
            Toast.makeText(this, this.getString(R.string.db_access_error), Toast.LENGTH_LONG).show();
            onBackPressed();
        }
    }

    public void setView() {
        borsaPresenter.setborsa();
        borsaPresenter.setOggettiNonInBorsa();
        equipaggiatoPresenter.setEquipaggiato();
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
                rimuoviDaBorsa(position);
            }
        });

    }

    public void equipaggia(int position) {
        equipaggiatoPresenter.equipaggia(cardEquipList.get(position).getNome(), position);
    }

    public void disequipaggiaArma(View view) {
        this.disequipaggia(R.id.bag_weapon);
    }

    public void disequipaggiaArmatura(View view) {
        this.disequipaggia(R.id.bag_armor);
    }

    public void disequipaggiaScudo(View view) {
        this.disequipaggia(R.id.bag_shield);
    }

    public void disequipaggia(int viewId){
        text = findViewById(viewId);
        equipaggiatoPresenter.disequipaggia(text.getText().toString(), viewId);
    }

    public void setBorsaEmptyView() {
        int visibility = cardEquipList.isEmpty() ? View.VISIBLE : View.GONE;
        (findViewById(R.id.bag_empty)).setVisibility(visibility);
    }

    public void aggiungiABorsa() {
        String nomee = itemSpinner.getSelectedItem().toString();
        if (nomee.equals(getString(R.string.aggiungi))) return;

        borsaPresenter.aggiungiOggetto(nomee);
    }

    public void rimuoviDaBorsa(int position) {
        borsaPresenter.rimuoviOggetto(cardEquipList.get(position).getNome(), position);
    }

    public void openEquipment(int position) {
        /*Intent intent = new Intent(this, EquipmentActivity.class);
        intent.putExtra("nomee", cardBoolList.get(position).getNome());
        startActivity(intent);
        finish();*/
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

    @Override
    public void displayError(int indexError) {
        Toast.makeText(this, this.getString(indexError), Toast.LENGTH_LONG).show();
    }

    @Override
    public void setBorsa(@NonNull Iterator<CardEquip> cardEquipIterator) {
        cardEquipList = new ArrayList<>();
        while(cardEquipIterator.hasNext()){
            cardEquipList.add(cardEquipIterator.next());
        }
        this.setBorsaEmptyView();
        this.setRecyclerView();
    }

    @Override
    public void addOggetto(@NonNull BorsaDataStruct borsaDataStruct) {
        cardEquipList.add(new CardEquip(borsaDataStruct.getNome(), borsaDataStruct.getTipo()));
        mAdapter.notifyItemInserted(cardEquipList.size() - 1);
    }

    @Override
    public void removeOggetto(int position) {
        cardEquipList.remove(position);
        mAdapter.notifyItemRemoved(position);
        this.setBorsaEmptyView();
    }

    @Override
    public void setSpinnerAddOggetto(@NonNull Iterator<String> nomiIterator) {
        itemSpinner = findViewById(R.id.bag_add_item_spinner);
        itemSpinner.setPrompt("Selezione l'oggetto da aggiungere");

        List<String> equipaggiamentoList = new ArrayList<>();
        while(nomiIterator.hasNext()){
            equipaggiamentoList.add(nomiIterator.next());
        }
        ArrayAdapter<String> ItemSpinnerAdapter = new ArrayAdapter<String>(this, R.layout.spinner_custom_item);
        ItemSpinnerAdapter.add(getString(R.string.aggiungi));
        ItemSpinnerAdapter.addAll(equipaggiamentoList);
        ItemSpinnerAdapter.setDropDownViewResource(R.layout.spinner_dropdown_custom_item);
        itemSpinner = findViewById(R.id.bag_add_item_spinner);
        itemSpinner.setAdapter(ItemSpinnerAdapter);
        itemSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                aggiungiABorsa();
                setBorsaEmptyView();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void equipaggia(@NonNull EquipDataStruct equip, int viewId) {
        text = findViewById(viewId);
        text.setText(equip.getNome());
    }

    @Override
    public void disequipaggia(EquipDataStruct equip, int viewId) {
        text = findViewById(viewId);
        text.setText(getString(R.string.non_equip));
    }

    @Override
    public void setEquipaggiato(@NonNull Iterator<EquipViewStruct> iterator) {
        while(iterator.hasNext()){
            EquipViewStruct equip = iterator.next();
            text = findViewById(equip.getViewId());
            text.setText(equip.getNome());
        }
    }
}