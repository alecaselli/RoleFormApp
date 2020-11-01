package com.example.myfirstapp.utilities;

public class CardIncantesimo {
    private String nomeincantesimo;
    private Boolean aBoolean;

    public CardIncantesimo(String nomeincantesimo) {
        this.nomeincantesimo = nomeincantesimo;
        this.aBoolean = aBoolean;
    }

    public String getNomeincantesimo() {
        return nomeincantesimo;
    }

    public void setNomeincantesimo(String nomeincantesimo) {
        this.nomeincantesimo = nomeincantesimo;
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
