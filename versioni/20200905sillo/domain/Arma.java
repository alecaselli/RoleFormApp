package com.example.roleapp.domain;

public class Arma extends Equipaggiamento {
    private String danno;
    private String proprieta;

    public Arma(String nome, StringBuffer descrizione, String tipo, int costo,int capacita, int peso, String danno, String proprieta) {
        super(nome, descrizione, tipo, costo, peso, capacita);
        this.danno = danno;
        this.proprieta = proprieta;
    }

    public String getDanno() {
        return danno;
    }

    public void setDanno(String danno) {
        this.danno = danno;
    }

    public String getProprieta() {
        return proprieta;
    }

    public void setProprieta(String proprieta) {
        this.proprieta = proprieta;
    }
}
