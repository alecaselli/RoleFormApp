package com.example.myfirstapp.presenter;

import com.example.myfirstapp.interactor.InterfaceErrorView;
import com.example.myfirstapp.interactorbosa.BorsaDataStruct;
import com.example.myfirstapp.utilities.CardEquip;

import java.util.Iterator;
import java.util.List;

public interface InterfaceBorsaGiocatoreView extends InterfaceErrorView {
    void setBorsa(Iterator<CardEquip> cardEquipIterator);
    void addOggetto(BorsaDataStruct borsaDataStruct);
    void removeOggetto(int position);
    void setSpinnerAddOggetto(Iterator<String> nomiIterator);
}
