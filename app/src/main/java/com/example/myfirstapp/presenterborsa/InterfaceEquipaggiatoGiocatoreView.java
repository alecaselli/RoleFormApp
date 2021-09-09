package com.example.myfirstapp.presenterborsa;

import com.example.myfirstapp.interactor.InterfaceErrorView;
import com.example.myfirstapp.interactorbosa.EquipDataStruct;

import java.util.Iterator;

public interface InterfaceEquipaggiatoGiocatoreView extends InterfaceErrorView {
    void equipaggia(EquipDataStruct equipData);
    void disequipaggia(EquipDataStruct equipData);
    void setEquipaggiato(Iterator<EquipDataStruct> iterator);
}

