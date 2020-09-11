package com.example.roleapp.domain;

import java.util.List;

public class Abilita {
    private String nome;
    private List<String> descrizione;
    private int livello = 0;

    public Abilita(String nome, List<String> descrizione) {
        this.nome = nome;
        this.descrizione = descrizione;
    }

    public void levelUp(){
        this.livello ++ ;
    }
}
