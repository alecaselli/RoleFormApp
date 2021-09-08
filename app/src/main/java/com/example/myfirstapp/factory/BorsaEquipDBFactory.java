package com.example.myfirstapp.factory;

import android.content.Context;

import com.example.myfirstapp.database.BorsaGiocatoreDB;
import com.example.myfirstapp.database.EquipaggiatoGiocatoreDB;
import com.example.myfirstapp.database.OggettoDB;
import com.example.myfirstapp.interactorbosa.InterfaceBorsaGiocatoreDB;
import com.example.myfirstapp.interactorbosa.InterfaceEquipaggiatoGiocatoreDB;
import com.example.myfirstapp.interactorbosa.InterfaceOggettoDB;

public class BorsaEquipDBFactory implements InterfaceBorsaEquipDBFactory {

    @Override
    public InterfaceBorsaGiocatoreDB createBorsaDB(String nomecamp, String nomeg, Context ctx) {
        return new BorsaGiocatoreDB(nomecamp, nomeg, ctx);
    }

    @Override
    public InterfaceEquipaggiatoGiocatoreDB createEquipaggiatoDB(String nomecamp, String nomeg, Context ctx) {
        return new EquipaggiatoGiocatoreDB(nomecamp, nomeg, ctx);
    }

    @Override
    public InterfaceOggettoDB createOggettoDB(Context ctx) {
        return new OggettoDB(ctx);
    }
}
