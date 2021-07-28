package com.kangaroos.youwill;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.widget.TextView;

import java.util.ArrayList;

import jp.wasabeef.richeditor.RichEditor;

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
        RichEditor editor = findViewById(R.id.editor_healing);
        TextView textView_date = findViewById(R.id.textView_post_date);
        TextView textView_number = findViewById(R.id.textView_post_number);

        textView_title.setText(title);
        editor.setHtml(content);
        textView_date.setText(date);
        textView_number.setText(String.valueOf(likesCount));
    }
}