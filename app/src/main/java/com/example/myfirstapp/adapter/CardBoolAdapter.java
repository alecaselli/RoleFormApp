package com.example.myfirstapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfirstapp.R;
import com.example.myfirstapp.utilities.CardBool;

import java.util.ArrayList;

public class CardBoolAdapter extends RecyclerView.Adapter<CardBoolAdapter.CardAbilitaViewHolder> {
    private ArrayList<CardBool> mCardBoolList;
    private OnItemClickListener mListener;


    public interface OnItemClickListener {
        void onItemClick(int position);
        void onBoolClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public static class CardAbilitaViewHolder extends RecyclerView.ViewHolder {
        public TextView mNomeAbilitaView;
        public CardView mPallinoCard;
        public CardView mColorePallinoCard;


        public CardAbilitaViewHolder(View itemView, final OnItemClickListener listener) {
            super(itemView);
            mNomeAbilitaView = itemView.findViewById(R.id.skill_name);
            mPallinoCard = itemView.findViewById(R.id.skill_ball);
            mColorePallinoCard = itemView.findViewById(R.id.skill_mod_on);

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

            mPallinoCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onBoolClick(position);
                        }
                    }
                }
            });
        }
    }

    public CardBoolAdapter(ArrayList<CardBool> cardabilitaList) {
        mCardBoolList = cardabilitaList;
    }

    @NonNull
    @Override
    public CardAbilitaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_skill_item, parent, false);
        return new CardAbilitaViewHolder(v, mListener);
    }

    @Override
    public void onBindViewHolder(CardAbilitaViewHolder holder, int position) {
        CardBool currentItem = mCardBoolList.get(position);
        if (currentItem != null) {
            holder.mNomeAbilitaView.setText(currentItem.getNome());
            if (currentItem.getaBoolean()) {
                holder.mColorePallinoCard.setVisibility(View.VISIBLE);
            } else {
                holder.mColorePallinoCard.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public int getItemCount() {
        return mCardBoolList.size();
    }

}


