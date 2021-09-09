package com.example.myfirstapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import com.example.myfirstapp.databasetabelle.TabellaCampiComuni;
import com.example.myfirstapp.databasetabelle.TabellaGiocatore;
import com.example.myfirstapp.interactor.InterfaceDettagliLivelloDB;

public class DettagliLivelloDB implements InterfaceDettagliLivelloDB {
    String nomecamp;
    String nomeg;
    DBHelper dbHelper;

    public DettagliLivelloDB(String nomecamp, String nomeg, Context ctx) {
        this.nomecamp = nomecamp;
        this.nomeg = nomeg;
        this.dbHelper = new DBHelper(ctx);
    }

    @Override
    public int readLivello() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String whereClause = TabellaGiocatore.FIELD_NOMECAMPAGNA + "=?" + " AND " + TabellaGiocatore.FIELD_NOMEG + "=?";
        String[] whereArgs = new String[]{nomecamp, nomeg};

        try {
            Cursor resultSet = db.query(TabellaGiocatore.TBL_NOME, null, whereClause, whereArgs, null, null, null);
            if (resultSet == null || resultSet.getCount() == 0) {
                return 0;
            }
            resultSet.moveToFirst();

            int livello = resultSet.getInt(resultSet.getColumnIndex(TabellaCampiComuni.FIELD_LIVELLO));

            resultSet.close();
            return livello;
        } catch (SQLiteException sqle) {
            Log.e("LEGGI LIVELLO", "leggi fallita", sqle);
            return 0;
        }
    }

    @Override
    public boolean updateLivello(int livello) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        String whereClause = TabellaGiocatore.FIELD_NOMECAMPAGNA + " = ? " + " AND " + TabellaGiocatore.FIELD_NOMEG + " = ? ";
        String[] whereArgs = new String[]{nomecamp, nomeg};

        cv.put(TabellaCampiComuni.FIELD_LIVELLO, livello);

        try {
            return db.update(TabellaGiocatore.TBL_NOME, cv, whereClause, whereArgs) > 0;
        } catch (SQLiteException sqle) {
            Log.e("AGGIORNA LIVELLO", "aggiorna fallita", sqle);
            return false;
        }
    }
}
