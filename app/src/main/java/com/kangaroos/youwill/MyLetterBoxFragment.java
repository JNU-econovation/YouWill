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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MyLetterBoxFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyLetterBoxFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MyLetterBoxFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MyLetterBoxFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MyLetterBoxFragment newInstance(String param1, String param2) {
        MyLetterBoxFragment fragment = new MyLetterBoxFragment();
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
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_my_letter_box, container, false);
        ArrayList<PostItem> list = new ArrayList<>();
        ArrayList<LetterItem> letterList = new ArrayList<>();
        MyPostAdapter adapter = new MyPostAdapter(list);

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();

        databaseReference.child("LetterBox").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                letterList.clear();
                Log.d("letter","user email "+user.getEmail());
                for (DataSnapshot fileSnapshot : snapshot.getChildren()) {
                    LetterItem item = fileSnapshot.child(uid).getValue(LetterItem.class);
                    if(item!=null){
                        Log.d("letter", item.toString());
                        list.add(0, new PostItem("", item.write_date));
                        letterList.add(0, new LetterItem(item.recipient_number, item.write_date,item.send_date, item.paper_type, item.content));
                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        RecyclerView recyclerView = rootView.findViewById(R.id.recyclerView_myletterbox);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        recyclerView.setAdapter(adapter);

        adapter.setOnItemClicklistener(new MyPostAdapter.OnMyPostItemClickListener() {
            @Override
            public void onItemClick(MyPostAdapter.ViewHolder holder, View view, int position) {
                Intent intent = new Intent(getContext(), LetterPost.class);
                intent.putExtra("content", letterList.get(position).getContent());
                intent.putExtra("date", letterList.get(position).getWrite_date());
                intent.putExtra("letterNumber", letterList.get(position).getPaper_type());
                startActivity(intent);
            }
        });

        return rootView;
    }
}