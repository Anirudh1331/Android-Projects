package com.example.timer;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
TextView tv1,tv2;
Button b1,b2,b3;
int k=0,a,b;
int time;

    @Override
    protected void onStart() {
        a=(time/1000)/60;
        b=(time/1000)%60;
        String str=String.format(Locale.getDefault(),"%02d:%02d",a,b);
        if(time==0)
            tv1.setText("00:00");
        else
        tv1.setText(str);
        super.onStart();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv1=findViewById(R.id.tv1);
        tv2=findViewById(R.id.tv2);
        b1=findViewById(R.id.b1);
        b2=findViewById(R.id.b2);
        b3=findViewById(R.id.b3);
        Intent in=getIntent();
        time=Integer.parseInt(in.getStringExtra("time"))*1000;

        final CountDownTimer ct=new CountDownTimer(time,1000)
        {
            @Override
            public void onTick(long l) {
                a=(int)(l/1000)/60;
                b=(int)(l/1000)%60;
                String str=String.format(Locale.getDefault(),"%02d:%02d",a,b);
                tv1.setText(str);
            }

            @Override
            public void onFinish() {

            }
        };

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ct.start();

            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ct.cancel();
                AlertDialog.Builder x=new AlertDialog.Builder(MainActivity.this);
                x.setTitle("Alert");
                x.setMessage("want to restart or continue");
                x.setPositiveButton("Restart", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        ct.cancel();
                        a=(time/1000)/60;
                        b=(time/1000)%60;
                        String str=String.format(Locale.getDefault(),"%02d:%02d",a,b);
                        tv1.setText(str);
                        tv2.setText("");
                        k=0;
                    }
                });
                x.setNegativeButton("continue", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        ct.cancel();
                    }
                });
                x.show();
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(k==0) {
                    tv2.setText(tv2.getText().toString() + tv1.getText().toString());
                    k = 1;
                }
                else
                    tv2.setText(tv2.getText().toString()+"\n"+tv1.getText().toString());
            }
        });
    }
}
