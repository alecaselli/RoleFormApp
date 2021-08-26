package com.example.myfirstapp.domain;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ListaAbilita implements InterfaceListAbilita{
    List<Abilita> abilitaList = new ArrayList<Abilita>();

    public ListaAbilita(List<Abilita> abilitaList) {
        this.abilitaList = abilitaList;
    }

    @Override
    public void addAbilita(Abilita abilita) {
        abilitaList.add(abilita);
    }

    @Override
    public void removeAbilita(Abilita abilita) {
        abilitaList.remove(abilita);
    }

    @Override
    public Abilita getAbilita(String nome) {
        for (Abilita abilita : abilitaList) {
            if(nome.equals(abilita.getNome()))
                return abilita;
        }
        return null;
    }

    @Override
    public Abilita getAbilita(int posizione) {
        return abilitaList.get(posizione);
    }

    @Override
    public Iterator<String> getNomiAbilita() {
        List<String> nomi = new ArrayList<>();
        for (Abilita abilita : abilitaList)
            if (null != abilita)
                nomi.add(abilita.getNome());
        if(nomi.isEmpty())
            return null;
        else
            return nomi.iterator();
    }
}
