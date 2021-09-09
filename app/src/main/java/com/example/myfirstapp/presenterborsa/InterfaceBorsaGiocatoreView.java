package com.example.myfirstapp.presenterborsa;

import com.example.myfirstapp.interactor.InterfaceErrorView;
import com.example.myfirstapp.interactorbosa.BorsaDataStruct;
import com.example.myfirstapp.utilities.CardEquip;

import java.util.Iterator;

public interface InterfaceBorsaGiocatoreView extends InterfaceErrorView {
    void setBorsa(Iterator<BorsaDataStruct> borsaDataIterator);
    void addOggetto(BorsaDataStruct borsaDataStruct);
    void removeOggetto(int position);
    void setSpinnerAddOggetto(Iterator<String> nomiIterator);
}
