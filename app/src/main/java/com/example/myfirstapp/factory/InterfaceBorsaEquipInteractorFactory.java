package com.example.myfirstapp.factory;

import com.example.myfirstapp.interactorbosa.InterfaceBorsaGiocatoreInteractor;
import com.example.myfirstapp.interactorbosa.InterfaceEquipaggiatoGiocatoreInteractor;
import com.example.myfirstapp.utilities.MyExceptionDB;

public interface InterfaceBorsaEquipInteractorFactory {
    InterfaceBorsaGiocatoreInteractor createBorsaGiocatoreInteractor() throws MyExceptionDB;
    InterfaceEquipaggiatoGiocatoreInteractor createEquipaggiatoGiocatoreInteractor(InterfaceBorsaGiocatoreInteractor borsaInteractor) throws MyExceptionDB;
}
