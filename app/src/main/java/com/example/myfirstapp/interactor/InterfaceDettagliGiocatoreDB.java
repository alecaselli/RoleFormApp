package com.example.myfirstapp.interactor;

import java.util.HashMap;

public interface InterfaceDettagliGiocatoreDB {
    HashMap<String, String> readFisonomiaGiocatore();
    HashMap<String, String> readDettagliRazzaGiocatore();
    HashMap<String, String> readDettagliClasseGiocatore();
    int readClasseArmatura();
}
