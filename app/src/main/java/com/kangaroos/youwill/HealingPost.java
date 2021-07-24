package com.kangaroos.youwill;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.widget.TextView;

import java.util.ArrayList;

public class HealingPost extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_healing_post);

        Intent intent = getIntent();


        String title = intent.getExtras().getString("title");
        String content = intent.getExtras().getString("content");
        String date = intent.getExtras().getString("date");
        int likesCount = intent.getExtras().getInt("likesCount");

        TextView textView_title = findViewById(R.id.textView_post_title);
        TextView textView_content = findViewById(R.id.textView_post_content);
        TextView textView_date = findViewById(R.id.textView_post_date);
        TextView textView_number = findViewById(R.id.textView_post_number);

        textView_title.setText(title);
        textView_content.setText(content);
        textView_date.setText(date);
        textView_number.setText(String.valueOf(likesCount));

        textView_title.setText(title);
        textView_content.setText(content);
        textView_date.setText(date);
        textView_number.setText(String.valueOf(likesCount));
    }
}