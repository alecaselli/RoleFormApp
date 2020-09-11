package com.example.roleapp.domain;

import java.util.List;

public class Abilita {
    private boolean competenza;
    /*private int livello = 0;*/
    private String nome;
    private String descrizione;

    public Abilita(boolean competenza, String nome, String descrizione) {
        this.competenza = competenza;
        this.nome = nome;
        this.descrizione = descrizione;
    }

    public boolean isCompetenza() {
        return competenza;
    }

    public void setCompetenza(boolean competenza) {
        this.competenza = competenza;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }
/*
    public int getLivello() {
        return livello;
    }

    public void setLivello(int livello) {
        this.livello = livello;
    }

    public void levelUp(){
        this.livello ++ ;
    }*/
}
