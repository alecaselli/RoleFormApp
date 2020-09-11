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
CREATE TABLE IF NOT EXISTS "abilita" (
	"competenza"	INTEGER,
	"nomea"	TEXT,
	"desc"	TEXT,
	PRIMARY KEY("nomea")
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
	PRIMARY KEY("nomee"),
	FOREIGN KEY("nomee") REFERENCES "equipaggiamento"("nomee") ON UPDATE CASCADE
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
	"nDadi"		INTEGER,
	"dado"		INTEGER,
	"iniziativa"	TEXT,
	"eta"	TEXT,
	"noteAvventura"	TEXT,
	"allineamento"	TEXT,
	"lingua"	TEXT,
	"nomecla"	TEXT NOT NULL,
	"nomer"	TEXT NOT NULL,
	"nomev"	TEXT NOT NULL,
	"valoreVal"	INTEGER,
	PRIMARY KEY("nomeCampagna","nomeg"),
	FOREIGN KEY("nomecla") REFERENCES "classe"("nomecla") ON UPDATE CASCADE,
	FOREIGN KEY("nomer") REFERENCES "razza"("nomer") ON UPDATE CASCADE,
	FOREIGN KEY("nomev") REFERENCES "valuta"("nomev") ON UPDATE CASCADE
);
CREATE TABLE IF NOT EXISTS "carBase" (
	"nomer"	TEXT,
	"nomecb"	TEXT,
	"valore"	INTEGER,
	FOREIGN KEY("nomer") REFERENCES "razza"("nomer") ON UPDATE CASCADE,
	PRIMARY KEY("nomer","nomecb")
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
	PRIMARY KEY("nomeCampagna","nomeg","nomecar"),
	FOREIGN KEY("nomecar") REFERENCES "caratteristica"("nomecar") ON UPDATE CASCADE,
	FOREIGN KEY("nomeCampagna","nomeg") REFERENCES "giocatore"("nomeCampagna","nomeg") ON UPDATE CASCADE
);
CREATE TABLE IF NOT EXISTS "nomeVal" (
	"nomev"	TEXT,
	"nome"	TEXT,
	FOREIGN KEY("nomev") REFERENCES "valuta"("nomev") ON UPDATE CASCADE,
	CONSTRAINT "PF_nomeval" PRIMARY KEY("nomev","nome")
);
CREATE TABLE IF NOT EXISTS "haga" (
	"nomeCampagna"	TEXT,
	"nomeg"	TEXT,
	"nomea"	TEXT,
	PRIMARY KEY("nomea","nomeCampagna","nomeg"),
	FOREIGN KEY("nomeCampagna","nomeg") REFERENCES "giocatore"("nomeCampagna","nomeg") ON UPDATE CASCADE,
	FOREIGN KEY("nomea") REFERENCES "abilita"("nomea") ON UPDATE CASCADE
);
CREATE TABLE IF NOT EXISTS "hage" (
	"nomeCampagna"	TEXT,
	"nomeg"	TEXT,
	"nomee"	TEXT,
	"borsa"	INTEGER,
	PRIMARY KEY("nomee","nomeCampagna","nomeg"),
	FOREIGN KEY("nomeCampagna","nomeg") REFERENCES "giocatore"("nomeCampagna","nomeg") ON UPDATE CASCADE,
	FOREIGN KEY("nomee") REFERENCES "equipaggiamento"("nomee") ON UPDATE CASCADE
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
	FOREIGN KEY("nomee") REFERENCES "equipaggiamento"("nomee") ON UPDATE CASCADE,
	FOREIGN KEY("nomecla") REFERENCES "classe"("nomecla") ON UPDATE CASCADE
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
	CONSTRAINT "FK_classe" FOREIGN KEY("nomecla") REFERENCES "classe"("nomecla") ON UPDATE CASCADE,
	CONSTRAINT "FK_privilegi" FOREIGN KEY("nomep") REFERENCES "privilegi"("nomep") ON UPDATE CASCADE,
	CONSTRAINT "PK_hacp" PRIMARY KEY("nomep","nomecla")
);
CREATE TABLE IF NOT EXISTS "harp" (
	"nomer"	INTEGER,
	"nomep"	INTEGER,
	FOREIGN KEY("nomer") REFERENCES "razza"("nomer") ON UPDATE CASCADE,
	FOREIGN KEY("nomep") REFERENCES "privilegi"("nomep") ON UPDATE CASCADE,
	PRIMARY KEY("nomer","nomep")
);
COMMIT;
