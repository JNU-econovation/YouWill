package com.kangaroos.youwill;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


public class LetterChoice extends AppCompatActivity {

    Button button_write;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_letter_choice);

        button_write = findViewById(R.id.button_write);
        ImageView imageView = (ImageView)findViewById(R.id.imageview1);
        TextView textView = (TextView)findViewById(R.id.textview1);


        button_write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LetterWrite.class);
                startActivity(intent);
            }
        });
    }

}
