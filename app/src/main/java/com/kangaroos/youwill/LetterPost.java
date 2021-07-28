package com.kangaroos.youwill;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
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
        RichEditor editor = findViewById(R.id.editor);

        TextView textView_content = findViewById(R.id.textView_letter_content);
        TextView textView_date = findViewById(R.id.textView_letter_date);
        ImageView imageView = findViewById(R.id.imageView_letter);

        textView_content.setText(content);
        textView_date.setText(date);
        editor.setHtml(content);
        //이미지 뷰 셋 코드 추가해야 함.
    }
}