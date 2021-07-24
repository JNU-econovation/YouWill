package com.kangaroos.youwill;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MyPageDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_page_detail);
        Button button_my_post = findViewById(R.id.button_my_post);
        Button button_my_info = findViewById(R.id.button_my_info);

        Intent intent = getIntent();
        String mypageDetail = intent.getExtras().getString("mypage");

        if(mypageDetail.equals("info")){
            Fragment myInfoFragment = new MyInfoFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView2, myInfoFragment).commit();
        }

        button_my_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment myInfoFragment = new MyInfoFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView2, myInfoFragment).commit();
            }
        });

        button_my_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment myPostFragment = new MyPostFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView2, myPostFragment).commit();
            }
        });
    }
}