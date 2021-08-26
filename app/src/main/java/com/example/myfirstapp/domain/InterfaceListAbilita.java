package com.example.myfirstapp.domain;

import java.util.Iterator;
import java.util.List;

public interface InterfaceListAbilita {
    void addAbilita(Abilita abilita);
    void removeAbilita(Abilita abilita);
    Abilita getAbilita(String nome);
    Abilita getAbilita(int posizione);
    Iterator<String> getNomiAbilita();
}
