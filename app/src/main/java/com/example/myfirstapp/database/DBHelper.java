package com.example.myfirstapp.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.jetbrains.annotations.NotNull;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DBNAME = "DND";

    public DBHelper(Context context) {
        super(context, DBNAME, null, 1);
    }

    @Override
    public void onCreate(@NonNull SQLiteDatabase db) {
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
                "\t\"nomev\"\tTEXT,\n" +
                "\t\"desc\"\tTEXT,\n" +
                "\t\"ratio\"\tINTEGER,\n" +
                "\tPRIMARY KEY(\"nomev\")\n" +
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

        q = "CREATE TABLE IF NOT EXISTS \"privilegi\" (\n" +
                "\t\"nomep\"\tTEXT,\n" +
                "\t\"desc\"\tTEXT,\n" +
                "\tPRIMARY KEY(\"nomep\")\n" +
                ");";
        db.execSQL(q);

        q = "CREATE TABLE IF NOT EXISTS \"classe\" (\n" +
                "\t\"nomecla\"\tTEXT,\n" +
                "\t\"desc\"\tTEXT,\n" +
                "\t\"nDadi\"\tINTEGER,\n" +
                "\t\"dado\"\tINTEGER,\n" +
                "\t\"descPrivilegi\"\tTEXT,\n" +
                "\t\"competenza\"\tTEXT,\n" +
                "\tPRIMARY KEY(\"nomecla\")\n" +
                ");";
        db.execSQL(q);

        q = "CREATE TABLE IF NOT EXISTS \"equipaggiamento\" (\n" +
                "\t\"nomee\"\tTEXT,\n" +
                "\t\"desc\"\tTEXT,\n" +
                "\t\"costo\"\tINTEGER,\n" +
                "\t\"peso\"\tINTEGER,\n" +
                "\t\"capacita\"\tINTEGER,\n" +
                "\t\"tipo\"\tTEXT,\n" +
                "\tPRIMARY KEY(\"nomee\")\n" +
                ");";
        db.execSQL(q);

        q = "CREATE TABLE IF NOT EXISTS \"caratteristica\" (\n" +
                "\t\"nomecar\"\tTEXT,\n" +
                "\t\"desc\"\tTEXT,\n" +
                "\tPRIMARY KEY(\"nomecar\")\n" +
                ");";
        db.execSQL(q);

        q = "CREATE TABLE IF NOT EXISTS \"arma\" (\n" +
                "\t\"nomee\"\tTEXT,\n" +
                "\t\"danno\"\tTEXT,\n" +
                "\t\"proprieta\"\tTEXT,\n" +
                "\tCONSTRAINT \"FK_oggetto\" FOREIGN KEY(\"nomee\") REFERENCES \"equipaggiamento\"(\"nomee\") ON UPDATE CASCADE,\n" +
                "\tCONSTRAINT \"PK_arma\" PRIMARY KEY(\"nomee\")\n" +
                ");";
        db.execSQL(q);

        q = "CREATE TABLE IF NOT EXISTS \"armatura\" (\n" +
                "\t\"nomee\"\tTEXT,\n" +
                "\t\"nonFurtiva\"\tINTEGER,\n" +
                "\t\"modificatoreCa\"\tINTEGER,\n" +
                "\t\"tempoTogliere\"\tTEXT,\n" +
                "\t\"tempoIndossare\"\tTEXT,\n" +
                "\t\"forzaNecessaria\"\tTEXT,\n" +
                "\tPRIMARY KEY(\"nomee\"),\n" +
                "\tFOREIGN KEY(\"nomee\") REFERENCES \"equipaggiamento\"(\"nomee\") ON UPDATE CASCADE\n" +
                ");";
        db.execSQL(q);

        q = "CREATE TABLE IF NOT EXISTS \"giocatore\" (\n" +
                "\t\"nomeCampagna\"\tTEXT,\n" +
                "\t\"nomeg\"\tTEXT,\n" +
                "\t\"desc\"\tTEXT,\n" +
                "\t\"mana\"\tINTEGER,\n" +
                "\t\"livello\"\tINTEGER,\n" +
                "\t\"puntiXP\"\tINTEGER,\n" +
                "\t\"modCompetenza\"\tINTEGER,\n" +
                "\t\"capacitaBorsa\"\tINTEGER,\n" +
                "\t\"puntiFerita\"\tINTEGER,\n" +
                "\t\"classeArmatura\"\tINTEGER,\n" +
                "\t\"puntiStat\"\tINTEGER,\n" +
                "\t\"nDadi\"\t\tINTEGER,\n" +
                "\t\"dado\"\t\tINTEGER,\n" +
                "\t\"iniziativa\"\tTEXT,\n" +
                "\t\"eta\"\tTEXT,\n" +
                "\t\"altezza\"\tTEXT,\n" +
                "\t\"genere\"\tTEXT,\n" +
                "\t\"noteAvventura\"\tTEXT,\n" +
                "\t\"allineamento\"\tTEXT,\n" +
                "\t\"lingua\"\tTEXT,\n" +
                "\t\"nomecla\"\tTEXT NOT NULL,\n" +
                "\t\"nomer\"\tTEXT NOT NULL,\n" +
                "\t\"nomev\"\tTEXT NOT NULL,\n" +
                "\t\"valoreVal\"\tINTEGER,\n" +
                "\tPRIMARY KEY(\"nomeCampagna\",\"nomeg\"),\n" +
                "\tFOREIGN KEY(\"nomecla\") REFERENCES \"classe\"(\"nomecla\") ON UPDATE CASCADE,\n" +
                "\tFOREIGN KEY(\"nomer\") REFERENCES \"razza\"(\"nomer\") ON UPDATE CASCADE,\n" +
                "\tFOREIGN KEY(\"nomev\") REFERENCES \"valuta\"(\"nomev\") ON UPDATE CASCADE\n" +
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

        q = "CREATE TABLE IF NOT EXISTS \"caratteristicaG\" (\n" +
                "\t\"nomeCampagna\"\tTEXT,\n" +
                "\t\"nomeg\"\tTEXT,\n" +
                "\t\"nomecar\"\tTEXT,\n" +
                "\t\"tiroSalvezza\"\tINTEGER,\n" +
                "\t\"valoreBase\"\tINTEGER,\n" +
                "\t\"valoreLivello\"\tINTEGER,\n" +
                "\t\"valoreEquipaggiamento\"\tINTEGER,\n" +
                "\t\"valoreBonus\"\tINTEGER,\n" +
                "\tPRIMARY KEY(\"nomeCampagna\",\"nomeg\",\"nomecar\"),\n" +
                "\tFOREIGN KEY(\"nomecar\") REFERENCES \"caratteristica\"(\"nomecar\") ON UPDATE CASCADE,\n" +
                "\tFOREIGN KEY(\"nomeCampagna\",\"nomeg\") REFERENCES \"giocatore\"(\"nomeCampagna\",\"nomeg\") ON UPDATE CASCADE\n" +
                ");";
        db.execSQL(q);

        q = "CREATE TABLE IF NOT EXISTS \"nomeVal\" (\n" +
                "\t\"nomev\"\tTEXT,\n" +
                "\t\"nome\"\tTEXT,\n" +
                "\tFOREIGN KEY(\"nomev\") REFERENCES \"valuta\"(\"nomev\") ON UPDATE CASCADE,\n" +
                "\tCONSTRAINT \"PF_nomeval\" PRIMARY KEY(\"nomev\",\"nome\")\n" +
                ");";
        db.execSQL(q);

        q = "CREATE TABLE IF NOT EXISTS \"haga\" (\n" +
                "\t\"nomeCampagna\"\tTEXT,\n" +
                "\t\"nomeg\"\tTEXT,\n" +
                "\t\"nomea\"\tTEXT,\n" +
                "\tPRIMARY KEY(\"nomea\",\"nomeCampagna\",\"nomeg\"),\n" +
                "\tFOREIGN KEY(\"nomeCampagna\",\"nomeg\") REFERENCES \"giocatore\"(\"nomeCampagna\",\"nomeg\") ON UPDATE CASCADE,\n" +
                "\tFOREIGN KEY(\"nomea\") REFERENCES \"abilita\"(\"nomea\") ON UPDATE CASCADE\n" +
                ");";
        db.execSQL(q);

        q = "CREATE TABLE IF NOT EXISTS \"hage\" (\n" +
                "\t\"nomeCampagna\"\tTEXT,\n" +
                "\t\"nomeg\"\tTEXT,\n" +
                "\t\"nomee\"\tTEXT,\n" +
                "\t\"borsa\"\tINTEGER,\n" +
                "\tPRIMARY KEY(\"nomee\",\"nomeCampagna\",\"nomeg\"),\n" +
                "\tFOREIGN KEY(\"nomeCampagna\",\"nomeg\") REFERENCES \"giocatore\"(\"nomeCampagna\",\"nomeg\") ON UPDATE CASCADE,\n" +
                "\tFOREIGN KEY(\"nomee\") REFERENCES \"equipaggiamento\"(\"nomee\") ON UPDATE CASCADE\n" +
                ");";
        db.execSQL(q);

        q = "CREATE TABLE IF NOT EXISTS \"hagi\" (\n" +
                "\t\"nomeCampagna\"\tTEXT,\n" +
                "\t\"nomeg\"\tTEXT,\n" +
                "\t\"nomei\"\tTEXT,\n" +
                "\tPRIMARY KEY(\"nomeCampagna\",\"nomeg\",\"nomei\"),\n" +
                "\tFOREIGN KEY(\"nomeCampagna\",\"nomeg\") REFERENCES \"giocatore\"(\"nomeCampagna\",\"nomeg\") ON UPDATE CASCADE,\n" +
                "\tFOREIGN KEY(\"nomei\") REFERENCES \"incantesmi\"(\"nomei\") ON UPDATE CASCADE\n" +
                ");";
        db.execSQL(q);

        q = "CREATE TABLE IF NOT EXISTS \"hace\" (\n" +
                "\t\"nomecla\"\tTEXT,\n" +
                "\t\"nomee\"\tTEXT,\n" +
                "\tPRIMARY KEY(\"nomee\",\"nomecla\"),\n" +
                "\tFOREIGN KEY(\"nomee\") REFERENCES \"equipaggiamento\"(\"nomee\") ON UPDATE CASCADE,\n" +
                "\tFOREIGN KEY(\"nomecla\") REFERENCES \"classe\"(\"nomecla\") ON UPDATE CASCADE\n" +
                ");";
        db.execSQL(q);

        q = "CREATE TABLE IF NOT EXISTS \"haci\" (\n" +
                "\t\"nomecla\"\tTEXT,\n" +
                "\t\"nomei\"\tTEXT,\n" +
                "\tPRIMARY KEY(\"nomecla\",\"nomei\"),\n" +
                "\tFOREIGN KEY(\"nomecla\") REFERENCES \"classe\"(\"nomecla\") ON UPDATE CASCADE,\n" +
                "\tFOREIGN KEY(\"nomei\") REFERENCES \"incantesmi\"(\"nomei\") ON UPDATE CASCADE\n" +
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

        q = "CREATE TABLE IF NOT EXISTS \"harp\" (\n" +
                "\t\"nomer\"\tINTEGER,\n" +
                "\t\"nomep\"\tINTEGER,\n" +
                "\tFOREIGN KEY(\"nomer\") REFERENCES \"razza\"(\"nomer\") ON UPDATE CASCADE,\n" +
                "\tFOREIGN KEY(\"nomep\") REFERENCES \"privilegi\"(\"nomep\") ON UPDATE CASCADE,\n" +
                "\tPRIMARY KEY(\"nomer\",\"nomep\")\n" +
                ");";
        db.execSQL(q);

        this.primoInserimento(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    private void primoInserimento(@NotNull SQLiteDatabase db) {
        /* incantesimi */
        String q = "INSERT INTO \"incantesmi\" (\"nomei\",\"desc\",\"livello\",\"tempoInvocazione\",\"raggioAzione\",\"componenti\",\"durata\") VALUES ('palla di fuoco','spari una palla di fuoco',1,'istantaneo','5m','nessuno','istantaneo');";
        db.execSQL(q);
        q = "INSERT INTO \"incantesmi\" (\"nomei\",\"desc\",\"livello\",\"tempoInvocazione\",\"raggioAzione\",\"componenti\",\"durata\") VALUES ('lanci di ghiaccio','spari una lancia di ghiaccio',2,'15sec','3m','ghiacciolo','istantaneo');";
        db.execSQL(q);
        q = "INSERT INTO \"incantesmi\" (\"nomei\",\"desc\",\"livello\",\"tempoInvocazione\",\"raggioAzione\",\"componenti\",\"durata\") VALUES ('cura','guarisci te stesso o un tuo alleato di 2 punto ferita ogni 30sec, devi mantenere il contatto',1,'istantaneo','contatto','pergamena del chierico','3min');";
        db.execSQL(q);
        q = "INSERT INTO \"incantesmi\" (\"nomei\",\"desc\",\"livello\",\"tempoInvocazione\",\"raggioAzione\",\"componenti\",\"durata\") VALUES ('evocazione elementare','puoi evocare un pet elemntare 4/8, possiede un proprio turno e può attaccare 2 volte',5,'1min','2m','una lacrima di cervo',NULL);";
        db.execSQL(q);
        q = "INSERT INTO \"incantesmi\" (\"nomei\",\"desc\",\"livello\",\"tempoInvocazione\",\"raggioAzione\",\"componenti\",\"durata\") VALUES ('freccia magica','spari 5 frecce di mana anzichè una normale, ogni freccia fa danno norale  -1',4,'istantaneo','15m','un crisallo di mana','istantaneo');";
        db.execSQL(q);

        /* valuta */
        q = "INSERT INTO \"valuta\" (\"nomev\",\"desc\",\"ratio\") VALUES ('moneta del regno di Ho','la valuta di uso comune nel regno di ho, si suddivide in 5 tagli',10);";
        db.execSQL(q);

        /* razza */
        q = "INSERT INTO \"razza\" (\"nomer\",\"desc\",\"taglia\",\"velocita\",\"lingua\") VALUES ('nano','una delle razze più comuni nel continente, sei riconosciuto da utta la popolazione come menbro della tua razza e quindi non susciti salcopre nelle città.\n" +
                "molti regnanti hanno una buona impressione della tua razza, non hai santaggi commerciali,\n" +
                "nato fabbro sei dotato di una forza straordinaria e nonostante la tua statura bassa e tozza hai una buona manualità','piccola','9m','nanico, umano, runico nanico');";
        db.execSQL(q);
        q = "INSERT INTO \"razza\" (\"nomer\",\"desc\",\"taglia\",\"velocita\",\"lingua\") VALUES ('elfo','una delle razze più comuni nel continente, sei riconosciuto da utta la popolazione come menbro della tua razza e quindi non susciti salcopre nelle città.\n" +
                "molti regnanti hanno una buona impressione della tua razza, non hai santaggi commerciali.\n" +
                "neato direttamente da madre natura hai una grande affinità con essa, ciò ti dona un''alta longevità e una apparenza affascinante, la vita nei boschi ti ha donato grande destrezza e capacità di elusione','media','9m','elfico, umano, runico elfico');";
        db.execSQL(q);

        /* privilegi */
        q = "INSERT INTO \"privilegi\" (\"nomep\",\"desc\") VALUES ('scurovisione','non hai impedimenti nei tiri osservazione al buio');";
        db.execSQL(q);
        q = "INSERT INTO \"privilegi\" (\"nomep\",\"desc\") VALUES ('durascorza','dimezzi i danni da impatto');";
        db.execSQL(q);
        q = "INSERT INTO \"privilegi\" (\"nomep\",\"desc\") VALUES ('meditazione','recuperi punti ferita nella metà del tempo, recupero totale in  4 ore anzichè 8');";
        db.execSQL(q);
        q = "INSERT INTO \"privilegi\" (\"nomep\",\"desc\") VALUES ('agilità superiore','hai un +2 nei tiri destrezza');";
        db.execSQL(q);
        q = "INSERT INTO \"privilegi\" (\"nomep\",\"desc\") VALUES ('instancabile','ti stanchi meno delle razze comuni, ti sazi con mezza razione di cibo');";
        db.execSQL(q);
        q = "INSERT INTO \"privilegi\" (\"nomep\",\"desc\") VALUES ('maetro della guerra','hai un +2 nei tiri attacco');";
        db.execSQL(q);
        q = "INSERT INTO \"privilegi\" (\"nomep\",\"desc\") VALUES ('furia','ogni 2 livelli acquisisci 1 punto furia spendibile in un tiro forza, puoi accumularne al massimo 6');";
        db.execSQL(q);
        q = "INSERT INTO \"privilegi\" (\"nomep\",\"desc\") VALUES ('leader in prima linea','hai un +3 nei tiri iniziativa');";
        db.execSQL(q);
        q = "INSERT INTO \"privilegi\" (\"nomep\",\"desc\") VALUES ('naturalista','la tua affinità con la natura è tale da portarti lla stima del dio Pam +3 nei tiri che riguardano cose naturali');";
        db.execSQL(q);
        q = "INSERT INTO \"privilegi\" (\"nomep\",\"desc\") VALUES ('protezione della foresta','se stai combattendo nella foresta riduci il danno magico di 4');";
        db.execSQL(q);

        /* classe */
        q = "INSERT INTO \"classe\" (\"nomecla\",\"desc\",\"nDadi\",\"dado\",\"descPrivilegi\",\"competenza\") VALUES ('ranger','sei un avamposto naturale per la tua compagnia di avventuri in luogni inoti per coglier informazioni essenziali per la vostra avventura, hai una forte affinità con la natura',1,6,NULL,'armi: leggere; abilità: correre, arrampicarti; tiri salvezza: destrezza, carisma; ');";
        db.execSQL(q);
        q = "INSERT INTO \"classe\" (\"nomecla\",\"desc\",\"nDadi\",\"dado\",\"descPrivilegi\",\"competenza\") VALUES ('gueriero','sei nato per stare in battaglia, sei in prima linea in ogni combattimento. Pronto a proteggere i tuo compagni e attaccare i tuoi avversari',1,8,NULL,'armi:da guerra; abilità: schermire, riparare; tiri salvezza: forza, costituzione');";
        db.execSQL(q);

        /* equipaggiamento */
        q = "INSERT INTO \"equipaggiamento\" (\"nomee\",\"desc\",\"costo\",\"peso\",\"capacita\",\"tipo\") VALUES ('maglia in cuoio',NULL,40,NULL,0,'armatura');";
        db.execSQL(q);
        q = "INSERT INTO \"equipaggiamento\" (\"nomee\",\"desc\",\"costo\",\"peso\",\"capacita\",\"tipo\") VALUES ('mazza',NULL,70,NULL,0,'arma');";
        db.execSQL(q);
        q = "INSERT INTO \"equipaggiamento\" (\"nomee\",\"desc\",\"costo\",\"peso\",\"capacita\",\"tipo\") VALUES ('razione','razione di cibo secco equivalente a un pasto',5,NULL,0,'equipaggiamento da avventura');";
        db.execSQL(q);

        /* caratteristica */
        q = "INSERT INTO \"caratteristica\" (\"nomecar\",\"desc\") VALUES ('forza','la tua forza fisica');";
        db.execSQL(q);
        q = "INSERT INTO \"caratteristica\" (\"nomecar\",\"desc\") VALUES ('destrezza','la capacità di cavartela in ogni circostanza');";
        db.execSQL(q);
        q = "INSERT INTO \"caratteristica\" (\"nomecar\",\"desc\") VALUES ('costituzione','la tua vitalità');";
        db.execSQL(q);
        q = "INSERT INTO \"caratteristica\" (\"nomecar\",\"desc\") VALUES ('intelligenza','la tua mole di conoscenza');";
        db.execSQL(q);
        q = "INSERT INTO \"caratteristica\" (\"nomecar\",\"desc\") VALUES ('saggezza','iil tuo grado di discernimento della natura intrinseca delle cose');";
        db.execSQL(q);
        q = "INSERT INTO \"caratteristica\" (\"nomecar\",\"desc\") VALUES ('carisma','capacità nell''affascinare gli altri');";
        db.execSQL(q);

        /* arma/armatura */
        q = "INSERT INTO \"arma\" (\"nomee\",\"danno\",\"proprieta\") VALUES ('mazza','1xd4 + modificatore forza','leggera');";
        db.execSQL(q);
        q = "INSERT INTO \"armatura\" (\"nomee\",\"nonFurtiva\",\"modificatoreCa\",\"tempoTogliere\",\"tempoIndossare\",\"forzaNecessaria\") VALUES ('maglia in cuoio',0,2,'4sec','3sec',NULL);";
        db.execSQL(q);

        /* nomi valute */
        q = "INSERT INTO \"nomeVal\" (\"nomev\",\"nome\") VALUES ('moneta del regno di Ho','platino');";
        db.execSQL(q);
        q = "INSERT INTO \"nomeVal\" (\"nomev\",\"nome\") VALUES ('moneta del regno di Ho','oro');";
        db.execSQL(q);
        q = "INSERT INTO \"nomeVal\" (\"nomev\",\"nome\") VALUES ('moneta del regno di Ho','argento');";
        db.execSQL(q);
        q = "INSERT INTO \"nomeVal\" (\"nomev\",\"nome\") VALUES ('moneta del regno di Ho','rame');";
        db.execSQL(q);

        /* HA */
        q = "INSERT INTO \"hace\" (\"nomecla\",\"nomee\") VALUES ('gueriero','mazza');";
        db.execSQL(q);
        q = "INSERT INTO \"hace\" (\"nomecla\",\"nomee\") VALUES ('ranger','maglia in cuoio');";
        db.execSQL(q);
        q = "INSERT INTO \"haci\" (\"nomecla\",\"nomei\") VALUES ('ranger','evocazione elementare');";
        db.execSQL(q);
        q = "INSERT INTO \"haci\" (\"nomecla\",\"nomei\") VALUES ('ranger','freccia magica');";
        db.execSQL(q);
        q = "INSERT INTO \"hacp\" (\"nomecla\",\"nomep\") VALUES ('gueriero','leader in prima linea');";
        db.execSQL(q);
        q = "INSERT INTO \"hacp\" (\"nomecla\",\"nomep\") VALUES ('gueriero','maetro della guerra');";
        db.execSQL(q);
        q = "INSERT INTO \"hacp\" (\"nomecla\",\"nomep\") VALUES ('ranger','naturalista');";
        db.execSQL(q);
        q = "INSERT INTO \"hacp\" (\"nomecla\",\"nomep\") VALUES ('ranger','protezione della foresta');";
        db.execSQL(q);
        q = "INSERT INTO \"harp\" (\"nomer\",\"nomep\") VALUES ('elfo','agilità superiore');";
        db.execSQL(q);
        q = "INSERT INTO \"harp\" (\"nomer\",\"nomep\") VALUES ('elfo','meditazione');";
        db.execSQL(q);
        q = "INSERT INTO \"harp\" (\"nomer\",\"nomep\") VALUES ('nano','durascorza');";
        db.execSQL(q);
        q = "INSERT INTO \"harp\" (\"nomer\",\"nomep\") VALUES ('nano','furia');";
        db.execSQL(q);
        q = "INSERT INTO \"harp\" (\"nomer\",\"nomep\") VALUES ('nano','instancabile');";
        db.execSQL(q);

        /* abilità */
        q = "INSERT INTO \"abilita\" (\"nomea\",\"desc\") VALUES ('correre','sai correre');";
        db.execSQL(q);
        q = "INSERT INTO \"abilita\" (\"nomea\",\"desc\") VALUES ('cavalcare','sai cavalcare');";
        db.execSQL(q);
        q = "INSERT INTO \"abilita\" (\"nomea\",\"desc\") VALUES ('scassinare','nessuna serratura può fermarti');";
        db.execSQL(q);
        q = "INSERT INTO \"abilita\" (\"nomea\",\"desc\") VALUES ('arrampicare','in una vita passata eri un geco');";
        db.execSQL(q);
        q = "INSERT INTO \"abilita\" (\"nomea\",\"desc\") VALUES ('origliare','anche il muro più spesso non riesce a fermarti');";
        db.execSQL(q);
        q = "INSERT INTO \"abilita\" (\"nomea\",\"desc\") VALUES ('schermire','nessun avversario sa resistere alle tue provocazioni');";
        db.execSQL(q);
        q = "INSERT INTO \"abilita\" (\"nomea\",\"desc\") VALUES ('riparazione','nelle tue mani messun equipaggiamento si romperà mai');";
        db.execSQL(q);

        /* carBase */
        q = "INSERT INTO \"carBase\" (\"nomer\",\"nomecb\",\"valore\") VALUES ('elfo','destrezza',2);";
        db.execSQL(q);
        q = "INSERT INTO \"carBase\" (\"nomer\",\"nomecb\",\"valore\") VALUES ('elfo','carisma',1);";
        db.execSQL(q);
        q = "INSERT INTO \"carBase\" (\"nomer\",\"nomecb\",\"valore\") VALUES ('elfo','intelligenza',2);";
        db.execSQL(q);
        q = "INSERT INTO \"carBase\" (\"nomer\",\"nomecb\",\"valore\") VALUES ('nano','costituzione',2);";
        db.execSQL(q);
        q = "INSERT INTO \"carBase\" (\"nomer\",\"nomecb\",\"valore\") VALUES ('nano','forza',3);";
        db.execSQL(q);

        q = "INSERT INTO \"giocatore\" (\"nomeCampagna\",\"nomeg\",\"desc\",\"mana\",\"livello\",\"puntiXP\",\"modCompetenza\",\"capacitaBorsa\",\"puntiFerita\",\"classeArmatura\",\"puntiStat\",\"nDadi\",\"dado\",\"iniziativa\",\"eta\",\"altezza\",\"genere\",\"noteAvventura\",\"allineamento\",\"lingua\",\"nomecla\",\"nomer\",\"nomev\",\"valoreVal\") VALUES ('campagna','ciro','un rozzo nano ',0,1,0,2,0,13,0,0,1,8,'0','130','90','M',NULL,'caotico neutrale',NULL,'gueriero','nano','moneta del regno di Ho',0);";
        db.execSQL(q);
        q = "INSERT INTO \"caratteristicaG\" (\"nomeCampagna\",\"nomeg\",\"nomecar\",\"tiroSalvezza\",\"valoreBase\",\"valoreLivello\",\"valoreEquipaggiamento\",\"valoreBonus\") VALUES ('campagna','ciro','carisma',0,0,0,0,0);";
        db.execSQL(q);
        q = "INSERT INTO \"caratteristicaG\" (\"nomeCampagna\",\"nomeg\",\"nomecar\",\"tiroSalvezza\",\"valoreBase\",\"valoreLivello\",\"valoreEquipaggiamento\",\"valoreBonus\") VALUES ('campagna','ciro','costituzione',0,0,0,0,0);";
        db.execSQL(q);
        q = "INSERT INTO \"caratteristicaG\" (\"nomeCampagna\",\"nomeg\",\"nomecar\",\"tiroSalvezza\",\"valoreBase\",\"valoreLivello\",\"valoreEquipaggiamento\",\"valoreBonus\") VALUES ('campagna','ciro','destrezza',0,0,0,0,0);";
        db.execSQL(q);
        q = "INSERT INTO \"caratteristicaG\" (\"nomeCampagna\",\"nomeg\",\"nomecar\",\"tiroSalvezza\",\"valoreBase\",\"valoreLivello\",\"valoreEquipaggiamento\",\"valoreBonus\") VALUES ('campagna','ciro','forza',0,0,0,0,0);";
        db.execSQL(q);
        q = "INSERT INTO \"caratteristicaG\" (\"nomeCampagna\",\"nomeg\",\"nomecar\",\"tiroSalvezza\",\"valoreBase\",\"valoreLivello\",\"valoreEquipaggiamento\",\"valoreBonus\") VALUES ('campagna','ciro','saggezza',0,0,0,0,0);";
        db.execSQL(q);
        q = "INSERT INTO \"caratteristicaG\" (\"nomeCampagna\",\"nomeg\",\"nomecar\",\"tiroSalvezza\",\"valoreBase\",\"valoreLivello\",\"valoreEquipaggiamento\",\"valoreBonus\") VALUES ('campagna','ciro','intelligenza',0,0,0,0,0);";
        db.execSQL(q);

        q = "INSERT INTO \"giocatore\" (\"nomeCampagna\",\"nomeg\",\"desc\",\"mana\",\"livello\",\"puntiXP\",\"modCompetenza\",\"capacitaBorsa\",\"puntiFerita\",\"classeArmatura\",\"puntiStat\",\"nDadi\",\"dado\",\"iniziativa\",\"eta\",\"altezza\",\"genere\",\"noteAvventura\",\"allineamento\",\"lingua\",\"nomecla\",\"nomer\",\"nomev\",\"valoreVal\") VALUES ('Arcadia','Fieryor','drago elettrico',0,12,0,2,0,70,0,0,1,8,'0','27','192','M',NULL,'caotico neutrale',NULL,'ranger','elfo','moneta del regno di Ho',0);";
        db.execSQL(q);
        q = "INSERT INTO \"caratteristicaG\" (\"nomeCampagna\",\"nomeg\",\"nomecar\",\"tiroSalvezza\",\"valoreBase\",\"valoreLivello\",\"valoreEquipaggiamento\",\"valoreBonus\") VALUES ('Arcadia','Fieryor','carisma',0,0,0,0,0);";
        db.execSQL(q);
        q = "INSERT INTO \"caratteristicaG\" (\"nomeCampagna\",\"nomeg\",\"nomecar\",\"tiroSalvezza\",\"valoreBase\",\"valoreLivello\",\"valoreEquipaggiamento\",\"valoreBonus\") VALUES ('Arcadia','Fieryor','costituzione',0,0,0,0,0);";
        db.execSQL(q);
        q = "INSERT INTO \"caratteristicaG\" (\"nomeCampagna\",\"nomeg\",\"nomecar\",\"tiroSalvezza\",\"valoreBase\",\"valoreLivello\",\"valoreEquipaggiamento\",\"valoreBonus\") VALUES ('Arcadia','Fieryor','destrezza',0,0,0,0,0);";
        db.execSQL(q);
        q = "INSERT INTO \"caratteristicaG\" (\"nomeCampagna\",\"nomeg\",\"nomecar\",\"tiroSalvezza\",\"valoreBase\",\"valoreLivello\",\"valoreEquipaggiamento\",\"valoreBonus\") VALUES ('Arcadia','Fieryor','forza',0,0,0,0,0);";
        db.execSQL(q);
        q = "INSERT INTO \"caratteristicaG\" (\"nomeCampagna\",\"nomeg\",\"nomecar\",\"tiroSalvezza\",\"valoreBase\",\"valoreLivello\",\"valoreEquipaggiamento\",\"valoreBonus\") VALUES ('Arcadia','Fieryor','saggezza',0,0,0,0,0);";
        db.execSQL(q);
        q = "INSERT INTO \"caratteristicaG\" (\"nomeCampagna\",\"nomeg\",\"nomecar\",\"tiroSalvezza\",\"valoreBase\",\"valoreLivello\",\"valoreEquipaggiamento\",\"valoreBonus\") VALUES ('Arcadia','Fieryor','intelligenza',0,0,0,0,0);";
        db.execSQL(q);
        
    }
}

