/*
    implementazione postuma
*/




package com.example.myfirstapp.alternative;

public class Competenza {
    private StringBuffer armatura;
    private StringBuffer arma;
    private StringBuffer attrezzo;
    private StringBuffer tiroSalvezza;
    private StringBuffer abilita;

    public Competenza(StringBuffer armatura, StringBuffer arma, StringBuffer attrezzo, StringBuffer tiroSalvezza, StringBuffer abilita) {
        this.armatura = armatura;
        this.arma = arma;
        this.attrezzo = attrezzo;
        this.tiroSalvezza = tiroSalvezza;
        this.abilita = abilita;
    }

    public StringBuffer getArmatura() {
        return armatura;
    }

    public void setArmatura(StringBuffer armatura) {
        this.armatura = armatura;
    }

    public StringBuffer getArma() {
        return arma;
    }

    public void setArma(StringBuffer arma) {
        this.arma = arma;
    }

    public StringBuffer getAttrezzo() {
        return attrezzo;
    }

    public void setAttrezzo(StringBuffer attrezzo) {
        this.attrezzo = attrezzo;
    }

    public StringBuffer getTiroSalvezza() {
        return tiroSalvezza;
    }

    public void setTiroSalvezza(StringBuffer tiriSalvezza) {
        this.tiroSalvezza = tiriSalvezza;
    }

    public StringBuffer getAbilita() {
        return abilita;
    }

    public void setAbilita(StringBuffer abilita) {
        this.abilita = abilita;
    }
}
