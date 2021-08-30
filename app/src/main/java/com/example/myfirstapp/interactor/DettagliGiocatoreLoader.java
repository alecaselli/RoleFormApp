package com.example.myfirstapp.interactor;

import com.example.myfirstapp.R;

import java.util.HashMap;

public class DettagliGiocatoreLoader implements InterfaceDettagliGiocatoreLoader {
    private final String NOME = "nome";
    private final String GENERE = "genere";
    private final String ALTEZZA = "altezza";
    private final String ETA = "eta";
    private final InterfaceDettagliGiocatoreDB dbReader;
    private final InterfaceDettagliGiocatoreView view;

    public DettagliGiocatoreLoader(InterfaceDettagliGiocatoreDB DBReader, InterfaceDettagliGiocatoreView view) {
        this.dbReader = DBReader;
        this.view = view;
    }

    @Override
    public void setFisionomiaGiocatore() {
        HashMap<String, String> fisionomia = dbReader.readFisonomiaGiocatore();
        if(null != fisionomia)
            view.setFisionomia(fisionomia.get(NOME), fisionomia.get(GENERE), fisionomia.get(ALTEZZA), fisionomia.get(ETA));
        else
            view.displayError(R.string.db_access_error);
    }

    @Override
    public void setRazza() {
        HashMap<String, String> descRazza = dbReader.readDettagliRazzaGiocatore();
        if(null != descRazza && descRazza.size() == 1)
            for(String nomeRazza : descRazza.keySet())
                view.setRazza(nomeRazza, descRazza.get(nomeRazza));
        else
            view.displayError(R.string.db_access_error);
    }

    @Override
    public void setClasse() {
        HashMap<String, String> descClasse = dbReader.readDettagliClasseGiocatore();
        if(null != descClasse)
            for(String nomeClasse : descClasse.keySet())
                view.setClasse(nomeClasse, descClasse.get(nomeClasse));
        else
            view.displayError(R.string.db_access_error);

    }

    @Override
    public void setClasseArmatura() {
        view.setClasseArmatura(dbReader.readClasseArmatura());
    }
}
