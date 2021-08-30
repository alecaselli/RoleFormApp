package com.example.myfirstapp.interactor;

import com.example.myfirstapp.R;
import com.example.myfirstapp.domain.ValutaOld;

import java.util.List;

public class PortafoglioInteractor implements InterfacePortafoglioInteractor{

    private ValutaOld portafoglio;
    private InterfacePortafoglioDB portafoglioDBReader;
    private InterfacePortafoglioDB portafoglioDBWriter;
    private InterfacePortafoglioView view;

    public PortafoglioInteractor(InterfacePortafoglioDB portafoglioDBWriter, InterfacePortafoglioDB portafoglioDBReader, InterfacePortafoglioView view){
        this.portafoglioDBWriter = portafoglioDBWriter;
        this.portafoglioDBReader = portafoglioDBReader;
        this.view = view;
        this.getPortafoglio();
    }

    private void getPortafoglio(){
        this.portafoglio = portafoglioDBReader.readPortafoglio();
        if(null==portafoglio)
            view.displayError(R.string.db_access_error);
    }

    @Override
    public void changeValorePortafoglio(List<Integer> valoriOrdineCrescente) {
        if (portafoglioDBWriter.updatePortafoglio(portafoglio.getValore())){
            portafoglio.aggiornaValore(valoriOrdineCrescente);
            view.setPortafoglio(portafoglio.getValoreInMonete());
        }
        else
            view.displayError(R.string.db_access_error);
    }

    @Override
    public void setPortafoglio() {
        view.setPortafoglio(portafoglio.getValoreInMonete());
    }
}
