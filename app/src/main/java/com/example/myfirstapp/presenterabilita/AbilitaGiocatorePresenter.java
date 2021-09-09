package com.example.myfirstapp.presenterabilita;

import com.example.myfirstapp.interactor.InterfaceAbilitaGiocatoreInteractor;
import com.example.myfirstapp.interactor.InterfaceAbilitaGiocatoreView;

public class AbilitaGiocatorePresenter{

    InterfaceAbilitaGiocatoreView view;
    InterfaceAbilitaGiocatoreInteractor interactor;

    public AbilitaGiocatorePresenter(InterfaceAbilitaGiocatoreView view, InterfaceAbilitaGiocatoreInteractor interactor) {
        this.view = view;
        this.interactor = interactor;
    }
}
