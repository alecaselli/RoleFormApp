package com.example.myfirstapp.domain;

import java.util.HashMap;

public class ValutaTemp{

    private final String nome;
    private final String descrizione;
    private final HashMap<String, Integer> nomiValori;

    public ValutaTemp(String nome, String descrizione, HashMap<String, Integer> nomiValori) {
        this.nome = nome;
        this.descrizione = descrizione;
        this.nomiValori = nomiValori;
    }

    public String getNome() {
        return this.nome;
    }

    public String getDescrizione() {
        return this.descrizione;
    }

    public HashMap<String, Integer> getNomiValori() {
        return nomiValori;
    }

    public void modNomiValori(HashMap<String, Integer> nomiValori) {
        if(null!=this.nomiValori && null!=nomiValori)
            for (String nome: nomiValori.keySet())
                if(null!=this.nomiValori.get(nome) && null!=nomiValori.get(nome))
                    this.nomiValori.put(nome,this.nomiValori.get(nome)+nomiValori.get(nome));
    }
}
