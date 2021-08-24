package com.example.myfirstapp.controller;

import com.example.myfirstapp.domain.ValutaOld;


public interface InterfacePortafoglioDB {
    boolean updatePortafoglio(ValutaOld portafoglio, String nomecamp, String nomeg);
    ValutaOld getPortafoglio(String nomecamp, String nomeg);

    void deletePortafoglio( String nomecamp, String nomeg);
    void insertPortafoglio(ValutaOld portafoglio, String nomecamp, String nomeg);

}
