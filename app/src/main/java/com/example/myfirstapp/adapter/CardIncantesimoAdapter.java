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
import com.example.myfirstapp.utilities.CardIncantesimo;

import java.util.ArrayList;

public class CardIncantesimoAdapter extends RecyclerView.Adapter<CardIncantesimoAdapter.CardIncantesimoViewHolder> {
    private ArrayList<CardIncantesimo> mCardIncantesimoList;
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);

        void onBoolClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public static class CardIncantesimoViewHolder extends RecyclerView.ViewHolder {
        public TextView mNomeIncantesimoView;
        public RelativeLayout mIncantesimoButton;
        public ImageView mIncantesimoImage;


        public CardIncantesimoViewHolder(View itemView, final OnItemClickListener listener) {
            super(itemView);
            mNomeIncantesimoView = itemView.findViewById(R.id.spell_name);
            mIncantesimoButton = itemView.findViewById(R.id.spell_ball_button);
            mIncantesimoImage = itemView.findViewById(R.id.spell_ball);

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

            mIncantesimoButton.setOnClickListener(new View.OnClickListener() {
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

    public CardIncantesimoAdapter(ArrayList<CardIncantesimo> cardincantesimoList) {
        mCardIncantesimoList = cardincantesimoList;
    }

    @NonNull
    @Override
    public CardIncantesimoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_spell_item, parent, false);
        return new CardIncantesimoViewHolder(v, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull CardIncantesimoAdapter.CardIncantesimoViewHolder holder, int position) {
        CardIncantesimo currentItem = mCardIncantesimoList.get(position);
        if (currentItem != null) {
            holder.mNomeIncantesimoView.setText(currentItem.getNomeincantesimo());
            if (currentItem.getaBoolean()) {
                holder.mIncantesimoImage.setBackgroundResource(R.drawable.ic_round_radio_button);
            } else {
                holder.mIncantesimoImage.setBackgroundResource(R.drawable.ic_round_radio_button_unchecked);
            }
        }
    }

    @Override
    public int getItemCount() {
        return mCardIncantesimoList.size();
    }

}
