package com.example.myfirstapp.domain;

import java.util.ArrayList;
import java.util.List;

public class Valuta {
    private List<String> nomelist;
    private List<Integer> ratiolist;
    private Integer valore = 0;

    public Valuta(List<String> nomelist, List<Integer> ratiolist) {
        this.nomelist = nomelist;
        this.ratiolist = ratiolist;
    }

    public List<String> getNomelist() {
        return nomelist;
    }

    public void setNomelist(List<String> nomelist) {
        this.nomelist = nomelist;
    }

    public List<Integer> getRatiolist() {
        return ratiolist;
    }

    public void setRatiolist(List<Integer> ratiolist) {
        this.ratiolist = ratiolist;
    }

    public Integer getValore() {
        return valore;
    }

    public void setValore(Integer valore) {
        this.valore = valore;
    }

    public List<Integer> valoreInMonete() {  //restituisce il valore come una collection di interi ognuno una moneta, partendo da quella di valore inferiore
        List<Integer> valorelist = new ArrayList<Integer>();
        Integer val = valore;
        for(Integer i : ratiolist){
            valorelist.add(val % i);  //i ratio sono dal minore al maggiore
            val /= i;
        }
        return valorelist;
    }

    public void aggiornaValore(List<Integer> valorelist) {
        for(Integer i : ratiolist)  /*int i = 0 ; i < val.size() ; ++i*/
            this.valore += valorelist.get(ratiolist.indexOf(i)) * i;
    }
}
