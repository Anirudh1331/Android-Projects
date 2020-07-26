package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
EditText et1;
Button bt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et1=findViewById(R.id.et1);
        bt=findViewById(R.id.bt1);
        SharedPreferences sp=getSharedPreferences("mysp",MODE_PRIVATE);
        if(sp.getString("name","NA").equals("NA"))
        {

        }
        else
        {
            Intent in=new Intent(MainActivity.this,WelcomeActivity.class);
            startActivity(in);
            this.finish();
        }
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sp=getSharedPreferences("mysp",MODE_PRIVATE);
                SharedPreferences.Editor ed=sp.edit();
                ed.putString("name",et1.getText().toString());
                ed.commit();
                Intent in=new Intent(MainActivity.this,WelcomeActivity.class);
                startActivity(in);
                MainActivity.this.finish();
            }
        });
    }
}
