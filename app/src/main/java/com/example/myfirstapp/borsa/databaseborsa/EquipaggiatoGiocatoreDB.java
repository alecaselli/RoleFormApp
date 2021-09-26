package com.example.myfirstapp.borsa.databaseborsa;

import android.content.Context;

import androidx.annotation.Nullable;

import com.example.myfirstapp.borsa.databaseborsa.BorseDB;
import com.example.myfirstapp.domain.EquipaggiamentoOld;
import com.example.myfirstapp.borsa.interactorbosa.InterfaceContenitoriEquipaggiamentoDB;

import java.util.List;

public class EquipaggiatoGiocatoreDB implements InterfaceContenitoriEquipaggiamentoDB {
    private final BorseDB dbBorse;

    public EquipaggiatoGiocatoreDB(String nomecamp, String nomeg, Context ctx) {
        this.dbBorse = new BorseDB(nomecamp, nomeg, ctx);
    }

    @Override
    public boolean addOggetto(String nome) {
        if(dbBorse.isInBorsa(nome))
            return dbBorse.updateBorseOggetto(nome, false);
        return dbBorse.insertOggettoInBorse(nome, false);
    }

    @Override
    public boolean removeOggetto(String nome) {
        if(dbBorse.isInBorsa(nome)) return true;
        return dbBorse.deleteOggettoFromBorse(nome);
    }

    @Nullable
    @Override
    public List<EquipaggiamentoOld> readBorsa() {
        return dbBorse.readEquipaggiamenti(false);
    }

}
