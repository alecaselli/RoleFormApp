package com.example.myfirstapp.database;

import android.content.Context;

import androidx.annotation.Nullable;

import com.example.myfirstapp.domain.EquipaggiamentoOld;
import com.example.myfirstapp.interactorbosa.InterfaceBorsaGiocatoreDB;

import java.util.List;

public class BorsaGiocatoreDB implements InterfaceBorsaGiocatoreDB {
    private final BorseDB dbBorse;

    public BorsaGiocatoreDB(String nomecamp, String nomeg, Context ctx) {
        this.dbBorse = new BorseDB(nomecamp, nomeg, ctx);
    }

    @Override
    public boolean addOggetto(String nome) {
        if(dbBorse.isInEquipaggiato(nome))
            return dbBorse.updateBorseOggetto(nome, true);
        if(dbBorse.isInBorsa(nome))
            return true;
        return dbBorse.insertOggettoInBorse(nome, true);
    }

    @Override
    public boolean removeOggetto(String nome) {
        if(dbBorse.isInEquipaggiato(nome))
            return false;
        if(dbBorse.isInBorsa(nome))
            return dbBorse.deleteOggettoFromBorse(nome);
        return false;
    }

    @Nullable
    @Override
    public List<EquipaggiamentoOld> readBorsa() {
       return dbBorse.readEquipaggiamenti(true);
    }
}
