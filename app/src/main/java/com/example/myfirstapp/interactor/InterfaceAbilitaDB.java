package com.example.myfirstapp.interactor;

import com.example.myfirstapp.domain.Abilita;

public interface InterfaceAbilitaDB {
    boolean createAbilita(String nomea, String desc);
    Abilita readAbilita(String nomea);
    boolean updateAbilita(String nomea,String desc);
    boolean deleteAbilita(String nomea);
}
