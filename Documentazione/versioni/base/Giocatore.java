<<<<<<< Updated upstream
package com.example.myfirstapp.domain;
=======
package com.example.myfirstapp.domainn;
>>>>>>> Stashed changes

import java.util.List;

public class Giocatore extends Descrivibile {
    private int eta;
    private int livello;
    private int puntiEsperienza;
    private float altezza;
    private Clazza razza;
    private Clazza classe;
    private String sesso;
    private String allineamento;
    private Valuta portafoglio;
    private String note;
    private List<StatisticaBase> statisticaBaseList; // ac,speed,iniz
    private List<Caratteristica> caratteristicaList; //con anche hp
    private List<Oggetto> borsa;
    private List<Abilita> abilitaList;
    private List<Capacita> capacitaList;

    public Giocatore(String nome, String descrizione, int eta, int livello, int puntiEsperienza, float altezza, Clazza razza, Clazza classe, String sesso, String allineamento, Valuta portafoglio, String note, List<StatisticaBase> statisticaBaseList, List<Caratteristica> caratteristicaList, List<Oggetto> borsa, List<Abilita> abilitaList, List<Capacita> capacitaList) {
        super(nome, descrizione);
        this.eta = eta;
        this.livello = livello;
        this.puntiEsperienza = puntiEsperienza;
        this.altezza = altezza;
        this.razza = razza;
        this.classe = classe;
        this.sesso = sesso;
        this.allineamento = allineamento;
        this.portafoglio = portafoglio;
        this.note = note;
        this.statisticaBaseList = statisticaBaseList;
        this.caratteristicaList = caratteristicaList;
        this.borsa = borsa;
        this.abilitaList = abilitaList;
        this.capacitaList = capacitaList;
    }

    public int getEta() {
        return eta;
    }

    public void setEta(int eta) {
        this.eta = eta;
    }

    public int getLivello() {
        return livello;
    }

    public void setLivello(int livello) {
        this.livello = livello;
    }

    public float getAltezza() {
        return altezza;
    }

    public void setAltezza(float altezza) {
        this.altezza = altezza;
    }

    public Clazza getRazza() {
        return razza;
    }

    public void setRazza(Clazza razza) {
        this.razza = razza;
    }

    public Clazza getClasse() {
        return classe;
    }

    public void setClasse(Clazza classe) {
        this.classe = classe;
    }

    public String getSesso() {
        return sesso;
    }

    public void setSesso(String sesso) {
        this.sesso = sesso;
    }

    public Valuta getPortafoglio() {
        return portafoglio;
    }

    public void setPortafoglio(Valuta portafoglio) {
        this.portafoglio = portafoglio;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public List<StatisticaBase> getStatisticaBaseList() {
        return statisticaBaseList;
    }

    public void setStatisticaBaseList(List<StatisticaBase> statisticaBaseList) {
        this.statisticaBaseList = statisticaBaseList;
    }

    public List<Caratteristica> getCaratteristicaList() {
        return caratteristicaList;
    }

    public void setCaratteristicaList(List<Caratteristica> caratteristicaList) {
        this.caratteristicaList = caratteristicaList;
    }

    public List<Oggetto> getBorsa() {
        return borsa;
    }

    public void setBorsa(List<Oggetto> borsa) {
        this.borsa = borsa;
    }

    public List<Abilita> getAbilitaList() {
        return abilitaList;
    }

    public void setAbilitaList(List<Abilita> abilitaList) {
        this.abilitaList = abilitaList;
    }

    public List<Capacita> getCapacitaList() {
        return capacitaList;
    }

    public void setCapacitaList(List<Capacita> capacitaList) {
        this.capacitaList = capacitaList;
    }

    public int getPuntiEsperienza() {
        return puntiEsperienza;
    }

    public void setPuntiEsperienza(int puntiEsperienza) {
        this.puntiEsperienza = puntiEsperienza;
    }

    public String getAllineamento() {
        return allineamento;
    }

    public void setAllineamento(String allineamento) {
        this.allineamento = allineamento;
    }

    public void levelUp(){
        this.livello ++;
    }

    /* calcola il valoreBase di tutte le statistiche usando razza e classe*/
    public void caratteristicaBase(){
        for(int i = 0; i < caratteristicaList.size(); ++i){
            this.caratteristicaList.get(i).addValoreBase(razza.getStatisticaBaseList().get(i).getValore() + classe.getStatisticaBaseList().get(i).getValore());
        }
    }

    public void addNote(String note){
        this.note = this.note + " " + note;
    }


    /*  for(StatisticheBase stat1: razza.getStatisticheBaseList()){
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
