package com.example.myfirstapp.interactorbosa;

import com.example.myfirstapp.domain.EquipaggiamentoOld;

import java.util.List;

public interface InterfaceBorsaGiocatoreDB {
    boolean addOggetto(String nome);
    boolean removeOggetto(String nome);
    List<EquipaggiamentoOld> readBorsa();
}
