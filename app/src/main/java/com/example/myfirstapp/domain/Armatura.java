package com.example.myfirstapp.domain;

public class Armatura extends EquipaggiamentoOld {
    private boolean nonFurtiva;
    private int modificatoreCA;
    private String tempoTogliere;
    private String tempoIndossare;
    private String forzaNecessaria;

    public Armatura(String nome, StringBuffer descrizione, String tipo, int costo, int peso, int capacita, boolean nonFurtiva, int modificatoreCA, String tempoTogliere, String tempoIndossare, String forzaNecessaria, String subtipo) {
        super(nome, descrizione, tipo, costo, peso, capacita, subtipo);
        this.nonFurtiva = nonFurtiva;
        this.modificatoreCA = modificatoreCA;
        this.tempoTogliere = tempoTogliere;
        this.tempoIndossare = tempoIndossare;
        this.forzaNecessaria = forzaNecessaria;
    }

    public boolean isNonFurtiva() {
        return nonFurtiva;
    }

    public void setNonFurtiva(boolean nonFurtiva) {
        this.nonFurtiva = nonFurtiva;
    }

    public int getModificatoreCA() {
        return modificatoreCA;
    }

    public void setModificatoreCA(int modificatoreCA) {
        this.modificatoreCA = modificatoreCA;
    }

    public String getTempoTogliere() {
        return tempoTogliere;
    }

    public void setTempoTogliere(String tempoTogliere) {
        this.tempoTogliere = tempoTogliere;
    }

    public String getTempoIndossare() {
        return tempoIndossare;
    }

    public void setTempoMettere(String tempoIndossare) {
        this.tempoIndossare = tempoIndossare;
    }

    public String getForzaNecessaria() {
        return forzaNecessaria;
    }

    public void setForzaNecessaria(String forzaNecessaria) {
        this.forzaNecessaria = forzaNecessaria;
    }
}
