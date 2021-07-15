package com.kangaroos.youwill;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HealingItemAdapter extends RecyclerView.Adapter<HealingItemAdapter.ViewHolder> {

    private ArrayList<HealingItem> mData = null;
    OnHealingItemClickListener listener;

    public void onItemClick(ViewHolder holder, View view, int position) {
        if (listener != null) {
            listener.onItemClick(holder, view, position);
        }
    }

    public void setOnItemClicklistener(OnHealingItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnHealingItemClickListener {
        public void onItemClick(HealingItemAdapter.ViewHolder holder, View view, int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView_title;
        TextView textView_content;
        TextView textView_date;
        TextView textView_number;


        ViewHolder(View itemView) {
            super(itemView);

            textView_title = itemView.findViewById(R.id.textView_title);
            textView_content = itemView.findViewById(R.id.textView_content);
            textView_date = itemView.findViewById(R.id.textView_date);
            textView_number = itemView.findViewById(R.id.textView_number);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(ViewHolder.this, v, position);
                    }
                }
            });
        }
    }

    HealingItemAdapter(ArrayList<HealingItem> list) {
        mData = list;
    }

    @NonNull
    @Override
    public HealingItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.healing_item, parent, false);
        HealingItemAdapter.ViewHolder vh = new HealingItemAdapter.ViewHolder(view);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull HealingItemAdapter.ViewHolder holder, int position) {
        holder.textView_title.setText(mData.get(position).getTitle());
        holder.textView_content.setText(mData.get(position).getContent());
        holder.textView_number.setText(String.valueOf(mData.get(position).getNumber()));
        holder.textView_date.setText(mData.get(position).getDate());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public HealingItem getItem(int position) {
        return mData.get(position);
    }
}
