package com.kangaroos.youwill;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


public class LetterFirstInfo extends AppCompatActivity {

    Button button_main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_letter_first_info);

        button_main = findViewById(R.id.button_main);
        ImageView imageView = (ImageView)findViewById(R.id.imageview1);
        TextView textView = (TextView)findViewById(R.id.textview1);

        imageView.setImageResource(R.drawable.image1);
        button_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LetterChoice.class);
                startActivity(intent);
            }
        });

    }

}