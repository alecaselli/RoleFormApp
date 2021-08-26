package com.example.myfirstapp.domain;


import java.util.List;

public class Privilegi {
    private String nome;
    private StringBuffer descrizione;

    public Privilegi(String nome, StringBuffer descrizione) {
        this.nome = nome;
        this.descrizione = descrizione;
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

    public void aggiungiDescrizione(String nuova) {
        this.descrizione.append(nuova);
    }
}
