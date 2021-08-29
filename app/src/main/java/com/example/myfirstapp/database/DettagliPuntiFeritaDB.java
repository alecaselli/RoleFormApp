package com.example.myfirstapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import com.example.myfirstapp.databasetabelle.TabellaGiocatore;
import com.example.myfirstapp.domain.Giocatore;
import com.example.myfirstapp.interactor.InterfaceDettagliPuntiFeritaDB;

public class DettagliPuntiFeritaDB implements InterfaceDettagliPuntiFeritaDB {
    String nomecamp;
    String nomeg;
    DBHelper dbHelper;
    DBManager dbManager;

    public DettagliPuntiFeritaDB(String nomecamp, String nomeg, Context ctx) {
        this.nomecamp = nomecamp;
        this.nomeg = nomeg;
        this.dbHelper = new DBHelper(ctx);
        this.dbManager = new DBManager(ctx);
    }

    @Override
    public int readPuntiFerita() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String whereClause = TabellaGiocatore.FIELD_NOMECAMPAGNA + "=?" + " AND " + TabellaGiocatore.FIELD_NOMEG + "=?";
        String[] whereArgs = new String[]{nomecamp, nomeg};

        try {
            Cursor resultSet = db.query(TabellaGiocatore.TBL_NOME, null, whereClause, whereArgs, null, null, null);
            if (resultSet == null || resultSet.getCount() == 0) {
                return 0;
            }
            resultSet.moveToFirst();

            int mana = resultSet.getInt(resultSet.getColumnIndex(TabellaGiocatore.FIELD_PUNTIFERITA));

            resultSet.close();
            return mana;
        } catch (SQLiteException sqle) {
            Log.e("LEGGI PUNTI FERITA", "leggi fallita", sqle);
            return 0;
        }
    }

    @Override
    public int readPuntiFeritaMax() {
        //TODO: riorganizza read una volta suddiviso giocatore in più classi
        Giocatore giocatore = dbManager.leggiGiocatore(nomecamp, nomeg);
        if(null != giocatore){
            return giocatore.getPuntiFeritaMax();
        }
        return 0;
    }

    @Override
    public boolean updatePuntiFerita(int puntiFerita) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        String whereClause = TabellaGiocatore.FIELD_NOMECAMPAGNA + " = ? " + " AND " + TabellaGiocatore.FIELD_NOMEG + " = ? ";
        String[] whereArgs = new String[]{nomecamp, nomeg};

        cv.put(TabellaGiocatore.FIELD_PUNTIFERITA, puntiFerita);

        try {
            return (db.update(TabellaGiocatore.TBL_NOME, cv, whereClause, whereArgs) > 0);
        } catch (SQLiteException sqle) {
            Log.e("AGGIORNA PUNTI FERITA", "aggiorna fallita", sqle);
            return false;
        }
    }

}
