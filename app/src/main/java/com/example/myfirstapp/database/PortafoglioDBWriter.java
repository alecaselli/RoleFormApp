package com.example.myfirstapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import com.example.myfirstapp.databaseTabelle.TabellaGiocatore;
import com.example.myfirstapp.interactor.InterfacePortafoglioDB;
import com.example.myfirstapp.domain.ValutaOld;

public class PortafoglioDBWriter implements InterfacePortafoglioDB {
    private final String nomecamp;
    private final String nomeg;
    private final DBHelper dbhelper;

    public PortafoglioDBWriter(String nomecamp, String nomeg, Context ctx) {
        this.nomecamp = nomecamp;
        this.nomeg = nomeg;
        dbhelper = new DBHelper(ctx);
    }

    @Override
    public boolean updatePortafoglio(int valore) {
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        String whereClause = TabellaGiocatore.FIELD_NOMECAMPAGNA + " = ? " + " AND " + TabellaGiocatore.FIELD_NOMEG + " = ? ";
        String[] whereArgs = new String[]{nomecamp,nomeg};

        cv.put(TabellaGiocatore.FIELD_VALOREVAL, valore);

        try {
            return db.update(TabellaGiocatore.TBL_NOME, cv, whereClause, whereArgs) > 0;
        } catch (SQLiteException sqle) {
            Log.e("AGGIORNA PORTAFOGLIO", "aggiorna fallita", sqle);
            return false;
        }
    }

    @Override
    public ValutaOld readPortafoglio() {
        return null;
    }

    @Override
    public void deletePortafoglio() {

    }

    @Override
    public void createPortafoglio(ValutaOld portafoglio) {

    }
}
