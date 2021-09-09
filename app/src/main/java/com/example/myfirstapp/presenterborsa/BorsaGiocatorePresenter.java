package com.example.myfirstapp.presenterborsa;

import com.example.myfirstapp.R;
import com.example.myfirstapp.interactorbosa.BorsaDataStruct;
import com.example.myfirstapp.interactorbosa.InterfaceBorsaGiocatoreInteractor;
import com.example.myfirstapp.utilities.CardEquip;
import com.example.myfirstapp.utilities.MyExceptionDB;

import java.util.ArrayList;
import java.util.Iterator;

public class BorsaGiocatorePresenter {
    InterfaceBorsaGiocatoreInteractor borsa;
    InterfaceBorsaGiocatoreView viewBorsa;

    public BorsaGiocatorePresenter(InterfaceBorsaGiocatoreInteractor borsa, InterfaceBorsaGiocatoreView viewBorsa) {
        this.borsa = borsa;
        this.viewBorsa = viewBorsa;
    }

    public void aggiungiOggetto(String nomeOggetto){
        try {
            viewBorsa.addOggetto(borsa.aggiungiOggetto(nomeOggetto));
        }catch (MyExceptionDB exceptionDB){
            viewBorsa.displayError(R.string.db_insert_error);
        }
    }

    public void rimuoviOggetto(String nomeOggetto, int position){
        try {
            borsa.rimuoviOggetto(nomeOggetto);
            viewBorsa.removeOggetto(position);
        }catch (MyExceptionDB exceptionDB){
            viewBorsa.displayError(R.string.borsa_rimuovi_fallita);
        }
    }

    public void setborsa(){
        Iterator<BorsaDataStruct> borsaDataIterator = borsa.getborsaIterator();
        viewBorsa.setBorsa(borsaDataIterator);
    }

    public void setOggettiNonInBorsa(){
         viewBorsa.setSpinnerAddOggetto(borsa.getOggettiNonInBorsaIterator());
    }
}
