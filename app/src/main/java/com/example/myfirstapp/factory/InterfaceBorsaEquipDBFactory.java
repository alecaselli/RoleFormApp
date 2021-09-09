package com.example.myfirstapp.factory;

import com.example.myfirstapp.interactorbosa.InterfaceBorsaGiocatoreDB;
import com.example.myfirstapp.interactorbosa.InterfaceEquipaggiatoGiocatoreDB;
import com.example.myfirstapp.interactorbosa.InterfaceOggettoDB;

public interface InterfaceBorsaEquipDBFactory {

    InterfaceBorsaGiocatoreDB createBorsaDB();
    InterfaceEquipaggiatoGiocatoreDB createEquipaggiatoDB();
    InterfaceOggettoDB createOggettoDB();
}
