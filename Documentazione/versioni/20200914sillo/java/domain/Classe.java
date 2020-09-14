package com.example.myfirstapp.domain;


import java.util.List;

public class Classe extends Descrivibile {
    private int nDadi;
    private int dado;
    private StringBuffer descrizionePrivilegiPoteri; // in alternativa imagine con tab del manuale
    private StringBuffer competenza;
  /*  private Competenza competenzaClasse;*/
    private List<Equipaggiamento> equipaggiamentoList;
    private List<Descrivibile> privilegiClasse;
    private List<Incantesimo> incantesimiClasse;

    public Classe(String nome,
                  StringBuffer descrizione,
                  StringBuffer descrizionePrivilegiPoteri,
                  /*Competenza competenzaClasse,*/
                  int nDadi, int dado, StringBuffer competenza, List<Equipaggiamento> equipaggiamentoList,
                  List<Descrivibile> privilegiClasse,
                  List<Incantesimo> incantesimiClasse) {
        super(nome, descrizione);
        this.descrizionePrivilegiPoteri = descrizionePrivilegiPoteri;
        this.nDadi = nDadi;
        this.dado = dado;

        this.competenza = competenza;
        /*this.competenzaClasse = competenzaClasse;*/
        this.equipaggiamentoList = equipaggiamentoList;
        this.privilegiClasse = privilegiClasse;
        this.incantesimiClasse = incantesimiClasse;
    }

    public int getnDadi() {
        return nDadi;
    }

    public void setnDadi(int nDadi) {
        this.nDadi = nDadi;
    }

    public int getDado() {
        return dado;
    }

    public void setDado(int dado) {
        this.dado = dado;
    }

    public StringBuffer getDescrizionePrivilegiPoteri() {
        return descrizionePrivilegiPoteri;
    }

    public void setDescrizionePrivilegiPoteri(StringBuffer descrizioneElementiParticolari) {
        this.descrizionePrivilegiPoteri = descrizioneElementiParticolari;
    }

    public StringBuffer getCompetenza() {
        return competenza;
    }

    public void setCompetenza(StringBuffer competenza) {
        this.competenza = competenza;
    }

    /*
    public Competenza getCompetenzaClasse() {
        return competenzaClasse;
    }

    public void setCompetenzaClasse(Competenza competenzaClasse) {
        this.competenzaClasse = competenzaClasse;
    }*/

    public List<Equipaggiamento> getEquipaggiamentoList() {
        return equipaggiamentoList;
    }

    public void setEquipaggiamentoList(List<Equipaggiamento> equipaggiamentoList) {
        this.equipaggiamentoList = equipaggiamentoList;
    }

    public List<Descrivibile> getPrivilegiClasse() {
        return privilegiClasse;
    }

    public void setPrivilegiClasse(List<Descrivibile> privilegiClasse) {
        this.privilegiClasse = privilegiClasse;
    }

    public List<Incantesimo> getIncantesimiClasse() {
        return incantesimiClasse;
    }

    public void setIncantesimiClasse(List<Incantesimo> incantesimiClasse) {
        this.incantesimiClasse = incantesimiClasse;
    }
}
