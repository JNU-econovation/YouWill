package com.kangaroos.youwill;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
<<<<<<< Updated upstream

=======
import android.widget.ImageView;
import android.widget.TextView;
>>>>>>> Stashed changes


public class LetterWrite extends AppCompatActivity {
    Button button_sub;

    @Override
<<<<<<< Updated upstream
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_letter_write);

=======
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_letter_write);

        button_sub = findViewById(R.id.button_sub);
        ImageView imageView = (ImageView)findViewById(R.id.imageview1);
        TextView textView = (TextView)findViewById(R.id.textview1);

        imageView.setImageResource(R.drawable.image1);
        button_sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LetterWrite.class);
                startActivity(intent);
            }
        });
>>>>>>> Stashed changes

    }

}