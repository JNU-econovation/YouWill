package com.kangaroos.youwill;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import jp.wasabeef.richeditor.RichEditor;

public class LetterPost extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_letter_post);

        Intent intent = getIntent();

        String content = intent.getExtras().getString("content");
        String date = intent.getExtras().getString("date");
        int letterNumber = intent.getExtras().getInt("letterNumber");
        RichEditor editor = findViewById(R.id.editor_letter);

        TextView textView_date = findViewById(R.id.textView_letter_date);

        String healingDate = date.substring(0,4)+"년 "+date.substring(4,6)+"월 "+date.substring(6,8)+"일";
        textView_date.setText(healingDate);
        editor.setHtml(content);
        editor.setInputEnabled(false);

        if (letterNumber == 0) {
            editor.setBackgroundResource(R.drawable.paper1);
        } else if (letterNumber == 1) {
            editor.setBackgroundResource(R.drawable.paper2);
        } else if (letterNumber == 2) {
            editor.setBackgroundResource(R.drawable.paper3);
        }

    }
}