package com.example.myfirstapp.domain;

import java.util.List;

public class Descrivibile { // i privilegi sono oggetti di descrivibile
    String nome;
    StringBuffer descrizione;

    public Descrivibile(String nome, StringBuffer descrizione) {
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
}
