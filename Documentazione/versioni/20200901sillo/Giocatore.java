<<<<<<< Updated upstream
package com.example.myfirstapp.domain;
=======
package com.example.myfirstapp.domainn;
>>>>>>> Stashed changes

import java.util.List;

public class Giocatore extends Descrivibile {
    private int mana;
    private int livello;
    private int puntiEsperienza;
    private int modCompetenza;
    private int capacitaBorsa;
    private String iniziativa;
    private String eta;
    private StringBuffer note;
    private StringBuffer allineamento;
    private StringBuffer lingua;
    private Classe classe;
    private Razza razza;
    private PuntiFerita puntiFeritaGiocatore;
    private Background backgroundGiocatore;
    private Competenza competenzaGiocatore;
    private List<Caratteristica> caratteristicaList;
    private List<Equipaggiamento> borsa;
    private List<Equipaggiamento> equipaggiato;
    private List<Incantesimo> incantesimiGiocatore;

    public Giocatore(String nome,
                     StringBuffer descrizione,
                     int mana,
                     int livello,
                     int puntiEsperienza,
                     String eta,
                     String iniziativa,
                     int modCompetenza,
                     int capacitaBorsa,
                     StringBuffer note,
                     StringBuffer allineamento,
                     StringBuffer lingua,
                     Classe classe,
                     Razza razza,
                     PuntiFerita puntiFeritaGiocatore,
                     Background backgroundGiocatore,
                     Competenza competenzaGiocatore,
                     List<Caratteristica> caratteristicaList, List<Equipaggiamento> borsa,
                     List<Equipaggiamento> equipaggiato,
                     List<Incantesimo> incantesimiClasse) {
        super(nome, descrizione);
        this.mana = mana;
        this.livello = livello;
        this.puntiEsperienza = puntiEsperienza;
        this.eta = eta;
        this.iniziativa = iniziativa;
        this.modCompetenza = modCompetenza;
        this.capacitaBorsa = capacitaBorsa;
        this.note = note;
        this.allineamento = allineamento;
        this.lingua = lingua;
        this.classe = classe;
        this.razza = razza;
        this.puntiFeritaGiocatore = puntiFeritaGiocatore;
        this.backgroundGiocatore = backgroundGiocatore;
        this.competenzaGiocatore = competenzaGiocatore;
        this.caratteristicaList = caratteristicaList;
        this.borsa = borsa;
        this.equipaggiato = equipaggiato;
        this.incantesimiGiocatore = incantesimiClasse;
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

    public String getEta() {
        return eta;
    }

    public void setEta(String eta) {
        this.eta = eta;
    }

    public String getIniziativa() {
        return iniziativa;
    }

    public void setIniziativa(String iniziativa) {
        this.iniziativa = iniziativa;
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

    public StringBuffer getNote() {
        return note;
    }

    public void setNote(StringBuffer note) {
        this.note = note;
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


    public void aggiungiNote(StringBuffer note){
        this.note.append(note);
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

    public void eliminaBorsa(List<Equipaggiamento> togli){
        for(Equipaggiamento i : togli)
            this.borsa.remove(i);
    }

    public void aggiungiEquipaggiato(List<Equipaggiamento> nuovo){
            this.equipaggiato.addAll(nuovo);
    }

    public void eliminaEquipaggiato(List<Equipaggiamento> togli){
        for(Equipaggiamento i : togli)
            this.equipaggiato.remove(i);
    }

    public void aggiungiIncantesimo(List<Incantesimo> nuovo){
            this.incantesimiGiocatore.addAll(nuovo);
    }

    public void eliminaIncantesimo(List<Incantesimo> togli){
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

        this.aggiungiLingua(this.razza.getLinguaggio());

        this.puntiFeritaGiocatore = new PuntiFerita(this.classe.getPuntiFeritaClasse().getPuntiFerita(),
                                                        this.classe.getPuntiFeritaClasse().getPuntiFeritaMax(),
                                                        this.classe.getPuntiFeritaClasse().getnDadi(),
                                                        this.classe.getPuntiFeritaClasse().getDado());

        this.competenzaGiocatore = new Competenza(this.classe.getCompetenzaClasse().getArmatura(),
                                                    this.classe.getCompetenzaClasse().getArma(),
                                                    this.classe.getCompetenzaClasse().getAttrezzo(),
                                                    this.classe.getCompetenzaClasse().getTiroSalvezza(),
                                                    this.classe.getCompetenzaClasse().getAbilita());

        this.aggiungiBorsa(this.classe.getEquipaggiamentoList());

        this.aggiungiNote(this.classe.getdescrizioneElementiParticolari());

        this.modCompetenza = 2;
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
