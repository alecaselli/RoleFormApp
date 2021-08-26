package com.example.myfirstapp.domain;

import java.util.*;

public class Equipaggiamento{
    private String nome;
    private StringBuffer descrizione;
    static final List<String> subtipobase = new ArrayList<String>(Arrays.asList("arma da guerra a distanza", "arma da guerra da mischia", "arma semplice a distanza", "arma semplice da mischia", "armatura leggera", "armatura media", "armatura pesante", "attrezzo", "equipaggiamento da avventura", "scudo"));
    static final List<String> tipobase = new ArrayList<String>(Arrays.asList("arma", "armatura", "scudo", "oggetto"));
    private int costo;
    private int peso;
    private int capacita;
    private String tipo;
    private String subtipo;

    public Equipaggiamento(String nome, StringBuffer descrizione, String tipo, int costo, int peso, int capacita, String subtipo) {
        this.nome = nome;
        this.descrizione = descrizione;
        this.tipo = tipo;
        this.costo = costo;
        this.peso = peso;
        this.capacita = capacita;
        this.subtipo = subtipo;
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getSubtipo() {
        return subtipo;
    }

    public void setSubtipo(String subtipo) {
        this.subtipo = subtipo;
    }

    public static List<String> getSubtipobase() {
        return subtipobase;
    }

    public static List<String> getTipobase() {
        return tipobase;
    }
}
