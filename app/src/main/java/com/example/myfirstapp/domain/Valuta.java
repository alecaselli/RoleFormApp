package com.example.myfirstapp.domain;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class Valuta extends Descrivibile{
    private int  ratio;
    private int valore;
    private List<String> nomelist; //in ordine decrescente di valore
    private List<Integer> valorelist;

    public Valuta(String nome, StringBuffer descrizione, int ratio, int valore, List<String> nomelist) {
        super(nome, descrizione);
        this.ratio = ratio;
        this.valore = valore;
        this.nomelist = nomelist;
    }

    public List<String> getNomelist() {
        return nomelist;
    }

    public void setNomelist(List<String> nomelist) {
        this.nomelist = nomelist;
    }

    public int getRatio() {
        return ratio;
    }

    public void setRatio(int ratio) {
        this.ratio = ratio;
    }

    public Integer getValore() {
        return valore;
    }

    public void setValore(Integer valore) {
        this.valore = valore;
    }

    /* restituisce il valore di portafoglio espresso in monete
       urilizza una lista che parte dalla moneta di valore inferiore*/
    public List<Integer> getValoreInMoneta() {
        int val = valore;
        for(String i : nomelist){
            valorelist.add(val % ratio);
            val /= ratio;
        }
        return valorelist;
    }

    /* prendo una lista di monete contenenti ciascuno il numero di monete di quel tipo da aggiungere al portafoglio
       la lista parte dalla moneta più piccola; calcolo il valore della lista in termini di numero di monete di valore
       inferiore e sommo al portafoglio*/
    public void aggiornaValore(@NotNull List<Integer> valorelist) {
        int rat = 1;
        for(Integer val : valorelist){
            this.valore += val*rat;
            rat *= this.ratio;
        }
    }

}
