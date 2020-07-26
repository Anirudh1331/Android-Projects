package com.example.convert;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
EditText et1,et2;
Button bt1,bt2;
double z;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et1=findViewById(R.id.et1);
        et2=findViewById(R.id.et2);
        bt1=findViewById(R.id.button);
        bt2=findViewById(R.id.button2);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    z=Double.parseDouble(et1.getText().toString());
                    z=z*.6213;
                    DecimalFormat x=new DecimalFormat("##.##");
                    et2.setText(x.format(z));
                } catch (NumberFormatException e) {
                    Toast.makeText(MainActivity.this, "error", Toast.LENGTH_SHORT).show();
                }
            }
        });
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    z=Double.parseDouble(et2.getText().toString());
                    z=z/.6213;
                    DecimalFormat x=new DecimalFormat("##.##");
                    et1.setText(x.format(z));
                } catch (NumberFormatException e) {
                    Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
