package com.kangaroos.youwill;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MyPostFragment extends Fragment {
    static String category;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MyPostFragment() {
        // Required empty public constructor
    }

    public static MyPostFragment newInstance(String param1, String param2) {
        MyPostFragment fragment = new MyPostFragment();
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
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_my_post, container, false);

        TextView textView_will = rootView.findViewById(R.id.textView_will);
        TextView textView_letter = rootView.findViewById(R.id.textView_letter);
        TextView textView_healing = rootView.findViewById(R.id.textView_healing);

        String TAG = "mypost";

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();

        ArrayList<PostItem> list = new ArrayList<>();
        ArrayList<HealingItem> healingList = new ArrayList<>();
        ArrayList<WillItem> willList = new ArrayList<>();
        ArrayList<LetterItem> letterList = new ArrayList<>();

        MyPostAdapter adapter = new MyPostAdapter(list);

        category = "Will";
        databaseReference.child("Will").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for (DataSnapshot fileSnapshot : snapshot.getChildren()) {
                    Log.d(TAG, fileSnapshot.toString());
                    WillItem item = fileSnapshot.child(uid).getValue(WillItem.class);
                    if (item != null) {
                        list.add(0, new PostItem("", item.date));
                        willList.add(0, new WillItem(item.date, item.content, item.id));
                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //유서 클릭 이벤트
        textView_will.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                category = "Will";

                databaseReference.child("Will").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        list.clear();
                        for (DataSnapshot fileSnapshot : snapshot.getChildren()) {
                            Log.d(TAG, fileSnapshot.toString());
                            WillItem item = fileSnapshot.child(uid).getValue(WillItem.class);
                            if (item != null) {
                                list.add(0, new PostItem("", item.date));
                                willList.add(0, new WillItem(item.date, item.content, item.id));
                            }
                        }
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

        //편지 클릭 이벤트
        textView_letter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                category = "Letter";
                databaseReference.child("Letter").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        list.clear();
                        for (DataSnapshot fileSnapshot : snapshot.getChildren()) {
                            Log.d(TAG, fileSnapshot.toString());
                            LetterItem item = fileSnapshot.child(uid).getValue(LetterItem.class);
                            if (item != null) {
                                list.add(0, new PostItem("", item.write_date));
                                letterList.add(0, new LetterItem(item.recipient_number, item.write_date, item.send_date, item.paper_type, item.content));
                            }
                        }
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

        //힐링 클릭 이벤트
        textView_healing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                category = "Healing";
                databaseReference.child("Healing").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        list.clear();
                        for (DataSnapshot fileSnapshot : snapshot.getChildren()) {
                            HealingItem item = fileSnapshot.getValue(HealingItem.class);
                            if (item.uid.equals(uid)) {
                                Log.d("post", "ㄹㅣ스트 들어옴");
                                list.add(0, new PostItem(item.title, item.date));
                                healingList.add(0, new HealingItem(item.getTitle(), item.getContent(), item.getDate(), item.getLikes(), item.getLikesCount(), item.id, item.uid));
                            }
                        }
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

        RecyclerView recyclerView = rootView.findViewById(R.id.recyclerView_myletterbox);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        recyclerView.setAdapter(adapter);
        adapter.setOnItemClicklistener(new MyPostAdapter.OnMyPostItemClickListener() {
            @Override
            public void onItemClick(MyPostAdapter.ViewHolder holder, View view, int position) {
                PostItem item = adapter.getItem(position);

                if (category.equals("Will")) {
                    Intent intent = new Intent(getContext(), WillPost.class);
                    intent.putExtra("content", willList.get(position).getContent());
                    intent.putExtra("date", willList.get(position).getDate());
                    intent.putExtra("pushId", willList.get(position).getId());
                    startActivity(intent);
                    return;
                }

                if (category.equals("Letter")) {
                    Intent intent = new Intent(getContext(), LetterPost.class);
                    intent.putExtra("content", letterList.get(position).getContent());
                    intent.putExtra("date", letterList.get(position).getWrite_date());
                    intent.putExtra("letterNumber", letterList.get(position).getPaper_type());
                    startActivity(intent);
                    return;
                }

                if (category.equals("Healing")) {
                    Intent intent = new Intent(getContext(), HealingPost.class);
                    intent.putExtra("title", healingList.get(position).getTitle());
                    intent.putExtra("content", healingList.get(position).getContent());
                    intent.putExtra("date", healingList.get(position).getDate());
                    intent.putExtra("likesCount", healingList.get(position).getLikesCount());
                    intent.putExtra("pushId", healingList.get(position).getId());
                    startActivity(intent);
                    return;
                }
            }
        });

        return rootView;
    }

}