package com.example.timer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class TimeStart extends AppCompatActivity {
EditText et;
Button bt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_start);
        et=findViewById(R.id.start_et);
        bt=findViewById(R.id.start_bt);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in=new Intent(TimeStart.this,MainActivity.class);
                in.putExtra("time",et.getText().toString());
                startActivity(in);
            }
        });
    }
}
