package com.example.roleapp.domain;

public class StatisticaBase {
    private String nome;
    private Integer valore;

    public StatisticaBase(String nome, Integer valore) {
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
