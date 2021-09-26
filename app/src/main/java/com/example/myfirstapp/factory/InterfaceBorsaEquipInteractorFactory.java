package com.example.myfirstapp.factory;

import com.example.myfirstapp.borsa.interactorbosa.InterfaceBorsaGiocatoreInteractor;
import com.example.myfirstapp.borsa.interactorbosa.InterfaceEquipaggiatoGiocatoreInteractor;
import com.example.myfirstapp.utilities.MyExceptionDB;

public interface InterfaceBorsaEquipInteractorFactory {
    InterfaceBorsaGiocatoreInteractor createBorsaGiocatoreInteractor() throws MyExceptionDB;
    InterfaceEquipaggiatoGiocatoreInteractor createEquipaggiatoGiocatoreInteractor(InterfaceBorsaGiocatoreInteractor borsaInteractor) throws MyExceptionDB;
}
