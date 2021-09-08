package com.example.myfirstapp.presenter;


import com.example.myfirstapp.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Idequipaggiato {
    public static Map<String, Integer> getTipiId(){
        HashMap<String,Integer> tipiId = new HashMap<>();
        tipiId.put("arma", R.id.bag_weapon);
        tipiId.put("armatura", R.id.bag_armor);
        tipiId.put("scudo", R.id.bag_shield);
        return tipiId;
    }
}
