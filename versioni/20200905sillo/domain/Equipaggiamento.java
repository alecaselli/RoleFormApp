package com.example.roleapp.domain;

import java.util.*;

public class Equipaggiamento extends Descrivibile{
    private static final List<String> tipolist = new ArrayList<String>(Arrays.asList("arma da guerra a distanza",
            "arma da guerra da mischia",
            "arma semplice a distanza",
            "arma semplice da mischia",
            "armatura leggera",
            "armatura media",
            "armatura pesante",
            "attrezzo",
            "equipaggiamento da avventura",
            "scudo"));
    private int costo;
    private int peso;
    private int capacita;
    private String tipo;

    public Equipaggiamento(String nome, StringBuffer descrizione, String tipo, int costo, int peso, int capacita) {
        super(nome, descrizione);
        this.tipo = tipo;
        this.costo = costo;
        this.peso = peso;
        this.capacita = capacita;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getCosto() {
        return costo;
    }

    public void setCosto(int costo) {
        this.costo = costo;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public int getCapacita() {
        return capacita;
    }

    public void setCapacita(int capacita) {
        this.capacita = capacita;
    }

    public static List<String> getTipolist() {
        return tipolist;
    }
}
