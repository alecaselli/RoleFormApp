package com.example.myfirstapp.factory;

import com.example.myfirstapp.borsa.interactorbosa.BorsaGiocatoreInteractor;
import com.example.myfirstapp.borsa.interactorbosa.EquipaggiatoGiocatoreInteractor;
import com.example.myfirstapp.borsa.interactorbosa.InterfaceBorsaGiocatoreInteractor;
import com.example.myfirstapp.borsa.interactorbosa.InterfaceEquipaggiatoGiocatoreInteractor;
import com.example.myfirstapp.utilities.MyExceptionDB;

public class BorsaEquipDND5InteractorFactory implements InterfaceBorsaEquipInteractorFactory{
    private final InterfaceBorsaEquipDBFactory dbFactory;

    public BorsaEquipDND5InteractorFactory(InterfaceBorsaEquipDBFactory dbFactory) {
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
