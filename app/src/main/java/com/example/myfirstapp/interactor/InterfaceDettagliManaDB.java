package com.example.myfirstapp.interactor;

import java.util.HashMap;

public interface InterfaceDettagliManaDB {
    int readMana();
    int readManaMax();
    boolean updateMana(int mana);
}
