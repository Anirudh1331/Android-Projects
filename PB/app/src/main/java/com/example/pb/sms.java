package com.example.pb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class sms extends AppCompatActivity {
TextView tv;
    EditText et;
Button bt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);
        ActivityCompat.requestPermissions(sms.this,new String[]{Manifest.permission.SEND_SMS},1);
        et=findViewById(R.id.sms_s2);
        tv=findViewById(R.id.sms_s1);
        bt=findViewById(R.id.sms_bt1);
        Intent in=getIntent();
        String pno=in.getStringExtra("pno");
        tv.setText(pno);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SmsManager sm=SmsManager.getDefault();
                sm.sendTextMessage(tv.getText().toString(),null,et.getText().toString(),null,null);
                Toast.makeText(sms.this, "Message sent", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
