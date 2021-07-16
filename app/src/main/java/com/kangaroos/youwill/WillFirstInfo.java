

package com.kangaroos.youwill;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.ramotion.circlemenu.CircleMenuView;

public class WillFirstInfo extends AppCompatActivity {

    Button button_main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_will_first_info);

        button_main = findViewById(R.id.button_main);

        ImageView imageView1 = (ImageView)findViewById(R.id.imageview1);
        ImageView imageView2 = (ImageView)findViewById(R.id.imageview2);
        ImageView imageView3 = (ImageView)findViewById(R.id.imageview3);
        ImageView imageView4 = (ImageView)findViewById(R.id.imageview4);
        ImageView imageView5 = (ImageView)findViewById(R.id.imageview5);

        TextView textView = (TextView)findViewById(R.id.textview1);

        imageView1.setImageResource(R.drawable.image1);
        imageView2.setImageResource(R.drawable.image2);
        imageView3.setImageResource(R.drawable.image3);
        imageView4.setImageResource(R.drawable.image4);
        imageView5.setImageResource(R.drawable.image5);


        ImageView imageView = (ImageView)findViewById(R.id.imageview1);


        imageView.setImageResource(R.drawable.image1);

        button_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), WillWrite.class);
                startActivity(intent);
            }
        });


        final CircleMenuView menu = findViewById(R.id.circle_menu);
        menu.setEventListener(new CircleMenuView.EventListener() {
            @Override
            public void onButtonClickAnimationStart(@NonNull CircleMenuView view, int index) {
                switch (index){
                    case 0:
                        Intent intent = new Intent(getApplicationContext(), MyPage.class);
                        startActivity(intent);
                        break;
                    case 1:
                        intent = new Intent(getApplicationContext(), LetterFirstInfo.class);
                        startActivity(intent);
                        break;
                    case 2:
                        intent = new Intent(getApplicationContext(), WillFirstInfo.class);
                        startActivity(intent);
                        break;
                    case 3:
                        intent = new Intent(getApplicationContext(), Healing.class);
                        startActivity(intent);
                        break;
                }

                Log.d("D", "onButtonClickAnimationStart| index: " + index);
            }
        });

    }
}





