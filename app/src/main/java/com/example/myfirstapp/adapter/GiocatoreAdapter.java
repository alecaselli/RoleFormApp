package com.example.myfirstapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.example.myfirstapp.R;
import com.example.myfirstapp.domain.Caratteristica;
import com.example.myfirstapp.domain.Equipaggiamento;
import com.example.myfirstapp.domain.Giocatore;


import java.util.List;

public class GiocatoreAdapter extends BaseAdapter {
    private List<Giocatore> giocatorelist = null;
    private Context context = null;

    public GiocatoreAdapter(Context context, List<Giocatore> giocatore) {
        this.context = context;
        this.giocatorelist = giocatore;
    }

    @Override
    public int getCount() {
        return giocatorelist.size();
    }

    @Override
    public Object getItem(int position) {
        return giocatorelist.get(getCount() - 1);
    }

    @Override
    public long getItemId(int iposition) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        if (view==null)
        {
            view= LayoutInflater.from(context).inflate(R.layout.activity_character, null);
        }

        Giocatore giocatorev = (Giocatore) getItem(position);
        TextView txt=(TextView) view.findViewById(R.id.campaign_name);
        txt.setText(giocatorev.getNomeCampagna());

        txt=(TextView) view.findViewById(R.id.character_name);
        txt.setText(giocatorev.getNome());

        txt=(TextView) view.findViewById(R.id.lv);
        txt.setText(giocatorev.getLivello());

        txt=(TextView) view.findViewById(R.id.character_height);
        txt.setText(giocatorev.getAltezza());

        txt=(TextView) view.findViewById(R.id.character_age);
        txt.setText(giocatorev.getEta());

        txt=(TextView) view.findViewById(R.id.character_race);
        txt.setText(giocatorev.getRazza().getNome());

        txt=(TextView) view.findViewById(R.id.character_class);
        txt.setText(giocatorev.getClasse().getNome());

        Caratteristica caratteristica = giocatorev.getCaratteristica("forza");
        txt=(TextView) view.findViewById(R.id.character_base_strenght);
        txt.setText(caratteristica.getBase());
        txt=(TextView) view.findViewById(R.id.character_bonus_strenght);
        txt.setText(caratteristica.getBonus());

        caratteristica = giocatorev.getCaratteristica("destrezza");
        txt=(TextView) view.findViewById(R.id.character_base_dexterity);
        txt.setText(caratteristica.getBase());
        txt=(TextView) view.findViewById(R.id.character_bonus_dexterity);
        txt.setText(caratteristica.getBonus());

        caratteristica = giocatorev.getCaratteristica("costituzione");
        txt=(TextView) view.findViewById(R.id.character_base_constitution);
        txt.setText(caratteristica.getBase());
        txt=(TextView) view.findViewById(R.id.character_bonus_constitution);
        txt.setText(caratteristica.getBonus());

        caratteristica = giocatorev.getCaratteristica("intelligenza");
        txt=(TextView) view.findViewById(R.id.character_base_intelligence);
        txt.setText(caratteristica.getBase());
        txt=(TextView) view.findViewById(R.id.character_bonus_intelligence);
        txt.setText(caratteristica.getBonus());

        caratteristica = giocatorev.getCaratteristica("saggezza");
        txt=(TextView) view.findViewById(R.id.character_base_wisdom);
        txt.setText(caratteristica.getBase());
        txt=(TextView) view.findViewById(R.id.character_bonus_wisdom);
        txt.setText(caratteristica.getBonus());

        caratteristica = giocatorev.getCaratteristica("carisma");
        txt=(TextView) view.findViewById(R.id.character_base_charisma);
        txt.setText(caratteristica.getBase());
        txt=(TextView) view.findViewById(R.id.character_bonus_charisma);
        txt.setText(caratteristica.getBonus());

        List<Integer> valore = giocatorev.getPortafoglio().getValoreInMoneta();
        txt=(TextView) view.findViewById(R.id.character_copper);
        txt.setText(valore.get(0));
        txt=(TextView) view.findViewById(R.id.character_silver);
        txt.setText(valore.get(1));
        txt=(TextView) view.findViewById(R.id.character_gold);
        txt.setText(valore.get(2));
        txt=(TextView) view.findViewById(R.id.character_platinum);
        txt.setText(valore.get(3));

        Equipaggiamento equipaggiamento = giocatorev.getEquipaggiato("armatura");
        String nome;
        if(equipaggiamento != null) nome = equipaggiamento.getNome();
        else nome = "non equipaggiato";
        txt=(TextView) view.findViewById(R.id.armor_name);
        txt.setText(nome);

        equipaggiamento = giocatorev.getEquipaggiato("scudo");
        if(equipaggiamento != null) nome = equipaggiamento.getNome();
        else nome = "non equipaggiato";
        txt=(TextView) view.findViewById(R.id.shield_name);
        txt.setText(nome);

        equipaggiamento = giocatorev.getEquipaggiato("arma");
        if(equipaggiamento != null) nome = equipaggiamento.getNome();
        else nome = "non equipaggiato";
        txt=(TextView) view.findViewById(R.id.weapon_name);
        txt.setText(nome);

        return view;
    }

}
