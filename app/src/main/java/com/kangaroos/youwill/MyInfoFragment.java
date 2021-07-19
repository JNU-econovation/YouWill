package com.kangaroos.youwill;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MyInfoFragment extends Fragment {
    private FirebaseAuth mAuth;
    Button buttonLogout, buttonExit;
    Button button_find_password;
    FirebaseUser user;
    final String[] userInfo = new String[2];

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MyInfoFragment() {
        // Required empty public constructor
    }

    public static MyInfoFragment newInstance(String param1, String param2) {
        MyInfoFragment fragment = new MyInfoFragment();
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
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_my_info, container, false);

        mAuth = FirebaseAuth.getInstance();
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference();
        user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();
        DatabaseReference userReference = databaseReference.child("Users").child(uid);

        //유저 정보 얻어오기
        userReference.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                userInfo[0] = task.getResult().child("email").getValue(String.class);
                userInfo[1] = task.getResult().child("name").getValue(String.class);
                Log.d("userTest", userInfo[0] + " " + userInfo[1]);
            }
        });

        buttonLogout = rootView.findViewById(R.id.button_move_my_post);
        buttonExit = rootView.findViewById(R.id.button_move_my_info);
        button_find_password = rootView.findViewById(R.id.button_find_password);

        button_find_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findPassword();
            }
        });
        buttonLogout.setOnClickListener(this::onClick);
        buttonExit.setOnClickListener(this::onClick);


        return rootView;
    }

    private void findPassword() {
        String emailAddress = userInfo[0];
        mAuth.sendPasswordResetEmail(emailAddress).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(getContext(), "이메일을 보냈습니다.", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getContext(), "메일 보내기에 실패했습니다. 다시 시도해주세요.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void signOut() {
        Toast.makeText(getContext(), "로그아웃하였습니다.", Toast.LENGTH_LONG).show();
        startActivity(new Intent(getContext(), LoginActivity.class));
        FirebaseAuth.getInstance().signOut();
    }

    private void revokeAccess() {
        user.delete()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(getContext(), "계정이 삭제 되었습니다.", Toast.LENGTH_LONG).show();
                        FirebaseAuth.getInstance().signOut();
                        startActivity(new Intent(getContext(), LoginActivity.class));
                    }
                });
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_move_my_post:
                signOut();
                break;
            case R.id.button_move_my_info:
                revokeAccess();
                break;
        }
    }
}