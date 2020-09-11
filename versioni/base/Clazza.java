package com.example.myfirstapp.domain;

import java.util.List;

public class Clazza extends Descrivibile {
    private boolean razza; //true = razza, false = classe
    private String taglia;
    private int velocita;
    private List<StatisticaBase> statisticaBaseList;
    private List<Capacita> capacitaList;
    private List<Abilita> abilitaList;

    public Clazza(String nome, String descrizione, boolean razza, String taglia, int velocita, List<StatisticaBase> statisticaBaseList, List<Capacita> capacitaList, List<Abilita> abilitaList) {
        super(nome, descrizione);
        this.razza = razza;
        this.taglia = taglia;
        this.velocita = velocita;
        this.statisticaBaseList = statisticaBaseList;
        this.capacitaList = capacitaList;
        this.abilitaList = abilitaList;
    }

    public boolean isRazza() {
        return razza;
    }

    public void setRazza(boolean razza) {
        this.razza = razza;
    }

    public String getTaglia() {
        return taglia;
    }

    public void setTaglia(String taglia) {
        this.taglia = taglia;
    }

    public int getVelocita() {
        return velocita;
    }

    public void setVelocita(int velocita) {
        this.velocita = velocita;
    }

    public List<StatisticaBase> getStatisticaBaseList() {
        return statisticaBaseList;
    }

    public void setStatisticaBaseList(List<StatisticaBase> statisticaBaseList) {
        this.statisticaBaseList = statisticaBaseList;
    }

    public List<Capacita> getCapacitaList() {
        return capacitaList;
    }

    public void setCapacitaList(List<Capacita> capacitaList) {
        this.capacitaList = capacitaList;
    }

    public List<Abilita> getAbilitaList() {
        return abilitaList;
    }

    public void setAbilitaList(List<Abilita> abilitaList) {
        this.abilitaList = abilitaList;
    }
}
