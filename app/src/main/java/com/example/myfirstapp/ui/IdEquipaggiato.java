package com.example.myfirstapp.ui;


import androidx.annotation.NonNull;

import com.example.myfirstapp.R;

import java.util.HashMap;
import java.util.Map;

public class IdEquipaggiato {
    @NonNull
    public static Map<String, Integer> getTipiId(){
        HashMap<String,Integer> tipiId = new HashMap<>();
        tipiId.put("arma", R.id.bag_weapon);
        tipiId.put("armatura", R.id.bag_armor);
        tipiId.put("scudo", R.id.bag_shield);
        return tipiId;
    }
}
