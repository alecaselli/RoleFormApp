package com.example.myfirstapp.interactor;

import com.example.myfirstapp.domain.Abilita;

import java.util.List;

public interface InterfaceAbilitaGiocatoreDB {
    int readModCompetenza();
    Abilita readAbilitaByName(String nomea);
    boolean createAbilitaGiocatore(String nomea);
    List<Abilita> readAbilitaGiocatore();
    boolean updateAbilitaGiocatore(String nomea, boolean competenza);
    boolean deleteAbilitaGiocatore(String nomea);
    List<Abilita> readAllAbilita();
}
