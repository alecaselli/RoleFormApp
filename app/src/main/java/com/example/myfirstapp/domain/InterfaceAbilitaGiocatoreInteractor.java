package com.example.myfirstapp.domain;

import java.util.HashMap;
import java.util.List;

public interface InterfaceAbilitaGiocatoreInteractor {
    boolean addAbilitaGiocatore(String nome);
    boolean removeAbilitaGiocatore(String nome);
    boolean swapAbilitaGiocatore(String nome);
    HashMap<String, Boolean> getNomiCompetenza();
    int getModCompetenza();
    List<String> getNomiAbilitaNonInGiocatore();
}
