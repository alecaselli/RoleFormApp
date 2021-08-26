package com.example.myfirstapp.domain;

import java.util.List;

public class Abilita{
    private String nome;
    private String descrizione;
    private boolean competenza;
    private String nameMod;

    public Abilita(String nome, String descrizione) {
        this.nome = nome;
        this.descrizione = descrizione;
        this.competenza = false;
    }
    public Abilita(String nome, String descrizione, boolean competenza) {
        this.nome = nome;
        this.descrizione = descrizione;
        this.competenza = competenza;
    }


    public Abilita(String nome, StringBuffer descrizione, boolean competenza, String nameMod) {
        this.nome = nome;
        this.descrizione = descrizione.toString();
        this.competenza = competenza;
        this.nameMod=nameMod;
    }

    public void swapCompetenza() {
        this.competenza= !competenza;
    }

    public void setCompetenza(boolean competenza) {
        this.competenza = competenza;
    }

    public boolean isCompetente() {
        return this.competenza;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescrizione() {
        return this.descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }
}
