package com.example.myfirstapp.borsa.interactorbosa;

import com.example.myfirstapp.utilities.MyExceptionDB;
import com.example.myfirstapp.utilities.MyExeptionOggettoNonTrovato;

import java.util.Iterator;

public interface InterfaceBorsaGiocatoreInteractor {
    BorsaDataStruct aggiungiOggetto(String nomeOggetto) throws MyExceptionDB;
    void rimuoviOggetto(String nomeOggetto) throws MyExceptionDB;
    Iterator<BorsaDataStruct> getborsaIterator();
    BorsaDataStruct getOggetto(String nomeOggetto) throws MyExeptionOggettoNonTrovato;
    Iterator<String> getOggettiNonInBorsaIterator();
}
