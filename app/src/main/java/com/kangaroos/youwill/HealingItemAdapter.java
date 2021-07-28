package com.kangaroos.youwill;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.utils.widget.ImageFilterView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Transaction;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import jp.wasabeef.richeditor.RichEditor;

public class HealingItemAdapter extends RecyclerView.Adapter<HealingItemAdapter.ViewHolder> {

    private ArrayList<HealingItem> mData = null;
    OnHealingItemClickListener listener;
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference = firebaseDatabase.getReference();
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    String uid = user.getUid();

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
        RichEditor textView_content;
        TextView textView_date;
        TextView textView_number;
        ImageView button_like;


        ViewHolder(View itemView) {
            super(itemView);

            textView_title = itemView.findViewById(R.id.textView_title);
            textView_content = itemView.findViewById(R.id.editor_healing_item);
            textView_date = itemView.findViewById(R.id.textView_date);
            textView_number = itemView.findViewById(R.id.textView_number);
            button_like = itemView.findViewById(R.id.button_like);

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
        HealingItem item = new HealingItem(mData.get(position).getTitle(), mData.get(position).getContent(), mData.get(position).getDate(), mData.get(position).getLikes(), mData.get(position).getLikesCount() + 1, mData.get(position).id, mData.get(position).uid);
        holder.textView_title.setText(mData.get(position).getTitle());
        holder.textView_content.setHtml(mData.get(position).getContent());
        holder.textView_number.setText(String.valueOf(mData.get(position).likesCount));
        holder.textView_date.setText(mData.get(position).getDate());

        if (mData.get(position).getLikes().containsKey(uid)) {
            holder.button_like.setImageResource(R.drawable.filled);
        } else {
            holder.button_like.setImageResource(R.drawable.likeblank);
        }
        holder.button_like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onStarClicked(databaseReference.child("Healing").child(item.id));
            }
        });
    }


    @Override
    public int getItemCount() {
        return mData.size();
    }

    public HealingItem getItem(int position) {
        return mData.get(position);
    }

    private void onStarClicked(DatabaseReference postRef) {
        postRef.runTransaction(new Transaction.Handler() {
            @Override
            public Transaction.Result doTransaction(MutableData mutableData) {
                HealingItem p = mutableData.getValue(HealingItem.class);
                Log.d("TAG", mutableData.toString());
                if (p == null) {
                    Log.d("TAG", "Healing item is null");
                    return Transaction.success(mutableData);
                }

                if (p.getLikes().containsKey(uid)) {
                    // Unstar the post and remove self from stars
                    p.setLikesCount(p.getLikesCount() - 1);
                    p.getLikes().remove(uid);
                    Log.d("TAG", "likes count:" + p.likesCount);
                } else {
                    // Star the post and add self to stars
                    p.setLikesCount(p.getLikesCount() + 1);
                    p.getLikes().put(uid, true);
                }

                // Set value and report transaction success
                mutableData.setValue(p);
                return Transaction.success(mutableData);
            }

            @Override
            public void onComplete(DatabaseError databaseError, boolean committed,
                                   DataSnapshot currentData) {
                // Transaction completed
                Log.d("TAG", "postTransaction:onComplete:" + databaseError);
            }
        });
    }
}
