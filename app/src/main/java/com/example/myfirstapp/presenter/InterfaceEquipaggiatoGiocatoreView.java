package com.example.myfirstapp.presenter;

import com.example.myfirstapp.interactor.InterfaceErrorView;
import com.example.myfirstapp.interactorbosa.EquipDataStruct;

import java.util.Iterator;

public interface InterfaceEquipaggiatoGiocatoreView extends InterfaceErrorView {
    void equipaggia(EquipDataStruct equip, int viewId);
    void disequipaggia(EquipDataStruct equip, int viewId);
    void setEquipaggiato(Iterator<EquipViewStruct> iterator);
}

