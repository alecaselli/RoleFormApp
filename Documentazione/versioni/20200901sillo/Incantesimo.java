<<<<<<< Updated upstream
package com.example.myfirstapp.domain;
=======
package com.example.myfirstapp.domainn;
>>>>>>> Stashed changes

import java.util.List;

public class Incantesimo extends Descrivibile {
    private String classe;
    private String tempoInvocazione;
    private String raggioAzione;
    private String componenti;
    private String durata;
    private int livello;
    //private String elemento;

    public Incantesimo(String nome, StringBuffer descrizione, String classe, String tempoInvocazione, String raggioAzione, String componenti, String durata, int livello) {
        super(nome, descrizione);
        this.classe = classe;
        this.tempoInvocazione = tempoInvocazione;
        this.raggioAzione = raggioAzione;
        this.componenti = componenti;
        this.durata = durata;
        this.livello = livello;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public String getTempoInvocazione() {
        return tempoInvocazione;
    }

    public void setTempoInvocazione(String tempoInvocazione) {
        this.tempoInvocazione = tempoInvocazione;
    }

    public String getRaggioAzione() {
        return raggioAzione;
    }

    public void setRaggioAzione(String raggioAzione) {
        this.raggioAzione = raggioAzione;
    }

    public String getComponenti() {
        return componenti;
    }

    public void setComponenti(String componenti) {
        this.componenti = componenti;
    }

    public String getDurata() {
        return durata;
    }

    public void setDurata(String durata) {
        this.durata = durata;
    }

    public int getLivello() {
        return livello;
    }

    public void setLivello(int livello) {
        this.livello = livello;
    }
}
