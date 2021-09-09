package com.example.myfirstapp.factory;

import com.example.myfirstapp.interactorbosa.BorsaGiocatoreInteractor;
import com.example.myfirstapp.interactorbosa.EquipaggiatoGiocatoreInteractor;
import com.example.myfirstapp.interactorbosa.InterfaceBorsaGiocatoreInteractor;
import com.example.myfirstapp.interactorbosa.InterfaceEquipaggiatoGiocatoreInteractor;
import com.example.myfirstapp.utilities.MyExceptionDB;

public class BorsaEquipInteractorFactory implements InterfaceBorsaEquipInteractorFactory{
    private final InterfaceBorsaEquipDBFactory dbFactory;

    public BorsaEquipInteractorFactory(InterfaceBorsaEquipDBFactory dbFactory) {
        this.dbFactory = dbFactory;
    }

    @Override
    public InterfaceBorsaGiocatoreInteractor createBorsaGiocatoreInteractor() throws MyExceptionDB {
        return new BorsaGiocatoreInteractor(dbFactory.createOggettoDB(), dbFactory.createBorsaDB());
    }

    @Override
    public InterfaceEquipaggiatoGiocatoreInteractor createEquipaggiatoGiocatoreInteractor(InterfaceBorsaGiocatoreInteractor borsaInteractor) throws MyExceptionDB {
        return new EquipaggiatoGiocatoreInteractor(borsaInteractor, dbFactory.createOggettoDB(), dbFactory.createEquipaggiatoDB());
    }
}
