package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    Button b1,b2,b3,b4,b5,b6,b7,b8,b9,b10,b11,b12,b13,b14,b15,b16,b17,b18,b19,b20,b21,b22,b23,b24;
    TextView tv;
    float a,b;
    int k=0,n=0,m=0;
    String op,op1,op2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = findViewById(R.id.tv);
        b1=findViewById(R.id.b1);
        b2=findViewById(R.id.b2);
        b3=findViewById(R.id.b3);
        b4=findViewById(R.id.b4);
        b5=findViewById(R.id.b5);
        b6=findViewById(R.id.b6);
        b7=findViewById(R.id.b7);
        b8=findViewById(R.id.b8);
        b9=findViewById(R.id.b9);
        b10=findViewById(R.id.b10);
        b11=findViewById(R.id.b11);
        b12=findViewById(R.id.b12);
        b13=findViewById(R.id.b13);
        b14=findViewById(R.id.b14);
        b15=findViewById(R.id.b15);
        b16=findViewById(R.id.b16);
        b17=findViewById(R.id.b17);
        b18=findViewById(R.id.b18);
        b19=findViewById(R.id.b19);
        b20=findViewById(R.id.b20);
        b21=findViewById(R.id.b21);
        b22=findViewById(R.id.b22);
        b23=findViewById(R.id.b23);
        b24=findViewById(R.id.b24);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(k==0){
                    tv.setText(b1.getText());
                    k=1;
                    m=1;  //m is used to determine again and again click of operators
                }
                else
                tv.setText(tv.getText()+""+b1.getText());

            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(k==0){
                    tv.setText(b2.getText());
                    k=1;
                    m=1;
                }
                else
                tv.setText(tv.getText()+""+b2.getText());
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(k==0){
                    tv.setText(b3.getText());
                    k=1;
                    m=1;
                }
                else
                    tv.setText(tv.getText()+""+b3.getText());
            }
        });
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(k==0){
                    tv.setText(b5.getText());
                    k=1;
                    m=1;
                }
                else
                    tv.setText(tv.getText()+""+b5.getText());
            }
        });
        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(k==0){
                    tv.setText(b6.getText());
                    k=1;
                    m=1;
                }
                else
                    tv.setText(tv.getText()+""+b6.getText());
            }
        });
        b7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(k==0){
                    tv.setText(b7.getText());
                    k=1;
                    m=1;
                }
                else
                    tv.setText(tv.getText()+""+b7.getText());
            }
        });
        b9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(k==0){
                    tv.setText(b9.getText());
                    k=1;
                    m=1;
                }
                else
                    tv.setText(tv.getText()+""+b9.getText());
            }
        });
        b10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(k==0){
                    tv.setText(b10.getText());
                    k=1;
                    m=1;
                }
                else
                    tv.setText(tv.getText()+""+b10.getText());
            }
        });
        b11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(k==0){
                    tv.setText(b11.getText());
                    k=1;
                    m=1;
                }
                else
                    tv.setText(tv.getText()+""+b11.getText());
            }
        });
        b13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(k==0){
                    tv.setText(b13.getText());
                    k=1;
                    m=1;
                }
                else
                    tv.setText(tv.getText()+""+b13.getText());
            }
        });
        b14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(k==0){
                    tv.setText(b14.getText());
                    k=1;
                    m=1;
                }
                else
                    tv.setText(tv.getText()+""+b14.getText());
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(m==1)
                if(op!=null){
                    b=Float.parseFloat(tv.getText().toString());
                    calc();
                }
                a=Float.parseFloat(tv.getText().toString());
                op=b4.getText().toString();
                op2=b4.getText().toString();
                k=0;
                m=0;
            }
        });
        b8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(m==1)
                if(op!=null){
                    b=Float.parseFloat(tv.getText().toString());
                    calc();
                }
                a=Float.parseFloat(tv.getText().toString());
                op=b8.getText().toString();
                op2=b8.getText().toString();
                k=0;
                m=0;
            }
        });
        b12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(m==1)
                if(op!=null){
                    b=Float.parseFloat(tv.getText().toString());
                    calc();
                }
                a=Float.parseFloat(tv.getText().toString());
                op=b12.getText().toString();
                op2=b12.getText().toString();
                k=0;
                m=0;
            }
        });
        b15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(m==1)
                if(op!=null){
                    b=Float.parseFloat(tv.getText().toString());
                    calc();
                }
                a=Float.parseFloat(tv.getText().toString());
                op=b15.getText().toString();
                k=0;
                op2=b15.getText().toString();
                m=0;
            }
        });
        b16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(m==1)
                if(op!=null){
                    b=Float.parseFloat(tv.getText().toString());
                    calc();
                }
                a=Float.parseFloat(tv.getText().toString());
                op=b16.getText().toString();
                k=0;
                op2=b16.getText().toString();
                m=0;
            }
        });
        b17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv.setText("0");
                k=0;
                op=null;
                a=0;
                b=0;
            }
        });
        b18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                op1=tv.getText().toString();
                op1=op1.substring(0,op1.length()-1);
                tv.setText(op1);
            }
        });
        b19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(n==0){
                    b1.setEnabled(false);
                    b2.setEnabled(false);
                    b3.setEnabled(false);
                    b4.setEnabled(false);
                    b5.setEnabled(false);
                    b6.setEnabled(false);
                    b7.setEnabled(false);
                    b8.setEnabled(false);
                    b9.setEnabled(false);
                    b10.setEnabled(false);
                    b11.setEnabled(false);
                    b12.setEnabled(false);
                    b13.setEnabled(false);
                    b14.setEnabled(false);
                    b15.setEnabled(false);
                    b16.setEnabled(false);
                    b17.setEnabled(false);
                    b18.setEnabled(false);
                    b20.setEnabled(false);
                    n=1;
                    tv.setText("");
                }
                else{
                    b1.setEnabled(true);
                    b2.setEnabled(true);
                    b3.setEnabled(true);
                    b4.setEnabled(true);
                    b5.setEnabled(true);
                    b6.setEnabled(true);
                    b7.setEnabled(true);
                    b8.setEnabled(true);
                    b9.setEnabled(true);
                    b10.setEnabled(true);
                    b11.setEnabled(true);
                    b12.setEnabled(true);
                    b13.setEnabled(true);
                    b14.setEnabled(true);
                    b15.setEnabled(true);
                    b16.setEnabled(true);
                    b17.setEnabled(true);
                    b18.setEnabled(true);
                    b20.setEnabled(true);
                    a=0;
                    b=0;
                    k=0;
                    n=0;
                    op=null;
                    tv.setText("0");
                }
            }
        });
        b20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                b=Float.parseFloat(tv.getText().toString());
                calc();
                op=null;
                k=0;
            }
        });
        b21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv.setText(b21.getText()+"(");
                op="inv1";
                k=0;
            }
        });
        b22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv.setText(b22.getText()+"(");
                op="inv2";
                k=0;
            }
        });
        b23.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv.setText(b23.getText()+"(");
                op="inv3";
                k=0;
            }
        });
        b24.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(m==1)
                    if(op!=null){
                        b=Float.parseFloat(tv.getText().toString());
                        calc();
                    }
                a=Float.parseFloat(tv.getText().toString());
                op=b24.getText().toString();
                calc();
                k=0;
                m=0;
                op=null;
            }
        });
    }
    void calc(){
        float z=1;
        int i;
        double c=1;
        if(op.equals("+"))
            z=a+b;
        else if(op.equals("-"))
            z=a-b;
        else if(op.equals("*"))
            z=a*b;
        else if(op.equals("/"))
            z=a/b;
        else if(op.equals("%"))
            z=a%b;
        else if(op.equals("!"))
        {
            z=1;
            for(i=2;i<=a;i++)
                z*=i;
        }
        else if(op.equals("inv1"))
        {
            c=Double.parseDouble(tv.getText().toString());
            c=Math.toDegrees(Math.asin(c));
        }
        else if(op.equals("inv2"))
        {
            c=Double.parseDouble(tv.getText().toString());
            c=Math.toDegrees(Math.acos(c));
        }
        else
        {
            c=Double.parseDouble(tv.getText().toString());
            c=Math.toDegrees(Math.atan(c));
        }
        if(op.equals("inv1") || op.equals("inv2") || op.equals("inv3"))
        tv.setText(""+String.format("%.2f", c));
        else
            tv.setText(""+z);
    }

    @Override
    public void onClick(View view) {
        if(view == b1){
            Toast.makeText(this, ""+b1.getText(), Toast.LENGTH_SHORT).show();
        }

    }
}
