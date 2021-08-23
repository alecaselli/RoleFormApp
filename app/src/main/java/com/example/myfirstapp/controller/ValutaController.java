package com.example.myfirstapp.controller;

import android.content.Context;

import com.example.myfirstapp.database.InterfaceValutaDB;
import com.example.myfirstapp.database.ValutaDBReader;
import com.example.myfirstapp.database.ValutaDBWriter;
import com.example.myfirstapp.domain.ValutaOld;
import com.example.myfirstapp.utilities.MyDBException;

import java.util.List;

public class ValutaController {

    private String nomecamp;
    private String nomeg;
    private ValutaOld portafoglio;
    private InterfaceValutaDB valutaDBWriter;
    private InterfaceValutaDB valutaDBReader;

    public ValutaController(String nomecamp, String nomeg, Context ctx) throws MyDBException {
        this.nomecamp = nomecamp;
        this.nomeg = nomeg;
        this.valutaDBWriter = new ValutaDBWriter(ctx);
        this.valutaDBReader = new ValutaDBReader(ctx);
        this.getPortafoglio();
    }

    private void getPortafoglio() throws MyDBException {
        portafoglio = valutaDBReader.getPortafoglio(nomecamp,nomeg);
        if(null==portafoglio)
            throw new MyDBException();
    }

    public void aggiornaValuta(List<Integer> valore) throws MyDBException {
        portafoglio.aggiornaValore(valore);
        if (!valutaDBWriter.updatePortafoglio(portafoglio, nomecamp, nomeg))
            throw new MyDBException();
    }

    public List<Integer> getValoreInMonete(){
        return portafoglio.getValoreInMonete();
    }
}
