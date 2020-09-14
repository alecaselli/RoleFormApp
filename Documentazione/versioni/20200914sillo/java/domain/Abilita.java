package com.example.myfirstapp.domain;

import java.util.List;

public class Abilita extends Descrivibile{
    private boolean competenza;
    /*private int livello = 0;*/

    public Abilita(String nome, StringBuffer descrizione, boolean competenza) {
        super(nome, descrizione);
        this.competenza = competenza;
    }

    public boolean isCompetenza() {
        return competenza;
    }

    public void setCompetenza(boolean competenza) {
        this.competenza = competenza;
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
