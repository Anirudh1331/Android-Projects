package com.example.dell.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
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

    @Override
    public void onClick(View v) {
        int sum = Integer.parseInt(String.valueOf(name.getText())) + Integer.parseInt(password.getText().toString());
        Toast.makeText(this, "Addition of 2 Number is "+sum, Toast.LENGTH_SHORT).show();
    }
}
