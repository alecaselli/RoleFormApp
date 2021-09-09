package com.example.myfirstapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import com.example.myfirstapp.databasetabelle.TabellaCampiComuni;
import com.example.myfirstapp.databasetabelle.TabellaClasse;
import com.example.myfirstapp.databasetabelle.TabellaGiocatore;
import com.example.myfirstapp.databasetabelle.TabellaRazza;
import com.example.myfirstapp.interactor.InterfaceDettagliGiocatoreDB;

import java.util.HashMap;

public class DettagliGiocatoreDB implements InterfaceDettagliGiocatoreDB {
    private final String NOME = "nome";
    private final String GENERE = "genere";
    private final String ALTEZZA = "altezza";
    private final String ETA = "eta";
    String nomecamp;
    String nomeg;
    DBHelper dbHelper;

    public DettagliGiocatoreDB(String nomecamp, String nomeg, Context ctx) {
        this.nomecamp = nomecamp;
        this.nomeg = nomeg;
        this.dbHelper = new DBHelper(ctx);
    }

    @Override
    public HashMap<String, String> readFisonomiaGiocatore() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String whereClause = TabellaGiocatore.FIELD_NOMECAMPAGNA + "=?" + " AND " + TabellaGiocatore.FIELD_NOMEG + "=?";
        String[] whereArgs = new String[]{nomecamp, nomeg};

        try {
            Cursor resultSet = db.query(TabellaGiocatore.TBL_NOME, null, whereClause, whereArgs, null, null, null);
            if (resultSet == null || resultSet.getCount() == 0) {
                return null;
            }
            resultSet.moveToFirst();

            HashMap<String, String> fisionomia = new HashMap<>();
            fisionomia.put(NOME, nomeg);
            String eta = resultSet.getString(resultSet.getColumnIndex(TabellaGiocatore.FIELD_ETA));
            fisionomia.put(ETA, eta);
            String altezza = resultSet.getString(resultSet.getColumnIndex(TabellaGiocatore.FIELD_ALTEZZA));
            fisionomia.put(ALTEZZA, altezza);
            String genere = resultSet.getString(resultSet.getColumnIndex(TabellaGiocatore.FIELD_GENERE));
            fisionomia.put(GENERE, genere);

            resultSet.close();
            return fisionomia;
        } catch (SQLiteException sqle) {
            Log.e("LEGGI GIOCATORE", "leggi fallita", sqle);
            return null;
        }
    }

    @Override
    public HashMap<String, String> readDettagliRazzaGiocatore() {
        return this.buildNomeDescrizione(TabellaRazza.TBL_NOME, TabellaRazza.FIELD_NOMER);
    }

    @Override
    public HashMap<String, String> readDettagliClasseGiocatore() {
        return this.buildNomeDescrizione(TabellaClasse.TBL_NOME, TabellaClasse.FIELD_NOMECLA);
    }

    @Override
    public int readClasseArmatura() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String whereClause = TabellaGiocatore.FIELD_NOMECAMPAGNA + "=?" + " AND " + TabellaGiocatore.FIELD_NOMEG + "=?";
        String[] whereArgs = new String[]{nomecamp, nomeg};

        try {
            Cursor resultSet = db.query(TabellaGiocatore.TBL_NOME, null, whereClause, whereArgs, null, null, null);
            if (resultSet == null || resultSet.getCount() == 0) {
                return 0;
            }
            resultSet.moveToFirst();

            int classeArmatura = resultSet.getInt(resultSet.getColumnIndex(TabellaGiocatore.FIELD_CLASSEARMATURA));

            resultSet.close();
            return classeArmatura;
        } catch (SQLiteException sqle) {
            Log.e("LEGGI CLASSEARMATURA", "leggi fallita", sqle);
            return 0;
        }
    }

    @Override
    public boolean updateClasseArmatura(int classeArmatura) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        String whereClause = TabellaGiocatore.FIELD_NOMECAMPAGNA + " = ? " + " AND " + TabellaGiocatore.FIELD_NOMEG + " = ? ";
        String[] whereArgs = new String[]{nomecamp, nomeg};

        cv.put(TabellaGiocatore.FIELD_CLASSEARMATURA, classeArmatura);

        try {
            return db.update(TabellaGiocatore.TBL_NOME, cv, whereClause, whereArgs) > 0;
        } catch (SQLiteException sqle) {
            Log.e("AGGIORNA CLASSEARMATURA", "aggiorna fallita", sqle);
            return false;
        }
    }

    private String getFieldStringGiocatore(String field){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String whereClause = TabellaGiocatore.FIELD_NOMECAMPAGNA + "=?" + " AND " + TabellaGiocatore.FIELD_NOMEG + "=?";
        String[] whereArgs = new String[]{nomecamp, nomeg};

        try {
            Cursor resultSet = db.query(TabellaGiocatore.TBL_NOME, null, whereClause, whereArgs, null, null, null);
            if (resultSet == null || resultSet.getCount() == 0) {
                return null;
            }
            resultSet.moveToFirst();

            String nome = resultSet.getString(resultSet.getColumnIndex(field));

            resultSet.close();
            return nome;
        } catch (SQLiteException sqle) {
            Log.e("LEGGI FIELD GIOCATORE", "leggi fallita", sqle);
            return null;
        }
    }

    private String getDescrizione(String tblNome, String fieldNome, String nome){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String whereClause = fieldNome + " = ? ";
        String[] whereArgs = new String[]{nome};

        try {
            Cursor resultSet = db.query(tblNome, null, whereClause, whereArgs, null, null, null);
            if (resultSet == null || resultSet.getCount() == 0) {
                return null;
            }
            resultSet.moveToFirst();

            String descrizione = resultSet.getString(resultSet.getColumnIndex(TabellaCampiComuni.FIELD_DESC));

            resultSet.close();
            return descrizione;
        } catch (SQLiteException sqle) {
            Log.e("LEGGI DESC CLASSE/RAZZA", "leggi fallita", sqle);
            return null;
        }
    }

    private HashMap<String, String> buildNomeDescrizione(String nomeTabella, String fieldNome){
        String nome = this.getFieldStringGiocatore(fieldNome);
        String descrizione = this.getDescrizione(nomeTabella, fieldNome, nome);
        HashMap<String, String> nomeDescrizione = new HashMap<>();
        nomeDescrizione.put(nome, descrizione);
        return nomeDescrizione;
    }
}
