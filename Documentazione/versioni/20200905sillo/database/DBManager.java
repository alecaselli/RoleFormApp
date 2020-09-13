package com.example.myfirstapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

public class DBManager {
    private DBHelper dbhelper;

    public DBManager(Context ctx) {
        dbhelper=new DBHelper(ctx);
    }

    /* UPDATE */
    public void salvaIncantesimi(String nomei, StringBuffer desc, String tempoInvocazione, String raggioAzione, String componenti, String durata, int livello){
        SQLiteDatabase db=dbhelper.getWritableDatabase();
        ContentValues cv=new ContentValues();

        cv.put(DatabaseStrings.FIELD_NOMEI, nomei);
        cv.put(DatabaseStrings.FIELD_DESC, desc.toString());
        cv.put(DatabaseStrings.FIELD_LIVELLO,livello);
        cv.put(DatabaseStrings.FIELD_TEMPOINVOCAZIONE,tempoInvocazione);
        cv.put(DatabaseStrings.FIELD_RAGGIOAZIONE,raggioAzione);
        cv.put(DatabaseStrings.FIELD_COMPONENTI,componenti);
        cv.put(DatabaseStrings.FIELD_DURATA,durata);

        try{
            db.insert(DatabaseStrings.TBL_NOMEI,null,cv);
        }
        catch(SQLiteException sqle){
            System.out.println("fallito salvataggio incantesimi, riprova");
        }
    }
    public void salvaAbilita(boolean competenza, String nomea, String desca){
        SQLiteDatabase db=dbhelper.getWritableDatabase();
        ContentValues cv=new ContentValues();

        cv.put(DatabaseStrings.FIELD_COMPETENZA,competenza);
        cv.put(DatabaseStrings.FIELD_NOMEA,nomea);
        cv.put(DatabaseStrings.FIELD_DESC,desca);

        try{
            db.insert(DatabaseStrings.TBL_NOMEA,null,cv);
        }
        catch(SQLiteException sqle){
            System.out.println("fallito salvataggio abilita, riprova");
        }
    }
    public void salvaValuta(int ratio){
        SQLiteDatabase db=dbhelper.getWritableDatabase();
        ContentValues cv=new ContentValues();

        cv.put(DatabaseStrings.FIELD_RATIO,ratio);

        try{
            db.insert(DatabaseStrings.TBL_NOMEV,null,cv);
        }
        catch(SQLiteException sqle){
            System.out.println("fallito salvataggio valuta, riprova");
        }
    }
    public void salvaRazza(String nomer, StringBuffer desc, String taglia, String velocita, StringBuffer lingua){
        SQLiteDatabase db=dbhelper.getWritableDatabase();
        ContentValues cv=new ContentValues();

        cv.put(DatabaseStrings.FIELD_NOMER,nomer);
        cv.put(DatabaseStrings.FIELD_DESC, desc.toString());
        cv.put(DatabaseStrings.FIELD_TAGLIA,taglia);
        cv.put(DatabaseStrings.FIELD_VELOCITA,velocita);
        cv.put(DatabaseStrings.FIELD_LINGUA,lingua.toString());

        try{
            db.insert(DatabaseStrings.TBL_NOMER,null,cv);
        }
        catch(SQLiteException sqle){
            System.out.println("fallito salvataggio razza, riprova");
        }
    }
    public void salvaPrivivlegi(String nomep, StringBuffer desc){
        SQLiteDatabase db=dbhelper.getWritableDatabase();
        ContentValues cv=new ContentValues();

        cv.put(DatabaseStrings.FIELD_NOMEP,nomep);
        cv.put(DatabaseStrings.FIELD_DESC,desc.toString());
        try{
            db.insert(DatabaseStrings.TBL_NOMEP,null,cv);
        }
        catch(SQLiteException sqle){
            System.out.println("fallito salvataggio , riprova");
        }
    }
    public void salvaClasse(String nomecla, StringBuffer desc, StringBuffer descPrivilegi, int nDadicla, int dadocla, StringBuffer competenzacla){
        SQLiteDatabase db=dbhelper.getWritableDatabase();
        ContentValues cv=new ContentValues();

        cv.put(DatabaseStrings.FIELD_NOMECLA,nomecla);
        cv.put(DatabaseStrings.FIELD_DESC,desc.toString());
        cv.put(DatabaseStrings.FIELD_NDADI,nDadicla);
        cv.put(DatabaseStrings.FIELD_DADO,dadocla);
        cv.put(DatabaseStrings.FIELD_DESCPRIVILEGI,descPrivilegi.toString());
        cv.put(DatabaseStrings.FIELD_COMPETENZA,competenzacla.toString());

        try{
            db.insert(DatabaseStrings.TBL_NOMECLA,null,cv);
        }
        catch(SQLiteException sqle){
            System.out.println("fallito salvataggio classe, riprova");
        }
    }
    public void salvaEquipaggiamento(String nomeo, StringBuffer desc, String tipo, int costo, int peso, int capacita){
        SQLiteDatabase db=dbhelper.getWritableDatabase();
        ContentValues cv=new ContentValues();

        cv.put(DatabaseStrings.FIELD_NOMEO,nomeo);
        cv.put(DatabaseStrings.FIELD_DESC,desc.toString());
        cv.put(DatabaseStrings.FIELD_COSTO,costo);
        cv.put(DatabaseStrings.FIELD_PESO,peso);
        cv.put(DatabaseStrings.FIELD_CAPACITA,capacita);
        cv.put(DatabaseStrings.FIELD_TIPO,tipo);

        try{
            db.insert(DatabaseStrings.TBL_NOMEO,null,cv);
        }
        catch(SQLiteException sqle){
            System.out.println("fallito salvataggio equipaggiamento, riprova");
        }
    }
    public void salvaCaratteristica(String nomecar, StringBuffer desc){
        SQLiteDatabase db=dbhelper.getWritableDatabase();
        ContentValues cv=new ContentValues();

        cv.put(DatabaseStrings.FIELD_NOMECAR,nomecar);
        cv.put(DatabaseStrings.FIELD_DESC,desc.toString());


        try{
            db.insert(DatabaseStrings.TBL_NOMECAR,null,cv);
        }
        catch(SQLiteException sqle){
            System.out.println("fallito salvataggio caratteristca, riprova");
        }
    }
    public void salvaCarBase(String nomer, String nomecb, int valore){
        SQLiteDatabase db=dbhelper.getWritableDatabase();
        ContentValues cv=new ContentValues();

        cv.put(DatabaseStrings.FIELD_NOMER,nomer);
        cv.put(DatabaseStrings.FIELD_NOMECB,nomecb);
        cv.put(DatabaseStrings.FIELD_VALORE,valore);

        try{
            db.insert(DatabaseStrings.TBL_NOMECB,null,cv);
        }
        catch(SQLiteException sqle){
            System.out.println("fallito salvataggio carBase, riprova");
        }
    }
    public void salvaNomeVal(int idval, String nome){
        SQLiteDatabase db=dbhelper.getWritableDatabase();
        ContentValues cv=new ContentValues();

        cv.put(DatabaseStrings.FIELD_IDVAL,idval);
        cv.put(DatabaseStrings.FIELD_NOMEVAL, nome);

        try{
            db.insert(DatabaseStrings.TBL_NOMEVAL,null,cv);
        }
        catch(SQLiteException sqle){
            System.out.println("fallito salvataggio nomeVal, riprova");
        }
    }
    public void salvaArma(String nomeo, String danno, String proprieta){
        SQLiteDatabase db=dbhelper.getWritableDatabase();
        ContentValues cv=new ContentValues();

        cv.put(DatabaseStrings.FIELD_NOMEO,nomeo);
        cv.put(DatabaseStrings.FIELD_DANNO,danno);
        cv.put(DatabaseStrings.FIELD_PROPRIETA,proprieta);

        try{
            db.insert(DatabaseStrings.TBL_ARMA,null,cv);
        }
        catch(SQLiteException sqle){
            System.out.println("fallito salvataggio arma, riprova");
        }
    }
    public void salvaArmatura(String nomeo, boolean nonFurtiva, int modificatoreCa, String tempoTogliere, String tempoIndossare, String forzaNecessaria){
        SQLiteDatabase db=dbhelper.getWritableDatabase();
        ContentValues cv=new ContentValues();

        cv.put(DatabaseStrings.FIELD_NOMEO,nomeo);
        cv.put(DatabaseStrings.FIELD_NONFURTIVA,nonFurtiva);
        cv.put(DatabaseStrings.FIELD_MODIFICATORECA,modificatoreCa);
        cv.put(DatabaseStrings.FIELD_TEMPOINDOSSARE,tempoIndossare);
        cv.put(DatabaseStrings.FIELD_TEMPOTOGLIERE,tempoTogliere);
        cv.put(DatabaseStrings.FIELD_FORZANECESSARIA,forzaNecessaria);

        try{
            db.insert(DatabaseStrings.TBL_ARMATURA,null,cv);
        }
        catch(SQLiteException sqle){
            System.out.println("fallito salvataggio armatura, riprova");
        }
    }
    public void salvaGiocatore(String nomeg, StringBuffer desc, int mana, int livello, int puntiXP, int modCompetenza, int capacitaBorsa, int puntiFerita, int puntiFeritaMax, int classeArmatura, int puntiStat, int nDadi, int dado, String eta, String iniziativa, StringBuffer noteAvventura, StringBuffer allineamento, StringBuffer lingua, String nomecla, String nomer, int idval, int valoreVal){
        SQLiteDatabase db=dbhelper.getWritableDatabase();
        ContentValues cv=new ContentValues();

        cv.put(DatabaseStrings.FIELD_NOMEG,nomeg);
        cv.put(DatabaseStrings.FIELD_DESC,desc.toString());
        cv.put(DatabaseStrings.FIELD_MANA,mana);
        cv.put(DatabaseStrings.FIELD_LIVELLO,livello);
        cv.put(DatabaseStrings.FIELD_PUNTIXP,puntiXP);
        cv.put(DatabaseStrings.FIELD_MODCOMPETENZA,modCompetenza);
        cv.put(DatabaseStrings.FIELD_CAPACITABORSA,capacitaBorsa);
        cv.put(DatabaseStrings.FIELD_PUNTIFERITA,puntiFerita);
        cv.put(DatabaseStrings.FIELD_PUNTIFERITAMAX,puntiFeritaMax);
        cv.put(DatabaseStrings.FIELD_CLASSEARMATURA,classeArmatura);
        cv.put(DatabaseStrings.FIELD_PUNTISTAT,puntiStat);
        cv.put(DatabaseStrings.FIELD_NDADI,nDadi);
        cv.put(DatabaseStrings.FIELD_DADO,dado);
        cv.put(DatabaseStrings.FIELD_INIZIATIVA,iniziativa);
        cv.put(DatabaseStrings.FIELD_ETA,eta);
        cv.put(DatabaseStrings.FIELD_NOTEAVVENTURA,noteAvventura.toString());
        cv.put(DatabaseStrings.FIELD_ALLINEAMENTO,allineamento.toString());
        cv.put(DatabaseStrings.FIELD_LINGUA,lingua.toString());
        cv.put(DatabaseStrings.FIELD_NOMECLA,nomecla);
        cv.put(DatabaseStrings.FIELD_NOMER,nomer);
        cv.put(DatabaseStrings.FIELD_IDVAL,idval);
        cv.put(DatabaseStrings.FIELD_VALOREVAL,valoreVal);


        try{
            db.insert(DatabaseStrings.TBL_NOMEG,null,cv);
        }
        catch(SQLiteException sqle){
            System.out.println("fallito salvataggio giocatore, riprova");
        }
    }
    public void salvaCaratteristicaG(int id_g, String nomecar, boolean tiroSalvezza, int valoreBase, int valoreLivello, int valoreEquipaggiamento, int valoreBonus){
        SQLiteDatabase db=dbhelper.getWritableDatabase();
        ContentValues cv=new ContentValues();

        cv.put(DatabaseStrings.FIELD_ID_G,id_g);
        cv.put(DatabaseStrings.FIELD_NOMECAR,nomecar);
        cv.put(DatabaseStrings.FIELD_TIROSALVEZZA,tiroSalvezza);
        cv.put(DatabaseStrings.FIELD_VALOREBASE,valoreBase);
        cv.put(DatabaseStrings.FIELD_VALORELIVELLO,valoreLivello);
        cv.put(DatabaseStrings.FIELD_VALOREEQUIPAGGAMENTO,valoreEquipaggiamento);
        cv.put(DatabaseStrings.FIELD_VALOREBONUS,valoreBonus);

        try{
            db.insert(DatabaseStrings.TBL_NOMECARG,null,cv);
        }
        catch(SQLiteException sqle){
            System.out.println("fallito salvataggio caratteristicaG, riprova");
        }
    }
    public void salvaHaga(int id_g, String nomea){
        SQLiteDatabase db=dbhelper.getWritableDatabase();
        ContentValues cv=new ContentValues();

        cv.put(DatabaseStrings.FIELD_ID_G,id_g);
        cv.put(DatabaseStrings.FIELD_NOMEO,nomea);

        try{
            db.insert(DatabaseStrings.TBL_HAGA,null,cv);
        }
        catch(SQLiteException sqle){
            System.out.println("fallito salvataggio haga, riprova");
        }
    }
    public void salvaHage(int id_g, String nomeo, boolean borsa){
        SQLiteDatabase db=dbhelper.getWritableDatabase();
        ContentValues cv=new ContentValues();

        cv.put(DatabaseStrings.FIELD_ID_G,id_g);
        cv.put(DatabaseStrings.FIELD_NOMEO,nomeo);
        cv.put(DatabaseStrings.FIELD_BORSA,borsa);

        try{
            db.insert(DatabaseStrings.TBL_HAGE,null,cv);
        }
        catch(SQLiteException sqle){
            System.out.println("fallito salvataggio hage, riprova");
        }
    }
    public void salvaHagi(int id_g, String nomei){
        SQLiteDatabase db=dbhelper.getWritableDatabase();
        ContentValues cv=new ContentValues();

        cv.put(DatabaseStrings.FIELD_ID_G,id_g);
        cv.put(DatabaseStrings.FIELD_NOMEO,nomei);

        try{
            db.insert(DatabaseStrings.TBL_HAGI,null,cv);
        }
        catch(SQLiteException sqle){
            System.out.println("fallito salvataggio hagi, riprova");
        }
    }
    public void salvaHace(String nomecla, String nomeo){
        SQLiteDatabase db=dbhelper.getWritableDatabase();
        ContentValues cv=new ContentValues();

        cv.put(DatabaseStrings.FIELD_NOMEO,nomecla);
        cv.put(DatabaseStrings.FIELD_NOMEO,nomeo);

        try{
            db.insert(DatabaseStrings.TBL_HACE,null,cv);
        }
        catch(SQLiteException sqle){
            System.out.println("fallito salvataggio , riprova");
        }
    }
    public void salvaHaci(String nomecla, String nomei){
        SQLiteDatabase db=dbhelper.getWritableDatabase();
        ContentValues cv=new ContentValues();

        cv.put(DatabaseStrings.FIELD_NOMEO,nomecla);
        cv.put(DatabaseStrings.FIELD_NOMEO,nomei);

        try{
            db.insert(DatabaseStrings.TBL_HACI,null,cv);
        }
        catch(SQLiteException sqle){
            System.out.println("fallito salvataggio haci, riprova");
        }
    }
    public void salvaHacp(String nomecla, String nomep){
        SQLiteDatabase db=dbhelper.getWritableDatabase();
        ContentValues cv=new ContentValues();

        cv.put(DatabaseStrings.FIELD_NOMEO,nomecla);
        cv.put(DatabaseStrings.FIELD_NOMEO,nomep);

        try{
            db.insert(DatabaseStrings.TBL_HACP,null,cv);
        }
        catch(SQLiteException sqle){
            System.out.println("fallito salvataggio hacp, riprova");
        }
    }
    public void salvaHarp(String nomer, String nomep){
        SQLiteDatabase db=dbhelper.getWritableDatabase();
        ContentValues cv=new ContentValues();

        cv.put(DatabaseStrings.FIELD_NOMEO,nomer);
        cv.put(DatabaseStrings.FIELD_NOMEO,nomep);

        try{
            db.insert(DatabaseStrings.TBL_HARP,null,cv);
        }
        catch(SQLiteException sqle){
            System.out.println("fallito salvataggio harp, riprova");
        }
    }

    /* DELETE */
    public boolean eliminaIncantesimo(String nomei) {
        SQLiteDatabase db = dbhelper.getWritableDatabase();

        try {
            return db.delete(DatabaseStrings.TBL_NOMEI, DatabaseStrings.FIELD_NOMEI + "=?", new String[]{nomei}) > 0;
        } catch (SQLiteException sqle) {
            return false;
        }
    }
    public boolean eliminaAbilita(String nomea) {
        SQLiteDatabase db = dbhelper.getWritableDatabase();

        try {
            return db.delete(DatabaseStrings.TBL_NOMEA, DatabaseStrings.FIELD_NOMEA + "=?", new String[]{nomea}) > 0;
        } catch (SQLiteException sqle) {
            return false;
        }
    }
    public boolean eliminavaluta(int idval) {
        SQLiteDatabase db = dbhelper.getWritableDatabase();

        try {
            return db.delete(DatabaseStrings.TBL_NOMEV, DatabaseStrings.FIELD_IDVAL + "=?", new String[]{Integer.toString(idval)}) > 0;
        } catch (SQLiteException sqle) {
            return false;
        }
    }
    public boolean eliminaRazza(String nomer) {
        SQLiteDatabase db = dbhelper.getWritableDatabase();

        try {
            return db.delete(DatabaseStrings.TBL_NOMER, DatabaseStrings.FIELD_NOMER + "=?", new String[]{nomer}) > 0;
        } catch (SQLiteException sqle) {
            return false;
        }
    }
    public boolean eliminaPrivilegi(String nomep) {
        SQLiteDatabase db = dbhelper.getWritableDatabase();

        try {
            return db.delete(DatabaseStrings.TBL_NOMEP, DatabaseStrings.FIELD_NOMEP + "=?", new String[]{nomep}) > 0;
        } catch (SQLiteException sqle) {
            return false;
        }
    }
    public boolean eliminaClasse(String nomecla) {
        SQLiteDatabase db = dbhelper.getWritableDatabase();

        try {
            return db.delete(DatabaseStrings.TBL_NOMECLA, DatabaseStrings.FIELD_NOMECLA + "=?", new String[]{nomecla}) > 0;
        } catch (SQLiteException sqle) {
            return false;
        }
    }
    public boolean eliminaEquipaggiamento(String nomeo) {
        SQLiteDatabase db = dbhelper.getWritableDatabase();

        try {
            return db.delete(DatabaseStrings.TBL_NOMEO, DatabaseStrings.FIELD_NOMEO + "=?", new String[]{nomeo}) > 0;
        } catch (SQLiteException sqle) {
            return false;
        }
    }
    public boolean eliminaCaratteristica(String nomecar) {
        SQLiteDatabase db = dbhelper.getWritableDatabase();

        try {
            return db.delete(DatabaseStrings.TBL_NOMECAR, DatabaseStrings.FIELD_NOMECAR + "=?", new String[]{nomecar}) > 0;
        } catch (SQLiteException sqle) {
            return false;
        }
    }
    public boolean eliminaCarBase(String nomer, String nomecb) {
        SQLiteDatabase db = dbhelper.getWritableDatabase();

        try {
            return db.delete(DatabaseStrings.TBL_NOMECB, DatabaseStrings.FIELD_NOMER + "=?" + " AND " + DatabaseStrings.FIELD_NOMECB + "=?", new String[]{nomer,nomecb}) > 0;
        } catch (SQLiteException sqle) {
            return false;
        }
    }
    public boolean eliminaNomeVal(int idval, String nome) {
        SQLiteDatabase db = dbhelper.getWritableDatabase();

        try {
            return db.delete(DatabaseStrings.TBL_NOMEVAL, DatabaseStrings.FIELD_IDVAL + "=?" + " AND " + DatabaseStrings.FIELD_NOMEVAL + "=?", new String[]{Integer.toString(idval),nome}) > 0;
        } catch (SQLiteException sqle) {
            return false;
        }
    }
    public boolean eliminaArma(String nomeo) {
        SQLiteDatabase db = dbhelper.getWritableDatabase();

        try {
            return db.delete(DatabaseStrings.TBL_ARMA, DatabaseStrings.FIELD_NOMEO + "=?", new String[]{nomeo}) > 0;
        } catch (SQLiteException sqle) {
            return false;
        }
    }
    public boolean eliminaArmatura(String nomeo) {
        SQLiteDatabase db = dbhelper.getWritableDatabase();

        try {
            return db.delete(DatabaseStrings.TBL_ARMATURA, DatabaseStrings.FIELD_NOMEO + "=?", new String[]{nomeo}) > 0;
        } catch (SQLiteException sqle) {
            return false;
        }
    }
    public boolean eliminaGiocatore(int id_g) {
        SQLiteDatabase db = dbhelper.getWritableDatabase();

        try {
            return db.delete(DatabaseStrings.TBL_NOMEG, DatabaseStrings.FIELD_ID_G + "=?", new String[]{Integer.toString(id_g)}) > 0;
        } catch (SQLiteException sqle) {
            return false;
        }
    }
    public boolean eliminaCaratteristicaG(int id_g,String nomecar) {
        SQLiteDatabase db = dbhelper.getWritableDatabase();

        try {
            return db.delete(DatabaseStrings.TBL_NOMECARG, DatabaseStrings.FIELD_ID_G + "=?" + " AND " + DatabaseStrings.FIELD_NOMECAR + "=?", new String[]{Integer.toString(id_g),nomecar}) > 0;
        } catch (SQLiteException sqle) {
            return false;
        }
    }
    public boolean eliminaHaga(int id_g, String nomea) {
        SQLiteDatabase db = dbhelper.getWritableDatabase();

        try {
            return db.delete(DatabaseStrings.TBL_HAGA, DatabaseStrings.FIELD_ID_G + "=?" + " AND " + DatabaseStrings.FIELD_NOMEA + "=?", new String[]{Integer.toString(id_g),nomea}) > 0;
        } catch (SQLiteException sqle) {
            return false;
        }
    }
    public boolean eliminaHage(int id_g, String nomeo) {
        SQLiteDatabase db = dbhelper.getWritableDatabase();

        try {
            return db.delete(DatabaseStrings.TBL_HAGE, DatabaseStrings.FIELD_ID_G + "=?" + " AND " + DatabaseStrings.FIELD_NOMEO + "=?", new String[]{Integer.toString(id_g),nomeo}) > 0;
        } catch (SQLiteException sqle) {
            return false;
        }
    }
    public boolean eliminaHagi(int id_g, String nomei) {
        SQLiteDatabase db = dbhelper.getWritableDatabase();

        try {
            return db.delete(DatabaseStrings.TBL_HAGI, DatabaseStrings.FIELD_ID_G + "=?" + " AND " + DatabaseStrings.FIELD_NOMEI + "=?", new String[]{Integer.toString(id_g),nomei}) > 0;
        } catch (SQLiteException sqle) {
            return false;
        }
    }
    public boolean eliminaHace(String nomecla, String nomeo) {
        SQLiteDatabase db = dbhelper.getWritableDatabase();

        try {
            return db.delete(DatabaseStrings.TBL_HACE, DatabaseStrings.FIELD_NOMECLA + "=?" + " AND " + DatabaseStrings.FIELD_NOMEO + "=?", new String[]{nomecla,nomeo}) > 0;
        } catch (SQLiteException sqle) {
            return false;
        }
    }
    public boolean eliminaHaci(String nomecla, String nomei) {
        SQLiteDatabase db = dbhelper.getWritableDatabase();

        try {
            return db.delete(DatabaseStrings.TBL_HACI, DatabaseStrings.FIELD_NOMECLA + "=?" + " AND " + DatabaseStrings.FIELD_NOMEI + "=?", new String[]{nomecla,nomei}) > 0;
        } catch (SQLiteException sqle) {
            return false;
        }
    }
    public boolean eliminaHacp(String nomecla, String nomep) {
        SQLiteDatabase db = dbhelper.getWritableDatabase();

        try {
            return db.delete(DatabaseStrings.TBL_HACP, DatabaseStrings.FIELD_NOMECLA + "=?" + " AND " + DatabaseStrings.FIELD_NOMEP + "=?", new String[]{nomecla,nomep}) > 0;
        } catch (SQLiteException sqle) {
            return false;
        }
    }
    public boolean eliminaHarp(String nomer, String nomep) {
        SQLiteDatabase db = dbhelper.getWritableDatabase();

        try {
            return db.delete(DatabaseStrings.TBL_HARP, DatabaseStrings.FIELD_NOMER + "=?" + " AND " + DatabaseStrings.FIELD_NOMEP + "=?", new String[]{nomer,nomep}) > 0;
        } catch (SQLiteException sqle) {
            return false;
        }
    }

    /* READ */

}
