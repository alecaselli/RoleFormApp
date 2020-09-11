<<<<<<< Updated upstream
package com.example.myfirstapp.domain;
=======
package com.example.myfirstapp.domainn;
>>>>>>> Stashed changes

public class Armatura extends Equipaggiamento{
    private boolean nonFurtiva;
    private int ModificatoreCA;
    private String tempoTogliere;
    private String tempoMettere;
    private String forzaNecessaria;

    public Armatura(String nome,
                    StringBuffer descrizione,
                    String tipo,
                    int costo,
                    int peso,
                    int capacita,
                    boolean nonFurtiva,
                    int modificatoreCA,
                    String tempoTogliere,
                    String tempoMettere,
                    String forzaNecessaria) {
        super(nome, descrizione, tipo, costo, peso, capacita);
        this.nonFurtiva = nonFurtiva;
        ModificatoreCA = modificatoreCA;
        this.tempoTogliere = tempoTogliere;
        this.tempoMettere = tempoMettere;
        this.forzaNecessaria = forzaNecessaria;
    }

    public boolean isNonFurtiva() {
        return nonFurtiva;
    }

    public void setNonFurtiva(boolean nonFurtiva) {
        this.nonFurtiva = nonFurtiva;
    }

    public int getModificatoreCA() {
        return ModificatoreCA;
    }

    public void setModificatoreCA(int modificatoreCA) {
        ModificatoreCA = modificatoreCA;
    }

    public String getTempoTogliere() {
        return tempoTogliere;
    }

    public void setTempoTogliere(String tempoTogliere) {
        this.tempoTogliere = tempoTogliere;
    }

    public String getTempoMettere() {
        return tempoMettere;
    }

    public void setTempoMettere(String tempoMettere) {
        this.tempoMettere = tempoMettere;
    }

    public String getForzaNecessaria() {
        return forzaNecessaria;
    }

    public void setForzaNecessaria(String forzaNecessaria) {
        this.forzaNecessaria = forzaNecessaria;
    }
}
