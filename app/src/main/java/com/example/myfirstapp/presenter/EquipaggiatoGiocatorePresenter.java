package com.example.myfirstapp.presenter;

import com.example.myfirstapp.R;
import com.example.myfirstapp.interactorbosa.BorsaDataStruct;
import com.example.myfirstapp.interactorbosa.EquipDataStruct;
import com.example.myfirstapp.interactorbosa.EquipaggiatoGiocatoreInteractor;
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
    private final Map<String, Integer> tipiId = Idequipaggiato.getTipiId();

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
            viewEquipaggiato.equipaggia(daEquipaggiare, tipiId.get(daEquipaggiare.getTipo()));
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

    public void disequipaggia(String nomeOggetto, int viewId){
        try {
            EquipDataStruct disequipaggiato = equipaggiato.disequipaggia(nomeOggetto);
            viewEquipaggiato.disequipaggia(disequipaggiato, viewId);
            viewBorsa.addOggetto(new BorsaDataStruct(disequipaggiato.getNome(), disequipaggiato.getTipo()));
        }
        catch (MyExceptionNonEquipaggiato ignore){}
        catch (MyExceptionDB e){
            viewEquipaggiato.displayError(R.string.db_access_error);
        }
    }

    public void setEquipaggiato(){
        List<EquipViewStruct> equipViewStructs = new ArrayList<>();
        Iterator<EquipDataStruct> iterator = equipaggiato.equipaggiatoIterator();
        while(iterator.hasNext()){
            EquipDataStruct equipData = iterator.next();
            equipViewStructs.add(new EquipViewStruct(equipData,tipiId.get(equipData.getTipo())));
        }
        viewEquipaggiato.setEquipaggiato(equipViewStructs.iterator());
    }

}
