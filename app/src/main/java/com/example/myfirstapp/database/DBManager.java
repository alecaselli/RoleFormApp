package com.example.myfirstapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;


import com.example.myfirstapp.domain.*;

import java.util.ArrayList;
import java.util.List;

public class DBManager {
    private DBHelper dbhelper;

    public DBManager(Context ctx) {
        dbhelper=new DBHelper(ctx);
    }

    /* INSERT */
    public boolean aggiungiIncantesimi(Incantesimo nuovo){
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
            db.insert(TabellaIncantesimi.TBL_NOME,null,cv);
            return true;
        }
        catch(SQLiteException sqle){
            return false;
        }
    }
    public boolean aggiungiAbilita(Abilita nuovo){
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(CampiComuni.FIELD_COMPETENZA,nuovo.isCompetenza());
        cv.put(TabellaAbilita.FIELD_NOMEA,nuovo.getNome());
        cv.put(CampiComuni.FIELD_DESC,nuovo.getDescrizione());

        try{
            db.insert(TabellaAbilita.TBL_NOME,null,cv);
            return true;
        }
        catch(SQLiteException sqle){
            return false;
        }
    }
    public boolean aggiungiValuta(Valuta nuovo){
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
    public boolean aggiungiRazza(Razza nuovo){
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
    public boolean aggiungiPrivivlegi(Descrivibile nuovo){
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(TabellaPrivilegi.FIELD_NOMEP,nuovo.getNome());
        cv.put(CampiComuni.FIELD_DESC,nuovo.getDescrizione().toString());

        try{
            db.insert(TabellaPrivilegi.TBL_NOME,null,cv);
            return true;
        }
        catch(SQLiteException sqle){
           return false;
        }
    }
    public boolean aggiungiClasse(Classe nuovo){
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
    public boolean aggiungiEquipaggiamento(Equipaggiamento nuovo){
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(TabellaEquipaggiamento.FIELD_NOMEE,nuovo.getNome());
        cv.put(CampiComuni.FIELD_DESC,nuovo.getDescrizione().toString());
        cv.put(TabellaEquipaggiamento.FIELD_COSTO,nuovo.getCosto());
        cv.put(TabellaEquipaggiamento.FIELD_PESO,nuovo.getPeso());
        cv.put(TabellaEquipaggiamento.FIELD_CAPACITA,nuovo.getCapacita());
        cv.put(TabellaEquipaggiamento.FIELD_TIPO,nuovo.getTipo());

        try{
            db.insert(TabellaEquipaggiamento.TBL_NOME,null,cv);
            return true;
        }
        catch(SQLiteException sqle){
          return false;
        }
    }
    public boolean aggiungiCaratteristica(Caratteristica nuovo){
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(TabellaCaratteristica.FIELD_NOMECAR,nuovo.getNome());
        cv.put(CampiComuni.FIELD_DESC,nuovo.getDescrizione().toString());


        try{
            db.insert(TabellaCaratteristica.TBL_NOME,null,cv);
            return true;
        }
        catch(SQLiteException sqle){
            return false;
        }
    }
    public boolean aggiungiArma(Arma nuovo){
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
    public boolean aggiungiArmatura(Armatura nuovo){
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
    public boolean aggiungiGiocatore(Giocatore nuovo){
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
        cv.put(TabellaGiocatore.FIELD_PUNTIFERITAMAX,nuovo.getPuntiFeritaMax());
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
    public boolean aggiungiCarBase(String nomer, CaratteristicaBase nuovo){
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(TabellaRazza.FIELD_NOMER,nomer);
        cv.put(TabellaCarBase.FIELD_NOMECB,nuovo.getNome());
        cv.put(TabellaCarBase.FIELD_VALORE,nuovo.getValore());

        try{
            db.insert(TabellaCarBase.TBL_NOME,null,cv);
            return true;
        }
        catch(SQLiteException sqle){
            return false;
        }
    }
    public boolean aggiungiCaratteristicaG(String nomecamp, String nomeg, Caratteristica nuova){
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
            db.insert(TabellaCaratteristicaG.TBL_NOME,null,cv);
            return true;
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
            db.insert(TabellaNomeVal.TBL_NOME,null,cv);
            return true;
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
            db.insert(TabelleHA.TBL_HAGA,null,cv);
            return true;
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
        cv.put(TabelleHA.FIELD_BORSA,borsa);

        try{
            db.insert(TabelleHA.TBL_HAGE,null,cv);
            return true;
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
            db.insert(TabelleHA.TBL_HAGI,null,cv);
            return true;
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
            db.insert(TabelleHA.TBL_HACE,null,cv);
            return true;
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
            db.insert(TabelleHA.TBL_HACI,null,cv);
            return true;
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
            db.insert(TabelleHA.TBL_HACP,null,cv);
            return true;
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
            db.insert(TabelleHA.TBL_HARP,null,cv);
            return true;
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
    public boolean eliminavaluta(Valuta eliminato) {
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        String whereClause = TabellaValuta.FIELD_NOMEV + "=?";
        String[] whereArgs =  new String[]{eliminato.getNome()};

        try {
            if(db.delete(TabellaValuta.TBL_NOME, whereClause, whereArgs) > 0)
                for(String nome : eliminato.getNomelist()) {
                    if(!this.eliminaNomeVal(eliminato.getNome(),nome)){
                        return false;
                     }
                }
            else return false;
            return true;
        } catch (SQLiteException sqle) {
            return false;
        }
    }
    public boolean eliminaRazza(Razza eliminato) {
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
            }
            else return false;
            return true;
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
    public boolean eliminaClasse(Classe eliminato) {
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
            }
            else return false;
            return true;
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
    public boolean eliminaCaratteristica(Caratteristica eliminato) {
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        String whereClause = TabellaCaratteristica.FIELD_NOMECAR + "=?";
        String[] whereArgs = new String[]{eliminato.getNome()};

        try {
            return db.delete(TabellaCaratteristica.TBL_NOME, whereClause, whereArgs) > 0;
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
    public boolean eliminaGiocatore(Giocatore eliminato) {
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
            }
            else return false;
            return true;
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
    public boolean aggiornaIncantesimo(Incantesimo aggiornato){
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
            db.update(TabellaIncantesimi.TBL_NOME,cv,whereClause,whereArgs );
            return true;
        }
        catch(SQLiteException sqle){
            return false;
        }
    }
    public boolean aggiornaAbilita(Abilita aggiornato){
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        String whereClause = TabellaAbilita.FIELD_NOMEA + " = ? ";
        String[] whereArgs = new String[]{aggiornato.getNome()};

        cv.put(CampiComuni.FIELD_COMPETENZA,aggiornato.isCompetenza());
        cv.put(CampiComuni.FIELD_DESC,aggiornato.getDescrizione());

        try{
            db.update(TabellaAbilita.TBL_NOME,cv,whereClause,whereArgs);
            return true;
        }
        catch(SQLiteException sqle){
            return false;
        }
    }
    public boolean aggiornaValuta(Valuta aggiornato){
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        String whereClause = TabellaValuta.FIELD_NOMEV + " = ? ";
        String[] whereArgs = new String[]{aggiornato.getNome()};


        cv.put(CampiComuni.FIELD_DESC,aggiornato.getDescrizione().toString());
        cv.put(TabellaValuta.FIELD_RATIO,aggiornato.getRatio());

        try{
            db.update(TabellaValuta.TBL_NOME,cv,whereClause,whereArgs);
            for(String nome : aggiornato.getNomelist()){
                if(!this.aggiornaNomeVal(aggiornato.getNome(),nome))
                    return false;
            }
            return true;
        }
        catch(SQLiteException sqle){
            return false;
        }
    }
    public boolean aggiornaRazza(Razza aggiornato){
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        String whereClause = TabellaRazza.FIELD_NOMER + " = ? ";
        String[] whereArgs = new String[]{aggiornato.getNome()};

        cv.put(CampiComuni.FIELD_DESC, aggiornato.getDescrizione().toString());
        cv.put(TabellaRazza.FIELD_TAGLIA,aggiornato.getTaglia());
        cv.put(TabellaRazza.FIELD_VELOCITA,aggiornato.getVelocita());
        cv.put(CampiComuni.FIELD_LINGUA,aggiornato.getLingua().toString());

        try{
            db.update(TabellaRazza.TBL_NOME,cv,whereClause,whereArgs);
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
        catch(SQLiteException sqle){
            return false;
        }
    }
    public boolean aggiornaPrivivlegi(Descrivibile aggiornato){
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        String whereClause = TabellaPrivilegi.FIELD_NOMEP + " = ? ";
        String[] whereArgs = new String[]{aggiornato.getNome()};

        cv.put(CampiComuni.FIELD_DESC,aggiornato.getDescrizione().toString());

        try{
            db.update(TabellaPrivilegi.TBL_NOME,cv,whereClause,whereArgs);
            return true;
        }
        catch(SQLiteException sqle){
            return false;
        }
    }
    public boolean aggiornaClasse(Classe aggiornato){
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
            db.update(TabellaClasse.TBL_NOME,cv,whereClause,whereArgs);
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
        catch(SQLiteException sqle){
            return false;
        }
    }
    public boolean aggiornaEquipaggiamento(Equipaggiamento aggiornato){
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
            db.update(TabellaEquipaggiamento.TBL_NOME,cv,whereClause,whereArgs);
            return true;
        }
        catch(SQLiteException sqle){
            return false;
        }
    }
    public boolean aggiornaCaratteristica(String nomecar, StringBuffer desc){
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        String whereClause = TabellaCaratteristica.FIELD_NOMECAR + " = ? ";
        String[] whereArgs = new String[]{nomecar};

        cv.put(CampiComuni.FIELD_DESC,desc.toString());

        try{
            db.update(TabellaCaratteristica.TBL_NOME,cv,whereClause,whereArgs);
            return true;
        }
        catch(SQLiteException sqle){
            return false;
        }
    }
    public boolean aggiornaArma(Arma aggiornato){
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        String whereClause = TabellaEquipaggiamento.FIELD_NOMEE + " = ? ";
        String[] whereArgs = new String[]{aggiornato.getNome()};

        cv.put(TabellaArma.FIELD_DANNO,aggiornato.getDanno());
        cv.put(TabellaArma.FIELD_PROPRIETA,aggiornato.getProprieta());

        try{
            db.update(TabellaArma.TBL_NOME,cv,whereClause,whereArgs);
            return this.aggiornaEquipaggiamento(aggiornato);
        }
        catch(SQLiteException sqle){
            return false;
        }
    }
    public boolean aggiornaArmatura(Armatura aggiornato){
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
            db.insert(TabellaArmatura.TBL_NOME,null,cv);
            return this.aggiornaEquipaggiamento(aggiornato);
        }
        catch(SQLiteException sqle){
            return false;
        }
    }
    public boolean aggiornaGiocatore(Giocatore aggiornato){
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
        cv.put(TabellaGiocatore.FIELD_PUNTIFERITAMAX,aggiornato.getPuntiFeritaMax());
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
            db.update(TabellaGiocatore.TBL_NOME,cv,whereClause,whereArgs);
            for(Caratteristica nuovac : aggiornato.getCaratteristicaList()){
                if(!this.aggiungiCaratteristicaG(aggiornato.getNomeCampagna(),aggiornato.getNome(), nuovac))
                    return false;
            }
            for(Equipaggiamento nuovoe : aggiornato.getEquipaggiato()){
                if(!this.aggiornaHage(aggiornato.getNomeCampagna(),aggiornato.getNome(), nuovoe.getNome(),false))
                    return false;
            }
            for(Equipaggiamento nuovoe : aggiornato.getBorsa()){
                if(!this.aggiornaHage(aggiornato.getNomeCampagna(),aggiornato.getNome(), nuovoe.getNome(),true))
                    return false;
            }
            for(Incantesimo nuovoi : aggiornato.getIncantesimiGiocatore()){
                if(!this.aggiornaHagi(aggiornato.getNomeCampagna(),aggiornato.getNome(), nuovoi.getNome()))
                    return false;
            }
            for (Abilita nuovaa : aggiornato.getAbilitaList()){
                if(!this.aggiornaHaga(aggiornato.getNomeCampagna(),aggiornato.getNome(), nuovaa.getNome()))
                    return false;
            }
            return true;
        }
        catch(SQLiteException sqle){
            return false;
        }
    }
    public boolean aggiornaCarBase(String nomer, CaratteristicaBase aggiornato){
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        String whereClause = TabellaRazza.FIELD_NOMER + " = ? " + " AND " + TabellaCarBase.FIELD_NOMECB + " = ? " ;
        String[] whereArgs = new String[]{nomer,aggiornato.getNome()};

        cv.put(TabellaCarBase.FIELD_VALORE,aggiornato.getValore());

        try{
            db.update(TabellaCarBase.TBL_NOME,cv,whereClause,whereArgs);
            return true;
        }
        catch(SQLiteException sqle){
            return false;
        }
    }
    public boolean aggiornaCaratteristicaG(String nomecamp, String nomeg, Caratteristica aggiornato){
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
            db.update(TabellaCaratteristicaG.TBL_NOME,cv,whereClause,whereArgs);
            return true;
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

        cv.put(TabelleHA.FIELD_BORSA,borsa);

        try{
            db.update(TabelleHA.TBL_HAGE,cv,whereClause,whereArgs);
            return true;
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
    public Incantesimo leggiIncantesimi(String nomei){
        SQLiteDatabase db = dbhelper.getReadableDatabase();
        String whereClause = TabellaIncantesimi.FIELD_NOMEI + " = ? ";
        String[] whereArgs = new String[]{nomei};

        try {
            Cursor resultSet = db.query(TabellaIncantesimi.TBL_NOME, null, whereClause, whereArgs, null, null, null);
            if (resultSet == null || resultSet.getCount() == 0){
                return null;
            }
            resultSet.moveToFirst();

            String nome = resultSet.getString(resultSet.getColumnIndex(TabellaIncantesimi.FIELD_NOMEI));
            StringBuffer descrizione = new StringBuffer();
            descrizione.append(resultSet.getString(resultSet.getColumnIndex(CampiComuni.FIELD_DESC)));
            int livello = resultSet.getInt(resultSet.getColumnIndex(CampiComuni.FIELD_LIVELLO));
            String tempoInvocazione = resultSet.getString(resultSet.getColumnIndex(TabellaIncantesimi.FIELD_TEMPOINVOCAZIONE));
            String raggioAzione = resultSet.getString(resultSet.getColumnIndex(TabellaIncantesimi.FIELD_RAGGIOAZIONE));
            String componenti = resultSet.getString(resultSet.getColumnIndex(TabellaIncantesimi.FIELD_COMPONENTI));
            String durata = resultSet.getString(resultSet.getColumnIndex(TabellaIncantesimi.FIELD_DURATA));

            resultSet.close();
            return new Incantesimo(nome,descrizione,tempoInvocazione,raggioAzione,componenti,durata,livello);
        }
        catch (SQLiteException sqle) {
            return null;
        }
    }
    public List<Incantesimo> leggiIncantesimi(String... arg) {
        SQLiteDatabase db = dbhelper.getReadableDatabase();
        String table;
        String whereClause;
        String[] columns =  new String[]{TabellaIncantesimi.FIELD_NOMEI};
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
            Cursor resultSet = db.query(table, columns, whereClause, whereArgs, null, null, null);
            if (resultSet == null || resultSet.getCount() == 0){
                return null;
            }
            List<Incantesimo> incantesimi = new ArrayList<Incantesimo>();

            resultSet.moveToFirst();
            while (!resultSet.isAfterLast()) {
                Incantesimo incantesimo = leggiIncantesimi(resultSet.getString(resultSet.getColumnIndex(TabellaIncantesimi.FIELD_NOMEI)));
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
    /*
    }
    public Cursor getAbilita(String nomea){
        SQLiteDatabase db=dbhelper.getReadableDatabase();
        String whereClause = TabellaAbilita.FIELD_NOMEA + " = ? ";
        String[] whereArgs = new String[]{nomea};

        try {
            Cursor resultSet = db.query(TabellaAbilita.TBL_NOME, null, whereClause, whereArgs, null, null, null);
            return resultSet;
        }
        catch (SQLiteException sqle) {
            return null;
        }
    }
    public Cursor getValuta(int idval,int ratio){
        SQLiteDatabase db=dbhelper.getReadableDatabase();
        String whereClause = TabellaValuta.FIELD_IDVAL + " = ? ";
        String[] columns = new String[]{TabellaValuta.FIELD_RATIO};
        String[] whereArgs = new String[]{Integer.toString(idval)};

        try {
            Cursor resultSet = db.query(TabellaAbilita.TBL_NOME, columns, whereClause, whereArgs, null, null, null);
            return resultSet;
        }
        catch (SQLiteException sqle) {
            return null;
        }
    }
*/

}
