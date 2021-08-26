package com.example.myfirstapp.domain;

import java.util.List;

public class Incantesimo{
    private String nome;
    private StringBuffer descrizione;
    private int livello;
    private String tempoInvocazione;
    private String raggioAzione;
    private String componenti;
    private String durata;
    //private String elemento;

    public Incantesimo(String nome, StringBuffer descrizione, String tempoInvocazione, String raggioAzione, String componenti, String durata, int livello) {
        this.nome = nome;
        this.descrizione = descrizione;
        this.tempoInvocazione = tempoInvocazione;
        this.raggioAzione = raggioAzione;
        this.componenti = componenti;
        this.durata = durata;
        this.livello = livello;
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

    public String getTempoInvocazione() {
        return tempoInvocazione;
    }

    public void setTempoInvocazione(String tempoInvocazione) {
        this.tempoInvocazione = tempoInvocazione;
    }

    public String getRaggioAzione() {
        return raggioAzione;
    }

    public void setRaggioAzione(String raggioAzione) {
        this.raggioAzione = raggioAzione;
    }

    public String getComponenti() {
        return componenti;
    }

    public void setComponenti(String componenti) {
        this.componenti = componenti;
    }

    public String getDurata() {
        return durata;
    }

    public void setDurata(String durata) {
        this.durata = durata;
    }

    public int getLivello() {
        return livello;
    }

    public void setLivello(int livello) {
        this.livello = livello;
    }
}
