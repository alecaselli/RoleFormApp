<<<<<<< Updated upstream
package com.example.myfirstapp.domain;
=======
package com.example.myfirstapp.domainn;
>>>>>>> Stashed changes

import java.util.List;

public class Abilita {
    private String nome;
    private List<String> descrizione;
    private int livello = 0;

    public Abilita(String nome, List<String> descrizione) {
        this.nome = nome;
        this.descrizione = descrizione;
    }

    public void levelUp(){
        this.livello ++ ;
    }
}
