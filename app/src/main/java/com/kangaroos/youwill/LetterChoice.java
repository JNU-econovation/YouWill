package com.kangaroos.youwill;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


public class LetterChoice extends AppCompatActivity {

    Button button_write;
    final int[] mDay = new int[3];
    String number;
    String sdate;
    int letterNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_letter_choice);

        button_write = findViewById(R.id.button_write);
        EditText editText = findViewById(R.id.edittext_recipient_number);
        RadioGroup radioGroup = findViewById(R.id.radio_group);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.radio_button_one:
                        letterNumber = 0;
                        break;
                    case R.id.radio_button_two:
                        letterNumber = 1;
                        break;
                    case R.id.radio_button_three:
                        letterNumber = 2;
                        break;
                }
            }
        });

        button_write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                number = editText.getText().toString();
                Log.d("datew", String.valueOf(number));
                sdate = mDay[0]+""+mDay[1]+""+mDay[2];

                Intent intent = new Intent(LetterChoice.this, LetterWrite.class);
                intent.putExtra("recipient_number",number);
                intent.putExtra("send_date",sdate);
                intent.putExtra("letterNumber",letterNumber);
                Log.d("date", "intent");
                startActivity(intent);
            }
        });

    }
}
