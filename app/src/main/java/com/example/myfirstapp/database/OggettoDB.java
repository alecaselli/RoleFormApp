package com.example.myfirstapp.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.myfirstapp.databasetabelle.TabellaArma;
import com.example.myfirstapp.databasetabelle.TabellaArmatura;
import com.example.myfirstapp.databasetabelle.TabellaCampiComuni;
import com.example.myfirstapp.databasetabelle.TabellaEquipaggiamento;
import com.example.myfirstapp.domain.Arma;
import com.example.myfirstapp.domain.Armatura;
import com.example.myfirstapp.domain.EquipaggiamentoOld;
import com.example.myfirstapp.interactorbosa.InterfaceOggettoDB;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class OggettoDB implements InterfaceOggettoDB {
    DBHelper dbHelper;

    public OggettoDB(Context ctx) {
        this.dbHelper = new DBHelper(ctx);
    }

    @Override
    public void createOggetto() {

    }

    @Nullable
    @Override
    public EquipaggiamentoOld readOggetto(String nome) {
        EquipaggiamentoOld equipaggiamento = this.leggiEquipaggiamento(nome);
        if(equipaggiamento == null)
            return equipaggiamento;
        switch (equipaggiamento.getTipo()) {
            case "arma":
                equipaggiamento = this.leggiArma(nome);
                break;
            case "armatura":
            case "scudo":
                equipaggiamento = this.leggiArmatura(nome);
                break;
            default:
                break;
        }
        return equipaggiamento;
    }

    @Override
    public void updateOggetto() {

    }

    @Override
    public void deleteOggetto(String Nome) {

    }

    @Nullable
    @Override
    public List<String> readAllNomiOggetti() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] colums = new String[]{TabellaEquipaggiamento.FIELD_NOMEE};

        try {
            Cursor resultSet = db.query(TabellaEquipaggiamento.TBL_NOME, colums, null, null, null, null, null);
            if (resultSet == null || resultSet.getCount() == 0) {
                return new ArrayList<>();
            }
            resultSet.moveToFirst();

            List<String> nomi = new ArrayList<>();
            while (!resultSet.isAfterLast()) {
                nomi.add(resultSet.getString(resultSet.getColumnIndex(TabellaEquipaggiamento.FIELD_NOMEE)));
                resultSet.moveToNext();
            }

            resultSet.close();
            return nomi;
        } catch (SQLiteException sqle) {
            Log.e("LEGGI PKS", "leggi fallita", sqle);
            return null;
        }
    }

    @Nullable
    public Arma leggiArma(@NotNull String nomee) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String whereClause = TabellaEquipaggiamento.FIELD_NOMEE + " = ? ";
        String[] whereArgs = new String[]{nomee};

        try {
            Cursor resultSet = db.query(TabellaArma.TBL_NOME, null, whereClause, whereArgs, null, null, null);
            if (resultSet == null || resultSet.getCount() == 0) {
                return null;
            }
            resultSet.moveToFirst();

            String danno = resultSet.getString(resultSet.getColumnIndex(TabellaArma.FIELD_DANNO));
            String proprieta = resultSet.getString(resultSet.getColumnIndex(TabellaArma.FIELD_PROPRIETA));
            EquipaggiamentoOld equi = this.leggiEquipaggiamento(nomee);
            if (equi == null)
                return null;

            resultSet.close();
            return new Arma(equi.getNome(), equi.getDescrizione(), equi.getTipo(), equi.getCosto(), equi.getCapacita(), equi.getPeso(), danno, proprieta, equi.getSubtipo());
        } catch (SQLiteException sqle) {
            Log.e("LEGGI ARMA", "leggi fallita", sqle);
            return null;
        }
    }

    @Nullable
    public Armatura leggiArmatura(@NotNull String nomee) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String whereClause = TabellaEquipaggiamento.FIELD_NOMEE + " = ? ";
        String[] whereArgs = new String[]{nomee};

        try {
            Cursor resultSet = db.query(TabellaArmatura.TBL_NOME, null, whereClause, whereArgs, null, null, null);
            if (resultSet == null || resultSet.getCount() == 0) {
                return null;
            }
            resultSet.moveToFirst();

            boolean nonFurtiva = resultSet.getInt(resultSet.getColumnIndex(TabellaArmatura.FIELD_NONFURTIVA)) == 1;
            int modificatoreCA = resultSet.getInt(resultSet.getColumnIndex(TabellaArmatura.FIELD_MODIFICATORECA));
            String tempoTogliere = resultSet.getString(resultSet.getColumnIndex(TabellaArmatura.FIELD_TEMPOTOGLIERE));
            String tempoIndossare = resultSet.getString(resultSet.getColumnIndex(TabellaArmatura.FIELD_TEMPOINDOSSARE));
            String forzaNecessaria = resultSet.getString(resultSet.getColumnIndex(TabellaArmatura.FIELD_FORZANECESSARIA));
            EquipaggiamentoOld equi = this.leggiEquipaggiamento(nomee);
            if (equi == null)
                return null;

            resultSet.close();
            return new Armatura(equi.getNome(), equi.getDescrizione(), equi.getTipo(), equi.getCosto(), equi.getCapacita(), equi.getPeso(), nonFurtiva, modificatoreCA, tempoTogliere, tempoIndossare, forzaNecessaria, equi.getSubtipo());
        } catch (SQLiteException sqle) {
            Log.e("LEGGI ARMATURA", "leggi fallita", sqle);
            return null;
        }
    }

    @Nullable
    public EquipaggiamentoOld leggiEquipaggiamento(@NotNull String nomee) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String whereClause = TabellaEquipaggiamento.FIELD_NOMEE + " = ? ";
        String[] whereArgs = new String[]{nomee};

        try {
            Cursor resultSet = db.query(TabellaEquipaggiamento.TBL_NOME, null, whereClause, whereArgs, null, null, null);
            if (resultSet == null || resultSet.getCount() == 0) {
                return null;
            }
            resultSet.moveToFirst();

            StringBuffer descrizione = new StringBuffer();
            descrizione.append(resultSet.getString(resultSet.getColumnIndex(TabellaCampiComuni.FIELD_DESC)));
            String subtipo = resultSet.getString(resultSet.getColumnIndex(TabellaEquipaggiamento.FIELD_SUBTIPO));
            String tipo = resultSet.getString(resultSet.getColumnIndex(TabellaEquipaggiamento.FIELD_TIPO));
            int costo = resultSet.getInt(resultSet.getColumnIndex(TabellaEquipaggiamento.FIELD_COSTO));
            int peso = resultSet.getInt(resultSet.getColumnIndex(TabellaEquipaggiamento.FIELD_PESO));
            int capacita = resultSet.getInt(resultSet.getColumnIndex(TabellaEquipaggiamento.FIELD_CAPACITA));

            resultSet.close();
            return new EquipaggiamentoOld(nomee, descrizione, tipo, costo, peso, capacita, subtipo);
        } catch (SQLiteException sqle) {
            Log.e("LEGGI EQUI", "leggi fallita", sqle);
            return null;
        }
    }
}
