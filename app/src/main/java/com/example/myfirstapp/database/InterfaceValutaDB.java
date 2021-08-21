package com.example.myfirstapp.database;

import com.example.myfirstapp.domain.ValutaOld;
import com.example.myfirstapp.domain.ValutaTemp;

public interface InterfaceValutaDB {
    public abstract boolean aggiornaPortafoglio(ValutaOld valuta, String nomecamp, String nomeg);
    public abstract ValutaOld leggiPortafoglio(String nomecamp, String nomeg);

}
