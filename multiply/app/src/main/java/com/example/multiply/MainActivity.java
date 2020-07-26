package com.example.multiply;

import androidx.appcompat.app.AppCompatActivity;

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
        name=findViewById(R.id.name);
        password=findViewById(R.id.pass);
        btnsubmit=findViewById(R.id.submit);
        btnsubmit.setOnClickListener(this);
    }
    public void onClick(View v){
        int multi= Integer.parseInt(name.getText().toString())*Integer.parseInt(password.getText().toString());
        Toast.makeText(this,"multiplication of two numbers is : "+multi,Toast.LENGTH_SHORT).show();
    }

}
