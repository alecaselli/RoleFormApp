<<<<<<< Updated upstream
package com.example.myfirstapp.domain;
=======
package com.example.myfirstapp.domainn;
>>>>>>> Stashed changes

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class Valuta {
    private List<String> nomelist;
    private int  ratio;
    private int valore;

    public Valuta(List<String> nomelist, int ratio, int valore) {
        this.nomelist = nomelist;
        this.ratio = ratio;
        this.valore = valore;
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
    public List<Integer> valoreInMonete() {
        List<Integer> valorelist = new ArrayList<Integer>();
        Integer val = valore;
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
