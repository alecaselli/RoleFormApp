package com.example.myfirstapp.domain;

import java.util.List;

public class Capacita extends Descrivibile {
    List<StatisticaBase> statisticaBaseList;

    public Capacita(String nome, String descrizione, List<StatisticaBase> statisticaBaseList) {
        super(nome, descrizione);
        this.statisticaBaseList = statisticaBaseList;
    }

    public List<StatisticaBase> getStatisticaBaseList() {
        return statisticaBaseList;
    }

    public void setStatisticaBaseList(List<StatisticaBase> statisticaBaseList) {
        this.statisticaBaseList = statisticaBaseList;
    }
}
