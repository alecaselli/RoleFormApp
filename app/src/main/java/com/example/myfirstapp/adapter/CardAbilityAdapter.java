package com.example.myfirstapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfirstapp.R;
import com.example.myfirstapp.utilities.CardAbility;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class CardAbilityAdapter extends RecyclerView.Adapter<CardAbilityAdapter.CardAbilitaViewHolder> {
    private ArrayList<CardAbility> mCardAbilityList;
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
        public RelativeLayout mPallinoButton;
        public ImageView mPallinoImage;


        public CardAbilitaViewHolder(View itemView, final OnItemClickListener listener) {
            super(itemView);
            mNomeAbilitaView = itemView.findViewById(R.id.skill_name);
            mPallinoButton = itemView.findViewById(R.id.skill_ball_button);
            mPallinoImage = itemView.findViewById(R.id.skill_ball);

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

            mPallinoButton.setOnClickListener(new View.OnClickListener() {
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

    public CardAbilityAdapter(ArrayList<CardAbility> cardabilitaList) {
        mCardAbilityList = cardabilitaList;
    }

    @NonNull
    @Override
    public CardAbilitaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_ability_item, parent, false);
        return new CardAbilitaViewHolder(v, mListener);
    }

    @Override
    public void onBindViewHolder(@NotNull CardAbilitaViewHolder holder, int position) {
        CardAbility currentItem = mCardAbilityList.get(position);
        if (currentItem != null) {
            holder.mNomeAbilitaView.setText(currentItem.getNome());
            if (currentItem.getaBoolean()) {
                holder.mPallinoImage.setBackgroundResource(R.drawable.ic_round_radio_button);
            } else {
                holder.mPallinoImage.setBackgroundResource(R.drawable.ic_round_radio_button_unchecked);
            }
        }
    }

    @Override
    public int getItemCount() {
        return mCardAbilityList.size();
    }

}


