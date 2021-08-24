package com.example.myfirstapp.domain;

import java.util.HashMap;

public class ValutaTemp{

    private final String nome;
    private final String descrizione;
    private final HashMap<String, Integer> nomiRatio;
    private HashMap<String, Integer> nomiValori;

    public ValutaTemp(String nome, String descrizione, HashMap<String, Integer> nomiRatio) {
        this.nome = nome;
        this.descrizione = descrizione;
        this.nomiRatio=nomiRatio;
    }

    public String getNome() {
        return this.nome;
    }

    public String getDescrizione() {
        return this.descrizione;
    }

    public HashMap<String, Integer> getNomiValori() {
        return nomiRatio;
    }

    public void modNomiValori(HashMap<String, Integer> nomiValori) {
        if(null!=this.nomiRatio && null!=nomiValori)
            for (String nome: nomiValori.keySet())
                if((null != this.nomiRatio.get(nome)) && ( nomiValori.get(nome)!= null)){
                    int val=this.nomiRatio.get(nome)+nomiValori.get(nome);
                    this.nomiRatio.put(nome,val);
                }
    }
}
