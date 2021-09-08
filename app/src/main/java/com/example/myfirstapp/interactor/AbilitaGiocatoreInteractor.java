package com.example.myfirstapp.interactor;

import com.example.myfirstapp.R;
import com.example.myfirstapp.domain.Abilita;

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
        this.inizializzaAbilitaList();
    }

    private void inizializzaAbilitaList(){
        abilitaList = DBReader.readAbilitaGiocatore();
        if(null == abilitaList)
            view.displayError(R.string.db_access_error);
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
            view.displayError(R.string.db_access_error);
            return false;
        }
        if(!DBWriter.createAbilitaGiocatore(aggiungere.getNome())){
            view.displayError(R.string.db_insert_error);
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
            view.displayError(R.string.db_access_error);
            return false;
        }
        if(!DBWriter.deleteAbilitaGiocatore(rimuovere.getNome())){
            view.displayError(R.string.db_access_error);
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
            view.displayError(R.string.db_access_error);
            return false;
        }
        view.swapAbilita(nome);
        return true;
    }

    @Override
    public void setAbilita() {
        HashMap<String, Boolean> nomiCompetenze = new HashMap<>();
        for (Abilita abilita : abilitaList){
            nomiCompetenze.put(abilita.getNome(),abilita.isCompetente());
        }
        view.setAbilita(nomiCompetenze);
    }

    @Override
    public void setAddAbilita() {
        List<Abilita> allAbilita = DBReader.readAllAbilita();
        List<String> remove = new ArrayList<>();
        List<String> nomi = new ArrayList<>();

        for(Abilita abilita : abilitaList){
            remove.add(abilita.getNome());
        }
        for(Abilita abilitaNomi : allAbilita){
            nomi.add(abilitaNomi.getNome());
        }
        nomi.removeAll(remove);
        view.setSpinnerAddAbilita(nomi);
    }

    @Override
    public void setModCompetenza() {
        view.setModCompetenza(DBReader.readModCompetenza());
    }
}
