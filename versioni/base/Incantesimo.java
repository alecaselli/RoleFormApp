package com.example.roleapp.domain;

import java.util.List;

public class Incantesimo extends Abilita {
    private int livello;
    private String elemento;
    private List<Integer> tempoAttivazione;
    private List<Integer> raggioAzione;

    public Incantesimo(String nome, List<String> descrizione, int livello, String elemento, List<Integer> tempoAttivazione, List<Integer> raggioAzione) {
        super(nome, descrizione);
        this.livello = livello;
        this.elemento = elemento;
        this.tempoAttivazione = tempoAttivazione;
        this.raggioAzione = raggioAzione;
    }

    public int getLivello() {
        return livello;
    }

    public void setLivello(int livello) {
        this.livello = livello;
    }

    public String getElemento() {
        return elemento;
    }

    public void setElemento(String elemento) {
        this.elemento = elemento;
    }

    public List<Integer> getTempoAttivazione() {
        return tempoAttivazione;
    }

    public void setTempoAttivazione(List<Integer> tempoAttivazione) {
        this.tempoAttivazione = tempoAttivazione;
    }

    public List<Integer> getRaggioAzione() {
        return raggioAzione;
    }

    public void setRaggioAzione(List<Integer> raggioAzione) {
        this.raggioAzione = raggioAzione;
    }
}
