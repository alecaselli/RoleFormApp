package com.example.myfirstapp.presenterborsa;

import com.example.myfirstapp.R;
import com.example.myfirstapp.interactorbosa.BorsaDataStruct;
import com.example.myfirstapp.interactorbosa.EquipDataStruct;
import com.example.myfirstapp.interactorbosa.InterfaceEquipaggiatoGiocatoreInteractor;
import com.example.myfirstapp.utilities.MyExceptionDB;
import com.example.myfirstapp.utilities.MyExceptionNonEquipaggiabile;
import com.example.myfirstapp.utilities.MyExceptionNonEquipaggiato;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class EquipaggiatoGiocatorePresenter {
    private final InterfaceEquipaggiatoGiocatoreInteractor equipaggiato;
    private final InterfaceEquipaggiatoGiocatoreView viewEquipaggiato;
    private final InterfaceBorsaGiocatoreView viewBorsa;


    public EquipaggiatoGiocatorePresenter(InterfaceEquipaggiatoGiocatoreInteractor equipaggiato, InterfaceEquipaggiatoGiocatoreView viewEquipaggiato, InterfaceBorsaGiocatoreView viewBorsa) {
        this.equipaggiato = equipaggiato;
        this.viewEquipaggiato = viewEquipaggiato;
        this.viewBorsa = viewBorsa;
    }

    public void equipaggia(String nomeOggetto, int position){
        try {
            EquipDataStruct daDisequipaggiare = equipaggiato.equipaggia(nomeOggetto);
            EquipDataStruct daEquipaggiare = equipaggiato.getEquipaggiatoDataByNome(nomeOggetto);
            if(daDisequipaggiare != null){
                viewBorsa.addOggetto(new BorsaDataStruct(daDisequipaggiare.getNome(),daDisequipaggiare.getTipo()));
            }
            viewEquipaggiato.equipaggia(daEquipaggiare);
            viewBorsa.removeOggetto(position);
        }
        catch (MyExceptionDB e){
            viewEquipaggiato.displayError(R.string.db_access_error);
        }
        catch (MyExceptionNonEquipaggiabile e){
            viewEquipaggiato.displayError(R.string.non_equipaggiabile);
        } catch (MyExceptionNonEquipaggiato myExceptionNonEquipaggiato) {
            myExceptionNonEquipaggiato.printStackTrace();
        }
    }

    public void disequipaggia(String nomeOggetto){
        try {
            EquipDataStruct disequipaggiato = equipaggiato.disequipaggia(nomeOggetto);
            viewEquipaggiato.disequipaggia(disequipaggiato);
            viewBorsa.addOggetto(new BorsaDataStruct(disequipaggiato.getNome(), disequipaggiato.getTipo()));
        }
        catch (MyExceptionNonEquipaggiato ignore){}
        catch (MyExceptionDB e){
            viewEquipaggiato.displayError(R.string.db_access_error);
        }
    }

    public void setEquipaggiato(){
        viewEquipaggiato.setEquipaggiato(equipaggiato.equipaggiatoIterator());
    }

}
