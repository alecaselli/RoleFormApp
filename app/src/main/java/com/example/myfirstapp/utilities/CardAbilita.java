package com.example.myfirstapp.utilities;

import androidx.cardview.widget.CardView;

import com.example.myfirstapp.R;

public class CardAbilita {
    private String nomeabilita;
    private Boolean acquisita;

    public CardAbilita(String nomeabilita, Boolean acquisita) {
        this.nomeabilita = nomeabilita;
        this.acquisita = acquisita;
    }

    public void changeAcquired() {
        acquisita = !acquisita;
    }

    public String getNomeabilita() {
        return nomeabilita;
    }

    public Boolean getAcquisita() {
        return acquisita;
    }

    public void setNomeabilita(String nomeabilita) {
        this.nomeabilita = nomeabilita;
    }

    public void setAcquisita(Boolean acquisita) {
        this.acquisita = acquisita;
    }


}
