package com.example.myfirstapp.factory;

import com.example.myfirstapp.interactorbosa.InterfaceBorsaGiocatoreInteractor;
import com.example.myfirstapp.interactorbosa.InterfaceEquipaggiatoGiocatoreInteractor;
import com.example.myfirstapp.presenterborsa.BorsaGiocatorePresenter;
import com.example.myfirstapp.presenterborsa.EquipaggiatoGiocatorePresenter;
import com.example.myfirstapp.presenterborsa.InterfaceBorsaGiocatoreView;
import com.example.myfirstapp.presenterborsa.InterfaceEquipaggiatoGiocatoreView;
import com.example.myfirstapp.utilities.MyExceptionDB;

public class BorsaEquipPresenterFactory implements InterfaceBorsaEquipPresenterFactory {
    private final InterfaceBorsaEquipInteractorFactory interactorFactory;
    private InterfaceBorsaGiocatoreInteractor borsaInteractor = null;

    public BorsaEquipPresenterFactory(InterfaceBorsaEquipInteractorFactory interactorFactory) {
        this.interactorFactory = interactorFactory;
    }

    @Override
    public BorsaGiocatorePresenter createBorsaGiocatorePresenter(InterfaceBorsaGiocatoreView viewBorsa) throws MyExceptionDB {
        borsaInteractor = interactorFactory.createBorsaGiocatoreInteractor();
        return new BorsaGiocatorePresenter(borsaInteractor, viewBorsa);
    }

    @Override
    public EquipaggiatoGiocatorePresenter createEquipaggiatoGiocatorePresenter(InterfaceEquipaggiatoGiocatoreView viewEquipaggiato, InterfaceBorsaGiocatoreView viewBorsa) throws MyExceptionDB {
        if(borsaInteractor == null)
            throw new MyExceptionDB();
        InterfaceEquipaggiatoGiocatoreInteractor equipaggiatoInteractor = interactorFactory.createEquipaggiatoGiocatoreInteractor(borsaInteractor);
        return new EquipaggiatoGiocatorePresenter(equipaggiatoInteractor, viewEquipaggiato, viewBorsa);
    }
}
