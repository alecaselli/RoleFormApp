package com.example.myfirstapp.ui;

import android.content.Context;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myfirstapp.R;
import com.example.myfirstapp.database.InterfaceValutaDB;
import com.example.myfirstapp.database.ValutaDBReader;
import com.example.myfirstapp.database.ValutaDBWriter;
import com.example.myfirstapp.domain.ValutaOld;
import com.example.myfirstapp.utilities.DBException;

import java.util.ArrayList;
import java.util.List;

public class ValutaController {

    private String nomecamp;
    private String nomeg;
    private ValutaOld portafoglio;
    private InterfaceValutaDB valutaDBWriter;
    private InterfaceValutaDB valutaDBReader;

    public ValutaController(String nomecamp, String nomeg, Context ctx) throws DBException{
        this.nomecamp = nomecamp;
        this.nomeg = nomeg;
        this.valutaDBWriter = new ValutaDBWriter(ctx);
        this.valutaDBReader = new ValutaDBReader(ctx);
        this.getPortafoglio();
    }

    private void getPortafoglio() throws DBException {
        portafoglio = valutaDBReader.leggiPortafoglio(nomecamp,nomeg);
        if(null==portafoglio)
            throw new DBException();
    }

    public void aggiornaValuta(List<Integer> valore) throws DBException{
        portafoglio.aggiornaValore(valore);
        if (!valutaDBWriter.aggiornaPortafoglio(portafoglio, nomecamp, nomeg))
            throw new DBException();
    }

    public List<Integer> getValoreInMonete(){
        return portafoglio.getValoreInMonete();
    }
}
