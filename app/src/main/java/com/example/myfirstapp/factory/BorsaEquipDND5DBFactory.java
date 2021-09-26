package com.example.myfirstapp.factory;

import android.content.Context;

import com.example.myfirstapp.borsa.databaseborsa.BorsaGiocatoreDB;
import com.example.myfirstapp.borsa.databaseborsa.EquipaggiatoGiocatoreDB;
import com.example.myfirstapp.borsa.databaseborsa.OggettoDB;
import com.example.myfirstapp.borsa.interactorbosa.InterfaceContenitoriEquipaggiamentoDB;
import com.example.myfirstapp.borsa.interactorbosa.InterfaceOggettoDB;

public class BorsaEquipDND5DBFactory implements InterfaceBorsaEquipDBFactory {
    private final String nomecamp;
    private final String nomeg;
    private final Context ctx;

    public BorsaEquipDND5DBFactory(String nomecamp, String nomeg, Context ctx) {
        this.nomecamp = nomecamp;
        this.nomeg = nomeg;
        this.ctx = ctx;
    }

    @Override
    public InterfaceContenitoriEquipaggiamentoDB createBorsaDB() {
        return new BorsaGiocatoreDB(nomecamp, nomeg, ctx);
    }

    @Override
    public InterfaceContenitoriEquipaggiamentoDB createEquipaggiatoDB() {
        return new EquipaggiatoGiocatoreDB(nomecamp, nomeg, ctx);
    }

    @Override
    public InterfaceOggettoDB createOggettoDB() {
        return new OggettoDB(ctx);
    }
}
