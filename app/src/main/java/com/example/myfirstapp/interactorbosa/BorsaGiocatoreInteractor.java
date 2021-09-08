package com.example.myfirstapp.interactorbosa;

import androidx.annotation.Nullable;

import com.example.myfirstapp.domain.EquipaggiamentoOld;
import com.example.myfirstapp.utilities.MyExceptionDB;
import com.example.myfirstapp.utilities.MyExeptionOggettoNonTrovato;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BorsaGiocatoreInteractor implements InterfaceBorsaGiocatoreInteractor {

    private final InterfaceOggettoDB dbOggetto;
    private final InterfaceBorsaGiocatoreDB dbBorsa;
    private List<EquipaggiamentoOld> borsa;

    public BorsaGiocatoreInteractor(InterfaceOggettoDB dbOggetto, InterfaceBorsaGiocatoreDB dbBorsa) throws MyExceptionDB {
        this.dbOggetto = dbOggetto;
        this.dbBorsa = dbBorsa;
        this.inizializzaBorsa();
    }

    private void inizializzaBorsa() throws MyExceptionDB {
        borsa = dbBorsa.readBorsa();
        if(borsa == null)
            throw new MyExceptionDB();
    }

    @Override
    public BorsaDataStruct aggiungiOggetto(String nomeOggetto) throws MyExceptionDB{
        EquipaggiamentoOld aggiunto = dbOggetto.readOggetto(nomeOggetto);
        if (!dbBorsa.addOggetto(aggiunto.getNome()))
            throw new MyExceptionDB();
        borsa.add(aggiunto);
        return new BorsaDataStruct(aggiunto.getNome(), aggiunto.getTipo());
    }

    @Override
    public void rimuoviOggetto(String nomeOggetto) throws MyExceptionDB{
        EquipaggiamentoOld rimosso = dbOggetto.readOggetto(nomeOggetto);
        if (!dbBorsa.removeOggetto(rimosso.getNome()))
            throw new MyExceptionDB();
        borsa.remove(rimosso);
    }

    @Override
    public Iterator<BorsaDataStruct> getborsaIterator() {
        List<BorsaDataStruct> equipData = new ArrayList<>();
        for (EquipaggiamentoOld equipaggiamento : borsa){
            equipData.add(new BorsaDataStruct(equipaggiamento.getNome(), equipaggiamento.getTipo()));
        }
        return equipData.iterator();
    }

    //Mai usato
    @Override
    public BorsaDataStruct getOggetto(String nomeOggetto) throws MyExeptionOggettoNonTrovato {
        for (EquipaggiamentoOld equip : borsa){
           if(nomeOggetto.equals(equip.getNome()))
               return new BorsaDataStruct(equip.getNome(),equip.getTipo());
        }
        throw new MyExeptionOggettoNonTrovato();
    }

    @Override
    public Iterator<String> getOggettiNonInBorsaIterator() {
        List<String> nomi = dbOggetto.readAllNomiOggetti();
        for(EquipaggiamentoOld equipaggiamento : borsa){
            nomi.remove(equipaggiamento.getNome());
        }
        return nomi.iterator();
    }
}
