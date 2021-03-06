package com.example.myfirstapp.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public static  final String DBNAME = "DND";
    public DBHelper(Context context){
        super(context,DBNAME,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        String q = "CREATE TABLE IF NOT EXISTS \"incantesmi\" (\n" +
                "\t\"nomei\"\tTEXT,\n" +
                "\t\"desc\"\tTEXT,\n" +
                "\t\"livello\"\tINTEGER,\n" +
                "\t\"tempoInvocazione\"\tTEXT,\n" +
                "\t\"raggioAzione\"\tTEXT,\n" +
                "\t\"componenti\"\tTEXT,\n" +
                "\t\"durata\"\tTEXT,\n" +
                "\tPRIMARY KEY(\"nomei\")\n" +
                ");";
        db.execSQL(q);

        q = "CREATE TABLE IF NOT EXISTS \"abilita\" (\n" +
                "\t\"competenza\"\tINTEGER,\n" +
                "\t\"nomea\"\tTEXT,\n" +
                "\t\"desc\"\tTEXT,\n" +
                "\tPRIMARY KEY(\"nomea\")\n" +
                ");";
        db.execSQL(q);

        q = "CREATE TABLE IF NOT EXISTS \"valuta\" (\n" +
                "\t\"idval\"\tINTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "\t\"ratio\"\tINTEGER,\n" +
                "\tPRIMARY KEY(\"idval\" )\n" +
                ");";
        db.execSQL(q);

        q = "CREATE TABLE IF NOT EXISTS \"razza\" (\n" +
                "\t\"nomer\"\tTEXT,\n" +
                "\t\"desc\"\tTEXT,\n" +
                "\t\"taglia\"\tTEXT,\n" +
                "\t\"velocita\"\tTEXT,\n" +
                "\t\"lingua\"\tTEXT,\n" +
                "\tPRIMARY KEY(\"nomer\")\n" +
                ");";
        db.execSQL(q);

        q = "CREATE TABLE IF NOT EXISTS \"carBase\" (\n" +
                "\t\"nomer\"\tTEXT,\n" +
                "\t\"nomecb\"\tTEXT,\n" +
                "\t\"valore\"\tINTEGER,\n" +
                "\tFOREIGN KEY(\"nomer\") REFERENCES \"razza\"(\"nomer\") ON UPDATE CASCADE,\n" +
                "\tPRIMARY KEY(\"nomer\",\"nomecb\")\n" +
                ");";
        db.execSQL(q);

        q = "CREATE TABLE IF NOT EXISTS \"nomeVal\" (\n" +
                "\t\"idval\"\tINTEGER,\n" +
                "\t\"nome\"\tTEXT,\n" +
                "\tFOREIGN KEY(\"idval\") REFERENCES \"valuta\"(\"idval\") ON UPDATE CASCADE,\n" +
                "\tCONSTRAINT \"PF_nomeval\" PRIMARY KEY(\"idval\",\"nome\")\n" +
                ");";
        db.execSQL(q);

        q = "CREATE TABLE IF NOT EXISTS \"privilegi\" (\n" +
                "\t\"nomep\"\tTEXT,\n" +
                "\t\"desc\"\tTEXT,\n" +
                "\tPRIMARY KEY(\"nomep\")\n" +
                ");";
        db.execSQL(q);

        q = "CREATE TABLE IF NOT EXISTS \"harp\" (\n" +
                "\t\"nomer\"\tINTEGER,\n" +
                "\t\"nomep\"\tINTEGER,\n" +
                "\tFOREIGN KEY(\"nomer\") REFERENCES \"razza\"(\"nomer\") ON UPDATE CASCADE,\n" +
                "\tFOREIGN KEY(\"nomep\") REFERENCES \"privilegi\"(\"nomep\") ON UPDATE CASCADE,\n" +
                "\tPRIMARY KEY(\"nomer\",\"nomep\")\n" +
                ");";
        db.execSQL(q);

        q = "CREATE TABLE IF NOT EXISTS \"classe\" (\n" +
                "\t\"nomecla\"\tTEXT,\n" +
                "\t\"desc\"\tTEXT,\n" +
                "\t\"ndadi\"\tINTEGER,\n" +
                "\t\"dado\"\tINTEGER,\n" +
                "\t\"descPrivilegi\"\tTEXT,\n" +
                "\t\"competenza\"\tTEXT,\n" +
                "\tPRIMARY KEY(\"nomecla\")\n" +
                ");";
        db.execSQL(q);

        q = "CREATE TABLE IF NOT EXISTS \"hacp\" (\n" +
                "\t\"nomecla\"\tTEXT,\n" +
                "\t\"nomep\"\tTEXT,\n" +
                "\tCONSTRAINT \"FK_classe\" FOREIGN KEY(\"nomecla\") REFERENCES \"classe\"(\"nomecla\") ON UPDATE CASCADE,\n" +
                "\tCONSTRAINT \"FK_privilegi\" FOREIGN KEY(\"nomep\") REFERENCES \"privilegi\"(\"nomep\") ON UPDATE CASCADE,\n" +
                "\tCONSTRAINT \"PK_hacp\" PRIMARY KEY(\"nomep\",\"nomecla\")\n" +
                ");";
        db.execSQL(q);

        q = "CREATE TABLE IF NOT EXISTS \"equipaggiamento\" (\n" +
                "\t\"nomeo\"\tTEXT,\n" +
                "\t\"desc\"\tTEXT,\n" +
                "\t\"costo\"\tINTEGER,\n" +
                "\t\"peso\"\tINTEGER,\n" +
                "\t\"capacita\"\tINTEGER,\n" +
                "\t\"tipo\"\tTEXT,\n" +
                "\tPRIMARY KEY(\"nomeo\")\n" +
                ");";
        db.execSQL(q);

        q ="CREATE TABLE IF NOT EXISTS \"arma\" (\n" +
                "\t\"nomeo\"\tTEXT,\n" +
                "\t\"danno\"\tTEXT,\n" +
                "\t\"proprieta\"\tTEXT,\n" +
                "\tCONSTRAINT \"FK_oggetto\" FOREIGN KEY(\"nomeo\") REFERENCES \"equipaggiamento\"(\"nomeo\") ON UPDATE CASCADE,\n" +
                "\tCONSTRAINT \"PK_arma\" PRIMARY KEY(\"nomeo\")\n" +
                ");";
        db.execSQL(q);

        q = "CREATE TABLE IF NOT EXISTS \"armatura\" (\n" +
                "\t\"nomeo\"\tTEXT,\n" +
                "\t\"nonFurtiva\"\tINTEGER,\n" +
                "\t\"modificatoreCa\"\tINTEGER,\n" +
                "\t\"tempoTogliere\"\tTEXT,\n" +
                "\t\"tempoIndossare\"\tTEXT,\n" +
                "\t\"forzaNecessaria\"\tTEXT,\n" +
                "\tPRIMARY KEY(\"nomeo\"),\n" +
                "\tFOREIGN KEY(\"nomeo\") REFERENCES \"equipaggiamento\"(\"nomeo\") ON UPDATE CASCADE\n" +
                ");";
        db.execSQL(q);

        q = "CREATE TABLE IF NOT EXISTS \"haci\" (\n" +
                "\t\"noecla\"\tTEXT,\n" +
                "\t\"nomei\"\tTEXT,\n" +
                "\tPRIMARY KEY(\"noecla\",\"nomei\"),\n" +
                "\tFOREIGN KEY(\"noecla\") REFERENCES \"classe\"(\"nomecla\") ON UPDATE CASCADE,\n" +
                "\tFOREIGN KEY(\"nomei\") REFERENCES \"incantesmi\"(\"nomei\") ON UPDATE CASCADE\n" +
                ");";
        db.execSQL(q);

        q = "CREATE TABLE IF NOT EXISTS \"caratteristicaG\" (\n" +
                "\t\"Id_G\"\tINTEGER,\n" +
                "\t\"nomecar\"\tTEXT,\n" +
                "\t\"tiroSalvezza\"\tINTEGER,\n" +
                "\t\"valoreBase\"\tINTEGER,\n" +
                "\t\"valoreLivello\"\tINTEGER,\n" +
                "\t\"valoreEquipaggiamento\"\tINTEGER,\n" +
                "\t\"valoreBonus\"\tINTEGER,\n" +
                "\tPRIMARY KEY(\"Id_G\",\"nomecar\"),\n" +
                "\tFOREIGN KEY(\"nomecar\") REFERENCES \"caratteristica\"(\"nomecar\") ON UPDATE CASCADE,\n" +
                "\tFOREIGN KEY(\"Id_G\") REFERENCES \"giocatore\"(\"Id_G\") ON UPDATE CASCADE\n" +
                ");";
        db.execSQL(q);

        q = "CREATE TABLE IF NOT EXISTS \"caratteristica\" (\n" +
                "\t\"nomecar\"\tTEXT,\n" +
                "\t\"desc\"\tTEXT,\n" +
                "\tPRIMARY KEY(\"nomecar\")\n" +
                ");";
        db.execSQL(q);

        q = "CREATE TABLE IF NOT EXISTS \"hage\" (\n" +
                "\t\"Id_G\"\tINTEGER,\n" +
                "\t\"nomeo\"\tTEXT,\n" +
                "\t\"borsa\"\tINTEGER,\n" +
                "\tPRIMARY KEY(\"nomeo\",\"Id_G\"),\n" +
                "\tFOREIGN KEY(\"Id_G\") REFERENCES \"giocatore\"(\"Id_G\") ON UPDATE CASCADE,\n" +
                "\tFOREIGN KEY(\"nomeo\") REFERENCES \"equipaggiamento\"(\"nomeo\") ON UPDATE CASCADE\n" +
                ");";
        db.execSQL(q);

        q = "CREATE TABLE IF NOT EXISTS \"hace\" (\n" +
                "\t\"nomecla\"\tTEXT,\n" +
                "\t\"nomeo\"\tTEXT,\n" +
                "\tPRIMARY KEY(\"nomeo\",\"nomecla\"),\n" +
                "\tFOREIGN KEY(\"nomeo\") REFERENCES \"equipaggiamento\"(\"nomeo\") ON UPDATE CASCADE,\n" +
                "\tFOREIGN KEY(\"nomecla\") REFERENCES \"classe\"(\"nomecla\") ON UPDATE CASCADE\n" +
                ");";
        db.execSQL(q);

        q = "CREATE TABLE IF NOT EXISTS \"hagi\" (\n" +
                "\t\"Id_G\"\tINTEGER,\n" +
                "\t\"nomei\"\tTEXT,\n" +
                "\tPRIMARY KEY(\"Id_G\",\"nomei\"),\n" +
                "\tFOREIGN KEY(\"Id_G\") REFERENCES \"giocatore\"(\"Id_G\") ON UPDATE CASCADE,\n" +
                "\tFOREIGN KEY(\"nomei\") REFERENCES \"incantesmi\"(\"nomei\") ON UPDATE CASCADE\n" +
                ");";
        db.execSQL(q);

        q = "CREATE TABLE IF NOT EXISTS \"giocatore\" (\n" +
                "\t\"Id_G\"\tINTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "\t\"nomeg\"\tTEXT NOT NULL,\n" +
                "\t\"desc\"\tTEXT,\n" +
                "\t\"mana\"\tINTEGER,\n" +
                "\t\"livello\"\tINTEGER,\n" +
                "\t\"puntiXP\"\tINTEGER,\n" +
                "\t\"modCompetenza\"\tINTEGER,\n" +
                "\t\"capacitaBorsa\"\tINTEGER,\n" +
                "\t\"puntiFerita\"\tINTEGER,\n" +
                "\t\"puntiFeritaMax\"\tINTEGER,\n" +
                "\t\"classeArmatura\"\tINTEGER,\n" +
                "\t\"puntiStat\"\tINTEGER,\n" +
                "\t\"nDadi\"\tINTEGER,\n" +
                "\t\"dado\"\tINTEGER,\n" +
                "\t\"iniziativa\"\tTEXT,\n" +
                "\t\"eta\"\tTEXT,\n" +
                "\t\"noteAvventura\"\tTEXT,\n" +
                "\t\"allineamento\"\tTEXT,\n" +
                "\t\"lingua\"\tTEXT,\n" +
                "\t\"nomecla\"\tTEXT NOT NULL,\n" +
                "\t\"nomer\"\tTEXT NOT NULL,\n" +
                "\t\"idval\"\tINTEGER NOT NULL,\n" +
                "\t\"valoreVal\"\tINTEGER,\n" +
                "\tFOREIGN KEY(\"nomecla\") REFERENCES \"classe\"(\"nomecla\") ON UPDATE CASCADE,\n" +
                "\tFOREIGN KEY(\"nomer\") REFERENCES \"razza\"(\"nomer\") ON UPDATE CASCADE,\n" +
                "\tFOREIGN KEY(\"idval\") REFERENCES \"valuta\"(\"idval\") ON UPDATE CASCADE\n" +
                ");";
        db.execSQL(q);

        q = "CREATE TABLE IF NOT EXISTS \"haga\" (\n" +
                "\t\"Id_G\"\tINTEGER,\n" +
                "\t\"nomea\"\tTEXT,\n" +
                "\tPRIMARY KEY(\"nomea\",\"Id_G\"),\n" +
                "\tFOREIGN KEY(\"Id_G\") REFERENCES \"giocatore\"(\"Id_G\") ON UPDATE CASCADE,\n" +
                "\tFOREIGN KEY(\"nomea\") REFERENCES \"abilita\"(\"nomea\") ON UPDATE CASCADE\n" +
                ");";
        db.execSQL(q);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

