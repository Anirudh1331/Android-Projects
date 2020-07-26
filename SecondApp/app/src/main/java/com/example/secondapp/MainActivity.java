package com.example.secondapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    EditText name,password;
    Button btnsubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = findViewById(R.id.name);
        password = findViewById(R.id.pass);
        btnsubmit = findViewById(R.id.submit);
        btnsubmit.setOnClickListener(this);
    }
        public void onClick(View v){
        int sum= Integer.parseInt(name.getText().toString())+Integer.parseInt(password.getText().toString());
            Toast.makeText(this,"addition of 2 numbers is : "+sum,Toast.LENGTH_SHORT).show();
        }
}
