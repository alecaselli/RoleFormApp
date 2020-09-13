<<<<<<< Updated upstream
package com.example.myfirstapp.domain;
=======
package com.example.myfirstapp.domainn;
>>>>>>> Stashed changes

import java.util.List;

public class Statistica extends Descrivibile {
    private int valoreBase = 0;
    private int valoreLivello = 0;
    private int valoreBonus = 0;

    public Statistica(String nome, String descrizione) {
        super(nome, descrizione);
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

}
