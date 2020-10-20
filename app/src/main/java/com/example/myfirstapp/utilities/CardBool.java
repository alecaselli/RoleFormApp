package com.example.myfirstapp.utilities;

public class CardBool {
    private String nome;
    private Boolean aBoolean;

    public CardBool(String nome, Boolean aBoolean) {
        this.nome = nome;
        this.aBoolean = aBoolean;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Boolean getaBoolean() {
        return aBoolean;
    }

    public void setaBoolean(Boolean aBoolean) {
        this.aBoolean = aBoolean;
    }

    /* METODI NON STANDARD */
    public void swapBool() {
        aBoolean = !aBoolean;
    }

}
