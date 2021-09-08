package com.example.myfirstapp.interactorbosa;

import com.example.myfirstapp.utilities.MyExceptionDB;
import com.example.myfirstapp.utilities.MyExceptionNonEquipaggiabile;
import com.example.myfirstapp.utilities.MyExceptionNonEquipaggiato;

import java.util.Iterator;

public interface InterfaceEquipaggiatoGiocatoreInteractor {
    // ritorna un EquipDataStruct corrispondente al'equipaggamento da disequipagiare per poter equipaggiare nomeOggetto
    EquipDataStruct equipaggia(String nomeOggetto) throws MyExceptionDB, MyExceptionNonEquipaggiabile;
    EquipDataStruct disequipaggia(String nomeOggetto) throws MyExceptionDB, MyExceptionNonEquipaggiato;
    Iterator<EquipDataStruct> equipaggiatoIterator();
    EquipDataStruct getEquipaggiatoDataByTipo(String tipo) throws MyExceptionNonEquipaggiato;
    EquipDataStruct getEquipaggiatoDataByNome(String nomeOggetto) throws MyExceptionNonEquipaggiato;
}
