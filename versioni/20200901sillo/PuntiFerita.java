package com.example.myfirstapp.domain;

public class PuntiFerita {
    private int puntiFerita;
    private int puntiFeritaMax;
    private int nDadi;
    private int dado;

    public PuntiFerita(int puntiFerita, int puntiFeritaMax, int nDadi, int dado) {
        this.puntiFerita = puntiFerita;
        this.setPuntiFeritaMax();
        this.nDadi = nDadi;
        this.dado = dado;
    }

    public int getPuntiFerita() {
        return puntiFerita;
    }

    public void setPuntiFerita(int puntiFerita) {
        this.puntiFerita = puntiFerita;
    }

    public int getPuntiFeritaMax() {
        return puntiFeritaMax;
    }

    public void setPuntiFeritaMax() {
        this.puntiFeritaMax = this.nDadi * this.dado;
    }

    public int getnDadi() {
        return nDadi;
    }

    public void setnDadi(int nDadi) {
        this.nDadi = nDadi;
    }

    public int getDado() {
        return dado;
    }

    public void setDado(int dado) {
        this.dado = dado;
    }

    public void dannoGuarigione(int danno){
        this.puntiFerita += danno;
    }

    public void levelUp(int livello){
        this.nDadi += livello;
    }
}
