package com.example.myfirstapp.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class EquipaggiamentoNew{
    private String nome;
    private String descrizione;
    private int costo;
    private int peso;
    private String tipo;
    private String subtipo;

    public abstract boolean isEquipaggiabile();
}
