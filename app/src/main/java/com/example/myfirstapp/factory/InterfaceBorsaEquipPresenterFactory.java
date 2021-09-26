package com.example.myfirstapp.factory;

import com.example.myfirstapp.borsa.presenterborsa.BorsaGiocatorePresenter;
import com.example.myfirstapp.borsa.presenterborsa.EquipaggiatoGiocatorePresenter;
import com.example.myfirstapp.borsa.presenterborsa.InterfaceBorsaGiocatoreView;
import com.example.myfirstapp.borsa.presenterborsa.InterfaceEquipaggiatoGiocatoreView;
import com.example.myfirstapp.utilities.MyExceptionDB;

public interface InterfaceBorsaEquipPresenterFactory {
    BorsaGiocatorePresenter createBorsaGiocatorePresenter(InterfaceBorsaGiocatoreView viewBorsa) throws MyExceptionDB;
    EquipaggiatoGiocatorePresenter createEquipaggiatoGiocatorePresenter(InterfaceEquipaggiatoGiocatoreView viewEquipaggiato, InterfaceBorsaGiocatoreView viewBorsa) throws MyExceptionDB;
}
