package com.example.myfirstapp.domain;

import java.util.List;

public class Abilita extends Descrivibile {
    private boolean competenza;

    public Abilita(String nome, StringBuffer descrizione) {
        super(nome, descrizione);
        this.competenza = false;
    }

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

}
