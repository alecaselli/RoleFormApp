package com.example.myfirstapp.domain;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class Valuta extends Descrivibile {
    private int ratio;
    private int valore;
    private List<String> nomelist; //in ordine crescente

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

    public int getValore() {
        return valore;
    }

    public void setValore(int valore) {
        this.valore = valore;
    }

    /* ritorna valora come lista di unità in cui il valore è suddiviso in ordine crescente di valore; il ratio è definito da ratio; */
    public List<Integer> getValoreInMonete() {
        int val = valore;
        List<Integer> valoreInMonete = new ArrayList<Integer>();
        for (String i : nomelist.subList(0, nomelist.size() - 1)) {
            valoreInMonete.add(val % ratio);
            val /= ratio;
        }
        valoreInMonete.add(val);
        return valoreInMonete;
    }

    /* prendo una lista di monete contenenti ciascuno il numero di monete di quel tipo da aggiungere al portafoglio
       la lista parte dalla moneta più piccola; calcolo il valore della lista in termini di numero di monete di valore
       inferiore e sommo al portafoglio*/
    public void aggiornaValore(@NotNull List<Integer> valorelist) {
        if (valorelist.size() == nomelist.size()) {
            int rat = 1;
            for (Integer val : valorelist) {
                this.valore += val * rat;
                rat *= this.ratio;
            }
        }
    }

}
