package com.example.myfirstapp.domain;

public class CaratteristicaBase {
    private String nome;
    private Integer valore;

    public CaratteristicaBase(String nome, Integer valore) {
        this.nome = nome;
        this.valore = valore;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getValore() {
        return valore;
    }

    public void setValore(Integer valore) {
        this.valore = valore;
    }
}
