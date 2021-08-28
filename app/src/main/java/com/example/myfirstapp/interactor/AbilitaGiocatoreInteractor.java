package com.example.myfirstapp.interactor;

import com.example.myfirstapp.R;
import com.example.myfirstapp.domain.Abilita;
import com.example.myfirstapp.domain.InterfaceAbilitaGiocatoreInteractor;
import com.example.myfirstapp.domain.InterfaceAbilitaGiocatoreView;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AbilitaGiocatoreInteractor implements InterfaceAbilitaGiocatoreInteractor {
    private List<Abilita> abilitaList;
    private InterfaceAbilitaGiocatoreView view;
    private InterfaceAbilitaGiocatoreDB DBWriter;
    private InterfaceAbilitaGiocatoreDB DBReader;

    public AbilitaGiocatoreInteractor(InterfaceAbilitaGiocatoreView view, InterfaceAbilitaGiocatoreDB DBWriter, InterfaceAbilitaGiocatoreDB DBReader){
        this.view = view;
        this.DBWriter = DBWriter;
        this.DBReader = DBReader;
        this.getAbilitalist();
    }

    private void getAbilitalist(){
        abilitaList = DBReader.readAbilitaGiocatore();
        if(null == abilitaList)
            view.displayError(R.string.db_error);
    }

    private int getIdByName(String nome){
        for(Abilita abilita : abilitaList){
            if(abilita.getNome().equals(nome))
                return abilitaList.indexOf(abilita);
        }
        return -1;
    }

    @Override
    public boolean addAbilitaGiocatore(String nome){
        Abilita aggiungere = DBReader.readAbilitaByName(nome);
        if (null == aggiungere){
            view.displayError(R.string.db_error);
            return false;
        }
        if(!DBWriter.createAbilitaGiocatore(aggiungere.getNome())){
            view.displayError(R.string.db_error);
            return false;
        }
        abilitaList.add(aggiungere);
        view.addAbilita(aggiungere.getNome(),aggiungere.isCompetente());
        return true;
    }

    @Override
    public boolean removeAbilitaGiocatore(String nome){
        Abilita rimuovere = DBReader.readAbilitaByName(nome);
        if (null == rimuovere){
            view.displayError(R.string.db_error);
            return false;
        }
        if(!DBWriter.deleteAbilitaGiocatore(rimuovere.getNome())){
            view.displayError(R.string.db_error);
            return false;
        }
        abilitaList.remove(rimuovere);
        view.removeAbilita(rimuovere.getNome());
        return true;
    }

    @Override
    public boolean swapAbilitaGiocatore(String nome){
        int id = this.getIdByName(nome);
        if (id < 0){
            view.displayError(R.string.abilita_assente_error);
            return false;
        }
        abilitaList.get(id).swapCompetenza();
        if(!DBWriter.updateAbilitaGiocatore(nome, abilitaList.get(id).isCompetente())){
            view.displayError(R.string.db_error);
            return false;
        }
        view.swapAbilita(nome);
        return true;
    }

    @Override
    public HashMap<String, Boolean> getNomiCompetenza() {
        HashMap<String, Boolean> nomiCompetenze = new HashMap<>();
        for (Abilita abilita : abilitaList){
            nomiCompetenze.put(abilita.getNome(),abilita.isCompetente());
        }
        return nomiCompetenze;
    }

    @Override
    public int getModCompetenza() {
        return DBReader.readModCompetenza();
    }

    @Override
    public List<String> getNomiAbilitaNonInGiocatore() {
        List<Abilita> allAbilita = DBReader.readAllAbilita();
        List<String> nomi = new ArrayList<>();
        allAbilita.removeAll(abilitaList);
        for(Abilita abilita : allAbilita){
            nomi.add(abilita.getNome());
        }
        return nomi;
    }
}
