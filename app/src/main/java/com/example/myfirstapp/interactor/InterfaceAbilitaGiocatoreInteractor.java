package com.example.myfirstapp.interactor;

import java.util.HashMap;
import java.util.List;

public interface InterfaceAbilitaGiocatoreInteractor {
    boolean addAbilitaGiocatore(String nome);
    boolean removeAbilitaGiocatore(String nome);
    boolean swapAbilitaGiocatore(String nome);
    void setAbilita();
    void setAddAbilita();
    void setModCompetenza();

}
