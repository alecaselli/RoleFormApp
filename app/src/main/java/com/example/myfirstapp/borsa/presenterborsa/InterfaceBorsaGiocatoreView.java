package com.example.myfirstapp.borsa.presenterborsa;

import com.example.myfirstapp.interactor.InterfaceErrorView;
import com.example.myfirstapp.borsa.interactorbosa.BorsaDataStruct;

import java.util.Iterator;

public interface InterfaceBorsaGiocatoreView extends InterfaceErrorView {
    void setBorsa(Iterator<BorsaDataStruct> borsaDataIterator);
    void addOggetto(BorsaDataStruct borsaDataStruct);
    void removeOggetto(int position);
    void setSpinnerAddOggetto(Iterator<String> nomiIterator);
}
