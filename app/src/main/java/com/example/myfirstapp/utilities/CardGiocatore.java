package com.example.myfirstapp.utilities;

public class CardGiocatore {
    private int mImageResource;
    private String nomecampagna;
    private String nomegiocatore;
    private String livellogiocatore;

    public CardGiocatore(int mImageResource, String nomecampagna, String nomegiocatore, String livellogiocatore) {
        this.mImageResource = mImageResource;
        this.nomecampagna = nomecampagna;
        this.nomegiocatore = nomegiocatore;
        this.livellogiocatore = livellogiocatore;
    }

    public int getmImageResource() {
        return mImageResource;
    }

    public void setmImageResource(int mImageResource) {
        this.mImageResource = mImageResource;
    }

    public String getNomecampagna() {
        return nomecampagna;
    }

    public void setNomecampagna(String nomecampagna) {
        this.nomecampagna = nomecampagna;
    }

    public String getNomegiocatore() {
        return nomegiocatore;
    }

    public void setNomegiocatore(String nomegiocatore) {
        this.nomegiocatore = nomegiocatore;
    }

    public String getLivellogiocatore() {
        return livellogiocatore;
    }

    public void setLivellogiocatore(String livellogiocatore) {
        this.livellogiocatore = livellogiocatore;
    }
}
