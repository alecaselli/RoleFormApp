package com.example.myfirstapp.factory;

import com.example.myfirstapp.presenterborsa.BorsaGiocatorePresenter;
import com.example.myfirstapp.presenterborsa.EquipaggiatoGiocatorePresenter;
import com.example.myfirstapp.presenterborsa.InterfaceBorsaGiocatoreView;
import com.example.myfirstapp.presenterborsa.InterfaceEquipaggiatoGiocatoreView;
import com.example.myfirstapp.utilities.MyExceptionDB;

public interface InterfaceBorsaEquipPresenterFactory {
    BorsaGiocatorePresenter createBorsaGiocatorePresenter(InterfaceBorsaGiocatoreView viewBorsa) throws MyExceptionDB;
    EquipaggiatoGiocatorePresenter createEquipaggiatoGiocatorePresenter(InterfaceEquipaggiatoGiocatoreView viewEquipaggiato, InterfaceBorsaGiocatoreView viewBorsa) throws MyExceptionDB;
}
