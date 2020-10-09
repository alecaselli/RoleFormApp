package com.example.myfirstapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfirstapp.R;
import com.example.myfirstapp.utilities.CardAbilita;

import java.util.ArrayList;

public class CardAbilitaAdapter extends RecyclerView.Adapter<CardAbilitaAdapter.CardAbilitaViewHolder> {
    private ArrayList<CardAbilita> mCardAbilitaList;
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener; }

    public static class CardAbilitaViewHolder extends RecyclerView.ViewHolder {
        public TextView mNomeAbilitaView;


        public CardAbilitaViewHolder(View itemView, final OnItemClickListener listener) {
            super(itemView);
            mNomeAbilitaView = itemView.findViewById(R.id.skill_name);

            itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }

    public CardAbilitaAdapter(ArrayList<CardAbilita> cardabilitaList) {
        mCardAbilitaList = cardabilitaList;
    }

    @NonNull
    @Override
    public CardAbilitaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_skill_item, parent, false);
        return new CardAbilitaViewHolder(v, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull CardAbilitaAdapter.CardAbilitaViewHolder holder, int position) {
        CardAbilita currentItem = mCardAbilitaList.get(position);
        if (currentItem != null) {
            holder.mNomeAbilitaView.setText(currentItem.getNomeabilita());
        }
    }

    @Override
    public int getItemCount() {
        return mCardAbilitaList.size();
    }

}


