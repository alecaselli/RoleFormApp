package com.example.myfirstapp.ui;

import android.content.Context;
import android.widget.Toast;

import com.example.myfirstapp.database.InterfaceValutaDB;
import com.example.myfirstapp.database.ValutaDBReader;
import com.example.myfirstapp.database.ValutaDBWriter;
import com.example.myfirstapp.domain.ValutaOld;

import java.util.ArrayList;
import java.util.List;

public class ValutaController {

    private String nomecamp;
    private String nomeg;
    private ValutaOld portafoglio;
    private InterfaceValutaDB valutaDBWriter;
    private InterfaceValutaDB valutaDBReader;
    private Context ctx;

    public ValutaController(ValutaOld portafoglio, String nomecamp, String nomeg, Context ctx) {
        this.portafoglio = portafoglio;
        this.nomecamp = nomecamp;
        this.nomeg = nomeg;
        this.ctx = ctx;
        this.valutaDBWriter = new ValutaDBWriter(ctx);
        this.valutaDBReader = new ValutaDBReader(ctx);
    }

    private void aggiornaValuta(List<Integer> valore){
        // if(null==(this.portafoglio = valutaDBReader.leggiPortafoglio(nomecamp,nomeg)))
        //    Toast.makeText(ctx, "lettura portafoglio fallita", Toast.LENGTH_LONG).show();
        //else {
            portafoglio.aggiornaValore(valore);
            if (!valutaDBWriter.aggiornaPortafoglio(portafoglio, nomecamp, nomeg)) {
                Toast.makeText(ctx, "aggiornamento portafoglio fallito", Toast.LENGTH_LONG).show();
        //    }
        }
    }
    public void aggiornaValuta(int val0, int val1, int val2){
        List<Integer> valore = new ArrayList<>();
        valore.add(val0);
        valore.add(val1);
        valore.add(val2);
        this.aggiornaValuta(valore);
    }



}
