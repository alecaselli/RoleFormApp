package com.example.myfirstapp.domain;

public class Equipaggiamento extends Descrivibile{
    private String tipo;
    private int costo;
    private int peso;
    private int capacita;

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
}
