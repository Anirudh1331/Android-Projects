package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class WelcomeActivity extends AppCompatActivity {
TextView tv;
Button bt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        SharedPreferences sp=getSharedPreferences("mysp",MODE_PRIVATE);
        tv=findViewById(R.id.tv);
        String name=sp.getString("name","NA");
        tv.setText("Welcome "+name);
        bt=findViewById(R.id.bt2);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sp=getSharedPreferences("mysp",MODE_PRIVATE);
                SharedPreferences.Editor ed=sp.edit();
                ed.putString("name","NA");
                ed.commit();
                Intent in=new Intent(WelcomeActivity.this,MainActivity.class);
                startActivity(in);
            }
        });
    }
}
