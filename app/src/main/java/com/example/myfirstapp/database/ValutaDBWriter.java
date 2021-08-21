package com.example.myfirstapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import com.example.myfirstapp.domain.ValutaOld;
import com.example.myfirstapp.domain.ValutaTemp;

public class ValutaDBWriter implements InterfaceValutaDB{
    private DBHelper dbhelper;

    public ValutaDBWriter(Context ctx) {
        dbhelper = new DBHelper(ctx);
    }

    @Override
    public boolean aggiornaPortafoglio(ValutaOld valuta, String nomecamp, String nomeg) {
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        String whereClause = TabellaGiocatore.FIELD_NOMECAMPAGNA + " = ? " + " AND " + TabellaGiocatore.FIELD_NOMEG + " = ? ";
        String[] whereArgs = new String[]{nomecamp,nomeg};
        cv.put(TabellaGiocatore.FIELD_VALOREVAL, valuta.getValore());
        try {
            return db.update(TabellaGiocatore.TBL_NOME, cv, whereClause, whereArgs) > 0;
        } catch (SQLiteException sqle) {
            Log.e("AGGIORNA PORTAFOGLIO", "aggiorna fallita", sqle);
            return false;
        }
    }

    @Override
    public ValutaOld leggiPortafoglio(String nomecamp, String nomeg) {
        return null;
    }
}
