package com.example.myfirstapp.domain;

import java.util.HashMap;
import java.util.Iterator;

public interface InterfaceAbilitaController {
    Iterator<HashMap<String,Boolean>> getAbilitaGiocatore();
    void addAbilitaGiocatore(String nomecamp, String nomeg, String nomea);

}
