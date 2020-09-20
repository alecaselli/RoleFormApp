package com.example.myfirstapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfirstapp.R;
import com.example.myfirstapp.utilities.CardGiocatore;

import java.util.ArrayList;

public class CardGiocatoreAdapter extends RecyclerView.Adapter<CardGiocatoreAdapter.CardGiocatoreViewHolder> {
    private ArrayList<CardGiocatore> mCardGiocatoreList;
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public static class CardGiocatoreViewHolder extends RecyclerView.ViewHolder {
        public ImageView mImageView;
        public TextView mNomeCampagnaView;
        public TextView mNomeGiocatoreView;
        public TextView mLivelloView;

        public CardGiocatoreViewHolder(View itemView, final OnItemClickListener listener) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.imageView);
            mNomeCampagnaView = itemView.findViewById(R.id.campaign_name);
            mNomeGiocatoreView = itemView.findViewById(R.id.character_name);
            mLivelloView = itemView.findViewById(R.id.level);

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

    public CardGiocatoreAdapter(ArrayList<CardGiocatore> cardgiocatoreList) {
        mCardGiocatoreList = cardgiocatoreList;
    }

    @NonNull
    @Override
    public CardGiocatoreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_character_item, parent, false);
        return new CardGiocatoreViewHolder(v, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull CardGiocatoreViewHolder holder, int position) {
        CardGiocatore currentItem = mCardGiocatoreList.get(position);
        if (currentItem != null) {
            holder.mImageView.setImageResource(currentItem.getmImageResource());
            holder.mNomeCampagnaView.setText(currentItem.getNomecampagna());
            holder.mNomeGiocatoreView.setText(currentItem.getNomegiocatore());
            holder.mLivelloView.setText(currentItem.getLivellogiocatore());
        }
    }

    @Override
    public int getItemCount() {
        return mCardGiocatoreList.size();
    }
}
