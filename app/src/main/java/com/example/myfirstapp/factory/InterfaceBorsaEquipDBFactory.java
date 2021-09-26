package com.example.myfirstapp.factory;

import com.example.myfirstapp.borsa.interactorbosa.InterfaceContenitoriEquipaggiamentoDB;
import com.example.myfirstapp.borsa.interactorbosa.InterfaceOggettoDB;

public interface InterfaceBorsaEquipDBFactory {

    InterfaceContenitoriEquipaggiamentoDB createBorsaDB();
    InterfaceContenitoriEquipaggiamentoDB createEquipaggiatoDB();
    InterfaceOggettoDB createOggettoDB();
}
