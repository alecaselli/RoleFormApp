package com.example.myfirstapp.database;

import android.content.Context;

import com.example.myfirstapp.domain.Abilita;
import com.example.myfirstapp.interactor.InterfaceAbilitaDB;

public class AbilitaDB implements InterfaceAbilitaDB {
    DBHelper dbHelper;

    public AbilitaDB(Context ctx) {
        this.dbHelper = new DBHelper(ctx);
    }

    @Override
    public boolean createAbilita(String nomea, String desc) {
        return false;
    }

    @Override
    public Abilita readAbilita(String nomea) {
        return null;
    }

    @Override
    public boolean updateAbilita(String nomea, String desc) {
        return false;
    }

    @Override
    public boolean deleteAbilita(String nomea) {
        return false;
    }
}
