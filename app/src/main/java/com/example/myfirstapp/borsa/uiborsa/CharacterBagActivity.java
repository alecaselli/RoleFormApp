package com.example.myfirstapp.borsa.uiborsa;

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
import com.example.myfirstapp.factory.BorsaEquipDND5DBFactory;
import com.example.myfirstapp.factory.BorsaEquipDND5InteractorFactory;
import com.example.myfirstapp.factory.BorsaEquipDND5PresenterFactory;
import com.example.myfirstapp.factory.InterfaceBorsaEquipDBFactory;
import com.example.myfirstapp.factory.InterfaceBorsaEquipInteractorFactory;
import com.example.myfirstapp.factory.InterfaceBorsaEquipPresenterFactory;
import com.example.myfirstapp.borsa.interactorbosa.BorsaDataStruct;
import com.example.myfirstapp.borsa.interactorbosa.EquipDataStruct;
import com.example.myfirstapp.borsa.presenterborsa.BorsaGiocatorePresenter;
import com.example.myfirstapp.borsa.presenterborsa.EquipaggiatoGiocatorePresenter;
import com.example.myfirstapp.borsa.presenterborsa.InterfaceBorsaGiocatoreView;
import com.example.myfirstapp.borsa.presenterborsa.InterfaceEquipaggiatoGiocatoreView;
import com.example.myfirstapp.ui.CharacterActivity;
import com.example.myfirstapp.ui.IdEquipaggiato;
import com.example.myfirstapp.utilities.MyExceptionDB;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class CharacterBagActivity extends AppCompatActivity implements InterfaceBorsaGiocatoreView, InterfaceEquipaggiatoGiocatoreView {

    private TextView text;
    private RecyclerView mRecyclerView;
    private GridLayoutManager mGridLayoutManager;
    private ArrayList<CardEquip> cardEquipList;
    private CardEquipAdapter cardEquipAdapter;
    private Spinner itemSpinner;

    private BorsaGiocatorePresenter borsaPresenter;
    private EquipaggiatoGiocatorePresenter equipaggiatoPresenter;
    private final Map<String, Integer> tipiId = IdEquipaggiato.getTipiId();
    private String nomecamp;
    private String nomeg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_bag_temporaneo);

        this.estraiIntent();
        this.setPresenter();
        this.setView();
    }

    public void estraiIntent() {
        Intent intent = getIntent();
        nomecamp = intent.getStringExtra("nomecamp");
        nomeg = intent.getStringExtra("nomeg");
    }

    private void setPresenter(){
        InterfaceBorsaEquipDBFactory dbFactory = new BorsaEquipDND5DBFactory(nomecamp, nomeg, this);
        InterfaceBorsaEquipInteractorFactory interactorFactory = new BorsaEquipDND5InteractorFactory(dbFactory);
        InterfaceBorsaEquipPresenterFactory presenterFactory = new BorsaEquipDND5PresenterFactory(interactorFactory);
        try {
            borsaPresenter = presenterFactory.createBorsaGiocatorePresenter(this);
            equipaggiatoPresenter = presenterFactory.createEquipaggiatoGiocatorePresenter(this, this);
        } catch (MyExceptionDB exceptionDB) {
            Toast.makeText(this, this.getString(R.string.db_access_error), Toast.LENGTH_LONG).show();
            onBackPressed();
        }
    }

    private void setView() {
        borsaPresenter.setBorsa();
        borsaPresenter.setOggettiNonInBorsa();
        equipaggiatoPresenter.setEquipaggiato();
    }

    private void aggiungiABorsa(@NonNull String nome) {
        if (nome.equals(getString(R.string.aggiungi))) return;
        borsaPresenter.aggiungiOggetto(nome);
    }

    private void setBorsaEmptyView() {
        int visibility = cardEquipList.isEmpty() ? View.VISIBLE : View.GONE;
        (findViewById(R.id.bag_empty)).setVisibility(visibility);
    }

    private void setRecyclerView() {
        mRecyclerView = findViewById(R.id.bag_recyclerView);
        mRecyclerView.setHasFixedSize(true);

        mGridLayoutManager = new GridLayoutManager(this,3);
        cardEquipAdapter = new CardEquipAdapter(cardEquipList);

        mRecyclerView.setLayoutManager(mGridLayoutManager);
        mRecyclerView.setAdapter(cardEquipAdapter);

        cardEquipAdapter.setOnItemClickListener(new CardEquipAdapter.OnItemClickListener() {
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

    private void rimuoviDaBorsa(int position) {
        borsaPresenter.rimuoviOggetto(cardEquipList.get(position).getNome(), position);
    }

    private void equipaggia(int position) {
        equipaggiatoPresenter.equipaggia(cardEquipList.get(position).getNome(), position);
    }

    private void disequipaggia(int viewId){
        text = findViewById(viewId);
        equipaggiatoPresenter.disequipaggia(text.getText().toString());
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

    /* METODI BORSA VIEW */
    @Override
    public void displayError(int indexError) {
        Toast.makeText(this, this.getString(indexError), Toast.LENGTH_LONG).show();
    }

    @Override
    public void setBorsa(@NonNull Iterator<BorsaDataStruct> borsaDataIterator) {
        cardEquipList = new ArrayList<>();
        while(borsaDataIterator.hasNext()){
            BorsaDataStruct borsaData = borsaDataIterator.next();
            cardEquipList.add(new CardEquip(borsaData.getNome(), borsaData.getTipo()));
        }
        this.setBorsaEmptyView();
        this.setRecyclerView();
    }

    @Override
    public void addOggetto(@NonNull BorsaDataStruct borsaDataStruct) {
        cardEquipList.add(new CardEquip(borsaDataStruct.getNome(), borsaDataStruct.getTipo()));
        cardEquipAdapter.notifyItemInserted(cardEquipList.size() - 1);
    }

    @Override
    public void removeOggetto(int position) {
        cardEquipList.remove(position);
        //cardEquipList.indexOf();
        cardEquipAdapter.notifyItemRemoved(position);
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
        ArrayAdapter<String> ItemSpinnerAdapter = new ArrayAdapter<>(this, R.layout.spinner_custom_item);
        ItemSpinnerAdapter.add(getString(R.string.aggiungi));
        ItemSpinnerAdapter.addAll(equipaggiamentoList);
        ItemSpinnerAdapter.setDropDownViewResource(R.layout.spinner_dropdown_custom_item);
        itemSpinner = findViewById(R.id.bag_add_item_spinner);
        itemSpinner.setAdapter(ItemSpinnerAdapter);
        itemSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String nome = itemSpinner.getSelectedItem().toString();
                aggiungiABorsa(nome);
                setBorsaEmptyView();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    /* METODI EQUIPAGGIATO VIEW */
    @Override
    public void equipaggia(@NonNull EquipDataStruct equipData) {
        int viewId = tipiId.get(equipData.getTipo());
        text = findViewById(viewId);
        text.setText(equipData.getNome());
    }

    @Override
    public void disequipaggia(@NonNull EquipDataStruct equipData) {
        int viewId = tipiId.get(equipData.getTipo());
        text = findViewById(viewId);
        text.setText(getString(R.string.non_equip));
    }

    @Override
    public void setEquipaggiato(@NonNull Iterator<EquipDataStruct> iterator) {
        while(iterator.hasNext()){
            EquipDataStruct equipData = iterator.next();
            int viewId = tipiId.get(equipData.getTipo());
            text = findViewById(viewId);
            text.setText(equipData.getNome());
        }
    }

    /* METODI DI ATTIVAZIONE ACTIVITY */
    private void openEquipment(int position) {
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
}