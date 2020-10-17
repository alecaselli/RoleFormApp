package com.example.myfirstapp.domain;

import android.content.Context;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class Giocatore extends Descrivibile {
    private int capacitaBorsa;
    private int classeArmatura;
    private int dado;
    private int livello;
    private int mana;
    private int modCompetenza;
    private int nDadi;
    private int puntiEsperienza;
    private int puntiFerita;
    private int puntiFeritaMax;
    private int puntiStat;
    private String altezza;
    private String eta;
    private String genere;
    private String ideali;
    private String iniziativa;
    private String nomeCampagna;
    private String noteAvventura;
    private String sinossi;
    private StringBuffer lingua;
    private Valuta portafoglio;
    private Classe classe;
    private Razza razza;
    private List<Caratteristica> caratteristicaList;
    private List<Equipaggiamento> borsa;
    private List<Equipaggiamento> equipaggiato;
    private List<Incantesimo> incantesimiGiocatore;
    private List<Abilita> abilitaList;

    public Giocatore(String nome, StringBuffer descrizione, String nomeCampagna, String eta, String altezza, String genere, Valuta portafoglio, Classe classe, Razza razza, List<Caratteristica> caratteristicaList, List<Abilita> abilitaList) {
        super(nome, descrizione);
        this.classe = classe;
        this.nomeCampagna = nomeCampagna;
        this.eta = eta;
        this.altezza = altezza;
        this.genere = genere;
        this.razza = razza;
        this.portafoglio = portafoglio;
        this.caratteristicaList = caratteristicaList;
        this.abilitaList = abilitaList;
        this.inizializzazionePG();
    }

    public Giocatore(String nome, StringBuffer descrizione, int capacitaBorsa, int classeArmatura, int dado, int livello, int mana, int modCompetenza, int nDadi, int puntiEsperienza, int puntiFerita, int puntiStat, String altezza, String eta, String genere, String ideali, String iniziativa, String nomeCampagna, String noteAvventura, String sinossi, StringBuffer lingua, Valuta portafoglio, Classe classe, Razza razza, List<Caratteristica> caratteristicaList, List<Equipaggiamento> borsa, List<Equipaggiamento> equipaggiato, List<Incantesimo> incantesimiGiocatore, List<Abilita> abilitaList) {
        super(nome, descrizione);
        this.capacitaBorsa = capacitaBorsa;
        this.classeArmatura = classeArmatura;
        this.dado = dado;
        this.livello = livello;
        this.mana = mana;
        this.modCompetenza = modCompetenza;
        this.nDadi = nDadi;
        this.puntiEsperienza = puntiEsperienza;
        this.puntiFerita = puntiFerita;
        this.inizPuntiFeritaMax();
        this.puntiStat = puntiStat;
        this.altezza = altezza;
        this.eta = eta;
        this.genere = genere;
        this.ideali = ideali;
        this.iniziativa = iniziativa;
        this.nomeCampagna = nomeCampagna;
        this.noteAvventura = noteAvventura;
        this.sinossi = sinossi;
        this.lingua = lingua;
        this.portafoglio = portafoglio;
        this.classe = classe;
        this.razza = razza;
        this.caratteristicaList = caratteristicaList;
        this.borsa = borsa;
        this.equipaggiato = equipaggiato;
        this.incantesimiGiocatore = incantesimiGiocatore;
        this.abilitaList = abilitaList;
    }

    public int getCapacitaBorsa() {
        return capacitaBorsa;
    }

    public void setCapacitaBorsa(int capacitaBorsa) {
        this.capacitaBorsa = capacitaBorsa;
    }

    public int getClasseArmatura() {
        return classeArmatura;
    }

    public void setClasseArmatura(int classeArmatura) {
        this.classeArmatura = classeArmatura;
    }

    public int getDado() {
        return dado;
    }

    public void setDado(int dado) {
        this.dado = dado;
    }

    public int getLivello() {
        return livello;
    }

    public void setLivello(int livello) {
        this.livello = livello;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public int getModCompetenza() {
        return modCompetenza;
    }

    public void setModCompetenza(int modCompetenza) {
        this.modCompetenza = modCompetenza;
    }

    public int getnDadi() {
        return nDadi;
    }

    public void setnDadi(int nDadi) {
        this.nDadi = nDadi;
    }

    public int getPuntiEsperienza() {
        return puntiEsperienza;
    }

    public void setPuntiEsperienza(int puntiEsperienza) {
        this.puntiEsperienza = puntiEsperienza;
    }

    public int getPuntiFerita() {
        return puntiFerita;
    }

    public void setPuntiFerita(int puntiFerita) {
        this.puntiFerita = puntiFerita;
    }

    public int getPuntiFeritaMax() {
        return puntiFeritaMax;
    }

    public void setPuntiFeritaMax(int puntiFeritaMax) {
        this.puntiFeritaMax = puntiFeritaMax;
    }

    public int getPuntiStat() {
        return puntiStat;
    }

    public void setPuntiStat(int puntiStat) {
        this.puntiStat = puntiStat;
    }

    public String getAltezza() {
        return altezza;
    }

    public void setAltezza(String altezza) {
        this.altezza = altezza;
    }

    public String getEta() {
        return eta;
    }

    public void setEta(String eta) {
        this.eta = eta;
    }

    public String getGenere() {
        return genere;
    }

    public void setGenere(String genere) {
        this.genere = genere;
    }

    public String getIdeali() {
        return ideali;
    }

    public void setIdeali(String ideali) {
        this.ideali = ideali;
    }

    public String getIniziativa() {
        return iniziativa;
    }

    public void setIniziativa(String iniziativa) {
        this.iniziativa = iniziativa;
    }

    public String getNomeCampagna() {
        return nomeCampagna;
    }

    public void setNomeCampagna(String nomeCampagna) {
        this.nomeCampagna = nomeCampagna;
    }

    public String getNoteAvventura() {
        return noteAvventura;
    }

    public void setNoteAvventura(String noteAvventura) {
        this.noteAvventura = noteAvventura;
    }

    public String getSinossi() {
        return sinossi;
    }

    public void setSinossi(String sinossi) {
        this.sinossi = sinossi;
    }

    public StringBuffer getLingua() {
        return lingua;
    }

    public void setLingua(StringBuffer lingua) {
        this.lingua = lingua;
    }

    public Valuta getPortafoglio() {
        return portafoglio;
    }

    public void setPortafoglio(Valuta portafoglio) {
        this.portafoglio = portafoglio;
    }

    public Classe getClasse() {
        return classe;
    }

    public void setClasse(Classe classe) {
        this.classe = classe;
    }

    public Razza getRazza() {
        return razza;
    }

    public void setRazza(Razza razza) {
        this.razza = razza;
    }

    public List<Caratteristica> getCaratteristicaList() {
        return caratteristicaList;
    }

    public void setCaratteristicaList(List<Caratteristica> caratteristicaList) {
        this.caratteristicaList = caratteristicaList;
    }

    public List<Equipaggiamento> getBorsa() {
        return borsa;
    }

    public void setBorsa(List<Equipaggiamento> borsa) {
        this.borsa = borsa;
    }

    public List<Equipaggiamento> getEquipaggiato() {
        return equipaggiato;
    }

    public void setEquipaggiato(List<Equipaggiamento> equipaggiato) {
        this.equipaggiato = equipaggiato;
    }

    public List<Incantesimo> getIncantesimiGiocatore() {
        return incantesimiGiocatore;
    }

    public void setIncantesimiGiocatore(List<Incantesimo> incantesimiGiocatore) {
        this.incantesimiGiocatore = incantesimiGiocatore;
    }

    public List<Abilita> getAbilitaList() {
        return abilitaList;
    }

    public void setAbilitaList(List<Abilita> abilitaList) {
        this.abilitaList = abilitaList;
    }

    /* metodi non base*/
    public void inizPuntiFeritaMax() {
        this.setPuntiFeritaMax(this.getnDadi() * this.getDado());
    }

    public Caratteristica getCaratteristica(String nomec) {
        for (Caratteristica caratteristica : this.getCaratteristicaList()) {
            if (caratteristica.getNome().equals(nomec))
                return caratteristica;
        }
        return null;
    }

    public Equipaggiamento getEquipaggiato(String tipo) {
        if (equipaggiato != null) {
            for (Equipaggiamento equipaggiamento : equipaggiato) {
                if (equipaggiamento.getTipo().equals(tipo))
                    return equipaggiamento;
            }
        }
        return null;
    }


    public void aggiungiLingua(String nuova) {
        this.lingua.append(nuova);
    }

    /* serie di metodi per aggiornare i valori di parametri numerici */
    public void aggiornaMana(int val) {
        this.mana += val;
    }

    public void aggiornaPuntiEsperienza(int val) {
        this.puntiEsperienza += val;
    }

    public void aggiornaClasseArmatura(int val) {
        this.classeArmatura += val;
    }

    public void aggiornaClasseArmatura() {
        Armatura armatura = (Armatura) this.getEquipaggiato("armatura");
        this.aggiornaClasseArmatura(armatura.getModificatoreCA());
    }

    public void aggiornaPuntiStat(int val) {
        this.puntiStat += val;
    }

    public void aggiornaModCompetenza(int val) {
        this.modCompetenza += val;
    }

    /* serie di metodi per aggiungere/eliminare elementi da liste */
    public void aggiungiBorsa(List<Equipaggiamento> nuovo) {
        if (borsa == null)
            borsa = new ArrayList<Equipaggiamento>();
        this.borsa.addAll(nuovo);
    }

    public void eliminaBorsa(@NotNull List<Equipaggiamento> togli) {
        for (Equipaggiamento i : togli)
            this.borsa.remove(i);
    }

    public void aggiungiEquipaggiato(List<Equipaggiamento> nuovo) {
        if (equipaggiato == null)
            equipaggiato = new ArrayList<Equipaggiamento>();
        this.equipaggiato.addAll(nuovo);
    }

    public void eliminaEquipaggiato(@NotNull List<Equipaggiamento> togli) {
        for (Equipaggiamento i : togli)
            this.equipaggiato.remove(i);
    }

    public void aggiungiIncantesimo(List<Incantesimo> nuovo) {
        this.incantesimiGiocatore.addAll(nuovo);
    }

    public void eliminaIncantesimo(@NotNull List<Incantesimo> togli) {
        for (Incantesimo i : togli)
            this.incantesimiGiocatore.remove(i);
    }

    /* serie di metodi necessari alla creazione di un nuovo PG */
    public void inizializzazionePG() {

        for (CaratteristicaBase elementoR : this.razza.getCaratteristicaBaseList()) {
            this.getCaratteristica(elementoR.getNome()).addValoreBase(elementoR.getValore());
        }
        this.capacitaBorsa = this.classeArmatura = this.mana = this.puntiEsperienza = this.puntiStat = 0;
        this.livello = 1;
        this.modCompetenza = 2;
        this.setnDadi(this.classe.getnDadi());
        this.setDado(this.classe.getDado());
        this.inizPuntiFeritaMax();
        this.setPuntiFerita(this.puntiFeritaMax);
        this.ideali = "";
        this.iniziativa = "0";
        this.noteAvventura = "";
        this.sinossi = "";
        this.setLingua(this.razza.getLingua());

        List<Equipaggiamento> equitemp = this.classe.getEquipaggiamentoList();
        if (equitemp != null)
            this.setBorsa(equitemp);
        else
            this.setBorsa(new ArrayList<Equipaggiamento>());
        this.setEquipaggiato(new ArrayList<Equipaggiamento>());

        List<Incantesimo> inctemp = this.classe.getIncantesimiClasse();
        if (inctemp != null)
            this.setIncantesimiGiocatore(inctemp);
        else
            this.setIncantesimiGiocatore(new ArrayList<Incantesimo>());

        this.setDescrizione(this.getLingua());
        this.aggiungiDescrizione(this.classe.getDescrizionePrivilegiPoteri().toString());
        this.aggiungiDescrizione(this.classe.getCompetenza().toString());
    }

    public void levelUp() {
        this.aggiornaPuntiStat(2);
        if (this.getLivello() % 4 == 0)
            this.aggiornaModCompetenza(1);
    }

    public void assegnaPuntiStat(List<String> nomecarlist, @NotNull List<Integer> vallist, Context context) {
        int valtot = 0;
        for (int i : vallist)
            valtot += i;

        if ((this.getPuntiStat() - valtot) != 0) {
            for (String nomecar : nomecarlist) {
                this.getCaratteristica(nomecar).addValoreBase(vallist.get(nomecarlist.indexOf(nomecar)));
            }
            this.aggiornaPuntiStat(-valtot);
        } else
            Toast.makeText(context, "giocatore inserito", Toast.LENGTH_LONG).show();
    }
}
