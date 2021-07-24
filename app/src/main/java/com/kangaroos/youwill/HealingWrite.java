package com.kangaroos.youwill;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

public class HealingWrite extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_healing_write);

        Date currentTime = new Date(System.currentTimeMillis());
        SimpleDateFormat dayFormat = new SimpleDateFormat("dd", Locale.getDefault());
        SimpleDateFormat monthFormat = new SimpleDateFormat("MM", Locale.getDefault());
        SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy", Locale.getDefault());

        String year = yearFormat.format(currentTime);
        String month = monthFormat.format(currentTime);
        String day = dayFormat.format(currentTime);

        String today = year + "년 " + month + "월 " + day + "일 ";
        String date = year + month + day;

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();

        Button button_submit = findViewById(R.id.button_healing_submit);
        EditText editText_title = findViewById(R.id.edittext_healing_title);
        EditText editText_content = findViewById(R.id.edittext_healing_content);


        //데이터베이스에 일기 저장
        button_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap<String, Boolean> hm = new HashMap<String, Boolean>();
                hm.put("userId", true);
                String id = databaseReference.child("Healing").push().getKey();

                databaseReference.child("Healing").child(id).setValue(new HealingItem(editText_title.getText().toString(), editText_content.getText().toString(),date, hm, 0, id, uid));
                Toast.makeText(getApplicationContext(), "힐링글 저장되었습니다.", Toast.LENGTH_SHORT).show();
            }
        });

                /*
        //데이터베이스에서 일기 읽어오기
        databaseReference.child("Healing").child(uid).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data", task.getException());
                } else {
                    Log.d("firebase", String.valueOf(task.getResult().getValue()));
                    String diary = String.valueOf(task.getResult().getValue());
                    if (!task.getResult().exists()) {

                    } else {

                    }

                }
            }
        });*/
    }
}