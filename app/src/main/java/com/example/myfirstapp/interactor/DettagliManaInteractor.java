package com.example.myfirstapp.interactor;

import com.example.myfirstapp.R;

public class DettagliManaInteractor implements InterfaceDettagliManaInteractor{
    InterfaceDettagliManaDB dbMana;
    InterfaceDettagliManaView view;

    public DettagliManaInteractor(InterfaceDettagliManaDB dbMana, InterfaceDettagliManaView view) {
        this.dbMana = dbMana;
        this.view = view;
    }

    @Override
    public void setMana() {
        int mana = dbMana.readMana();
        int manaMax = dbMana.readManaMax();
        view.setMana(mana);
        view.setManaMax(manaMax);
    }

    @Override
    public void changeMana(int mana) {
        if(dbMana.updateMana(mana))
            view.setMana(mana);
        else
            view.displayError(R.string.db_access_error);
    }
}
