package com.example.myfirstapp.database;

import com.example.myfirstapp.domain.ValutaOld;
import com.example.myfirstapp.domain.ValutaTemp;

public interface InterfaceValutaDB {
    boolean updatePortafoglio(ValutaOld portafoglio, String nomecamp, String nomeg);
    ValutaOld getPortafoglio(String nomecamp, String nomeg);

    void deletePortafoglio( String nomecamp, String nomeg);
    void insertPortafoglio(ValutaOld portafoglio, String nomecamp, String nomeg);

}
