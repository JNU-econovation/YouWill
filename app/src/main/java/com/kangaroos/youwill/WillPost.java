package com.kangaroos.youwill;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
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

import jp.wasabeef.richeditor.RichEditor;

public class WillPost extends AppCompatActivity {
    String content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_will_post);

        Intent intent = getIntent();

       content = intent.getExtras().getString("content");
        String date = intent.getExtras().getString("date");
        String pushId = intent.getExtras().getString("pushId");

        TextView textView_date = findViewById(R.id.textView_mywill_date);
        RichEditor editor = findViewById(R.id.editor_will);
        Button button_update = findViewById(R.id.button_will_update);
        Button button_delete = findViewById(R.id.button_will_delete);

        String healingDate = date.substring(0,4)+"년 "+date.substring(4,6)+"월 "+date.substring(6,8)+"일";
        textView_date.setText(healingDate);
        editor.setHtml(content);

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

        //데이터베이스에 유서 업데이트
        button_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onUpdateClicked(databaseReference.child("Will").child(pushId).child(uid));
                Toast.makeText(getApplicationContext(), "유서가 업데이트 되었습니다.", Toast.LENGTH_SHORT).show();
            }
        });

        //유서글 삭제
        button_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference.child("Will").child(pushId).child(uid).removeValue();
                Toast.makeText(getApplicationContext(), "유서가 삭제되었습니다.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void onUpdateClicked(DatabaseReference postRef) {
        postRef.runTransaction(new Transaction.Handler() {
            @Override
            public Transaction.Result doTransaction(MutableData mutableData) {
                WillItem p = mutableData.getValue(WillItem.class);
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