package com.example.myfirstapp.interactor;

import java.util.HashMap;
import java.util.List;

public interface InterfaceAbilitaGiocatoreView extends InterfaceErrorView{
    void addAbilita(String nomea, boolean competenza);
    void removeAbilita(String nomea);
    void swapAbilita(String nomea);
    void setAbilita(HashMap<String, Boolean> nomiCompetenze);
    void setSpinnerAddAbilita(List<String> nomiAbilita);
    void setModCompetenza(int modCompetenza);
}
