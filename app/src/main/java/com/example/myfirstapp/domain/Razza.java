package com.example.myfirstapp.domain;

import java.util.List;

public class Razza {
    private String nome;
    private StringBuffer descrizione;
    private String taglia;
    private String velocita;
    private StringBuffer lingua;
    private List<Privilegi> privilegiRazza;
    private List<CaratteristicaBase> caratteristicaBaseList;

    public Razza(String nome, StringBuffer descrizione, String taglia, String velocita, StringBuffer linguaggio, List<Privilegi> privilegiRazza, List<CaratteristicaBase> caratteristicaBaseList) {
        this.nome = nome;
        this.descrizione = descrizione;
        this.taglia = taglia;
        this.velocita = velocita;
        this.lingua = linguaggio;
        this.privilegiRazza = privilegiRazza;
        this.caratteristicaBaseList = caratteristicaBaseList;
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public StringBuffer getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(StringBuffer descrizione) {
        this.descrizione = descrizione;
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

    public StringBuffer getLingua() {
        return lingua;
    }

    public void setLingua(StringBuffer lingua) {
        this.lingua = lingua;
    }

    public List<Privilegi> getPrivilegiRazza() {
        return privilegiRazza;
    }

    public void setPrivilegiRazza(List<Privilegi> privilegiRazza) {
        this.privilegiRazza = privilegiRazza;
    }

    public List<CaratteristicaBase> getCaratteristicaBaseList() {
        return caratteristicaBaseList;
    }

    public void setCaratteristicaBaseList(List<CaratteristicaBase> caratteristicaBaseList) {
        this.caratteristicaBaseList = caratteristicaBaseList;
    }

    /* METODI NON BASE */
    public CaratteristicaBase getCaratteristicaBase(String nomec) {
        for (CaratteristicaBase caratteristicabase : this.getCaratteristicaBaseList()) {
            if (caratteristicabase.getNome().equals(nomec))
                return caratteristicabase;
        }
        return null;
    }
}
