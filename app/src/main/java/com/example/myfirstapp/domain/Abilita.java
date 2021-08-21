package com.example.myfirstapp.domain;

import java.util.List;

public class Abilita extends Descrivibile {
    private boolean competenza;
    private String nameMod;

    public Abilita(String nome, StringBuffer descrizione) {
        super(nome, descrizione);
        this.competenza = false;
    }

    public Abilita(String nome, StringBuffer descrizione, boolean competenza, String nameMod) {
        super(nome, descrizione);
        this.competenza = competenza;
        this.nameMod=nameMod;
    }

    public boolean isCompetenza() {
        return competenza;
    }

    public void setCompetenza(boolean competenza) {
        this.competenza = competenza;
    }

}
