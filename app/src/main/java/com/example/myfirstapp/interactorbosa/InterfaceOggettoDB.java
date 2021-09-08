package com.example.myfirstapp.interactorbosa;

import com.example.myfirstapp.domain.EquipaggiamentoOld;

import java.util.List;

public interface InterfaceOggettoDB {
    void createOggetto();
    EquipaggiamentoOld readOggetto(String nome);
    void updateOggetto();
    void deleteOggetto(String Nome);
    List<String> readAllNomiOggetti();

}
