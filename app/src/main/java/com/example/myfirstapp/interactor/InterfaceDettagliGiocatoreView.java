package com.example.myfirstapp.interactor;

public interface InterfaceDettagliGiocatoreView extends InterfaceErrorView{
    void setFisionomia(String nome, String genere, String altezza, String eta);
    void setRazza(String nome, String descrizione);
    void setClasse(String nome, String descrizione);
    void setClasseArmatura(int classeArmatura);
}
