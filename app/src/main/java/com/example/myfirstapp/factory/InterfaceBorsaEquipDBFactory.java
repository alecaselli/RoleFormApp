package com.example.myfirstapp.factory;

import android.content.Context;

import com.example.myfirstapp.database.OggettoDB;
import com.example.myfirstapp.interactorbosa.InterfaceBorsaGiocatoreDB;
import com.example.myfirstapp.interactorbosa.InterfaceEquipaggiatoGiocatoreDB;
import com.example.myfirstapp.interactorbosa.InterfaceOggettoDB;

public interface InterfaceBorsaEquipDBFactory {

    InterfaceBorsaGiocatoreDB createBorsaDB();
    InterfaceEquipaggiatoGiocatoreDB createEquipaggiatoDB();
    InterfaceOggettoDB createOggettoDB();
}
