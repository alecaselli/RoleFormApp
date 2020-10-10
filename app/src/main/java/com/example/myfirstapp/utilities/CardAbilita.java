package com.example.myfirstapp.utilities;

public class CardAbilita {
    private String nomeabilita;
    private Boolean competenza;

    public CardAbilita(String nomeabilita, Boolean acquisita) {
        this.nomeabilita = nomeabilita;
        this.competenza = acquisita;
    }

    public String getNomeabilita() {
        return nomeabilita;
    }

    public void setNomeabilita(String nomeabilita) {
        this.nomeabilita = nomeabilita;
    }

    public Boolean getCompetenza() {
        return competenza;
    }

    public void setCompetenza(Boolean competenza) {
        this.competenza = competenza;
    }

    /* METODI NON STANDARD */
    public void changeAcquired() {
        competenza = !competenza;
    }

}
