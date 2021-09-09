package com.example.myfirstapp.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import com.example.myfirstapp.databasetabelle.TabellaCampiComuni;
import com.example.myfirstapp.databasetabelle.TabellaGiocatore;
import com.example.myfirstapp.databasetabelle.TabellaNomeVal;
import com.example.myfirstapp.databasetabelle.TabellaValuta;
import com.example.myfirstapp.interactor.InterfacePortafoglioDB;
import com.example.myfirstapp.domain.ValutaOld;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class PortafoglioDBReader implements InterfacePortafoglioDB {
    private final String nomecamp;
    private final String nomeg;
    private final DBHelper dbhelper;

    public PortafoglioDBReader(String nomecamp, String nomeg, Context ctx) {
        this.nomecamp = nomecamp;
        this.nomeg = nomeg;
        dbhelper = new DBHelper(ctx);
    }

    @Override
    public boolean updatePortafoglio(int valore) {
        return false;
    }

    private List<String> leggiNomeVal(@NotNull String nomev) {
        SQLiteDatabase db = dbhelper.getReadableDatabase();
        String whereClause = TabellaValuta.FIELD_NOMEV + " = ? ";
        String[] whereArgs = new String[]{nomev};

        try {
            Cursor resultSet = db.query(TabellaNomeVal.TBL_NOME, null, whereClause, whereArgs, null, null, null);
            if (resultSet == null || resultSet.getCount() == 0) {
                return null;
            }
            resultSet.moveToFirst();

            List<String> nomi = new ArrayList<String>();
            while (!resultSet.isAfterLast()) {
                String nome = resultSet.getString(resultSet.getColumnIndex(TabellaNomeVal.FIELD_NOMEVAL));
                nomi.add(nome);

                resultSet.moveToNext();
            }

            resultSet.close();
            return nomi;
        } catch (SQLiteException sqle) {
            Log.e("LEGGI NOMEVAL", "leggi fallita", sqle);
            return null;
        }
    }
    private ValutaOld leggiValuta(@NotNull String nomev) {
        SQLiteDatabase db = dbhelper.getReadableDatabase();
        String whereClause = TabellaValuta.FIELD_NOMEV + " = ? ";
        String[] whereArgs = new String[]{nomev};


        try {
            Cursor resultSet = db.query(TabellaValuta.TBL_NOME, null, whereClause, whereArgs, null, null, null);
            if (resultSet == null || resultSet.getCount() == 0) {
                return null;
            }
            resultSet.moveToFirst();

            StringBuffer descrizione = new StringBuffer();
            descrizione.append(resultSet.getString(resultSet.getColumnIndex(TabellaCampiComuni.FIELD_DESC)));
            int ratio = resultSet.getInt(resultSet.getColumnIndex(TabellaValuta.FIELD_RATIO));

            resultSet.close();
            return new ValutaOld(nomev, descrizione, ratio, 0, this.leggiNomeVal(nomev));
        } catch (SQLiteException sqle) {
            Log.e("LEGGI VALUTA", "leggi fallita", sqle);
            return null;
        }
    }

    @Override
    public ValutaOld readPortafoglio() {
        SQLiteDatabase db = dbhelper.getReadableDatabase();
        String whereClause = TabellaGiocatore.FIELD_NOMECAMPAGNA + "=?" + " AND " + TabellaGiocatore.FIELD_NOMEG + "=?";
        String[] whereArgs = new String[]{nomecamp, nomeg};

        try {
            Cursor resultSet = db.query(TabellaGiocatore.TBL_NOME, null, whereClause, whereArgs, null, null, null);
            if (resultSet == null || resultSet.getCount() == 0) {
                return null;
            }
            resultSet.moveToFirst();

            ValutaOld portafoglio = this.leggiValuta(resultSet.getString(resultSet.getColumnIndex(TabellaValuta.FIELD_NOMEV)));
            if(null!=portafoglio)
                portafoglio.setValore(resultSet.getInt(resultSet.getColumnIndex(TabellaGiocatore.FIELD_VALOREVAL)));
            else
                throw new SQLiteException();

            resultSet.close();
            return portafoglio;

        } catch (SQLiteException sqle) {
            Log.e("LEGGI PORTAFOGLIO", "leggi fallita", sqle);
            return null;
        }
    }

    @Override
    public void deletePortafoglio() {

    }

    @Override
    public void createPortafoglio(ValutaOld portafoglio) {

    }
}
