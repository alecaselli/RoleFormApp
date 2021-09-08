package com.example.myfirstapp.factory;

import com.example.myfirstapp.interactorbosa.BorsaGiocatoreInteractor;
import com.example.myfirstapp.interactorbosa.EquipaggiatoGiocatoreInteractor;
import com.example.myfirstapp.interactorbosa.InterfaceBorsaGiocatoreDB;
import com.example.myfirstapp.interactorbosa.InterfaceBorsaGiocatoreInteractor;
import com.example.myfirstapp.interactorbosa.InterfaceEquipaggiatoGiocatoreDB;
import com.example.myfirstapp.interactorbosa.InterfaceEquipaggiatoGiocatoreInteractor;
import com.example.myfirstapp.interactorbosa.InterfaceOggettoDB;
import com.example.myfirstapp.presenter.BorsaGiocatorePresenter;
import com.example.myfirstapp.presenter.EquipaggiatoGiocatorePresenter;
import com.example.myfirstapp.presenter.InterfaceBorsaGiocatoreView;
import com.example.myfirstapp.presenter.InterfaceEquipaggiatoGiocatoreView;
import com.example.myfirstapp.utilities.MyExceptionDB;

public class BorsaEquipFactory implements InterfaceBorsaEquipFactory {

    @Override
    public BorsaGiocatorePresenter createBorsaGiocatorePresenter(InterfaceBorsaGiocatoreInteractor borsa, InterfaceBorsaGiocatoreView viewBorsa){
        return new BorsaGiocatorePresenter(borsa, viewBorsa);
    }

    @Override
    public InterfaceBorsaGiocatoreInteractor createBorsaGiocatoreInteractor(InterfaceOggettoDB dbOggetto, InterfaceBorsaGiocatoreDB dbBorsa) throws MyExceptionDB {
        return new BorsaGiocatoreInteractor(dbOggetto, dbBorsa);
    }

    @Override
    public EquipaggiatoGiocatorePresenter createEquipaggiatoGiocatorePresenter(InterfaceEquipaggiatoGiocatoreInteractor equipaggiato, InterfaceEquipaggiatoGiocatoreView viewEquipaggiato, InterfaceBorsaGiocatoreView viewBorsa){
        return new EquipaggiatoGiocatorePresenter(equipaggiato, viewEquipaggiato, viewBorsa);
    }

    @Override
    public InterfaceEquipaggiatoGiocatoreInteractor createEquipaggiatoGiocatoreInteractor(InterfaceBorsaGiocatoreInteractor borsaIterator, InterfaceOggettoDB dbOggetto, InterfaceEquipaggiatoGiocatoreDB dbEquipaggiato) throws MyExceptionDB {
        return new EquipaggiatoGiocatoreInteractor(borsaIterator, dbOggetto, dbEquipaggiato);
    }
}
