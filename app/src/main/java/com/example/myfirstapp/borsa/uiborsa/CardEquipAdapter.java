package com.example.myfirstapp.borsa.uiborsa;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfirstapp.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class CardEquipAdapter extends RecyclerView.Adapter<CardEquipAdapter.CardEquipViewHolder>{
    private ArrayList<CardEquip> mCardEquipList;
    private CardEquipAdapter.OnItemClickListener mListener;
    private final String ARMA = "arma";
    private final String ARMATURA = "armatura";
    private final String SCUDO = "scudo";


    public interface OnItemClickListener {
        void onItemClick(int position);
        void onEquipClick(int position);
        void onRemoveClick(int position);
    }

    public void setOnItemClickListener(CardEquipAdapter.OnItemClickListener listener) {
        mListener = listener;
    }

    public static class CardEquipViewHolder extends RecyclerView.ViewHolder {
        public TextView mNomeEquipView;
        public ImageView mTipoImage;
        public CardView mEquipCard;
        public CardView mRemoveCard;
        
        
        public CardEquipViewHolder(View itemView, final CardEquipAdapter.OnItemClickListener listener) {
            super(itemView);
            mNomeEquipView = itemView.findViewById(R.id.equip_name);
            mTipoImage = itemView.findViewById(R.id.equip_type);
            mEquipCard = itemView.findViewById(R.id.equip_equip);
            mRemoveCard = itemView.findViewById(R.id.equip_remove);

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

            mEquipCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onEquipClick(position);
                        }
                    }
                }
            });

            mRemoveCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onRemoveClick(position);
                        }
                    }
                }
            });

        }
    }

    public CardEquipAdapter(ArrayList<CardEquip> cardEquipList) {
        mCardEquipList = cardEquipList;
    }

    @NonNull
    @Override
    public CardEquipAdapter.CardEquipViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_equip_item, parent, false);
        return new CardEquipAdapter.CardEquipViewHolder(v, mListener);
    }

    @Override
    public void onBindViewHolder(@NotNull CardEquipAdapter.CardEquipViewHolder holder, int position) {
        CardEquip currentItem = mCardEquipList.get(position);
        if (currentItem != null) {
            holder.mNomeEquipView.setText(currentItem.getNome());
            switch (currentItem.getTipo()){
                case ARMA:
                    holder.mTipoImage.setImageResource(R.drawable.equip_sword);
                    break;
                case ARMATURA:
                    holder.mTipoImage.setImageResource(R.drawable.equip_breastplate);
                    break;
                case SCUDO:
                    holder.mTipoImage.setImageResource(R.drawable.equip_shield);
                    break;
                default:
                    holder.mTipoImage.setImageResource(R.drawable.equip_box);
                    break;
            }
        }
    }

    @Override
    public int getItemCount() {
        return mCardEquipList.size();
    }

}
