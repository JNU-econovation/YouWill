package com.kangaroos.youwill;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

public class HealingFragment extends Fragment {
    public static final String TAG = "healingItemTest";
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference = firebaseDatabase.getReference();
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    String uid = user.getUid();

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HealingFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static HealingFragment newInstance(String param1, String param2) {
        HealingFragment fragment = new HealingFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_healing, container, false);

        ArrayList<HealingItem> list = new ArrayList<>();

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference().child("Healing");
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();

        ArrayList<HealingItem> finalList = list;
        HealingItemAdapter adapter = new HealingItemAdapter(finalList);

        String category = "";
        if (getArguments() != null) {
            category = getArguments().getString("button");
        }

        Comparator<HealingItem> comparator = new Comparator<HealingItem>() {
            @Override
            public int compare(HealingItem o1, HealingItem o2) {
                if (o1.likesCount < o2.likesCount) {
                    return 1;
                }
                if (o1.likesCount > o2.likesCount) {
                    return -1;
                }
                return 0;
            }
        };

        //최신순으로 정렬
        if (category.equals("recent")) {
            finalList.clear();
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    finalList.clear();
                    for (DataSnapshot fileSnapshot : snapshot.getChildren()) {
                        HealingItem item = fileSnapshot.getValue(HealingItem.class);
                        if (item != null) {
                            finalList.add(0, new HealingItem(item.title, item.content, item.date, item.likes, item.likesCount, item.id, item.uid));
                            Log.d(TAG, item.title);
                            Log.d(TAG, item.content);
                            Log.d(TAG, item.date);
                            Log.d(TAG, String.valueOf(item.likesCount));
                        }
                    }
                    adapter.notifyDataSetChanged();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }


        //인기순으로 어뎁터 넣기
        if (category.equals("popular") || category.equals("")) {
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    finalList.clear();
                    for (DataSnapshot fileSnapshot : snapshot.getChildren()) {
                        HealingItem item = fileSnapshot.getValue(HealingItem.class);
                        if (item != null) {
                            finalList.add(0, new HealingItem(item.title, item.content, item.date, item.likes, item.likesCount, item.id, item.uid));
                            Log.d(TAG, item.title);
                            Log.d(TAG, item.content);
                            Log.d(TAG, item.date);
                            Log.d(TAG, String.valueOf(item.likesCount));
                        }
                    }
                    Collections.sort(finalList, comparator);
                    adapter.notifyDataSetChanged();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }

        Log.d(TAG, "view");

        RecyclerView recyclerView = rootView.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));

        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new RecyclerViewDecoration(30, 30));

        adapter.setOnItemClicklistener(new HealingItemAdapter.OnHealingItemClickListener() {
            @Override
            public void onItemClick(HealingItemAdapter.ViewHolder holder, View view, int position) {
                HealingItem item = adapter.getItem(position);

                Intent intent = new Intent(getContext(), HealingPost.class);
                intent.putExtra("title", finalList.get(position).getTitle());
                intent.putExtra("content", finalList.get(position).getContent());
                intent.putExtra("date", finalList.get(position).getDate());
                intent.putExtra("likesCount", finalList.get(position).getLikesCount());
                startActivity(intent);
            }
        });

        return rootView;
    }
}

//리사이클러뷰 아이템 간 간격 조절하는 클래스
class RecyclerViewDecoration extends RecyclerView.ItemDecoration {

    private final int divHeight;
    private final int divWidth;

    public RecyclerViewDecoration(int divHeight, int divWidth) {
        this.divHeight = divHeight;
        this.divWidth = divWidth;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.top = divHeight;
        outRect.right = divWidth;
    }
}