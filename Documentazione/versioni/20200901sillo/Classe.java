<<<<<<< Updated upstream
package com.example.myfirstapp.domain;
=======
package com.example.myfirstapp.domainn;
>>>>>>> Stashed changes

import java.util.List;

public class Classe extends Descrivibile {
    private StringBuffer descrizioneElementiParticolari; // in alternativa imagine con tab del manuale
    private PuntiFerita puntiFeritaClasse;
    private Competenza competenzaClasse;
    private List<Equipaggiamento> equipaggiamentoList;
    private List<Descrivibile> privilegiClasse;
    private List<Incantesimo> incantesimiClasse;

    public Classe(String nome,
                  StringBuffer descrizione,
                  StringBuffer descrizioneElementiParticolari,
                  PuntiFerita puntiFeritaClasse,
                  Competenza competenzaClasse,
                  List<Equipaggiamento> equipaggiamentoList,
                  List<Descrivibile> privilegiClasse,
                  List<Incantesimo> incantesimiClasse) {
        super(nome, descrizione);
        this.descrizioneElementiParticolari = descrizioneElementiParticolari;
        this.puntiFeritaClasse = puntiFeritaClasse;
        this.competenzaClasse = competenzaClasse;
        this.equipaggiamentoList = equipaggiamentoList;
        this.privilegiClasse = privilegiClasse;
        this.incantesimiClasse = incantesimiClasse;
    }

    public StringBuffer getdescrizioneElementiParticolari() {
        return descrizioneElementiParticolari;
    }

    public void setdescrizioneElementiParticolari(StringBuffer descrizioneElementiParticolari) {
        this.descrizioneElementiParticolari = descrizioneElementiParticolari;
    }

    public PuntiFerita getPuntiFeritaClasse() {
        return puntiFeritaClasse;
    }

    public void setPuntiFeritaClasse(PuntiFerita puntiFeritaClasse) {
        this.puntiFeritaClasse = puntiFeritaClasse;
    }

    public Competenza getCompetenzaClasse() {
        return competenzaClasse;
    }

    public void setCompetenzaClasse(Competenza competenzaClasse) {
        this.competenzaClasse = competenzaClasse;
    }

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
