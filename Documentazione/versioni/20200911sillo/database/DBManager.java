package com.example.myfirstapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;


import com.example.myfirstapp.domain.*;
import com.example.myfirstapp.domain.Arma;
import com.example.myfirstapp.domain.Caratteristica;
import com.example.myfirstapp.domain.CaratteristicaBase;
import com.example.myfirstapp.domain.Classe;
import com.example.myfirstapp.domain.Descrivibile;
import com.example.myfirstapp.domain.Equipaggiamento;
import com.example.myfirstapp.domain.Incantesimo;
import com.example.myfirstapp.domain.Razza;
import com.example.myfirstapp.domain.Valuta;


import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

public class DBManager {
    private DBHelper dbhelper;

    public DBManager(Context ctx) {
        dbhelper=new DBHelper(ctx);
    }

    /* INSERT */
    public boolean aggiungiIncantesimi(@NotNull Incantesimo nuovo){
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(TabellaIncantesimi.FIELD_NOMEI, nuovo.getNome());
        cv.put(CampiComuni.FIELD_DESC, nuovo.getDescrizione().toString());
        cv.put(CampiComuni.FIELD_LIVELLO,nuovo.getLivello());
        cv.put(TabellaIncantesimi.FIELD_TEMPOINVOCAZIONE,nuovo.getTempoInvocazione());
        cv.put(TabellaIncantesimi.FIELD_RAGGIOAZIONE,nuovo.getRaggioAzione());
        cv.put(TabellaIncantesimi.FIELD_COMPONENTI,nuovo.getComponenti());
        cv.put(TabellaIncantesimi.FIELD_DURATA,nuovo.getDurata());

        try{
            return db.insert(TabellaIncantesimi.TBL_NOME,null,cv) > 0;
        }
        catch(SQLiteException sqle){
            return false;
        }
    }
    public boolean aggiungiAbilita(@NotNull Abilita nuovo){
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(CampiComuni.FIELD_COMPETENZA,nuovo.isCompetenza());
        cv.put(TabellaAbilita.FIELD_NOMEA,nuovo.getNome());
        cv.put(CampiComuni.FIELD_DESC,nuovo.getDescrizione().toString());

        try{
            return db.insert(TabellaAbilita.TBL_NOME,null,cv) > 0;
        }
        catch(SQLiteException sqle){
            return false;
        }
    }
    public boolean aggiungiValuta(@NotNull Valuta nuovo){
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(TabellaValuta.FIELD_NOMEV,nuovo.getRatio());
        cv.put(CampiComuni.FIELD_DESC,nuovo.getDescrizione().toString());
        cv.put(TabellaValuta.FIELD_RATIO,nuovo.getRatio());

        try{
            if(db.insert(TabellaValuta.TBL_NOME,null,cv) > 0){
                for(String nome : nuovo.getNomelist()){
                    if (!this.aggiungiNomeVal(nuovo.getNome(), nome))
                        return false;
                }
            }
            else return false;
            return true;
        }
        catch(SQLiteException sqle){
            return false;
        }
    }
    public boolean aggiungiRazza(@NotNull Razza nuovo){
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(TabellaRazza.FIELD_NOMER,nuovo.getNome());
        cv.put(CampiComuni.FIELD_DESC, nuovo.getDescrizione().toString());
        cv.put(TabellaRazza.FIELD_TAGLIA,nuovo.getTaglia());
        cv.put(TabellaRazza.FIELD_VELOCITA,nuovo.getVelocita());
        cv.put(CampiComuni.FIELD_LINGUA,nuovo.getLingua().toString());

        try{
            if (db.insert(TabellaRazza.TBL_NOME,null,cv) > 0) {
                for (Descrivibile nuovoP : nuovo.getPrivilegiRazza()) {
                    if (!this.aggiungiHarp(nuovo.getNome(), nuovoP.getNome()))
                        return false;
                }
                for (CaratteristicaBase nuovaCB : nuovo.getCaratteristicaBaseList()){
                    if(!this.aggiungiCarBase(nuovo.getNome(),nuovaCB))
                        return false;
                }
            }
            else return false;
            return true;
        }
        catch(SQLiteException sqle){
            return false;
        }
    }
    public boolean aggiungiPrivivlegi(@NotNull Descrivibile nuovo){
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(TabellaPrivilegi.FIELD_NOMEP,nuovo.getNome());
        cv.put(CampiComuni.FIELD_DESC,nuovo.getDescrizione().toString());

        try{
            return db.insert(TabellaPrivilegi.TBL_NOME,null,cv) > 0;
        }
        catch(SQLiteException sqle){
           return false;
        }
    }
    public boolean aggiungiClasse(@NotNull Classe nuovo){
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(TabellaClasse.FIELD_NOMECLA,nuovo.getNome());
        cv.put(CampiComuni.FIELD_DESC,nuovo.getDescrizione().toString());
        cv.put(CampiComuni.FIELD_NDADI,nuovo.getnDadi());
        cv.put(CampiComuni.FIELD_DADO,nuovo.getDado());
        cv.put(TabellaClasse.FIELD_DESCPRIVILEGI,nuovo.getDescrizionePrivilegiPoteri().toString());
        cv.put(CampiComuni.FIELD_COMPETENZA,nuovo.getCompetenza().toString());

        try{
            if (db.insert(TabellaClasse.TBL_NOME,null,cv) > 0){
                for (Descrivibile nuovoP : nuovo.getPrivilegiClasse()) {
                    if (!this.aggiungiHacp(nuovo.getNome(), nuovoP.getNome()))
                        return false;
                }
                for (Incantesimo nuovoi : nuovo.getIncantesimiClasse()){
                    if (!this.aggiungiHaci(nuovo.getNome(), nuovoi.getNome()))
                        return false;
                }
                for (Equipaggiamento nuovoe : nuovo.getEquipaggiamentoList()){
                    if(!this.aggiungiHace(nuovo.getNome(),nuovoe.getNome()))
                        return false;
                }
            }
            else return false;
            return true;
        }
        catch(SQLiteException sqle){
            return false;
        }
    }
    public boolean aggiungiEquipaggiamento(@NotNull Equipaggiamento nuovo){
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(TabellaEquipaggiamento.FIELD_NOMEE,nuovo.getNome());
        cv.put(CampiComuni.FIELD_DESC,nuovo.getDescrizione().toString());
        cv.put(TabellaEquipaggiamento.FIELD_COSTO,nuovo.getCosto());
        cv.put(TabellaEquipaggiamento.FIELD_PESO,nuovo.getPeso());
        cv.put(TabellaEquipaggiamento.FIELD_CAPACITA,nuovo.getCapacita());
        cv.put(TabellaEquipaggiamento.FIELD_TIPO,nuovo.getTipo());

        try{
            return db.insert(TabellaEquipaggiamento.TBL_NOME,null,cv) > 0;
        }
        catch(SQLiteException sqle){
          return false;
        }
    }
    public boolean aggiungiCaratteristica(@NotNull Caratteristica nuovo){
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(TabellaCaratteristica.FIELD_NOMECAR,nuovo.getNome());
        cv.put(CampiComuni.FIELD_DESC,nuovo.getDescrizione().toString());


        try{
            return db.insert(TabellaCaratteristica.TBL_NOME,null,cv) > 0;
        }
        catch(SQLiteException sqle){
            return false;
        }
    }
    public boolean aggiungiArma(@NotNull Arma nuovo){
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(TabellaEquipaggiamento.FIELD_NOMEE,nuovo.getNome());
        cv.put(TabellaArma.FIELD_DANNO,nuovo.getDanno());
        cv.put(TabellaArma.FIELD_PROPRIETA,nuovo.getProprieta());

        try{
            if (db.insert(TabellaArma.TBL_NOME,null,cv) > 0){
                return this.aggiungiEquipaggiamento(nuovo);
            }
            else return false;
        }
        catch(SQLiteException sqle){
            return false;
        }
    }
    public boolean aggiungiArmatura(@NotNull Armatura nuovo){
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(TabellaEquipaggiamento.FIELD_NOMEE,nuovo.getNome());
        cv.put(TabellaArmatura.FIELD_NONFURTIVA,nuovo.isNonFurtiva());
        cv.put(TabellaArmatura.FIELD_MODIFICATORECA,nuovo.getModificatoreCA());
        cv.put(TabellaArmatura.FIELD_TEMPOINDOSSARE,nuovo.getTempoIndossare());
        cv.put(TabellaArmatura.FIELD_TEMPOTOGLIERE,nuovo.getTempoTogliere());
        cv.put(TabellaArmatura.FIELD_FORZANECESSARIA,nuovo.getForzaNecessaria());

        try{
            if (db.insert(TabellaArmatura.TBL_NOME,null,cv) > 0){
                return this.aggiungiEquipaggiamento(nuovo);
            }
            else return false;
        }
        catch(SQLiteException sqle){
            return false;
        }
    }
    public boolean aggiungiGiocatore(@NotNull Giocatore nuovo){
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(TabellaGiocatore.FIELD_NOMECAMPAGNA, nuovo.getNomeCampagna());
        cv.put(TabellaGiocatore.FIELD_NOMEG, nuovo.getNome());
        cv.put(CampiComuni.FIELD_DESC,nuovo.getDescrizione().toString());
        cv.put(TabellaGiocatore.FIELD_MANA,nuovo.getMana());
        cv.put(CampiComuni.FIELD_LIVELLO,nuovo.getLivello());
        cv.put(TabellaGiocatore.FIELD_PUNTIXP,nuovo.getPuntiEsperienza());
        cv.put(TabellaGiocatore.FIELD_MODCOMPETENZA,nuovo.getModCompetenza());
        cv.put(TabellaGiocatore.FIELD_CAPACITABORSA,nuovo.getCapacitaBorsa());
        cv.put(TabellaGiocatore.FIELD_PUNTIFERITA,nuovo.getPuntiFerita());
        cv.put(TabellaGiocatore.FIELD_CLASSEARMATURA,nuovo.getClasseArmatura());
        cv.put(TabellaGiocatore.FIELD_PUNTISTAT,nuovo.getPuntiStat());
        cv.put(CampiComuni.FIELD_NDADI,nuovo.getnDadi());
        cv.put(CampiComuni.FIELD_DADO,nuovo.getDado());
        cv.put(TabellaGiocatore.FIELD_INIZIATIVA,nuovo.getIniziativa());
        cv.put(TabellaGiocatore.FIELD_ETA,nuovo.getEta());
        cv.put(TabellaGiocatore.FIELD_NOTEAVVENTURA,nuovo.getNoteAvventura().toString());
        cv.put(TabellaGiocatore.FIELD_ALLINEAMENTO,nuovo.getAllineamento().toString());
        cv.put(CampiComuni.FIELD_LINGUA,nuovo.getLingua().toString());
        cv.put(TabellaClasse.FIELD_NOMECLA,nuovo.getClasse().getNome());
        cv.put(TabellaRazza.FIELD_NOMER,nuovo.getRazza().getNome());
        cv.put(TabellaValuta.FIELD_NOMEV,nuovo.getPortafoglio().getNome());
        cv.put(TabellaGiocatore.FIELD_VALOREVAL,nuovo.getPortafoglio().getValore());


        try{
            if (db.insert(TabellaGiocatore.TBL_NOME,null,cv) > 0){
                for(Caratteristica nuovac : nuovo.getCaratteristicaList()){
                    if(!this.aggiungiCaratteristicaG(nuovo.getNomeCampagna(),nuovo.getNome(), nuovac))
                        return false;
                }
                for(Equipaggiamento nuovoe : nuovo.getEquipaggiato()){
                    if(!this.aggiungiHage(nuovo.getNomeCampagna(),nuovo.getNome(), nuovoe.getNome(),false))
                        return false;
                }
                for(Equipaggiamento nuovoe : nuovo.getBorsa()){
                    if(!this.aggiungiHage(nuovo.getNomeCampagna(),nuovo.getNome(), nuovoe.getNome(),true))
                        return false;
                }
                for(Incantesimo nuovoi : nuovo.getIncantesimiGiocatore()){
                    if(!this.aggiungiHagi(nuovo.getNomeCampagna(),nuovo.getNome(), nuovoi.getNome()))
                        return false;
                }
                for (Abilita nuovaa : nuovo.getAbilitaList()){
                    if(!this.aggiungiHaga(nuovo.getNomeCampagna(),nuovo.getNome(), nuovaa.getNome()))
                        return false;
                }
            }
            else return false;
            return true;
        }
        catch(SQLiteException sqle){
            return false;
        }
    }
    public boolean aggiungiCarBase(String nomer, @NotNull CaratteristicaBase nuovo){
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(TabellaRazza.FIELD_NOMER,nomer);
        cv.put(TabellaCarBase.FIELD_NOMECB,nuovo.getNome());
        cv.put(TabellaCarBase.FIELD_VALORE,nuovo.getValore());

        try{
            return db.insert(TabellaCarBase.TBL_NOME,null,cv) > 0;
        }
        catch(SQLiteException sqle){
            return false;
        }
    }
    public boolean aggiungiCaratteristicaG(String nomecamp, String nomeg, @NotNull Caratteristica nuova){
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(TabellaGiocatore.FIELD_NOMECAMPAGNA,nomecamp);
        cv.put(TabellaGiocatore.FIELD_NOMEG,nomeg);
        cv.put(TabellaCaratteristica.FIELD_NOMECAR,nuova.getNome());
        cv.put(TabellaCaratteristicaG.FIELD_TIROSALVEZZA,nuova.isTiroSalveza());
        cv.put(TabellaCaratteristicaG.FIELD_VALOREBASE,nuova.getValoreBase());
        cv.put(TabellaCaratteristicaG.FIELD_VALORELIVELLO,nuova.getValoreLivello());
        cv.put(TabellaCaratteristicaG.FIELD_VALOREEQUIPAGGAMENTO,nuova.getValoreEquipaggiamento());
        cv.put(TabellaCaratteristicaG.FIELD_VALOREBONUS,nuova.getValoreBonus());

        try{
            return db.insert(TabellaCaratteristicaG.TBL_NOME,null,cv) > 0;
        }
        catch(SQLiteException sqle){
            return false;
        }
    }
    public boolean aggiungiNomeVal(String nomev, String nome){
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(TabellaValuta.FIELD_NOMEV,nomev);
        cv.put(TabellaNomeVal.FIELD_NOMEVAL, nome);

        try{
            return db.insert(TabellaNomeVal.TBL_NOME,null,cv) > 0;
        }
        catch(SQLiteException sqle){
            return false;
        }
    }
    public boolean aggiungiHaga(String nomecamp, String nomeg, String nomea){
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(TabellaGiocatore.FIELD_NOMECAMPAGNA,nomecamp);
        cv.put(TabellaGiocatore.FIELD_NOMEG,nomeg);
        cv.put(TabellaEquipaggiamento.FIELD_NOMEE,nomea);

        try{
            return db.insert(TabelleHA.TBL_HAGA,null,cv) > 0;
        }
        catch(SQLiteException sqle){
         return false;
        }
    }
    public boolean aggiungiHage(String nomecamp, String nomeg, String nomee, boolean borsa){
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(TabellaGiocatore.FIELD_NOMECAMPAGNA,nomecamp);
        cv.put(TabellaGiocatore.FIELD_NOMEG,nomeg);
        cv.put(TabellaEquipaggiamento.FIELD_NOMEE,nomee);
        int flag = (borsa)? 1 : 0;
        cv.put(TabelleHA.FIELD_BORSA,flag);

        try{
            return db.insert(TabelleHA.TBL_HAGE,null,cv) > 0;
        }
        catch(SQLiteException sqle){
            return false;
        }
    }
    public boolean aggiungiHagi(String nomecamp, String nomeg, String nomei){
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(TabellaGiocatore.FIELD_NOMECAMPAGNA,nomecamp);
        cv.put(TabellaGiocatore.FIELD_NOMEG,nomeg);
        cv.put(TabellaIncantesimi.FIELD_NOMEI,nomei);

        try{
            return db.insert(TabelleHA.TBL_HAGI,null,cv) > 0;
        }
        catch(SQLiteException sqle){
            return false;
        }
    }
    public boolean aggiungiHace(String nomecla, String nomee){
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(TabellaClasse.FIELD_NOMECLA,nomecla);
        cv.put(TabellaEquipaggiamento.FIELD_NOMEE,nomee);

        try{
            return db.insert(TabelleHA.TBL_HACE,null,cv) > 0;
        }
        catch(SQLiteException sqle){
            return false;
        }
    }
    public boolean aggiungiHaci(String nomecla, String nomei){
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(TabellaClasse.FIELD_NOMECLA,nomecla);
        cv.put(TabellaIncantesimi.FIELD_NOMEI,nomei);

        try{
            return db.insert(TabelleHA.TBL_HACI,null,cv) > 0;
        }
        catch(SQLiteException sqle){
            return false;
        }
    }
    public boolean aggiungiHacp(String nomecla, String nomep){
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(TabellaClasse.FIELD_NOMECLA,nomecla);
        cv.put(TabellaPrivilegi.FIELD_NOMEP,nomep);

        try{
            return db.insert(TabelleHA.TBL_HACP,null,cv) > 0;
        }
        catch(SQLiteException sqle){
            return false;
        }
    }
    public boolean aggiungiHarp(String nomer, String nomep){
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(TabellaRazza.FIELD_NOMER,nomer);
        cv.put(TabellaPrivilegi.FIELD_NOMEP,nomep);

        try{
            return db.insert(TabelleHA.TBL_HARP,null,cv) > 0;
        }
        catch(SQLiteException sqle){
            return false;
        }
    }

    /* DELETE */
    public boolean eliminaIncantesimo(String nomei) {
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        String whereClause = TabellaIncantesimi.FIELD_NOMEI + "=?";
        String[] whereArgs =  new String[]{nomei};


        try {
            return db.delete(TabellaIncantesimi.TBL_NOME, whereClause, whereArgs) > 0;

        } catch (SQLiteException sqle) {
            return false;
        }
    }
    public boolean eliminaAbilita(String nomea) {
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        String whereClause = TabellaAbilita.FIELD_NOMEA + "=?";
        String[] whereArgs = new String[]{nomea};

        try {
            return db.delete(TabellaAbilita.TBL_NOME, whereClause, whereArgs) > 0;

        } catch (SQLiteException sqle) {
            return false;
        }
    }
    public boolean eliminavaluta(@NotNull Valuta eliminato) {
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        String whereClause = TabellaValuta.FIELD_NOMEV + "=?";
        String[] whereArgs =  new String[]{eliminato.getNome()};

        try {
            if(db.delete(TabellaValuta.TBL_NOME, whereClause, whereArgs) > 0) {
                for (String nome : eliminato.getNomelist()) {
                    if (!this.eliminaNomeVal(eliminato.getNome(), nome)) {
                        return false;
                    }
                }
                return true;
            }
            return false;

        } catch (SQLiteException sqle) {
            return false;
        }
    }
    public boolean eliminaRazza(@NotNull Razza eliminato) {
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        String whereClause = TabellaRazza.FIELD_NOMER + "=?";
        String[] whereArgs = new String[]{eliminato.getNome()};

        try {
            if(db.delete(TabellaRazza.TBL_NOME, whereClause, whereArgs) > 0){
                for(CaratteristicaBase carb : eliminato.getCaratteristicaBaseList()){
                    if(!this.eliminaCarBase(eliminato.getNome(),carb.getNome())){
                       return false;
                    }
                }
                for (Descrivibile priv : eliminato.getPrivilegiRazza()){
                    if(!this.eliminaHarp(eliminato.getNome(),priv.getNome())){
                        return false;
                    }
                }
                return true;
            }
            return false;

        } catch (SQLiteException sqle) {
            return false;
        }
    }
    public boolean eliminaPrivilegi(String nomep) {
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        String whereClause = TabellaPrivilegi.FIELD_NOMEP + "=?";
        String[] whereArgs = new String[]{nomep};

        try {
            return db.delete(TabellaPrivilegi.TBL_NOME, whereClause, whereArgs) > 0;

        } catch (SQLiteException sqle) {
            return false;
        }
    }
    public boolean eliminaClasse(@NotNull Classe eliminato) {
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        String whereClause = TabellaClasse.FIELD_NOMECLA + "=?";
        String[] whereArgs = new String[]{eliminato.getNome()};

        try {
            if(db.delete(TabellaClasse.TBL_NOME, whereClause, whereArgs) > 0){
                for (Equipaggiamento equi : eliminato.getEquipaggiamentoList()){
                    if((!this.eliminaHace(eliminato.getNome(),equi.getNome()))){
                        return false;
                    }
                }
                for (Descrivibile priv : eliminato.getPrivilegiClasse()){
                    if(!this.eliminaHacp(eliminato.getNome(),priv.getNome())){
                        return false;
                    }
                }
                for (Incantesimo inc : eliminato.getIncantesimiClasse()){
                    if(!this.eliminaHaci(eliminato.getNome(),inc.getNome())){
                        return false;
                    }
                }
                return true;
            }
            return false;

        } catch (SQLiteException sqle) {
            return false;
        }
    }
    public boolean eliminaEquipaggiamento(String nomee) {
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        String whereClause = TabellaEquipaggiamento.FIELD_NOMEE + "=?";
        String[] whereArgs = new String[]{nomee};

        try {
            return db.delete(TabellaEquipaggiamento.TBL_NOME, whereClause, whereArgs) > 0;

        } catch (SQLiteException sqle) {
            return false;
        }
    }
    public boolean eliminaCaratteristica(@NotNull Caratteristica eliminato) {
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        String whereClause = TabellaCaratteristica.FIELD_NOMECAR + "=?";
        String[] whereArgs = new String[]{eliminato.getNome()};

        try {
            if (db.delete(TabellaCaratteristica.TBL_NOME, whereClause, whereArgs) > 0)
                return db.delete(TabellaCaratteristicaG.TBL_NOME, whereClause, whereArgs) > 0;
            return false;

        } catch (SQLiteException sqle) {
            return false;
        }
    }
    public boolean eliminaArma(String nomee) {
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        String whereClause = TabellaEquipaggiamento.FIELD_NOMEE + "=?";
        String[] whereArgs = new String[]{nomee};

        try {
            if (db.delete(TabellaArma.TBL_NOME, whereClause, whereArgs) > 0)
                return this.eliminaEquipaggiamento(nomee);
            else return false;

        } catch (SQLiteException sqle) {
            return false;
        }
    }
    public boolean eliminaArmatura(String nomee) {
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        String whereClause = TabellaEquipaggiamento.FIELD_NOMEE + "=?";
        String[] whereArgs = new String[]{nomee};

        try {
            if (db.delete(TabellaArmatura.TBL_NOME, whereClause, whereArgs) > 0)
                return this.eliminaEquipaggiamento(nomee);
            else return false;

        } catch (SQLiteException sqle) {
            return false;
        }
    }
    public boolean eliminaGiocatore(@NotNull Giocatore eliminato) {
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        String whereClause = TabellaGiocatore.FIELD_NOMECAMPAGNA + "=?" + " AND " + TabellaGiocatore.FIELD_NOMEG + "=?";
        String[] whereArgs = new String[]{eliminato.getNomeCampagna(),eliminato.getNome()};

        try {
            if (db.delete(TabellaGiocatore.TBL_NOME, whereClause, whereArgs) > 0){
                for(Caratteristica car :eliminato.getCaratteristicaList()){
                    if(!eliminaCaratteristicaG(eliminato.getNomeCampagna(),eliminato.getNome(),car.getNome())){
                        return false;
                    }
                }
                for(Equipaggiamento equi : eliminato.getEquipaggiato()){
                    if(!this.eliminaHage(eliminato.getNomeCampagna(),eliminato.getNome(), equi.getNome()))
                        return false;
                }
                for(Equipaggiamento equi : eliminato.getBorsa()){
                    if(!this.eliminaHage(eliminato.getNomeCampagna(),eliminato.getNome(), equi.getNome()))
                        return false;
                }
                for(Incantesimo inc : eliminato.getIncantesimiGiocatore()){
                    if(!this.eliminaHagi(eliminato.getNomeCampagna(),eliminato.getNome(), inc.getNome()))
                        return false;
                }
                for (Abilita ab : eliminato.getAbilitaList()){
                    if(!this.eliminaHaga(eliminato.getNomeCampagna(),eliminato.getNome(), ab.getNome()))
                        return false;
                }
                return true;
            }
            return false;

        } catch (SQLiteException sqle) {
            return false;
        }
    }
    public boolean eliminaCarBase(String nomer, String nomecb) {
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        String whereClause = TabellaRazza.FIELD_NOMER + "=?" + " AND " + TabellaCarBase.FIELD_NOMECB + "=?";
        String[] whereArgs = new String[]{nomer,nomecb};

        try {
            return db.delete(TabellaCarBase.TBL_NOME, whereClause, whereArgs) > 0;

        } catch (SQLiteException sqle) {
            return false;
        }
    }
    public boolean eliminaCaratteristicaG(String nomecamp, String nomeg, String nomecar) {
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        String whereClause = TabellaGiocatore.FIELD_NOMECAMPAGNA + "=?" + " AND " + TabellaGiocatore.FIELD_NOMEG + "=?" + " AND " + TabellaCaratteristica.FIELD_NOMECAR + "=?";
        String[] whereArgs = new String[]{nomecamp,nomeg,nomecar};

        try {
            return db.delete(TabellaCaratteristicaG.TBL_NOME, whereClause, whereArgs) > 0;

        } catch (SQLiteException sqle) {
            return false;
        }
    }
    public boolean eliminaNomeVal(String nomev, String nome) {
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        String whereClause = TabellaValuta.FIELD_NOMEV + "=?" + " AND " + TabellaNomeVal.FIELD_NOMEVAL + "=?";
        String[] whereArgs = new String[]{nomev,nome};

        try {
            return db.delete(TabellaNomeVal.TBL_NOME, whereClause, whereArgs) > 0;

        } catch (SQLiteException sqle) {
            return false;
        }
    }
    public boolean eliminaHaga(String nomecamp, String nomeg, String nomea) {
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        String whereClause = TabellaGiocatore.FIELD_NOMECAMPAGNA + "=?" + " AND " + TabellaGiocatore.FIELD_NOMEG + "=?" + " AND " + TabellaAbilita.FIELD_NOMEA + "=?";
        String[] whereArgs = new String[]{nomecamp,nomeg,nomea};

        try {
            return db.delete(TabelleHA.TBL_HAGA, whereClause, whereArgs) > 0;

        } catch (SQLiteException sqle) {
            return false;
        }
    }
    public boolean eliminaHage(String nomecamp, String nomeg, String nomee) {
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        String whereClause = TabellaGiocatore.FIELD_NOMECAMPAGNA + "=?" + " AND " + TabellaGiocatore.FIELD_NOMEG + "=?" + " AND " + TabellaEquipaggiamento.FIELD_NOMEE + "=?";
        String[] whereArgs = new String[]{nomecamp,nomeg,nomee};

        try {
            return db.delete(TabelleHA.TBL_HAGE, whereClause, whereArgs) > 0;

        } catch (SQLiteException sqle) {
            return false;
        }
    }
    public boolean eliminaHagi(String nomecamp, String nomeg, String nomei) {
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        String whereClause = TabellaGiocatore.FIELD_NOMECAMPAGNA + "=?" + " AND " + TabellaGiocatore.FIELD_NOMEG + "=?" + " AND " + TabellaIncantesimi.FIELD_NOMEI + "=?";
        String[] whereArgs = new String[]{nomecamp,nomeg,nomei};

        try {
            return db.delete(TabelleHA.TBL_HAGI, whereClause, whereArgs) > 0;

        } catch (SQLiteException sqle) {
            return false;
        }
    }
    public boolean eliminaHace(String nomecla, String nomee) {
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        String whereClause = TabellaClasse.FIELD_NOMECLA + "=?" + " AND " + TabellaEquipaggiamento.FIELD_NOMEE + "=?";
        String[] whereArgs = new String[]{nomecla,nomee};

        try {
            return db.delete(TabelleHA.TBL_HACE, whereClause, whereArgs) > 0;

        } catch (SQLiteException sqle) {
            return false;
        }
    }
    public boolean eliminaHaci(String nomecla, String nomei) {
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        String whereClause = TabellaClasse.FIELD_NOMECLA + "=?" + " AND " + TabellaIncantesimi.FIELD_NOMEI + "=?";
        String[] whereArgs = new String[]{nomecla,nomei};

        try {
            return db.delete(TabelleHA.TBL_HACI, whereClause, whereArgs) > 0;

        } catch (SQLiteException sqle) {
            return false;
        }
    }
    public boolean eliminaHacp(String nomecla, String nomep) {
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        String whereClause = TabellaClasse.FIELD_NOMECLA + "=?" + " AND " + TabellaPrivilegi.FIELD_NOMEP + "=?";
        String[] whereArgs = new String[]{nomecla,nomep};

        try {
            return db.delete(TabelleHA.TBL_HACP, whereClause, whereArgs) > 0;

        } catch (SQLiteException sqle) {
            return false;
        }
    }
    public boolean eliminaHarp(String nomer, String nomep) {
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        String whereClause = TabellaRazza.FIELD_NOMER + "=?" + " AND " + TabellaPrivilegi.FIELD_NOMEP + "=?";
        String[] whereArgs = new String[]{nomer,nomep};

        try {
            return db.delete(TabelleHA.TBL_HARP, whereClause, whereArgs) > 0;

        } catch (SQLiteException sqle) {
            return false;
        }
    }

    /* UPDATE */
    public boolean aggiornaIncantesimo(@NotNull Incantesimo aggiornato){
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        String whereClause = TabellaIncantesimi.FIELD_NOMEI + " = ? ";
        String[] whereArgs = new String[]{aggiornato.getNome()};

        cv.put(CampiComuni.FIELD_DESC, aggiornato.getDescrizione().toString());
        cv.put(CampiComuni.FIELD_LIVELLO,aggiornato.getLivello());
        cv.put(TabellaIncantesimi.FIELD_TEMPOINVOCAZIONE,aggiornato.getTempoInvocazione());
        cv.put(TabellaIncantesimi.FIELD_RAGGIOAZIONE,aggiornato.getRaggioAzione());
        cv.put(TabellaIncantesimi.FIELD_COMPONENTI,aggiornato.getComponenti());
        cv.put(TabellaIncantesimi.FIELD_DURATA,aggiornato.getDurata());

        try{
            return  db.update(TabellaIncantesimi.TBL_NOME,cv,whereClause,whereArgs) > 0;

        }
        catch(SQLiteException sqle){
            return false;
        }
    }
    public boolean aggiornaAbilita(@NotNull Abilita aggiornato){
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        String whereClause = TabellaAbilita.FIELD_NOMEA + " = ? ";
        String[] whereArgs = new String[]{aggiornato.getNome()};

        cv.put(CampiComuni.FIELD_COMPETENZA,aggiornato.isCompetenza());
        cv.put(CampiComuni.FIELD_DESC,aggiornato.getDescrizione().toString());

        try{
            return db.update(TabellaAbilita.TBL_NOME,cv,whereClause,whereArgs) > 0;
        }
        catch(SQLiteException sqle){
            return false;
        }
    }
    public boolean aggiornaValuta(@NotNull Valuta aggiornato){
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        String whereClause = TabellaValuta.FIELD_NOMEV + " = ? ";
        String[] whereArgs = new String[]{aggiornato.getNome()};


        cv.put(CampiComuni.FIELD_DESC,aggiornato.getDescrizione().toString());
        cv.put(TabellaValuta.FIELD_RATIO,aggiornato.getRatio());

        try{
            if (db.update(TabellaValuta.TBL_NOME,cv,whereClause,whereArgs) > 0){
                for(String nome : aggiornato.getNomelist()){
                    if(!this.aggiornaNomeVal(aggiornato.getNome(),nome))
                        return false;
                }
                return true;
            }
            return false;
        }
        catch(SQLiteException sqle){
            return false;
        }
    }
    public boolean aggiornaRazza(@NotNull Razza aggiornato){
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        String whereClause = TabellaRazza.FIELD_NOMER + " = ? ";
        String[] whereArgs = new String[]{aggiornato.getNome()};

        cv.put(CampiComuni.FIELD_DESC, aggiornato.getDescrizione().toString());
        cv.put(TabellaRazza.FIELD_TAGLIA,aggiornato.getTaglia());
        cv.put(TabellaRazza.FIELD_VELOCITA,aggiornato.getVelocita());
        cv.put(CampiComuni.FIELD_LINGUA,aggiornato.getLingua().toString());

        try{
            if (db.update(TabellaRazza.TBL_NOME,cv,whereClause,whereArgs) > 0){
                for (Descrivibile nuovoP : aggiornato.getPrivilegiRazza()) {
                    if (!this.aggiornaHarp(aggiornato.getNome(), nuovoP.getNome()))
                        return false;
                }
                for (CaratteristicaBase nuovaCB : aggiornato.getCaratteristicaBaseList()){
                    if(!this.aggiornaCarBase(aggiornato.getNome(),nuovaCB))
                        return false;
                }
                return true;
            }
            return false;
        }
        catch(SQLiteException sqle){
            return false;
        }
    }
    public boolean aggiornaPrivivlegi(@NotNull Descrivibile aggiornato){
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        String whereClause = TabellaPrivilegi.FIELD_NOMEP + " = ? ";
        String[] whereArgs = new String[]{aggiornato.getNome()};

        cv.put(CampiComuni.FIELD_DESC,aggiornato.getDescrizione().toString());

        try{
            return db.update(TabellaPrivilegi.TBL_NOME,cv,whereClause,whereArgs) > 0;

        }
        catch(SQLiteException sqle){
            return false;
        }
    }
    public boolean aggiornaClasse(@NotNull Classe aggiornato){
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        String whereClause = TabellaClasse.FIELD_NOMECLA + " = ? ";
        String[] whereArgs = new String[]{aggiornato.getNome()};

        cv.put(CampiComuni.FIELD_DESC,aggiornato.getDescrizione().toString());
        cv.put(CampiComuni.FIELD_NDADI,aggiornato.getnDadi());
        cv.put(CampiComuni.FIELD_DADO,aggiornato.getDado());
        cv.put(TabellaClasse.FIELD_DESCPRIVILEGI,aggiornato.getDescrizionePrivilegiPoteri().toString());
        cv.put(CampiComuni.FIELD_COMPETENZA,aggiornato.getCompetenza().toString());

        try{
            if (db.update(TabellaClasse.TBL_NOME,cv,whereClause,whereArgs) > 0){
                for (Descrivibile nuovoP : aggiornato.getPrivilegiClasse()) {
                    if (!this.aggiornaHacp(aggiornato.getNome(), nuovoP.getNome()))
                        return false;
                }
                for (Incantesimo nuovoi : aggiornato.getIncantesimiClasse()){
                    if (!this.aggiornaHaci(aggiornato.getNome(), nuovoi.getNome()))
                        return false;
                }
                for (Equipaggiamento nuovoe : aggiornato.getEquipaggiamentoList()){
                    if(!this.aggiornaHace(aggiornato.getNome(),nuovoe.getNome()))
                        return false;
                }
                return true;
            }
            return false;
        }
        catch(SQLiteException sqle){
            return false;
        }
    }
    public boolean aggiornaEquipaggiamento(@NotNull Equipaggiamento aggiornato){
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        String whereClause = TabellaEquipaggiamento.FIELD_NOMEE + " = ? ";
        String[] whereArgs = new String[]{aggiornato.getNome()};

        cv.put(CampiComuni.FIELD_DESC,aggiornato.getDescrizione().toString());
        cv.put(TabellaEquipaggiamento.FIELD_COSTO,aggiornato.getCosto());
        cv.put(TabellaEquipaggiamento.FIELD_PESO,aggiornato.getPeso());
        cv.put(TabellaEquipaggiamento.FIELD_CAPACITA,aggiornato.getCapacita());
        cv.put(TabellaEquipaggiamento.FIELD_TIPO,aggiornato.getTipo());

        try{
            return db.update(TabellaEquipaggiamento.TBL_NOME,cv,whereClause,whereArgs) > 0;
        }
        catch(SQLiteException sqle){
            return false;
        }
    }
    public boolean aggiornaCaratteristica(String nomecar, @NotNull StringBuffer desc){
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        String whereClause = TabellaCaratteristica.FIELD_NOMECAR + " = ? ";
        String[] whereArgs = new String[]{nomecar};

        cv.put(CampiComuni.FIELD_DESC,desc.toString());

        try{
            return db.update(TabellaCaratteristica.TBL_NOME,cv,whereClause,whereArgs) > 0;
        }
        catch(SQLiteException sqle){
            return false;
        }
    }
    public boolean aggiornaArma(@NotNull Arma aggiornato){
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        String whereClause = TabellaEquipaggiamento.FIELD_NOMEE + " = ? ";
        String[] whereArgs = new String[]{aggiornato.getNome()};

        cv.put(TabellaArma.FIELD_DANNO,aggiornato.getDanno());
        cv.put(TabellaArma.FIELD_PROPRIETA,aggiornato.getProprieta());

        try{
            if (db.update(TabellaArma.TBL_NOME,cv,whereClause,whereArgs) > 0)
                return this.aggiornaEquipaggiamento(aggiornato);
            return false;
        }
        catch(SQLiteException sqle){
            return false;
        }
    }
    public boolean aggiornaArmatura(@NotNull Armatura aggiornato){
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        String whereClause = TabellaEquipaggiamento.FIELD_NOMEE + " = ? ";
        String[] whereArgs = new String[]{aggiornato.getNome()};

        cv.put(TabellaArmatura.FIELD_NONFURTIVA,aggiornato.isNonFurtiva());
        cv.put(TabellaArmatura.FIELD_MODIFICATORECA,aggiornato.getModificatoreCA());
        cv.put(TabellaArmatura.FIELD_TEMPOINDOSSARE,aggiornato.getTempoIndossare());
        cv.put(TabellaArmatura.FIELD_TEMPOTOGLIERE,aggiornato.getTempoTogliere());
        cv.put(TabellaArmatura.FIELD_FORZANECESSARIA,aggiornato.getForzaNecessaria());

        try{
            if (db.insert(TabellaArmatura.TBL_NOME,null,cv) > 0)
                return this.aggiornaEquipaggiamento(aggiornato);
            return false;
        }
        catch(SQLiteException sqle){
            return false;
        }
    }
    public boolean aggiornaGiocatore(@NotNull Giocatore aggiornato){
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        String whereClause = TabellaGiocatore.FIELD_NOMECAMPAGNA + " = ? " + " AND " + TabellaGiocatore.FIELD_NOMEG + " = ? ";
        String[] whereArgs = new String[]{aggiornato.getNomeCampagna(),aggiornato.getNome()};

        cv.put(CampiComuni.FIELD_DESC,aggiornato.getDescrizione().toString());
        cv.put(TabellaGiocatore.FIELD_MANA,aggiornato.getMana());
        cv.put(CampiComuni.FIELD_LIVELLO,aggiornato.getLivello());
        cv.put(TabellaGiocatore.FIELD_PUNTIXP,aggiornato.getPuntiEsperienza());
        cv.put(TabellaGiocatore.FIELD_MODCOMPETENZA,aggiornato.getModCompetenza());
        cv.put(TabellaGiocatore.FIELD_CAPACITABORSA,aggiornato.getCapacitaBorsa());
        cv.put(TabellaGiocatore.FIELD_PUNTIFERITA,aggiornato.getPuntiFerita());
        cv.put(TabellaGiocatore.FIELD_CLASSEARMATURA,aggiornato.getClasseArmatura());
        cv.put(TabellaGiocatore.FIELD_PUNTISTAT,aggiornato.getPuntiStat());
        cv.put(CampiComuni.FIELD_NDADI,aggiornato.getnDadi());
        cv.put(CampiComuni.FIELD_DADO,aggiornato.getDado());
        cv.put(TabellaGiocatore.FIELD_INIZIATIVA,aggiornato.getIniziativa());
        cv.put(TabellaGiocatore.FIELD_ETA,aggiornato.getEta());
        cv.put(TabellaGiocatore.FIELD_NOTEAVVENTURA,aggiornato.getNoteAvventura().toString());
        cv.put(TabellaGiocatore.FIELD_ALLINEAMENTO,aggiornato.getAllineamento().toString());
        cv.put(CampiComuni.FIELD_LINGUA,aggiornato.getLingua().toString());
        cv.put(TabellaClasse.FIELD_NOMECLA,aggiornato.getClasse().getNome());
        cv.put(TabellaRazza.FIELD_NOMER,aggiornato.getRazza().getNome());
        cv.put(TabellaValuta.FIELD_NOMEV,aggiornato.getPortafoglio().getNome());
        cv.put(TabellaGiocatore.FIELD_VALOREVAL,aggiornato.getPortafoglio().getValore());


        try{
            if (db.update(TabellaGiocatore.TBL_NOME,cv,whereClause,whereArgs) > 0){
                for(Caratteristica nuovac : aggiornato.getCaratteristicaList()){
                    if (!this.aggiornaCaratteristicaG(aggiornato.getNomeCampagna(),aggiornato.getNome(), nuovac))
                        return false;
                }
                for(Equipaggiamento nuovoe : aggiornato.getEquipaggiato()){
                    if (!this.aggiornaHage(aggiornato.getNomeCampagna(),aggiornato.getNome(), nuovoe.getNome(),false))
                        return false;
                }
                for(Equipaggiamento nuovoe : aggiornato.getBorsa()){
                    if (!this.aggiornaHage(aggiornato.getNomeCampagna(),aggiornato.getNome(), nuovoe.getNome(),true))
                        return false;
                }
                for(Incantesimo nuovoi : aggiornato.getIncantesimiGiocatore()){
                    if (!this.aggiornaHagi(aggiornato.getNomeCampagna(),aggiornato.getNome(), nuovoi.getNome()))
                        return false;
                }
                for (Abilita nuovaa : aggiornato.getAbilitaList()){
                    if (!this.aggiornaHaga(aggiornato.getNomeCampagna(),aggiornato.getNome(), nuovaa.getNome()))
                        return false;
                }
                return true;
            }
            return false;
        }
        catch(SQLiteException sqle){
            return false;
        }
    }
    public boolean aggiornaCarBase(String nomer, @NotNull CaratteristicaBase aggiornato){
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        String whereClause = TabellaRazza.FIELD_NOMER + " = ? " + " AND " + TabellaCarBase.FIELD_NOMECB + " = ? " ;
        String[] whereArgs = new String[]{nomer,aggiornato.getNome()};

        cv.put(TabellaCarBase.FIELD_VALORE,aggiornato.getValore());

        try{
            return db.update(TabellaCarBase.TBL_NOME,cv,whereClause,whereArgs) > 0;
        }
        catch(SQLiteException sqle){
            return false;
        }
    }
    public boolean aggiornaCaratteristicaG(String nomecamp, String nomeg, @NotNull Caratteristica aggiornato){
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        String whereClause = TabellaGiocatore.FIELD_NOMECAMPAGNA + " = ? " + " AND " + TabellaGiocatore.FIELD_NOMEG + " = ? " + " AND " + TabellaCaratteristica.FIELD_NOMECAR + " = ? " ;
        String[] whereArgs = new String[]{nomecamp, nomeg, aggiornato.getNome()};

        cv.put(TabellaCaratteristicaG.FIELD_TIROSALVEZZA,aggiornato.isTiroSalveza());
        cv.put(TabellaCaratteristicaG.FIELD_VALOREBASE,aggiornato.getValoreBase());
        cv.put(TabellaCaratteristicaG.FIELD_VALORELIVELLO,aggiornato.getValoreLivello());
        cv.put(TabellaCaratteristicaG.FIELD_VALOREEQUIPAGGAMENTO,aggiornato.getValoreEquipaggiamento());
        cv.put(TabellaCaratteristicaG.FIELD_VALOREBONUS,aggiornato.getValoreBonus());

        try{
            return db.update(TabellaCaratteristicaG.TBL_NOME,cv,whereClause,whereArgs) > 0;
        }
        catch(SQLiteException sqle){
            return false;
        }
    }
    public boolean aggiornaNomeVal(String nomev, String nome){
        return aggiungiNomeVal(nomev, nome);
    }
    public boolean aggiornaHaga(String nomecamp, String nomeg, String nomea){
        return aggiungiHaga(nomecamp, nomeg, nomea);
    }
    public boolean aggiornaHage(String nomecamp, String nomeg, String nomee, boolean borsa){
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        String whereClause = TabellaGiocatore.FIELD_NOMECAMPAGNA + " = ? " + " AND " + TabellaGiocatore.FIELD_NOMEG + " = ? " + " AND " + TabellaEquipaggiamento.FIELD_NOMEE + " = ? " ;
        String[] whereArgs = new String[]{nomecamp, nomeg, nomee};

        int flag = (borsa)? 1 : 0;
        cv.put(TabelleHA.FIELD_BORSA,flag);

        try{
            if (db.update(TabelleHA.TBL_HAGE,cv,whereClause,whereArgs) > 0)
                return true;
            return this.aggiungiHage(nomecamp,nomeg,nomee,borsa);
        }
        catch(SQLiteException sqle){
            return false;
        }
    }
    public boolean aggiornaHagi(String nomecamp, String nomeg, String nomei){
        return aggiungiHagi(nomecamp, nomeg, nomei);
    }
    public boolean aggiornaHace(String nomecla, String nomee) {
        return aggiungiHace(nomecla, nomee);
    }
    public boolean aggiornaHaci(String nomecla, String nomei){
        return aggiungiHaci(nomecla, nomei);
    }
    public boolean aggiornaHacp(String nomecla, String nomep){
        return aggiungiHacp(nomecla, nomep);
    }
    public boolean aggiornaHarp(String nomer, String nomep){
        return aggiungiHarp(nomer, nomep);
    }

    /* READ */
    public Cursor leggiTabella (String table){
        SQLiteDatabase db = dbhelper.getReadableDatabase();

        try {
            Cursor resultSet = db.query(table, null, null, null, null, null, null);
            if (resultSet == null || resultSet.getCount() == 0){
                return null;
            }

            return resultSet;
        }
        catch (SQLiteException sqle) {
            return null;
        }
    }
    public Incantesimo leggiIncantesimo(String nomei){
        SQLiteDatabase db = dbhelper.getReadableDatabase();
        String whereClause = TabellaIncantesimi.FIELD_NOMEI + " = ? ";
        String[] whereArgs = new String[]{nomei};

        try {
            Cursor resultSet = db.query(TabellaIncantesimi.TBL_NOME, null, whereClause, whereArgs, null, null, null);
            if (resultSet == null || resultSet.getCount() == 0){
                return null;
            }
            resultSet.moveToFirst();

            StringBuffer descrizione = new StringBuffer();
            descrizione.append(resultSet.getString(resultSet.getColumnIndex(CampiComuni.FIELD_DESC)));
            int livello = resultSet.getInt(resultSet.getColumnIndex(CampiComuni.FIELD_LIVELLO));
            String tempoInvocazione = resultSet.getString(resultSet.getColumnIndex(TabellaIncantesimi.FIELD_TEMPOINVOCAZIONE));
            String raggioAzione = resultSet.getString(resultSet.getColumnIndex(TabellaIncantesimi.FIELD_RAGGIOAZIONE));
            String componenti = resultSet.getString(resultSet.getColumnIndex(TabellaIncantesimi.FIELD_COMPONENTI));
            String durata = resultSet.getString(resultSet.getColumnIndex(TabellaIncantesimi.FIELD_DURATA));

            resultSet.close();
            return new Incantesimo(nomei,descrizione,tempoInvocazione,raggioAzione,componenti,durata,livello);
        }
        catch (SQLiteException sqle) {
            return null;
        }
    }
    /*public List<Incantesimo> leggiIncantesimiGiocatore(String nomecamp, String nomeg) {
        SQLiteDatabase db = dbhelper.getReadableDatabase();
        String whereClause = TabellaGiocatore.FIELD_NOMECAMPAGNA + "=?" + " AND " + TabellaGiocatore.FIELD_NOMEG + "=?";
        String[] columns =  new String[]{TabellaIncantesimi.FIELD_NOMEI};
        String[] whereArgs = new String[]{nomecamp, nomeg};

        try {
            Cursor resultSet = db.query(TabelleHA.TBL_HAGI, columns, whereClause, whereArgs, null, null, null);
            return leggiIncantesimiList(resultSet);
        }
        catch (SQLiteException sqle) {
            return null;
        }
    }*/
    public List<Incantesimo> leggiIncantesimi(@NotNull String... arg) {
        SQLiteDatabase db = dbhelper.getReadableDatabase();
        String table;
        String whereClause;
        String[] whereArgs;
        switch (arg.length){
            case 1:
                table = TabelleHA.TBL_HACI;
                whereClause = TabellaClasse.FIELD_NOMECLA + "=?";
                whereArgs = new String[]{arg[0]};
                break;
            case 2:
                table = TabelleHA.TBL_HAGI;
                whereClause = TabellaGiocatore.FIELD_NOMECAMPAGNA + "=?" + " AND " + TabellaGiocatore.FIELD_NOMEG + "=?";
                whereArgs = new String[]{arg[0],arg[1]};
                break;
            default:
                return null;
        }

        try {
            Cursor resultSet = db.query(table, null, whereClause, whereArgs, null, null, null);
            if (resultSet == null || resultSet.getCount() == 0){
                return null;
            }
            resultSet.moveToFirst();

            List<Incantesimo> incantesimi = new ArrayList<Incantesimo>();
            while (!resultSet.isAfterLast()) {
                Incantesimo incantesimo = leggiIncantesimo(resultSet.getString(resultSet.getColumnIndex(TabellaIncantesimi.FIELD_NOMEI)));
                if (incantesimo != null)
                    incantesimi.add(incantesimo);

                resultSet.moveToNext();
            }

            resultSet.close();
            return incantesimi;
        }
        catch (SQLiteException sqle) {
            return null;
        }
    }
    public Abilita leggiAbilita(String nomea){
        SQLiteDatabase db = dbhelper.getReadableDatabase();
        String whereClause = TabellaAbilita.FIELD_NOMEA + " = ? ";
        String[] whereArgs = new String[]{nomea};

        try{
            Cursor resultSet = db.query(TabellaAbilita.TBL_NOME, null, whereClause, whereArgs, null, null, null);
            if (resultSet == null || resultSet.getCount() == 0){
                return null;
            }
            resultSet.moveToFirst();

            boolean competenza = resultSet.getInt(resultSet.getColumnIndex(CampiComuni.FIELD_COMPETENZA)) == 1;
            StringBuffer descrizione = new StringBuffer();
            descrizione.append(resultSet.getString(resultSet.getColumnIndex(CampiComuni.FIELD_DESC)));

            resultSet.close();
            return new Abilita(nomea, descrizione, competenza);
        }
        catch(SQLiteException sqle){
            return null;
        }
    }
    public List<Abilita> leggiAbilita(String nomecamp, String nomeg){
        SQLiteDatabase db = dbhelper.getReadableDatabase();
        String whereClause = TabellaGiocatore.FIELD_NOMECAMPAGNA + "=?" + " AND " + TabellaGiocatore.FIELD_NOMEG + "=?";
        String[] whereArgs = new String[]{nomecamp,nomeg};

        try {
            Cursor resultSet = db.query(TabelleHA.TBL_HAGA, null, whereClause, whereArgs, null, null, null);
            if (resultSet == null || resultSet.getCount() == 0){
                return null;
            }
            resultSet.moveToFirst();

            List<Abilita> abilitaList = new ArrayList<Abilita>();
            while (!resultSet.isAfterLast()) {
                Abilita abilita = leggiAbilita(resultSet.getString(resultSet.getColumnIndex(TabellaAbilita.FIELD_NOMEA)));
                if (abilita != null)
                    abilitaList.add(abilita);

                resultSet.moveToNext();
            }

            resultSet.close();
            return abilitaList;
        }
        catch (SQLiteException sqle) {
            return null;
        }
    }
    @Nullable
    private List<String> leggiNomeVal(String nomev){
        SQLiteDatabase db = dbhelper.getReadableDatabase();
        String whereClause = TabellaValuta.FIELD_NOMEV + " = ? ";
        String[] whereArgs = new String[]{nomev};

        try{
            Cursor resultSet = db.query(TabellaNomeVal.TBL_NOME, null, whereClause, whereArgs, null, null, null);
            if (resultSet == null || resultSet.getCount() == 0){
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
        }
        catch(SQLiteException sqle){
            return null;
        }
    }
    public Valuta leggiValuta(String nomev){
        SQLiteDatabase db = dbhelper.getReadableDatabase();
        String whereClause = TabellaValuta.FIELD_NOMEV + " = ? ";
        String[] whereArgs = new String[]{nomev};


        try{
            Cursor resultSet = db.query(TabellaValuta.TBL_NOME, null, whereClause, whereArgs, null, null, null);
            if (resultSet == null || resultSet.getCount() == 0){
                return null;
            }
            resultSet.moveToFirst();

            StringBuffer descrizione = new StringBuffer();
            descrizione.append(resultSet.getString(resultSet.getColumnIndex(CampiComuni.FIELD_DESC)));
            int  ratio = resultSet.getInt(resultSet.getColumnIndex(TabellaValuta.FIELD_RATIO));

            resultSet.close();
            return new Valuta(nomev, descrizione, ratio, 0, this.leggiNomeVal(nomev));
        }
        catch(SQLiteException sqle){
            return null;
        }
    }
    public Descrivibile leggiPrivilegio(String nomep){
        SQLiteDatabase db = dbhelper.getReadableDatabase();
        String whereClause = TabellaPrivilegi.FIELD_NOMEP + " = ? ";
        String[] whereArgs = new String[]{nomep};

        try {
            Cursor resultSet = db.query(TabellaPrivilegi.TBL_NOME, null, whereClause, whereArgs, null, null, null);
            if (resultSet == null || resultSet.getCount() == 0){
            return null;
        }
        resultSet.moveToFirst();

        StringBuffer descrizione = new StringBuffer();
        descrizione.append(resultSet.getString(resultSet.getColumnIndex(CampiComuni.FIELD_DESC)));

        resultSet.close();
        return new Descrivibile(nomep,descrizione);
    }
        catch (SQLiteException sqle) {
        return null;
    }
    }
    public List<Descrivibile> leggiPrivilegi(@NotNull String... arg){
        SQLiteDatabase db = dbhelper.getReadableDatabase();
        String table;
        String whereClause;
        String[] whereArgs = new String[]{arg[0]};
        switch (arg[1]){
            case "razza":
                table = TabelleHA.TBL_HARP;
                whereClause = TabellaRazza.FIELD_NOMER + "=?";

                break;
            case "classe":
                table = TabelleHA.TBL_HACP;
                whereClause = TabellaClasse.FIELD_NOMECLA + "=?";
                break;
            default:
                return null;
        }

        try {
            Cursor resultSet = db.query(table, null, whereClause, whereArgs, null, null, null);
            if (resultSet == null || resultSet.getCount() == 0){
                return null;
            }
            resultSet.moveToFirst();

            List<Descrivibile> privilegi = new ArrayList<Descrivibile>();
            while (!resultSet.isAfterLast()) {
                Descrivibile privilegio = leggiPrivilegio(resultSet.getString(resultSet.getColumnIndex(TabellaPrivilegi.FIELD_NOMEP)));
                if (privilegio != null)
                    privilegi.add(privilegio);

                resultSet.moveToNext();
            }

            resultSet.close();
            return privilegi;
        }
        catch (SQLiteException sqle) {
            return null;
        }
    } //sia HACP che HARP
    public List<CaratteristicaBase> leggiCarBase(String nomer){
        SQLiteDatabase db = dbhelper.getReadableDatabase();
        String whereClause = TabellaRazza.FIELD_NOMER + " = ? ";
        String[] whereArgs = new String[]{nomer};

        try{
            Cursor resultSet = db.query(TabellaCarBase.TBL_NOME, null, whereClause, whereArgs, null, null, null);
            if (resultSet == null || resultSet.getCount() == 0){
                return null;
            }
            resultSet.moveToFirst();

            List<CaratteristicaBase> carBaselist = new ArrayList<CaratteristicaBase>();
            while (!resultSet.isAfterLast()) {
                String nome = resultSet.getString(resultSet.getColumnIndex(TabellaCarBase.FIELD_NOMECB));
                int valore = resultSet.getInt(resultSet.getColumnIndex(TabellaCarBase.FIELD_VALORE));
                carBaselist.add(new CaratteristicaBase(nome, valore));

                resultSet.moveToNext();
            }

            resultSet.close();
            return carBaselist;
        }
        catch(SQLiteException sqle){
            return null;
        }
    }
    public Razza leggiRazza(String nomer){
        SQLiteDatabase db = dbhelper.getReadableDatabase();
        String whereClause = TabellaRazza.FIELD_NOMER + " = ? ";
        String[] whereArgs = new String[]{nomer};

        try{
            Cursor resultSet = db.query(TabellaRazza.TBL_NOME, null, whereClause, whereArgs, null, null, null);
            if (resultSet == null || resultSet.getCount() == 0){
                return null;
            }
            resultSet.moveToFirst();

            StringBuffer descrizione = new StringBuffer();
            descrizione.append(resultSet.getString(resultSet.getColumnIndex(CampiComuni.FIELD_DESC)));
            String taglia = resultSet.getString(resultSet.getColumnIndex(TabellaRazza.FIELD_TAGLIA));
            String velocita = resultSet.getString(resultSet.getColumnIndex(TabellaRazza.FIELD_VELOCITA));
            StringBuffer lingua = new StringBuffer();
            lingua.append(resultSet.getString(resultSet.getColumnIndex(CampiComuni.FIELD_LINGUA)));
            List<Descrivibile> privilegiRazza = this.leggiPrivilegi(nomer,"razza");
            List<CaratteristicaBase> caratteristicaBaseList = this.leggiCarBase(nomer);

            resultSet.close();
            return new Razza(nomer, descrizione, taglia, velocita, lingua, privilegiRazza, caratteristicaBaseList);
        }
        catch(SQLiteException sqle){
            return null;
        }
    }
    public Equipaggiamento leggiEquipaggiamento(String nomee){
        SQLiteDatabase db = dbhelper.getReadableDatabase();
        String whereClause = TabellaEquipaggiamento.FIELD_NOMEE + " = ? ";
        String[] whereArgs = new String[]{nomee};

        try{
            Cursor resultSet = db.query(TabellaEquipaggiamento.TBL_NOME, null, whereClause, whereArgs, null, null, null);
            if (resultSet == null || resultSet.getCount() == 0){
                return null;
            }
            resultSet.moveToFirst();

            StringBuffer descrizione = new StringBuffer();
            descrizione.append(resultSet.getString(resultSet.getColumnIndex(CampiComuni.FIELD_DESC)));
            String tipo = resultSet.getString(resultSet.getColumnIndex(TabellaEquipaggiamento.FIELD_TIPO));
            int costo = resultSet.getInt(resultSet.getColumnIndex(TabellaEquipaggiamento.FIELD_COSTO));
            int peso = resultSet.getInt(resultSet.getColumnIndex(TabellaEquipaggiamento.FIELD_PESO));
            int capacita = resultSet.getInt(resultSet.getColumnIndex(TabellaEquipaggiamento.FIELD_CAPACITA));

            resultSet.close();
            return new Equipaggiamento(nomee, descrizione, tipo, costo, peso, capacita);
        }
        catch(SQLiteException sqle){
            return null;
        }
    }
    public Arma leggiArma(String nomee){
        SQLiteDatabase db = dbhelper.getReadableDatabase();
        String whereClause = TabellaEquipaggiamento.FIELD_NOMEE + " = ? ";
        String[] whereArgs = new String[]{nomee};

        try{
            Cursor resultSet = db.query(TabellaArma.TBL_NOME, null, whereClause, whereArgs, null, null, null);
            if (resultSet == null || resultSet.getCount() == 0){
                return null;
            }
            resultSet.moveToFirst();

            String danno  = resultSet.getString(resultSet.getColumnIndex(TabellaArma.FIELD_DANNO));
            String proprieta  = resultSet.getString(resultSet.getColumnIndex(TabellaArma.FIELD_PROPRIETA));
            Equipaggiamento equi = this.leggiEquipaggiamento(nomee);
            if(equi == null)
                return null;

            resultSet.close();
            return new Arma(equi.getNome(), equi.getDescrizione(), equi.getTipo(), equi.getCosto(), equi.getCapacita(), equi.getPeso(), danno, proprieta);
        }
        catch(SQLiteException sqle){
            return null;
        }
    }
    public Armatura leggiArmatura(String nomee){
        SQLiteDatabase db = dbhelper.getReadableDatabase();
        String whereClause = TabellaEquipaggiamento.FIELD_NOMEE + " = ? ";
        String[] whereArgs = new String[]{nomee};

        try{
            Cursor resultSet = db.query(TabellaArmatura.TBL_NOME, null, whereClause, whereArgs, null, null, null);
            if (resultSet == null || resultSet.getCount() == 0){
                return null;
            }
            resultSet.moveToFirst();

            boolean nonFurtiva  = resultSet.getInt(resultSet.getColumnIndex(TabellaArmatura.FIELD_NONFURTIVA)) == 1;
            int modificatoreCA = resultSet.getInt(resultSet.getColumnIndex(TabellaArmatura.FIELD_MODIFICATORECA));
            String tempoTogliere = resultSet.getString(resultSet.getColumnIndex(TabellaArmatura.FIELD_TEMPOTOGLIERE));
            String tempoIndossare = resultSet.getString(resultSet.getColumnIndex(TabellaArmatura.FIELD_TEMPOINDOSSARE));
            String forzaNecessaria = resultSet.getString(resultSet.getColumnIndex(TabellaArmatura.FIELD_FORZANECESSARIA));
            Equipaggiamento equi = this.leggiEquipaggiamento(nomee);
            if(equi == null)
                return null;

            resultSet.close();
            return new Armatura(equi.getNome(), equi.getDescrizione(), equi.getTipo(), equi.getCosto(), equi.getCapacita(), equi.getPeso(), nonFurtiva, modificatoreCA, tempoTogliere, tempoIndossare, forzaNecessaria);
        }
        catch(SQLiteException sqle){
            return null;
        }
    }
    public List<Equipaggiamento> leggiEquipaggiamenti(boolean borsa, @NotNull String... arg){
        SQLiteDatabase db = dbhelper.getReadableDatabase();
        String table;
        String whereClause;
        String[] whereArgs;
        switch (arg.length){
            case 1:
                table = TabelleHA.TBL_HACE;
                whereClause = TabellaClasse.FIELD_NOMECLA + "=?";
                whereArgs = new String[]{arg[0]};
                break;
            case 2:
                int b = 0;
                if(borsa)
                    b = 1;
                table = TabelleHA.TBL_HAGE;
                whereClause = TabellaGiocatore.FIELD_NOMECAMPAGNA + "=?" + " AND " + TabellaGiocatore.FIELD_NOMEG + "=?" + " AND " + TabelleHA.FIELD_BORSA + "=?";
                whereArgs = new String[]{arg[0],arg[1], Integer.toString(b)};
                break;
            default:
                return null;
        }

        try {
            Cursor resultSet = db.query(table, null, whereClause, whereArgs, null, null, null);
            if (resultSet == null || resultSet.getCount() == 0){
                return null;
            }
            resultSet.moveToFirst();

            List<Equipaggiamento> equipaggiamentoList = new ArrayList<Equipaggiamento>();
            while (!resultSet.isAfterLast()) {
                Arma arma = this.leggiArma(resultSet.getString(resultSet.getColumnIndex(TabellaEquipaggiamento.FIELD_NOMEE)));

                if (arma != null)
                    equipaggiamentoList.add(arma);
                else {
                    Armatura armatura = this.leggiArmatura(resultSet.getString(resultSet.getColumnIndex(TabellaEquipaggiamento.FIELD_NOMEE)));
                    if (armatura != null)
                        equipaggiamentoList.add(armatura);
                    else {
                        Equipaggiamento equi = this.leggiEquipaggiamento(resultSet.getString(resultSet.getColumnIndex(TabellaEquipaggiamento.FIELD_NOMEE)));
                        if (equi != null)
                            equipaggiamentoList.add(equi);
                    }
                }

                resultSet.moveToNext();
            }

            resultSet.close();
            return equipaggiamentoList;
        }
        catch (SQLiteException sqle) {
            return null;
        }
    }
    public Classe leggiClasse(String nomecla){
        SQLiteDatabase db = dbhelper.getReadableDatabase();
        String whereClause = TabellaClasse.FIELD_NOMECLA + " = ? ";
        String[] whereArgs = new String[]{nomecla};

        try{
            Cursor resultSet = db.query(TabellaClasse.TBL_NOME, null, whereClause, whereArgs, null, null, null);
            if (resultSet == null || resultSet.getCount() == 0){
                return null;
            }
            resultSet.moveToFirst();

            StringBuffer descrizione = new StringBuffer();
            descrizione.append(resultSet.getString(resultSet.getColumnIndex(CampiComuni.FIELD_DESC)));
            int nDadi = resultSet.getInt(resultSet.getColumnIndex(CampiComuni.FIELD_NDADI));
            int dado = resultSet.getInt(resultSet.getColumnIndex(CampiComuni.FIELD_DADO));
            StringBuffer descrizionePrivilegiPoteri = new StringBuffer();
            descrizione.append(resultSet.getString(resultSet.getColumnIndex(TabellaClasse.FIELD_DESCPRIVILEGI)));
            StringBuffer competenza = new StringBuffer();
            descrizione.append(resultSet.getString(resultSet.getColumnIndex(CampiComuni.FIELD_COMPETENZA)));
            List<Equipaggiamento> equipaggiamentoList = this.leggiEquipaggiamenti(true, nomecla);
            List<Descrivibile> privilegiClasse = this.leggiPrivilegi(nomecla,"classe");
            List<Incantesimo> incantesimiClasse = this.leggiIncantesimi(nomecla);

            resultSet.close();
            return new Classe(nomecla, descrizione, descrizionePrivilegiPoteri, nDadi, dado, competenza, equipaggiamentoList, privilegiClasse, incantesimiClasse);
        }
        catch(SQLiteException sqle){
            return null;
        }
    }
    public Caratteristica leggiCaratteristica(String nomecar){
        SQLiteDatabase db = dbhelper.getReadableDatabase();
        String whereClause = TabellaCaratteristica.FIELD_NOMECAR + " = ? ";
        String[] whereArgs = new String[]{nomecar};


        try{
            Cursor resultSet = db.query(TabellaCaratteristica.TBL_NOME, null, whereClause, whereArgs, null, null, null);
            if (resultSet == null || resultSet.getCount() == 0){
                return null;
            }
            resultSet.moveToFirst();

            StringBuffer descrizione = new StringBuffer();
            descrizione.append(resultSet.getString(resultSet.getColumnIndex(CampiComuni.FIELD_DESC)));

            resultSet.close();
            return new Caratteristica(nomecar,descrizione);
        }
        catch (SQLiteException sqle) {
            return null;
        }
    }
    public List<Caratteristica> leggiCaratteristicheG(String nomecamp, String nomeg){
        SQLiteDatabase db = dbhelper.getReadableDatabase();
        String whereClause = TabellaGiocatore.FIELD_NOMECAMPAGNA + "=?" + " AND " + TabellaGiocatore.FIELD_NOMEG + "=?";
        String[] whereArgs = new String[]{nomecamp, nomeg};

        try {
            Cursor resultSet = db.query(TabellaCaratteristicaG.TBL_NOME, null, whereClause, whereArgs, null, null, null);
            if (resultSet == null || resultSet.getCount() == 0){
                return null;
            }
            resultSet.moveToFirst();

            List<Caratteristica> caratteristicaList = new ArrayList<Caratteristica>();
            while (!resultSet.isAfterLast()) {
                Caratteristica caratteristica = leggiCaratteristica(resultSet.getString(resultSet.getColumnIndex(TabellaCaratteristica.FIELD_NOMECAR)));
                if (caratteristica != null){
                    caratteristica.setTiroSalveza(resultSet.getInt(resultSet.getColumnIndex(TabellaCaratteristicaG.FIELD_TIROSALVEZZA)) == 1);
                    caratteristica.setValoreBase(resultSet.getInt(resultSet.getColumnIndex(TabellaCaratteristicaG.FIELD_VALOREBASE)));
                    caratteristica.setValoreLivello(resultSet.getColumnIndex(TabellaCaratteristicaG.FIELD_VALORELIVELLO));
                    caratteristica.setValoreEquipaggiamento(resultSet.getColumnIndex(TabellaCaratteristicaG.FIELD_VALOREEQUIPAGGAMENTO));
                    caratteristica.setValoreBonus(resultSet.getColumnIndex(TabellaCaratteristicaG.FIELD_VALOREBONUS));
                    caratteristica.setModificatore();
                    caratteristicaList.add(caratteristica);
                }

                resultSet.moveToNext();
            }

            resultSet.close();
            return caratteristicaList;
        }
        catch (SQLiteException sqle) {
            return null;
        }
    }
    public Giocatore leggiGiocatore(String nomecamp, String nomeg){
        SQLiteDatabase db = dbhelper.getReadableDatabase();
        String whereClause = TabellaGiocatore.FIELD_NOMECAMPAGNA + "=?" + " AND " + TabellaGiocatore.FIELD_NOMEG + "=?";
        String[] whereArgs = new String[]{nomecamp,nomeg};

        try{
            Cursor resultSet = db.query(TabellaGiocatore.TBL_NOME, null, whereClause, whereArgs, null, null, null);
            if (resultSet == null || resultSet.getCount() == 0){
                return null;
            }
            resultSet.moveToFirst();

            StringBuffer descrizione = new StringBuffer();
            descrizione.append(resultSet.getString(resultSet.getColumnIndex(CampiComuni.FIELD_DESC)));
            int mana = resultSet.getInt(resultSet.getColumnIndex(TabellaGiocatore.FIELD_MANA));
            int livello = resultSet.getInt(resultSet.getColumnIndex(CampiComuni.FIELD_LIVELLO));
            int puntiEsperienza = resultSet.getInt(resultSet.getColumnIndex(TabellaGiocatore.FIELD_PUNTIXP));
            int modCompetenza = resultSet.getInt(resultSet.getColumnIndex(TabellaGiocatore.FIELD_MODCOMPETENZA));
            int capacitaBorsa = resultSet.getInt(resultSet.getColumnIndex(TabellaGiocatore.FIELD_CAPACITABORSA));
            int puntiFerita = resultSet.getInt(resultSet.getColumnIndex(TabellaGiocatore.FIELD_PUNTIFERITA));
            int classeArmatura = resultSet.getInt(resultSet.getColumnIndex(TabellaGiocatore.FIELD_CLASSEARMATURA));
            int puntiStat = resultSet.getInt(resultSet.getColumnIndex(TabellaGiocatore.FIELD_PUNTISTAT));
            int nDadi = resultSet.getInt(resultSet.getColumnIndex(CampiComuni.FIELD_NDADI));
            int dado = resultSet.getInt(resultSet.getColumnIndex(CampiComuni.FIELD_DADO));
            String iniziativa = resultSet.getString(resultSet.getColumnIndex(TabellaGiocatore.FIELD_INIZIATIVA));
            String eta = resultSet.getString(resultSet.getColumnIndex(TabellaGiocatore.FIELD_ETA));
            StringBuffer noteAvventura = new StringBuffer();
            noteAvventura.append(resultSet.getString(resultSet.getColumnIndex(TabellaGiocatore.FIELD_NOTEAVVENTURA)));
            StringBuffer allineamento = new StringBuffer();
            allineamento.append(resultSet.getString(resultSet.getColumnIndex(TabellaGiocatore.FIELD_ALLINEAMENTO)));
            StringBuffer lingua = new StringBuffer();
            lingua.append(resultSet.getString(resultSet.getColumnIndex(CampiComuni.FIELD_LINGUA)));
            Valuta portafoglio = this.leggiValuta(resultSet.getString(resultSet.getColumnIndex(TabellaValuta.FIELD_NOMEV)));
            portafoglio.setValore(resultSet.getInt(resultSet.getColumnIndex(TabellaGiocatore.FIELD_VALOREVAL)));
            Classe classe = this.leggiClasse(resultSet.getString(resultSet.getColumnIndex(TabellaClasse.FIELD_NOMECLA)));
            Razza razza = this.leggiRazza(resultSet.getString(resultSet.getColumnIndex(TabellaRazza.FIELD_NOMER)));
            List<Caratteristica> caratteristicaList = this.leggiCaratteristicheG(nomecamp, nomeg);
            List<Equipaggiamento> borsa = this.leggiEquipaggiamenti(true, nomecamp, nomeg);
            List<Equipaggiamento> equipaggiato = this.leggiEquipaggiamenti(false, nomecamp, nomeg);
            List<Incantesimo> incantesimiGiocatore = this.leggiIncantesimi(nomecamp, nomeg);
            List<Abilita> abilitaList = this.leggiAbilita(nomecamp, nomeg);

            resultSet.close();
            return new Giocatore(nomeg, descrizione, mana, livello, puntiEsperienza, modCompetenza, capacitaBorsa, puntiFerita, nDadi, dado, classeArmatura, puntiStat, nomecamp, iniziativa, eta, noteAvventura, allineamento, lingua, portafoglio, classe, razza, caratteristicaList, borsa, equipaggiato, incantesimiGiocatore, abilitaList);
        }
        catch(SQLiteException sqle){
            return null;
        }
    }
}
