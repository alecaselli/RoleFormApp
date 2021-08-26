package com.example.myfirstapp.domain;


import java.util.List;

public class Classe{
    private String nome;
    private StringBuffer descrizione;
    private int nDadi;
    private int dado;
    private StringBuffer descrizionePrivilegiPoteri; // in alternativa imagine con tab del manuale
    private StringBuffer competenza;
    /*  private Competenza competenzaClasse;*/
    private List<Equipaggiamento> equipaggiamentoList;
    private List<Privilegi> privilegiClasse;
    private List<Incantesimo> incantesimiClasse;

    public Classe(String nome,
                  StringBuffer descrizione,
                  StringBuffer descrizionePrivilegiPoteri,
            /*Competenza competenzaClasse,*/
                  int nDadi, int dado, StringBuffer competenza, List<Equipaggiamento> equipaggiamentoList,
                  List<Privilegi> privilegiClasse,
                  List<Incantesimo> incantesimiClasse) {
        this.nome = nome;
        this.descrizione = descrizione;
        this.descrizionePrivilegiPoteri = descrizionePrivilegiPoteri;
        this.nDadi = nDadi;
        this.dado = dado;

        this.competenza = competenza;
        /*this.competenzaClasse = competenzaClasse;*/
        this.equipaggiamentoList = equipaggiamentoList;
        this.privilegiClasse = privilegiClasse;
        this.incantesimiClasse = incantesimiClasse;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public StringBuffer getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(StringBuffer descrizione) {
        this.descrizione = descrizione;
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

    public void setDescrizionePrivilegiPoteri(StringBuffer descrizionePrivilegiPoteri) {
        this.descrizionePrivilegiPoteri = descrizionePrivilegiPoteri;
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

    public List<Privilegi> getPrivilegiClasse() {
        return privilegiClasse;
    }

    public void setPrivilegiClasse(List<Privilegi> privilegiClasse) {
        this.privilegiClasse = privilegiClasse;
    }

    public List<Incantesimo> getIncantesimiClasse() {
        return incantesimiClasse;
    }

    public void setIncantesimiClasse(List<Incantesimo> incantesimiClasse) {
        this.incantesimiClasse = incantesimiClasse;
    }
}
