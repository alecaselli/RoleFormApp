package com.example.myfirstapp.domain;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ValutaOld {
    private String nome;
    private StringBuffer descrizione;
    private int ratio;
    private int valore;
    private List<String> nomelistOrdineCrescente;

    public ValutaOld(String nome, StringBuffer descrizione, int ratio, int valore, List<String> nomelist) {
        this.nome = nome;
        this.descrizione = descrizione;
        this.ratio = ratio;
        this.valore = valore;
        this.nomelistOrdineCrescente = nomelist;
    }

    public List<String> getNomelist() {
        return nomelistOrdineCrescente;
    }

    public void setNomelist(List<String> nomelist) {
        this.nomelistOrdineCrescente = nomelist;
    }

    public int getRatio() {
        return ratio;
    }

    public void setRatio(int ratio) {
        this.ratio = ratio;
    }

    public int getValore() {
        return valore;
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

    public void setValore(int valore) {
        this.valore = valore;
    }

    public List<Integer> getValoreInMonete() {
        int val = valore;
        List<Integer> valoreInMoneteOrdineCrescente = new ArrayList<Integer>();
        for (String i : nomelistOrdineCrescente) {
            valoreInMoneteOrdineCrescente.add(val % ratio);
            val /= ratio;
        }
        valoreInMoneteOrdineCrescente.add(val);
        return valoreInMoneteOrdineCrescente;
    }

    /* prendo una lista di monete contenenti ciascuno il numero di monete di quel tipo da aggiungere al portafoglio
       la lista parte dalla moneta più piccola;
       calcolo il valore della lista in termini di numero di monete
       di valore inferiore e sommo al portafoglio*/
    public void aggiornaValore(@NotNull List<Integer> valorelist) {
        if (valorelist.size() == nomelistOrdineCrescente.size()) {
            int rat = 1;
            for (Integer val : valorelist) {
                this.valore += val * rat;
                rat *= this.ratio;
            }
        }
    }

}
