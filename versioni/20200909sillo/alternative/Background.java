package com.example.myfirstapp.alternative;

import com.example.myfirstapp.domain.Descrivibile;
import com.example.myfirstapp.domain.Equipaggiamento;

import java.util.List;

public class Background extends Descrivibile {
    private String lingua;
    private Competenza competenzaBack;
    private List<Equipaggiamento> equipaggiamentoList;

    public Background(String nome, StringBuffer descrizione, String lingua, Competenza competenzaBack, List<Equipaggiamento> equipaggiamentoList) {
        super(nome, descrizione);
        this.lingua = lingua;
        this.competenzaBack = competenzaBack;
        this.equipaggiamentoList = equipaggiamentoList;
    }

    public String getLingua() {
        return lingua;
    }

    public void setLingua(String lingua) {
        this.lingua = lingua;
    }

    public Competenza getCompetenzaBack() {
        return competenzaBack;
    }

    public void setCompetenzaBack(Competenza competenzaBack) {
        this.competenzaBack = competenzaBack;
    }

    public List<Equipaggiamento> getEquipaggiamentoList() {
        return equipaggiamentoList;
    }

    public void setEquipaggiamentoList(List<Equipaggiamento> equipaggiamentoList) {
        this.equipaggiamentoList = equipaggiamentoList;
    }
}
