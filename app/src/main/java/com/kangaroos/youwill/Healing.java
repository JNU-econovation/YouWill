package com.kangaroos.youwill;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.ramotion.circlemenu.CircleMenuView;

import java.util.ArrayList;

public class Healing extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_healing);

        Button button_recent = findViewById(R.id.button_recent);
        button_recent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment healingFragment = new HealingFragment();
                Bundle bundle=new Bundle();
                bundle.putString("button", "recent");
                healingFragment.setArguments(bundle);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView, healingFragment).commit();
            }
        });

        Button button_popular = findViewById(R.id.button_popular);
        button_popular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment healingFragment = new HealingFragment();
                Bundle bundle=new Bundle();
                bundle.putString("button","popular");
                healingFragment.setArguments(bundle);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView, healingFragment).commit();
            }
        });

        Button button_write = findViewById(R.id.button_write);
        button_write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), HealingWrite.class);
                startActivity(intent);
            }
        });

        final CircleMenuView menu = findViewById(R.id.circle_menu);
        menu.setEventListener(new CircleMenuView.EventListener() {
            @Override
            public void onMenuOpenAnimationStart(@NonNull CircleMenuView view) {
                Log.d("D", "onMenuOpenAnimationStart");
            }

            @Override
            public void onMenuOpenAnimationEnd(@NonNull CircleMenuView view) {
                Log.d("D", "onMenuOpenAnimationEnd");
            }

            @Override
            public void onMenuCloseAnimationStart(@NonNull CircleMenuView view) {
                Log.d("D", "onMenuCloseAnimationStart");
            }

            @Override
            public void onMenuCloseAnimationEnd(@NonNull CircleMenuView view) {
                Log.d("D", "onMenuCloseAnimationEnd");
            }

            @Override
            public void onButtonClickAnimationStart(@NonNull CircleMenuView view, int index) {
                switch (index){
                    case 0:
                        Intent intent = new Intent(getApplicationContext(), MyPage.class);
                        startActivity(intent);
                        break;
                    case 1:
                        intent = new Intent(getApplicationContext(), LetterChoice.class);
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

            @Override
            public void onButtonClickAnimationEnd(@NonNull CircleMenuView view, int index) {
                Log.d("D", "onButtonClickAnimationEnd| index: " + index);
            }

            @Override
            public boolean onButtonLongClick(@NonNull CircleMenuView view, int index) {
                Log.d("D", "onButtonLongClick| index: " + index);
                return true;
            }

            @Override
            public void onButtonLongClickAnimationStart(@NonNull CircleMenuView view, int index) {
                Log.d("D", "onButtonLongClickAnimationStart| index: " + index);
            }

            @Override
            public void onButtonLongClickAnimationEnd(@NonNull CircleMenuView view, int index) {
                Log.d("D", "onButtonLongClickAnimationEnd| index: " + index);
            }
        });
    }
}