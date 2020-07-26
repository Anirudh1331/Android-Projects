package com.example.womensecapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
Button bt1,bt2,bt3,bt4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bt1=findViewById(R.id.bt_up_police);
        bt2=findViewById(R.id.bt_in_police);
        bt3=findViewById(R.id.bt_up_user);
        bt4=findViewById(R.id.bt_in_user);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in=new Intent(MainActivity.this,SignUpPolice.class);
                startActivity(in);
            }
        });
    }
}
