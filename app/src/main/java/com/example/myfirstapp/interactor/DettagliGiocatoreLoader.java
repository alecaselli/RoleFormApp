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
            view.displayError(R.string.db_error);
    }

    @Override
    public void setRazza() {
        HashMap<String, String> razza = dbReader.readDettagliRazzaGiocatore();
        if(null != razza && razza.size() == 1)
            for(String nome : razza.keySet())
                view.setRazza(nome, razza.get(nome));
        else
            view.displayError(R.string.db_error);
    }

    @Override
    public void setClasse() {
        HashMap<String, String> classe = dbReader.readDettagliClasseGiocatore();
        if(null != classe)
            for(String nome : classe.keySet())
                view.setRazza(nome, classe.get(nome));
        else
            view.displayError(R.string.db_error);

    }

    @Override
    public void setClasseArmatura() {
        view.setClasseArmatura(dbReader.readClasseArmatura());
    }
}
