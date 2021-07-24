package com.kangaroos.youwill;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MyPostAdapter extends RecyclerView.Adapter<MyPostAdapter.ViewHolder> {
    private ArrayList<PostItem> mData = null;

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView_content;
        TextView textView_date;

        ViewHolder(View itemView) {
            super(itemView);

            textView_content = itemView.findViewById(R.id.textView_mypost_content);
            textView_date = itemView.findViewById(R.id.textView_mypost_date);
        }
    }

    MyPostAdapter(ArrayList<PostItem> list) {
        mData = list;
    }

    @Override
    public MyPostAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.post_item, parent, false);
        MyPostAdapter.ViewHolder vh = new MyPostAdapter.ViewHolder(view);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyPostAdapter.ViewHolder holder, int position) {
        holder.textView_content.setText(mData.get(position).content);
        holder.textView_date.setText(mData.get(position).date);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
}
