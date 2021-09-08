package com.example.myfirstapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import com.example.myfirstapp.databaseTabelle.TabellaCampiComuni;
import com.example.myfirstapp.databaseTabelle.TabellaAbilita;
import com.example.myfirstapp.databaseTabelle.TabellaGiocatore;
import com.example.myfirstapp.databaseTabelle.TabelleHA;
import com.example.myfirstapp.domain.Abilita;
import com.example.myfirstapp.interactor.InterfaceAbilitaGiocatoreDB;

import java.util.ArrayList;
import java.util.List;

public class AbilitaGiocatoreDB implements InterfaceAbilitaGiocatoreDB {
    String nomecamp;
    String nomeg;
    DBHelper dbHelper;

    public AbilitaGiocatoreDB(String nomecamp, String nomeg, Context ctx) {
        this.nomecamp = nomecamp;
        this.nomeg = nomeg;
        this.dbHelper = new DBHelper(ctx);
    }

    @Override
    public int readModCompetenza() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String whereClause = TabellaGiocatore.FIELD_NOMECAMPAGNA + "=?" + " AND " + TabellaGiocatore.FIELD_NOMEG + "=?";
        String[] whereArgs = new String[]{nomecamp, nomeg};

        try {
            Cursor resultSet = db.query(TabellaGiocatore.TBL_NOME, null, whereClause, whereArgs, null, null, null);
            if (resultSet == null || resultSet.getCount() == 0) {
                return 0;
            }
            resultSet.moveToFirst();

            int modCompetenza = resultSet.getInt(resultSet.getColumnIndex(TabellaGiocatore.FIELD_MODCOMPETENZA));
            resultSet.close();
            return modCompetenza;

        } catch (SQLiteException sqle) {
            Log.e("LEGGI MODCOMP", "leggi fallita", sqle);
            return 0;
        }
    }

    @Override
    public List<Abilita> readAbilitaGiocatore() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String whereClause = TabellaGiocatore.FIELD_NOMECAMPAGNA + "=?" + " AND " + TabellaGiocatore.FIELD_NOMEG + "=?";
        String[] whereArgs = new String[]{nomecamp, nomeg};

        try {
            Cursor resultSet = db.query(TabelleHA.TBL_HAGA, null, whereClause, whereArgs, null, null, null);
            if (resultSet == null || resultSet.getCount() == 0) {
                return null;
            }
            resultSet.moveToFirst();

            List<Abilita> abilitaList = new ArrayList<Abilita>();
            while (!resultSet.isAfterLast()) {
                Abilita abilita = readAbilitaByName(resultSet.getString(resultSet.getColumnIndex(TabellaAbilita.FIELD_NOMEA)));
                if (abilita != null) {
                    boolean comp = resultSet.getInt(resultSet.getColumnIndex(TabellaCampiComuni.FIELD_COMPETENZA)) == 1;
                    abilita.setCompetenza(comp);
                    abilitaList.add(abilita);
                }
                resultSet.moveToNext();
            }

            resultSet.close();
            return abilitaList;
        } catch (SQLiteException sqle) {
            Log.e("LEGGI ABILITALIST", "fallita lettura", sqle);
            return null;
        }
    }

    @Override
    public boolean createAbilitaGiocatore(String nomea) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(TabellaCampiComuni.FIELD_COMPETENZA, 0);
        cv.put(TabellaGiocatore.FIELD_NOMECAMPAGNA, nomecamp);
        cv.put(TabellaGiocatore.FIELD_NOMEG, nomeg);
        cv.put(TabellaAbilita.FIELD_NOMEA, nomea);

        try {
            return db.insert(TabelleHA.TBL_HAGA, null, cv) > 0;
        } catch (SQLiteException sqle) {
            Log.e("AGGIUNGI HAGA", "aggiunta fallita", sqle);
            return false;
        }
    }

    @Override
    public boolean deleteAbilitaGiocatore(String nomea) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String whereClause = TabellaGiocatore.FIELD_NOMECAMPAGNA + "=?" + " AND " + TabellaGiocatore.FIELD_NOMEG + "=?" + " AND " + TabellaAbilita.FIELD_NOMEA + "=?";
        String[] whereArgs = new String[]{nomecamp, nomeg, nomea};

        try {
            return db.delete(TabelleHA.TBL_HAGA, whereClause, whereArgs) > 0;
        } catch (SQLiteException sqle) {
            Log.e("ELIMINA HAGA", "elimina fallita", sqle);
            return false;
        }
    }

    @Override
    public Abilita readAbilitaByName(String nomea) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String whereClause = TabellaAbilita.FIELD_NOMEA + " = ? ";
        String[] whereArgs = new String[]{nomea};

        try {
            Cursor resultSet = db.query(TabellaAbilita.TBL_NOME, null, whereClause, whereArgs, null, null, null);
            if (resultSet == null || resultSet.getCount() == 0) {
                return null;
            }
            resultSet.moveToFirst();

            String desc = resultSet.getString(resultSet.getColumnIndex(TabellaCampiComuni.FIELD_DESC));
            resultSet.close();
            return new Abilita(nomea, desc);
        } catch (SQLiteException sqle) {
            Log.e("LEGGI ABILITA", "leggi fallita", sqle);
            return null;
        }
    }

    @Override
    public boolean updateAbilitaGiocatore(String nomea, boolean competenza) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        String whereClause = TabellaGiocatore.FIELD_NOMECAMPAGNA + " = ? " + " AND " + TabellaGiocatore.FIELD_NOMEG + " = ? " + " AND " + TabellaAbilita.FIELD_NOMEA + " = ? ";
        String[] whereArgs = new String[]{nomecamp, nomeg, nomea};

        int flag = (competenza) ? 1 : 0;
        cv.put(TabellaCampiComuni.FIELD_COMPETENZA, flag);

        try {
            return db.update(TabelleHA.TBL_HAGA, cv, whereClause, whereArgs) > 0;
        } catch (SQLiteException sqle) {
            Log.e("AGGIORNA HAGA", "aggiornamento fallito", sqle);
            return false;
        }
    }

    @Override
    public List<Abilita> readAllAbilita() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        try {
            Cursor resultSet = db.query(TabellaAbilita.TBL_NOME, null, null, null, null, null, null);
            if (resultSet == null || resultSet.getCount() == 0) {
                return null;
            }
            resultSet.moveToFirst();

            List<Abilita> abilitaList = new ArrayList<Abilita>();
            while (!resultSet.isAfterLast()) {
                String nome = resultSet.getString(resultSet.getColumnIndex(TabellaAbilita.FIELD_NOMEA));
                String desc = resultSet.getString(resultSet.getColumnIndex(TabellaCampiComuni.FIELD_DESC));
                abilitaList.add(new Abilita(nome, desc));

                resultSet.moveToNext();
            }

            resultSet.close();
            return abilitaList;
        } catch (SQLiteException sqle) {
            Log.e("LEGGI ABILITALIST", "fallita lettura", sqle);
            return null;
        }
    }
}
