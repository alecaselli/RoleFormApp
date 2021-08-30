package com.example.myfirstapp.interactor;

import com.example.myfirstapp.R;

public class DettagliPuntiFeritaInteractor implements InterfaceDettagliPuntiFeritaInteractor{
    InterfaceDettagliPuntiFeritaDB dbPuntiFerita;
    InterfaceDettagliPuntiFeritaView view;

    public DettagliPuntiFeritaInteractor(InterfaceDettagliPuntiFeritaDB dbPuntiFerita, InterfaceDettagliPuntiFeritaView view) {
        this.dbPuntiFerita = dbPuntiFerita;
        this.view = view;
    }

    @Override
    public void setPuntiFerita() {
        int puntiFerita = dbPuntiFerita.readPuntiFerita();
        int puntiFeritaMax = dbPuntiFerita.readPuntiFeritaMax();
        view.setPuntiFerita(puntiFerita);
        view.setPuntiFeritaMax(puntiFeritaMax);
    }

    @Override
    public void changePuntiFerita(int puntiFerita) {
        if(dbPuntiFerita.updatePuntiFerita(puntiFerita))
            view.setPuntiFerita(puntiFerita);
        else
            view.displayError(R.string.db_access_error);
    }
}
