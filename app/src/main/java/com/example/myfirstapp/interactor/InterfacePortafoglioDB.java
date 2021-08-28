package com.example.myfirstapp.interactor;

import com.example.myfirstapp.domain.ValutaOld;


public interface InterfacePortafoglioDB {
    boolean updatePortafoglio(ValutaOld portafoglio, String nomecamp, String nomeg);
    ValutaOld readPortafoglio(String nomecamp, String nomeg);

    void deletePortafoglio( String nomecamp, String nomeg);
    void createPortafoglio(ValutaOld portafoglio, String nomecamp, String nomeg);

}
