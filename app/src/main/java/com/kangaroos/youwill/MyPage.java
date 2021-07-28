package com.kangaroos.youwill;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class MyPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_page);

        Button button_move_to_my_info = findViewById(R.id.button_move_my_info);
        Button button_move_to_my_post = findViewById(R.id.button_move_my_post);
        Button button_move_to_letterbox = findViewById(R.id.button_move_letterbox);

        button_move_to_my_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyPage.this, MyPageDetail.class);
                intent.putExtra("mypage", "info");
                startActivity(intent);
            }
        });
        button_move_to_my_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyPage.this, MyPageDetail.class);
                intent.putExtra("mypage", "post");
                startActivity(intent);
            }
        });

        button_move_to_letterbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyPage.this, MyPageDetail.class);
                intent.putExtra("mypage", "letterbox");
                startActivity(intent);
            }
        });
    }
}