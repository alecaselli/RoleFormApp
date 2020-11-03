package com.example.myfirstapp.utilities;

public class CardEquip {
    private String nome;
    private String tipo;
    private Boolean equipaggiato;

    public CardEquip(String nome, String tipo, Boolean equipaggiato) {
        this.nome = nome;
        this.tipo = tipo;
        this.equipaggiato = equipaggiato;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Boolean getEquipaggiato() {
        return equipaggiato;
    }

    public void setEquipaggiato(Boolean equipaggiato) {
        this.equipaggiato = equipaggiato;
    }

    /* METODI NON STANDARD */
    public void swapEquipaggiato() {
        equipaggiato = !equipaggiato;
    }
}
