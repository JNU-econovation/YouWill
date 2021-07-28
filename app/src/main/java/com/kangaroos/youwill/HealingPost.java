package com.kangaroos.youwill;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Transaction;

import java.util.ArrayList;
import java.util.HashMap;

import jp.wasabeef.richeditor.RichEditor;

public class HealingPost extends AppCompatActivity {
    String content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_healing_post);

        Intent intent = getIntent();


        String title = intent.getExtras().getString("title");
        content = intent.getExtras().getString("content");
        String date = intent.getExtras().getString("date");
        int likesCount = intent.getExtras().getInt("likesCount");
        String pushId = intent.getExtras().getString("pushId");

        TextView textView_title = findViewById(R.id.textView_post_title);
        RichEditor editor = findViewById(R.id.editor_healing);
        TextView textView_date = findViewById(R.id.textView_post_date);
        TextView textView_number = findViewById(R.id.textView_post_number);
        Button button_update = findViewById(R.id.button_healing_update);
        Button button_delete = findViewById(R.id.button_healing_delete);

        textView_title.setText(title);
        editor.setHtml(content);
        textView_date.setText(date);
        textView_number.setText(String.valueOf(likesCount));
        editor.setOnTextChangeListener(new RichEditor.OnTextChangeListener() {
            @Override
            public void onTextChange(String text) {
                content = text;
            }
        });

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();

        //데이터베이스에 힐링글 업데이트
        button_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onUpdateClicked(databaseReference.child("Healing").child(pushId));
                Toast.makeText(getApplicationContext(), "힐링글이 업데이트 되었습니다.", Toast.LENGTH_SHORT).show();
            }
        });

        //힐링글 삭제
        button_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference.child("Healing").child(pushId).removeValue();
                Toast.makeText(getApplicationContext(), "힐링글이 삭제되었습니다.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void onUpdateClicked(DatabaseReference postRef) {
        postRef.runTransaction(new Transaction.Handler() {
            @Override
            public Transaction.Result doTransaction(MutableData mutableData) {
                HealingItem p = mutableData.getValue(HealingItem.class);
                Log.d("TAG", mutableData.toString());
                if (p == null) {
                    Log.d("TAG", "Healing item is null");
                    return Transaction.success(mutableData);
                }

                p.content = content;

                mutableData.setValue(p);
                return Transaction.success(mutableData);
            }

            @Override
            public void onComplete(DatabaseError databaseError, boolean committed,
                                   DataSnapshot currentData) {
                // Transaction completed
                Log.d("TAG", "postTransaction:onComplete:" + databaseError);
            }
        });
    }
}