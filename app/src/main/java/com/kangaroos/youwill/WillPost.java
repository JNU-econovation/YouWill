package com.kangaroos.youwill;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import jp.wasabeef.richeditor.RichEditor;

public class WillPost extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_will_post);

        Intent intent = getIntent();

        String content = intent.getExtras().getString("content");
        String date = intent.getExtras().getString("date");

        TextView textView_date = findViewById(R.id.textView_mywill_date);
        RichEditor editor = findViewById(R.id.editor_will);

        textView_date.setText(date);
        editor.setHtml(content);
    }
}