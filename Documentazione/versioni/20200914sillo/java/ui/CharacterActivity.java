package com.example.myfirstapp.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;

import com.example.myfirstapp.R;
import com.example.myfirstapp.adapter.GiocatoreAdapter;
import com.example.myfirstapp.database.DBManager;
import com.example.myfirstapp.domain.Giocatore;

import java.util.ArrayList;
import java.util.List;

public class CharacterActivity extends ListActivity {
    private RecyclerView mRecyclerView;
    private GiocatoreAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character);

        Intent intent = getIntent();
        String nomecamp = intent.getStringExtra("nomecamp");
        String nomeg = intent.getStringExtra("nomeg");
        assert nomeg != null;
        assert nomecamp != null;
        adapter = new GiocatoreAdapter(this, this.leggiGiocatore(nomecamp, nomeg));

        getListView().setPadding( 5, 5, 5,5);
        setListAdapter(adapter);

    }

    private List<Giocatore> leggiGiocatore(@NonNull String nomecamp,@NonNull String nomeg) {
        DBManager db = new DBManager(this);
        Giocatore giocatore = db.leggiGiocatore(nomecamp, nomeg);
        List<Giocatore> giocatoreList = new ArrayList<Giocatore>();
        giocatoreList.add(giocatore);
        return giocatoreList;
    }
}