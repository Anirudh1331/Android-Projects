package com.example.checkbox;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
LinearLayout ll;
Button bt;
CheckBox ck;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bt=findViewById(R.id.b1);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s="";
                for(int i=0;i<ll.getChildCount();i++){
                    CheckBox cc=(CheckBox) ll.getChildAt(i);
                    if(cc.isChecked())
                    {
                        s+=cc.getText().toString()+",";
                    }
                }
                Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
            }
        });
        ck=findViewById(R.id.ck);
        ck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(ck.isChecked())
                {
                    for(int i=0;i<ll.getChildCount();i++){
                        CheckBox cc=(CheckBox) ll.getChildAt(i);
                        cc.setChecked(true);
                    }
                }
                else
                {
                    for(int i=0;i<ll.getChildCount();i++){
                        CheckBox cc=(CheckBox) ll.getChildAt(i);
                        cc.setChecked(false);
                    }
                }
            }
        });
        ll=findViewById(R.id.ll);
        for(int i=0;i<100;i++){
            CheckBox c=new CheckBox(MainActivity.this);
            c.setText("CheckBox->"+i);
            c.setTextSize(24);
            ll.addView(c);
        }
    }
}
