package com.example.myfirstapp.database;

import com.example.myfirstapp.domain.ValutaOld;
import com.example.myfirstapp.domain.ValutaTemp;

public interface InterfaceValutaDB {
    boolean aggiornaPortafoglio(ValutaOld valuta, String nomecamp, String nomeg);
    ValutaOld leggiPortafoglio(String nomecamp, String nomeg);

}
