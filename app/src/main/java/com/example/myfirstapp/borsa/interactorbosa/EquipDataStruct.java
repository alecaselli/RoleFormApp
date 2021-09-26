package com.example.myfirstapp.borsa.interactorbosa;

public class EquipDataStruct {
    private String nome;
    private String tipo;

    public EquipDataStruct(String nome, String tipo) {
        this.nome = nome;
        this.tipo = tipo;
    }

    public String getNome() {
        return nome;
    }

    public String getTipo() {
        return tipo;
    }
}
