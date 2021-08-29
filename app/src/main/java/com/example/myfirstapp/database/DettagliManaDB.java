package com.example.myfirstapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import com.example.myfirstapp.databasetabelle.TabellaGiocatore;
import com.example.myfirstapp.interactor.InterfaceDettagliManaDB;

public class DettagliManaDB implements InterfaceDettagliManaDB {
    String nomecamp;
    String nomeg;
    DBHelper dbHelper;

    public DettagliManaDB(String nomecamp, String nomeg, Context ctx) {
        this.nomecamp = nomecamp;
        this.nomeg = nomeg;
        this.dbHelper = new DBHelper(ctx);
    }

    @Override
    public int readMana() {
        return this.readMM(TabellaGiocatore.FIELD_MANA);
    }

    @Override
    public int readManaMax() {
        return this.readMM(TabellaGiocatore.FIELD_MANAMAX);
    }

    @Override
    public boolean updateMana(int mana) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        String whereClause = TabellaGiocatore.FIELD_NOMECAMPAGNA + " = ? " + " AND " + TabellaGiocatore.FIELD_NOMEG + " = ? ";
        String[] whereArgs = new String[]{nomecamp, nomeg};

        cv.put(TabellaGiocatore.FIELD_MANA, mana);

        try {
            return db.update(TabellaGiocatore.TBL_NOME, cv, whereClause, whereArgs) > 0;
        } catch (SQLiteException sqle) {
            Log.e("AGGIORNA MANA", "aggiorna fallita", sqle);
            return false;
        }
    }

    private int readMM(String field){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String whereClause = TabellaGiocatore.FIELD_NOMECAMPAGNA + "=?" + " AND " + TabellaGiocatore.FIELD_NOMEG + "=?";
        String[] whereArgs = new String[]{nomecamp, nomeg};

        try {
            Cursor resultSet = db.query(TabellaGiocatore.TBL_NOME, null, whereClause, whereArgs, null, null, null);
            if (resultSet == null || resultSet.getCount() == 0) {
                return 0;
            }
            resultSet.moveToFirst();

            int mana = resultSet.getInt(resultSet.getColumnIndex(field));

            resultSet.close();
            return mana;
        } catch (SQLiteException sqle) {
            Log.e("LEGGI MANA", "leggi fallita", sqle);
            return 0;
        }
    }
}
