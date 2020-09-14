package com.example.myfirstapp.domain;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class Valuta extends Descrivibile {
    private int ratio;
    private int valore;
    private List<String> nomelist; //in ordine crescente
    private List<Integer> valoreInMonete; //i valori della valuta in monete di taglio nomelist e ratio this.ratio ordine crescente

    public Valuta(String nome, StringBuffer descrizione, int ratio, int valore, List<String> nomelist) {
        super(nome, descrizione);
        this.ratio = ratio;
        this.valore = valore;
        this.nomelist = nomelist;
        this.setValoreInMonete();
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

    public int getValore() {
        return valore;
    }

    public void setValore(int valore) {
        this.valore = valore;
    }

    public void setValoreInMonete() {
        int val = valore;
        this.valoreInMonete = new ArrayList<Integer>();
        for (String i : nomelist) {
            this.valoreInMonete.add(val % ratio);
            val /= ratio;
        }
    }

    public List<Integer> getValoreInMonete() {
        return valoreInMonete;
    }
    /* restituisce il valore di portafoglio espresso in monete
               urilizza una lista che parte dalla moneta di valore inferiore*/


    /* prendo una lista di monete contenenti ciascuno il numero di monete di quel tipo da aggiungere al portafoglio
       la lista parte dalla moneta più piccola; calcolo il valore della lista in termini di numero di monete di valore
       inferiore e sommo al portafoglio*/
    public void aggiornaValore(@NotNull List<Integer> valorelist) {
        int rat = 1;
        for (Integer val : valorelist) {
            this.valore += val * rat;
            rat *= this.ratio;
        }
    }

}
