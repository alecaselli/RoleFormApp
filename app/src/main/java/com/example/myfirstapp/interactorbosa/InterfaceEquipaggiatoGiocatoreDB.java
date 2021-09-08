package com.example.myfirstapp.interactorbosa;

import com.example.myfirstapp.domain.EquipaggiamentoOld;

import java.util.List;

public interface InterfaceEquipaggiatoGiocatoreDB {
    boolean addOggetto(String nome); //va modificata anche la classe armatura se oggettto è un'armatura
    boolean removeOggetto(String nome); //va modificata anche la classe armatura se oggettto è un'armatura
    List<EquipaggiamentoOld> readEquipaggiato();
}
