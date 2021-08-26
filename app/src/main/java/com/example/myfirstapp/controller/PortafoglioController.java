package com.example.myfirstapp.controller;

import com.example.myfirstapp.domain.ValutaOld;
import com.example.myfirstapp.utilities.MyDBException;

import java.util.List;

public class PortafoglioController {

    private String nomecamp;
    private String nomeg;
    private ValutaOld portafoglio;
    private InterfacePortafoglioDB portafoglioDBReader;
    private InterfacePortafoglioDB portafoglioDBWriter;

    public PortafoglioController(String nomecamp, String nomeg, InterfacePortafoglioDB portafoglioDBWriter, InterfacePortafoglioDB portafoglioDBReader) throws MyDBException {
        this.nomecamp = nomecamp;
        this.nomeg = nomeg;
        this.portafoglioDBWriter = portafoglioDBWriter;
        this.portafoglioDBReader = portafoglioDBReader;
        this.getPortafoglio();
    }

    private void getPortafoglio() throws MyDBException {
        this.portafoglio = portafoglioDBReader.getPortafoglio(nomecamp,nomeg);
        if(null==portafoglio)
            throw new MyDBException();
    }

    public void aggiornaPortafoglio(List<Integer> valore) throws MyDBException {
        portafoglio.aggiornaValore(valore);
        if (!portafoglioDBWriter.updatePortafoglio(portafoglio, nomecamp, nomeg))
            throw new MyDBException();
    }

    public List<Integer> getValoreInMonete(){
        return portafoglio.getValoreInMonete();
    }
}
