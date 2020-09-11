package com.example.roleapp.domain;

import java.util.List;

public class Razza extends Descrivibile {
    private String taglia;
    private String velocita;
    private StringBuffer linguaggio;
    private List<Descrivibile> privilegiRazza;
    private List<CaratteristicaBase> caratteristicaBaseList;

    public Razza(String nome,
                 StringBuffer descrizione,
                 String taglia,
                 String velocita,
                 StringBuffer linguaggio,
                 List<Descrivibile> privilegiRazza,
                 List<CaratteristicaBase> caratteristicaBaseList) {
        super(nome, descrizione);
        this.taglia = taglia;
        this.velocita = velocita;
        this.linguaggio = linguaggio;
        this.privilegiRazza = privilegiRazza;
        this.caratteristicaBaseList = caratteristicaBaseList;
    }

    public String getTaglia() {
        return taglia;
    }

    public void setTaglia(String taglia) {
        this.taglia = taglia;
    }

    public String getVelocita() {
        return velocita;
    }

    public void setVelocita(String velocita) {
        this.velocita = velocita;
    }

    public StringBuffer getLinguaggio() {
        return linguaggio;
    }

    public void setLinguaggio(StringBuffer linguaggio) {
        this.linguaggio = linguaggio;
    }

    public List<Descrivibile> getPrivilegiRazza() {
        return privilegiRazza;
    }

    public void setPrivilegiRazza(List<Descrivibile> privilegiRazza) {
        this.privilegiRazza = privilegiRazza;
    }

    public List<CaratteristicaBase> getCaratteristicaBaseList() {
        return caratteristicaBaseList;
    }

    public void setCaratteristicaBaseList(List<CaratteristicaBase> caratteristicaBaseList) {
        this.caratteristicaBaseList = caratteristicaBaseList;
    }
}
