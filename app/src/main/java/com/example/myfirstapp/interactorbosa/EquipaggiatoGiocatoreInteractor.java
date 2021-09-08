package com.example.myfirstapp.interactorbosa;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myfirstapp.domain.EquipaggiamentoOld;
import com.example.myfirstapp.utilities.MyExceptionDB;
import com.example.myfirstapp.utilities.MyExceptionNonEquipaggiabile;
import com.example.myfirstapp.utilities.MyExceptionNonEquipaggiato;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class EquipaggiatoGiocatoreInteractor implements InterfaceEquipaggiatoGiocatoreInteractor {

    private final String TIPO = "tipo";
    private final String NOME = "nome";
    private List<EquipaggiamentoOld> equipaggiato;
    private final InterfaceBorsaGiocatoreInteractor borsaIterator;
    private final InterfaceOggettoDB dbOggetto;
    private final InterfaceEquipaggiatoGiocatoreDB dbEquipaggiato;

    public EquipaggiatoGiocatoreInteractor(InterfaceBorsaGiocatoreInteractor borsaIterator, InterfaceOggettoDB dbOggetto, InterfaceEquipaggiatoGiocatoreDB dbEquipaggiato) throws MyExceptionDB{
        this.borsaIterator = borsaIterator;
        this.dbOggetto = dbOggetto;
        this.dbEquipaggiato = dbEquipaggiato;
        this.inizzializzaEquipaggiato();
    }

    private void inizzializzaEquipaggiato() throws MyExceptionDB {
        equipaggiato = dbEquipaggiato.readEquipaggiato();
        if(equipaggiato == null)
            throw new MyExceptionDB();
    }

    private EquipaggiamentoOld getEquipaggiato(String valore, String categoria) throws MyExceptionNonEquipaggiato {
        for( EquipaggiamentoOld equipaggiamento : equipaggiato){
                String secondoValore = (NOME.equals(categoria))? equipaggiamento.getNome() : equipaggiamento.getTipo();
            if(valore.equals(secondoValore))
                return equipaggiamento;
        }
        throw new MyExceptionNonEquipaggiato();
    }

    @NonNull
    private EquipaggiamentoOld getEquipaggiatoByTipo(String tipo) throws MyExceptionNonEquipaggiato {
        return this.getEquipaggiato(tipo, TIPO);
    }

    @NonNull
    private EquipaggiamentoOld getEquipaggiatoByNome(String nome) throws MyExceptionNonEquipaggiato {
        return this.getEquipaggiato(nome, NOME);
    }

    @Nullable
    @Override
    public EquipDataStruct equipaggia(String nomeOggetto) throws MyExceptionDB, MyExceptionNonEquipaggiabile{
        EquipaggiamentoOld daEquipaggiare = dbOggetto.readOggetto(nomeOggetto);
        EquipDataStruct equipDataEquipaggiato = null;

        if(daEquipaggiare.isNonEquipaggiabile())
            throw new MyExceptionNonEquipaggiabile();

        try {
            EquipaggiamentoOld attualmenteEquipaggiato = this.getEquipaggiatoByTipo(daEquipaggiare.getTipo());
            this.disequipaggia(attualmenteEquipaggiato.getNome());
            equipDataEquipaggiato = new EquipDataStruct(attualmenteEquipaggiato.getNome(), attualmenteEquipaggiato.getTipo());
        }catch (MyExceptionNonEquipaggiato ignore){}

        borsaIterator.rimuoviOggetto(daEquipaggiare.getNome());
        if (!dbEquipaggiato.addOggetto(daEquipaggiare.getNome()))
            throw new MyExceptionDB();
        equipaggiato.add(daEquipaggiare);
        return equipDataEquipaggiato;
    }

    @Override
    public EquipDataStruct disequipaggia(String nomeOggetto) throws MyExceptionDB, MyExceptionNonEquipaggiato {
        EquipaggiamentoOld daDisequipaggiare = this.getEquipaggiatoByNome(nomeOggetto);

        borsaIterator.aggiungiOggetto(daDisequipaggiare.getNome());
        if (!dbEquipaggiato.removeOggetto(daDisequipaggiare.getNome())) {
            throw new MyExceptionDB();
        }
        equipaggiato.remove(daDisequipaggiare);
        return  new EquipDataStruct(daDisequipaggiare.getNome(), daDisequipaggiare.getTipo());
    }

    @Override
    public Iterator<EquipDataStruct> equipaggiatoIterator() {
        List<EquipDataStruct> equipData = new ArrayList<>();
        for (EquipaggiamentoOld equipaggiamento : equipaggiato){
            equipData.add(new EquipDataStruct(equipaggiamento.getNome(), equipaggiamento.getTipo()));
        }
        return equipData.iterator();
    }

    //Mai usato
    @Override
    public EquipDataStruct getEquipaggiatoDataByTipo(String tipo) throws MyExceptionNonEquipaggiato {
        EquipaggiamentoOld equipaggiamento = this.getEquipaggiatoByTipo(tipo);
        return new EquipDataStruct(equipaggiamento.getNome(), equipaggiamento.getTipo());
    }
    //Mai usato
    @Override
    public EquipDataStruct getEquipaggiatoDataByNome(String nomeOggetto) throws MyExceptionNonEquipaggiato {
        EquipaggiamentoOld equipaggiamento = this.getEquipaggiatoByNome(nomeOggetto);
        return new EquipDataStruct(equipaggiamento.getNome(), equipaggiamento.getTipo());
    }
}
