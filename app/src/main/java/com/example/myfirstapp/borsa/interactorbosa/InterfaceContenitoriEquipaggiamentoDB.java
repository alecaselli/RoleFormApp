package com.example.myfirstapp.borsa.interactorbosa;

import com.example.myfirstapp.domain.EquipaggiamentoOld;

import java.util.List;

public interface InterfaceContenitoriEquipaggiamentoDB {
    boolean addOggetto(String nome);
    boolean removeOggetto(String nome);
    List<EquipaggiamentoOld> readBorsa();
}
