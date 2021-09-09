package com.example.myfirstapp.factory;

import android.content.Context;

import com.example.myfirstapp.database.BorsaGiocatoreDB;
import com.example.myfirstapp.database.EquipaggiatoGiocatoreDB;
import com.example.myfirstapp.database.OggettoDB;
import com.example.myfirstapp.interactorbosa.InterfaceBorsaGiocatoreDB;
import com.example.myfirstapp.interactorbosa.InterfaceEquipaggiatoGiocatoreDB;
import com.example.myfirstapp.interactorbosa.InterfaceOggettoDB;

public class BorsaEquipDBFactory implements InterfaceBorsaEquipDBFactory {
    private final String nomecamp;
    private final String nomeg;
    private final Context ctx;

    public BorsaEquipDBFactory(String nomecamp, String nomeg, Context ctx) {
        this.nomecamp = nomecamp;
        this.nomeg = nomeg;
        this.ctx = ctx;
    }

    @Override
    public InterfaceBorsaGiocatoreDB createBorsaDB() {
        return new BorsaGiocatoreDB(nomecamp, nomeg, ctx);
    }

    @Override
    public InterfaceEquipaggiatoGiocatoreDB createEquipaggiatoDB() {
        return new EquipaggiatoGiocatoreDB(nomecamp, nomeg, ctx);
    }

    @Override
    public InterfaceOggettoDB createOggettoDB() {
        return new OggettoDB(ctx);
    }
}
