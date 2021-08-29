package com.example.myfirstapp.interactor;

import com.example.myfirstapp.R;

public class DettagliLivelloInteractor implements InterfaceDettagliLivelloInteractor{
    InterfaceDettagliLivelloDB dbLivello;
    InterfaceDettagliLivelloview view;

    public DettagliLivelloInteractor(InterfaceDettagliLivelloDB dbLivello, InterfaceDettagliLivelloview view) {
        this.dbLivello = dbLivello;
        this.view = view;
    }

    @Override
    public void setLivello() {
        view.setLivello(dbLivello.readLivello());
    }

    @Override
    public void livelloUp(int livello) {
        this.changeLivello(livello, 1);
    }

    @Override
    public void livelloDown(int livello) {
        this.changeLivello(livello, -1);
    }

    private void changeLivello(int livello, int change){
        if(dbLivello.updateLivello(livello + change))
            view.setLivello(livello + change);
        else
            view.displayError(R.string.db_error);
    }
    //TODO: implementazione funzionalita livello con punti xp
}
