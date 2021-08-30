package com.example.myfirstapp.interactor;

import com.example.myfirstapp.domain.ValutaOld;

import java.util.List;


public interface InterfacePortafoglioDB {
    void createPortafoglio(ValutaOld portafoglio);
    ValutaOld readPortafoglio();
    boolean updatePortafoglio(int valore);
    void deletePortafoglio();

}
