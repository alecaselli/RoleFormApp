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
    public long aggiungiValuta(Valuta nuovo){
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(TabellaValuta.FIELD_NOMEV,nuovo.getRatio());
        cv.put(CampiComuni.FIELD_DESC,nuovo.getDescrizione().toString());
        cv.put(TabellaValuta.FIELD_RATIO,nuovo.getRatio());

        try{
            long idval = db.insert(TabellaValuta.TBL_NOME,null,cv);
            if(idval != -1)
                for(String nome : nuovo.getNomelist())
                    if (!this.aggiungiNomeVal(nuovo.getNome(), nome))
                        System.out.println("errore nell'inserimeno del nome della valuta");
            return idval;
        }
        catch(SQLiteException sqle){
            return -1;
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
            long id = db.insert(TabellaRazza.TBL_NOME,null,cv);
            if(id != -1) {
                for (Descrivibile nuovoP : nuovo.getPrivilegiRazza()) {
                    if (!this.aggiungiHarp(nuovo.getNome(), nuovoP.getNome()))
                        System.out.println("errore nell'inserimeno del privilegio nella razza");
                }
                for (CaratteristicaBase nuovaCB : nuovo.getCaratteristicaBaseList()){
                    if(!this.aggiungiCarBase(nuovo.getNome(),nuovaCB))
                        System.out.println("errore nell'inserimeno del modificatore di caratteristica");
                }

            }
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
            long id = db.insert(TabellaClasse.TBL_NOME,null,cv);
            if(id != -1) {
                for (Descrivibile nuovoP : nuovo.getPrivilegiClasse()) {
                    if (!this.aggiungiHacp(nuovo.getNome(), nuovoP.getNome()))
                        System.out.println("errore nell'inserimeno del privilegio nella classe");
                }
                for (Incantesimo nuovoi : nuovo.getIncantesimiClasse()){
                    if (!this.aggiungiHaci(nuovo.getNome(), nuovoi.getNome()))
                        System.out.println("errore nell'inserimeno dell'incantesimo nella classe");
                }
                for (Equipaggiamento nuovoe : nuovo.getEquipaggiamentoList()){
                    if(!this.aggiungiHace(nuovo.getNome(),nuovoe.getNome()))
                        System.out.println("errore nell'inserimeno dell'oggetto nella classe");
                }
            }
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
            db.insert(TabellaArma.TBL_NOME,null,cv);
            return this.aggiungiEquipaggiamento(nuovo);
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
            db.insert(TabellaArmatura.TBL_NOME,null,cv);
            return this.aggiungiEquipaggiamento(nuovo);
        }
        catch(SQLiteException sqle){
            return false;
        }
    }
    public boolean aggiungiGiocatore(Giocatore nuovo){
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        ContentValues cv = new ContentValues();

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
        cv.put(TabellaValuta.FIELD_IDVAL,nuovo.getPortafoglio().getNome());
        cv.put(TabellaGiocatore.FIELD_VALOREVAL,nuovo.getPortafoglio().getValore());


        try{
            long idg = db.insert(TabellaGiocatore.TBL_NOME,null,cv);
            if(idg != -1){
                for(Caratteristica nuovac : nuovo.getCaratteristicaList()){
                    if(!this.aggiungiCaratteristicaG( idg, nuovac))
                        System.out.println("errore nell'inserimeno della caratteristica");
                }
                for(Equipaggiamento nuovoe : nuovo.getEquipaggiato()){
                    if(!this.aggiungiHage(idg, nuovoe.getNome(),false))
                        System.out.println("errore nell'equipaggiare l'oggetto");
                }
                for(Equipaggiamento nuovoe : nuovo.getBorsa()){
                    if(!this.aggiungiHage(idg, nuovoe.getNome(),true))
                        System.out.println("errore nell'inserimeno del'oggetto nella borsa");
                }
                for(Incantesimo nuovoi : nuovo.getIncantesimiGiocatore()){
                    if(!this.aggiungiHagi(idg, nuovoi.getNome()))
                        System.out.println("errore nell'imparare l'incantesimo");
                }
                for (Abilita nuovaa : nuovo.getAbilitaList()){
                    if(!this.aggiungiHaga(idg, nuovaa.getNome()))
                        System.out.println("errore nell'imparare l'abilità");
                }
            }
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
    public boolean aggiungiCaratteristicaG(long id_g, Caratteristica nuova){
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(TabellaGiocatore.FIELD_ID_G,id_g);
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
    public boolean aggiungiHaga(long id_g, String nomea){
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(TabellaGiocatore.FIELD_ID_G,id_g);
        cv.put(TabellaEquipaggiamento.FIELD_NOMEE,nomea);

        try{
            db.insert(TabelleHA.TBL_HAGA,null,cv);
            return true;
        }
        catch(SQLiteException sqle){
         return false;
        }
    }
    public boolean aggiungiHage(long id_g, String nomee, boolean borsa){
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(TabellaGiocatore.FIELD_ID_G,id_g);
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
    public boolean aggiungiHagi(long id_g, String nomei){
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(TabellaGiocatore.FIELD_ID_G,id_g);
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
        String whereClause = TabellaValuta.FIELD_IDVAL + "=?";
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
    public boolean eliminaGiocatore(int id_g, Giocatore eliminato) {
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        String whereClause = TabellaGiocatore.FIELD_ID_G + "=?";
        String[] whereArgs = new String[]{Integer.toString(id_g)};

        try {
            if (db.delete(TabellaGiocatore.TBL_NOME, whereClause, whereArgs) > 0){
                for(Caratteristica car :eliminato.getCaratteristicaList()){
                    if(!eliminaCaratteristicaG(id_g,car.getNome())){
                        return false;
                    }
                }
                for(Equipaggiamento equi : eliminato.getEquipaggiato()){
                    if(!this.eliminaHage(id_g, equi.getNome()))
                        return false;
                }
                for(Equipaggiamento equi : eliminato.getBorsa()){
                    if(!this.eliminaHage(id_g, equi.getNome()))
                        return false;
                }
                for(Incantesimo inc : eliminato.getIncantesimiGiocatore()){
                    if(!this.eliminaHagi(id_g, inc.getNome()))
                        return false;
                }
                for (Abilita ab : eliminato.getAbilitaList()){
                    if(!this.eliminaHaga(id_g, ab.getNome()))
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
    public boolean eliminaCaratteristicaG(int id_g,String nomecar) {
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        String whereClause =TabellaGiocatore.FIELD_ID_G + "=?" + " AND " + TabellaCaratteristica.FIELD_NOMECAR + "=?";
        String[] whereArgs = new String[]{Integer.toString(id_g),nomecar};

        try {
            return db.delete(TabellaCaratteristicaG.TBL_NOME, whereClause, whereArgs) > 0;
        } catch (SQLiteException sqle) {
            return false;
        }
    }
    public boolean eliminaNomeVal(String nomev, String nome) {
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        String whereClause = TabellaValuta.FIELD_IDVAL + "=?" + " AND " + TabellaNomeVal.FIELD_NOMEVAL + "=?";
        String[] whereArgs = new String[]{nomev,nome};

        try {
            return db.delete(TabellaNomeVal.TBL_NOME, whereClause, whereArgs) > 0;
        } catch (SQLiteException sqle) {
            return false;
        }
    }
    public boolean eliminaHaga(int id_g, String nomea) {
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        String whereClause =TabellaGiocatore.FIELD_ID_G + "=?" + " AND " + TabellaAbilita.FIELD_NOMEA + "=?";
        String[] whereArgs = new String[]{Integer.toString(id_g),nomea};

        try {
            return db.delete(TabelleHA.TBL_HAGA, whereClause, whereArgs) > 0;
        } catch (SQLiteException sqle) {
            return false;
        }
    }
    public boolean eliminaHage(int id_g, String nomee) {
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        String whereClause =TabellaGiocatore.FIELD_ID_G + "=?" + " AND " + TabellaEquipaggiamento.FIELD_NOMEE + "=?";
        String[] whereArgs = new String[]{Integer.toString(id_g),nomee};

        try {
            return db.delete(TabelleHA.TBL_HAGE, whereClause, whereArgs) > 0;
        } catch (SQLiteException sqle) {
            return false;
        }
    }
    public boolean eliminaHagi(int id_g, String nomei) {
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        String whereClause =TabellaGiocatore.FIELD_ID_G + "=?" + " AND " + TabellaIncantesimi.FIELD_NOMEI + "=?";
        String[] whereArgs = new String[]{Integer.toString(id_g),nomei};

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
    public boolean aggiornaIncantesimo(String nomei, StringBuffer desc, String tempoInvocazione, String raggioAzione, String componenti, String durata, int livello){
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        String whereClause = TabellaIncantesimi.FIELD_NOMEI + " = ? ";
        String[] whereArgs = new String[]{nomei};

        cv.put(CampiComuni.FIELD_DESC, desc.toString());
        cv.put(CampiComuni.FIELD_LIVELLO,livello);
        cv.put(TabellaIncantesimi.FIELD_TEMPOINVOCAZIONE,tempoInvocazione);
        cv.put(TabellaIncantesimi.FIELD_RAGGIOAZIONE,raggioAzione);
        cv.put(TabellaIncantesimi.FIELD_COMPONENTI,componenti);
        cv.put(TabellaIncantesimi.FIELD_DURATA,durata);

        try{
            db.update(TabellaIncantesimi.TBL_NOME,cv,whereClause,whereArgs );
            return true;
        }
        catch(SQLiteException sqle){
            return false;
        }
    }
    public boolean aggiornaAbilita(boolean competenza, String nomea, String desca){
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        String whereClause = TabellaAbilita.FIELD_NOMEA + " = ? ";
        String[] whereArgs = new String[]{nomea};

        cv.put(CampiComuni.FIELD_COMPETENZA,competenza);
        cv.put(CampiComuni.FIELD_DESC,desca);

        try{
            db.update(TabellaAbilita.TBL_NOME,cv,whereClause,whereArgs);
            return true;
        }
        catch(SQLiteException sqle){
            return false;
        }
    }
    public boolean aggiornaValuta(int idval,int ratio){
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        String whereClause = TabellaValuta.FIELD_IDVAL + " = ? ";
        String[] whereArgs = new String[]{Integer.toString(idval)};

        cv.put(TabellaValuta.FIELD_RATIO,ratio);

        try{
            db.update(TabellaValuta.TBL_NOME,cv,whereClause,whereArgs);
            return true;
        }
        catch(SQLiteException sqle){
            return false;
        }
    }
    public boolean aggiornaRazza(String nomer, StringBuffer desc, String taglia, String velocita, StringBuffer lingua){
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        String whereClause = TabellaRazza.FIELD_NOMER + " = ? ";
        String[] whereArgs = new String[]{nomer};


        cv.put(CampiComuni.FIELD_DESC, desc.toString());
        cv.put(TabellaRazza.FIELD_TAGLIA,taglia);
        cv.put(TabellaRazza.FIELD_VELOCITA,velocita);
        cv.put(CampiComuni.FIELD_LINGUA,lingua.toString());

        try{
            db.update(TabellaRazza.TBL_NOME,cv,whereClause,whereArgs);
            return true;
        }
        catch(SQLiteException sqle){
            return false;
        }
    }
    public boolean aggiornaPrivivlegi(String nomep, StringBuffer desc){
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        String whereClause = TabellaPrivilegi.FIELD_NOMEP + " = ? ";
        String[] whereArgs = new String[]{nomep};

        cv.put(CampiComuni.FIELD_DESC,desc.toString());
        try{
            db.update(TabellaPrivilegi.TBL_NOME,cv,whereClause,whereArgs);
            return true;
        }
        catch(SQLiteException sqle){
            return false;
        }
    }
    public boolean aggiornaClasse(String nomecla, StringBuffer desc, StringBuffer descPrivilegi, int nDadicla, int dadocla, StringBuffer competenzacla){
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        String whereClause = TabellaClasse.FIELD_NOMECLA + " = ? ";
        String[] whereArgs = new String[]{nomecla};

        cv.put(CampiComuni.FIELD_DESC,desc.toString());
        cv.put(CampiComuni.FIELD_NDADI,nDadicla);
        cv.put(CampiComuni.FIELD_DADO,dadocla);
        cv.put(TabellaClasse.FIELD_DESCPRIVILEGI,descPrivilegi.toString());
        cv.put(CampiComuni.FIELD_COMPETENZA,competenzacla.toString());

        try{
            db.update(TabellaClasse.TBL_NOME,cv,whereClause,whereArgs);
            return true;
        }
        catch(SQLiteException sqle){
            return false;
        }
    }
    public boolean aggiornaEquipaggiamento(String nomee, StringBuffer desc, String tipo, int costo, int peso, int capacita){
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        String whereClause = TabellaEquipaggiamento.FIELD_NOMEE + " = ? ";
        String[] whereArgs = new String[]{nomee};

        cv.put(CampiComuni.FIELD_DESC,desc.toString());
        cv.put(TabellaEquipaggiamento.FIELD_COSTO,costo);
        cv.put(TabellaEquipaggiamento.FIELD_PESO,peso);
        cv.put(TabellaEquipaggiamento.FIELD_CAPACITA,capacita);
        cv.put(TabellaEquipaggiamento.FIELD_TIPO,tipo);

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
    public boolean aggiornaArma(String nomee, String danno, String proprieta){
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        String whereClause = TabellaEquipaggiamento.FIELD_NOMEE + " = ? ";
        String[] whereArgs = new String[]{nomee};

        cv.put(TabellaArma.FIELD_DANNO,danno);
        cv.put(TabellaArma.FIELD_PROPRIETA,proprieta);

        try{
            db.update(TabellaArma.TBL_NOME,cv,whereClause,whereArgs);
            return true;
        }
        catch(SQLiteException sqle){
            return false;
        }
    }
    public boolean aggiornaArmatura(String nomee, boolean nonFurtiva, int modificatoreCa, String tempoTogliere, String tempoIndossare, String forzaNecessaria){
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        String whereClause = TabellaEquipaggiamento.FIELD_NOMEE + " = ? ";
        String[] whereArgs = new String[]{nomee};

        cv.put(TabellaArmatura.FIELD_NONFURTIVA,nonFurtiva);
        cv.put(TabellaArmatura.FIELD_MODIFICATORECA,modificatoreCa);
        cv.put(TabellaArmatura.FIELD_TEMPOINDOSSARE,tempoIndossare);
        cv.put(TabellaArmatura.FIELD_TEMPOTOGLIERE,tempoTogliere);
        cv.put(TabellaArmatura.FIELD_FORZANECESSARIA,forzaNecessaria);

        try{
            db.insert(TabellaArmatura.TBL_NOME,null,cv);
            return true;
        }
        catch(SQLiteException sqle){
            return false;
        }
    }
    public boolean aggiornaGiocatore(int id_g, String nomeg, StringBuffer desc, int mana, int livello, int puntiXP, int modCompetenza, int capacitaBorsa, int puntiFerita, int puntiFeritaMax, int classeArmatura, int puntiStat, int nDadi, int dado, String eta, String iniziativa, StringBuffer noteAvventura, StringBuffer allineamento, StringBuffer lingua, String nomecla, String nomer, int idval, int valoreVal){
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        String whereClause = TabellaGiocatore.FIELD_ID_G + " = ? ";
        String[] whereArgs = new String[]{Integer.toString(id_g)};

        cv.put(TabellaGiocatore.FIELD_NOMEG,nomeg);
        cv.put(CampiComuni.FIELD_DESC,desc.toString());
        cv.put(TabellaGiocatore.FIELD_MANA,mana);
        cv.put(CampiComuni.FIELD_LIVELLO,livello);
        cv.put(TabellaGiocatore.FIELD_PUNTIXP,puntiXP);
        cv.put(TabellaGiocatore.FIELD_MODCOMPETENZA,modCompetenza);
        cv.put(TabellaGiocatore.FIELD_CAPACITABORSA,capacitaBorsa);
        cv.put(TabellaGiocatore.FIELD_PUNTIFERITA,puntiFerita);
        cv.put(TabellaGiocatore.FIELD_PUNTIFERITAMAX,puntiFeritaMax);
        cv.put(TabellaGiocatore.FIELD_CLASSEARMATURA,classeArmatura);
        cv.put(TabellaGiocatore.FIELD_PUNTISTAT,puntiStat);
        cv.put(CampiComuni.FIELD_NDADI,nDadi);
        cv.put(CampiComuni.FIELD_DADO,dado);
        cv.put(TabellaGiocatore.FIELD_INIZIATIVA,iniziativa);
        cv.put(TabellaGiocatore.FIELD_ETA,eta);
        cv.put(TabellaGiocatore.FIELD_NOTEAVVENTURA,noteAvventura.toString());
        cv.put(TabellaGiocatore.FIELD_ALLINEAMENTO,allineamento.toString());
        cv.put(CampiComuni.FIELD_LINGUA,lingua.toString());
        cv.put(TabellaClasse.FIELD_NOMECLA,nomecla);
        cv.put(TabellaRazza.FIELD_NOMER,nomer);
        cv.put(TabellaValuta.FIELD_IDVAL,idval);
        cv.put(TabellaGiocatore.FIELD_VALOREVAL,valoreVal);


        try{
            db.update(TabellaGiocatore.TBL_NOME,cv,whereClause,whereArgs);
            return true;
        }
        catch(SQLiteException sqle){
            return false;
        }
    }
    public boolean aggiornaCarBase(String nomer, String nomecb, int valore){
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        String whereClause = TabellaRazza.FIELD_NOMER + " = ? " + TabellaCarBase.FIELD_NOMECB + " = ? " ;
        String[] whereArgs = new String[]{nomer,nomecb};

        cv.put(TabellaCarBase.FIELD_NOMECB,nomecb);
        cv.put(TabellaCarBase.FIELD_VALORE,valore);

        try{
            db.update(TabellaCarBase.TBL_NOME,cv,whereClause,whereArgs);
            return true;
        }
        catch(SQLiteException sqle){
            return false;
        }
    }
    public boolean aggiornaCaratteristicaG(int id_g, String nomecar, boolean tiroSalvezza, int valoreBase, int valoreLivello, int valoreEquipaggiamento, int valoreBonus){
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        String whereClause = TabellaGiocatore.FIELD_ID_G + " = ? " + TabellaCaratteristica.FIELD_NOMECAR + " = ? " ;
        String[] whereArgs = new String[]{Integer.toString(id_g),nomecar};

        cv.put(TabellaCaratteristicaG.FIELD_TIROSALVEZZA,tiroSalvezza);
        cv.put(TabellaCaratteristicaG.FIELD_VALOREBASE,valoreBase);
        cv.put(TabellaCaratteristicaG.FIELD_VALORELIVELLO,valoreLivello);
        cv.put(TabellaCaratteristicaG.FIELD_VALOREEQUIPAGGAMENTO,valoreEquipaggiamento);
        cv.put(TabellaCaratteristicaG.FIELD_VALOREBONUS,valoreBonus);

        try{
            db.update(TabellaCaratteristicaG.TBL_NOME,cv,whereClause,whereArgs);
            return true;
        }
        catch(SQLiteException sqle){
            return false;
        }
    }
    public boolean aggiornaNomeVal(int idval, String nome){
        return aggiungiNomeVal(idval, nome);
    }
    public boolean aggiornaHaga(int id_g, String nomea){
        return aggiungiHaga(id_g, nomea);
    }
    public boolean aggiornaHage(int id_g, String nomee, boolean borsa){
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        String whereClause = TabellaGiocatore.FIELD_ID_G + " = ? " + TabellaEquipaggiamento.FIELD_NOMEE + " = ? " ;
        String[] whereArgs = new String[]{Integer.toString(id_g),nomee};

        cv.put(TabelleHA.FIELD_BORSA,borsa);

        try{
            db.update(TabelleHA.TBL_HAGE,cv,whereClause,whereArgs);
            return true;
        }
        catch(SQLiteException sqle){
            return false;
        }
    }
    public boolean aggiornaHagi(int id_g, String nomei){
        return aggiungiHagi(id_g, nomei);
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
    public Cursor getIncantesimi(String nomei){
        SQLiteDatabase db=dbhelper.getReadableDatabase();
        String whereClause = TabellaIncantesimi.FIELD_NOMEI + " = ? ";
        String[] whereArgs = new String[]{nomei};

        try {
            Cursor resultSet = db.query(TabellaIncantesimi.TBL_NOME, null, whereClause, whereArgs, null, null, null);
            return resultSet;
        }
        catch (SQLiteException sqle) {
            return null;
        }

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


}
