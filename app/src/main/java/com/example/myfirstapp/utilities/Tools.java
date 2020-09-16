package com.example.myfirstapp.utilities;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class Tools<T> {

    /* funzione che converte una lista di n liste lunghe m in
         una lista di m liste lunghe n */
    public List<List<T>> convertiLista(@NotNull List<List<T>> listainiziale) {
        List<List<T>> listafinale = new ArrayList<List<T>>();
        for (T elemento : listainiziale.get(0)) {
            List<T> primostrato = new ArrayList<T>();
            listafinale.add(primostrato);
        }

        for (List<T> primostrato : listainiziale) {
            for (T elemento : primostrato) {
                listafinale.get(primostrato.indexOf(elemento)).add(elemento);
            }
        }

        return listafinale;
    }
}
