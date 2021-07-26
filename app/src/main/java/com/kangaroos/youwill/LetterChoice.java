package com.kangaroos.youwill;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


public class LetterChoice extends AppCompatActivity {

    Button button_write;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_letter_choice);

        button_write = findViewById(R.id.button_write);
        EditText editText = findViewById(R.id.edittext_recipient_number);
        DatePicker datePicker = findViewById(R.id.datePicker);

        String number = editText.getText().toString();

        final int[] mDay = new int[3];


        DatePicker.OnDateChangedListener mOnDateChangedListener = new DatePicker.OnDateChangedListener(){

            @Override

            public void onDateChanged(DatePicker datePicker, int yy, int mm, int dd) {

                mDay[0] = yy;

                mDay[1] = mm;

                mDay[2] = dd;

            }

        };
        datePicker.init(datePicker.getYear(),datePicker.getMonth()+1,datePicker.getDayOfMonth(),mOnDateChangedListener);
        String sdate = mDay[0]+""+mDay[1]+1+""+mDay[2];
        button_write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LetterChoice.this, LetterWrite.class);
                intent.putExtra("recipient_number",number);
                intent.putExtra("send_date",sdate);
                startActivity(intent);
            }
        });
    }

}
