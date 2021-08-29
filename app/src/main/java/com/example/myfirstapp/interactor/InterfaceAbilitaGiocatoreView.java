package com.example.myfirstapp.interactor;

public interface InterfaceAbilitaGiocatoreView {
    void displayError(int indexError);
    void addAbilita(String nomea, boolean competenza);
    void removeAbilita(String nomea);
    void swapAbilita(String nomea);
}
