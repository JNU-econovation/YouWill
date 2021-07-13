package com.kangaroos.youwill;

import android.graphics.Rect;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class HealingFragment extends Fragment {

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

        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_healing, container, false);

        ArrayList<HealingItem> list = new ArrayList<>();
        for(int i=0;i<50;i++){
            list.add(new HealingItem("제목","내용","날짜", 1));
        }
        if(getArguments() != null) {
            list = new ArrayList<>();
            list = getArguments().getParcelableArrayList("recentList");
        }

        RecyclerView recyclerView = rootView.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));

        HealingItemAdapter adpater = new HealingItemAdapter(list);
        recyclerView.setAdapter(adpater);
        recyclerView.addItemDecoration(new RecyclerViewDecoration(30,30));

        return rootView;
    }
}

//리사이클러뷰 아이템 간 간격 조절하는 클래스
class RecyclerViewDecoration extends RecyclerView.ItemDecoration {

    private final int divHeight;
    private final int divWidth;

    public RecyclerViewDecoration(int divHeight,int divWidth)
    {
        this.divHeight = divHeight;
        this.divWidth = divWidth;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state)
    {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.top = divHeight;
        outRect.right = divWidth;
    }
}