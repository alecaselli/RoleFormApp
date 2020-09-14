BEGIN TRANSACTION;
CREATE TABLE IF NOT EXISTS "incantesmi" (
	"nomei"	TEXT,
	"desc"	TEXT,
	"livello"	INTEGER,
	"tempoInvocazione"	TEXT,
	"raggioAzione"	TEXT,
	"componenti"	TEXT,
	"durata"	TEXT,
	PRIMARY KEY("nomei")
);
CREATE TABLE IF NOT EXISTS "valuta" (
	"nomev"	TEXT,
	"desc"	TEXT,
	"ratio"	INTEGER,
	PRIMARY KEY("nomev")
);
CREATE TABLE IF NOT EXISTS "razza" (
	"nomer"	TEXT,
	"desc"	TEXT,
	"taglia"	TEXT,
	"velocita"	TEXT,
	"lingua"	TEXT,
	PRIMARY KEY("nomer")
);
CREATE TABLE IF NOT EXISTS "privilegi" (
	"nomep"	TEXT,
	"desc"	TEXT,
	PRIMARY KEY("nomep")
);
CREATE TABLE IF NOT EXISTS "classe" (
	"nomecla"	TEXT,
	"desc"	TEXT,
	"nDadi"	INTEGER,
	"dado"	INTEGER,
	"descPrivilegi"	TEXT,
	"competenza"	TEXT,
	PRIMARY KEY("nomecla")
);
CREATE TABLE IF NOT EXISTS "equipaggiamento" (
	"nomee"	TEXT,
	"desc"	TEXT,
	"costo"	INTEGER,
	"peso"	INTEGER,
	"capacita"	INTEGER,
	"tipo"	TEXT,
	PRIMARY KEY("nomee")
);
CREATE TABLE IF NOT EXISTS "caratteristica" (
	"nomecar"	TEXT,
	"desc"	TEXT,
	PRIMARY KEY("nomecar")
);
CREATE TABLE IF NOT EXISTS "arma" (
	"nomee"	TEXT,
	"danno"	TEXT,
	"proprieta"	TEXT,
	CONSTRAINT "FK_oggetto" FOREIGN KEY("nomee") REFERENCES "equipaggiamento"("nomee") ON UPDATE CASCADE,
	CONSTRAINT "PK_arma" PRIMARY KEY("nomee")
);
CREATE TABLE IF NOT EXISTS "armatura" (
	"nomee"	TEXT,
	"nonFurtiva"	INTEGER,
	"modificatoreCa"	INTEGER,
	"tempoTogliere"	TEXT,
	"tempoIndossare"	TEXT,
	"forzaNecessaria"	TEXT,
	FOREIGN KEY("nomee") REFERENCES "equipaggiamento"("nomee") ON UPDATE CASCADE,
	PRIMARY KEY("nomee")
);
CREATE TABLE IF NOT EXISTS "caratteristicaG" (
	"nomeCampagna"	TEXT,
	"nomeg"	TEXT,
	"nomecar"	TEXT,
	"tiroSalvezza"	INTEGER,
	"valoreBase"	INTEGER,
	"valoreLivello"	INTEGER,
	"valoreEquipaggiamento"	INTEGER,
	"valoreBonus"	INTEGER,
	FOREIGN KEY("nomecar") REFERENCES "caratteristica"("nomecar") ON UPDATE CASCADE,
	FOREIGN KEY("nomeCampagna","nomeg") REFERENCES "giocatore"("nomeCampagna","nomeg") ON UPDATE CASCADE,
	PRIMARY KEY("nomeCampagna","nomeg","nomecar")
);
CREATE TABLE IF NOT EXISTS "nomeVal" (
	"nomev"	TEXT,
	"nome"	TEXT,
	CONSTRAINT "PF_nomeval" PRIMARY KEY("nomev","nome"),
	FOREIGN KEY("nomev") REFERENCES "valuta"("nomev") ON UPDATE CASCADE
);
CREATE TABLE IF NOT EXISTS "hage" (
	"nomeCampagna"	TEXT,
	"nomeg"	TEXT,
	"nomee"	TEXT,
	"borsa"	INTEGER,
	PRIMARY KEY("nomee","nomeCampagna","nomeg"),
	FOREIGN KEY("nomee") REFERENCES "equipaggiamento"("nomee") ON UPDATE CASCADE,
	FOREIGN KEY("nomeCampagna","nomeg") REFERENCES "giocatore"("nomeCampagna","nomeg") ON UPDATE CASCADE
);
CREATE TABLE IF NOT EXISTS "hagi" (
	"nomeCampagna"	TEXT,
	"nomeg"	TEXT,
	"nomei"	TEXT,
	PRIMARY KEY("nomeCampagna","nomeg","nomei"),
	FOREIGN KEY("nomeCampagna","nomeg") REFERENCES "giocatore"("nomeCampagna","nomeg") ON UPDATE CASCADE,
	FOREIGN KEY("nomei") REFERENCES "incantesmi"("nomei") ON UPDATE CASCADE
);
CREATE TABLE IF NOT EXISTS "hace" (
	"nomecla"	TEXT,
	"nomee"	TEXT,
	PRIMARY KEY("nomee","nomecla"),
	FOREIGN KEY("nomecla") REFERENCES "classe"("nomecla") ON UPDATE CASCADE,
	FOREIGN KEY("nomee") REFERENCES "equipaggiamento"("nomee") ON UPDATE CASCADE
);
CREATE TABLE IF NOT EXISTS "haci" (
	"nomecla"	TEXT,
	"nomei"	TEXT,
	PRIMARY KEY("nomecla","nomei"),
	FOREIGN KEY("nomecla") REFERENCES "classe"("nomecla") ON UPDATE CASCADE,
	FOREIGN KEY("nomei") REFERENCES "incantesmi"("nomei") ON UPDATE CASCADE
);
CREATE TABLE IF NOT EXISTS "hacp" (
	"nomecla"	TEXT,
	"nomep"	TEXT,
	CONSTRAINT "PK_hacp" PRIMARY KEY("nomep","nomecla"),
	CONSTRAINT "FK_classe" FOREIGN KEY("nomecla") REFERENCES "classe"("nomecla") ON UPDATE CASCADE,
	CONSTRAINT "FK_privilegi" FOREIGN KEY("nomep") REFERENCES "privilegi"("nomep") ON UPDATE CASCADE
);
CREATE TABLE IF NOT EXISTS "harp" (
	"nomer"	INTEGER,
	"nomep"	INTEGER,
	PRIMARY KEY("nomer","nomep"),
	FOREIGN KEY("nomer") REFERENCES "razza"("nomer") ON UPDATE CASCADE,
	FOREIGN KEY("nomep") REFERENCES "privilegi"("nomep") ON UPDATE CASCADE
);
CREATE TABLE IF NOT EXISTS "abilita" (
	"nomea"	TEXT,
	"desc"	TEXT,
	PRIMARY KEY("nomea")
);
CREATE TABLE IF NOT EXISTS "haga" (
	"nomeCampagna"	TEXT,
	"nomeg"	TEXT,
	"nomea"	TEXT,
	"competenza"	INTEGER,
	PRIMARY KEY("nomea","nomeCampagna","nomeg"),
	FOREIGN KEY("nomeCampagna","nomeg") REFERENCES "giocatore"("nomeCampagna","nomeg") ON UPDATE CASCADE,
	FOREIGN KEY("nomea") REFERENCES "abilita"("nomea") ON UPDATE CASCADE
);
CREATE TABLE IF NOT EXISTS "carBase" (
	"nomer"	TEXT,
	"nomecb"	TEXT,
	"valore"	INTEGER,
	PRIMARY KEY("nomer","nomecb"),
	FOREIGN KEY("nomecb") REFERENCES "caratteristica"("nomecar") ON UPDATE CASCADE,
	FOREIGN KEY("nomer") REFERENCES "razza"("nomer") ON UPDATE CASCADE
);
CREATE TABLE IF NOT EXISTS "giocatore" (
	"nomeCampagna"	TEXT,
	"nomeg"	TEXT,
	"desc"	TEXT,
	"mana"	INTEGER,
	"livello"	INTEGER,
	"puntiXP"	INTEGER,
	"modCompetenza"	INTEGER,
	"capacitaBorsa"	INTEGER,
	"puntiFerita"	INTEGER,
	"classeArmatura"	INTEGER,
	"puntiStat"	INTEGER,
	"nDadi"	INTEGER,
	"dado"	INTEGER,
	"iniziativa"	TEXT,
	"eta"	TEXT,
	"altezza"	TEXT,
	"genere"	TEXT,
	"noteAvventura"	TEXT,
	"allineamento"	TEXT,
	"lingua"	TEXT,
	"nomecla"	TEXT NOT NULL,
	"nomer"	TEXT NOT NULL,
	"nomev"	TEXT NOT NULL,
	"valoreVal"	INTEGER,
	PRIMARY KEY("nomeCampagna","nomeg"),
	FOREIGN KEY("nomev") REFERENCES "valuta"("nomev") ON UPDATE CASCADE,
	FOREIGN KEY("nomer") REFERENCES "razza"("nomer") ON UPDATE CASCADE,
	FOREIGN KEY("nomecla") REFERENCES "classe"("nomecla") ON UPDATE CASCADE
);
INSERT INTO "incantesmi" ("nomei","desc","livello","tempoInvocazione","raggioAzione","componenti","durata") VALUES ('palla di fuoco','spari una palla di fuoco',1,'istantaneo','5m','nessuno','istantaneo');
INSERT INTO "incantesmi" ("nomei","desc","livello","tempoInvocazione","raggioAzione","componenti","durata") VALUES ('lanci di ghiaccio','spari una lancia di ghiaccio',2,'15sec','3m','ghiacciolo','istantaneo');
INSERT INTO "incantesmi" ("nomei","desc","livello","tempoInvocazione","raggioAzione","componenti","durata") VALUES ('cura','guarisci te stesso o un tuo alleato di 2 punto ferita ogni 30sec, devi mantenere il contatto',1,'istantaneo','contatto','pergamena del chierico','3min');
INSERT INTO "incantesmi" ("nomei","desc","livello","tempoInvocazione","raggioAzione","componenti","durata") VALUES ('evocazione elementare','puoi evocare un pet elemntare 4/8, possiede un proprio turno e può attaccare 2 volte',5,'1min','2m','una lacrima di cervo',NULL);
INSERT INTO "incantesmi" ("nomei","desc","livello","tempoInvocazione","raggioAzione","componenti","durata") VALUES ('freccia magica','spari 5 frecce di mana anzichè una normale, ogni freccia fa danno norale  -1',4,'istantaneo','15m','un crisallo di mana','istantaneo');
INSERT INTO "valuta" ("nomev","desc","ratio") VALUES ('moneta del regno di Ho','la valuta di uso comune nel regno di ho, si suddivide in 5 tagli',10);
INSERT INTO "razza" ("nomer","desc","taglia","velocita","lingua") VALUES ('nano','una delle razze più comuni nel continente, sei riconosciuto da utta la popolazione come menbro della tua razza e quindi non susciti salcopre nelle città.
molti regnanti hanno una buona impressione della tua razza, non hai santaggi commerciali,
nato fabbro sei dotato di una forza straordinaria e nonostante la tua statura bassa e tozza hai una buona manualità','piccola','9m','nanico, umano, runico nanico');
INSERT INTO "razza" ("nomer","desc","taglia","velocita","lingua") VALUES ('elfo','una delle razze più comuni nel continente, sei riconosciuto da utta la popolazione come menbro della tua razza e quindi non susciti salcopre nelle città.
molti regnanti hanno una buona impressione della tua razza, non hai santaggi commerciali.
neato direttamente da madre natura hai una grande affinità con essa, ciò ti dona un''alta longevità e una apparenza affascinante, la vita nei boschi ti ha donato grande destrezza e capacità di elusione','media','9m','elfico, umano, runico elfico');
INSERT INTO "privilegi" ("nomep","desc") VALUES ('scurovisione','non hai impedimenti nei tiri osservazione al buio');
INSERT INTO "privilegi" ("nomep","desc") VALUES ('durascorza','dimezzi i danni da impatto');
INSERT INTO "privilegi" ("nomep","desc") VALUES ('meditazione','recuperi punti ferita nella metà del tempo, recupero totale in  4 ore anzichè 8');
INSERT INTO "privilegi" ("nomep","desc") VALUES ('agilità superiore','hai un +2 nei tiri destrezza');
INSERT INTO "privilegi" ("nomep","desc") VALUES ('instancabile','ti stanchi meno delle razze comuni, ti sazi con mezza razione di cibo');
INSERT INTO "privilegi" ("nomep","desc") VALUES ('maetro della guerra','hai un +2 nei tiri attacco');
INSERT INTO "privilegi" ("nomep","desc") VALUES ('furia','ogni 2 livelli acquisisci 1 punto furia spendibile in un tiro forza, puoi accumularne al massimo 6');
INSERT INTO "privilegi" ("nomep","desc") VALUES ('leader in prima linea','hai un +3 nei tiri iniziativa');
INSERT INTO "privilegi" ("nomep","desc") VALUES ('naturalista','la tua affinità con la natura è tale da portarti lla stima del dio Pam +3 nei tiri che riguardano cose naturali');
INSERT INTO "privilegi" ("nomep","desc") VALUES ('protezione della foresta','se stai combattendo nella foresta riduci il danno magico di 4');
INSERT INTO "classe" ("nomecla","desc","nDadi","dado","descPrivilegi","competenza") VALUES ('ranger','sei un avamposto naturale per la tua compagnia di avventuri in luogni inoti per coglier informazioni essenziali per la vostra avventura, hai una forte affinità con la natura',1,6,NULL,'armi: leggere; abilità: correre, arrampicarti; tiri salvezza: destrezza, carisma; ');
INSERT INTO "classe" ("nomecla","desc","nDadi","dado","descPrivilegi","competenza") VALUES ('gueriero','sei nato per stare in battaglia, sei in prima linea in ogni combattimento. Pronto a proteggere i tuo compagni e attaccare i tuoi avversari',1,8,NULL,'armi:da guerra; abilità: schermire, riparare; tiri salvezza: forza, costituzione');
INSERT INTO "equipaggiamento" ("nomee","desc","costo","peso","capacita","tipo") VALUES ('maglia in cuoio',NULL,40,NULL,0,'armatura');
INSERT INTO "equipaggiamento" ("nomee","desc","costo","peso","capacita","tipo") VALUES ('mazza',NULL,70,NULL,0,'arma');
INSERT INTO "equipaggiamento" ("nomee","desc","costo","peso","capacita","tipo") VALUES ('razione','razione di cibo secco equivalente a un pasto',5,NULL,0,'equipaggiamento da avventura');
INSERT INTO "caratteristica" ("nomecar","desc") VALUES ('forza','la tua forza fisica');
INSERT INTO "caratteristica" ("nomecar","desc") VALUES ('destrezza','la capacità di cavartela in ogni circostanza');
INSERT INTO "caratteristica" ("nomecar","desc") VALUES ('costituzione','la tua vitalità');
INSERT INTO "caratteristica" ("nomecar","desc") VALUES ('intelligenza','la tua mole di conoscenza');
INSERT INTO "caratteristica" ("nomecar","desc") VALUES ('saggezza','iil tuo grado di discernimento della natura intrinseca delle cose');
INSERT INTO "caratteristica" ("nomecar","desc") VALUES ('carisma','capacità nell''affascinare gli altri');
INSERT INTO "arma" ("nomee","danno","proprieta") VALUES ('mazza','1xd4 + modificatore forza','leggera');
INSERT INTO "armatura" ("nomee","nonFurtiva","modificatoreCa","tempoTogliere","tempoIndossare","forzaNecessaria") VALUES ('maglia in cuoio',0,2,'4sec','3sec',NULL);
INSERT INTO "caratteristicaG" ("nomeCampagna","nomeg","nomecar","tiroSalvezza","valoreBase","valoreLivello","valoreEquipaggiamento","valoreBonus") VALUES ('campagna','ciro','carisma',0,0,0,0,0);
INSERT INTO "caratteristicaG" ("nomeCampagna","nomeg","nomecar","tiroSalvezza","valoreBase","valoreLivello","valoreEquipaggiamento","valoreBonus") VALUES ('campagna','ciro','costituzione',0,0,0,0,0);
INSERT INTO "caratteristicaG" ("nomeCampagna","nomeg","nomecar","tiroSalvezza","valoreBase","valoreLivello","valoreEquipaggiamento","valoreBonus") VALUES ('campagna','ciro','destrezza',0,0,0,0,0);
INSERT INTO "caratteristicaG" ("nomeCampagna","nomeg","nomecar","tiroSalvezza","valoreBase","valoreLivello","valoreEquipaggiamento","valoreBonus") VALUES ('campagna','ciro','forza',0,0,0,0,0);
INSERT INTO "caratteristicaG" ("nomeCampagna","nomeg","nomecar","tiroSalvezza","valoreBase","valoreLivello","valoreEquipaggiamento","valoreBonus") VALUES ('campagna','ciro','saggezza',0,0,0,0,0);
INSERT INTO "caratteristicaG" ("nomeCampagna","nomeg","nomecar","tiroSalvezza","valoreBase","valoreLivello","valoreEquipaggiamento","valoreBonus") VALUES ('campagna','ciro','intelligenza',0,0,0,0,0);
INSERT INTO "nomeVal" ("nomev","nome") VALUES ('moneta del regno di Ho','platino');
INSERT INTO "nomeVal" ("nomev","nome") VALUES ('moneta del regno di Ho','oro');
INSERT INTO "nomeVal" ("nomev","nome") VALUES ('moneta del regno di Ho','argento');
INSERT INTO "nomeVal" ("nomev","nome") VALUES ('moneta del regno di Ho','rame');
INSERT INTO "hace" ("nomecla","nomee") VALUES ('gueriero','mazza');
INSERT INTO "hace" ("nomecla","nomee") VALUES ('ranger','maglia in cuoio');
INSERT INTO "haci" ("nomecla","nomei") VALUES ('ranger','evocazione elementare');
INSERT INTO "haci" ("nomecla","nomei") VALUES ('ranger','freccia magica');
INSERT INTO "hacp" ("nomecla","nomep") VALUES ('gueriero','leader in prima linea');
INSERT INTO "hacp" ("nomecla","nomep") VALUES ('gueriero','maetro della guerra');
INSERT INTO "hacp" ("nomecla","nomep") VALUES ('ranger','naturalista');
INSERT INTO "hacp" ("nomecla","nomep") VALUES ('ranger','protezione della foresta');
INSERT INTO "harp" ("nomer","nomep") VALUES ('elfo','agilità superiore');
INSERT INTO "harp" ("nomer","nomep") VALUES ('elfo','meditazione');
INSERT INTO "harp" ("nomer","nomep") VALUES ('nano','durascorza');
INSERT INTO "harp" ("nomer","nomep") VALUES ('nano','furia');
INSERT INTO "harp" ("nomer","nomep") VALUES ('nano','instancabile');
INSERT INTO "abilita" ("nomea","desc") VALUES ('correre','sai correre');
INSERT INTO "abilita" ("nomea","desc") VALUES ('cavalcare','sai cavalcare');
INSERT INTO "abilita" ("nomea","desc") VALUES ('scassinare','nessuna serratura può fermarti');
INSERT INTO "abilita" ("nomea","desc") VALUES ('arrampicare','in una vita passata eri un geco');
INSERT INTO "abilita" ("nomea","desc") VALUES ('origliare','anche il muro più spesso non riesce a fermarti');
INSERT INTO "abilita" ("nomea","desc") VALUES ('schermire','nessun avversario sa resistere alle tue provocazioni');
INSERT INTO "abilita" ("nomea","desc") VALUES ('riparazione','nelle tue mani messun equipaggiamento si romperà mai');
INSERT INTO "carBase" ("nomer","nomecb","valore") VALUES ('elfo','destrezza',2);
INSERT INTO "carBase" ("nomer","nomecb","valore") VALUES ('elfo','carisma',1);
INSERT INTO "carBase" ("nomer","nomecb","valore") VALUES ('elfo','intelligenza',2);
INSERT INTO "carBase" ("nomer","nomecb","valore") VALUES ('nano','costituzione',2);
INSERT INTO "carBase" ("nomer","nomecb","valore") VALUES ('nano','forza',3);
INSERT INTO "giocatore" ("nomeCampagna","nomeg","desc","mana","livello","puntiXP","modCompetenza","capacitaBorsa","puntiFerita","classeArmatura","puntiStat","nDadi","dado","iniziativa","eta","altezza","genere","noteAvventura","allineamento","lingua","nomecla","nomer","nomev","valoreVal") VALUES ('campagna','ciro','un rozzo nano ',0,1,0,2,0,13,0,0,1,8,'0','130','90','M',NULL,'caotico neutrale',NULL,'gueriero','nano','moneta del regno di Ho',0);
COMMIT;
