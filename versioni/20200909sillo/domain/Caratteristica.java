package com.example.myfirstapp.domain;

public class Caratteristica extends Descrivibile {
    private boolean tiroSalveza;
    private int valoreBase = 0;
    private int valoreLivello = 0;
    private int valoreEquipaggiamento = 0;
    private int valoreBonus = 0;
    private int modificatore;

    public Caratteristica(String nome, StringBuffer descrizione, boolean tiroSalveza) {
        super(nome, descrizione);
        this.tiroSalveza = tiroSalveza;
        this.setModificatore();
    }

    public boolean isTiroSalveza() {
        return tiroSalveza;
    }

    public void setTiroSalveza(boolean tiroSalveza) {
        this.tiroSalveza = tiroSalveza;
    }

    public int getValoreBase() {
        return valoreBase;
    }

    public void setValoreBase(int valoreBase) {
        this.valoreBase = valoreBase;
    }

    public int getValoreLivello() {
        return valoreLivello;
    }

    public void setValoreLivello(int valoreLivello) {
        this.valoreLivello = valoreLivello;
    }

    public int getValoreBonus() {
        return valoreBonus;
    }

    public void setValoreBonus(int valoreBonus) {
        this.valoreBonus = valoreBonus;
    }

    public void levelUp(int puntiStstistica){
        this.valoreLivello += puntiStstistica;
    }

    public void aggiornaBonus(int bonus){
        this.valoreBonus += bonus;
    } //con bonus negativo quando tolgo un item

    public int getModificatore() {
        return modificatore;
    }

    public void setModificatore() {
        this. modificatore = (int) ((valoreBase + valoreLivello + valoreBonus) - 10) / 2;
    }

    public int getValoreEquipaggiamento() {
        return valoreEquipaggiamento;
    }

    public void setValoreEquipaggiamento(int valoreEquipaggiamento) {
        this.valoreEquipaggiamento = valoreEquipaggiamento;
    }

    public void addValoreBase(int valore){
        this.valoreBase += valore;
    }
}
