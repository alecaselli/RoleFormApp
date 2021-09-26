package com.example.myfirstapp.factory;

import com.example.myfirstapp.borsa.interactorbosa.InterfaceBorsaGiocatoreInteractor;
import com.example.myfirstapp.borsa.presenterborsa.BorsaGiocatorePresenter;
import com.example.myfirstapp.borsa.presenterborsa.EquipaggiatoGiocatorePresenter;
import com.example.myfirstapp.borsa.presenterborsa.InterfaceBorsaGiocatoreView;
import com.example.myfirstapp.borsa.presenterborsa.InterfaceEquipaggiatoGiocatoreView;
import com.example.myfirstapp.utilities.MyExceptionDB;

public class BorsaEquipDND5PresenterFactory implements InterfaceBorsaEquipPresenterFactory {
    private final InterfaceBorsaEquipInteractorFactory interactorFactory;
    private InterfaceBorsaGiocatoreInteractor borsaInteractor = null;

    public BorsaEquipDND5PresenterFactory(InterfaceBorsaEquipInteractorFactory interactorFactory) {
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
        return new EquipaggiatoGiocatorePresenter(interactorFactory.createEquipaggiatoGiocatoreInteractor(borsaInteractor), viewEquipaggiato, viewBorsa);
    }
}
