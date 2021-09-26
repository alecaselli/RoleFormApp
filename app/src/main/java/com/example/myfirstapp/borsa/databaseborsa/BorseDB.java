package com.example.myfirstapp.borsa.databaseborsa;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.myfirstapp.database.DBHelper;
import com.example.myfirstapp.databasetabelle.TabellaEquipaggiamento;
import com.example.myfirstapp.databasetabelle.TabellaGiocatore;
import com.example.myfirstapp.databasetabelle.TabelleHA;
import com.example.myfirstapp.domain.EquipaggiamentoOld;
import com.example.myfirstapp.borsa.interactorbosa.InterfaceOggettoDB;

import java.util.ArrayList;
import java.util.List;

public class BorseDB {
    DBHelper dbHelper;
    InterfaceOggettoDB dbOggetto;
    String nomecamp;
    String nomeg;

    public BorseDB(String nomecamp, String nomeg, Context ctx) {
        this.dbHelper = new DBHelper(ctx);
        this.dbOggetto = new OggettoDB(ctx);
        this.nomecamp = nomecamp;
        this.nomeg = nomeg;
    }

    public boolean insertOggettoInBorse(String nome, boolean borsa) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(TabellaGiocatore.FIELD_NOMECAMPAGNA, nomecamp);
        cv.put(TabellaGiocatore.FIELD_NOMEG, nomeg);
        cv.put(TabellaEquipaggiamento.FIELD_NOMEE, nome);
        int flag = (borsa) ? 1 : 0;
        cv.put(TabelleHA.FIELD_BORSA, flag);

        try {
            return db.insert(TabelleHA.TBL_HAGE, null, cv) > 0;
        } catch (SQLiteException sqle) {
            Log.e("INSERT HAGE", "fallita", sqle);
            return false;
        }
    }

    public boolean updateBorseOggetto(String nome, boolean borsa) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        String whereClause = TabellaGiocatore.FIELD_NOMECAMPAGNA + " = ? " + " AND " + TabellaGiocatore.FIELD_NOMEG + " = ? " + " AND " + TabellaEquipaggiamento.FIELD_NOMEE + " = ? ";
        String[] whereArgs = new String[]{nomecamp, nomeg, nome};

        int flag = (borsa) ? 1 : 0;
        cv.put(TabelleHA.FIELD_BORSA, flag);

        try {
            return db.update(TabelleHA.TBL_HAGE, cv, whereClause, whereArgs) > 0;
        } catch (SQLiteException sqle) {
            Log.e("UPDATE HAGE", "fallita", sqle);
            return false;
        }
    }

    public boolean deleteOggettoFromBorse(String nome) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String whereClause = TabellaGiocatore.FIELD_NOMECAMPAGNA + "=?" + " AND " + TabellaGiocatore.FIELD_NOMEG + "=?" + " AND " + TabellaEquipaggiamento.FIELD_NOMEE + "=?";
        String[] whereArgs = new String[]{nomecamp, nomeg, nome};

        try {
            return db.delete(TabelleHA.TBL_HAGE, whereClause, whereArgs) > 0;
        } catch (SQLiteException sqle) {
            Log.e("DELETE HAGE", "fallita", sqle);
            return false;
        }
    }

    public boolean isInBorse(String nome){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String whereClause = TabellaEquipaggiamento.FIELD_NOMEE + " = ? ";
        String[] whereArgs = new String[]{nome};

        try {
            Cursor resultSet = db.query(TabelleHA.TBL_HAGE, null, whereClause, whereArgs, null, null, null);
            if (resultSet == null || resultSet.getCount() == 0) {
                return false;
            }

            resultSet.close();
            return true;
        } catch (SQLiteException sqle) {
            Log.e("IS IN BORSE", " fallita", sqle);
            return false;
        }
    }

    public boolean isInBorsa(String nome){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String whereClause = TabellaEquipaggiamento.FIELD_NOMEE + " = ? " + " AND " + TabelleHA.FIELD_BORSA + " = ? ";
        String[] whereArgs = new String[]{nome, Integer.toString(1)};

        try {
            Cursor resultSet = db.query(TabelleHA.TBL_HAGE, null, whereClause, whereArgs, null, null, null);
            if (resultSet == null || resultSet.getCount() == 0) {
                return false;
            }

            resultSet.close();
            return true;
        } catch (SQLiteException sqle) {
            Log.e("IS IN BORSA", " fallita", sqle);
            return false;
        }
    }

    public boolean isInEquipaggiato(String nome){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String whereClause = TabellaEquipaggiamento.FIELD_NOMEE + " = ? " + " AND " + TabelleHA.FIELD_BORSA + " = ? ";
        String[] whereArgs = new String[]{nome, Integer.toString(0)};

        try {
            Cursor resultSet = db.query(TabelleHA.TBL_HAGE, null, whereClause, whereArgs, null, null, null);
            if (resultSet == null || resultSet.getCount() == 0) {
                return false;
            }

            resultSet.close();
            return true;
        } catch (SQLiteException sqle) {
            Log.e("IS IN BORSA", " fallita", sqle);
            return false;
        }
    }

    @Nullable
    public List<EquipaggiamentoOld> readEquipaggiamenti(boolean borsa) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        int flag = (borsa) ? 1 : 0;
        String whereClause = TabellaGiocatore.FIELD_NOMECAMPAGNA + "=?" + " AND " + TabellaGiocatore.FIELD_NOMEG + "=?" + " AND " + TabelleHA.FIELD_BORSA + "=?";
        String[] whereArgs = new String[]{nomecamp, nomeg, Integer.toString(flag)};

        try {
            Cursor resultSet = db.query(TabelleHA.TBL_HAGE, null, whereClause, whereArgs, null, null, null);
            if (resultSet == null || resultSet.getCount() == 0) {
                return new ArrayList<>();
            }
            resultSet.moveToFirst();

            List<EquipaggiamentoOld> equipaggiamentoOldList = new ArrayList<EquipaggiamentoOld>();
            while (!resultSet.isAfterLast()) {
                String nome = resultSet.getString(resultSet.getColumnIndex(TabellaEquipaggiamento.FIELD_NOMEE));
                EquipaggiamentoOld equipaggiamento = dbOggetto.readOggetto(nome);
                if(equipaggiamento != null)
                    equipaggiamentoOldList.add(equipaggiamento);

                resultSet.moveToNext();
            }

            resultSet.close();
            return equipaggiamentoOldList;
        } catch (SQLiteException sqle) {
            Log.e("LEGGI EQUILIST", "fallita", sqle);
            return null;
        }

    }
}
