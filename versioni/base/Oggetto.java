package com.example.myfirstapp.domain;

import java.util.List;

public class Oggetto extends Descrivibile{
    private List<StatisticaBase> statisticaBaseList;
    private List<Character> equipaggiato;
    private int dimensione;
    private List<Abilita> abilitaList;

    public Oggetto(String nome, String descrizione, List<StatisticaBase> statisticaBaseList, List<Character> equipaggiato, int dimensione, List<Abilita> abilitaList) {
        super(nome, descrizione);
        this.statisticaBaseList = statisticaBaseList;
        this.equipaggiato = equipaggiato;
        this.dimensione = dimensione;
        this.abilitaList = abilitaList;
    }

    public List<StatisticaBase> getStatisticaBaseList() {
        return statisticaBaseList;
    }

    public void setStatisticaBaseList(List<StatisticaBase> statisticaBaseList) {
        this.statisticaBaseList = statisticaBaseList;
    }

    public List<Character> getEquipaggiato() {
        return equipaggiato;
    }

    public void setEquipaggiato(List<Character> equipaggiato) {
        this.equipaggiato = equipaggiato;
    }

    public int getDimensione() {
        return dimensione;
    }

    public void setDimensione(int dimensione) {
        this.dimensione = dimensione;
    }

    public List<Abilita> getAbilitaList() {
        return abilitaList;
    }

    public void setAbilitaList(List<Abilita> abilitaList) {
        this.abilitaList = abilitaList;
    }
}
