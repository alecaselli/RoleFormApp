package com.example.myfirstapp.domain;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class Giocatore extends Descrivibile {
    private int mana;
    private int livello;
    private int puntiEsperienza;
    private int modCompetenza;
    private int capacitaBorsa;
    private int puntiFerita;
    private int puntiFeritaMax;
    private int nDadi;
    private int dado;
    private int classeArmatura;
    private int puntiStat;
    private String nomeCampagna;
    private String iniziativa;
    private String eta;
    private String altezza;
    private StringBuffer noteAvventura;
    private StringBuffer allineamento;
    private StringBuffer lingua;
    private Valuta portafoglio;
    private Classe classe;
    private Razza razza;
    /*private PuntiFerita puntiFeritaGiocatore;
    private Background backgroundGiocatore;
    private Competenza competenzaGiocatore;*/
    private List<Caratteristica> caratteristicaList;
    private List<Equipaggiamento> borsa;
    private List<Equipaggiamento> equipaggiato;
    private List<Incantesimo> incantesimiGiocatore;
    private List<Abilita> abilitaList;

    public Giocatore(String nome,
                     StringBuffer descrizione,
                     int mana,
                     int livello,
                     int puntiEsperienza,
                     int modCompetenza,
                     int capacitaBorsa,
                     int puntiFerita,
                     int nDadi,
                     int dado,
                     int classeArmatura,
                     int puntiStat,
                     String nomeCampagna,
                     String iniziativa,
                     String eta,
                     String altezza, StringBuffer noteAvventura,
                     StringBuffer allineamento,
                     StringBuffer lingua,
                     Valuta portafoglio,
                     Classe classe,
                     Razza razza,
                     List<Caratteristica> caratteristicaList,
                     List<Equipaggiamento> borsa,
                     List<Equipaggiamento> equipaggiato,
                     List<Incantesimo> incantesimiGiocatore,
                     List<Abilita> abilitaList) {
        super(nome, descrizione);
        this.mana = mana;
        this.livello = livello;
        this.puntiEsperienza = puntiEsperienza;
        this.modCompetenza = modCompetenza;
        this.capacitaBorsa = capacitaBorsa;
        this.puntiFerita = puntiFerita;
        this.altezza = altezza;
        this.setPuntiFeritaMax();
        this.nDadi = nDadi;
        this.dado = dado;
        this.classeArmatura = classeArmatura;
        this.puntiStat = puntiStat;
        this.nomeCampagna = nomeCampagna;
        this.iniziativa = iniziativa;
        this.eta = eta;
        this.noteAvventura = noteAvventura;
        this.allineamento = allineamento;
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

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public int getLivello() {
        return livello;
    }

    public void setLivello(int livello) {
        this.livello = livello;
    }

    public int getPuntiEsperienza() {
        return puntiEsperienza;
    }

    public void setPuntiEsperienza(int puntiEsperienza) {
        this.puntiEsperienza = puntiEsperienza;
    }

    public int getModCompetenza() {
        return modCompetenza;
    }

    public void setModCompetenza(int modCompetenza) {
        this.modCompetenza = modCompetenza;
    }

    public int getCapacitaBorsa() {
        return capacitaBorsa;
    }

    public void setCapacitaBorsa(int capacitaBorsa) {
        this.capacitaBorsa = capacitaBorsa;
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

    public void setPuntiFeritaMax() {
        this.puntiFeritaMax =  this.getnDadi() * this.getDado();
    }

    public int getnDadi() {
        return nDadi;
    }

    public void setnDadi(int nDadi) {
        this.nDadi = nDadi;
    }

    public int getDado() {
        return dado;
    }

    public void setDado(int dado) {
        this.dado = dado;
    }

    public int getClasseArmatura() {
        return classeArmatura;
    }

    public void setClasseArmatura(int classeArmatura) {
        this.classeArmatura = classeArmatura;
    }

    public int getPuntiStat() {
        return puntiStat;
    }

    public void setPuntiStat(int puntiStat) {
        this.puntiStat = puntiStat;
    }

    public String getNomeCampagna() {
        return nomeCampagna;
    }

    public void setNomeCampagna(String nomeCampagna) {
        this.nomeCampagna = nomeCampagna;
    }

    public String getIniziativa() {
        return iniziativa;
    }

    public void setIniziativa(String iniziativa) {
        this.iniziativa = iniziativa;
    }

    public String getEta() {
        return eta;
    }

    public void setEta(String eta) {
        this.eta = eta;
    }

    public String getAltezza() {
        return altezza;
    }

    public void setAltezza(String altezza) {
        this.altezza = altezza;
    }

    public StringBuffer getNoteAvventura() {
        return noteAvventura;
    }

    public void setNoteAvventura(StringBuffer noteAvventura) {
        this.noteAvventura = noteAvventura;
    }

    public StringBuffer getAllineamento() {
        return allineamento;
    }

    public void setAllineamento(StringBuffer allineamento) {
        this.allineamento = allineamento;
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



/*
    public PuntiFerita getPuntiFeritaGiocatore() {
        return puntiFeritaGiocatore;
    }

    public void setPuntiFeritaGiocatore(PuntiFerita puntiFeritaGiocatore) {
        this.puntiFeritaGiocatore = puntiFeritaGiocatore;
    }

    public Background getBackgroundGiocatore() {
        return backgroundGiocatore;
    }

    public void setBackgroundGiocatore(Background backgroundGiocatore) {
        this.backgroundGiocatore = backgroundGiocatore;
    }

    public Competenza getCompetenzaGiocatore() {
        return competenzaGiocatore;
    }

    public void setCompetenzaGiocatore(Competenza competenzaGiocatore) {
        this.competenzaGiocatore = competenzaGiocatore;
    }*/

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

    public Caratteristica getCaratteristica(String nomec){
        for(Caratteristica caratteristica: this.getCaratteristicaList()){
            if(caratteristica.getNome().equals(nomec))
                return caratteristica;
        }
        return null;
    }

    public Equipaggiamento getEquipaggiato(String tipo) {
        for (Equipaggiamento equipaggiamento : equipaggiato) {
            if (equipaggiamento.getTipo().equals(tipo))
                return equipaggiamento;
        }
        return null;
    }

    public void aggiungiNoteAvventura(StringBuffer note){
        this.noteAvventura.append(note);
    }

    public void aggiungiLingua(StringBuffer lingua){
        this.lingua.append(lingua);
    }

    /* serie di metodi per aggiornare i valori di parametri numerici */
    public void aggiornaMana(int val){
        this.mana += val;
    }

    public void aggiornaPuntiEsperienza(int val){
        this.puntiEsperienza += val;
    }

    /* serie di metodi per aggiungere/eliminare elementi da liste */
    public void aggiungiBorsa(List<Equipaggiamento> nuovo){
        this.borsa.addAll(nuovo);
    }

    public void eliminaBorsa(@NotNull List<Equipaggiamento> togli){
        for(Equipaggiamento i : togli)
            this.borsa.remove(i);
    }

    public void aggiungiEquipaggiato(List<Equipaggiamento> nuovo){
            this.equipaggiato.addAll(nuovo);
    }

    public void eliminaEquipaggiato(@NotNull List<Equipaggiamento> togli){
        for(Equipaggiamento i : togli)
            this.equipaggiato.remove(i);
    }

    public void aggiungiIncantesimo(List<Incantesimo> nuovo){
            this.incantesimiGiocatore.addAll(nuovo);
    }

    public void eliminaIncantesimo(@NotNull List<Incantesimo> togli){
        for(Incantesimo i : togli)
            this.incantesimiGiocatore.remove(i);
    }

    /* serie di metodi necessari alla creazione di un nuovo PG */
    public void inizializzazionePG(){

        for(CaratteristicaBase elementoR : this.razza.getCaratteristicaBaseList()){
            for(Caratteristica elementoC : this.caratteristicaList){
                if(elementoC.getNome().compareToIgnoreCase(elementoR.getNome())==0)
                    elementoC.addValoreBase(elementoR.getValore());
            }
        }

        this.aggiungiLingua(this.razza.getLingua());

        this.setnDadi(this.classe.getnDadi());
        this.setDado(this.classe.getDado());
        this.setPuntiFeritaMax();
        this.setPuntiFerita(this.puntiFeritaMax);
        /*this.puntiFeritaGiocatore = new PuntiFerita(this.classe.getnDadi(), this.classe.getDado());

        this.competenzaGiocatore = new Competenza(this.classe.getCompetenzaClasse().getArmatura(),
                                                    this.classe.getCompetenzaClasse().getArma(),
                                                    this.classe.getCompetenzaClasse().getAttrezzo(),
                                                    this.classe.getCompetenzaClasse().getTiroSalvezza(),
                                                    this.classe.getCompetenzaClasse().getAbilita());*/

        this.aggiungiBorsa(this.classe.getEquipaggiamentoList());

        this.aggiungiDescrizione(this.classe.getDescrizionePrivilegiPoteri().toString());
        this.aggiungiDescrizione(this.classe.getCompetenza().toString());


        this.setModCompetenza(2);
    }


    /* calcola il valoreBase di tutte le statistiche usando razza e classe
    public void caratteristicaBase(){
        for(int i = 0; i < caratteristicaList.size(); ++i){
            this.caratteristicaList.get(i).addValoreBase(razza.getStatisticaBaseList().get(i).getValore() + classe.getStatisticaBaseList().get(i).getValore());
        }
    }



      for(StatisticheBase stat1: razza.getStatisticheBaseList()){
            if(this.compareStatistiche(stat1) == 0){
                this.valoreBase += stat1.getValore();
            }
        }
        for(StatisticheBase stat1: classe.getStatisticheBaseList()){
            if(this.compareStatistiche(stat1) == 0){
                this.valoreBase += stat1.getValore();
            }
        }
        private int compareStatistiche(StatisticheBase stat2){
        return this.nome.compareToIgnoreCase(stat2.getNome());
    }*/

}
