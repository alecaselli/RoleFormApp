package com.example.myfirstapp.borsa.presenterborsa;

import com.example.myfirstapp.borsa.interactorbosa.EquipDataStruct;

public class EquipViewStruct {
    EquipDataStruct equipData;
    int viewId;

    public EquipViewStruct(EquipDataStruct equipData, int viewId) {
        this.equipData = equipData;
        this.viewId = viewId;
    }

    public String getNome() {
        return equipData.getNome();
    }

    public String getTipo() {
        return equipData.getTipo();
    }

    public int getViewId() {
        return viewId;
    }
}
