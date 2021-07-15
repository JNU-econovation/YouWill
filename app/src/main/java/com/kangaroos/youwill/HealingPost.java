package com.kangaroos.youwill;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class HealingPost extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_healing_post);

        Intent intent = getIntent();
        HealingItem item = intent.getParcelableExtra("post");

        TextView textView_title = findViewById(R.id.textView_post_title);
        TextView textView_content = findViewById(R.id.textView_post_content);
        TextView textView_date = findViewById(R.id.textView_post_date);
        TextView textView_number = findViewById(R.id.textView_post_number);

        textView_title.setText(item.getTitle());
        textView_content.setText(item.getContent());
        textView_date.setText(item.getDate());
        textView_number.setText(String.valueOf(item.getNumber()));
    }
}