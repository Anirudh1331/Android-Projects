package com.example.pb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class GroupSMS extends AppCompatActivity {
EditText gsms_et1,gsms_et2;
Button gsms_bt1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_s_m_s);
        ActivityCompat.requestPermissions(GroupSMS.this,new String[]{Manifest.permission.SEND_SMS},1);
        gsms_et1=findViewById(R.id.gsms_et1);
        gsms_bt1=findViewById(R.id.gsms_bt1);
        gsms_et2=findViewById(R.id.gsms_et2);
        gsms_bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SmsManager sm=SmsManager.getDefault();
                SQLiteDatabase db=openOrCreateDatabase("pb",MODE_PRIVATE,null);
                String query="select * from contacts where grp='"+gsms_et1.getText().toString()+"' ";
                Cursor c=db.rawQuery(query,null);
                if(c.moveToFirst()){
                    do{
                        sm.sendTextMessage(c.getString(1),null,gsms_et2.getText().toString(),null,null);
                        Toast.makeText(GroupSMS.this, "Messages sent", Toast.LENGTH_SHORT).show();
                    }while (c.moveToNext());
                }
            }
        });
    }
}
